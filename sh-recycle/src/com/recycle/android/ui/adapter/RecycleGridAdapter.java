package com.recycle.android.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.recycle.android.model.CategoryInfo;
import com.recycle.recycle.R;
import come.recycle.android.util.ImageLoaderHelper;

public class RecycleGridAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<CategoryInfo> dataList;
	public RecycleGridAdapter(Context context, ArrayList<CategoryInfo> list){
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
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder;  
        if(null==convertView){  
            holder=new Holder();  
            convertView=LayoutInflater.from(context).inflate(R.layout.recycle_griditem, null); //mContext指的是调用的Activtty  
            holder.textView=(TextView)convertView.findViewById(R.id.recycle_text);
            holder.imageView=(ImageView)convertView.findViewById(R.id.recycle_image);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.textView.setText(dataList.get(position).getCategoryName());  
        ImageLoaderHelper.displayImageFromURL(dataList.get(position).getImgUrl(),holder.imageView);
        return convertView;
	}
	
	class Holder  
    {  
        public TextView textView;  
        public ImageView imageView;
  
    }

}
