package com.recycle.android.ui.my;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycle.android.manager.UserDataManager;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;

public class MyWalletActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private TextView jfRemainView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_wallet);
		context = this;
		initView();
		initData();
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
		titleMid.setText("我的钱包");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setText("充值");
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		jfRemainView =  (TextView)findViewById(R.id.jf_remain);
		if(UserDataManager.getInstance().getUser()!=null){
			jfRemainView.setText(UserDataManager.getInstance().getUser().getJfRemain());
		}
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
