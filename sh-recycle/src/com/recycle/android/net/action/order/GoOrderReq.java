package com.recycle.android.net.action.order;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class GoOrderReq extends ActAbsSthReq {
	
	private String userId;
	
	private String categoryIds;
	
	private String numbers;
	
	private String name;
	
	private String address;
	
	private String phone;
	
	private String startTime;
	
	public GoOrderReq(String userId,String categoryIds,String numbers,String name,String address,String phone
			,String startTime){
		this.userId = userId;
		this.categoryIds = categoryIds;
		this.numbers = numbers;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.startTime = startTime;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("user_id", userId);
		halfway.put("category_id", categoryIds);
		halfway.put("number", numbers);
		halfway.put("name", name);
		halfway.put("address", address);
		halfway.put("phone", phone);
		halfway.put("start_time", startTime);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_ORDER;
	}

}
