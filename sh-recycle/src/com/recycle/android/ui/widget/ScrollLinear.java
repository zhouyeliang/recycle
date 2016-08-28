package com.recycle.android.ui.widget;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.recycle.android.ui.recycle.RecycleDetailActivity;
import com.recycle.recycle.R;

import come.recycle.android.util.ConstantVar;
import come.recycle.android.util.LogUtil;
import come.recycle.android.util.Util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ScrollLinear extends LinearLayout {
	
	private Context mContext;
	 
	 private final String Tag = "ScrollLinear";
	
	 private TextView mTitle;  
	 
     private HorizontalScrollView mHorizontalScrollView; 
     
     private LinearLayout linear;

	public ScrollLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	public ScrollLinear(Context context,String title,JSONArray data){
		super(context);
		mContext = context;
		 this.setOrientation(VERTICAL);  
         // Here we build the child views in code. They could also have  
         // been specified in an XML file.  
         mTitle = new TextView(context);  
         mTitle.setText(title);  
         LinearLayout.LayoutParams titleParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
         titleParams.setMargins(Util.dip2px(context,8), Util.dip2px(context,22), 0, Util.dip2px(context,8));
         mTitle.setLayoutParams(titleParams);
         addView(mTitle);  
         mHorizontalScrollView = new HorizontalScrollView(context);
         linear = new LinearLayout(context);
         LinearLayout.LayoutParams scrollViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, Util.dip2px(context,95));
         mHorizontalScrollView.setLayoutParams(scrollViewParams);
         mHorizontalScrollView.setHorizontalScrollBarEnabled(false);
         linear.setLayoutParams(scrollViewParams);
         int len = data.length();
         for(int i = 0; i<len; i++){
        	 String imgUrl = null;
        	 String name = null;
			try {
				imgUrl = data.getJSONObject(i).optString(ConstantVar.IMAGE_URL_KEY);
				name = data.getJSONObject(i).optString(ConstantVar.RECY_NAME_KEY);
				LogUtil.e("RecycleFragment",imgUrl);
				LogUtil.e("RecycleFragment",name);
			} catch (JSONException e) {
				e.printStackTrace();
			}
        	 
        	 LayoutInflater inflate =  LayoutInflater.from(context);
        	 View rootView = inflate.inflate(R.layout.recycle_item2, null);
        	 LinearLayout.LayoutParams rootViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        	 rootViewParams.setMargins(Util.dip2px(context,8),0,0, 0);
        	 rootView.setLayoutParams(rootViewParams);
        	 TextView textName = (TextView)rootView.findViewById(R.id.recycle_text);
        	 textName.setText(name);
        	 ImageView img =(ImageView)rootView.findViewById(R.id.recycle_image);
        	 ImageLoader.getInstance().displayImage(imgUrl, img);
        	 linear.addView(rootView);
        	 rootView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Util.jumpTo(mContext, RecycleDetailActivity.class);
				}
			});
         }
         mHorizontalScrollView.addView(linear);
         addView(mHorizontalScrollView); 
	}
	
	public void setOnItemClickListener(){
		
	}
}
