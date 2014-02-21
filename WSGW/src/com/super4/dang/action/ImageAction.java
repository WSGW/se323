package com.super4.dang.action;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("image/jpeg");
		BufferedImage image=new BufferedImage(80,20,BufferedImage.TYPE_INT_BGR);
		Graphics g=image.getGraphics();
		int i=(int)(Math.random()*255);
		int j=(int)(Math.random()*255);
		int x=(int)(Math.random()*255);
		g.setColor(new Color(i,j,x));
		g.fillRect(0, 0, 80, 20);
		char[] words={'a','b','c','d','e','f','g','h','i','j',
	              'k','l','m','n','o','p','g','r','s','t',
	              'u','v','w','x','y','z','A','B','C','D',
	              'E','F','G','H','I','J','K','L','M','N',
	              'O','P','Q','R','S','T','U','V','W','X',
	              'Y','Z','0','1','2','3','4','5','6','7','8','9'};
		g.setColor(new Color(255,255,255));//设置颜色
		int[] index={(int)(Math.random()*61+1),(int)(Math.random()*61+1),(int)(Math.random()*61+1),(int)(Math.random()*61+1)};
		g.drawChars(words, index[0], 1, 10, 15);
		g.drawChars(words, index[1], 1, 30, 15);
		g.drawChars(words, index[2], 1, 50, 15);
		g.drawChars(words, index[3], 1, 70, 15);
		//压缩成jpeg格式
		OutputStream os=response.getOutputStream();
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(os);
		//把BufferedImage对象中的图像信息编码后
		//向创建该对象(encoder)时指定的输出流输出
		encoder.encode(image);
		os.close();
		char[] copy={words[index[0]],words[index[1]],words[index[2]],words[index[3]]};
		String yanzm=new String(copy);
		yanzm=yanzm.toLowerCase();
		HttpSession session=request.getSession();
		session.setAttribute("validateCode", yanzm);
		return null;
	}
}
