package com.solstice.service;

import java.util.List;

import com.solstice.bean.Order2;
import com.solstice.bean.OrderDto;

/**
 *
 * @author lingchao
 * @date 2017年4月27日 下午7:31:00
 *
 **/
public interface OrderService {
	/**
	 * 保存订单
	 * 
	 * @param dto
	 */
	public void saveOrder(OrderDto dto);

	/**
	 * 查询订单
	 * 
	 * @param dto
	 */
	public List<Order2> getOrderList(OrderDto dto);
	
	/**
	 * 查询订单定数量
	 * 
	 * @param dto
	 */
	public Long getOrdersCount(OrderDto dto);
	
	
	/**
	 * 查询订单
	 * 
	 * @param dto
	 */
	public List<Order2> getAllOrderList(OrderDto dto);
	
	/**
	 * 查询订单定数量
	 * 
	 * @param dto
	 */
	public Long getAllOrdersCount(OrderDto dto);
	/**
	 * 删除订单
	 * 
	 * @param dto
	 */
	public void deleteOrder(OrderDto dto);



}
