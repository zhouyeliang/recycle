package com.recycle.android.net.action.order;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class GetOrderInfoReq extends ActAbsSthReq {
	
	private String orderId;
	
	
	public GetOrderInfoReq(String orderId){
		this.orderId = orderId;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put(NetConstant.KEY_ORDER_ID, orderId);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_ORDER_INFO;
	}

}
