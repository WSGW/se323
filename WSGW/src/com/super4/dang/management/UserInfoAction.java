package com.super4.dang.management;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.User;
import com.super4.dang.service.UserService;

public class UserInfoAction extends MappingDispatchAction {
	public ActionForward showAllUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserService us=new UserService();
		List list=us.findAllUser();
		request.setAttribute("users", list);
		return mapping.findForward("success");
	}
	public ActionForward showUserInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserService us=new UserService();
		String uid=request.getParameter("userId");
		User user=us.getUserById(Integer.parseInt(uid));
		request.setAttribute("user", user);
		return mapping.findForward("success");
	}
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserService us=new UserService();
		String uid=request.getParameter("userId");
		us.deleteUser(Integer.parseInt(uid));
		request.setAttribute("redirecturl", "showmain.do");
		return mapping.findForward("success");
	}
	public ActionForward showSearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		return mapping.findForward("success");
	}
	public ActionForward searchUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserService us=new UserService();	
		String type=request.getParameter("search_type");
			String value=request.getParameter("search_value");
			List users=us.findSimpleUser(type, value);
			request.setAttribute("users", users);
		return mapping.findForward("success");
	}
}
