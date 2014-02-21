package com.super4.dang.util;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	
	public static final Integer COOKIE_AGE=60*60*24*7;
	
	public static final String USER_LOGIN="dangdang.user.login";
	
	public static final String USER_REGISTER="dangdang.user.register";
	
	public static final String USER_CARTID="dangdang.user.cartId";
	
	
	public static Cookie createCookie(HttpServletResponse response,String key,String value) throws UnsupportedEncodingException{
		Cookie cookie=new Cookie(key,value);
		cookie.setMaxAge(COOKIE_AGE);
		cookie.setPath("/dangdang");//该路径下的所有请求都带上该cookie
		response.addCookie(cookie);
		return cookie;
	}
	
	public static String findCookie(HttpServletRequest request,String key) throws UnsupportedEncodingException{
		Cookie [] cookies=request.getCookies();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				if(key.equals(cookies[i].getName())){
					return cookies[i].getValue();
				}
			}
		}
		return null;
	}
	
	public static void deleteCookie(HttpServletResponse response,String key) throws UnsupportedEncodingException{
		Cookie cookie=createCookie(response,key,"");
		cookie.setMaxAge(0);
	}
}
