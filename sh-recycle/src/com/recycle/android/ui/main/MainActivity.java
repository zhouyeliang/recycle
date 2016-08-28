package com.recycle.android.ui.main;


import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.recycle.android.ui.base.DrawerLayoutActivity;
import com.recycle.android.ui.life.LifeActivity;
import com.recycle.android.ui.recycle.RecycleActivity;
import com.recycle.recycle.R;
import come.recycle.android.util.Util;

public class MainActivity extends DrawerLayoutActivity implements OnClickListener{
	
	private LinearLayout lifeButton;
	
	private LinearLayout recycleButton;
	
	private ImageView userIcon;
	
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}	
	
	@Override
	public void initView() {
		lifeButton = (LinearLayout)findViewById(R.id.life_button);
		lifeButton.setOnClickListener(this);
		recycleButton = (LinearLayout)findViewById(R.id.recycle_button);
		recycleButton.setOnClickListener(this);
		userIcon = (ImageView)findViewById(R.id.image_user);
		userIcon.setOnClickListener(this);
	}

	@Override
	public void initData() {
		
	}

	@Override
	public int getLayout() {
		return R.layout.activity_main;
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.life_button:
			Util.jumpTo(context,LifeActivity.class);
			break;
		case R.id.recycle_button:
			Util.jumpTo(context,RecycleActivity.class);
			break;
		case R.id.image_user:
			mDrawerLayout.openDrawer(GravityCompat.START);
			break;
			
		}
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			if ((System.currentTimeMillis() - exitTime) > 2000) {
				Toast.makeText(getApplicationContext(), "再按一次退出程序",
						Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
			} else {
				finish();
				System.exit(0);
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	

}
