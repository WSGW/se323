package com.super4.dang.domain;

import java.util.List;

public class Category {
	private Integer id;//主键
	private Integer turn;//分类的顺序
	private String en_name;//英文名称
	private String cn_name;//中文名称
	private String abridge_name;//分类缩写
	private Integer parent_id;//父分类ID
	private String category_value;//分类值
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
