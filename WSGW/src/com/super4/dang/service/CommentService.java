package com.super4.dang.service;

import java.util.List;

import com.super4.dang.dao.CommentDao;
import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.domain.Comment;
import com.super4.dang.domain.CommentReply;
import com.super4.dang.domain.Product;
import com.super4.dang.domain.User;

public class CommentService {
	
	private CommentDao dao=DaoImplFactory.getCommentDao();
	
	public void createOrupdateComment(Comment comment) {
		dao.createComment(comment);
	}
	public Comment getCommentById(Integer id, boolean withReplay) {
		return dao.findCommentById(id);
	}

	public Comment getCommentById(Integer id) {
		return this.getCommentById(id, false);
	}

	public void createReply(CommentReply reply) {
		dao.addRepalyComment(reply);
	}

	public List<?> findCommentByUser(User user, boolean withReply) {
		return null;
	}

	public List<?> findReplyByUser(User user) {
		return null;
	}

	public List<?> findCommentByProduct(Product product, Integer count) {
		
		return dao.findCommentsByProductId(product.getId(), count);
	}
	
	public Integer getTurn(Integer commentId){
		return Integer.parseInt(dao.getTurn(commentId).toString());
	}
	
	public Long getTotalCommentNumByProduct(Product product){
		return dao.getTotalCommentNumByPid(product.getId());
	}
}
