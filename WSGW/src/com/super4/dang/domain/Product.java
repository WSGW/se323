package com.super4.dang.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public abstract class Product implements Serializable{
	
	private static final String DATE_FORMAT="yyyy��MM��dd��";
	
	private Integer id;//��Ʒ��
	
	private Integer category_id;//��������id
	
	private String product_name;	//��Ʒ��
	
	private String description;//��Ʒ˵��
	
	private long add_time;//���ʱ��
	
	private double fixed_price;//����
	
	private double dang_price;//������
	
	private String keywords;	//�ؼ���
	
	private double weight;//����
	
	private Integer has_deleted;//�Ƿ�ɾ��
	
	private String product_pic;//��ƷͼƬ
	
	public Product() {
		super();
	}

	public long getAdd_time() {
		return add_time;
	}

	public void setAdd_time(long add_time) {
		this.add_time = add_time;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

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

	public Integer getHas_deleted() {
		return has_deleted;
	}

	public void setHas_deleted(Integer has_deleted) {
		this.has_deleted = has_deleted;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getProduct_pic() {
		return product_pic;
	}

	public void setProduct_pic(String product_pic) {
		this.product_pic = product_pic;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	public String getTjDate(){
		Date date=new Date();
		date.setTime(add_time);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
}
