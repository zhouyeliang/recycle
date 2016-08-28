package com.recycle.pad.net.action.life;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;
import come.recycle.pad.util.NetConstant;


public class GetLifeOrderReq extends ActAbsSthReq {
	
	private String orderPay;
	
	private String userId;
	
	public GetLifeOrderReq(String userId,String orderPay){
		this.userId = userId;
		this.orderPay = orderPay;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("user_id", userId);
		halfway.put("order_pay", orderPay);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_LIFE_ORDER_LIST;
	}

}
