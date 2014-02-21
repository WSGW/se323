package com.super4.dang.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Address;
import com.super4.dang.domain.Order;
import com.super4.dang.domain.SendWay;
import com.super4.dang.domain.User;
import com.super4.dang.form.OrderForm;
import com.super4.dang.service.OrderService;
import com.super4.dang.service.UserService;
public class InformationAction extends MappingDispatchAction {
	public ActionForward information(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		OrderService os=new OrderService();
		List addresss1=os.findAddressByUser(user, true);
		List addresss2=os.findAddressByUser(user, false);
		List orders=os.findOrderByUser(user);
		request.setAttribute("addresss1", addresss1);
		request.setAttribute("addresss2", addresss2);
		request.setAttribute("orders", orders);
		return mapping.findForward("success");
	}
	
	public ActionForward showInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return mapping.findForward("success");
	}
	
	public ActionForward changeInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		String nickname=request.getParameter("nickname");
		UserService us=new UserService();
		User user=(User)session.getAttribute("user");
		user.setNickname(nickname);
		us.updateUser(user);
		session.setAttribute("user",user);
		response.sendRedirect("information.do");
		return null;
	}
	
	public ActionForward order(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid=request.getParameter("orderId");
		OrderService os=new OrderService();
		Order order=(Order)os.getOrderById(Integer.parseInt(oid));
		request.setAttribute("order", order);
		return mapping.findForward("success");
	}
	public ActionForward address(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String addid=request.getParameter("addressId");
		OrderService os=new OrderService();
		Address address=os.findAddressById(Integer.parseInt(addid));
		request.setAttribute("address", address);
		return mapping.findForward("success");
	}
	
	public ActionForward changeAddressform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String addid=request.getParameter("addressId");
		OrderService os=new OrderService();
		Address address=os.findAddressById(Integer.parseInt(addid));
		request.setAttribute("address", address);
		return mapping.findForward("success");
	}
	
	public ActionForward changeAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String option=request.getParameter("option");
		String oid=request.getParameter("orderId");
		String addid=request.getParameter("addressId");
		OrderForm f=(OrderForm)form;
		OrderService os=new OrderService();
		Address address=(Address)os.findAddressById(Integer.parseInt(addid));
		BeanUtils.copyProperties(address,f);
		os.updateAddress(address);
		response.sendRedirect(option+".do?orderId="+oid+"&addressId="+addid);
		return null;
	}
	public ActionForward changeOrderSendWayform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String sid=request.getParameter("sendwayId");
		OrderService os=new OrderService();
		List list=os.findAllSendWay(); 
		SendWay sendway=os.findSendWayById(Integer.parseInt(sid));
		request.setAttribute("list_sw", list);
		request.setAttribute("sendway", sendway);
		return mapping.findForward("success");
	}
	
	public ActionForward changeOrderSendWayform2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		OrderService os=new OrderService();
		String sendwayId=request.getParameter("sendwayId");
		SendWay sendway=os.findSendWayById(Integer.parseInt(sendwayId));
		List list=os.findAllSendWay(); 
		request.setAttribute("list_sw", list);
		request.setAttribute("sendway", sendway);
		return mapping.findForward("success");
	}
	
	
	public ActionForward changeOrderSendWay(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		OrderService os=new OrderService();
		String oid=request.getParameter("orderId");
		String sendwayId=request.getParameter("sendwayId");
		SendWay sendway=os.findSendWayById(Integer.parseInt(sendwayId));
		Order order=os.getOrderById(Integer.parseInt(oid));
		order.setSendWay(sendway);
		os.updateOrder(order);
		response.sendRedirect("order.do?orderId="+oid);
		return null;
	}
	public ActionForward changeOrderInfoform(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String oid=request.getParameter("orderId");
		OrderService os=new OrderService();
		Order order=(Order)os.getOrderById(Integer.parseInt(oid));
		request.setAttribute("order", order);	
		return mapping.findForward("success");
	}
	
	public ActionForward changeOrderInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String st=request.getParameter("sendTime");
		String del=request.getParameter("delete");
		String desc=request.getParameter("desc");
		String oid=request.getParameter("orderId");
		OrderService os=new OrderService();
		Order order=(Order)os.getOrderById(Integer.parseInt(oid));
		Date sendTime=Date.valueOf(st);
		order.setSendTime(sendTime.getTime());
		order.setDesc(desc);
		order.setDelete(Boolean.parseBoolean(del));
		os.updateOrder(order);
		System.out.println(order.isDelete());
		response.sendRedirect("order.do?orderId="+oid);
		return null;
	}
}
