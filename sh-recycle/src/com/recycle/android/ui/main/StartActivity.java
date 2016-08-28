package com.recycle.android.ui.main;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.recycle.android.manager.DataBooleanResponseListener;
import com.recycle.android.manager.UserDataManager;
import com.recycle.recycle.R;
import come.recycle.android.util.ConstantVar;
import come.recycle.android.util.Util;

/**
 * 欢迎页面
 * @author Administrator
 *
 */
public class StartActivity extends FragmentActivity {

	private ImageView imageView;

	private Context context;

	private boolean isFirst = true;
	
	private static SharedPreferences pref;
	
	private Bundle bundle;
	

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_start);
		this.context = this;
		Intent intent = getIntent();
		if(intent!=null){
			bundle = intent.getExtras();
		}
		imageView = (ImageView)findViewById(R.id.start_image);
		imageView.setScaleType(ScaleType.FIT_XY);
		//imageView.setImageResource(R.drawable.start);
		pref = context.getSharedPreferences(ConstantVar.PREF_USER_NAME,
				Context.MODE_PRIVATE);
		String userName = pref.getString(ConstantVar.PREF_USERNAME_KEY, "");
		String password = pref.getString(ConstantVar.PREF_PASSWORD_KEY, "");
		if(!userName.equals("")&&!password.equals("")){
			UserDataManager.getInstance().goLogin(context, userName, password, new DataBooleanResponseListener() {
				@Override
				public void response(boolean isSuccess) {
					Util.jumpTo(context, MainActivity.class);
				    ((FragmentActivity)context).finish();
				}
			});
		}else{
			Util.jumpTo(context, MainActivity.class);
			((FragmentActivity)context).finish();
		}
	}

	
}
