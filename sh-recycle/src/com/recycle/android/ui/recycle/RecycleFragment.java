package com.recycle.android.ui.recycle;

import java.util.ArrayList;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.recycle.android.manager.CategoryManager;
import com.recycle.android.manager.DataModelResponseListener;
import com.recycle.android.manager.DataResponseListener;
import com.recycle.android.model.CategoryInfo;
import com.recycle.android.model.base.JackJson;
import com.recycle.android.ui.adapter.RecycleGridAdapter;
import com.recycle.recycle.R;
import come.recycle.android.util.Util;

public class RecycleFragment extends Fragment {
	/**
	 * 回收小类页的总布局
	 */
	private LinearLayout recycleLayout;
	
	private GridView gridView;
	
	private RecycleGridAdapter adapter;

	private Context mContext;
	
	private String categoryId;
	
	private CategoryManager categoryManager;
	
	private ArrayList<CategoryInfo> categoryInfos;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.recycle_fragment, container,
				false);
		mContext = getActivity();
		categoryManager = CategoryManager.getInstance();
		categoryId = getArguments().getString(CategoryInfo.KEY_PARENT_ID);
		if(categoryManager.getMap().get(categoryId)!=null){
			categoryInfos = categoryManager.getMap().get(categoryId);
			adapter = new RecycleGridAdapter(mContext,categoryInfos);
			adapter.notifyDataSetChanged();
		}else{
			categoryInfos = categoryManager.getCategory(mContext, categoryId,new DataResponseListener() {
				@Override
				public void response() {
					adapter.notifyDataSetChanged();
				}
			});
			adapter = new RecycleGridAdapter(mContext,categoryInfos);
		}
		
		gridView = (GridView)rootView.findViewById(R.id.grid);
		adapter = new RecycleGridAdapter(mContext,categoryInfos);
		gridView.setAdapter(adapter);
		gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,final int positon,
					long arg3) {
				categoryManager.getCategoryDetail(mContext, categoryInfos.get(positon).getCategoryId(), new DataModelResponseListener() {
					
					@Override
					public void response(JackJson obj) {
						CategoryInfo info = (CategoryInfo)obj;
						Bundle bundle = new Bundle();
						bundle.putString(CategoryInfo.KEY_CATEGORY_NAME, info.getCategoryName());
						bundle.putString(CategoryInfo.KEY_CATEGORY_ID, info.getCategoryId());
						bundle.putString(CategoryInfo.KEY_IMAGE_URL, info.getImgUrl());
						bundle.putString(CategoryInfo.KEY_PRICE, info.getPrice());
						bundle.putString(CategoryInfo.KEY_DESCRIPTION, info.getDescription());
						bundle.putString("orderType", "1");
						Util.jumpTo(mContext, RecycleDetailActivity.class,bundle);
					}
				});
				
			}
		});
		recycleLayout = (LinearLayout) rootView.findViewById(R.id.layout_recycle);
		return rootView;
	}

	public static Fragment newInstance(Bundle bundle) {
		RecycleFragment fragment = new RecycleFragment();
		fragment.setArguments(bundle);
		return fragment;
	}
}
