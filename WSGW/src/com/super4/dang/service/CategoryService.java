package com.super4.dang.service;

import java.util.List;

import com.super4.dang.dao.CategoryDao;
import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.domain.Category;

public class CategoryService {
	
	private final CategoryDao dao=DaoImplFactory.getCategoryDao();
	/**
	 * 根据指定的定级分类ID获得该对象以及该对象所有子分类的信息，以及子分类的子分类的所有相关信息
	 * @param id 定级分类的ID
	 * @return 返回一个定级分类对象
	 */
	public Category getThreeCategory(Integer id){
		Category category=dao.findThreeCategorys(id);
		return category;
	}
	
	public Category getParentCategory(Integer id){
		return dao.findParentCategory(id);
	}
	
	public Category getCategoryById(Integer id){
		return dao.findCategoryById(id);
	}
	
	public Category getTopParentCategory(Integer id){
		return dao.findTopCategory(id);
	}
	public static void main(String[] args) {
		CategoryService cs=new CategoryService();
		System.out.println(cs.getParentCategory(2));
	}
	
	public List<?> getSubCategory(Integer id){
		return dao.findSubCategory(id);
	}
}
