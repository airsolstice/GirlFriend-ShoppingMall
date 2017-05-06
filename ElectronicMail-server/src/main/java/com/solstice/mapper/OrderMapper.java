package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.Order2;
import com.solstice.bean.OrderDto;
import com.solstice.bean.OrderGoods;
import com.solstice.bean.OrderGoodsDto;

/**
*
*@author 
*@date 2017年4月27日 下午7:46:16
*
**/
public interface OrderMapper {
    //提交订单
	public void saveOrder(OrderDto dto); //dto里边定义了入参
    //提交商品详情
	public void saveGoodsOrder( List<OrderGoodsDto>  list);
	//获取订单   
	public List<Order2> getOrders(OrderDto dto);
	
	//获取所有订单   
	public List<Order2> getAllOrders(OrderDto dto);
	
	//获取订单商品   
	public List<OrderGoods> getGoodsOrders(OrderGoodsDto dto);
	//获取订单总数
    public Long getOrdersCount(OrderDto dto);
    
	//获取所有订单总数
    public Long getAllOrdersCount(OrderDto dto);
    
	//删除订单
    public void deleteOrder(OrderDto dto);
	
	//删除订单详情
    public void deleteOrderDetail(OrderDto dto);
}

