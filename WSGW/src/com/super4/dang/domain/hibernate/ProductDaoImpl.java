package com.super4.dang.domain.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.super4.dang.dao.ProductDao;
import com.super4.dang.domain.Category;
import com.super4.dang.domain.MobiePhone;
import com.super4.dang.domain.Product;
import com.super4.dang.util.CategoryUtil;

public class ProductDaoImpl implements ProductDao {
	public List<?> getProductByName(String pname) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from MobiePhone m where m.product_name=?");
		query.setString(0, pname);
		List list=query.list();
		session.close();
		return list;
	}
	public List<?> getProductByCategoryId(Integer cid) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from MobiePhone b where b.category_id=?");
		query.setInteger(0, cid);
		List list=query.list();
		session.close();
		return list;
	}
	public void createProduct(Product product) {
		Session session=HbnSessionFactory.getSession();
		Transaction tx=session.beginTransaction();
		session.save(product);
		tx.commit();
		session.close();
	}
	/**
	 * 根据三级分类ID查找该分类的所有书籍
	 */

	@SuppressWarnings("unchecked")
	public List getBooksById(Integer id,Integer page,String order){
			Session session=HbnSessionFactory.getSession();
			Query query=session.createQuery("from MobiePhone b where b.category_id=? order by b."+order);
			query.setInteger(0, id);
			query.setFirstResult((page-1)*4);
			query.setMaxResults(4);
			List list=query.list();
			session.close();
			return list;
	}

	/**
	 * 根据二级分类ID查询出该分类的子分类的信息，以及各子分类类型的产品数量
	 */


	@SuppressWarnings("unchecked")
	public List getCategorysById(Integer id) {
		List list=new ArrayList();
			Session session=HbnSessionFactory.getSession();
			Query query=session.createQuery("from Category c where c.parent_id=? order by c.id");
			query.setInteger(0, id);
			List list1=query.list();
			for(Category category:(List<Category>)list1){
				query=session.createQuery("select count(*) from Product p where p.category_id=? order by p.id");
				query.setInteger(0, category.getId());
				List list2=query.list();
				CategoryUtil cu = new CategoryUtil();
				cu.setCategory(category);
				cu.setCount(list2.get(0));
				list.add(cu);
			}
			session.close();
		return list;
	}

	public Integer getCount(Integer id) {

		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from MobiePhone b where b.category_id=?");
		query.setInteger(0, id);
		List list=query.list();
		Integer totalPage=(Integer)list.size();
		if(totalPage%4!=0){
			totalPage=totalPage/4+1;
		}else{
			totalPage=totalPage/4;
		}
		session.close();
		return totalPage;
		}
	public MobiePhone getBookById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from MobiePhone b where b.id=?");
		query.setInteger(0, id);
		MobiePhone book=(MobiePhone)query.uniqueResult();
		session.close();
		return book;
	}
	public Product getProductById(Integer productId) {
		Session session=HbnSessionFactory.getSession();
		Product product=(Product)session.load(Product.class, productId);
		session.close();
		return product;
	}
	public static void main(String[] args) {
		ProductDaoImpl dao=new ProductDaoImpl();
		Product product=dao.getProductById(2);
		System.out.println(product);
	}
}
