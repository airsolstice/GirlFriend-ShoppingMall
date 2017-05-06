
package com.solstice.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import com.alibaba.fastjson.JSONObject;


public class User {
	//用户id，在移动端使用手机号，在web端使用邮箱
    @NotNull(message = "id不能为空") 
    @Email(message = "id只能为邮箱账号")
	private String id;
	//用户名
	private String name;
	//密码
	@Length(min = 6, max = 18, message = "密码长度只能在6-18位之间")
	private String pwd;
	//邮箱
	@Email(message = "邮箱格式不正确")
	private String email;																									
	//0-保密，1-男，2-女
	@Min(value = 0, message = "性别的值只能为0，1，2")
	@Max(value = 2, message = "性别的值只能为0，1，2")
	private int sex = 0;
	//手机号码
	@Phone(value = "phone", message = "手机号码格式不正确")
	private String phone;
	//激活状态 1-已激活 ，0-未激活
	@Min(value = 0, message = "激活状态只能是0或1")
	@Max(value = 1, message = "激活状态只能是0或1")
	private int status;
	//激活码
	@Max(value = 64, message = "激活码最大长度为64位UUID")
	private String activeCode;
	
	private Long isAdmin;//0:不是  管理员 1：是管理员
	
	
	


	public Long getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Long isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User(){}
	
	public User(String id, String pwd){
		this.id = id;
		this.pwd = pwd;
	}
	
	//用户个人信息获取
	public User(String id, String name, String email, int sex, String phone){
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.phone = phone;
	}
	

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getActiveCode() {
		return activeCode;
	}

	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}
}
