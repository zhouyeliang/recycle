package com.recycle.android.model;

import org.json.JSONException;
import org.json.JSONObject;

import com.recycle.android.model.base.JackJson;

/**
 * 生活购买的物品实体类
 * @author Administrator
 *
 */
public class LifeGoods extends JackJson{
	
	
	private String goodsId;
	/**
	 * 商品名称
	 */
	private String goodsTitle;

	/**
	 * 单张优惠券积分
	 */
	private String goodsjf;

	/**
	 * 商品图片url
	 */
	private String imgUrl;

	private String goodsDes;
	
	public LifeGoods(){
		goodsTitle = "";
		goodsjf = "";
		imgUrl = "";
		goodsDes = "";
		goodsId = "";
	}
	
	public LifeGoods(JSONObject obj) {
		super(obj);
	}
	

	@Override
	public JSONObject toJsonObj() {
		return job;
	}

	public static final String KEY_GD_ID = "Gd_ID";
	public static final String KEY_NAME = "title";
	public static final String KEY_JF = "jifen";
	public static final String KEY_IMAGE_URL = "image";
	public static final String KEY_DESCRIPTION = "description";
	
	@Override
	public void initJackJson(JSONObject job) throws JSONException {
		goodsTitle = job.optString(KEY_NAME);
		goodsjf = job.optString(KEY_JF);
		imgUrl = job.optString(KEY_IMAGE_URL);
		goodsDes = job.optString(KEY_DESCRIPTION);
		goodsId = job.optString(KEY_GD_ID);
		
	}


	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getGoodsTitle() {
		return goodsTitle;
	}

	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
	
	public String getGoodsjf() {
		return goodsjf;
	}

	public void setGoodsjf(String goodsjf) {
		this.goodsjf = goodsjf;
	}
	
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsDes() {
		return goodsDes;
	}

	public void setGoodsDes(String goodsDes) {
		this.goodsDes = goodsDes;
	}
}
