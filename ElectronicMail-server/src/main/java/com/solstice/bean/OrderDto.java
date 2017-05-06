package com.solstice.bean;

import java.io.Serializable;
import java.util.List;

/**
*
*@author
*@date 2017年4月27日 下午5:53:26
*
**/
public class OrderDto implements Serializable{
	 private static final long serialVersionUID = 1L;
	 public        Long       id;//
	 public        String       userId;//用户id
	 public        Long       goodsNum;//商品数量
	 public        Double     totalPrice;//总价格 
	 public        String     consigneeName;//收货人姓名
	 public        String     consigneeAddress;//收货人地址
	 public        Long       consigneeNumber;//收货人电话号码
	 public        List<OrderGoodsDto>  ordersGoos;//商品详情
	 public        String     ordersGoosStr;//商品详情字符串
	 
	 public        Long       index;//页数
	 public        Long       start ;  //开始位置
	 public        Long       rows;//每页行数
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
	public List<OrderGoodsDto> getOrdersGoos() {
		return ordersGoos;
	}
	public void setOrdersGoos(List<OrderGoodsDto> ordersGoos) {
		this.ordersGoos = ordersGoos;
	}
	public String getOrdersGoosStr() {
		return ordersGoosStr;
	}
	public void setOrdersGoosStr(String ordersGoosStr) {
		this.ordersGoosStr = ordersGoosStr;
	}
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
		this.setStartNum();
	}
	public Long getStart() {
		return start;
	}
	public void setStart(Long start) {
		this.start = start;
	}
	
    public void setStartNum( ) {  	
    	if(this.getIndex()>0&&this.getRows()>0){
    		this.setStart((this.getIndex()-1)*this.getRows());
    	}else{
    		this.setStart(0L);
    	}
    }
    
	public Long getRows() {
		return rows == null ? 20 : rows;
	}
	public void setRows(Long rows) {
		this.rows = rows;
	}
	 
	 
	 
}

