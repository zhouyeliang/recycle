package com.recycle.pad.net.action.member;

import java.util.Map;

import com.recycle.pad.net.action.ActAbsSthReq;
import come.recycle.pad.util.NetConstant;

/**
 * 二维码登录接口
 * @author zhouyeliang
 *
 */
public class QrLoginReq extends ActAbsSthReq {
	
	String qrCode;
	
	public QrLoginReq(String qrCode){
		this.qrCode = qrCode;
	}

	@Override
	public Map<String, String> halfwayParamMap(Map<String, String> halfway) {
		halfway.put("qrcode", qrCode);
		return halfway;
	}

	@Override
	public String getApiUrl() {
		return NetConstant.URL_QR_LOGIN;
	}

}
