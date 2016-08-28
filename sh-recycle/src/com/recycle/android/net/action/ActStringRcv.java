package com.recycle.android.net.action;

import org.json.JSONException;

import android.content.Context;

public class ActStringRcv extends ActAbsSthRcv {

	public ActStringRcv(Context context) {
		super(context);
	}

	public String resultStr;

	@Override
	public boolean response(String result) throws JSONException {
		resultStr = ActionJSONStrategies.getResultString(result,
				getReceiverContext());
		return null != resultStr && !resultStr.isEmpty();
	}

}
