package com.recycle.pad.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


/**
 * 所有Activity的抽象类
 * 
 * @author scc
 */
public abstract class BaseActivity extends FragmentActivity {
	private String versionName;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	}

	/**
	 * Activity激活时候的操作
	 */
	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onStart() {
		super.onStart();

	}

	/**
	 * Activity被其他Activity覆盖时执行的操作
	 */
	@Override
	protected void onStop() {
		super.onStop();
	}

}
