
package com.super4.dang.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.service.UserService;
import com.super4.dang.util.AuthorizeUtils;


public class LoginInfo extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		HttpSession session=request.getSession();
		UserService us=new UserService();
		boolean is_login=AuthorizeUtils.isLogin(request, us);
		session.setAttribute("is_login", is_login);
		System.out.println(is_login);
		return mapping.findForward("success");
	}
}