package com.recycle.android.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recycle.android.ui.widget.ActionSheetDialog;
import com.recycle.android.ui.widget.ActionSheetDialog.OnSheetItemClickListener;
import com.recycle.android.ui.widget.ActionSheetDialog.SheetItemColor;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.DataCleanHelper;

public class SettingActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private RelativeLayout cleanCacheLayout;
	
	private TextView cacheSize;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		context = this;
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initData(){
		
	}
	
	private void initView(){
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("设置");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setVisibility(View.GONE);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		cleanCacheLayout = (RelativeLayout)findViewById(R.id.clean_cache);
		cleanCacheLayout.setOnClickListener(this);
		cacheSize = (TextView)findViewById(R.id.cache_size);
		try {
			cacheSize.setText(DataCleanHelper.getTotalCacheSize(context));
		} catch (Exception e) {
			Log.e("SettingFragment", "cacheSize exception");
		}
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			this.finish();
			break;
		case R.id.clean_cache:
			new ActionSheetDialog(context)
					.builder()
					.addSheetItem("确定", SheetItemColor.Red,
							new OnSheetItemClickListener() {
								@Override
								public void onClick(int which) {
									DataCleanHelper.clearAllCache(context);
									cacheSize.setText("0.0B");
								}
							}).setTitle("确定清空本地缓存数据？").show();
			break;	
			
		}
		
	}
}
