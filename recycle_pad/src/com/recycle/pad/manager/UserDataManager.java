package com.recycle.pad.manager;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.pad.model.User;
import com.recycle.pad.net.action.ActAbsSthRcv;
import com.recycle.pad.net.action.ActionBuilder;
import com.recycle.pad.net.action.member.LoginReq;
import com.recycle.pad.net.action.member.QrLoginReq;
import com.recycle.pad.ui.inter.DataResponseListener;

import come.recycle.pad.util.ConstantVar;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.Toast;

public class UserDataManager {
	private static UserDataManager userDataManager = new UserDataManager();
	private User user;
	
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

	public void goLogin(final Context context,final String userName,final String password,final DataResponseListener<Boolean> listener){
		LoginReq loginReq = new LoginReq(userName, password);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				JSONObject obj = new JSONObject(result);
				if(obj.getString("state").equals("10001")){
					user = new User(obj);
					listener.response(true);
				}else{
					Toast.makeText(context, obj.getString("message"), 1).show();
					listener.response(false);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(loginReq, rcv);
	}
	
	public void goQrLogin(final Context context,final String qrCode,final DataResponseListener<Boolean> listener){
		QrLoginReq loginReq = new QrLoginReq(qrCode);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				JSONObject obj = new JSONObject(result);
				if(obj.getString("state").equals("10001")){
					user = new User(obj);
					UserDataManager.getInstance().setUser(user);
					listener.response(true);
				}else{
					Toast.makeText(context, obj.getString("message"), 1).show();
					listener.response(false);
				}
				return false;
			}
		};
		ActionBuilder.getInstance().request(loginReq, rcv);
	}
	
	public void logout(){
		UserDataManager.getInstance().setUser(null);
	}
	
	
	public boolean isLogin(){
		return user!=null;
	}
}
