package come.recycle.pad.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;



/**
 * 常用方法集合——工具类
 * 
 * @Modified By ZZ
 * @Modified Comments 补全工具类注释
 * @Modified Date 2013-09-26
 * 
 */
public final class Util {
	
	public static int SCREEN_WIDTH;
	
	public static int SCREEN_HEIGHT;
	
	private static final int IMAGE_HALFWIDTH = 40;
	
	public static int getScreenWidth(){
		return SCREEN_WIDTH;
	}
	
	public static int getScreenHeight(){
		return SCREEN_HEIGHT;
	}
	
	/**
	 * 判断是否是最上层Activity
	 * 
	 * @param activity
	 * @return boolean true为最顶层 false不是最顶层
	 * 
	 * 
	 */
	public static boolean isTopActivity(Activity activity) {
		// 针对长时间后台，导致静态变量丢失问题，对activity为空做判断 modify by scc 20130927
		if (activity == null) {
			return false;
		}
		String packageName = "com.telek.smarthome.android";

		ActivityManager activityManager = (ActivityManager) activity
				.getSystemService(Context.ACTIVITY_SERVICE);
		PowerManager pm = (PowerManager) activity
				.getSystemService(Context.POWER_SERVICE);
		boolean screen = pm.isScreenOn();
		List<RunningTaskInfo> tasksInfo = activityManager.getRunningTasks(1);

		if (tasksInfo.size() > 0
				&& packageName.equals(tasksInfo.get(0).topActivity
						.getPackageName()) && screen) {
			// 应用程序位于堆栈的顶层
			return true;
		}
		return false;
	}
	
	/**
	 * 无参数跳转
	 * 
	 * @param context 上下文
	 * @param c 跳转到的类
	 * 
	 * @return 无返回
	 */
	public static void jumpTo(Context context, Class<?> c) {
		Intent intent = new Intent();
		intent.setClass(context, c);
		context.startActivity(intent);
	}
	
	/**
	 * 带参数跳转
	 * 
	 * @param context 上下文
	 * @param c 跳转到的类
	 * @param bundle 参数
	 * @return 无返回
	 */
	public static void jumpTo(Context context, Class<?> c,Bundle bundle){
		Intent intent = new Intent();
		intent.putExtras(bundle);
		intent.setClass(context, c);
		context.startActivity(intent);
	}
	
	
	/** 
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素) 
     */  
    public static int dip2px(Context context, float dpValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (dpValue * scale + 0.5f);  
    }  
  
    /** 
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp 
     */  
    public static int px2dip(Context context, float pxValue) {  
        final float scale = context.getResources().getDisplayMetrics().density;  
        return (int) (pxValue / scale + 0.5f);  
    } 
    
    public static HashMap<String, String> JSONToHashMap(JSONObject object)  
    {  
        HashMap<String, String> data = null;  
        if(object != null){
        	data = new HashMap<String, String>(); 
        	Iterator it = object.keys();  
            // 遍历jsonObject数据，添加到Map对象  
            while (it.hasNext())  
            {  
                String key = String.valueOf(it.next());  
                String value;
    			try {
    				value = (String) object.get(key);
    				data.put(key, value);
    			} catch (JSONException e) {
    				e.printStackTrace();
    			}    
            }  
        }
        return data; 
    }
    

    

}
