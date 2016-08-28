package com.recycle.android.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.recycle.android.model.OrderCategory;
import com.recycle.recycle.R;

public class OrderSimpleCategoryAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<OrderCategory> dataList;
	public OrderSimpleCategoryAdapter(Context context, ArrayList<OrderCategory> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_order_category_simple, null);  
            holder.recycleType=(TextView)convertView.findViewById(R.id.recycle_type);
            holder.recycleNum=(TextView)convertView.findViewById(R.id.num);
            holder.recycleJf=(TextView)convertView.findViewById(R.id.money);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.recycleType.setText(dataList.get(position).getCategoryName()); 
        holder.recycleNum.setText(dataList.get(position).getCategoryNum() + "单位");
        holder.recycleJf.setText(dataList.get(position).getCategoryjf() + "积分"); 
        return convertView;
	}
	
	class Holder  
    {  
        public TextView recycleType;  
        public TextView recycleNum;
        public TextView recycleJf;
  
    }

}
