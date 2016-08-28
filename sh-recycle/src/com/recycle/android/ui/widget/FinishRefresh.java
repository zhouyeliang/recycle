package com.recycle.android.ui.widget;

import android.os.AsyncTask;

import com.handmark.pulltorefresh.library.PullToRefreshAdapterViewBase;
import com.recycle.android.manager.DataResponseListener;

public class FinishRefresh extends AsyncTask<Void, Void, Void> {

	private PullToRefreshAdapterViewBase<?> pullToRefresh;
	
	private DataResponseListener listener;
	
	public FinishRefresh(PullToRefreshAdapterViewBase<?> pullToRefresh) {
		this.pullToRefresh = pullToRefresh;
	}

	public FinishRefresh(PullToRefreshAdapterViewBase<?> pullToRefresh,DataResponseListener listener) {
		this.pullToRefresh = pullToRefresh;
		this.listener =listener;
	}

	@Override
	protected Void doInBackground(Void... params) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		}
		return null;
	}

	@Override
	protected void onPostExecute(Void result) {
		// adapter.notifyDataSetChanged();
		pullToRefresh.onRefreshComplete();
		if(listener!=null){
			listener.response();
		}
	}

}
