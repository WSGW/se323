package com.super4.dang.service;

import java.util.List;

import org.apache.log4j.Logger;

import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.dao.OrderDao;
import com.super4.dang.domain.Address;
import com.super4.dang.domain.Order;
import com.super4.dang.domain.SendWay;
import com.super4.dang.domain.User;

public class OrderService {
	private Order order;
	
	private static Logger logger=Logger.getLogger(OrderService.class);
	
	private OrderDao dao=DaoImplFactory.getOrderDao();
	
	public OrderService(){
		
	}
	public void createOrder(Order order) {
		dao.addOrder(order);
	}
	
	public void updateOrder(Order order){
		dao.updateOrder(order);
	}
	public void createAddress(Address address) {
		List<?> adds=dao.findAddressByUserId(order.getUser().getId(),true);
		System.out.println("wo ri a  ni ta ya de  ni bu xiang hun le a ");
		System.out.println(adds.size());
		if(adds.isEmpty()){
			address.setDefaultAddress(true);
		}
		System.out.println(address.isDefaultAddress());
		dao.addAddress(address);
		
	}
	
	public void createItems(List<?> set){
		dao.addItems(set);
	}
	public void setDefaultAddress(Address address) {
		Address add=dao.findAddressById(address.getId());
		if(add!=null){
			order.setAddress(address);
			add.setDefaultAddress(true);
			dao.updateAddress(address);
			List<?> adds=dao.findAddressByUserId(order.getUser().getId(),true);
			if(!adds.isEmpty()){
				Address addr=(Address)adds.get(0);
				addr.setDefaultAddress(false);
				dao.updateAddress(addr);
			}
		}
	}

	public List<?> findAddressByUser(User user,boolean isdefault) {
		System.out.println("findAddressByUser  ....");
		return dao.findAddressByUserId(user.getId(),isdefault);
	}

	public List<?> findAllSendWay() {
		return dao.findAllSendway();
	}


	/**
	 * ´øItems
	 * @param user
	 * @return
	 */
	public List<?> findOrderByUser(User user) {
		return dao.findOrderByUserId(user.getId());
	}
	
	public List<?> findSimpleOrders(){
		return dao.findSimpleOrders();
	}
	
	public List<?> findSimpleOrdersByUserId(Integer oid){
		return dao.findSimpleOrdersByUserId(oid);
	}
	/**
	 * ´øItems
	 * @param orderId
	 * @return
	 */
	public Order getOrderById(Integer orderId) {
		return dao.findOrderById(orderId);
	}
	public OrderDao getDao() {
		return dao;
	}
	public void setDao(OrderDao dao) {
		this.dao = dao;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public SendWay findSendWayById(Integer id){
		return dao.findSendwayById(id);
	}
	
	
	public Address findAddressById(Integer id){
		return dao.findAddressById(id);
	}
	
	public void updateAddress(Address address){
		dao.updateAddress(address);
	}
	
	public static void main(String [] args ){
		OrderService os=new OrderService();
//		Address address=new Address();
//		address.setFullAddress("soefjs");
//		address.setDefaultAddress(false);
//		address.setName("fsjefjs");
//		address.setPhone("46767");
//		address.setPostalCode("sfesohf");
//		address.setMobile("3413545");
//		address.setUserId(1);
//		os.createAddress(address);
//		System.out.println(address);
		User user=new User();
		user.setId(1);
		System.out.println(os.findAddressByUser(user, true));
//		System.out.println(os.getDao().findAddressByUserId(1, true));
	}
}
