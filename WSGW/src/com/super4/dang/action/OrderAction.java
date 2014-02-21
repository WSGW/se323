package com.super4.dang.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Address;
import com.super4.dang.domain.CartItem;
import com.super4.dang.domain.Item;
import com.super4.dang.domain.Order;
import com.super4.dang.domain.SendWay;
import com.super4.dang.domain.User;
import com.super4.dang.form.OrderForm;
import com.super4.dang.service.CartService;
import com.super4.dang.service.OrderService;

public class OrderAction extends MappingDispatchAction {
	
	private static final Logger logger=Logger.getLogger(OrderAction.class);
	public ActionForward address(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		CartService cs=(CartService)session.getAttribute("cs");
		OrderService os=getOrderService(request);
		if(cs.getItems().isEmpty()){
			return mapping.findForward("fail1");
		}
		String flag=request.getParameter("flag");
		String addressId=request.getParameter("addressId");
		Address address=null;
		Address defaultAdd=null;
		List<?> addresss=os.findAddressByUser(os.getOrder().getUser(), true);
		if(!addresss.isEmpty()){
			address=defaultAdd=(Address)addresss.get(0);
		}
		addresss=os.findAddressByUser(os.getOrder().getUser(), false);
		if(addressId!=null){
			Integer id=Integer.parseInt(addressId);
			address=os.findAddressById(id);
		}
		request.setAttribute("defaultAddress",defaultAdd);
		request.setAttribute("address",address);
		request.setAttribute("addresss", addresss);
		if(addressId==null){
			if(flag==null){
				if(defaultAdd!=null){
					return mapping.findForward("fail");
				}
				return mapping.findForward("success");
			}else{
				if(flag.equals("true")){
					return mapping.findForward("success");
				}else{
					return mapping.findForward("fail");
				}
			}
		}else{
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward sendway(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("sendway");
		OrderService os=getOrderService(request);
		OrderForm f=(OrderForm)form;
		Address address=new Address();
		BeanUtils.copyProperties(address,f);
		address.setUserId(os.getOrder().getUser().getId());
		address.setDefaultAddress(false);
		os.getOrder().setAddress(address);
		List<?> list=os.findAllSendWay(); 
		request.setAttribute("list_sw", list);
		request.setAttribute("sendway", list.get(0));
		return mapping.findForward("success");
	}
	public ActionForward sendway1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("sendway1");
		OrderService os=getOrderService(request);
		String addressId=request.getParameter("addressId");
		Address address=os.findAddressById(Integer.parseInt(addressId));
		List list=os.findAllSendWay(); 
		os.getOrder().setAddress(address);
		request.setAttribute("list_sw", list);
		request.setAttribute("sendway", list.get(0));
		return mapping.findForward("success");
	}
	@SuppressWarnings("unchecked")
	public ActionForward sendway2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		logger.info("sendway2");
		OrderService os=getOrderService(request);
		String sendwayId=request.getParameter("sendwayId");
		SendWay sendway=os.findSendWayById(Integer.parseInt(sendwayId));
		List<SendWay> list=(List<SendWay>)os.findAllSendWay(); 
		os.getOrder().setSendWay(sendway);
		request.setAttribute("list_sw", list);
		request.setAttribute("sendway", sendway);
		return mapping.findForward("success");
	}
	public ActionForward sure(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		OrderService os=getOrderService(request);
		String sendwayId=request.getParameter("sendwayId");
		SendWay sendway=os.findSendWayById(Integer.parseInt(sendwayId));
		List<Item> set=getItems(request);
		logger.info(set.size());
		os.getOrder().setItems(set);
		os.getOrder().setSendWay(sendway);
		os.getOrder().setSendFee(sendway.getSendFee());
		os.getOrder().setOrderPrice(os.getOrder().getSendFee()+os.getOrder().getTotalPrice());
		return mapping.findForward("success");
	}
	
	public ActionForward order(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("success");
	}
	
	public ActionForward orderok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws InterruptedException{
		HttpSession session=request.getSession();
		CartService cs=(CartService)session.getAttribute("cs");
		if(cs.getItems().isEmpty()){
			return mapping.findForward("fail");
		}
		OrderService os=getOrderService(request);
		OrderForm f=(OrderForm)form;
		Order order=os.getOrder();
		order.setDesc(f.getDesc());
		order.setSendTime(f.getSendTime().getTime());
		order.setCreateTime(new Date().getTime());
		order.setDelete(false);
		order.setStatus(Order.STATE_WEIFAHUO);
		Address address=order.getAddress();
		logger.info(address);
		os.createAddress(address);
		os.createOrder(order);
		setOrderId(order.getItems(),order);
		os.createItems(order.getItems());
		cs.getItems().clear();
		return mapping.findForward("success");
	}
	
	
	private OrderService getOrderService(HttpServletRequest request){
		HttpSession session=request.getSession();
		OrderService os=(OrderService)session.getAttribute("os");
		CartService cs=(CartService)session.getAttribute("cs");
		User user=(User)session.getAttribute("user");
		if(os==null){
			os=new OrderService();
			Order order=new Order();
			os.setOrder(order);
		}
		os.getOrder().setUser(user);
		os.getOrder().setTotalPrice(cs.getDangPrice());
		session.setAttribute("os",os);
		return os;
	}
	private List<Item> getItems(HttpServletRequest request){
		HttpSession session=request.getSession();
		CartService cs=(CartService)session.getAttribute("cs");
		List<Item> set=new ArrayList<Item>();
		Map cartitems=cs.getItems();
		Iterator it=cartitems.keySet().iterator();
		while(it.hasNext()){
			Integer cartitemId=(Integer)it.next();
			CartItem cartItem=(CartItem)cartitems.get(cartitemId);
			if(!cartItem.isDrop()){
				Item item=new Item();
				item.setProductId(cartItem.getProduct().getId());
				item.setProductName(cartItem.getProduct().getProduct_name());
				item.setNum(cartItem.getNum());
				item.setDangPrice(cartItem.getProduct().getDang_price());
				item.setAmount(100);
				set.add(item);
			}
		}
		return set;
	}
	private void setOrderId(List set,Order order){
		Iterator it=set.iterator();
		while(it.hasNext()){
			Item item=(Item)it.next();
			item.setOrderId(order.getId());
		}
	}
}
