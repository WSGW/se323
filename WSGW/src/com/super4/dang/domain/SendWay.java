package com.super4.dang.domain;

public class SendWay implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private boolean isClose;
	private String sendWay;
	private String sendDesc;
	private double sendFee;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean getIsClose() {
		return this.isClose;
	}

	public void setIsClose(boolean isClose) {
		this.isClose = isClose;
	}

	public String getSendWay() {
		return this.sendWay;
	}

	public void setSendWay(String sendWay) {
		this.sendWay = sendWay;
	}

	public String getSendDesc() {
		return this.sendDesc;
	}

	public void setSendDesc(String sendDesc) {
		this.sendDesc = sendDesc;
	}

	public double getSendFee() {
		return this.sendFee;
	}

	public void setSendFee(double sendFee) {
		this.sendFee = sendFee;
	}
	public boolean getClose(){
		return isClose;
	}
}