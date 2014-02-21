package com.super4.dang.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Comment;
import com.super4.dang.domain.CommentReply;
import com.super4.dang.domain.User;
import com.super4.dang.service.CommentService;
import com.super4.dang.service.UserService;
import com.super4.dang.util.AuthorizeUtils;

public class CommentAction extends MappingDispatchAction {
	public ActionForward newcomment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		CommentService cs=new CommentService();
		UserService us=new UserService();
		boolean b=AuthorizeUtils.isLogin(request, us);
		if(b){
			String productId=request.getParameter("productId");
			String comment_title=request.getParameter("comment_title").trim();
			String comment_content=request.getParameter("comment_content");
			Integer pid=Integer.parseInt(productId);
			User user=(User)session.getAttribute("user");
			Comment comment =new Comment();
			comment.setProductId(pid);
			comment.setComment_title(comment_title);
			comment.setComment_content(comment_content);
			comment.setUser(user);
			comment.setComment_time(new Date().getTime());
			cs.createOrupdateComment(comment);
			response.sendRedirect("detail.do?pid="+productId);
		}else{
			response.sendRedirect("../authorize/loginForm.do");
		}
		
		return null;
	}
	public ActionForward newreply(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession();
		CommentService cs=new CommentService();
		String productId=request.getParameter("productId");
		String commentId=request.getParameter("commentId");
		String comment_content=request.getParameter("comment_content");
		Integer cid=Integer.parseInt(commentId);
		User user=(User)session.getAttribute("user");
		CommentReply reply =new CommentReply();
		reply.setCommentId(cid);
		reply.setUser(user);
		reply.setReply_content(comment_content);
		reply.setReply_time(new Date().getTime());
		reply.setTurn(cs.getTurn(cid)+1);
		cs.createReply(reply);
		response.sendRedirect("detail.do?pid="+productId);
		return null;
	}
}
