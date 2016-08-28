package com.recycle.android.ui.widget;

import java.util.ArrayList;

import org.json.JSONArray;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recycle.android.model.CategoryInfo;
import com.recycle.android.ui.adapter.RecycleGridAdapter;
import com.recycle.android.ui.recycle.RecycleDetailActivity;

import come.recycle.android.util.ConstantVar;
import come.recycle.android.util.Util;

public class GridLinear extends LinearLayout {

	private Context mContext;
	/**
	 * 每一个girdview组件的title
	 */
	private TextView mTitle;

	private GridView grid;
	
	private ArrayList<CategoryInfo> lstImageItem;

	public GridLinear(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public GridLinear(Context context, String title, JSONArray data) {
		super(context);
		mContext = context;
		this.setOrientation(VERTICAL);
		mTitle = new TextView(context);
		mTitle.setText(title);
		LinearLayout.LayoutParams titleParams = new LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		titleParams.setMargins(Util.dip2px(context, 8),
				Util.dip2px(context, 22), 0, Util.dip2px(context, 8));
		mTitle.setLayoutParams(titleParams);
		addView(mTitle);
		grid = new GridView(mContext);
		lstImageItem = new ArrayList<CategoryInfo>();
		// 生成适配器
		RecycleGridAdapter saImageItems = new RecycleGridAdapter(mContext,
				lstImageItem);
		// 添加并且显示
		grid.setAdapter(saImageItems);
		grid.setNumColumns(3);
		// 添加消息处理
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int positon,
					long arg3) {
				lstImageItem.get(positon);
				Bundle bundle = new Bundle();
				bundle.putString(ConstantVar.RECY_NAME_KEY, lstImageItem.get(positon).getCategoryId());
				Util.jumpTo(mContext, RecycleDetailActivity.class,bundle);
			}
		});
		grid.setLayoutParams(new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
		addView(grid);
	}

	public void setOnItemClickListener() {

	}
}
