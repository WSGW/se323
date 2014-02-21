package com.super4.dang.util;

public class OrderUtils {
	public static String getOrder(Integer order){
		switch(order){
		case 1:return "id desc";
		case 2:return "dang_price";
		case 3:return "dang_price desc";
		case 4:return "publish_time";
		case 5:return "publish_time desc";
		case 6:return "shangshi_time";
		case 7:return "shangshi_time desc";
		default:return null;
		}
	}
}
