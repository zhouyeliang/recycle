package com.recycle.pad.ui.recycle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

import com.example.recycle_pad.R;
import com.recycle.pad.ui.life.LifeActivity;
import come.recycle.pad.util.Util;

public class RecycleSuccessActivity extends Activity implements OnClickListener {
	
	private Button logoutBtn;
	
	private Button lifeBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_recycle_order_success);
		initView();
	}

	private void initView() {
		logoutBtn = (Button) findViewById(R.id.logout_btn);
		logoutBtn.setOnClickListener(this);
		lifeBtn = (Button) findViewById(R.id.life_btn);
		lifeBtn.setOnClickListener(this);
	}

	/*@Override
	protected void onDataReceived(byte[] buffer, int size) {

	}*/

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout_btn:
			break;
		case R.id.life_btn:
			Util.jumpTo(this, LifeActivity.class);
			break;
		case R.id.ok_btn:
			Util.jumpTo(this, RecycleSuccessActivity.class);
			break;
		default:
			break;
		}
	}

}
