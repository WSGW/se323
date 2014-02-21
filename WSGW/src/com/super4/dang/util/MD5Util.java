package com.super4.dang.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class MD5Util {
	/*
	 * 加密运算  按MD5算法进行运算
	 */
	public static String md5(String msg){
		try {
			MessageDigest md=MessageDigest.getInstance("md5");
			byte [] b=md.digest(msg.getBytes());
			BASE64Encoder encoder=new BASE64Encoder();
			return encoder.encode(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
}
