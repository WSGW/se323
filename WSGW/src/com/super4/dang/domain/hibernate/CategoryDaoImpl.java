package com.super4.dang.domain.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.super4.dang.dao.CategoryDao;
import com.super4.dang.domain.Category;
import com.super4.dang.domain.Product;

public class CategoryDaoImpl implements CategoryDao{

	@SuppressWarnings("unchecked")
	public Category findThreeCategorys(Integer id) {
		List list=null;
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, id);
		Category category=(Category)query.uniqueResult();
		
		query=session.createQuery("from Category c where c.parent_id=? order by c.id");
		query.setInteger(0,category.getId());
		list=query.list();
		if(list!=null && list.size()>0){
			category.setSubcategory(list);
			for(Category cate:(List<Category>)list){
				query=session.createQuery("from Category c where c.parent_id=? order by c.id");
				query.setInteger(0,cate.getId());
				List list1=query.list();
				if(list!=null && list.size()>0){
					cate.setSubcategory(list1);
				}
			}
		}
		return category;
	}
	
	public Category findParentCategory(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, id);
		Category category=(Category)query.uniqueResult();
		query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, category.getParent_id());
		Category category1=(Category)query.uniqueResult();
		category.setParentCategory(category1);
		return category;
	}
	
	public Category findCategoryById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, id);
		Category category=(Category)query.uniqueResult();
		return category;
	}
	
	public Category findTopCategory(Integer id){
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Product p where p.id=?");
		query.setInteger(0, id);
		Product product=(Product)query.uniqueResult();
		query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, product.getCategory_id());
		Category category=(Category)query.uniqueResult();
		query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, category.getParent_id());
		Category category1=(Category)query.uniqueResult();
		query=session.createQuery("from Category c where c.id=?");
		query.setInteger(0, category1.getParent_id());
		Category category2=(Category)query.uniqueResult();
		category1.setParentCategory(category2);
		category.setParentCategory(category1);
		return category;
	}
	
	public List<?> findSubCategory(Integer id){
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Category c where c.parent_id=?");
		query.setInteger(0, id);
	  List list=query.list();
	  return list;
	}
	public static void main(String[] args) {
		CategoryDaoImpl dao=new CategoryDaoImpl();
		System.out.println(dao.findThreeCategorys(1));
	}
}
