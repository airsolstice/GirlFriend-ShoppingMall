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
import com.solstice.bean.Result;
import com.solstice.bean.ResultCode;
import com.solstice.bean.ShoppingCar;
import com.solstice.bean.ShoppingCarDto;
import com.solstice.service.ShoppingCarService;

@Controller
@RequestMapping(value = "/car")
public class ShoppingCarController {
	@Autowired  ShoppingCarService  shoppingCarService;
	
	/**
	 * 1。加车
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/saveCar", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.POST)
	public @ResponseBody String saveCar(@ModelAttribute ShoppingCarDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		System.out.println(JSONArray.toJSONString(dto));
		if(StringUtils.isEmpty(dto.getUserId())||dto.getNum()==null||dto.getGoodsId()==null){
			result = new Result(ResultCode.FAIL, "err", "用户userId 和 num,goodsId不能为空");
			return result.toString();
		}
		try{
			shoppingCarService.saveCar(dto);
			ShoppingCar vo=new ShoppingCar();
			vo.setId(dto.getId());
			result = new Result(ResultCode.SUCESS, "加车成功！",vo);
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * 2.获取购物车列表
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/getShoppingCarList", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.GET)
	public @ResponseBody String getShoppingCarPage(@ModelAttribute ShoppingCarDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		try{
			if(StringUtils.isEmpty(dto.getUserId())){
				result = new Result(ResultCode.FAIL, "err", "userId 不能为空");
				return result.toString();
			}
			
			List<ShoppingCar> list=shoppingCarService.getShoppingCarList(dto);
			if (list == null) {
				result = new Result(ResultCode.FAIL, "获取对象为空", null);
			} else if (list.size() == 0) {
				result = new Result(ResultCode.SUCESS, "购物车是空的", list);
			} else {
				result = new Result(ResultCode.SUCESS, "获取购物车成功",
						list);
			}
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
	
	
	/**
	 * 1。加车
	 * @param dto
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/deleteCarGoodsByGdId", produces = { "application/json;charset=UTF-8",
	"text/json;charset=UTF-8" }, method = RequestMethod.POST)
	public @ResponseBody String deleteCarGoodsByGdId(@ModelAttribute ShoppingCarDto dto, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {
		Result result = null;
		System.out.println(JSONArray.toJSONString(dto));
		if(StringUtils.isEmpty(dto.getUserId())){
			result = new Result(ResultCode.FAIL, "err", "用户userId 不能为空");
			return result.toString();
		}
		try{
			shoppingCarService.deleteCarGoodsByGdId(dto);
			result = new Result(ResultCode.SUCESS, "删除购物车成功！");
		} catch (Exception e) {
			result = new Result(ResultCode.FAIL, "err", e.getMessage());
			e.printStackTrace();
		}
		return result.toString();
	}
	

}
