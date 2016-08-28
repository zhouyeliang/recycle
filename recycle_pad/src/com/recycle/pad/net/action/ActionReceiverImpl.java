package com.recycle.pad.net.action;

import org.json.JSONException;

import android.content.Context;

public interface ActionReceiverImpl{
	/**
	 * @param result
	 * @return true if no error occurs
	 * @throws JSONException
	 */
	public boolean response(String result) throws JSONException;

	/**
	 * 
	 * @return the context the loading dialog is to put, do not use
	 *         applcationContext
	 */
	public Context getReceiverContext();
}
