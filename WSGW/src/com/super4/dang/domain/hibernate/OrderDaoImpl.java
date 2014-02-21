package com.super4.dang.domain.hibernate;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.super4.dang.dao.OrderDao;
import com.super4.dang.domain.Address;
import com.super4.dang.domain.Order;
import com.super4.dang.domain.SendWay;

public class OrderDaoImpl implements OrderDao {
	public void addOrder(Order order) {
		try {
			Session session=HbnSessionFactory.getSession();
			Transaction ts=session.getTransaction();
			ts.begin();
			session.save(order);
			ts.commit();
			session.close();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void updateOrder(Order order) {
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		session.update(order);
		ts.commit();
		session.close();
	}
	public void addItems(List<?> set) {
		Iterator it=set.iterator();
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		while(it.hasNext()){
			session.save(it.next());
		}
		ts.commit();
		session.close();
	}
	public void addAddress(Address address) {
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		session.saveOrUpdate(address);
		ts.commit();
		session.close();
	}

	public List<?> findAddressByUserId(Integer userid,boolean isdefault) {
		Session session=HbnSessionFactory.getSession();
		System.out.println(userid+"\t"+isdefault);
		Query query=session.createQuery("from Address a where a.userId=? and a.defaultAddress=?");
		query.setInteger(0, userid);
		query.setBoolean(1, isdefault);
		List<?> addresss=query.list();
		session.close();
		return addresss;
	}

	public List<?> findAllSendway() {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from SendWay");
		List list=query.list();
		session.close();
		return list;
	}

	public SendWay findSendwayById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from SendWay s where s.id=?");
		query.setInteger(0, id);
		SendWay sendway=(SendWay)query.uniqueResult();
		session.close();
		return sendway;
	}
	public Address findAddressById(Integer id){
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Address a where a.id=?");
		query.setInteger(0, id);
		Address address=(Address)query.uniqueResult();
		session.close();
		return address;
	}
	public void updateAddress(Address address){
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		session.update(address);
		ts.commit();
		session.close();
	}
	public Order findOrderById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Order o where o.id=?");
		query.setInteger(0, id);
		Order order=(Order)query.uniqueResult();
		if(order!=null){
			query=session.createQuery("from Address a where a.id=?");
			query.setInteger(0, order.getAddress().getId());
			Address address=(Address)query.uniqueResult();
			query=session.createQuery("from SendWay s where s.id=?");
			query.setInteger(0, order.getSendWay().getId());
			SendWay sendway=(SendWay)query.uniqueResult();
			List list=findItemsByOrderId(id);
			order.setItems(list);
			order.setAddress(address);
			order.setSendWay(sendway);
		}
		session.close();
		return order;
	}
	public List<?> findOrderByUserId(Integer userId) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Order o join fetch o.user u where u.id=? ");
		query.setInteger(0, userId);
		List list=query.list();
		session.close();
		return list;
	}
	
//	public Address findAddressByUser(Integer userId, boolean isdefault) {
//		Session session=HbnSessionFactory.getSession();
//		Query query=session.createQuery("from Address a where a.userId=? and a.defaultAddress=?");
//		query.setInteger(0, userId);
//		query.setBoolean(1,isdefault);
//		Address address=(Address)query.uniqueResult();
//		session.close();
//		return address;
//	}
	
	public List<?> findItemsByOrderId(Integer id){
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Item i where i.orderId=?");
		query.setInteger(0, id);
		List list=query.list();
		session.close();
		return list;
	}
	
	public List<?> findSimpleOrders() {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Order");
		List list=query.list();
		session.close();
		return list;
	}
	public List<?> findSimpleOrdersByUserId(Integer uid) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Order o join fetch o.user u where u.id=?");
		query.setInteger(0, uid);
		List list=query.list();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		OrderDaoImpl dao=new OrderDaoImpl();
		List list=dao.findAddressByUserId(1,true);
		for(Address order:(List<Address>)list){
			System.out.println(order.getId()+"\tfsjeo"+order.isDefaultAddress());
		}
		System.out.println(list);
	}
}
