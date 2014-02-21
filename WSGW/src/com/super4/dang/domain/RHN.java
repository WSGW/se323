package com.super4.dang.domain;

public class RHN {
	
	public static final String TYPE_RECOMMEND="rec";
	public static final String TYPE_TEJIA="tejia";
	public static final String TYPE_NEW="new";
	private Integer id;
	
	private Product product;
	
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String toString(){
		return this.getId()+"  "+this.getType()+"   "+this.getProduct().getProduct_name();
	}
	
}
