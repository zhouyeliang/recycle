package com.recycle.pad.net.action.category;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;

import come.recycle.pad.util.NetConstant;

public class GetCategoryReq extends ActAbsSthReq {
	
	String typeId;
	
	public GetCategoryReq(String typeId){
		this.typeId = typeId;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put(NetConstant.KEY_TYPE_ID, typeId);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_CATEGORY;
	}

}
