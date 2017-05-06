package com.solstice.bean;

import java.io.Serializable;
import java.util.List;

/**
*
*@author 
*@date 2017年4月27日 下午8:48:55
*
**/
public class Order2 implements Serializable{
	 private static final long serialVersionUID = 1L;
	 public        Long       id;//订单id
	 public        String       userId;//用户id
	 public        Long       goodsNum;//商品数量
	 public        Double     totalPrice;//总价格 
	 public        String     consigneeName;//收货人姓名
	 public        String     consigneeAddress;//收货人地址
	 public        Long       consigneeNumber;//收货人电话号码
	 public        List<OrderGoods>  ordersGoos;//商品详情
	 public        String     createTime;//创建时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Long getGoodsNum() {
		return goodsNum;
	}
	public void setGoodsNum(Long goodsNum) {
		this.goodsNum = goodsNum;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getConsigneeName() {
		return consigneeName;
	}
	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}
	public String getConsigneeAddress() {
		return consigneeAddress;
	}
	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}
	public Long getConsigneeNumber() {
		return consigneeNumber;
	}
	public void setConsigneeNumber(Long consigneeNumber) {
		this.consigneeNumber = consigneeNumber;
	}
	public List<OrderGoods> getOrdersGoos() {
		return ordersGoos;
	}
	public void setOrdersGoos(List<OrderGoods> ordersGoos) {
		this.ordersGoos = ordersGoos;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	 
}

