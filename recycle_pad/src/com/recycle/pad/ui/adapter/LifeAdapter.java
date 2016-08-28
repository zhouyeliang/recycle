package com.recycle.pad.ui.adapter;

import java.util.ArrayList;

import com.example.recycle_pad.R;
import com.recycle.pad.model.LifeGoods;

import come.recycle.pad.util.ImageLoaderHelper;
import come.recycle.pad.util.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;


public class LifeAdapter extends BaseAdapter{
	
	private Context context;
	
	private ArrayList<LifeGoods> dataList;
	
	public ArrayList<LifeGoods> getDataList() {
		return dataList;
	}

	public void setDataList(ArrayList<LifeGoods> dataList) {
		this.dataList = dataList;
	}

	public LifeAdapter(Context context, ArrayList<LifeGoods> list){
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
            convertView=LayoutInflater.from(context).inflate(R.layout.item_life, null);
            holder.lifeTitle=(TextView)convertView.findViewById(R.id.goods_title);
            holder.lifePrice=(TextView)convertView.findViewById(R.id.goods_price);
            holder.imageView=(ImageView)convertView.findViewById(R.id.goods_icon);
            LayoutParams params = new LayoutParams(Util.getScreenWidth()/3,Util.getScreenWidth()/3);
            holder.imageView.setLayoutParams(params);
            convertView.setTag(holder);  
        }else{  
            holder=(Holder)convertView.getTag();  
        }
        holder.lifeTitle.setText(dataList.get(position).getGoodsTitle()); 
        holder.lifePrice.setText(dataList.get(position).getGoodsjf() + "积分");
        ImageLoaderHelper.displayImageFromURL(dataList.get(position).getImgUrl(),holder.imageView);
        return convertView;
	}
	
	class Holder  
    {  
        public TextView lifeTitle;  
        public TextView lifePrice;
        public ImageView imageView;
  
    }

}
