package com.recycle.android.ui.my;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.recycle.android.manager.UserDataManager;
import com.recycle.android.model.CategoryInfo;
import com.recycle.android.net.action.ActAbsSthRcv;
import com.recycle.android.net.action.ActionBuilder;
import com.recycle.android.net.action.category.GetCategoryDetailReq;
import com.recycle.android.net.action.my.OpinionReq;
import com.recycle.recycle.R;

import come.recycle.android.ui.login.LoginActivity;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.NetConstant;
import come.recycle.android.util.Util;

public class MyOpinionActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private EditText editView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_opinion);
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
		editView= (EditText)findViewById(R.id.opinion);
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("意见反馈");
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setText("发送");
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
			if(UserDataManager.getInstance().getUser()==null){
				Util.jumpTo(context, LoginActivity.class);
				return;
			}
			OpinionReq req = new OpinionReq(UserDataManager.getInstance().getUser().getUserId(),
					editView.getText().toString());
			ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
				@Override
				public boolean response(String result) throws JSONException {
					Log.e("", "OpinionReq " + result);
					JSONObject obj = new JSONObject(result);
					if(obj.optBoolean("resultSign")){
						Toast.makeText(context, "反馈成功", 1).show();
					}else{
						Toast.makeText(context, "反馈失败", 1).show();
					}
					return false;
				}
			};
			ActionBuilder.getInstance().request(req, rcv);
			this.finish();
			break;
		}
		
	}
}
