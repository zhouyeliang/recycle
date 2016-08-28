
package com.recycle.pad.main;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.Queue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android_serialport_api.ComBean;
import android_serialport_api.SerialHelper;
import android_serialport_api.SerialPortFinder;

import com.example.recycle_pad.R;
import com.recycle.pad.manager.OrderDataManager;
import com.recycle.pad.manager.UserDataManager;
import com.recycle.pad.model.OrderCategory;
import com.recycle.pad.model.base.JackJson;
import com.recycle.pad.print.WorkService;
import com.recycle.pad.ui.inter.DataResponseListener;
import com.recycle.pad.ui.life.LifeActivity;
import com.recycle.pad.ui.recycle.RecycleActivity;
import come.recycle.pad.util.CRC16M;
import come.recycle.pad.util.Util;

public class MainActivity extends Activity implements OnClickListener {

	private LinearLayout paperBtn;

	private LinearLayout plasicBtn;

	private LinearLayout applianceBtn;

	private LinearLayout glassBtn;

	private LinearLayout phoneBtn;
	
	private Button logoutBtn;
	
	private Button lifeBtn;
	
	private Button okBtn;
	
	private TextView nameTv;
	
	private TextView addressTv;
	
	private TextView jfTv;
	
	private LinearLayout noLoginLayout;
	
	private LinearLayout loginLayout;
	
	private int paperKg;
	private int plasicKg;
	private int applianceKg;
	private int glassKg;
	private int phoneKg;
	
	/**
	 * 是否是登录进来后的第一次称重
	 */
	private boolean jumpTo = false;
	
	
	/**
	 * 串口相关
	 */
	private SerialControl port;//串口
	private QrSerialControl portQr;//二维码串口
	private DispQueueThread DispQueue;//刷新显示线程
	SerialPortFinder mSerialPortFinder;
	
	private UserDataManager userDataManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Util.convertKgToG(4556);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		setContentView(R.layout.activity_main);
		userDataManager = UserDataManager.getInstance();
		mSerialPortFinder= new SerialPortFinder();
		port = new SerialControl();
		portQr = new QrSerialControl();
		DispQueue = new DispQueueThread();
        DispQueue.start();
        port.setPort("/dev/ttyS2");
        portQr.setPort("/dev/ttyS3");
        port.setBaudRate("9600");
        portQr.setBaudRate("9600");
		try
		{
			port.open();
			portQr.open();
		} catch (SecurityException e) {
			ShowMessage("打开串口失败:没有串口读/写权限!");
		} catch (IOException e) {
			ShowMessage("打开串口失败:未知错误!");
		} catch (InvalidParameterException e) {
			ShowMessage("打开串口失败:参数错误!");
		}
		initView();
		
