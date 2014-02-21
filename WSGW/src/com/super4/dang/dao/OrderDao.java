package com.super4.dang.dao;

import java.util.List;

import com.super4.dang.domain.Address;
import com.super4.dang.domain.Order;
import com.super4.dang.domain.SendWay;

public interface OrderDao {
	public void addOrder(Order order);
	
	public void updateOrder(Order order);
	
	public void addItems(List<?> set);
	public List<?> findAddressByUserId(Integer userid,boolean isdefault);
	
	public List<?> findAllSendway();
	
	public SendWay findSendwayById(Integer id);
	
	public void addAddress(Address address);
	
	public Address findAddressById(Integer id);
	
	public void updateAddress(Address address);
	
	public Order findOrderById(Integer id);
	
	public List<?> findOrderByUserId(Integer userId);
	
//	public Address findAddressByUser(Integer userId,boolean isdefault);
	
	public List<?> findSimpleOrders();
	
	public List<?> findSimpleOrdersByUserId(Integer uid);
	
}
