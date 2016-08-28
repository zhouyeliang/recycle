package com.recycle.android.ui.my;

import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;

public class AddAddressActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_address);
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
		titleMid.setText("添加配送地址");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setText("保存");
		titleRight.setOnClickListener(this);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			this.finish();
			break;
		case R.id.title_right:
			this.finish();
			break;
		}
		
	}
}
