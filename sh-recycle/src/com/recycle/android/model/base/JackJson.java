package com.recycle.android.model.base;

import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public abstract class JackJson {
	private final String TAG = JackJson.class.getSimpleName();

	protected JSONObject job;

	public JackJson() {
	};

	public JackJson(JSONObject job) {
		this.job = job;
		try {
			initJackJson(null != job ? job : new JSONObject());
		} catch (JSONException e) {
			Log.e(TAG, getClass().getSimpleName());
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public abstract JSONObject toJsonObj();

	/**
	 * 
	 * @param job
	 * @throws JSONException
	 */
	public abstract void initJackJson(JSONObject job) throws JSONException;

	public String toJsonStr() {
		return toJsonObj().toString();
	}
}
