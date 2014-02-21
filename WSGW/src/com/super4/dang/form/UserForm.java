package com.super4.dang.form;

import org.apache.struts.action.ActionForm;

public class UserForm extends ActionForm{
	private static final long serialVersionUID = 3928118257096562365L;
	
	private String email;
	
	private String nickname;
	
	private String passwd;

	private String repeatPass;
	
	private String txtVerifyCode;
	
	private String validatecode;
	
	
	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getRepeatPass() {
		return repeatPass;
	}

	public void setRepeatPass(String repeatPass) {
		this.repeatPass = repeatPass;
	}

	public String getTxtVerifyCode() {
		return txtVerifyCode;
	}

	public void setTxtVerifyCode(String txtVerifyCode) {
		this.txtVerifyCode = txtVerifyCode;
	}
	
//	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
//		ActionErrors errors=new ActionErrors();
//			HttpSession session=request.getSession();
//			String yanzm=(String)session.getAttribute("validateCode");
//			System.out.println("start");
//			System.out.println(yanzm+"\t"+txtVerifyCode);
//			if(!txtVerifyCode.equals(yanzm)){
//				System.out.println("error");
//				ActionMessage message=new ActionMessage("error.validate");
//				System.out.println(message.getKey()+"\t"+message.getValues());
//			errors.add("validateerror",message);
//			}
//		return errors;
//	}
}
