package com.super4.dang.domain;

import java.util.List;

public class Category {
	private Integer id;//����
	private Integer turn;//�����˳��
	private String en_name;//Ӣ������
	private String cn_name;//��������
	private String abridge_name;//������д
	private Integer parent_id;//������ID
	private String category_value;//����ֵ
	private List<Category> subcategory;
	private Category parentCategory;
	
	public String getAbridge_name() {
		return abridge_name;
	}


	public void setAbridge_name(String abridge_name) {
		this.abridge_name = abridge_name;
	}


	public String getCategory_value() {
		return category_value;
	}


	public void setCategory_value(String category_value) {
		this.category_value = category_value;
	}


	public String getCn_name() {
		return cn_name;
	}


	public void setCn_name(String cn_name) {
		this.cn_name = cn_name;
	}


	public String getEn_name() {
		return en_name;
	}


	public void setEn_name(String en_name) {
		this.en_name = en_name;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getParent_id() {
		return parent_id;
	}


	public void setParent_id(Integer parent_id) {
		this.parent_id = parent_id;
	}


	public List<Category> getSubcategory() {
		return subcategory;
	}


	public void setSubcategory(List<Category> subcategory) {
		this.subcategory = subcategory;
	}


	public Integer getTurn() {
		return turn;
	}


	public Category getParentCategory() {
		return parentCategory;
	}


	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}


	public void setTurn(Integer turn) {
		this.turn = turn;
	}


	public void addSubCategory(Category cate){
		subcategory.add(cate);
	}
	
	
}
