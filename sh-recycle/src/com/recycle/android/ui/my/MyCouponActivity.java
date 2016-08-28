package com.recycle.android.ui.my;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.recycle.android.manager.DataResponseListener;
import com.recycle.android.manager.OrderDataManager;
import com.recycle.android.manager.UserDataManager;
import com.recycle.android.model.CouponsInfo;
import com.recycle.android.model.Order;
import com.recycle.android.ui.adapter.CouponAdapter;
import com.recycle.android.ui.adapter.OrderAdapter;
import com.recycle.android.ui.order.OrderDetailActivity;
import com.recycle.android.ui.widget.FinishRefresh;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.Util;

public class MyCouponActivity extends BaseActivity implements OnClickListener{

	/**
	 * 订单listview
	 */
	private PullToRefreshListView orderList;
	
	private Context context;
	
	private ArrayList<CouponsInfo> dataList;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private CouponAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_order_activity);
		context = this;
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		adapter.notifyDataSetChanged();
	}
	
	private void initData(){
			dataList = OrderDataManager.getInstance().getLifeOrderList(context, UserDataManager.getInstance().getUser().getUserId(),true,
					new DataResponseListener() {
				@Override
				public void response() {
					adapter.notifyDataSetChanged();
				}
			});
		
	}
	
	private void initView(){
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("我的优惠券");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setVisibility(View.GONE);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		orderList = (PullToRefreshListView)findViewById(R.id.my_order_list);
		orderList.setMode(Mode.PULL_FROM_START);
		adapter = new CouponAdapter(context, dataList);
		orderList.setAdapter(adapter);
		orderList.setOnRefreshListener(new OnRefreshListener<ListView>() {

			@Override
			public void onRefresh(PullToRefreshBase<ListView> refreshView) {
				OrderDataManager.getInstance().getLifeOrderList(context, UserDataManager.getInstance().getUser().getUserId(),false,
						new DataResponseListener() {
					@Override
					public void response() {
						adapter.notifyDataSetChanged();
						new FinishRefresh(orderList);
					}
				});
			}
		});
	}
	
	

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			this.finish();
			break;
			
		}
		
	}
}
