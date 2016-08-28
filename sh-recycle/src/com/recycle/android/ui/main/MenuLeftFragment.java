package com.recycle.android.ui.main;

import com.recycle.android.manager.UserDataManager;
import com.recycle.android.ui.my.MyAddressActivity;
import com.recycle.android.ui.my.MyCouponActivity;
import com.recycle.android.ui.my.MyOpinionActivity;
import com.recycle.android.ui.my.MyOrderActivity;
import com.recycle.android.ui.my.MyQrCodeActivity;
import com.recycle.android.ui.my.MyWalletActivity;
import com.recycle.android.ui.my.SettingActivity;
import com.recycle.recycle.R;

import come.recycle.android.ui.login.LoginActivity;
import come.recycle.android.util.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MenuLeftFragment extends Fragment implements OnClickListener {
	private View mView;

	private RelativeLayout myOrder;

	private RelativeLayout myWallet;
	
	private RelativeLayout myQrCode;

	private RelativeLayout myAddress;

	private RelativeLayout myCoupon;

	private RelativeLayout myOpinion;

	private RelativeLayout mySetting;

	private TextView loginState;
	
	private ImageView userIcon;
	
	private TextView userName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.menu_fragment, container, false);
		initView();
		return mView;
	}
	
	@Override
	public void onResume() {
		initLoginState();
		super.onResume();
	}

	private void initView() {
		myOrder = (RelativeLayout) mView.findViewById(R.id.my_order_layout);
		myWallet = (RelativeLayout) mView.findViewById(R.id.my_wallet_layout);
		myQrCode = (RelativeLayout) mView.findViewById(R.id.my_qrcode_layout);
		myAddress = (RelativeLayout) mView.findViewById(R.id.my_address_layout);
		myCoupon = (RelativeLayout) mView.findViewById(R.id.my_coupon_layout);
		myOpinion = (RelativeLayout) mView.findViewById(R.id.my_opinion_layout);
		mySetting = (RelativeLayout) mView.findViewById(R.id.my_setting_layout);
		loginState = (TextView)mView.findViewById(R.id.login_state);
		userIcon = (ImageView)mView.findViewById(R.id.user_icon);
		userName = (TextView)mView.findViewById(R.id.user_name);
		initLoginState();
		myOrder.setOnClickListener(this);
		myWallet.setOnClickListener(this);
		myQrCode.setOnClickListener(this);
		myAddress.setOnClickListener(this);
		myCoupon.setOnClickListener(this);
		myOpinion.setOnClickListener(this);
		mySetting.setOnClickListener(this);
		loginState.setOnClickListener(this);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.my_order_layout:
			if(UserDataManager.getInstance().isLogin()){
				Util.jumpTo(getActivity(), MyOrderActivity.class);
			}else{
				Util.jumpTo(getActivity(), LoginActivity.class);
			}
			//Util.jumpTo(getActivity(), MyOrderActivity.class);
			break;
		case R.id.my_wallet_layout:
			if(UserDataManager.getInstance().isLogin()){
				Util.jumpTo(getActivity(), MyWalletActivity.class);
			}else{
				Util.jumpTo(getActivity(), LoginActivity.class);
			}
			break;
		case R.id.my_qrcode_layout:
			if(UserDataManager.getInstance().isLogin()){
				Util.jumpTo(getActivity(), MyQrCodeActivity.class);
			}else{
				Util.jumpTo(getActivity(), LoginActivity.class);
			}
			break;
		case R.id.my_address_layout:
			if(UserDataManager.getInstance().isLogin()){
				Util.jumpTo(getActivity(), MyAddressActivity.class);
			}else{
				Util.jumpTo(getActivity(), LoginActivity.class);
			}
			break;
		case R.id.my_coupon_layout:
			if(UserDataManager.getInstance().isLogin()){
				Util.jumpTo(getActivity(), MyCouponActivity.class);
			}else{
				Util.jumpTo(getActivity(), LoginActivity.class);
			}
			break;
		case R.id.my_opinion_layout:
			Util.jumpTo(getActivity(), MyOpinionActivity.class);
			break;
		case R.id.my_setting_layout:
			Util.jumpTo(getActivity(), SettingActivity.class);
			break;
			
		case R.id.login_state:
			if(UserDataManager.getInstance().getUser() == null){
				Util.jumpTo(getActivity(), LoginActivity.class);
			}else{
				UserDataManager.getInstance().cleanData(getActivity());
				initLoginState();
			}
			break;

		default:
			break;
		}

	}
	
	/**
	 * 初始化登录相关的一系列操作
	 */
	private void initLoginState(){
		if(UserDataManager.getInstance().getUser() == null){
			loginState.setText("登录");
			userName.setText("未登录");
		}else{
			loginState.setText("退出登录");
			userName.setText(UserDataManager.getInstance().getUser().getUserName());
		}
	}
}