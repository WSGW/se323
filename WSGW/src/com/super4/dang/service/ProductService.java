package com.super4.dang.service;

import java.util.List;

import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.dao.ProductDao;
import com.super4.dang.domain.MobiePhone;
import com.super4.dang.domain.Product;

public class ProductService {
	private final ProductDao dao=DaoImplFactory.getProductDao();
	/**
	 * ���ݶ��������ID ��ѯ���÷���������ӷ��࣬�Լ������ӷ����Ʒ�ľ�������
	 * @param id ���������ID
	 * @return ��ID���ӷ��࣬�Լ��ӷ������в�Ʒ������ ��Map����
	 */
	public List getSubProduct(Integer id){
		List list=dao.getCategorysById(id);
		return list;
	}
	
	/**
	 * �������������ID ��ѯ�����и÷���Ĳ�Ʒ��Ϣ
	 * @param id ���������ID
	 * @return ������������Ĳ�Ʒ����������
	 */
	public List getAllProduct(Integer id,Integer page,String order){
		List list=dao.getBooksById(id,page,order);
		return list;
	}
	
	public Integer getCount(Integer id){
		Integer count=dao.getCount(id);
		return count;
	}
	
	public MobiePhone getBookById(Integer id){
		return dao.getBookById(id);
	}
	
	public void createProduct(Product product){
		dao.createProduct(product);
	}
	
	public List<?> getAllProductByCategoryId(Integer cid){
		return dao.getProductByCategoryId(cid);
	}
	public List<?> getProductByName(String pname){
		return dao.getProductByName(pname);
	}
	
	public void deleteProduct(Integer pid){
		
	}
	public static void main(String[] args) {
		ProductService b=new ProductService();
		System.out.println(b.getBookById(22));
	}
}
