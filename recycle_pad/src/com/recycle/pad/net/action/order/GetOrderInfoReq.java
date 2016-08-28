package com.recycle.pad.net.action.order;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;
import come.recycle.pad.util.NetConstant;

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
