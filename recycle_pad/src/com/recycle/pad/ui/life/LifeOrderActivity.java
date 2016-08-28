package com.recycle.pad.ui.life;


import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycle_pad.R;
import com.recycle.pad.base.BaseActivity;
import com.recycle.pad.manager.OrderDataManager;
import com.recycle.pad.manager.UserDataManager;
import com.recycle.pad.model.Order;
import com.recycle.pad.model.OrderCategory;
import com.recycle.pad.model.base.JackJson;
import com.recycle.pad.ui.adapter.OrderCategoryAdapter;
import com.recycle.pad.ui.inter.DataResponseListener;

import come.recycle.pad.util.Util;

/**
 * 生活券购买确认页�?
 * @author zhouyeliang
 *
 */
public class LifeOrderActivity extends BaseActivity implements OnClickListener{
	
	/*--------------------------------------------控件相关定义--------------------------------------------*/
	/**
	 * 立即下单按钮
	 */
	private TextView numTv;
	
	private TextView totalJfTv;
	
	private Button orderButton;
	
	private ListView listView;
	
	private ArrayList<OrderCategory> list;
	
	private OrderCategoryAdapter adapter;
	
	/**
	 * 继续添加按钮
	 */
	private Button addBtn;
	
	/*------------------------------------------------------数据相关---------------------------------------------------------*/
	
	private Context mContext;
	
	private UserDataManager userDataManager;
	
	private OrderDataManager orderDataManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  
		setContentView(R.layout.activity_life_order);
		mContext = this;
		userDataManager = UserDataManager.getInstance();
		orderDataManager = OrderDataManager.getInstance();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initView(){
		numTv = (TextView)findViewById(R.id.orer_num);
		totalJfTv = (TextView)findViewById(R.id.orer_jf);
		orderButton = (Button)findViewById(R.id.order_button);
		orderButton.setOnClickListener(this);
		
		listView = (ListView)findViewById(R.id.order_cate_list);
		list = OrderDataManager.getInstance().getCurrentCategorys();
		adapter = new OrderCategoryAdapter(mContext, list);
		listView.setAdapter(adapter);
		numTv.setText("您已选购:"+list.size()+"件商品");
		float totalJf = 0.0f;
		for(int i=0;i<list.size();i++){
			OrderCategory info = list.get(i);
			totalJf = totalJf + Float.parseFloat(info.getCategoryjf())*Float.parseFloat(info.getCategoryNum());
		}
		totalJfTv.setText("总计积分:"+totalJf+"积分");
		addBtn = (Button)findViewById(R.id.go_add);
		addBtn.setOnClickListener(this);
	}


	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	public void onClick(View arg0) {
		Calendar c = Calendar.getInstance();
		switch(arg0.getId()){
		case R.id.order_button:
			StringBuffer categoryIds = new StringBuffer();
			StringBuffer nums = new StringBuffer();
			int length = list.size();
			float totalJf = 0.0f;
			for(int i=0;i<length;i++){
				OrderCategory info = list.get(i);
				if(i==length-1){
					categoryIds.append(info.getCategoryId());
					nums.append(info.getCategoryNum());
				}else{
					categoryIds.append(info.getCategoryId()).append("|");
					nums.append(info.getCategoryNum()).append("|");
				}
				totalJf = totalJf + Float.parseFloat(info.getCategoryjf());
			}
			orderDataManager.goLifeOrder(mContext, categoryIds.toString(), nums.toString(),UserDataManager.getInstance()
					.getUser().getUserId(),
					new DataResponseListener<JackJson>() {
				@Override
				public void response(JackJson model) {
					if(model!=null){
						Order info = (Order)model;
						Toast.makeText(mContext, "购买成功", Toast.LENGTH_SHORT).show();
						//list.clear();
						((Activity)mContext).finish();
						Bundle bundle = new Bundle();
						bundle.putString("orderNo", info.getOrderNo());
						//bundle.putString(key, value);
						Util.jumpTo(mContext, LifePrintActivity.class,bundle);
					}else{
						Toast.makeText(mContext, "下订单失败", Toast.LENGTH_SHORT).show();
						//Util.jumpTo(mContext, LifePrintActivity.class);
					}
				}
			});	
			
			break;
		case R.id.title_leftimg:
			finish();	
			list.clear();
			break;
		case R.id.go_add:
			Util.jumpTo(mContext, LifeActivity.class);
			break;
		
		}
	}
	
	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		//照相返回
		case 1:
			break;
		default:
			break;
		}
	}
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		list.clear();
	}

}
