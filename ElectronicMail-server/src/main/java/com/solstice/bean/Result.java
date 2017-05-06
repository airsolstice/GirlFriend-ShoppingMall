package com.solstice.bean;

import com.alibaba.fastjson.JSONObject;

public class Result {

	private int code = ResultCode.SUCESS;
	private String msg = "";
	private Object model = null;
	private Object totalpage=null;
	public Result() {
	}

	public Result(Object model) {
		this.model = model;
	}

	public Result(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Result(int code, String msg, Object model) {
		this.code = code;
		this.msg = msg;
		this.model = model;
	}
	
	public Result(int code, String msg, Object model,Object totalpage) {
		this.code = code;
		this.msg = msg;
		this.model = model;
		this.totalpage=totalpage;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getModel() {
		return model;
	}

	public void setModel(Object model) {
		this.model = model;
	}
	
	public Object getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(Object totalpage) {
		this.totalpage = totalpage;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
