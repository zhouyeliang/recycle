package com.recycle.android.ui.recycle;

import java.util.Calendar;

import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.recycle.android.ui.adapter.NumericWheelAdapter;
import com.recycle.android.ui.widget.OnWheelScrollListener;
import com.recycle.android.ui.widget.WheelView;
import com.recycle.recycle.R;

import come.recycle.android.util.BaseActivity;

public class TimePickActivity extends BaseActivity{
	
	private WheelView month;
	private WheelView day;
	private WheelView hour;
	private WheelView min;
	
	private View timeView;
	
	private LinearLayout timePicker;
	
	private LayoutInflater inflater = null;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.time_picker);
		inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
		initView();
	}
	
	private void initView(){
		timePicker = (LinearLayout)findViewById(R.id.time_picker);
		timePicker.addView(getDataPick());
	}

	private View getDataPick() {
		Calendar c = Calendar.getInstance();
		int norYear = c.get(Calendar.YEAR);
		int curMonth = c.get(Calendar.MONTH) + 1;//通过Calendar算出的月数要+1
		int curDate = c.get(Calendar.DATE);
		
		timeView = inflater.inflate(R.layout.wheel_date_picker, null);
		
		month = (WheelView) timeView.findViewById(R.id.month);
		NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(this,1, 12, "%02d"); 
		numericWheelAdapter2.setLabel("月");
		month.setViewAdapter(numericWheelAdapter2);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);
		
		day = (WheelView) timeView.findViewById(R.id.day);
		initDay(norYear,curMonth);
		day.setCyclic(true);
		
		hour = (WheelView) timeView.findViewById(R.id.hour);
		NumericWheelAdapter numericWheelAdapter4=new NumericWheelAdapter(this,1, 23, "%02d"); 
		numericWheelAdapter4.setLabel("时");
		hour.setViewAdapter(numericWheelAdapter4);
		hour.setCyclic(true);
		hour.addScrollingListener(scrollListener);
		
		min = (WheelView) timeView.findViewById(R.id.min);
		NumericWheelAdapter numericWheelAdapter3=new NumericWheelAdapter(this,1, 59, "%02d"); 
		numericWheelAdapter3.setLabel("分");
		min.setViewAdapter(numericWheelAdapter3);
		min.setCyclic(true);
		min.addScrollingListener(scrollListener);
		

		month.setVisibleItems(7);
		day.setVisibleItems(7);
		hour.setVisibleItems(7);
		min.setVisibleItems(7);
		month.setCurrentItem(curMonth - 1);
		day.setCurrentItem(curDate - 1);
		
		return timeView;
	}
	
	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
		}
	};
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	private int getDay(int year, int month) {
		int day = 30;
		boolean flag = false;
		switch (year % 4) {
		case 0:
			flag = true;
			break;
		default:
			flag = false;
			break;
		}
		switch (month) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			day = 31;
			break;
		case 2:
			day = flag ? 29 : 28;
			break;
		default:
			day = 30;
			break;
		}
		return day;
	}
	
	private void initDay(int arg1, int arg2) {
		NumericWheelAdapter numericWheelAdapter=new NumericWheelAdapter(this,1, getDay(arg1, arg2), "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}
}
