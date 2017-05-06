package com.solstice.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.solstice.bean.Order2;
import com.solstice.bean.OrderDto;
import com.solstice.bean.OrderGoods;
import com.solstice.bean.OrderGoodsDto;
import com.solstice.bean.ShoppingCarDto;
import com.solstice.mapper.OrderMapper;
import com.solstice.service.OrderService;
import com.solstice.service.ShoppingCarService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
*
*@author
*@date 2017年4月27日 下午7:36:12
*
**/
@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderMapper orderMapper;
	@Autowired  ShoppingCarService  shoppingCarService;
	
	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
	/**
	 * 保存订单
	 * @param dto
	 */
	@Override
	public void saveOrder(OrderDto dto){
		try{
		if (!StringUtils.isEmpty(dto.getOrdersGoosStr())) {
			JSONArray jsonArr = JSONArray.fromObject(dto.getOrdersGoosStr());
			List<OrderGoodsDto> list = new ArrayList<OrderGoodsDto>();
			Iterator<JSONObject> it = jsonArr.iterator();
			while (it.hasNext()) {
				JSONObject json2 = it.next();
				OrderGoodsDto vo = (OrderGoodsDto) JSONObject.toBean(json2, OrderGoodsDto.class);
				list.add(vo);
			}
			dto.setOrdersGoos(list);
		}
		orderMapper.saveOrder(dto);//保存订单
		if(dto.getOrdersGoos()!=null&&dto.getOrdersGoos().size()>0){//商品详情写入订单号
			for(OrderGoodsDto vo :dto.getOrdersGoos()){
				vo.setOrderId(dto.getId());
				vo.setUserId(dto.getUserId());
				 ShoppingCarDto dto2= new ShoppingCarDto();
				 dto2.setUserId(dto.getUserId());
				 dto2.setGoodsId(vo.getGoodsId());
				 shoppingCarService.deleteCarGoodsByGdId(dto2);//保订单前先清购物车对应商品
			}
		orderMapper.saveGoodsOrder(dto.getOrdersGoos());
		}
		
		}catch(Exception e){
			log.error("saveOrder  failed");
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 *查询订单
	 * @param dto
	 */
	@Override
	public  List<Order2> getOrderList(OrderDto dto){
		List<Order2>  list=null;
		try{
			   list=orderMapper.getOrders(dto);
			   if(list!=null&&list.size()>0){
				   for(Order2 vo:list){
					   OrderGoodsDto dto2=new OrderGoodsDto();
					   dto2.setOrderId(vo.getId());
					   List<OrderGoods> list1=orderMapper.getGoodsOrders(dto2);
					   if(list1!=null&&list1.size()>0){
						   vo.setOrdersGoos(list1);
					   }
				   }
			   }
		
		}catch(Exception e){
			log.error("getOrderList  failed");
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 *查询订单
	 * @param dto
	 */
	@Override
	public  Long getOrdersCount(OrderDto dto){
		return orderMapper.getOrdersCount(dto);
	}

	
	/**
	 *查询订单
	 * @param dto
	 */
	@Override
	public  List<Order2> getAllOrderList(OrderDto dto){
		List<Order2>  list=null;
		try{
			   list=orderMapper.getAllOrders(dto);
			   if(list!=null&&list.size()>0){
				   for(Order2 vo:list){
					   OrderGoodsDto dto2=new OrderGoodsDto();
					   dto2.setOrderId(vo.getId());
					   List<OrderGoods> list1=orderMapper.getGoodsOrders(dto2);
					   if(list1!=null&&list1.size()>0){
						   vo.setOrdersGoos(list1);
					   }
				   }
			   }
		
		}catch(Exception e){
			log.error("getOrderList  failed");
			e.printStackTrace();
		}
		return list;
	}

	
	/**
	 *查询订单
	 * @param dto
	 */
	@Override
	public  Long getAllOrdersCount(OrderDto dto){
		return orderMapper.getAllOrdersCount(dto);
	}

	/**
	 * 删除订单
	 * @param dto
	 */
	@Override
	public void deleteOrder(OrderDto dto){
		try{
		orderMapper.deleteOrder(dto);//删除订单
		orderMapper.deleteOrderDetail(dto);//删除订单详情
		}catch(Exception e){
			log.error("deleteOrder  failed");
			e.printStackTrace();
		}
		
	}


}

