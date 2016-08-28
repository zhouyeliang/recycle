package com.recycle.pad.model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.pad.model.base.JackJson;

/**
 * 订单实体类
 * @author Administrator
 *
 */
public class Order extends JackJson {

	private String orderId;


	private String orderNo;

	/**
	 * 订单总积分
	 */
	private String totalJf;

	/**
	 * 下单时间
	 */
	private String orderTime;

	/**
	 * 
	 */
	private ArrayList<OrderCategory> orderCategorys;


	/**
	 * 订单状态
	 */
	private String orderState;
	
	private AddressInfo addressInfo;


	/**
	 * 订单图片url
	 */
	private String imgUrl;
	
	/**
	 * 取货时间
	 */
	private String pickTime;


	public static final String KEY_ID = "order_id";
	public static final String KEY_NUMBER = "order_no";
	public static final String KEY_PRICE = "order_price";
	public static final String KEY_ORDER_TIME = "order_date";
	public static final String KEY_USER_NAME = "order_lxr";
	public static final String KEY_PHONE = "order_phone";
	public static final String KEY_ADDRESS = "order_address";
	public static final String KEY_ORDER_STATE = "order_status";
	public static final String KEY_ORDER_CATEGORYS = "ordergoods";
	public static final String KEY_PICK_TIME = "order_getdate";

	public Order(JSONObject obj) {
		super(obj);
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getOrderState() {
		return orderState;
	}
	

	public String getPickTime() {
		return pickTime;
	}

	public void setPickTime(String pickTime) {
		this.pickTime = pickTime;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getTotalJf() {
		return totalJf;
	}

	public void setTotalJf(String totalJf) {
		this.totalJf = totalJf;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	

	public AddressInfo getAddressInfo() {
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
	}
	

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public ArrayList<OrderCategory> getOrderCategorys() {
		return orderCategorys;
	}

	public void setOrderCategorys(ArrayList<OrderCategory> orderCategorys) {
		this.orderCategorys = orderCategorys;
	}
	

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		orderId = job.optString(KEY_ID);
		totalJf = job.optString(KEY_PRICE);
		orderTime = job.optString(KEY_ORDER_TIME);
		orderNo = job.optString(KEY_NUMBER);
		pickTime = job.optString(KEY_PICK_TIME);
		orderState = getOrderStateString(job.optInt(KEY_ORDER_STATE));
		addressInfo = new AddressInfo(job);
		orderCategorys = new ArrayList<OrderCategory>();
		JSONArray array = job.getJSONArray(KEY_ORDER_CATEGORYS);
		if(array!=null){
			for(int i=0;i<array.length();i++){
				OrderCategory info = new OrderCategory(array.getJSONObject(i));
				orderCategorys.add(info);
			}
		}
	}

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	public String getOrderStateString(int state) {
		String result = null;
		switch (state) {
		case 1:
			result = "已生成";
			break;
		case 2:
			result = "已付款";
			break;
		case 3:
			result = "已确认";
			break;
		case 4:
			result = "已完成";
			break;
		case 5:
			result = "已取消";
			break;
		case 6:
			result = "已取消";
			break;
		case 7:
			result = "已作废";
			break;
				
		default:
			result = "已取消";
			break;
		}
		return result;
	}
}
