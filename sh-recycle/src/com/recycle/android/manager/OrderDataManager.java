package com.recycle.android.manager;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.recycle.android.model.CouponsInfo;
import com.recycle.android.model.Order;
import com.recycle.android.model.OrderCategory;
import com.recycle.android.net.action.ActAbsSthRcv;
import com.recycle.android.net.action.ActionBuilder;
import com.recycle.android.net.action.life.GetLifeOrderReq;
import com.recycle.android.net.action.life.LifeOrderReq;
import com.recycle.android.net.action.order.CancelOrderReq;
import com.recycle.android.net.action.order.GetOrderInfoReq;
import com.recycle.android.net.action.order.GetOrderListReq;
import com.recycle.android.net.action.order.GoOrderReq;

import come.recycle.android.util.NetConstant;

public class OrderDataManager {
	
	private static OrderDataManager orderDataManager = new OrderDataManager();
	
	private ArrayList<Order> myOrderList;
	
	private ArrayList<CouponsInfo> myLifeOrderList;
	
	public ArrayList<CouponsInfo> getMyLifeOrderList() {
		return myLifeOrderList;
	}
	public void setMyLifeOrderList(ArrayList<CouponsInfo> myLifeOrderList) {
		this.myLifeOrderList = myLifeOrderList;
	}
	/**
	 * 订单详情接口
	 */
	private ArrayList<Order> orderDetailList;

	public ArrayList<Order> getOrderDetailList() {
		return orderDetailList;
	}
	public void setOrderDetailList(ArrayList<Order> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}
	private ArrayList<OrderCategory> currentCategorys;
	
	public ArrayList<OrderCategory> getCurrentCategorys() {
		return currentCategorys;
	}
	public void setCurrentCategorys(ArrayList<OrderCategory> currentCategorys) {
		this.currentCategorys = currentCategorys;
	}
	private OrderDataManager(){
		myLifeOrderList = new ArrayList<CouponsInfo>();
		myOrderList = new ArrayList<Order>();
		orderDetailList = new ArrayList<Order>(); 
		currentCategorys = new ArrayList<OrderCategory>();
	}
	public static OrderDataManager getInstance(){
		return orderDataManager;
	}

	public ArrayList<Order> getOrderList(Context context,final String userId,boolean isShow,final DataResponseListener listener){
		GetOrderListReq req = new GetOrderListReq(userId);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "getOrderList " + result);
				
				JSONObject obj = new JSONObject(result);
				if(obj.optBoolean(NetConstant.RESULT_SIGN)){
					myOrderList.clear();
					JSONArray resultArray = obj.getJSONArray("resultArray");
					for(int i=0;i<resultArray.length();i++){
						JSONObject resultObj = resultArray.getJSONObject(i);
						Order info = new Order(resultObj);
						myOrderList.add(info);
					}
				}
				
				listener.response();
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv,isShow);
		return myOrderList;
	}
	
	public ArrayList<CouponsInfo> getLifeOrderList(Context context,final String userId,boolean isShow,final DataResponseListener listener){
		GetLifeOrderReq req = new GetLifeOrderReq(userId,"1");
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "getLifeOrderList " + result);
				
				JSONObject obj = new JSONObject(result);
				if(obj.optBoolean(NetConstant.RESULT_SIGN)){
					myLifeOrderList.clear();
					JSONArray resultArray = obj.getJSONArray("resultArray");
					for(int i=0;i<resultArray.length();i++){
						JSONObject resultObj = resultArray.getJSONObject(i);
						CouponsInfo info = new CouponsInfo(resultObj);
						myLifeOrderList.add(info);
					}
				}
				listener.response();
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv,isShow);
		return myLifeOrderList;
	}
	
	public void getOrderDetial(Context context,final String orderId,final DataModelResponseListener listener){
		GetOrderInfoReq req = new GetOrderInfoReq(orderId);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "getOrderDetial " + result);
				JSONObject obj = new JSONObject(result);
				Order info = new Order(obj);
				orderDetailList.add(info);
				listener.response(info);
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
		
	} 
	
	public void cancelOrder(Context context,final String orderId,final DataBooleanResponseListener listener){
		CancelOrderReq req = new CancelOrderReq(orderId);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "cancelOrder " + result);
				JSONObject obj = new JSONObject(result);
				listener.response(obj.optBoolean(NetConstant.RESULT_SIGN));
				Iterator<Order> it = myOrderList.iterator();
				while(it.hasNext()){
					Order order = it.next();
					if(order.getOrderId().equals(orderId)){
						it.remove();
					}
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
		
	} 
	
	public void goOrder(Context context,final String categoryIds,String nums,String userId,
			String name,String address,String phone,String startTime,final DataModelResponseListener listener){
		GoOrderReq req = new GoOrderReq(userId,categoryIds,nums,name,address,phone,startTime);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "goOrder " + result);
				JSONObject obj = new JSONObject(result);
				if(obj.optString(NetConstant.RESULT_SIGN).equals("true")){
					Order info = new Order(obj);
					orderDetailList.add(info);
					listener.response(info);
				}else{
					listener.response(null);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
	}
	
	public void goLifeOrder(Context context,final String categoryIds,String nums,String userId,
			final DataModelResponseListener listener){
		LifeOrderReq req = new LifeOrderReq(userId,categoryIds,nums);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "LifeOrderReq " + result);
				JSONObject obj = new JSONObject(result);
				if(obj.optString(NetConstant.RESULT_SIGN).equals("true")){
					UserDataManager.getInstance().getUser().setJfRemain(obj.getString("leftjifen"));
					Order info = new Order(obj);
					listener.response(info);
				}else{
					listener.response(null);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
		
	}
	
	public Order findOrderDetailById(String orderId){
		for(Order info:orderDetailList){
			if(info.getOrderId().equals(orderId)){
				return info;
			}
		}
		return null;
	}
	
	
	
	public ArrayList<Order> getMyOrderList() {
		return myOrderList;
	}
	public void setMyOrderList(ArrayList<Order> myOrderList) {
		this.myOrderList = myOrderList;
	}

}
