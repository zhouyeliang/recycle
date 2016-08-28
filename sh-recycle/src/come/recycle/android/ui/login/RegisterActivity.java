package come.recycle.android.ui.login;


import android.app.Activity;
import android.content.Context;
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
import come.recycle.android.util.ValidateUtils;

public class RegisterActivity extends BaseActivity implements OnClickListener{
	
	private TextView titleMid;
	
	private TextView titleRight;
	
	private ImageView titleLeft;
	
	private Button registerButton;

	private EditText userNameView;
	
	private EditText passwordView;
	
	private Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
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
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		registerButton = (Button)findViewById(R.id.register_button);
		registerButton.setOnClickListener(this);
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
			break;
			
		case R.id.register_button:
			String userName = userNameView.getText().toString();
			String password = passwordView.getText().toString();
			if(!checkUsername(userName,4)){
				Toast.makeText(context, "用户名格式错误", 2).show();
				break;
			}
			if(!checkPassword(password)){
				Toast.makeText(context, "密码格式错误", 2).show();
				break;
			}
			UserDataManager.getInstance().goRegister(context, userName, password, new DataBooleanResponseListener() {
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
	
	
	public boolean checkPassword(String password) {
		return ValidateUtils.validateRegx("^\\S{6,20}$", password);
	}
	
	public boolean checkUsername(String username,int min)  
    {  
        String regex = "[\\w\u4e00-\u9fa5]{"+min+",}(?<!_)";  
        return ValidateUtils.validateRegx(regex, username);
    } 

}
