package com.recycle.android.ui.recycle;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.recycle.android.manager.CategoryManager;
import com.recycle.android.manager.DataResponseListener;
import com.recycle.android.model.CategoryInfo;
import com.recycle.android.ui.adapter.RecycleTypeAdapter;
import com.recycle.android.ui.base.DrawerLayoutActivity;
import com.recycle.recycle.R;

public class RecycleActivity extends DrawerLayoutActivity implements OnClickListener{
	
	private TextView titleMid;
	
	private ImageView loginIcon;
	
	private ImageView back;
	
	private ListView recycleListView;
	
	private RecycleTypeAdapter typeAdapter;
	
	private FragmentManager fm;  
	
	/**
	 * 缓存页面的所有碎片
	 */
	private HashMap<Integer,RecycleFragment> fragmentList;
	
	/**
	 * 回收类别数组
	 */
	private ArrayList<String> listData;
	
	/**
	 * 回收详细数据
	 */
	private ArrayList<CategoryInfo> recycleData;
	
	private CategoryManager categoryManager;
	
	private ArrayList<CategoryInfo> categoryInfos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void initView() {
		back = (ImageView)findViewById(R.id.title_leftimg);
		back.setOnClickListener(this);
		titleMid = (TextView)findViewById(R.id.title_midtext);
		titleMid.setText("分类");
		loginIcon = (ImageView)findViewById(R.id.title_right);
		loginIcon.setOnClickListener(this);
		recycleListView = (ListView)findViewById(R.id.type_recycle);
		typeAdapter.setSelectItem(0);
		recycleListView.setAdapter(typeAdapter);
		recycleListView.setDividerHeight(1);
		recycleListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				//替换fragment部分
				typeAdapter.setSelectItem(position);
				FragmentTransaction transaction = fm.beginTransaction();
				RecycleFragment itemFragment = fragmentList.get(position);
				if (itemFragment == null)  
	            {  
					Bundle bundle = new Bundle();
					bundle.putString(CategoryInfo.KEY_PARENT_ID, categoryInfos.get(position).getCategoryId());
					itemFragment = (RecycleFragment)RecycleFragment.newInstance(bundle);
					fragmentList.put(position, itemFragment);
	            }  
	            transaction.replace(R.id.recycle_content, itemFragment); 
	            transaction.commit();
	            typeAdapter.notifyDataSetChanged();
			}
		});
	}

	@Override
	public void initData() {
		fm = getFragmentManager();
		listData = new ArrayList<String>();
		typeAdapter = new RecycleTypeAdapter(context, listData);
		fragmentList = new HashMap<Integer, RecycleFragment>();
		categoryManager = CategoryManager.getInstance();
		if(categoryManager.getMap().get("0")!=null){
			categoryInfos = categoryManager.getMap().get("0");
			for(CategoryInfo info:categoryInfos){
				listData.add(info.getCategoryName());
			}
			typeAdapter.notifyDataSetChanged();
			//一进去显示第一面的回收数据
			FragmentTransaction transaction = fm.beginTransaction();
			RecycleFragment itemFragment = fragmentList.get(0);
			if (itemFragment == null)  
	        {  
				Bundle bundle = new Bundle();
				bundle.putString(CategoryInfo.KEY_PARENT_ID, categoryInfos.get(0).getCategoryId());
				itemFragment = (RecycleFragment)RecycleFragment.newInstance(bundle);
				fragmentList.put(0, itemFragment);
	        }  
	        transaction.replace(R.id.recycle_content, itemFragment); 
	        transaction.commit(); 
		}else{
			categoryManager.getCategory(context, "0",new DataResponseListener() {
				@Override
				public void response() {
					categoryInfos = categoryManager.getMap().get("0");
					for(CategoryInfo info:categoryInfos){
						listData.add(info.getCategoryName());
					}
					typeAdapter.notifyDataSetChanged();
					//一进去显示第一面的回收数据
					FragmentTransaction transaction = fm.beginTransaction();
					RecycleFragment itemFragment = fragmentList.get(0);
					if (itemFragment == null)  
			        {  
						Bundle bundle = new Bundle();
						bundle.putString(CategoryInfo.KEY_PARENT_ID, categoryInfos.get(0).getCategoryId());
						itemFragment = (RecycleFragment)RecycleFragment.newInstance(bundle);
						fragmentList.put(0, itemFragment);
			        }  
			        transaction.replace(R.id.recycle_content, itemFragment); 
			        transaction.commit(); 
				}
			});
		}
		
	}

	@Override
	public int getLayout() {
		return R.layout.recycle_category;
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
