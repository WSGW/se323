package com.super4.dang.service;

import java.util.List;

import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.dao.ProductDao;
import com.super4.dang.domain.MobiePhone;
import com.super4.dang.domain.Product;

public class ProductService {
	private final ProductDao dao=DaoImplFactory.getProductDao();
	/**
	 * 根据二级分类的ID 查询出该分类的所有子分类，以及所有子分类产品的具体数量
	 * @param id 二级分类的ID
	 * @return 该ID的子分类，以及子分类所有产品的数量 的Map对象
	 */
	public List getSubProduct(Integer id){
		List list=dao.getCategorysById(id);
		return list;
	}
	
	/**
	 * 根据三级分类的ID 查询出所有该分类的产品信息
	 * @param id 三级分类的ID
	 * @return 所有三级分类的产品，以链表返回
	 */
	public List getAllProduct(Integer id,Integer page,String order){
		List list=dao.getBooksById(id,page,order);
		return list;
	}
	
	public Integer getCount(Integer id){
		Integer count=dao.getCount(id);
		return count;
	}
	
	public MobiePhone getBookById(Integer id){
		return dao.getBookById(id);
	}
	
	public void createProduct(Product product){
		dao.createProduct(product);
	}
	
	public List<?> getAllProductByCategoryId(Integer cid){
		return dao.getProductByCategoryId(cid);
	}
	public List<?> getProductByName(String pname){
		return dao.getProductByName(pname);
	}
	
	public void deleteProduct(Integer pid){
		
	}
	public static void main(String[] args) {
		ProductService b=new ProductService();
		System.out.println(b.getBookById(22));
	}
}
