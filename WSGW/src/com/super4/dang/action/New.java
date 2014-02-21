
package com.super4.dang.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.super4.dang.domain.RHN;
import com.super4.dang.service.RHNService;

public class New extends Action {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		RHNService rhn=new RHNService();
		String more=request.getParameter("more");
		Integer count=8;
		if(more!=null){
			count=100;
		}
		List news=rhn.findAllRHN(RHN.TYPE_NEW,count);
		request.setAttribute("news", news);
		return mapping.findForward("success");
	}
}