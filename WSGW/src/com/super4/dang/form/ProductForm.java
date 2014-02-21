package com.super4.dang.form;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {
	private static final long serialVersionUID = 3445450567035708607L;
	
	private Integer category_id;
	
	private String product_name;	//产品名
	
	private String description;//产品说明
	
	private double fixed_price;//定价
	
	private double dang_price;//当当价
	
	private double weight;//重量
	
	private String keywords;	//关键字

	private String company;//销售厂商
	
	private String publishing;//生产厂商
	
	private Date publish_time;//生产日期
	
	private Date shangshi_time;//上市时间
	
	private String isbn;//产品编号
	
	private String publish_summary;//生产厂家介绍
	
	public double getDang_price() {
		return dang_price;
	}

	public void setDang_price(double dang_price) {
		this.dang_price = dang_price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFixed_price() {
		return fixed_price;
	}

	public void setFixed_price(double fixed_price) {
		this.fixed_price = fixed_price;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getPublish_summary() {
		return publish_summary;
	}

	public void setPublish_summary(String publish_summary) {
		this.publish_summary = publish_summary;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public Date getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Date publish_time) {
		this.publish_time = publish_time;
	}

	public Date getShangshi_time() {
		return shangshi_time;
	}

	public void setShangshi_time(Date shangshi_time) {
		this.shangshi_time = shangshi_time;
	}

	
}
