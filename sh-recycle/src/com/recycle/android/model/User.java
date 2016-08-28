package com.recycle.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.android.model.base.JackJson;

/**
 * 用户类
 * @author zhouyeliang
 *
 */
public class User extends JackJson{
	
	private String userId;
	
	private String userName;
	
	private String qrCode;


	private AddressInfo address;
	
	public AddressInfo getAddress() {
		return address;
	}

	public void setAddress(AddressInfo address) {
		this.address = address;
	}

	/**
	 * 积分余额
	 */
	private String jfRemain;
	
	public static final String KEY_USER_ID = "userId";
	
	public static final String KEY_USER_NAME = "uname";
	
	public static final String KEY_QR_CODE = "qrCode";
	
	public static final String KEY_DELIVER_NAME = "deliveryName";
	
	public static final String KEY_DELIVER_ADDRESS = "deliveryAddress";
	
	public static final String KEY_DELIVER_TELEPHONE= "deliveryTelephone";
	
	public static final String KEY_JF_REMAIN = "jfRemain";
	
	public User(JSONObject obj){
		super(obj);
	}

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		userId = job.optString(KEY_USER_ID);
		userName = job.optString(KEY_USER_NAME);
		//qrCode = job.optString(KEY_QR_CODE);
		qrCode = "12345HU";
		address = new AddressInfo(job);
		jfRemain = job.optString(KEY_JF_REMAIN);
	}
	
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getJfRemain() {
		return jfRemain;
	}

	public void setJfRemain(String jfRemain) {
		this.jfRemain = jfRemain;
	}
	
	
}
