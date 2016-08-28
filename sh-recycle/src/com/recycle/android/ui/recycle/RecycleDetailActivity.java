package com.recycle.android.ui.recycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycle.android.manager.OrderDataManager;
import com.recycle.android.model.CategoryInfo;
import com.recycle.android.model.OrderCategory;
import com.recycle.android.ui.life.LifeOrderActivity;
import com.recycle.android.ui.order.OrderActivity;
import com.recycle.recycle.R;
import come.recycle.android.util.BaseActivity;
import come.recycle.android.util.ImageLoaderHelper;
import come.recycle.android.util.Util;

public class RecycleDetailActivity extends BaseActivity implements OnClickListener{
	
	private Button addButton;
	
	private Button subButton;
	
	private Button orderButton;
	
	private TextView title;
	
	private ImageView img;
	
	private ImageView back;
	
	private TextView detailJfnum;
	
	private TextView detailDec;
	
	private TextView numText;

	/**
	 * 数据相关
	 */
	private float jfNum;
	
	private float totalJfNum;
	
	private int unitNum = 1;
	
	private String titleName ="";
	
	private String imgUrl;
	
	private String description = "";
	
	private String categoryId;
	
	/**
	 * 下单的类型 1--回收   2--生活
	 */
	private String orderType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.recycle_detail);
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void initView(){
		addButton = (Button)findViewById(R.id.add_button);
		subButton = (Button)findViewById(R.id.sub_button);
		orderButton = (Button)findViewById(R.id.order_button);
		
		title = (TextView)findViewById(R.id.detail_title);
		title.setText(titleName);
		
		img = (ImageView)findViewById(R.id.detail_image);
		ImageLoaderHelper.displayImageFromURL(imgUrl, img);
		
		back = (ImageView)findViewById(R.id.detail_back);
		
		detailJfnum = (TextView)findViewById(R.id.detail_num);
		detailJfnum.setText(jfNum+"积分");
		
		detailDec = (TextView)findViewById(R.id.detail_dec);
		if(description==null||description.equals("")){
			detailDec.setText("暂无描述");
		}else{
			detailDec.setText(description);
		}
		
		
		numText = (TextView)findViewById(R.id.the_num_text);
		
		addButton.setOnClickListener(this);
		subButton.setOnClickListener(this);
		orderButton.setOnClickListener(this);
		back.setOnClickListener(this);
	}
	
	private void initData(){
		Intent intent = getIntent();
		if(intent != null){
			Bundle bundle = intent.getExtras();
			categoryId= bundle.getString(CategoryInfo.KEY_CATEGORY_ID);
			titleName = bundle.getString(CategoryInfo.KEY_CATEGORY_NAME);
			imgUrl = bundle.getString(CategoryInfo.KEY_IMAGE_URL);
			jfNum = Float.parseFloat(bundle.getString(CategoryInfo.KEY_PRICE));
			description = bundle.getString(CategoryInfo.KEY_DESCRIPTION);
			orderType = bundle.getString("orderType");
		}
	}


	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.add_button:
			unitNum++;
			numText.setText("" + unitNum);
			break;
		case R.id.sub_button:
			if(unitNum > 0){
				unitNum--;
				numText.setText("" + unitNum);
			}
			break;
		case R.id.order_button:
			if(orderType.equals("1")){
				totalJfNum = jfNum * unitNum;
				String result = String.format("%.2f",totalJfNum);
				OrderCategory info = new OrderCategory();
				info.setCategoryId(categoryId);
				info.setCategoryjf(result);
				info.setCategoryName(titleName);
				info.setCategoryNum(unitNum+"");
				OrderDataManager.getInstance().getCurrentCategorys().add(info);
				Util.jumpTo(this, OrderActivity.class);
			}else{
				totalJfNum = jfNum * unitNum;
				String result = String.format("%.2f",totalJfNum);
				OrderCategory info = new OrderCategory();
				info.setCategoryId(categoryId);
				info.setCategoryjf(result);
				info.setCategoryName(titleName);
				info.setCategoryNum(unitNum+"");
				OrderDataManager.getInstance().getCurrentCategorys().add(info);
				Util.jumpTo(this, LifeOrderActivity.class);
			}
			
			finish();
			break;
		case R.id.detail_back:
			finish();
			break;
		}
		
	}

}
