package com.recycle.pad.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.pad.model.base.JackJson;

/**
 * 回收分类实体类
 * @author Administrator
 *
 */
public class CategoryInfo extends JackJson{
	
	private String parentId;

	private String categoryId;
	
	private String categoryName;

	private String imgUrl;
	
	private String price;
	
	private String description;


	public static final String KEY_PARENT_ID ="name";
	public static final String KEY_CATEGORY_ID ="Gd_ID";
	public static final String KEY_CATEGORY_NAME="category";
	public static final String KEY_IMAGE_URL ="imgUrl";
	public static final String KEY_PRICE ="price";
	public static final String KEY_DESCRIPTION ="description";


	public CategoryInfo(JSONObject obj){
		super(obj);
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		parentId = job.optString(KEY_PARENT_ID);
		categoryId = job.optString(KEY_CATEGORY_ID);
		categoryName = job.optString(KEY_CATEGORY_NAME);
		imgUrl = job.optString(KEY_IMAGE_URL);
		price = job.optString(KEY_PRICE);
		description = job.optString(KEY_DESCRIPTION);
	}
	
}
