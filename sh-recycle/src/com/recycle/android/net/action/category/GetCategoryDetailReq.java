package com.recycle.android.net.action.category;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class GetCategoryDetailReq extends ActAbsSthReq {
	
	String typeId;
	
	public GetCategoryDetailReq(String typeId){
		this.typeId = typeId;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put(NetConstant.KEY_TYPE_ID, typeId);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_CATEGORY_DETAIL;
	}

}
