package com.super4.dang.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.super4.dang.domain.User;
import com.super4.dang.service.UserService;

public class AuthorizeUtils {
	public static boolean isLogin(HttpServletRequest request,UserService us) throws UnsupportedEncodingException{
		HttpSession session=request.getSession();
		if(session.getAttribute("is_login")!=null && !session.getAttribute("is_login").equals(false)){
			return true;
		}else{
			String id=CookieUtils.findCookie(request, CookieUtils.USER_LOGIN);
			if(id!=null && !id.equals("") ){
				User user = us.getUserById((Integer.valueOf(id)));
				if (user != null) {
					session.setAttribute("user", user);
					return true;
				}
			}
		}
		return false;
	}
}
