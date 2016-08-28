/**
 * 
 */
package com.recycle.pad.main;

import java.io.File;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Application;
import android.util.DisplayMetrics;
import android_serialport_api.SerialPort;

import come.recycle.pad.util.ImageLoaderHelper;
import come.recycle.pad.util.Util;

/**
 * @author zhouyeliang
 * 
 */
public class MyApplication extends Application {

	private static MyApplication yApp;

	public static ArrayList<Activity> activitys = new ArrayList<Activity>();
	
	private SerialPort mSerialPort3 = null;

	private void initScreenData() {
		DisplayMetrics dm = getResources().getDisplayMetrics();
		Util.SCREEN_WIDTH = dm.widthPixels;
		Util.SCREEN_HEIGHT = dm.heightPixels;
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
	
	// 打印 机
	public SerialPort getSerialPort() throws SecurityException, IOException,
				InvalidParameterException {
			if (mSerialPort3 == null) {
				mSerialPort3 = new SerialPort(new File("/dev/ttyS2"), 9600, 0);
			}
			return mSerialPort3;
		}

		//关闭串口
		public void closeSerialPort() {
			if (mSerialPort3 != null) {
				mSerialPort3.close();
				mSerialPort3 = null;
			}
		}

}
