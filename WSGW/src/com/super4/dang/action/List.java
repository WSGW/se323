
package com.super4.dang.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.domain.Category;
import com.super4.dang.service.CategoryService;

public class List extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		CategoryService cs=new CategoryService();
		String cid=request.getParameter("id");
		System.out.println(cid);
		Integer id=Integer.parseInt(cid);
		Category category=cs.getParentCategory(id);
		request.setAttribute("category", category);
		return mapping.findForward("success");
	}
}