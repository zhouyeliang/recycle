package com.recycle.pad.ui.life;

import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.recycle_pad.R;
import com.recycle.pad.base.BaseActivity;
import com.recycle.pad.manager.OrderDataManager;
import com.recycle.pad.manager.UserDataManager;
import com.recycle.pad.model.OrderCategory;
import com.recycle.pad.print.DataUtils;
import com.recycle.pad.print.Global;
import com.recycle.pad.print.WorkService;
import com.recycle.pad.ui.adapter.OrderCategoryAdapter;

/**
 * 生活券打印页面
 * 
 * @author zhouyeliang
 * 
 */
public class LifePrintActivity extends BaseActivity implements OnClickListener {

	/*--------------------------------------------控件相关定义--------------------------------------------*/
	/**
	 * 打印按钮
	 */
	private Button printBtn;

	private Button noPrintBtn;

	private ListView listView;

	private ArrayList<OrderCategory> list;

	private OrderCategoryAdapter adapter;
	
	private TextView nameTv;

	/*------------------------------------------------------数据相关---------------------------------------------------------*/

	private Context mContext;

	private UserDataManager userDataManager;

	private OrderDataManager orderDataManager;

	private static Handler mHandler = null;
	
	private String orderNo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_life_print);
		mContext = this;
		orderNo = getIntent().getExtras().getString("orderNo");
		mHandler = new MHandler(this);
		WorkService.addHandler(mHandler);
		probe();
		userDataManager = UserDataManager.getInstance();
		orderDataManager = OrderDataManager.getInstance();
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void initView() {
		nameTv = (TextView) findViewById(R.id.name);
		printBtn = (Button) findViewById(R.id.print);
		printBtn.setOnClickListener(this);
		noPrintBtn = (Button) findViewById(R.id.no_print);
		noPrintBtn.setOnClickListener(this);

		listView = (ListView) findViewById(R.id.order_cate_list);
		list = OrderDataManager.getInstance().getCurrentCategorys();
		adapter = new OrderCategoryAdapter(mContext, list);
		listView.setAdapter(adapter);
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.print:
			if (WorkService.workThread.isConnected()) {
				print();
				list.clear();
				finish();
			} else {
				Toast.makeText(this, Global.toast_notconnect,
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.no_print:
			list.clear();
			finish();
			break;
		}
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {
		super.onActivityResult(arg0, arg1, arg2);
		switch (arg0) {
		// 照相返回
		case 1:
			break;
		default:
			break;
		}
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		list.clear();
	}

	/*
	 * 打印二维码
	 * 
	 * @param strqrcode 二维码内容
	 * 
	 * @param qr_width 宽度值范围2~6
	 */
	public void printQR(String strqrcode, int qr_width) {
		Bundle data = new Bundle();
		data.putString(Global.STRPARA1, strqrcode);
		data.putInt(Global.INTPARA1, qr_width);// 宽度控制单个模块宽度
		data.putInt(Global.INTPARA2, 10); // 版本控制模块数量
		data.putInt(Global.INTPARA3, 4);
		WorkService.workThread.handleCmd(Global.CMD_POS_SETQRCODE, data);
	}

	private void print() {
		byte header1[] = new byte[] { 0x1b, 0x40 };// 初始化
		byte header[] = new byte[] { 0x1b, 0x44, 0x0a, 0x10, 0x16, 0x00 };// 分割
		byte header2[] = new byte[] { 0x09 };// 分割点
		byte size1[] = new byte[] { 0x1d, 0x21, 0x00 }; // 字体正常大小
		byte size2[] = new byte[] { 0x1d, 0x21, 0x11 }; // 字体放大一倍
		byte size3[] = new byte[] { 0x1d, 0x21, 0x10 }; // 字体倍宽
		byte size4[] = new byte[] { 0x1d, 0x21, 0x01 }; // 字体倍高
		byte Align_Left[] = new byte[] { 0x1b, 0x61, 0x00, };// 左对齐
		byte Align_Center[] = new byte[] { 0x1b, 0x61, 0x01 };// 居中对齐
		byte Align_Right[] = new byte[] { 0x1b, 0x61, 0x02 };// 右对齐
		byte bold[] = new byte[] { 0x1b, 0x21, 0x08 };// 加粗
		byte end[] = new byte[] { 0x0d, 0x0a }; // 结束符
		byte str1[] = null;
		byte str2[] = null;
		byte str3[] = null;
		byte str4[] = null;
		byte str5[] = null;
		byte str6[] = null;
		byte str7[] = null;
		byte str78[] = null;
		byte str8[] = null;
		byte str9[] = null;
		byte str10[] = null;
		byte str11[] = null;
		byte str12[] = null;
		byte str13[] = null;
		byte str14[] = null;
		byte str15[] = null;
		byte str16[] = null;
		byte str17[] = null;
		byte str18[] = null;
		byte str19[] = null;
		byte str20[] = null;
		byte str21[] = null;
		byte str22[] = null;
		byte str23[] = null;
		byte buffer2[] = new byte[] {};
		try {
			str1 = "\r\n\r\n浙江易收环保科技有限公司\r\n".getBytes("GBK");
			str2 = "智能垃圾分类积分兑换系统\r\n\r\n".getBytes("GBK");
			String date = new SimpleDateFormat("yyyy年MM月dd日  HH:mm").format(new Date());
			str3 = ("日期：" + date + "\r\n").getBytes("GBK");
			str4 = ("订单号："+ orderNo + "\r\n").getBytes("GBK");
			str5 = ("\r\n\r\n用户名："+ UserDataManager.getInstance().getUser().getUserName() + "\r\n").getBytes("GBK");
			str6 = ("用户ID："+ UserDataManager.getInstance().getUser().getUserId() + "\r\n").getBytes("GBK");
			str7 = "兑换码：12345678".getBytes("GBK");
			str78 = "[扫一扫下载app]\r\n".getBytes("GBK");
			str8 = "请登录www.e-shou.cn或使用手机APP查询订单相关信息。\r\n".getBytes("GBK");
			str9 = "提示：用户凭本小票到指定兑换点兑换相应的商品需一次性兑换完毕。\r\n".getBytes("GBK");
			str11 = "一个兑换码只能兑换一次,未兑换完毕的无法延续下次继续兑换。\r\n\r\n".getBytes("GBK");
			str12 = "商品名称".getBytes("GBK");
			str13 = "数量".getBytes("GBK");
			str14 = "金额".getBytes("GBK");
			str15 = "使用积分".getBytes("GBK");
			str16 = "\r\nxxxxx".getBytes("GBK");
			str17 = "2".getBytes("GBK");
			str18 = "10.00".getBytes("GBK");
			str19 = "200.00".getBytes("GBK");
			str20 = "\r\n小计".getBytes("GBK");
			int totalNum = 0;
			float totalJf = 0f;

			for (int i = 0; i < list.size(); i++) {
				OrderCategory info = list.get(i);
				buffer2 = DataUtils.byteArraysToBytes(new byte[][] { buffer2,
						info.getCategoryName().getBytes("GBK"), header2, info.getCategoryNum().getBytes("GBK"),
						header2, info.getCategoryjf().getBytes("GBK"), header2, info.getCategoryjf().getBytes("GBK") });
				totalNum = totalNum + Integer.parseInt(info.getCategoryNum());
				totalJf = totalJf + Float.parseFloat(info.getCategoryjf());
				
			}
			str21 = (totalNum+"").getBytes("GBK");
			str22 = (totalJf+"").getBytes("GBK");
			str23 = (totalJf+"").getBytes("GBK");
			byte buffer[] = DataUtils.byteArraysToBytes(new byte[][] { header1,
					size4, Align_Center, str1, str2, header1, str3, str4,
					header, str12, header2, str13, header2, str14, header2,
					str15, header2, buffer2, header2, str20, header2, str21,
					header2, str22, header2, str23, str5, str6, str7, end });
			byte buffer3[] = DataUtils.byteArraysToBytes(new byte[][] {
					header1, Align_Center, str78, header1, str8, str9, header1,
					bold, str11, end });

			Bundle data2 = new Bundle();
			data2.putByteArray(Global.BYTESPARA1, buffer3);
			data2.putInt(Global.INTPARA1, 0);
			data2.putInt(Global.INTPARA2, buffer3.length);
			Bundle data = new Bundle();
			data.putByteArray(Global.BYTESPARA1, buffer);
			data.putInt(Global.INTPARA1, 0);
			data.putInt(Global.INTPARA2, buffer.length);
			WorkService.workThread.handleCmd(Global.CMD_POS_WRITE, data);
			//
			printQR("fdsfdsfasdfsd", 5);
			WorkService.workThread.handleCmd(Global.CMD_POS_WRITE, data2);

		} catch (Exception e) {
		}

	}

	private void probe() {
		final UsbManager mUsbManager = (UsbManager) getSystemService(Context.USB_SERVICE);
		HashMap<String, UsbDevice> deviceList = mUsbManager.getDeviceList();
		//Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
		for(UsbDevice device:deviceList.values()){
			if (mUsbManager.hasPermission(device)) {
				if(WorkService.workThread==null){
					Log.e("", "workthread null");
				}
				WorkService.workThread.connectUsb(mUsbManager, device);
			}
		}
		/*if (deviceList.size() > 0) {
			// 初始化选择对话框布局，并添加按钮和事件
				final UsbDevice device = deviceList.get("/dev/bus/usb/001/009");
				PendingIntent mPermissionIntent = PendingIntent.getBroadcast(
								LifePrintActivity.this,
								0,
								new Intent(LifePrintActivity.this
										.getApplicationInfo().packageName), 0);
				

		}*/
	}

	static class MHandler extends Handler {

		WeakReference<LifePrintActivity> mActivity;

		MHandler(LifePrintActivity activity) {
			mActivity = new WeakReference<LifePrintActivity>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			LifePrintActivity theActivity = mActivity.get();
			switch (msg.what) {
			/**
			 * DrawerService 的 onStartCommand会发送这个消息
			 */

			case Global.MSG_WORKTHREAD_SEND_CONNECTUSBRESULT: {
				int result = msg.arg1;
				Toast.makeText(
						theActivity,
						(result == 1) ? Global.toast_success
								: Global.toast_fail, Toast.LENGTH_SHORT).show();
				Log.v("", "Connect Result: " + result);
				break;
			}

			}
		}

	}

}
