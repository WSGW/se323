package com.super4.dang.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.RequestProcessor;

import com.super4.dang.service.UserService;
import com.super4.dang.util.AuthorizeUtils;

public class ShoppingController extends RequestProcessor {
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		UserService us=new UserService();
		boolean b=AuthorizeUtils.isLogin(request, us);
		if(b){
			super.process(request, response);
		}else{
			response.sendRedirect("../authorize/loginForm.do");
		}
	}
}
