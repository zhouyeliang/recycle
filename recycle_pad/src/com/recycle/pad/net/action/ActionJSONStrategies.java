package com.recycle.pad.net.action;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import come.recycle.pad.util.NetConstant;

import android.content.Context;


/**
 * 
 * @author zhouyeliang
 * 
 */
public class ActionJSONStrategies {

	/**
	 * 
	 * @param result
	 * @return
	 * @throws JSONException
	 */
	public static Object getResultObject(String result) throws JSONException {
		return getResultTrueObject(result, null);
	}

	/**
	 * 
	 * @param result
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static JSONObject getResultObject(String result, Context context)
			throws JSONException {
		Object obj = getResultTrueObject(result, context);
		if (null != obj) {
			if (obj instanceof JSONObject)
				return (JSONObject) obj;
		}
		return null;
	}

	/**
	 * ��������jsonarray��result
	 * 
	 * @param result
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static JSONArray getResultArray(String result, Context context)
			throws JSONException {
		Object obj = getResultTrueObject(result, context);
		if (null != obj) {
			if (obj instanceof JSONArray)
				return (JSONArray) obj;
		}
		return null;
	}

	/**
	 * ��������string��result
	 * 
	 * @param result
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static String getResultString(String result, Context context)
			throws JSONException {
		Object obj = getResultTrueObject(result, context);
		if (null != obj) {
			if (obj instanceof String)
				return (String) obj;
		}
		return null;
	}

	/**
	 * 
	 * @param result
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static boolean getResultBoolean(String result) throws JSONException {
		return new JSONObject(result).optBoolean(NetConstant.RESULT_DATA);
	}

	/**
	 * ��ȡRESULT_OBJECT���jsonstringת���Ķ���
	 * 
	 * @param result
	 * @param context
	 * @return
	 * @throws JSONException
	 */
	public static Object getResultTrueObject(String result, Context context)
			throws JSONException {
		JSONObject job = new JSONObject(result);
		if (job.optString(NetConstant.RESULT_CODE).equals(NetConstant.RESULT_OK)) {
			return job.opt(NetConstant.RESULT_DATA);
		} else if (job.optString(NetConstant.RESULT_SIGN).equals(
				NetConstant.RESULT_TRUE)) {
			return job.opt(NetConstant.RESULT_OBJ);
		} else {
			showErrMsg(result, context);
		}
		return null;
	}

	/**
	 * 
	 * @param result
	 * @param context
	 *            TODO
	 * @throws JSONException
	 */
	public static void showErrMsg(String result, Context context)
			throws JSONException {
		JSONObject job = new JSONObject(result);
		String errMsg = job.optString(NetConstant.RESULT_ERROR_MSG);
		if (!errMsg.isEmpty()) {
			//DialogLoaderHelper.showToast(MyApplication.app(), errMsg);
		}
	}

}
