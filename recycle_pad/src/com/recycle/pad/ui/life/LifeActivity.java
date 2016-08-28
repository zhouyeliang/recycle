package com.recycle.pad.ui.life;


import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.recycle_pad.R;
import com.recycle.pad.base.BaseActivity;
import com.recycle.pad.manager.CategoryManager;
import com.recycle.pad.model.CategoryInfo;
import com.recycle.pad.model.LifeGoods;
import com.recycle.pad.model.base.JackJson;
import com.recycle.pad.ui.adapter.LifeAdapter;
import com.recycle.pad.ui.adapter.RecycleTypeAdapter;
import com.recycle.pad.ui.inter.DataResponseListener;
import come.recycle.pad.util.Util;

/**
 * 生活的列表页
 * @author Administrator
 *
 */

public class LifeActivity extends BaseActivity implements OnClickListener{
	
	private Context context;
	
	private ListView listView;
	
	private GridView grid;
	
	private LifeAdapter lifeAdapter;
	
	private ArrayList<LifeGoods> lifeList = new ArrayList<LifeGoods>();
	
	private ImageView titleLeft;
	
	private TextView titleMid;
	
	private RecycleTypeAdapter typeAdapter;
	
	/**
	 * 生活类别数组
	 */
	private ArrayList<String> listData;
	
	private ArrayList<CategoryInfo> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_life);
		context=this;
		initData();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void initView() {
		listView = (ListView)findViewById(R.id.type_life);
		typeAdapter.setSelectItem(0);
		listView.setAdapter(typeAdapter);
		listView.setDividerHeight(1);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//替换fragment部分
				String typeId = list.get(position).getCategoryId();
				if(CategoryManager.getInstance().getLifeMap().get(typeId)!=null){
					lifeList = CategoryManager.getInstance().getLifeMap().get(typeId);
					lifeAdapter.setDataList(lifeList);
					lifeAdapter.notifyDataSetChanged();
				}else{
					lifeList = CategoryManager.getInstance().getLifeList(context,list.get(position).getCategoryId(), new DataResponseListener<JackJson>(){
						@Override
						public void response(JackJson obj) {
							lifeAdapter.setDataList(lifeList);
							lifeAdapter.notifyDataSetChanged();
						}
					});
				}
				typeAdapter.setSelectItem(position);
	            typeAdapter.notifyDataSetChanged();
			}
		});
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		grid = (GridView)findViewById(R.id.life_grid);
		grid.setAdapter(lifeAdapter);
		grid.setNumColumns(3);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
					long arg3) {
				LifeGoods info = lifeList.get(positon);
				Bundle bundle = new Bundle();
				bundle.putString(CategoryInfo.KEY_CATEGORY_NAME, info.getGoodsTitle());
				bundle.putString(CategoryInfo.KEY_CATEGORY_ID, info.getGoodsId());
				bundle.putString(CategoryInfo.KEY_IMAGE_URL, info.getImgUrl());
				bundle.putString(CategoryInfo.KEY_PRICE, info.getGoodsjf());
				bundle.putString(CategoryInfo.KEY_DESCRIPTION, info.getGoodsDes());
				bundle.putString("orderType", "2");
				Util.jumpTo(context, CategoryDetailActivity.class,bundle);
			}
		});
		
	}

	public void initData() {
		lifeList = new ArrayList<LifeGoods>();
		listData = new ArrayList<String>();
		list = CategoryManager.getInstance().getLifeTitleList(context, new DataResponseListener<JackJson>() {
			
			@Override
			public void response(JackJson obj) {
				for(CategoryInfo info:list){
					listData.add(info.getCategoryName());
				}
				lifeList = CategoryManager.getInstance().getLifeList(context,list.get(0).getCategoryId(), new DataResponseListener<JackJson>(){
					@Override
					public void response(JackJson obj) {
						lifeAdapter.setDataList(lifeList);
						lifeAdapter.notifyDataSetChanged();
					}});
				typeAdapter.notifyDataSetChanged();
			}
		});
		typeAdapter = new RecycleTypeAdapter(context, listData);
		
		
		lifeAdapter = new LifeAdapter(context, lifeList);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}


	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.title_leftimg:
			finish();
			break;
		case R.id.title_right:
			break;
		}
		
	}

	

}
