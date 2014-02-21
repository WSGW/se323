package com.super4.dang.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.User;
import com.super4.dang.form.UserForm;
import com.super4.dang.service.UserService;
import com.super4.dang.util.CookieUtils;
import com.super4.dang.util.EmailUtils;
import com.super4.dang.util.MD5Util;
import com.super4.dang.util.UUIDUtils;

public class RegisterAction extends MappingDispatchAction {
	private final UserService service=new UserService();
	public ActionForward one(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		return mapping.findForward("success");
	}

	public ActionForward two(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		UserForm f=(UserForm)form;
		User user=new User();
		user.setEmail(f.getEmail());
		user.setNickname(f.getNickname());
		user.setPasswd(MD5Util.md5(f.getPasswd()));
		String verify_code=UUIDUtils.getEmail_verify_code();
		user.setEmail_verify_code(verify_code);
		user.setUser_integral(0);
		user.setIs_email_verify(false);
		service.addUser(user);
		CookieUtils.createCookie(response, CookieUtils.USER_REGISTER, String.valueOf(user.getId()));
		request.setAttribute("user", user);
		EmailUtils.sendMessage(f.getEmail(), "当当网－验证码",user.getId()+"-"+verify_code );
		return mapping.findForward("success");
	}

	public ActionForward ok(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
			HttpSession session=request.getSession();
			UserForm f=(UserForm)form;	
			String id=	CookieUtils.findCookie(request, CookieUtils.USER_REGISTER);
			boolean b=service.validatorCode(id,f.getValidatecode() );
			User user=service.getUserById(Integer.valueOf(id));
			session.setAttribute("user",user);
			System.out.println(user.getEmail());
			if(b){
				user.setLast_login_time(new Date().getTime());
				user.setLast_login_ip(request.getRemoteAddr());
				service.updateUser(user);
				CookieUtils.createCookie(response,CookieUtils.USER_LOGIN, String.valueOf(user.getId()));
				session.setAttribute("is_login", true);
				return mapping.findForward("success");
			}
			ActionMessages messages=new ActionMessages();
			messages.add("validateerror",new ActionMessage("error.validate"));
			saveErrors(request, messages);
			ActionForward forward=new ActionForward();
			forward.setRedirect(true);
			forward.setPath("/registertwo.do");
			System.out.println(user.getId());
			return forward;
	}
}