		if (null == WorkService.workThread) {
			Intent intent = new Intent(this, WorkService.class);
			startService(intent);
		}
	}

	private void initView() {
		noLoginLayout = (LinearLayout) findViewById(R.id.no_login_layout);
		loginLayout = (LinearLayout) findViewById(R.id.login_layout);
		paperBtn = (LinearLayout) findViewById(R.id.paper);
		paperBtn.setOnClickListener(this);
		plasicBtn = (LinearLayout) findViewById(R.id.plasic);
		plasicBtn.setOnClickListener(this);
		applianceBtn = (LinearLayout) findViewById(R.id.appliance);
		applianceBtn.setOnClickListener(this);
		glassBtn = (LinearLayout) findViewById(R.id.glass);
		glassBtn.setOnClickListener(this);
		phoneBtn = (LinearLayout) findViewById(R.id.phone);
		phoneBtn.setOnClickListener(this);
		logoutBtn = (Button) findViewById(R.id.logout_btn);
		logoutBtn.setOnClickListener(this);
		lifeBtn = (Button) findViewById(R.id.life_btn);
		lifeBtn.setOnClickListener(this);
		okBtn = (Button) findViewById(R.id.ok_btn);
		okBtn.setOnClickListener(this);
		nameTv = (TextView) findViewById(R.id.name_tv);
		addressTv = (TextView) findViewById(R.id.address_tv);
		jfTv = (TextView) findViewById(R.id.jf_tv);
		nameTv.setText("");
		addressTv.setText("");
		jfTv.setText("");
		paperBtn.setClickable(false);
		plasicBtn.setClickable(false);
		applianceBtn.setClickable(false);
		glassBtn.setClickable(false);
		phoneBtn.setClickable(false);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.paper:
			if(!paperBtn.isSelected()){
				sendPortData(port,"01020000FF00");
				paperBtn.setSelected(true);
			}else{
				sendPortData(port,"010200000000");
				paperBtn.setSelected(false);
			}
			break;
		case R.id.plasic:
			if(!plasicBtn.isSelected()){
				sendPortData(port,"01020001FF00");
				plasicBtn.setSelected(true);
			}else{
				sendPortData(port,"010200010000");
				plasicBtn.setSelected(false);
			}

			break;
		case R.id.appliance:
			if(!applianceBtn.isSelected()){
				sendPortData(port,"01020002FF00");
				applianceBtn.setSelected(true);
			}else{
				sendPortData(port,"010200020000");
				applianceBtn.setSelected(false);
			}
			break;
		case R.id.glass:
			if(!glassBtn.isSelected()){
				sendPortData(port,"01020003FF00");
				glassBtn.setSelected(true);
			}else{
				sendPortData(port,"010200030000");
				glassBtn.setSelected(false);
			}
			break;
		case R.id.phone:
			if(!phoneBtn.isSelected()){
				sendPortData(port,"01020004FF00");
				phoneBtn.setSelected(true);
			}else{
				sendPortData(port,"010200040000");
				phoneBtn.setSelected(false);
			}
			break;
		case R.id.ok_btn:
			if(userDataManager.isLogin()){
				jumpTo = true;
				sendPortData(port,"010300000005");
			}else{
				Toast.makeText(this, "请先登录!", Toast.LENGTH_SHORT).show();
			}
			/*userDataManager.goQrLogin(MainActivity.this, "13251325", new DataResponseListener<Boolean>() {
				@Override
				public void response(Boolean t) {
					if(t){
						logoutBtn.setVisibility(View.VISIBLE);
						noLoginLayout.setVisibility(View.GONE);
						loginLayout.setVisibility(View.VISIBLE);
						nameTv.setText("姓名：" + userDataManager.getUser().getUserName());
						addressTv.setText("住址：" + userDataManager.getUser().getAddress().getAddress());
						jfTv.setText("当前账户积分余额：" + userDataManager.getUser().getJfRemain() + "分");
						paperBtn.setClickable(true);
						plasicBtn.setClickable(true);
						applianceBtn.setClickable(true);
						glassBtn.setClickable(true);
						phoneBtn.setClickable(true);
						sendPortData(port,"010300000005");
					}else{
						Toast.makeText(MainActivity.this, "登录失败，请检查您的二维码是否正确！", Toast.LENGTH_SHORT).show();
					}
				}
			});*/
			break;
		case R.id.life_btn:
			Util.jumpTo(this, LifeActivity.class);
			break;
		case R.id.logout_btn:
			nameTv.setText("");
			addressTv.setText("");
			jfTv.setText("");
			logoutBtn.setVisibility(View.GONE);
			noLoginLayout.setVisibility(View.VISIBLE);
			loginLayout.setVisibility(View.GONE);
			paperBtn.setClickable(false);
			plasicBtn.setClickable(false);
			applianceBtn.setClickable(false);
			glassBtn.setClickable(false);
			phoneBtn.setClickable(false);
			userDataManager.logout();
			jumpTo = false;
			break;
		default:
			break;
		}

	}
	
	   //----------------------------------------------------串口控制类
    private class SerialControl extends SerialHelper{
		public SerialControl(){
		}

		@Override
		protected void onDataReceived(final ComBean ComRecData)
		{
			DispQueue.AddQueue(ComRecData);//线程定时刷新显示(推荐)
		}
    }
    
    private class QrSerialControl extends SerialHelper{
		public QrSerialControl(){
		}

		@Override
		protected void onDataReceived(final ComBean ComRecData)
		{
			String qrCode = new String(ComRecData.bRec);
			if(UserDataManager.getInstance().isLogin()){
				Toast.makeText(MainActivity.this, "请先退出已登录的账户！", Toast.LENGTH_SHORT).show();
			}else{
				//扫描二维码后登录
				userDataManager.goQrLogin(MainActivity.this, qrCode, new DataResponseListener<Boolean>() {
					@Override
					public void response(Boolean t) {
						if(t){
							logoutBtn.setVisibility(View.VISIBLE);
							noLoginLayout.setVisibility(View.GONE);
							loginLayout.setVisibility(View.VISIBLE);
							nameTv.setText("姓名：" + userDataManager.getUser().getUserName());
							addressTv.setText("住址：" + userDataManager.getUser().getAddress().getAddress());
							jfTv.setText("当前账户积分余额：" + userDataManager.getUser().getJfRemain() + "分");
							paperBtn.setClickable(true);
							plasicBtn.setClickable(true);
							applianceBtn.setClickable(true);
							glassBtn.setClickable(true);
							phoneBtn.setClickable(true);
							sendPortData(port,"010300000005");
						}else{
							Toast.makeText(MainActivity.this, "登录失败，请检查您的二维码是否正确！", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
			
		}
    }
    //----------------------------------------------------刷新显示线程
    private class DispQueueThread extends Thread{
		private Queue<ComBean> QueueList = new LinkedList<ComBean>(); 
		@Override
		public void run() {
			super.run();
			while(!isInterrupted()) {
				final ComBean ComData;
		        while((ComData=QueueList.poll())!=null)
		        {
		        	runOnUiThread(new Runnable()
					{
						public void run()
						{
							DispRecData(ComData);
						}
					});
		        	try
					{
		        		Thread.sleep(100);//显示性能高的话，可以把此数值调小。
					} catch (Exception e)
					{
						e.printStackTrace();
					}
		        	break;
				}
			}
		}

		public synchronized void AddQueue(ComBean ComData){
			QueueList.add(ComData);
		}
	}
    
  //----------------------------------------------------显示接收数据
    private void DispRecData(ComBean ComRecData){
    	String result = CRC16M.getBufHexStr(ComRecData.bRec);
    	if(result.contains("01030000")){
    		if(!jumpTo){
    			String tmpPaper = result.substring(10, 14);
    			paperKg = Integer.parseInt(tmpPaper, 16);
    			String tmPlasic = result.substring(14, 18);
    			plasicKg = Integer.parseInt(tmPlasic, 16);
    			String tmpApp = result.substring(18, 22);
    			applianceKg = Integer.parseInt(tmpApp, 16);
    			String tmpGlass = result.substring(22, 26);
    			glassKg = Integer.parseInt(tmpGlass, 16);
    			String tmpPhone = result.substring(26, 30);
    			phoneKg = Integer.parseInt(tmpPhone, 16);
    			jumpTo = false;
    			Log.e("the kg","the kg is" + paperKg);
    			Log.e("the kg","the kg is" + plasicKg);
    			Log.e("the kg","the kg is" + applianceKg);
    			Log.e("the kg","the kg is" + glassKg);
    			Log.e("the kg","the kg is" + phoneKg);
    		}else{
    			final int subPaper = Integer.parseInt(result.substring(10, 14), 16) - paperKg;
    			final int subPlasic = Integer.parseInt(result.substring(14, 18), 16) - paperKg;
    			final int subApp = Integer.parseInt(result.substring(18, 22), 16) - paperKg;
    			final int subGlass = Integer.parseInt(result.substring(22, 26), 16) - paperKg;
    			final int subPhone = Integer.parseInt(result.substring(26, 30), 16) - paperKg;
    			StringBuffer categoryIds = new StringBuffer();
    			StringBuffer nums = new StringBuffer();
    			
    			/*categoryIds.append("1").append("|");
    			nums.append(Math.subPaper + "").append("|");
    			categoryIds.append("2").append("|");
    			nums.append("subPlasic").append("|");
    			categoryIds.append("4").append("|");
    			nums.append("subApp").append("|");
    			categoryIds.append("3").append("|");
    			nums.append("subGlass").append("|");
    			categoryIds.append("5");
    			nums.append("subPhone");*/
				OrderDataManager.getInstance().goOrder(MainActivity.this, categoryIds.toString(), nums.toString(), UserDataManager.getInstance().getUser().getUserId()+"",
						null, null, null, null,new DataResponseListener<JackJson>() {
							@Override
							public void response(JackJson t) {
								if(t!=null){
									Bundle bundle = new Bundle();
					    			bundle.putInt("paper", subPaper);
					    			bundle.putInt("plasic", subPlasic);
					    			bundle.putInt("app", subApp);
					    			bundle.putInt("glass", subGlass);
					    			bundle.putInt("phone", subPhone);
					    			Util.jumpTo(MainActivity.this, RecycleActivity.class,bundle);
								}
								
							}
				});
				
    		}
    	}
    }
    
  //----------------------------------------------------串口发送
    private void sendPortData(SerialHelper comPort,String sOut){
    	if (comPort!=null && comPort.isOpen())
		{
    		comPort.sendTxt(sOut);
		}
    }
    
    private void ShowMessage(String sMsg)
  	{
  		Toast.makeText(this, sMsg, Toast.LENGTH_SHORT).show();
  	}
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	CloseComPort(port);
    	CloseComPort(portQr);
    }
    
    private void CloseComPort(SerialHelper ComPort){
    	if (ComPort!=null){
    		ComPort.stopSend();
    		ComPort.close();
		}
    }

}
