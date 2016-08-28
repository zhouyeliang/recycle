package com.recycle.android.ui.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.recycle.android.model.AddressInfo;
import com.recycle.recycle.R;

public class AddressAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<AddressInfo> dataList;
	public AddressAdapter(Context context, ArrayList<AddressInfo> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_address, null); //mContext指的是调用的Activtty  
            holder.name=(TextView)convertView.findViewById(R.id.order_username);
            holder.address=(TextView)convertView.findViewById(R.id.orer_useradd);
            holder.telephone=(TextView)convertView.findViewById(R.id.order_usertel);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }  
        holder.name.setText(dataList.get(position).getName()); 
        holder.address.setText(dataList.get(position).getAddress());
        holder.telephone.setText(dataList.get(position).getTelephone());
        return convertView;
	}
	
	class Holder  
    {  
        public TextView name;  
        public TextView address;  
        public TextView telephone;
  
    }

}
