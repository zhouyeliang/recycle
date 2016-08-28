package com.recycle.android.ui.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.nineoldandroids.view.ViewHelper;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;

public abstract class DrawerLayoutActivity extends BaseActivity{
	
	protected Context context;
	
	/**
	 * 侧滑菜单
	 */
	protected DrawerLayout mDrawerLayout;
	
	private long exitTime = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater();
		ViewGroup rootView = (ViewGroup)inflater.inflate(R.layout.activity_drawer, null,
				false);
		View activityView = inflater.inflate(getLayout(), null,
				false);
		rootView.addView(activityView, 0);
		setContentView(rootView);
		context = this;
		initData();
		mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerLayout);
		mDrawerLayout.setDrawerListener(new DrawerListener()  
        {  
            @Override  
            public void onDrawerStateChanged(int newState)  
            {  
            }  
  
            @Override  
            public void onDrawerSlide(View drawerView, float slideOffset)  
            {  
                View mContent = mDrawerLayout.getChildAt(0);  
                View mMenu = drawerView;  
                float scale = 1 - slideOffset;  
                float rightScale = 0.8f + scale * 0.2f;  
  
                if (drawerView.getTag().equals("LEFT"))  
                {  
  
                    float leftScale = 1 - 0.3f * scale;  
  
                    ViewHelper.setScaleX(mMenu, leftScale);  
                    ViewHelper.setScaleY(mMenu, leftScale);  
                    ViewHelper.setAlpha(mMenu, 0.6f + 0.4f * (1 - scale));  
                    ViewHelper.setTranslationX(mContent,  
                            mMenu.getMeasuredWidth() * (1 - scale));  
                    ViewHelper.setPivotX(mContent, 0);  
                    ViewHelper.setPivotY(mContent,  
                            mContent.getMeasuredHeight() / 2);  
                    mContent.invalidate();  
                    ViewHelper.setScaleX(mContent, rightScale);  
                    ViewHelper.setScaleY(mContent, rightScale);  
                } else  
                {  
                    ViewHelper.setTranslationX(mContent,  
                            -mMenu.getMeasuredWidth() * slideOffset);  
                    ViewHelper.setPivotX(mContent, mContent.getMeasuredWidth());  
                    ViewHelper.setPivotY(mContent,  
                            mContent.getMeasuredHeight() / 2);  
                    mContent.invalidate();  
                    ViewHelper.setScaleX(mContent, rightScale);  
                    ViewHelper.setScaleY(mContent, rightScale);  
                }  
  
            }  
  
            @Override  
            public void onDrawerOpened(View drawerView)  
            {  
            }  
  
            @Override  
            public void onDrawerClosed(View drawerView)  
            {
            }  
        });
		initView();
	}
	
	public  abstract void initView();
	
	public  abstract void initData();
	
	public  abstract int getLayout();
	
	@Override
	protected void onResume() {
		super.onResume();
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
