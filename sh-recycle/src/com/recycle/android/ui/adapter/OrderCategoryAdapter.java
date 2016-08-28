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
import com.recycle.android.model.OrderCategory;
import com.recycle.recycle.R;

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
            holder.orderTotalJf=(TextView)convertView.findViewById(R.id.order_price);
            holder.imageView=(ImageView)convertView.findViewById(R.id.camera_image);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.orderTitle.setText(dataList.get(position).getCategoryName()); 
        holder.orderNum.setText(dataList.get(position).getCategoryNum()+" 公斤");
        holder.orderTotalJf.setText(dataList.get(position).getCategoryjf() + " 积分"); 
        //ImageLoader.getInstance().displayImage( Scheme.ASSETS.wrap("message_act.png"), holder.imageView);
        return convertView;
	}
	
	class Holder  
    {  
        public TextView orderTitle;  
        public TextView orderNum;
        public TextView orderTotalJf;
        public ImageView imageView;
  
    }

}
