package com.super4.dang.domain;

public class CommentReply implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private Integer commentId;
	private User user;
	private long reply_time;
	private String reply_content;
	private Integer turn;
	public CommentReply() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getReply_content() {
		return reply_content;
	}

	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}

	public long getReply_time() {
		return reply_time;
	}

	public void setReply_time(long reply_time) {
		this.reply_time = reply_time;
	}

	public Integer getTurn() {
		return turn;
	}

	public void setTurn(Integer turn) {
		this.turn = turn;
	}
	@Override
	public String toString() {
		return this.getId()+":"+this.getReply_time()+":"+this.getReply_content();
	}
}