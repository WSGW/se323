package com.super4.dang.form;

import org.apache.struts.action.ActionForm;

public class CartItemForm extends ActionForm {
	private static final long serialVersionUID = -8662804975717620962L;
	
	private Integer productId;
	private Integer num;
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
}
