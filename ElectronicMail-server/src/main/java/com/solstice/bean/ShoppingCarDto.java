package com.solstice.bean;

import java.io.Serializable;

public class ShoppingCarDto implements Serializable{
	 private static final long serialVersionUID = 1L;
	 public        Long       id;//
	 public        String       userId;//用户id
	 public        Long       num;//商品数量
	 public        Long       goodsId;//商品id
	 
	 
	 public        Long       index;//页数
	 public        Long       start ;  //开始位置
	 public        Long       rows;//每页行数
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
