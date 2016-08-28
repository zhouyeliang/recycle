package com.recycle.android.net.action;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;

/**
 * �������󷵻ص�jsonArray
 * 
 * @author zhouyeliang
 * 
 */
public class ActJarRcv extends ActAbsSthRcv {

	public JSONArray resultArray;

	public ActJarRcv(Context context) {
		super(context);
	}

	@Override
	public boolean response(String result) throws JSONException {
		resultArray = ActionJSONStrategies.getResultArray(result,
				getReceiverContext());
		return null != resultArray;
	}

}
