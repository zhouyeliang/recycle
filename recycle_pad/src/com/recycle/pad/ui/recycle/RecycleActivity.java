package com.recycle.pad.ui.recycle;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recycle_pad.R;
import com.recycle.pad.manager.UserDataManager;
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
	
	private TextView nameTv;
	
	private TextView addressTv;
	
	private TextView jfTv;
	
	private TextView listTv;
	
	private TextView totalTv;
	
	private UserDataManager userDataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		userDataManager = UserDataManager.getInstance();
		setContentView(R.layout.activity_recycle_order);
		Bundle bundle = getIntent().getExtras();
		OrderCategory paper = new OrderCategory();
		paper.setCategoryName("废纸");
		paper.setCategoryjf("1.0");
		paper.setCategoryNum(bundle.getString("paper"));
		OrderCategory plasic = new OrderCategory();
		plasic.setCategoryName("废塑料");
		plasic.setCategoryjf("1.0");
		plasic.setCategoryNum(bundle.getString("plasic"));
		OrderCategory app = new OrderCategory();
		app.setCategoryName("废金属");
		app.setCategoryjf("1.0");
		app.setCategoryNum(bundle.getString("app"));
		OrderCategory glass = new OrderCategory();
		glass.setCategoryName("废玻璃");
		glass.setCategoryjf("1.0");
		glass.setCategoryNum(bundle.getString("glass"));
		OrderCategory phone = new OrderCategory();
		phone.setCategoryName("废电子产品");
		phone.setCategoryjf("10.0");
		phone.setCategoryNum(bundle.getString("phone"));
		list = new ArrayList<OrderCategory>();
		list.add(paper);
		list.add(plasic);
		list.add(app);
		list.add(glass);
		list.add(phone);
		initView();
	}

	private void initView() {
		totalTv = (TextView) findViewById(R.id.total);
		listTv = (TextView) findViewById(R.id.name);
		nameTv = (TextView) findViewById(R.id.name_tv);
		addressTv = (TextView) findViewById(R.id.address_tv);
		jfTv = (TextView) findViewById(R.id.jf_tv);
		adapter = new OrderCategoryAdapter(this, list);
		listView = (ListView)findViewById(R.id.listview);
		listView.setAdapter(adapter);
		/*logoutBtn = (Button) findViewById(R.id.logout_btn);
		logoutBtn.setOnClickListener(this);*/
		lifeBtn = (Button) findViewById(R.id.life_btn);
		lifeBtn.setOnClickListener(this);
		okBtn = (Button) findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(this);
		nameTv.setText("姓名：" + userDataManager.getUser().getUserName());
		addressTv.setText("住址：" + userDataManager.getUser().getAddress().getAddress());
		jfTv.setText("当前账户积分余额：" + userDataManager.getUser().getJfRemain() + "分");
		listTv.setText(userDataManager.getUser().getUserName() + " 本次您出售如下产品。系统已将本次投放的积分计入您的账户");
		float totalJf = 0f;
		for(int i =0;i<list.size();++i){
			totalJf = totalJf + Float.parseFloat(list.get(i).getCategoryjf()) * Float.parseFloat(list.get(i).getCategoryNum());
		}
		totalTv.setText(new DecimalFormat("#.##").format(totalJf) + "积分");
	}
	 

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.life_btn:
			Util.jumpTo(this, LifeActivity.class);
			break;
		case R.id.ok_btn:
			this.finish();
			break;
		default:
			break;
		}

	}

}
