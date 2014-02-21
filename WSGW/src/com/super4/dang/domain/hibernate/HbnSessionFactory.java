package com.super4.dang.domain.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HbnSessionFactory {
	private static SessionFactory factory;
	private static Configuration conf;
	static{
			conf=new Configuration().configure();
			factory=conf.buildSessionFactory();
	}
	public  static  Session getSession(){
		Session session=factory.openSession();
		return session;
	}
	public static void closeSession(Session session){
		if(session!=null && session.isOpen()){
			session.close();
		}
	}
	
}
