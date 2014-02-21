
package com.super4.dang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.domain.Category;
import com.super4.dang.service.CategoryService;

public class AllCategory extends Action {
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String cid=request.getParameter("id");
		if(cid!=null){
			Integer id=Integer.parseInt(cid);
			CategoryService service=new CategoryService();
			Category category=service.getThreeCategory(id);
			request.setAttribute("category",category);
		}
		return mapping.findForward("success");
	}
}