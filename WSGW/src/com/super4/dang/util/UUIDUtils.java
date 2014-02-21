package com.super4.dang.util;

import java.util.UUID;

public class UUIDUtils {
	public static String getEmail_verify_code(){
		UUID uuid=UUID.randomUUID();
		return uuid.toString();
	}
}
