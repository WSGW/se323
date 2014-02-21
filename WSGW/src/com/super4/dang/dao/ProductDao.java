package com.super4.dang.dao;

import java.util.List;

import com.super4.dang.domain.MobiePhone;
import com.super4.dang.domain.Product;

public interface ProductDao {
	public void createProduct(Product product);
	public Product getProductById(Integer productId);
	public List getCategorysById(Integer id);
	public List getBooksById(Integer id,Integer page,String order);
	public Integer getCount(Integer id);
	public MobiePhone getBookById(Integer id);
	public List<?> getProductByCategoryId(Integer cid);
	public List<?> getProductByName(String pname);
}
