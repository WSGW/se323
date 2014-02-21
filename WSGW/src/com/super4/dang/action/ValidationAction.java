package com.super4.dang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.User;
import com.super4.dang.service.UserService;

public class ValidationAction extends MappingDispatchAction{
	public ActionForward email(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
			String email1=request.getParameter("email");
			UserService service=new UserService();
			User user=service.getUserByEmail(email1);
			if(user!=null && user.getEmail()!=null){
				response.getWriter().write("1");
			}else{
				response.getWriter().write("0");
			}
		return null;
	}
}
