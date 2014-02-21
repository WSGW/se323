package com.super4.dang.action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;

import com.super4.dang.domain.User;
import com.super4.dang.service.UserService;
import com.super4.dang.util.CookieUtils;
import com.super4.dang.util.MD5Util;

public class LoginAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DynaActionForm f=(DynaActionForm)form;
		HttpSession session=request.getSession();
		String email=(String)f.get("txtUsername");
		String passwd=(String)f.get("txtPassword");
		UserService service=new UserService();
		User user=service.getUserByEmail(email);
		ActionMessages messages=new ActionMessages();
		if(user!=null){
				if(MD5Util.md5(passwd).equals(user.getPasswd())){
					session.setAttribute("user", user);
					if(!user.isIs_email_verify()){
						ActionMessage message=new ActionMessage("jsp.validate");
						messages.add("validateCode",message);
						saveErrors(request, messages);
						CookieUtils.createCookie(response, CookieUtils.USER_REGISTER, String.valueOf(user.getId()));
						ActionForward forward=new ActionForward();
						forward.setRedirect(true);
						forward.setPath("/registertwo.do");
						System.out.println(user.getId());
						return forward;
					}
					user.setLast_login_time(new Date().getTime());
					user.setLast_login_ip(request.getRemoteAddr());
					
					service.updateUser(user);
					CookieUtils.createCookie(response, CookieUtils.USER_LOGIN, String.valueOf(user.getId()));
					session.setAttribute("is_login", true);
					session.setAttribute("user",user);
					return mapping.findForward("success");
				}
			ActionMessage message=new ActionMessage("jsp.login");
			messages.add("emailotpasswd",message);
			saveErrors(request, messages);
			return mapping.findForward("fail");
		}
		ActionMessage message=new ActionMessage("jsp.name");
		messages.add("email",message);
		saveErrors(request, messages);
		return mapping.findForward("fail");
	}
}
