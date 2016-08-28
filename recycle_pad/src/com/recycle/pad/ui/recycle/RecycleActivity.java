package com.recycle.pad.ui.recycle;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.example.recycle_pad.R;
import com.recycle.pad.model.OrderCategory;
import com.recycle.pad.ui.adapter.OrderCategoryAdapter;
import com.recycle.pad.ui.life.LifeActivity;
import come.recycle.pad.util.Util;

public class RecycleActivity extends Activity implements OnClickListener {
	
	private Button logoutBtn;
	
	private Button lifeBtn;
	
	private Button okBtn;
	
	private ListView listView;
	
	private ArrayList<OrderCategory> list;
	
	private OrderCategoryAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_recycle_order);
		Bundle bundle = getIntent().getExtras();
		OrderCategory paper = new OrderCategory();
		paper.setCategoryName("废纸");
		paper.setCategoryjf("2.4");
		paper.setCategoryNum(bundle.getInt("paper") + "");
		OrderCategory plasic = new OrderCategory();
		plasic.setCategoryName("废塑料");
		plasic.setCategoryjf("2.5");
		plasic.setCategoryNum(bundle.getInt("plasic") + "");
		OrderCategory app = new OrderCategory();
		app.setCategoryName("废金属");
		app.setCategoryjf("2.6");
		app.setCategoryNum(bundle.getInt("app") + "");
		OrderCategory glass = new OrderCategory();
		glass.setCategoryName("废玻璃");
		glass.setCategoryjf("2.7");
		glass.setCategoryNum(bundle.getInt("glass") + "");
		OrderCategory phone = new OrderCategory();
		phone.setCategoryName("废电子产品");
		phone.setCategoryjf("2.8");
		phone.setCategoryNum(bundle.getInt("phone") + "");
		list = new ArrayList<OrderCategory>();
		list.add(paper);
		list.add(plasic);
		list.add(app);
		list.add(glass);
		list.add(phone);
		initView();
	}

	private void initView() {
		adapter = new OrderCategoryAdapter(this, list);
		listView = (ListView)findViewById(R.id.listview);
		listView.setAdapter(adapter);
		logoutBtn = (Button) findViewById(R.id.logout_btn);
		logoutBtn.setOnClickListener(this);
		lifeBtn = (Button) findViewById(R.id.life_btn);
		lifeBtn.setOnClickListener(this);
		okBtn = (Button) findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(this);
	}
	 

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logout_btn:
			break;
		case R.id.life_btn:
			Util.jumpTo(this, LifeActivity.class);
			break;
		case R.id.ok_btn:
			Util.jumpTo(this, RecycleSuccessActivity.class);
			break;
		default:
			break;
		}

	}

}
