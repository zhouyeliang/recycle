package come.recycle.android.util;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.recycle.recycle.R;


import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.KeyEvent;



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
    
    /** 
     * 生成二维码 
     * @param string 二维码中包含的文本信息 
     * @param mBitmap logo图片 
     * @param format  编码格式 
     * @return Bitmap 位图 
     * @throws WriterException 
     */  
    public static Bitmap createCode(String string,Bitmap mBitmap, BarcodeFormat format)  
            throws WriterException {  
        Matrix m = new Matrix();  
        float sx = (float) 2 * IMAGE_HALFWIDTH / mBitmap.getWidth();  
        float sy = (float) 2 * IMAGE_HALFWIDTH  
                / mBitmap.getHeight();  
        m.setScale(sx, sy);//设置缩放信息  
        //将logo图片按martix设置的信息缩放  
        mBitmap = Bitmap.createBitmap(mBitmap, 0, 0,  
                mBitmap.getWidth(), mBitmap.getHeight(), m, false);  
        MultiFormatWriter writer = new MultiFormatWriter();  
        Hashtable<EncodeHintType, String> hst = new Hashtable<EncodeHintType, String>();  
        hst.put(EncodeHintType.CHARACTER_SET, "utf-8");//设置字符编码  
        BitMatrix matrix = writer.encode(string, format, 400, 400, hst);//生成二维码矩阵信息  
        int width = matrix.getWidth();//矩阵高度  
        int height = matrix.getHeight();//矩阵宽度  
        int halfW = width / 2;  
        int halfH = height / 2;  
        int[] pixels = new int[width * height];//定义数组长度为矩阵高度*矩阵宽度，用于记录矩阵中像素信息  
        for (int y = 0; y < height; y++) {//从行开始迭代矩阵  
            for (int x = 0; x < width; x++) {//迭代列  
                if (x > halfW - IMAGE_HALFWIDTH && x < halfW + IMAGE_HALFWIDTH  
                        && y > halfH - IMAGE_HALFWIDTH  
                        && y < halfH + IMAGE_HALFWIDTH) {//该位置用于存放图片信息  
//记录图片每个像素信息  
                    pixels[y * width + x] = mBitmap.getPixel(x - halfW  
                            + IMAGE_HALFWIDTH, y - halfH + IMAGE_HALFWIDTH);                } else {  
                    if (matrix.get(x, y)) {//如果有黑块点，记录信息  
                        pixels[y * width + x] = 0xff000000;//记录黑块信息  
                    }  
                }  
  
            }  
        }  
        Bitmap bitmap = Bitmap.createBitmap(width, height,  
                Bitmap.Config.ARGB_8888);  
        // 通过像素数组生成bitmap  
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);  
        return bitmap;  
    }
    
    /** 
     * 生成一个二维码图像 
     *  
     * @param url 
     *            传入的字符串，通常是一个URL 
     * @param widthAndHeight 
     *           图像的宽高 
     * @return 
     */  
    public static Bitmap generateBitmap(String content,int width, int height) {  
        QRCodeWriter qrCodeWriter = new QRCodeWriter();  
        Hashtable<EncodeHintType, String> hints = new Hashtable<>();  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");  
        try {  
            BitMatrix encode = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, width, height, hints);  
            int[] pixels = new int[width * height];  
            for (int i = 0; i < height; i++) {  
                for (int j = 0; j < width; j++) {  
                    if (encode.get(j, i)) {  
                        pixels[i * width + j] = 0x00000000;  
                    } else {  
                        pixels[i * width + j] = 0xffffffff;  
                    }  
                }  
            }  
            return Bitmap.createBitmap(pixels, 0, width, width, height, Bitmap.Config.RGB_565);  
        } catch (WriterException e) {  
            e.printStackTrace();  
        }  
        return null;  
    } 
    

}
