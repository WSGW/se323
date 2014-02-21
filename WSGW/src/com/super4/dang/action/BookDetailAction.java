package com.super4.dang.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Category;
import com.super4.dang.domain.Comment;
import com.super4.dang.domain.MobiePhone;
import com.super4.dang.domain.User;
import com.super4.dang.service.CategoryService;
import com.super4.dang.service.CommentService;
import com.super4.dang.service.ProductService;
import com.super4.dang.service.UserService;

public class BookDetailAction extends MappingDispatchAction {
	
	private static final Logger logger=Logger.getLogger(BookDetailAction.class);
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pid=request.getParameter("pid");
		String num=request.getParameter("option");
		Integer count=10;
		ProductService bs=new ProductService();
		CommentService cs=new CommentService();
		CategoryService cate=new CategoryService();
		Integer id=Integer.parseInt(pid);
		MobiePhone book=bs.getBookById(id);
		if(num!=null){
			count=Integer.parseInt(num);
		}
		logger.info(count);
		List<?> comms=cs.findCommentByProduct(book, count);
		Long number=cs.getTotalCommentNumByProduct(book);
		Category category=cate.getTopParentCategory(id);
		request.setAttribute("category", category);
		request.setAttribute("comments", comms);
		request.setAttribute("number", number);
		request.setAttribute("book", book);
		return mapping.findForward("success");
	}
	public ActionForward c_form(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String productId=request.getParameter("productId");
		String commentId=request.getParameter("commentId");
		String num=request.getParameter("num");
		CommentService cs=new CommentService();
		request.setAttribute("action","newcomment");
		if(commentId!=null){
			Comment comment=cs.getCommentById(Integer.parseInt(commentId));
			request.setAttribute("productId", productId);
			request.setAttribute("commentId",commentId);
			request.setAttribute("title", "»Ø¸´  "+num+" Â¥ "+comment.getUser().getNickname()+" µÄÌû×Ó");
		}else{
			request.setAttribute("productId", productId);
			request.setAttribute("title", "");
		}
		return mapping.findForward("success");
	}
	public ActionForward pingFen(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId=request.getParameter("userId");
		String option=request.getParameter("option");
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");
		if(userId.equals(user.getId().toString())){
			response.getWriter().write('0');
			return null;
		}
		UserService us=new UserService();
		User user1=us.getUserById(Integer.parseInt(userId));
		if(option.equals("sh")){
			user1.setUser_integral(user1.getUser_integral()+10);
		}else{
			user1.setUser_integral(user1.getUser_integral()-10);
		}
		us.updateUser(user1);
		response.getWriter().write('1');
		return null;
	}
}
