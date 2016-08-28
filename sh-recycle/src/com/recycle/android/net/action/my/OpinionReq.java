package com.recycle.android.net.action.my;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class OpinionReq extends ActAbsSthReq {
	
	String userId;
	
	String content;
	
	public OpinionReq(String userId,String content){
		this.userId = userId;
		this.content = content;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		if(userId!=null){
			halfway.put("user_id", userId);
		}
		halfway.put("content", content);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_OPINION;
	}

}
