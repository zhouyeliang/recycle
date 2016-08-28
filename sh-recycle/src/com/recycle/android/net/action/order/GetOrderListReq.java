package com.recycle.android.net.action.order;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class GetOrderListReq extends ActAbsSthReq {
	
	private String userId;
	
	public GetOrderListReq(String userId){
		this.userId = userId;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put(NetConstant.KEY_USER_ID, userId);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_ORDER_LIST;
	}

}
