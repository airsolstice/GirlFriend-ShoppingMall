package com.solstice.impl;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.solstice.bean.ShoppingCar;
import com.solstice.bean.ShoppingCarDto;
import com.solstice.mapper.ShoppingCarMapper;
import com.solstice.service.ShoppingCarService;

@Service
public class ShoppingCarServiceImpl implements ShoppingCarService{
	private static final Logger log = LoggerFactory.getLogger(ShoppingCarServiceImpl .class);
	@Autowired
	private ShoppingCarMapper  shoppingCarMapper;
	/**
	 *加车
	 * @param dto
	 */
	@Override
	public Long saveCar(ShoppingCarDto dto){
		Long id=null;
		try{
			//如购物车里面已有该商品那么更新商品数量，用于两次加车情况
		List<ShoppingCar>  list=shoppingCarMapper.getCarGoodsBYGdId(dto);
		if(list!=null&&list.size()>0){
			ShoppingCar vo=list.get(0);
			dto.setId((vo.getId()!=null?vo.getId():null));
			dto.setNum((vo.getNum()!=null?vo.getNum()+dto.getNum():dto.getNum()));
		}
		if(dto.getId()==null){//新增加车
			shoppingCarMapper.insertCar(dto);
		}else{//修改加车
			shoppingCarMapper.updateCar(dto);
		}
		}catch(Exception e){
			log.error("saveOrder  failed");
			e.printStackTrace();
		}
		id=dto.getId();
		return id;
	}
	
	
	/**
	 *查询订单
	 * @param dto
	 */
	@Override
	public List<ShoppingCar> getShoppingCarList(ShoppingCarDto dto){
		List<ShoppingCar>  list=null;
		try{
			   list=shoppingCarMapper.getShoppingCarList(dto);
		}catch(Exception e){
			log.error("getShoppingCarList  failed");
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 删除购物车商品
	 * @param dto
	 * @return
	 */
	public void deleteCarGoodsByGdId(ShoppingCarDto dto){
		try{
			shoppingCarMapper.deleteCarGoodsByGdId(dto);
		}catch(Exception e){
			log.error("deleteCarGoodsByGdId  failed");
			e.printStackTrace();
		}
	}


}
