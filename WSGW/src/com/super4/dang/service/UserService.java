package com.super4.dang.service;

import java.util.List;

import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.dao.UserDao;
import com.super4.dang.domain.User;

public class UserService {
	
	private static final UserDao dao=DaoImplFactory.getUserDao();
	
	public User getUserByEmail(String email){
		
		return dao.getUserByEmail(email);
	}
	
	public User getUserById(Integer id){
		User user=dao.getUserById(id);
		return user;
	}
	public void addUser(User user){
		dao.addUser(user);
	}
	
	public void updateUser(User user){
		dao.updateUser(user);
	}
	
	public void deleteUser(Integer id){
		dao.deleteUser(id);
	}
	public boolean validatorCode(String id,String code){
		User user=dao.getUserById(Integer.valueOf(id)); 
		if(code.equals(user.getEmail_verify_code())){
			user.setIs_email_verify(true);
			dao.updateUser(user);
			return true;
		}
		return false;
	}
	
	public List findAllUser(){
		return dao.getAllUser();
	}
	
	public List<?> findSimpleUser(String type,String value){
		return dao.findSimpleUser(type, value);
	}
	public static void main(String[] args) {
		UserService us=new UserService();
		User user=us.getUserById(2);
		System.out.println(user);
	}
}
