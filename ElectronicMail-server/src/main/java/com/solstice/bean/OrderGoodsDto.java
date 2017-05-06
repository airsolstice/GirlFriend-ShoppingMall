package com.solstice.bean;

import java.io.Serializable;

/**
*
*@author 
*@date 2017年4月27日 下午5:53:26
*
**/
public class OrderGoodsDto implements Serializable{
	 private static final long serialVersionUID = 1L;
	 public        Long       id;//
	 public        Long       goodsId;//商品id
	 public        Long       orderId;//商品id
	 public        String       userId;//用户id
	 public        Long       num;//商品数量
	 public        Double     totalPrice;//总价格 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	 
}

