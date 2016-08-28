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
import com.recycle.android.model.Order;
import com.recycle.recycle.R;

public class OrderAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<Order> dataList;
	public OrderAdapter(Context context, ArrayList<Order> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_order, null); //mContext指的是调用的Activtty  
            holder.orderTitle=(TextView)convertView.findViewById(R.id.order_title);
            holder.orderTime=(TextView)convertView.findViewById(R.id.order_time);
            holder.orderTotalJf=(TextView)convertView.findViewById(R.id.order_jf);
            holder.orderState=(TextView)convertView.findViewById(R.id.order_state);
            holder.imageView=(ImageView)convertView.findViewById(R.id.order_image);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.orderTitle.setText(dataList.get(position).getOrderNo()); 
        holder.orderTime.setText(dataList.get(position).getOrderTime());
        holder.orderTotalJf.setText(dataList.get(position).getTotalJf()); 
        holder.orderState.setText(dataList.get(position).getOrderState()); 
        ImageLoader.getInstance().displayImage( Scheme.ASSETS.wrap("message_act.png"), holder.imageView);
        return convertView;
	}
	
	class Holder  
    {  
        public TextView orderTitle;  
        public TextView orderTime;
        public TextView orderTotalJf; 
        public TextView orderState; 
        public ImageView imageView;
  
    }

}
