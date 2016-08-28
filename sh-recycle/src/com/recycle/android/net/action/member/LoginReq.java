package com.recycle.android.net.action.member;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class LoginReq extends ActAbsSthReq {
	
	String userName;
	
	String password;
	
	public LoginReq(String userName,String password){
		this.userName = userName;
		this.password = password;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put(NetConstant.KEY_USERNAME, userName);
		halfway.put(NetConstant.KEY_PASSWORD, password);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_LOGIN;
	}

}
