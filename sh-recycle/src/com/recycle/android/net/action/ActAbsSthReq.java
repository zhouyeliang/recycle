package com.recycle.android.net.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 * 
 * @author zhouyeliang
 * 
 */
public abstract class ActAbsSthReq implements ActionRequestImpl {
	private Map<String, String> paramKV;

	@Override
	public String toHttpBody() {
		getNewBasicParamMap();
		halfwayParamMap(paramKV);
		return finishTheURL(paramKV);
	}
	
	public Map<String, String> getNewBasicParamMap() {
		paramKV = new HashMap<String, String>();
		return paramKV;
	}

	public String finishTheURL(Map<String, String> map) {
		if (null == map)
			return "";// 0418
		StringBuffer url = new StringBuffer(getApiUrl());
		StringBuffer params = new StringBuffer();
		
		String[] arrays = new String[] {};
		arrays = map.keySet().toArray(arrays);
		Arrays.sort(arrays);
		
		for (String str : arrays) {
			try {
				params.append(str).append("=").append(URLEncoder.encode(new String(map.get(str)), "UTF-8"))
						.append("&");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		String result = null;
		if(!map.isEmpty()){
			String param = params.substring(0, params.length()-1);
			result  = url.append("?").append(param).toString();
		}else{
			result = url.toString();
		}
		return result;
	}

}
