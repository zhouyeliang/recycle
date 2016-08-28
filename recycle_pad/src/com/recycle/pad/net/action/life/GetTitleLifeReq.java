package com.recycle.pad.net.action.life;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;
import come.recycle.pad.util.NetConstant;


public class GetTitleLifeReq extends ActAbsSthReq {
	
	public GetTitleLifeReq(){
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_GET_LIFE_TITLE_LIST;
	}

}
