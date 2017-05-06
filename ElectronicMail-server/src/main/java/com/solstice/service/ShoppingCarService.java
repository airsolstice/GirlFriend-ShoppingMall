package com.solstice.service;

import java.util.List;

import com.solstice.bean.ShoppingCar;
import com.solstice.bean.ShoppingCarDto;

public interface ShoppingCarService {

	/**
	 * 加车
	 */
	public Long saveCar(ShoppingCarDto dto);
	/**
	 * 获取购物车
	 * @param dto
	 * @return
	 */
	public List<ShoppingCar> getShoppingCarList(ShoppingCarDto dto);
	
	
	/**
	 * 删除购物车商品
	 * @param dto
	 * @return
	 */
	public void deleteCarGoodsByGdId(ShoppingCarDto dto);
	
}
