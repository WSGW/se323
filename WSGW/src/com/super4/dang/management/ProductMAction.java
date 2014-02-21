package com.super4.dang.management;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.MappingDispatchAction;

import com.super4.dang.domain.Category;
import com.super4.dang.domain.MobiePhone;
import com.super4.dang.form.ProductForm;
import com.super4.dang.service.CategoryService;
import com.super4.dang.service.ProductService;

public class ProductMAction extends MappingDispatchAction {
	public ActionForward showform1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
			CategoryService cs=new CategoryService();
			Category category=(Category)cs.getThreeCategory(1);
			request.setAttribute("category", category);
			request.setAttribute("subcategory",category.getSubcategory().get(0).getSubcategory());
			return mapping.findForward("success");
	}
	public ActionForward showform2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		ProductForm f=(ProductForm)form;
		MobiePhone product=new MobiePhone();
		BeanUtils.copyProperties(product,f);
		session.setAttribute("product", product);
		return mapping.findForward("success");
	}
	public ActionForward showform3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		ProductForm f=(ProductForm)form;
		MobiePhone product=(MobiePhone)session.getAttribute("product");
		BeanUtils.copyProperties(product,f);
		product.setPublish_time(f.getPublish_time().getTime());
		product.setShangshi_time(f.getShangshi_time().getTime());
		session.setAttribute("product", product);
		return mapping.findForward("success");
	}
	@SuppressWarnings("unchecked")
	public ActionForward addproduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session=request.getSession();
		ProductService ps=new ProductService();
		MobiePhone product=(MobiePhone)session.getAttribute("product");
		String filename=null;
		FileItem item_image=null;
		try {
			DiskFileItemFactory dfif=new DiskFileItemFactory();
			ServletFileUpload sfu=new ServletFileUpload(dfif);
			sfu.setFileSizeMax(10*1024*1024);
			List list=sfu.parseRequest(request);
			for(FileItem item:(List<FileItem>)list){
				if(!item.isFormField()){
					filename=item.getName();
					item_image=item;
				}
			}
			product.setProduct_pic(filename);
			product.setHas_deleted(0);
			product.setAdd_time(new Date().getTime());
			ps.createProduct(product);
			String filepath=getServlet().getServletContext().getRealPath("phone_pic/"+filename);
			System.out.println(filename);
			System.out.println(filepath);
			item_image.write(new File(filepath));
			
		}catch(Exception e){
			throw e;
		}
		request.setAttribute("redirecturl", "showmain.do");
		return mapping.findForward("success");
	}
	
	public ActionForward showAllProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String cid=request.getParameter("cid");
		String pname=request.getParameter("pname");
		ProductService ps=new ProductService();
		List products=null;
		if(cid!=null){
			products=ps.getAllProductByCategoryId(Integer.parseInt(cid));
		}
		if(pname!=null){
			products=ps.getProductByName(pname);
		}
		request.setAttribute("products",products);
		return mapping.findForward("success");
	}

	public ActionForward showsearch(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		CategoryService cs=new CategoryService();
		Category category=(Category)cs.getThreeCategory(1);
		request.setAttribute("category", category);
		request.setAttribute("subcategory",category.getSubcategory().get(0).getSubcategory());
		return mapping.findForward("success");
	}
	
	public ActionForward deleteProduct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		String pid=request.getParameter("pid");
		ProductService ps=new ProductService();
		ps.deleteProduct(Integer.parseInt(pid));
		return mapping.findForward("success");
	}
	
	public ActionForward showindex(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException{
		System.out.println(request.getRequestURL());
		System.out.println(mapping.findForward("success"));
		return mapping.findForward("success");
	}
	public ActionForward JSONCategory(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		String cid=request.getParameter("cid");
		CategoryService cs=new CategoryService();
		List list=cs.getSubCategory(Integer.parseInt(cid));
		JSONArray array=JSONArray.fromObject(list);
		response.getWriter().write(array.toString());
		return null;
	}
	
}
