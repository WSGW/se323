package com.super4.dang.dao;

import java.util.List;

import com.super4.dang.domain.Comment;
import com.super4.dang.domain.CommentReply;

public interface CommentDao {
	public List<?> findCommentsByProductId(Integer productId,Integer count);
	
	public void createComment(Comment comment);
	
	
	public List<?> findCommentReplysByCommentId(Integer commentId);
	
	public void addRepalyComment(CommentReply reply);
	
	public Comment findCommentById(Integer id);
	
	public Long getTurn(Integer cid);
	
	public Long getTotalCommentNumByPid(Integer pid);
}
