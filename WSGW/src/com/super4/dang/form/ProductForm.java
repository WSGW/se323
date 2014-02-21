package com.super4.dang.form;

import java.sql.Date;

import org.apache.struts.action.ActionForm;

public class ProductForm extends ActionForm {
	private static final long serialVersionUID = 3445450567035708607L;
	
	private Integer category_id;
	
	private String product_name;	//��Ʒ��
	
	private String description;//��Ʒ˵��
	
	private double fixed_price;//����
	
	private double dang_price;//������
	
	private double weight;//����
	
	private String keywords;	//�ؼ���

	private String company;//���۳���
	
	private String publishing;//��������
	
	private Date publish_time;//��������
	
	private Date shangshi_time;//����ʱ��
	
	private String isbn;//��Ʒ���
	
	private String publish_summary;//�������ҽ���
	
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
