package com.super4.dang.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Order implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT="yyyy年MM月dd日";
	private static final String DATE_FORMAT1="yyyy-MM-dd";
	public static final int STATE_QIANSHOU=3;
	public static final int STATE_YIFAHUO=2;
	public static final int STATE_WEIFAHUO=1;
//	private Set<?> items;// one-to-many
	private List<?> items;// one-to-many
	private User user; //many-to-one
	
	private Address address; //
	private SendWay sendWay; //

	private Integer id;
	private Integer status;
	private long createTime;
	private String desc;
	private double totalPrice;
	private double orderPrice;
	private long sendTime;
	private boolean isDelete;
	private double sendFee;
	public Order(){}
	public double getSendFee() {
		return sendFee;
	}

	public void setSendFee(double sendFee) {
		this.sendFee = sendFee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public boolean isDelete() {
		return isDelete;
	}

	public void setDelete(boolean isDelete) {
		this.isDelete = isDelete;
	}

//	public Set<?> getItems() {
//		return items;
//	}
//
//	public void setItems(Set<?> items) {
//		this.items = items;
//	}
	
	public SendWay getSendWay() {
		return sendWay;
	}

	public void setSendWay(SendWay sendWay) {
		this.sendWay = sendWay;
	}
	public List<?> getItems() {
		return items;
	}
	public void setItems(List<?> items) {
		this.items = items;
	}
	
	public boolean getDelete(){
		System.out.println(isDelete);
		return this.isDelete;
	}
	
	public String getCtDate(){
		Date date=new Date();
		date.setTime(createTime);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
	public String getstDate1(){
		Date date=new Date();
		date.setTime(sendTime);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT1);
		format.format(date);
		return format.format(date);
	}
	public String getStDate(){
		Date date=new Date();
		date.setTime(sendTime);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
	public String getState(){
		if(this.status==3){
			return "已签收";
		}else if(this.status==2){
			return "已发货";
		}else{
			return "未发货";
		}
	}
}