package com.solstice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.solstice.bean.Order2;
import com.solstice.bean.OrderDto;
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.service.OrderService;

import net.sf.json.JSONObject;

/**
*
*@author 
*@date 2017年4月27日 下午5:49:07
*
**/
@Controller
@RequestMapping(value = "/order")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	/**
	 * 1.提交订单
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveOrder", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.POST)
	public @ResponseBody String saveOrder(@ModelAttribute OrderDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		System.out.println(JSONArray.toJSONString(dto));
		try{
			orderService.saveOrder(dto);
			result = new Result(ResultCode.SUCESS, "订单已生成！");
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

	
	/**
	 * 2.获取分页订单
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getOrderPage", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.GET)
	public @ResponseBody String getOrderPage(@ModelAttribute OrderDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		try{
			if(StringUtils.isEmpty(dto.getUserId())||dto.getIndex()==null){
				result = new Result(ResultCode.FAIL, "err", "用户id 和 index页数不能为空");
				return result.toString();
			}
			
			List<Order2> list=orderService.getOrderList(dto);
			Long  totalCounts=orderService.getOrdersCount(dto);
			Long  totalpage=null;
			if(totalCounts!=null){
				totalpage= (totalCounts  +  dto.getRows() - 1)/dto.getRows() ;  
			}else{
				totalpage=0L;
			}

			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "没有订单", list);
			} else {
				result = new Result(ResultCode.SUCESS, String.format("获取第%s页商品", dto.getIndex()),list,totalpage);
			}
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * 3.管理员获取分页所有订单
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getAllOrderPage", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.GET)
	public @ResponseBody String getAllOrderPage(@ModelAttribute OrderDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		try{
			if(dto.getIndex()==null){
				result = new Result(ResultCode.FAIL, "err", "index页数不能为空");
				return result.toString();
			}
			dto.setRows(4L);
			dto.setStartNum();
			System.out.println(JSONArray.toJSONString(dto));
			List<Order2> list=orderService.getAllOrderList(dto);
			Long  totalCounts=orderService.getAllOrdersCount(dto);
			Long  totalpage=null;
			if(totalCounts!=null){
				totalpage= (totalCounts  +  dto.getRows() - 1)/dto.getRows() ;  
			}else{
				totalpage=0L;
			}

			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "没有订单", list);
			} else {
				result = new Result(ResultCode.SUCESS, String.format("获取第%s页商品", dto.getIndex()),list,totalpage);
			}
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}


	
	/**
	 * 4.删除订单
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteOrder", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.GET)
	public @ResponseBody String deleteOrder(@ModelAttribute OrderDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		System.out.println(JSONArray.toJSONString(dto));
		try{
			if(dto.getId()==null){
				result = new Result(ResultCode.FAIL, "err", "订单Id不能为空！");
				return result.toString();
			}
			orderService.deleteOrder(dto);
			result = new Result(ResultCode.SUCESS, "订单已删除！");
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}

}

