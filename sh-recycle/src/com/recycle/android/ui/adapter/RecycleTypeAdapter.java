package com.recycle.android.ui.adapter;

import java.util.ArrayList;

import com.recycle.recycle.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RecycleTypeAdapter extends BaseAdapter{
	
	private int selectItem = -1;
	
	public int getSelectItem() {
		return selectItem;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}

	private Context context;
	
	private ArrayList<String> dataList;
	public RecycleTypeAdapter(Context context, ArrayList<String> list){
		this.context = context;
		this.dataList = list;
	}

	@Override
	public int getCount() {
		return dataList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return dataList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;  
        if(null==convertView){  
            holder=new Holder();  
            convertView=LayoutInflater.from(context).inflate(R.layout.recycle_item, null); //mContext指的是调用的Activtty  
            holder.textView=(TextView)convertView.findViewById(R.id.recycle_type_text);
            holder.greenLine = (View)convertView.findViewById(R.id.green_line);
            holder.greyLine = (View)convertView.findViewById(R.id.grey_line);
            holder.layout = (FrameLayout)convertView.findViewById(R.id.layout);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        if(position==selectItem){
        	holder.greenLine.setVisibility(View.VISIBLE);
        	holder.textView.setTextColor(Color.parseColor("#1dd186"));
        	holder.layout.setBackgroundColor(Color.parseColor("#F2F2F2"));
        }else{
        	holder.greenLine.setVisibility(View.GONE);
        	holder.textView.setTextColor(Color.parseColor("#333333"));
        	holder.layout.setBackgroundColor(Color.parseColor("#Ffffff"));
        }
        holder.textView.setText(dataList.get(position));  
        return convertView;
	}
	
	class Holder  
    {  
        public TextView textView;  
        public View greenLine;
        public View greyLine;
        public FrameLayout layout;
  
    }

}
