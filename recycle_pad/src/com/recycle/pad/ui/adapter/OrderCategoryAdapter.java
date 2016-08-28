package com.recycle.pad.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.recycle_pad.R;
import com.recycle.pad.model.OrderCategory;

public class OrderCategoryAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<OrderCategory> dataList;
	public OrderCategoryAdapter(Context context, ArrayList<OrderCategory> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_order_category, null); //mContext指的是调用的Activtty  
            holder.orderTitle=(TextView)convertView.findViewById(R.id.order_title);
            holder.orderNum=(TextView)convertView.findViewById(R.id.order_num);
            holder.orderJf=(TextView)convertView.findViewById(R.id.order_price);
            holder.orderTotalJf=(TextView)convertView.findViewById(R.id.order_total_price);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.orderTitle.setText(dataList.get(position).getCategoryName()); 
        holder.orderNum.setText(dataList.get(position).getCategoryNum()+" 公斤");
        holder.orderJf.setText(dataList.get(position).getCategoryjf() + " 积分"); 
        holder.orderTotalJf.setText(Float.parseFloat(dataList.get(position).getCategoryjf())
        		*Float.parseFloat(dataList.get(position).getCategoryNum()) + "积分");
        return convertView;
	}
	
	class Holder  
    {  
        public TextView orderTitle;  
        public TextView orderNum;
        public TextView orderJf;
        public TextView orderTotalJf;
    }

}
