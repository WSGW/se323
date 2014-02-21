package com.super4.dang.dao;

import com.super4.dang.domain.hibernate.CategoryDaoImpl;
import com.super4.dang.domain.hibernate.CommentDaoImpl;
import com.super4.dang.domain.hibernate.OrderDaoImpl;
import com.super4.dang.domain.hibernate.ProductDaoImpl;
import com.super4.dang.domain.hibernate.RHNDaoImpl;
import com.super4.dang.domain.hibernate.UserDaoImpl;

public class DaoImplFactory {
	public static CategoryDao getCategoryDao(){
		return new CategoryDaoImpl();
	}
	public static UserDao getUserDao(){
		return new UserDaoImpl();
	}
	public static ProductDao getProductDao(){
		return new ProductDaoImpl();
	}
	public static OrderDao getOrderDao(){
		return new OrderDaoImpl();
	}
	public static CommentDao getCommentDao(){
		return new CommentDaoImpl();
	}
	public static RHNDao getRHNDao(){
		return new RHNDaoImpl();
	}
}
