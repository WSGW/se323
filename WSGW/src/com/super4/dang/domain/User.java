package com.super4.dang.domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable{
	
	private static final long serialVersionUID = -1441472570203874844L;

	private static final String DATE_FORMAT="yyyyƒÍMM‘¬dd»’";
	
	private Integer id;
	
	private String email;
	
	private String nickname;
	
	private String passwd;
	
	private Integer user_integral;
	
	private boolean is_email_verify;
	
	private String email_verify_code;
	
	private long last_login_time;
	
	private String last_login_ip;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail_verify_code() {
		return email_verify_code;
	}

	public void setEmail_verify_code(String email_verify_code) {
		this.email_verify_code = email_verify_code;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public boolean isIs_email_verify() {
		return is_email_verify;
	}

	public void setIs_email_verify(boolean is_email_verify) {
		this.is_email_verify = is_email_verify;
	}

	public String getLast_login_ip() {
		return last_login_ip;
	}

	public void setLast_login_ip(String last_login_ip) {
		this.last_login_ip = last_login_ip;
	}

	public long getLast_login_time() {
		return last_login_time;
	}

	public void setLast_login_time(long last_login_time) {
		this.last_login_time = last_login_time;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public Integer getUser_integral() {
		return user_integral;
	}

	public void setUser_integral(Integer user_integral) {
		this.user_integral = user_integral;
	}
	public String getllDate(){
		Date date=new Date();
		date.setTime(last_login_time);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
}
