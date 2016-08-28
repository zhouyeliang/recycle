package com.recycle.pad.net.action;

import android.content.Context;

/**
 * rcv����
 * 
 * @author zhouyeliang
 * 
 */
public abstract class ActAbsSthRcv implements ActionReceiverImpl {

	private Context context;

	public ActAbsSthRcv(Context context) {
		this.context = context;
	}

	@Override
	public Context getReceiverContext() {
		return context;
	}
}
