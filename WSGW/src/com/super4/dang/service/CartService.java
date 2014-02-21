package com.super4.dang.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;

import com.super4.dang.dao.DaoImplFactory;
import com.super4.dang.dao.ProductDao;
import com.super4.dang.domain.CartItem;
import com.super4.dang.domain.Product;
import com.super4.dang.domain.User;

/**
 * 该类主要是对购物车中商品信息的相关操作
 * 它包括了购买，删除，恢复，以及更改购物车中以购买商品的数量的相关操作
 * @author soft01
 *
 */
public class CartService implements Serializable{
	
	private static final long serialVersionUID = 3469732757140151248L;

	private ProductDao dao=DaoImplFactory.getProductDao();
	
	private User user;
	
	private HashMap<Integer,CartItem> items;
	
	private HashMap<Integer,CartItem> dropItems;
	public CartService(User user){
		this.user=user;
		items=new HashMap<Integer,CartItem>();
		dropItems=new HashMap<Integer,CartItem>();
	}

	public ProductDao getDao() {
		return dao;
	}

	public void setDao(ProductDao dao) {
		this.dao = dao;
	}



	public HashMap<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(HashMap<Integer, CartItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	/**
	 *购买一件商品
	 * @param productId
	 * @return
	 * 向购物车中添加一件商品
	 */
	public boolean addItem(Integer productId){
		if(!items.containsKey(productId)){
			CartItem item=new CartItem();
			Product product=dao.getProductById(productId);
			item.setProduct(product);
			item.setNum(1);
			item.setDrop(false);
			items.put(product.getId(), item);
			return true;
		}
		return false;
	}
	/**
	 * 从购物车中删除一件商品
	 * @param productId
	 * @return
	 */
	public boolean dropItem(Integer productId){
		if(items.containsKey(productId)){
			CartItem item=items.get(productId);
			item.setDrop(true);
			item.setNum(1);
			dropItems.put(productId,item);
			return true;
		}
		return false;
	}
	/**
	 * 更改商品的数量
	 * @param productId
	 * @param num
	 * @return
	 */
	public boolean updateItemNum(Integer productId,int num){
		if(items.containsKey(productId)){
			CartItem item=items.get(productId);
			item.setNum(num);
			return true;
		}
		return false;
	}
	/**
	 * 恢复已经从购物车中删除的商品
	 * @param productId
	 * @return
	 */
	public boolean recoveryItem(Integer productId){
		if(items.containsKey(productId)){
			CartItem item=items.get(productId);
			item.setDrop(false);
			dropItems.remove(productId);
			return true;
		}
		return false;
	}
	
	/**
	 * 清空购物车中的所有商品 
	 *
	 */
	public void clearItems(){
		items.clear();
		dropItems.clear();
	}
	
	/**
	 * 将购物车中的所有商品以及购物车使用者进行序列化
	 * 将其转换成字符串，以便于保存到cookie中
	 * @return
	 */
	public String serialize(){
		StringBuffer sb=new StringBuffer();
		sb.append(user.getId()).append("$");
		if(items.isEmpty()){
			sb.append('0');
			return sb.toString();
		}
		Iterator<Integer> it=items.keySet().iterator();
		while(it.hasNext()){
			Integer productId=it.next();
			int num=items.get(productId).getNum();
			boolean drop=items.get(productId).isDrop();
			sb.append(productId+","+num+","+drop);
			sb.append(";");
		}
		if(sb.length()>0);
			sb.deleteCharAt(sb.length()-1);
		return sb.toString();
	}
	
	/**
	 *将从cookie中获取的该购物车的信息进行反序列化
	 *也就是说把该信息以对象的形式存放在内存中
	 * @param content
	 */
	public void load(String content){
		clearItems();
		Integer userId=Integer.parseInt(content.substring(0,content.indexOf("$")));
		if(userId!=null && userId.equals(user.getId())){
			content=content.substring(content.indexOf("$")+1);
			if(content==null || "0".equals(content)){
				return;
			}
			if(content!=null && content.length()>0){
				String [] itemsArray=content.split(";");
				for(int i=0;i<itemsArray.length;i++){
					String[] itemArray=itemsArray[i].split(",");
					Integer productId=Integer.parseInt(itemArray[0]);
					Integer num=Integer.parseInt(itemArray[1]);
					boolean drop=Boolean.parseBoolean(itemArray[2]);
					Product product=dao.getProductById(productId);
					CartItem item=new CartItem();
					item.setProduct(product);
					item.setNum(num);
					item.setDrop(drop);
					items.put(product.getId(), item);
					if(item.isDrop()){
						dropItems.put(product.getId(), item);
					}
				}
			}
		}
	}
	
	public boolean isEmpty(){
		return items.isEmpty();
	}
	
	public HashMap<Integer, CartItem> getDropItems() {
		return dropItems;
	}

	public void setDropItems(HashMap<Integer, CartItem> dropItems) {
		this.dropItems = dropItems;
	}

	public double getTotalPrice(){
		double totalPrice=0.0;
		if(!items.isEmpty()){
			Iterator<Integer> it=items.keySet().iterator();
			while(it.hasNext()){
				Integer id=it.next();
				CartItem item=items.get(id);
				if(!item.isDrop()){
					int num=item.getNum();
					double price=item.getProduct().getFixed_price();
					totalPrice=totalPrice+num*price;
				}
			}
		}
		return totalPrice;
	}
	public double getDangPrice(){
		double dangPrice=0.0;
		if(!items.isEmpty()){
			Iterator<Integer> it=items.keySet().iterator();
			while(it.hasNext()){
				Integer id=it.next();
				CartItem item=items.get(id);
				if(!item.isDrop()){
					int num=item.getNum();
					double price=item.getProduct().getDang_price();
					dangPrice=dangPrice+num*price;
				}
			}
		}
		return dangPrice;
	}
	public static void main(String[] args) {
		User user=new User();
		user.setId(2);
		CartService cs=new CartService(user);
		System.out.println(cs.serialize());
	}
}
