package com.recycle.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.android.model.base.JackJson;

/**
 * 订单中的已选择的分类信息
 * @author zhouyeliang
 *
 */
public class OrderCategory extends JackJson {

	private String orderId;

	private String categoryId;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getCategoryjf() {
		return categoryjf;
	}

	public void setCategoryjf(String categoryjf) {
		this.categoryjf = categoryjf;
	}

	private String categoryName;
	
	private String categoryNum;
	
	private String categoryjf;
	
	/**
	 * 图片url
	 */
	private String imgUrl;

	public static final String KEY_ORDER_ID = "order_id";
	public static final String KEY_ID = "Gd_ID";
	public static final String KEY_NAME = "category_name";
	public static final String KEY_NUM = "quantity";
	public static final String KEY_JF_NUM = "category_price";
	public static final String KEY_IMAGE_URL = "category_image";
	
	public OrderCategory() {
	}

	public OrderCategory(JSONObject obj) {
		super(obj);
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}



	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		orderId = job.optString(KEY_ORDER_ID);
		categoryId = job.optString(KEY_ID);
		categoryName = job.optString(KEY_NAME);
		categoryNum = job.optString(KEY_NUM);
		categoryjf = job.optString(KEY_JF_NUM);
	}

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

}
