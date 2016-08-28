package com.recycle.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.android.model.base.JackJson;

/**
 * 优惠券实体类
 * @author Administrator
 *
 */
public class CouponsInfo extends JackJson{
	
	private String couponName;

	
	private String couponId;
	
	private String number;

	private String time;
	
	private String jifen;

	public static final String KEY_ID ="Gd_ID";
	public static final String KEY_NAME ="category";
	public static final String KEY_NUMBER="number";
	public static final String KEY_TIME ="time";
	public static final String KEY_JF ="jifen";


	public CouponsInfo(JSONObject obj){
		super(obj);
	}
	

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		couponId = job.optString(KEY_ID);
		couponName = job.optString(KEY_NAME);
		number = job.optString(KEY_NUMBER);
		time = job.optString(KEY_TIME);
		jifen = job.optString(KEY_JF);
	}
	
	public String getCouponName() {
		return couponName;
	}


	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}


	public String getCouponId() {
		return couponId;
	}


	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}


	public String getNumber() {
		return number;
	}


	public void setNumber(String number) {
		this.number = number;
	}


	public String getTime() {
		return time;
	}


	public void setTime(String time) {
		this.time = time;
	}


	public String getJifen() {
		return jifen;
	}


	public void setJifen(String jifen) {
		this.jifen = jifen;
	}

	
}
