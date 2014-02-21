package com.super4.dang.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MobiePhone extends Product implements Serializable{
	private static final long serialVersionUID = 1943300099311230711L;
	
	private static final String DATE_FORMAT="yyyy年MM月dd日";
	private Integer id;//产品id
	
	private String company;//销售厂商
	
	private String publishing;//生产厂商
	
	private long publish_time;//生产日期
	
	private long shangshi_time;//上市时间
	
	private String isbn;//产品编号
	
	private String publish_summary;//生产厂家介绍
	
	
	public MobiePhone() {
		super();
	}

	public String getPublish_summary() {
		return publish_summary;
	}






	public void setPublish_summary(String publish_summary) {
		this.publish_summary = publish_summary;
	}






	public String getCompany() {
		return company;
	}



	public void setCompany(String company) {
		this.company = company;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getIsbn() {
		return isbn;
	}



	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public long getShangshi_time() {
		return shangshi_time;
	}



	public void setShangshi_time(long shangshi_time) {
		this.shangshi_time = shangshi_time;
	}



	public long getPublish_time() {
		return publish_time;
	}



	public void setPublish_time(long publish_time) {
		this.publish_time = publish_time;
	}



	public String getPublishing() {
		return publishing;
	}



	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}



	public String getScDate(){
		Date date=new Date();
		date.setTime(publish_time);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
	public String getSsDate(){
		Date date=new Date();
		date.setTime(shangshi_time);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
}
