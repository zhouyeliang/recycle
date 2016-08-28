package com.recycle.pad.net.action;

import java.util.Map;

public interface ActionRequestImpl{

	public String toHttpBody();

	public Map<String, String> halfwayParamMap(Map<String, String> halfway);
	
	public String getApiUrl();
}
