package com.solstice.bean;

import java.io.Serializable;

public class ShoppingCar implements Serializable{
	 private static final long serialVersionUID = 1L;
	 public        Long       id;//
	 public        String       userId;//用户id
	 public        Long       num;//商品数量
	 public        Long       goodsId;//商品id
	 public        Double     totalPrice;//总价格 
	 public        Double     price;//单价格 
	 public        String     name;//商品名称
	 public        String     detail;//商品描述
	 public        String     url;//图片地址
	 public        String     createTime;//创建时间
	 public        String     updateTime;//创建时间
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
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	

	
	 
	 
}
