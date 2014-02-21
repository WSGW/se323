package com.super4.dang.domain;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Comment implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private static final String DATE_FORMAT="yyyyƒÍMM‘¬dd»’ hh:mm";
	private Integer id;
	private Integer productId;
	private Integer commentId;
	private User user;
	private String comment_title;
	private String comment_content;
	private long comment_time;
	private List<CommentReply> replays;
	
	public Comment(){
		replays=new ArrayList<CommentReply>();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getCommentId() {
		return commentId;
	}
	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}
	public List<?> getReplay() {
		return replays;
	}

	public void setReplay(List<CommentReply> replays) {
		this.replays = replays;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public long getComment_time() {
		return comment_time;
	}

	public void setComment_time(long comment_time) {
		this.comment_time = comment_time;
	}

	public String getComment_title() {
		return comment_title;
	}

	public void setComment_title(String comment_title) {
		this.comment_title = comment_title;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public String getCtDate(){
		Date date=new Date();
		date.setTime(comment_time);
		SimpleDateFormat format=new SimpleDateFormat(DATE_FORMAT);
		format.format(date);
		return format.format(date);
	}
	
	@Override
	public String toString() {
		return this.comment_title+":"+this.comment_content;
	}
}