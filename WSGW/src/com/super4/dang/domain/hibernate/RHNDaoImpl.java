package com.super4.dang.domain.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.super4.dang.dao.RHNDao;
import com.super4.dang.domain.RHN;

public class RHNDaoImpl implements RHNDao {
	public List<?> findAllRHN(String type,Integer count) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from RHN r  join fetch r.product where r.type=?" );
		query.setString(0, type);
		List list=query.list();
		session.close();
		return list;
	}
	
	public static void main(String[] args) {
		RHNDaoImpl dao=new RHNDaoImpl();
		List list=dao.findAllRHN(RHN.TYPE_NEW,8);
		for(int i=0;i<list.size();i++){
			RHN rhn=(RHN)list.get(i);
			System.out.println(rhn);
		}
	}
}
