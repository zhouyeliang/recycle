package com.recycle.android.ui.widget;

import java.util.Calendar;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.recycle.android.ui.adapter.NumericWheelAdapter;
import com.recycle.recycle.R;

/**
 * 
 * @author zhouyeliang
 * 
 */
public class TimeDialog implements OnClickListener {
	private Context context;
	private Dialog dialog;

	private Button mBtnConfirm;
	
	private WheelView month;
	private WheelView day;
	private WheelView hour;
	private WheelView min;
	
	private int currentMonth;
	
	private int currentDay;
	
	private int currentHour;
	
	private int currentMin;

	private Display display;
	
	private OnConfirmListener listener;
	
	int norYear;

	public TimeDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public TimeDialog builder() {
		
		Calendar c = Calendar.getInstance();
		
		norYear = c.get(Calendar.YEAR);
		currentMonth = c.get(Calendar.MONTH) + 1;
		currentDay = c.get(Calendar.DATE);
		currentHour = c.get(Calendar.HOUR);
		currentMin = c.get(Calendar.MINUTE);
		
		View view = LayoutInflater.from(context).inflate(
				R.layout.view_time_sheet, null);

		view.setMinimumWidth(display.getWidth());
		month = (WheelView) view.findViewById(R.id.id_month);
		NumericWheelAdapter numericWheelAdapter2=new NumericWheelAdapter(context,1, 12, "%02d"); 
		numericWheelAdapter2.setLabel("月");
		month.setViewAdapter(numericWheelAdapter2);
		month.setCyclic(true);
		month.addScrollingListener(scrollListener);
		
		day = (WheelView) view.findViewById(R.id.id_day);
		initDay(norYear,currentMonth);
		day.setCyclic(true);
		
		hour = (WheelView) view.findViewById(R.id.id_hour);
		NumericWheelAdapter numericWheelAdapter3=new NumericWheelAdapter(context,1, 23, "%02d"); 
		numericWheelAdapter3.setLabel("时");
		hour.setViewAdapter(numericWheelAdapter3);
		hour.setCyclic(true);
		hour.addScrollingListener(scrollListener);
		
		min = (WheelView) view.findViewById(R.id.id_minute);
		NumericWheelAdapter numericWheelAdapter4=new NumericWheelAdapter(context,1, 59, "%02d"); 
		numericWheelAdapter4.setLabel("分");
		min.setViewAdapter(numericWheelAdapter4);
		min.setCyclic(true);
		min.addScrollingListener(scrollListener);
		
		//设置显示行数
		month.setVisibleItems(7);
		day.setVisibleItems(7);
		hour.setVisibleItems(7);
		min.setVisibleItems(7);
		
		month.setCurrentItem(currentMonth - 1);
		day.setCurrentItem(currentDay - 1);
		hour.setCurrentItem(currentHour-1);
		min.setCurrentItem(currentMin-1);
		
		
		mBtnConfirm = (Button) view.findViewById(R.id.btn_confirm);
		mBtnConfirm.setOnClickListener(this);
		dialog = new Dialog(context, R.style.ActionSheetDialogStyle);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);
		return this;
	}

	OnWheelScrollListener scrollListener = new OnWheelScrollListener() {
		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {
			currentMonth = month.getCurrentItem() + 1;
			currentDay = day.getCurrentItem() + 1;
			currentHour = hour.getCurrentItem()+1;
			currentMin = min.getCurrentItem()+1;
			initDay(norYear,currentMonth);
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

	/**
	 */
	private void initDay(int arg1, int arg2) {
		NumericWheelAdapter numericWheelAdapter=new NumericWheelAdapter(context,1, getDay(arg1, arg2), "%02d");
		numericWheelAdapter.setLabel("日");
		day.setViewAdapter(numericWheelAdapter);
	}

	public void show() {
		dialog.show();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_confirm:
			listener.onConfirm(currentMonth,currentDay,currentHour,currentMin);
			this.dialog.dismiss();
			break;
		default:
			break;
		}

	}

	public interface OnConfirmListener {
		public void onConfirm(int month,int day,int hour,int minute);
	}

	public TimeDialog setOnConfirmListener(OnConfirmListener listener) {
		this.listener = listener;
		return this;
	}

}
