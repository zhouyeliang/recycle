package com.recycle.android.manager;

import com.recycle.android.model.AddressInfo;

public class AddressDataManager {
	
	private static AddressDataManager addressDataManager = new AddressDataManager();
	/**
	 * 当前选中的地址信息
	 */
	private AddressInfo currentAddress;
	
	
	private AddressDataManager(){
		
	}
	public static AddressDataManager getInstance(){
		return addressDataManager;
	}

	public AddressInfo getCurrentAddress() {
		return currentAddress;
	}
	public void setCurrentAddress(AddressInfo currentAddress) {
		this.currentAddress = currentAddress;
	}

}
