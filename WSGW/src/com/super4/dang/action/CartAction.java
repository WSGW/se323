package com.super4.dang.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.User;
import com.super4.dang.form.CartItemForm;
import com.super4.dang.service.CartService;
import com.super4.dang.util.CookieUtils;

public class CartAction extends MappingDispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CartService cs=getCartService(request);
		if(cs.isEmpty()){
			ActionMessages errors=new ActionMessages();
			errors.add("error.cart.empty",new ActionMessage("error.cart.empty"));
			saveErrors(request, errors);
		}
		saveToCookie(response, cs);
		return mapping.findForward("success");
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CartService cs=getCartService(request);
		CartItemForm f=(CartItemForm)form;
		System.out.println(f.getProductId());
		boolean b=cs.addItem(f.getProductId());
		response.getWriter().write(b?'1':'0');
		return null;
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CartService cs=getCartService(request);
		CartItemForm f=(CartItemForm)form;
		cs.updateItemNum(f.getProductId(), f.getNum());
		return mapping.findForward("success");
	}
	
	public ActionForward drop(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CartService cs=getCartService(request);
		CartItemForm f=(CartItemForm)form;
		cs.dropItem(f.getProductId());
		return mapping.findForward("success");
	}
	
	public ActionForward recovery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CartService cs=getCartService(request);
		CartItemForm f=(CartItemForm)form;
		cs.recoveryItem(f.getProductId());
		return mapping.findForward("success");
	}
	
	public CartService getCartService(HttpServletRequest request) throws UnsupportedEncodingException{
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		CartService cs=(CartService)session.getAttribute("cs");
		if(cs==null){
			cs=new CartService(user);
			String cartId=CookieUtils.findCookie(request, CookieUtils.USER_CARTID);
			if(cartId!=null){
				cs.load(cartId);
			}
		}
		session.setAttribute("cs", cs);
		return cs;
	}
	
	public void saveToCookie(HttpServletResponse response,CartService cs) throws UnsupportedEncodingException{
		CookieUtils.createCookie(response, CookieUtils.USER_CARTID, cs.serialize());
	}
}
