package com.recycle.android.net.action;

import java.util.Map;

public abstract class NoParamsReq extends ActAbsSthReq {
	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		return halfway;
	}

}
