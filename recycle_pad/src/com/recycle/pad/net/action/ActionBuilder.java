package com.recycle.pad.net.action;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import com.recycle.pad.net.http.HttpRequestTask;

import android.os.AsyncTask.Status;

public class ActionBuilder {

	private Map<String, ActionRequestImpl> actingMap;

	private static ActionBuilder ab;

	private ActionBuilder() {
		actingMap = new HashMap<String, ActionRequestImpl>();
	}

	public static ActionBuilder getInstance() {
		if (null == ab) {
			ab = new ActionBuilder();
		}
		return ab;
	}

	SoftReference<HttpRequestTask> lastRequest;

	public void request(ActionRequestImpl actReq, ActionReceiverImpl actRcv,boolean isShowProgress) {
		if (null == actReq)
			throw new IllegalStateException("action nil");
		// map?
		HttpRequestTask httpRequestTask = new HttpRequestTask(actRcv,isShowProgress);
		lastRequest = new SoftReference<HttpRequestTask>(httpRequestTask);
		httpRequestTask.execute(actReq.toHttpBody());
		httpRequestTask = null;
	}
	
	public void request(ActionRequestImpl actReq, ActionReceiverImpl actRcv) {
		if (null == actReq)
			throw new IllegalStateException("action nil");
		// map?
		HttpRequestTask httpRequestTask = new HttpRequestTask(actRcv,true);
		lastRequest = new SoftReference<HttpRequestTask>(httpRequestTask);
		httpRequestTask.execute(actReq.toHttpBody());
		httpRequestTask = null;
	}

	public void cancelLastReq() {
		if (null == lastRequest)
			return;
		HttpRequestTask httpRequestTask = lastRequest.get();
		if (null != httpRequestTask
				&& httpRequestTask.getStatus() == Status.RUNNING)
			httpRequestTask.cancel(true);
		lastRequest.clear();
	}

}
