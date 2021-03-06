package com.recycle.pad.net.action.life;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;
import come.recycle.pad.util.NetConstant;


public class GetLifeReq extends ActAbsSthReq {
	
	private String id;
	
	public GetLifeReq(String id){
		this.id = id;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("typeid", id);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_LIFE_LIST;
	}

}
