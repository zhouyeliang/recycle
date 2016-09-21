package com.recycle.android.ui.my;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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
import com.recycle.android.model.User;
import com.recycle.android.net.action.ActAbsSthRcv;
import com.recycle.android.net.action.ActionBuilder;
import com.recycle.android.net.action.member.AddressReq;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.ConstantVar;

public class AddAddressActivity extends BaseActivity implements OnClickListener{

	
	private Context context;
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private EditText contactEt;
	
	private EditText phoneEt;
	
	private EditText addressEt;
	
	private User user;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_address);
		context = this;
		initData();
		initView();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		user = UserDataManager.getInstance().getUser();
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
		contactEt = (EditText)findViewById(R.id.contact_et);
		phoneEt = (EditText)findViewById(R.id.phone_et);
		addressEt = (EditText)findViewById(R.id.address_et);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			this.finish();
			break;
		case R.id.title_right:
			if(contactEt.getText().toString().trim().isEmpty()){
				Toast.makeText(context, "联系人不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if(phoneEt.getText().toString().trim().isEmpty()){
				Toast.makeText(context, "手机号不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if(addressEt.getText().toString().trim().isEmpty()){
				Toast.makeText(context, "地址不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			AddressReq req = new AddressReq(user.getUserId(), contactEt.getText().toString(), phoneEt.getText().toString(), addressEt.getText().toString());
			ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
				@Override
				public boolean response(String result) throws JSONException {
					JSONObject obj = new JSONObject(result);
					if(obj.optString("resultSign").equals("true")){
						Toast.makeText(context, obj.optString("message"), Toast.LENGTH_SHORT).show();
						user.getAddress().setAddress( addressEt.getText().toString());
						user.getAddress().setName(contactEt.getText().toString());
						user.getAddress().setTelephone( phoneEt.getText().toString());
					}else{
						Toast.makeText(context, obj.optString("message"), Toast.LENGTH_SHORT).show();
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
