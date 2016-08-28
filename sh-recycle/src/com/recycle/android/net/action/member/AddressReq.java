package com.recycle.android.net.action.member;

import java.util.Map;

import com.recycle.android.net.action.ActAbsSthReq;
import come.recycle.android.util.NetConstant;

public class AddressReq extends ActAbsSthReq {
	
	String userId;
	
	String userName;
	
	String telephone;
	
	String address;
	
	public AddressReq(String userId,String userName,String telephone,String address){
		this.userId = userId;
		this.userName = userName;
		this.telephone = telephone;
		this.address = address;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("user_id", userId);
		halfway.put("address", address);
		halfway.put("phone", telephone);
		halfway.put("name", userName);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_ADD_ADDRESS;
	}

}
