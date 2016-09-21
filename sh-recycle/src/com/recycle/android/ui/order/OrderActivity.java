package com.recycle.android.ui.order;


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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.recycle.android.manager.DataModelResponseListener;
import com.recycle.android.manager.OrderDataManager;
import com.recycle.android.manager.UserDataManager;
import com.recycle.android.model.AddressInfo;
import com.recycle.android.model.Order;
import com.recycle.android.model.OrderCategory;
import com.recycle.android.model.base.JackJson;
import com.recycle.android.ui.adapter.OrderCategoryAdapter;
import com.recycle.android.ui.my.AddAddressActivity;
import com.recycle.android.ui.recycle.RecycleActivity;
import com.recycle.android.ui.widget.AlertDialog;
import com.recycle.android.ui.widget.TimeDialog;
import com.recycle.android.ui.widget.TimeDialog.OnConfirmListener;
import com.recycle.recycle.R;

import come.recycle.android.ui.login.LoginActivity;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.Util;

public class OrderActivity extends BaseActivity implements OnClickListener{
	
	/*--------------------------------------------控件相关定义--------------------------------------------*/
	/**
	 * 立即下单按钮
	 */
	private Button orderButton;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private RelativeLayout orderSubRelative;
	
	private RelativeLayout orderDeliveryRelative;
	
	private ImageView deliveryIcon;
	
	private ImageView subscribeIcon;
	
	private TextView timeStart;
	
	private RelativeLayout personalInfoRaletive;
	
	private ListView listView;
	
	private ArrayList<OrderCategory> list;
	
	private OrderCategoryAdapter adapter;
	
	/**
	 * 继续添加按钮
	 */
	private TextView addTextView;
	
	private TextView personNameView;
	
	private TextView telephoneView;
	
	private TextView addressView;
	
	private TextView personalInfoTitle;
	
	/*------------------------------------------------------数据相关---------------------------------------------------------*/
	
	private Context mContext;
	
	private UserDataManager userDataManager;
	
	private OrderDataManager orderDataManager;
	
	private String endTime = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.order);
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
		
		orderSubRelative = (RelativeLayout)findViewById(R.id.orer_subscribe_relative);
		orderDeliveryRelative = (RelativeLayout)findViewById(R.id.orer_user_delivery_relative);
		
		subscribeIcon = (ImageView)findViewById(R.id.subscribe_icon);
		deliveryIcon = (ImageView)findViewById(R.id.delivery_icon);
		
		timeStart = (TextView)findViewById(R.id.time_start);
		timeStart.setOnClickListener(this);
		
		personalInfoRaletive = (RelativeLayout)findViewById(R.id.personal_info);
		personalInfoRaletive.setOnClickListener(this);
		personalInfoTitle = (TextView)findViewById(R.id.personal_info_title);
		personNameView = (TextView)findViewById(R.id.order_username);
		telephoneView = (TextView)findViewById(R.id.order_usertel);
		addressView =  (TextView)findViewById(R.id.orer_useradd);
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
		if(userDataManager.isLogin()){
			personalInfoTitle.setVisibility(View.VISIBLE);
			personalInfoRaletive.setVisibility(View.VISIBLE);
			AddressInfo info = userDataManager.getUser().getAddress();
			if(!info.getName().isEmpty()){
				personNameView.setText(info.getName());
				telephoneView.setText(info.getTelephone());
				addressView.setText(info.getAddress());
			}else{
				personNameView.setText("暂无");
				telephoneView.setText("暂无");
				addressView.setText("暂无");
			}
			
			
		}else{
			personalInfoTitle.setVisibility(View.GONE);
			personalInfoRaletive.setVisibility(View.GONE);
		}
	}
	@Override
	public void onClick(View arg0) {
		Calendar c = Calendar.getInstance();
		
		final int norYear = c.get(Calendar.YEAR);
		switch(arg0.getId()){
		case R.id.order_button:
			if(userDataManager.isLogin()){
				StringBuffer categoryIds = new StringBuffer();
				StringBuffer nums = new StringBuffer();
				int length = list.size();
				for(int i=0;i<length;i++){
					OrderCategory info = list.get(i);
					if(i==length-1){
						categoryIds.append(info.getCategoryId());
						nums.append(info.getCategoryNum());
					}else{
						categoryIds.append(info.getCategoryId()).append("|");
						nums.append(info.getCategoryNum()).append("|");
					}
				}
				if(endTime.equals("")){
					Toast.makeText(mContext, "请选择配送时间", 2).show();
					return;
				}
				orderDataManager.goOrder(mContext, categoryIds.toString(), nums.toString(), userDataManager.getUser().getUserId(),personNameView.getText().toString(),
						addressView.getText().toString(),telephoneView.getText().toString(),endTime,
						new DataModelResponseListener() {
					@Override
					public void response(JackJson model) {
						if(model!=null){
							String orderId = ((Order)model).getOrderId();
							Bundle bundle = new Bundle();
							bundle.putString(Order.KEY_ID, orderId);
							Util.jumpTo(mContext, OrderDetailActivity.class,bundle);
							list.clear();
							((Activity)mContext).finish();
						}else{
							new AlertDialog(mContext).builder().setMsg("下订单失败")
								.setPositiveButton("确定", null).show();
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
		case R.id.time_start:
			new TimeDialog(mContext).builder().setOnConfirmListener(new OnConfirmListener() {

				@Override
				public void onConfirm(int month, int day, int hour, int minute) {
					timeStart.setText(month+"/"+day+" "+hour+":"+minute+" ");
					endTime = norYear +"-"+month+"-"+day + " " + hour+":"+minute;
				}
				
			}).show();
			break;
		case R.id.personal_info:
			Bundle bundle = new Bundle();
			Util.jumpTo(this, AddAddressActivity.class);
			break;
		case R.id.go_add:
			Util.jumpTo(mContext, RecycleActivity.class);
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
