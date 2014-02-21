package com.super4.dang.domain;

import java.io.Serializable;

public class CartItem implements Serializable,Comparable<CartItem>{
	
	private static final long serialVersionUID = 1L;

	private Product product;
	
	private Integer num;
	
	private boolean drop;
	
	
	

	public boolean isDrop() {
		return drop;
	}




	public void setDrop(boolean drop) {
		this.drop = drop;
	}




	public Integer getNum() {
		return num;
	}




	public void setNum(Integer num) {
		this.num = num;
	}




	public Product getProduct() {
		return product;
	}




	public void setProduct(Product product) {
		this.product = product;
	}




	public int compareTo(CartItem o) {
		return this.getProduct().getId().compareTo(o.getProduct().getId());
	}
}
