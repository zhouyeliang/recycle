package com.recycle.android.ui.life;


import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recycle.android.manager.CategoryManager;
import com.recycle.android.manager.DataModelResponseListener;
import com.recycle.android.model.CategoryInfo;
import com.recycle.android.model.LifeGoods;
import com.recycle.android.model.base.JackJson;
import com.recycle.android.ui.adapter.LifeAdapter;
import com.recycle.android.ui.adapter.RecycleTypeAdapter;
import com.recycle.android.ui.base.DrawerLayoutActivity;
import com.recycle.android.ui.recycle.RecycleDetailActivity;
import com.recycle.recycle.R;
import come.recycle.android.util.Util;

/**
 * 生活的列表页
 * @author Administrator
 *
 */

public class LifeActivity extends DrawerLayoutActivity implements OnClickListener{
	
	private ListView listView;
	
	private GridView grid;
	
	private LifeAdapter lifeAdapter;
	
	private ArrayList<LifeGoods> lifeList = new ArrayList<LifeGoods>();
	
	private ImageView loginIcon;
	
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
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	@Override
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
					lifeList = CategoryManager.getInstance().getLifeList(context,list.get(position).getCategoryId(), new DataModelResponseListener(){
						@Override
						public void response(JackJson obj) {
							lifeAdapter.setDataList(lifeList);
							lifeAdapter.notifyDataSetChanged();
						}});
				}
				typeAdapter.setSelectItem(position);
	            typeAdapter.notifyDataSetChanged();
			}
		});
		
		loginIcon = (ImageView)findViewById(R.id.title_right);
		loginIcon.setOnClickListener(this);
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleLeft = (ImageView)findViewById(R.id.title_leftimg);
		titleLeft.setOnClickListener(this);
		grid = (GridView)findViewById(R.id.life_grid);
		grid.setAdapter(lifeAdapter);
		grid.setNumColumns(2);
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
				Util.jumpTo(context, RecycleDetailActivity.class,bundle);
			}
		});
		
	}

	@Override
	public void initData() {
		lifeList = new ArrayList<LifeGoods>();
		listData = new ArrayList<String>();
		list = CategoryManager.getInstance().getLifeTitleList(context, new DataModelResponseListener() {
			
			@Override
			public void response(JackJson obj) {
				for(CategoryInfo info:list){
					listData.add(info.getCategoryName());
				}
				lifeList = CategoryManager.getInstance().getLifeList(context,list.get(0).getCategoryId(), new DataModelResponseListener(){
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
	public int getLayout() {
		return R.layout.activity_life;
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
			mDrawerLayout.openDrawer(GravityCompat.START);
			break;

		}
		
	}

	

}
