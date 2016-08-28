package com.recycle.android.ui.order;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recycle.android.manager.DataBooleanResponseListener;
import com.recycle.android.manager.DataModelResponseListener;
import com.recycle.android.manager.OrderDataManager;
import com.recycle.android.model.AddressInfo;
import com.recycle.android.model.Order;
import com.recycle.android.model.OrderCategory;
import com.recycle.android.model.base.JackJson;
import com.recycle.android.ui.adapter.OrderSimpleCategoryAdapter;
import com.recycle.android.ui.widget.AlertDialog;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;

public class OrderDetailActivity extends BaseActivity implements OnClickListener{
	
	private Context mContext;
	
	/**
	 * 标题栏相关
	 */
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	/**
	 * 内容相关
	 */
	private TextView titleView;
	
	private ImageView checkStartView;
	
	private ImageView checkWaitView;
	
	private ImageView checkSuccessView;
	
	private TextView personalNameView;
	
	private TextView personalTelephoneView;
	
	private TextView personalAddressView;
	
	private TextView timeView;
	
	private TextView orderNoView;
	
	private ListView listView;
	
	private TextView cancelButton;
	
	private OrderSimpleCategoryAdapter adapter;
	
	private ArrayList<OrderCategory> list;
	
	private String orderId;
	
	private Order orderInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.check);
		mContext = this;
		cancelButton = (TextView)findViewById(R.id.cancel_buttom);
		cancelButton.setOnClickListener(this);
		cancelButton.setClickable(false);
		Bundle bundle = getIntent().getExtras();
		orderId = bundle.getString("order_id");
		if(OrderDataManager.getInstance().findOrderDetailById(orderId)!=null){
			orderInfo = OrderDataManager.getInstance().findOrderDetailById(orderId);
			initView();
		}else{
			OrderDataManager.getInstance().getOrderDetial(mContext, orderId, new DataModelResponseListener() {
				
				@Override
				public void response(JackJson obj) {
					orderInfo = (Order)obj;
					initView();
				}
			});
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initView(){
		
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("待接单");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setVisibility(View.GONE);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		titleView = (TextView)findViewById(R.id.check_title);
		titleView.setVisibility(View.GONE);
		
		checkStartView = (ImageView)findViewById(R.id.check_start_img);
		checkWaitView = (ImageView)findViewById(R.id.check_wait_img);
		checkSuccessView = (ImageView)findViewById(R.id.check_succ_img);
		
		personalNameView = (TextView)findViewById(R.id.check_name);
		personalTelephoneView = (TextView)findViewById(R.id.check_tel);
		personalAddressView = (TextView)findViewById(R.id.check_add);
		AddressInfo info = orderInfo.getAddressInfo();
		personalNameView.setText(info.getName());
		personalTelephoneView.setText(info.getTelephone());
		personalAddressView.setText(info.getAddress());
		
		timeView = (TextView)findViewById(R.id.check_deliver);
		if(orderInfo.getPickTime().isEmpty()){
			timeView.setText("预约上门取件");
		}else{
			timeView.setText("预约上门取件("+orderInfo.getPickTime()+")");
		}
		
		orderNoView = (TextView)findViewById(R.id.check_sn);
		orderNoView.setText(orderInfo.getOrderNo());
		listView = (ListView)findViewById(R.id.list_category);
		list = orderInfo.getOrderCategorys();
		adapter = new OrderSimpleCategoryAdapter(mContext, list);
		listView.setAdapter(adapter);
		cancelButton.setClickable(true);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			finish();
			break;
		case R.id.cancel_buttom:
			if(orderInfo.getOrderState().equals("已生成")||orderInfo.getOrderState().equals("已确认")){
				OrderDataManager.getInstance().cancelOrder(mContext, orderInfo.getOrderId(), new DataBooleanResponseListener() {
					@Override
					public void response(boolean isSuccess) {
						if(isSuccess){
							new AlertDialog(mContext).builder().setMsg("取消订单成功").setPositiveButton("确认", null).show();
							((Activity)mContext).finish();
						}else{
							new AlertDialog(mContext).builder().setMsg("取消订单失败").setPositiveButton("确认", null).show();
						}
						
					}
				});
			}
			break;
		}
		
	}

}
