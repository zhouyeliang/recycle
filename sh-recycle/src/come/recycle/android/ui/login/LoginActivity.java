package come.recycle.android.ui.login;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.recycle.android.manager.DataBooleanResponseListener;
import com.recycle.android.manager.UserDataManager;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.ConstantVar;
import come.recycle.android.util.Util;

public class LoginActivity extends BaseActivity implements OnClickListener{
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private Button loginButton;

	private EditText userNameView;
	
	private EditText passwordView;
	
	private Context context;
	
	/**
	 * 保存用户名密码
	 */
	private static SharedPreferences pref;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		context = this;
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initView(){
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleRight = (TextView)findViewById(R.id.title_right);
		titleRight.setOnClickListener(this);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		loginButton = (Button)findViewById(R.id.login_button);
		loginButton.setOnClickListener(this);
		userNameView = (EditText)findViewById(R.id.user_name);
		passwordView = (EditText)findViewById(R.id.user_password);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			finish();
			break;
		case R.id.title_right:
			Util.jumpTo(this, RegisterActivity.class);
			break;
			
		case R.id.login_button:
			String userName = userNameView.getText().toString();
			String password = passwordView.getText().toString();
			if(userName.equals("")){
				Toast.makeText(context, "用户名不能为空", 2).show();
				break;
			}
			if(password.equals("")){
				Toast.makeText(context, "密码不能为空", 2).show();
				break;
			}
			UserDataManager.getInstance().goLogin(context, userName, password, new DataBooleanResponseListener() {
				@Override
				public void response(boolean isSuccess) {
					if(isSuccess){
						((Activity)context).finish();
					}
				}
			});
			break;
			
		}
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		pref = this.getSharedPreferences(ConstantVar.PREF_USER_NAME,
				Context.MODE_PRIVATE);
		String username = pref.getString(ConstantVar.PREF_USERNAME_KEY, "");
		userNameView.setText(username);
	}

}
