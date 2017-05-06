package com.solstice.mapper;

import java.util.List;

import com.solstice.bean.ShoppingCar;
import com.solstice.bean.ShoppingCarDto;

public interface ShoppingCarMapper {
    //加车
	public void insertCar(ShoppingCarDto dto);
	
    //更新购物车
	public void updateCar(ShoppingCarDto dto);
	
   //获取购物车
	public List<ShoppingCar> getShoppingCarList(ShoppingCarDto dto);
	
	//根据商品id 清购物车
	
	public void deleteCarGoodsByGdId(ShoppingCarDto dto);
	
	
	//根据商品id 和用户id 查询购物车是否有改商品，有就查出商品数量
	
	public List<ShoppingCar> getCarGoodsBYGdId(ShoppingCarDto dto);
}
