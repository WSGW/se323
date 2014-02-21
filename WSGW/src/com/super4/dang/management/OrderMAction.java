package com.super4.dang.management;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Order;
import com.super4.dang.service.OrderService;

public class OrderMAction extends MappingDispatchAction {
	public ActionForward showAllOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String uid=request.getParameter("userId");
		List orders=null;
		OrderService os=new OrderService();
		if(uid==null){
			orders=os.findSimpleOrders();
		}else{
			orders=os.findSimpleOrdersByUserId(Integer.parseInt(uid));
		}
		request.setAttribute("orders",orders);
		return mapping.findForward("success");
	}
	
	public ActionForward showOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String oid=request.getParameter("orderId");
		System.out.println(oid);
		OrderService os=new OrderService();
		Order order=os.getOrderById(Integer.parseInt(oid));
		request.setAttribute("order",order);
		return mapping.findForward("success");
	}
	
	public ActionForward sureOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String oid=request.getParameter("orderId");
		String option=request.getParameter("option");
		Integer status=Integer.parseInt(option);
		OrderService os=new OrderService();
		Order order=os.getOrderById(Integer.parseInt(oid));
		order.setStatus(status);
		os.updateOrder(order);
		request.setAttribute("redirecturl","showOrder.do?orderId="+oid);
		return mapping.findForward("success");
	}
	public ActionForward searchOrderForm(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
			return mapping.findForward("success");
	}
	public ActionForward searchOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String type=request.getParameter("search_type");
		String value=request.getParameter("search_value");
		if(type.equals("id")){
			response.sendRedirect("showOrder.do?orderId="+value);
		}else{
			response.sendRedirect("showAllOrder.do?userId="+value);
		}
		return null;
	}
}
