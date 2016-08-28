package com.recycle.pad.net.http;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import org.json.JSONException;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.AsyncTask;
import android.util.Log;
import android.view.KeyEvent;

import com.example.recycle_pad.R;
import com.recycle.pad.net.action.ActionReceiverImpl;

public class HttpRequestTask extends AsyncTask<String, Integer, String> {
	private final String TAG = HttpRequestTask.class.getSimpleName();
	private static final int TIMEOUT = 30 * 1000;

	private ActionReceiverImpl receiver;
	
	/**
	 * 加载框
	 */
	private Dialog processDialog;
	
	private boolean isShowProgress;

	public HttpRequestTask(ActionReceiverImpl receiver,boolean isShowProgress) {
		this.receiver = receiver;
		this.isShowProgress = isShowProgress;
	}
	

	@Override
	protected String doInBackground(String... params) {
		String result = null;
		try {
			result = HttpRequestUtil.doHttpRequest(params[0]);
		} catch (UnknownHostException e) {
			publishProgress(1);
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			publishProgress(2);
			e.printStackTrace();
		} catch (IOException e) {
			publishProgress(10);
			e.printStackTrace();
		}
		if (null == result)
			result = "";
		return result;
	}

	@Override
	protected void onPreExecute() {
		if(isShowProgress){
			//showRoundProcessDialog(receiver.getReceiverContext());
		}
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		if (isCancelled())
			return;// 0620
		Log.i(TAG, "result::" + result);
		try {
			receiver.response(result);
		} catch (JSONException e) {
			Log.e(TAG, "result json error");
			e.printStackTrace();
		}
		dismissProcessDialog();
		receiver = null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		
	}

	public  void dismissProcessDialog() {
		if (processDialog != null && processDialog.isShowing()) {
			processDialog.dismiss();
		}
	}
	
	public void showRoundProcessDialog(Context mContext) {
		OnKeyListener keyListener = new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface dialog, int keyCode,
					KeyEvent event) {
				if (keyCode == KeyEvent.KEYCODE_HOME
						|| keyCode == KeyEvent.KEYCODE_SEARCH) {
					return true;
				}
				return false;
			}
		};

		processDialog = new android.app.AlertDialog.Builder(mContext).create();
		processDialog.setOnKeyListener(keyListener);
		processDialog.setCanceledOnTouchOutside(false);
		processDialog.show();
		processDialog.setContentView(R.layout.loading_process_dialog_anim);
		// 注意此处要放在show之后 否则会报异常
	}
}
