package com.recycle.android.manager;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

import com.recycle.android.model.User;
import com.recycle.android.net.action.ActAbsSthRcv;
import com.recycle.android.net.action.ActionBuilder;
import com.recycle.android.net.action.member.LoginReq;
import com.recycle.android.net.action.member.RegisterReq;
import come.recycle.android.util.ConstantVar;

public class UserDataManager {
	private static UserDataManager userDataManager = new UserDataManager();
	private User user;
	
	/**
	 * 保存用户名密码
	 */
	private static SharedPreferences pref;
	
	private UserDataManager(){
		
	}
	public static UserDataManager getInstance(){
		return userDataManager;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	public void cleanData(Context context){
		user = null;
		//重置密码
		SharedPreferences prefTemp = context.getSharedPreferences(ConstantVar.PREF_USER_NAME,
				Context.MODE_PRIVATE);
		prefTemp.edit().putString(ConstantVar.PREF_PASSWORD_KEY, "")
		        .commit();
		
	}

	public void goLogin(final Context context,final String userName,final String password,final DataBooleanResponseListener listener){
		LoginReq loginReq = new LoginReq(userName, password);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				JSONObject obj = new JSONObject(result);
				if(obj.getString("state").equals("10001")){
					user = new User(obj);
					pref = context.getSharedPreferences(ConstantVar.PREF_USER_NAME,
							Context.MODE_PRIVATE);
					pref.edit().putString(ConstantVar.PREF_USERNAME_KEY, userName)
							.commit();// 成功则保存用户名
					pref.edit().putString(ConstantVar.PREF_PASSWORD_KEY, password)
							.commit();
					listener.response(true);
				}else{
					Toast.makeText(context, obj.getString("message"), 2).show();
					listener.response(false);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(loginReq, rcv);
	}
	
	
	public void goRegister(final Context context,final String userName,final String password,final DataBooleanResponseListener listener){
		RegisterReq registerReq = new RegisterReq(userName, password);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				JSONObject obj = new JSONObject(result);
				if(obj.getString("state").equals("10001")){
					SharedPreferences sharedPreferences = context
							.getSharedPreferences(ConstantVar.PREF_USER_NAME,
									Context.MODE_PRIVATE);
					Editor editor = sharedPreferences.edit();
					editor.putString(ConstantVar.PREF_USERNAME_KEY, userName);
					editor.commit();
					Toast.makeText(context, "注册成功", 2).show();
					listener.response(true);
				}else{
					Toast.makeText(context, obj.getString("message"), 2).show();
					listener.response(false);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(registerReq, rcv);
	}
	
	
	public boolean isLogin(){
		return user!=null;
	}
}
