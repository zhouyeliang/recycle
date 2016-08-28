/**
 * 
 */
package com.recycle.android.application;

import java.util.ArrayList;

import come.recycle.android.util.ImageLoaderHelper;
import come.recycle.android.util.Util;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;

/**
 * @author zhouyeliang
 * 
 */
public class MyApplication extends Application {
 
	private static MyApplication yApp;

	public static ArrayList<Activity> activitys = new ArrayList<Activity>();

	private void initScreenData(){
		DisplayMetrics dm = getResources().getDisplayMetrics();
		Util.SCREEN_WIDTH = dm.widthPixels;
		Util.SCREEN_HEIGHT= dm.heightPixels;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		yApp = this;
		initScreenData();
		ImageLoaderHelper.initImageLoader(this);
	}

	public static MyApplication app() {
		return yApp;
	}

	public static void addActivity(Activity activity) {
		if (activitys != null && !activitys.contains(activity)) {
			activitys.add(activity);
		}
	}

	public static void removeActivity(Activity activity) {
		if (activitys != null && activitys.contains(activity)) {
			activitys.remove(activity);
		}
	}

	public static Activity getActivity(Activity activity) {
		return null;
	}

}
