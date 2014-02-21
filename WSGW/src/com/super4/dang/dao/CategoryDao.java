package com.super4.dang.dao;

import java.util.List;

import com.super4.dang.domain.Category;

public interface CategoryDao {
	public Category findThreeCategorys(Integer id);
	
	public Category findParentCategory(Integer id);
	
	public Category findCategoryById(Integer id);
	
	public Category findTopCategory(Integer id);
	
	public List<?> findSubCategory(Integer id);
}
