package com.super4.dang.domain.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.super4.dang.dao.UserDao;
import com.super4.dang.domain.User;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		Session session=HbnSessionFactory.getSession();
		System.out.println(session);
		Transaction t=session.beginTransaction();
		session.save(user);
		t.commit();
		session.close();
	}

	public User getUserByEmail(String email) {
		Session session=HbnSessionFactory.getSession();
		User user=(User)session.createQuery("from User u where u.email=?")
									.setString(0,email).uniqueResult();
		session.close();
		return user;
	}

	public User getUserById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		User user=(User)session.createQuery("from User u where u.id=?")
									.setInteger(0, id).uniqueResult();
		session.close();
		return user;
	}

	public void deleteUser(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Transaction t=session.beginTransaction();
		User user=getUserById(id);
		System.out.println(user.getId()+"\t"+user.getNickname());
		session.delete(user);
		t.commit();
		session.close();
	}

	public void updateUser(User user) {
		Session session=HbnSessionFactory.getSession();
		Transaction t=session.beginTransaction();
		session.update(user);
		t.commit();
		session.close();
	}
	
	public List<?> getAllUser() {
		Session session=HbnSessionFactory.getSession();
		List list=session.createQuery("from User")
									.list();
		session.close();
		return list;
	}
	
	public List<?> findSimpleUser(String type, String value) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from User o where o."+type+"=?");
		if(type.equals("id")){
			query.setInteger(0, Integer.parseInt(value));
		}else{
			query.setString(0, value);
		}
		List list=query.list();
		session.close();
		return list;
	}
	
	public static void main(String[] args) {
		UserDaoImpl dao=new UserDaoImpl();
		User user=dao.getUserById(2);
		System.out.println(user);
	}
}
