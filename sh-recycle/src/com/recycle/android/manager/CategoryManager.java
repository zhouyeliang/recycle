package com.recycle.android.manager;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.recycle.android.model.CategoryInfo;
import com.recycle.android.model.LifeGoods;
import com.recycle.android.net.action.ActAbsSthRcv;
import com.recycle.android.net.action.ActionBuilder;
import com.recycle.android.net.action.category.GetCategoryDetailReq;
import com.recycle.android.net.action.category.GetCategoryReq;
import com.recycle.android.net.action.life.GetLifeReq;
import com.recycle.android.net.action.life.GetTitleLifeReq;

import come.recycle.android.util.NetConstant;

public class CategoryManager {
	private static CategoryManager categoryManager = new CategoryManager();
	
	private HashMap<String,ArrayList<CategoryInfo>> map = new HashMap<String,ArrayList<CategoryInfo>>();
	
	private ArrayList<CategoryInfo> detailInfos;
	
	private ArrayList<LifeGoods> goodInfos;
	
	private HashMap<String,ArrayList<LifeGoods>> lifeMap = new HashMap<String,ArrayList<LifeGoods>>();
	
	public HashMap<String, ArrayList<LifeGoods>> getLifeMap() {
		return lifeMap;
	}
	public void setLifeMap(HashMap<String, ArrayList<LifeGoods>> lifeMap) {
		this.lifeMap = lifeMap;
	}

	private ArrayList<CategoryInfo> lifeList = new ArrayList<CategoryInfo>();
	
	public HashMap<String, ArrayList<CategoryInfo>> getMap() {
		return map;
	}
	public void setMap(HashMap<String, ArrayList<CategoryInfo>> map) {
		this.map = map;
	}
	private CategoryManager(){
		detailInfos = new ArrayList<CategoryInfo>();
		goodInfos = new ArrayList<LifeGoods>();
	}
	public static CategoryManager getInstance(){
		return categoryManager;
	}
	
	public ArrayList<CategoryInfo> getCategory(Context context,final String parentId,final DataResponseListener listener){
		final ArrayList<CategoryInfo> list;
			list = new ArrayList<CategoryInfo>();
			GetCategoryReq req = new GetCategoryReq(parentId);
			ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
				@Override
				public boolean response(String result) throws JSONException {
					Log.e("", "getCategory " + result);
					JSONObject obj = new JSONObject(result);
					JSONArray array = obj.getJSONArray(NetConstant.RESULT_ARRAY);
					for(int i=0;i<array.length();i++){
						if(array.getJSONObject(i)!=null){
							CategoryInfo info = new CategoryInfo(array.getJSONObject(i));
							list.add(info);
						}
					}
					map.put(parentId, list);
					listener.response();
					return false;
				}
			};
		ActionBuilder.getInstance().request(req, rcv);
		return list;
	}
	
	public void getCategoryDetail(Context context,final String categoryId,final DataModelResponseListener listener){
		GetCategoryDetailReq req = new GetCategoryDetailReq(categoryId);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "getCategoryDetail " + result);
				JSONObject obj = new JSONObject(result);
				JSONObject resultObj = obj.getJSONObject(NetConstant.RESULT_OBJ);
				CategoryInfo info = new CategoryInfo(resultObj);
				listener.response(info);
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
	}
	
	public ArrayList<LifeGoods> getLifeList(Context context,final String typeId,final DataModelResponseListener listener){
		final ArrayList<LifeGoods> list = new ArrayList<LifeGoods>();
		GetLifeReq req = new GetLifeReq(typeId);
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				Log.e("", "getLifeList " + result);
				JSONObject obj = new JSONObject(result);
				JSONArray array = obj.getJSONArray(NetConstant.RESULT_ARRAY);
				for(int i=0;i<array.length();i++){
					LifeGoods good = new LifeGoods(array.getJSONObject(i));
					list.add(good);
				}
				lifeMap.put(typeId, list);
				listener.response(null);
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
		return list;
	}
	
	public ArrayList<CategoryInfo> getLifeTitleList(Context context,final DataModelResponseListener listener){
		GetTitleLifeReq req = new GetTitleLifeReq();
		ActAbsSthRcv rcv = new ActAbsSthRcv(context) {
			@Override
			public boolean response(String result) throws JSONException {
				lifeList.clear();
				JSONObject obj = new JSONObject(result);
				JSONArray array = obj.getJSONArray(NetConstant.RESULT_ARRAY);
				for(int i=0;i<array.length();i++){
					CategoryInfo good = new CategoryInfo(array.getJSONObject(i));
					lifeList.add(good);
				}
				listener.response(null);
				return false;
			}
		};
		ActionBuilder.getInstance().request(req, rcv);
		return lifeList;
	}
	
	

}
