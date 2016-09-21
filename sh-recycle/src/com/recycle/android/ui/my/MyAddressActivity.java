package com.recycle.android.ui.my;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.recycle.android.manager.UserDataManager;
import com.recycle.android.model.AddressInfo;
import com.recycle.android.ui.adapter.AddressAdapter;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.Util;

public class MyAddressActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private RelativeLayout addAddress;
	
	private ListView listView;
	
	private ArrayList<AddressInfo> list;
	
	private AddressAdapter adapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_address);
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
		list = new ArrayList<AddressInfo>();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		if(UserDataManager.getInstance().isLogin()){
			list.clear();
			if(!UserDataManager.getInstance().getUser().getAddress().getName().isEmpty()){
				list.add(UserDataManager.getInstance().getUser().getAddress());
			}
			adapter.notifyDataSetChanged();
		}
	}
	
	private void initView(){
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("配送地址");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setVisibility(View.GONE);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		addAddress = (RelativeLayout)findViewById(R.id.add_address);
		addAddress.setOnClickListener(this);
		listView = (ListView)findViewById(R.id.address_list);
		adapter = new AddressAdapter(context, list);
		listView.setAdapter(adapter);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			this.finish();
			break;
			
		case R.id.add_address:
			Util.jumpTo(context, AddAddressActivity.class);
			break;
			
		}
		
	}
}
