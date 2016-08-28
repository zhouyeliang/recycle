package com.recycle.pad.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.pad.model.base.JackJson;

/**
 * 地址实体类
 * @author Administrator
 *
 */
public class AddressInfo extends JackJson{
	
	private String addressId;

	private String name;
	
	private String telephone;

	private String address;

	public static final String KEY_ID ="id";
	public static final String KEY_NAME ="deliveryName";
	public static final String KEY_TELEPHONE="deliveryTelephone";
	public static final String KEY_ADDRESS ="deliveryAddress";


	public AddressInfo(JSONObject obj){
		super(obj);
	}
	

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		addressId = job.optString(KEY_ID);
		name = job.optString(KEY_NAME);
		telephone = job.optString(KEY_TELEPHONE);
		address = job.optString(KEY_ADDRESS);
	}
	
	public String getAddressId() {
		return addressId;
	}


	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getTelephone() {
		return telephone;
	}


	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
}
