
package com.super4.dang.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.service.ProductService;
public class CategoryAction extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String cid=request.getParameter("id");	
		if(cid!=null){
			ProductService service=new ProductService();
			Integer id=Integer.parseInt(cid);
			List list=service.getSubProduct(id);
			request.setAttribute("list",list);
		}
		return mapping.findForward("success");
	}
}