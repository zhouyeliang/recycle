package com.recycle.android.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.recycle.android.model.CouponsInfo;
import com.recycle.android.model.Order;
import com.recycle.recycle.R;

public class CouponAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<CouponsInfo> dataList;
	public CouponAdapter(Context context, ArrayList<CouponsInfo> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_coupon, null); //mContext指的是调用的Activtty  
            holder.orderTitle=(TextView)convertView.findViewById(R.id.order_title);
            holder.orderTime=(TextView)convertView.findViewById(R.id.order_time);
            holder.orderTotalJf=(TextView)convertView.findViewById(R.id.order_jf);
            holder.orderNum=(TextView)convertView.findViewById(R.id.order_number);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.orderTitle.setText(dataList.get(position).getCouponName()); 
        holder.orderTime.setText(dataList.get(position).getTime());
        holder.orderTotalJf.setText(dataList.get(position).getJifen()); 
        holder.orderNum.setText(dataList.get(position).getNumber()); 
        return convertView;
	}
	
	class Holder  
    {  
        public TextView orderTitle;  
        public TextView orderTime;
        public TextView orderTotalJf; 
        public TextView orderNum;
  
    }

}
