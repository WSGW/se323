package com.super4.dang.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.service.ProductService;
import com.super4.dang.util.OrderUtils;


public class BookList extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HttpSession session=request.getSession();
		ProductService service=new ProductService();
		String cid=request.getParameter("cid");
		String page=request.getParameter("num");
		String order=request.getParameter("order");
		Integer select=1;
		Integer totalPage=0;
		Integer p=1;
		List books=null;
		if(page!=null){
			p=Integer.parseInt(page);
		}
		if(order!=null){
			select=Integer.parseInt(order);
		}else{
			Integer rule=(Integer)session.getAttribute("select");
			if(rule!=null){
				select=rule;
			}
		}
		if(!cid.equals(session.getAttribute("cid"))){
			select=1;
		}
		Integer id=Integer.parseInt(cid);
		order=OrderUtils.getOrder(select);
		books=service.getAllProduct(id,p,order);
		totalPage=service.getCount(id);
		request.setAttribute("books",books);
		request.setAttribute("page", p);
		request.setAttribute("totalPage",totalPage );
		request.setAttribute("cid", cid);
		session.setAttribute("cid", cid);
		session.setAttribute("select", select);
		return mapping.findForward("success");
	}
}