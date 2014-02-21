package com.super4.dang.domain.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.super4.dang.dao.CommentDao;
import com.super4.dang.domain.Comment;
import com.super4.dang.domain.CommentReply;

public class CommentDaoImpl implements CommentDao {

	public void createComment(Comment comment) {
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		session.saveOrUpdate(comment);
		ts.commit();
		session.close();
	}
	public void addRepalyComment(CommentReply reply) {
		Session session=HbnSessionFactory.getSession();
		Transaction ts=session.getTransaction();
		ts.begin();
		session.save(reply);
		ts.commit();
		session.close();
	}

	public List<?> findCommentReplysByCommentId(Integer commentId) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from CommentReply c where c.commentId=? order by c.turn");
		query.setInteger(0, commentId);
		List list=query.list();
		session.close();
		return list;
	}

	public List<?> findCommentsByProductId(Integer productId,Integer count) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Comment c join fetch c.user where c.productId=?");
		query.setInteger(0, productId);
		query.setFirstResult(0);
		query.setMaxResults(count);
		List list=query.list();
		session.close();
		return list;
	}
	public Comment findCommentById(Integer id) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("from Comment c join fetch c.user where c.id=?");
		query.setInteger(0, id);
		Comment comment=(Comment)query.uniqueResult();
		session.close();
		return comment;
	}
	public Long getTurn(Integer cid) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("select count(*) from CommentReply c  where c.commentId=?");
		query.setInteger(0, cid);
		Long count=(Long)query.uniqueResult();
		session.close();
		return count;
	}
	public Long getTotalCommentNumByPid(Integer pid) {
		Session session=HbnSessionFactory.getSession();
		Query query=session.createQuery("select count(*) from Comment c  where c.productId=?");
		query.setInteger(0, pid);
		Long count=(Long)query.uniqueResult();
		session.close();
		return count;
	}
	public static void main(String[] args) {
		CommentDaoImpl dao=new CommentDaoImpl();
		System.out.println(dao.getTurn(3));
	}
}
