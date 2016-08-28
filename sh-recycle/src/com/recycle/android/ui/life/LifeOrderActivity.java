package com.recycle.android.ui.life;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.recycle.android.manager.DataModelResponseListener;
import com.recycle.android.manager.OrderDataManager;
import com.recycle.android.manager.UserDataManager;
import com.recycle.android.model.Order;
import com.recycle.android.model.OrderCategory;
import com.recycle.android.model.base.JackJson;
import com.recycle.android.ui.adapter.OrderCategoryAdapter;
import com.recycle.android.ui.widget.AlertDialog;
import com.recycle.recycle.R;

import come.recycle.android.ui.login.LoginActivity;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.Util;

/**
 * 生活券购买确认页面
 * @author zhouyeliang
 *
 */
public class LifeOrderActivity extends BaseActivity implements OnClickListener{
	
	/*--------------------------------------------控件相关定义--------------------------------------------*/
	/**
	 * 立即下单按钮
	 */
	private Button orderButton;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private ListView listView;
	
	private ArrayList<OrderCategory> list;
	
	private OrderCategoryAdapter adapter;
	
	/**
	 * 继续添加按钮
	 */
	private TextView addTextView;
	
	/*------------------------------------------------------数据相关---------------------------------------------------------*/
	
	private Context mContext;
	
	private UserDataManager userDataManager;
	
	private OrderDataManager orderDataManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_life_order);
		mContext = this;
		userDataManager = UserDataManager.getInstance();
		orderDataManager = OrderDataManager.getInstance();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initView(){
		orderButton = (Button)findViewById(R.id.order_button);
		orderButton.setOnClickListener(this);
		
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("订单");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setVisibility(View.GONE);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		listView = (ListView)findViewById(R.id.order_cate_list);
		list = OrderDataManager.getInstance().getCurrentCategorys();
		adapter = new OrderCategoryAdapter(mContext, list);
		listView.setAdapter(adapter);
		
		addTextView = (TextView)findViewById(R.id.go_add);
		addTextView.setOnClickListener(this);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onClick(View arg0) {
		Calendar c = Calendar.getInstance();
		switch(arg0.getId()){
		case R.id.order_button:
			if(userDataManager.isLogin()){
				StringBuffer categoryIds = new StringBuffer();
				StringBuffer nums = new StringBuffer();
				int length = list.size();
				float totalJf = 0.0f;
				for(int i=0;i<length;i++){
					OrderCategory info = list.get(i);
					if(i==length-1){
						categoryIds.append(info.getCategoryId());
						nums.append(info.getCategoryNum());
					}else{
						categoryIds.append(info.getCategoryId()).append("|");
						nums.append(info.getCategoryNum()).append("|");
					}
					totalJf = totalJf + Float.parseFloat(info.getCategoryjf());
				}
				/*if(totalJf>Float.parseFloat(userDataManager.getUser().getJfRemain())){
					Toast.makeText(mContext, "积分余额不足", Toast.LENGTH_SHORT).show();
					return;
				}*/
				orderDataManager.goLifeOrder(mContext, categoryIds.toString(), nums.toString(), userDataManager.getUser().getUserId(),
						new DataModelResponseListener() {
					@Override
					public void response(JackJson model) {
						if(model!=null){
							Toast.makeText(mContext, "购买成功", Toast.LENGTH_SHORT).show();
							list.clear();
							((Activity)mContext).finish();
						}else{
							Toast.makeText(mContext, "下订单失败", Toast.LENGTH_SHORT).show();
							/*new AlertDialog(mContext).builder().setMsg("下订单失败")
								.setPositiveButton("确定", null).show();*/
						}
						
					}
				});	
			}else{
				Util.jumpTo(this, LoginActivity.class);
			}
			break;
		case R.id.title_leftimg:
			finish();	
			list.clear();
			break;
		case R.id.camera_image:
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, 1);
			break;
		case R.id.go_add:
			Util.jumpTo(mContext, LifeActivity.class);
			break;
		
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		//照相返回
		case 1:
			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		list.clear();
	}

}
