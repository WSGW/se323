package com.super4.dang.dao;

import java.util.List;

import com.super4.dang.domain.User;

public interface UserDao {
	public User getUserById(Integer id);
	
	public void addUser(User user);
	
	public User getUserByEmail(String email);
	
	public void updateUser(User user);
	
	public void deleteUser(Integer id);
	
	public List<?> getAllUser();
	
	public List<?> findSimpleUser(String type,String value);
	
}
