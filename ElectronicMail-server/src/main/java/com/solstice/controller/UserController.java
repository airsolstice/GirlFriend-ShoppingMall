package com.solstice.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.bean.User;
import com.solstice.exception.UserException;
import com.solstice.service.UserService;
import com.solstice.utils.MailUtil;
import com.solstice.utils.Utils;

@Controller
@RequestMapping(value = "/user")
public class UserController {
	@Autowired
	private UserService userService;

	/*
	 * 登录
	 */
	@ResponseBody
	@RequestMapping(value = "/login", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String login(HttpServletRequest request, @ModelAttribute User user) {
		Result result = null;
		Map<String, String> errors = new HashMap<String, String>();
		errors = Utils.validateModel(user);
		System.out.println(user.toString());
		
		if (errors.size() > 0) {
			// 保存错误信息
			request.setAttribute("errors", errors);
			result = new Result(ResultCode.FAIL, "表单验证失败", errors);
			
			return result.toString();
		}
		
		try {
			if(user.getIsAdmin()!=null&&user.getIsAdmin()==1){
		    	User _user = userService.adminLogin(user);
				result = new Result(ResultCode.SUCESS, "管理员登录成功", _user);
		    }else{
				User _user = userService.login(user);
				result = new Result(ResultCode.SUCESS, "登录成功", _user);
		    }

			
		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			return result.toString();
		}
		System.out.println(result.toString());
		return result.toString();
	}

	/*
	 * 注册 获取基本信息，补全user信息(激活码) 验证输入合法性 验证用户名和邮箱是否已经注册 发送激活码到邮箱 转向登录界面
	 */
	@ResponseBody
	@RequestMapping(value = "/regist", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String regist(HttpServletRequest request, User user) {
		Result result = null;
		// 验证属性的合法性
		Map<String, String> errors = new HashMap<>();
		
		String activeCode = UUID.randomUUID().toString();
		user.setActiveCode(activeCode);
		// 根据id的类别，覆盖相应的属性
		String id = user.getId();
		if (!Utils.isEmpty(id)) {
			if (Utils.isEmail(id)) {
				user.setEmail(id);
			} else if (Utils.isPhone(id)) {
				user.setPhone(id);
			}else {
				errors.put("id", "id只能为邮箱");
			}
			
		}else{
				errors.put("id", "id不能为空");
		}

		//Map<String, String> errors = Utils.validateModel(user);
		System.out.println("\n\n\n\n"+user.toString()+"\n\n\n\n"+errors+"\n\n\n\n");
		
		if (errors.size() > 0) {
			// 保存错误信息
			request.setAttribute("errors", errors);
			result = new Result(ResultCode.FAIL, "表单验证失败", errors);
			return result.toString();
		}

		try {
			if(user.getIsAdmin()!=null&&user.getIsAdmin()==1){
			// 调用service，验证手机号和邮箱是否已经被注册
			userService.findUserByAdminUserId(user.getId());
			}else{
			userService.findUserByUserId(user.getId());
			}
			//userService.findUserByEmail(user.getEmail());
			//userService.findUserByUserPhone(user.getPhone());
			// 添加用户
			if(user.getIsAdmin()!=null&&user.getIsAdmin()==1){
				userService.addAdminUser(user);
			}else{
				userService.addUser(user);
			}

			if(user.getIsAdmin()!=null&&user.getIsAdmin()==1){
				
			}else{
				String text = "你正在使用该邮箱进行账号激活，请点击下面的链接完成激活；如不是本人操作，请忽略"
						+ System.getProperty("line.separator", "\n")
						+ "<a href=\"http://localhost:8080"
						+ request.getContextPath() + "/user/active?activeCode="
						+ user.getActiveCode() + "\"></a>";
				MailUtil.sendMail("账号激活", text, new String[] { user.getId() });
			}

		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			return result.toString();
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			return result.toString();
		}
		if(user.getIsAdmin()!=null&&user.getIsAdmin()==1){
			result = new Result(ResultCode.SUCESS, "管理员注册完成");
		}else{
			result = new Result(ResultCode.SUCESS, "注册完成，请完成激活", "请到邮箱" + user.getId()
			+ "完成激活操作。如已操作请点击下面的按钮去登录" );	
		}
		return result.toString();
	}

	/*
	 * 激活账号 根据激活码查找id 将查找到的id设置其status为1(激活状态)
	 */
	@ResponseBody
	@RequestMapping(value = "/active", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String active(String activeCode) {
		Result result = null;
		String id = null;
		try {
			id = userService.findIdByCode(activeCode);
			userService.active(id);
		} catch (UserException e) {
			e.printStackTrace();
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			return result.toString();
		}
		
		result = new Result(ResultCode.SUCESS, String.format("[%s]激活成功", id), id);
		return result.toString();
	}

	@ResponseBody
	@RequestMapping(value = "/forgetpwd", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String forgetpwd(HttpServletRequest request, String email, String pwd,Long isAdmin) {
		Result result = null;
		Map<String, String> errors = new HashMap<String, String>();

		if (Utils.isEmpty(email)) {
			errors.put("email", "邮箱不能为null");
		} else if (!Utils.isEmail(email)) {
			errors.put("email", "邮箱格式不正确");
		}

		try {
			if(isAdmin!=null&&isAdmin==1){
				userService.checkAdminEmail(email);
			}else{
				userService.checkEmail(email);
			}

		} catch (UserException e) {
			errors.put("email", e.getMessage());
		}

		// 密码的判断
		if (Utils.isEmpty(pwd)) {
			errors.put("pwd", "密码不能为null");
		} else if (pwd.trim().length() > 18 || pwd.trim().length() < 6) {
			errors.put("pwd", "密码长度必须介于6~18之间");
		}
		if (errors.size() > 0) {
			result = new Result(ResultCode.FAIL, "err",errors);
			return result.toString();
		}
		
		try {
			if(isAdmin!=null&&isAdmin==1){
				userService.updateAdminPwd(email, Utils.MD5(pwd));
			}else {
				String text = "你正在使用该邮箱找回密码，请点击下面的链接完成密码找回；如不是本人操作，请忽略"
						+ System.getProperty("line.separator", "\n")
						+ "<a href=\"http://localhost:8080" + request.getContextPath()
						+ "/user/retrievePwd?email=" + email + "&pwd=" + Utils.MD5(pwd)
						+ "\"></a>";
				MailUtil.sendMail("找回密码", text, new String[] { email });
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			return result.toString();
			
		}
		if(isAdmin!=null&&isAdmin==1){
			result=new Result(ResultCode.SUCESS, "管理员密码修改成功！");
		}else{
			// 发送了消息之后应该转到一个信息提示页面，提示已经提交，请到邮箱去找回密码
			result = new Result(ResultCode.SUCESS, "请到输入的邮箱完成更改操作", "请到邮箱" + email + "完成密码找回操作。如已操作请点击下面的链接去登录"
					+ System.getProperty("line.separator")
					+ "<a href=\"http://localhost:8080"
					+ request.getContextPath() + "/user/toLogin");
		}
		
		return result.toString();
	}


	// 找回密码
	@ResponseBody
	@RequestMapping(value = "/retrievePwd", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" })
	public String retrievePwd(HttpServletRequest request, String email,
			String pwd,Long isAdmin) {
		Result result = null;
		try {
			if(isAdmin!=null&&isAdmin==1){
				userService.updateAdminPwd(email, pwd);
				result = new Result(ResultCode.SUCESS, "管理员密码更新成功",email);	
			}else{
				userService.updatePwd(email, pwd);
				result = new Result(ResultCode.SUCESS, "密码更新成功",email);

			}
		} catch (UserException e) {
			result = new Result(ResultCode.FAIL, "err",e.getMessage());
			return result.toString();
		}
		return result.toString();
	}

	// 退出
	@RequestMapping(value = "/loginOut")
	public String loginOut(HttpServletRequest request) {
		// 返回登录界面
		return "login";
	}

	@RequestMapping("/index")
	public String index() {
		return "../index";
	}

}
