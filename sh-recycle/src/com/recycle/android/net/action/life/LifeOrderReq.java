package com.recycle.android.net.action.life;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class LifeOrderReq extends ActAbsSthReq {
	
	private String userId;
	
	private String categoryIds;
	
	private String numbers;
	
	public LifeOrderReq(String userId,String categoryIds,String numbers){
		this.userId = userId;
		this.categoryIds = categoryIds;
		this.numbers = numbers;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("user_id", userId);
		halfway.put("category_id", categoryIds);
		halfway.put("number", numbers);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_LIFE_ORDER;
	}

}
