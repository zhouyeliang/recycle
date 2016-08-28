package come.recycle.android.util;

import com.recycle.android.application.MyApplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;


/**
 * 所有Activity的抽象类
 * 
 * @author scc
 */
public abstract class BaseActivity extends FragmentActivity {
	private String versionName;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
	}

	/**
	 * Activity激活时候的操作
	 */
	@Override
	protected void onResume() {
		if (!ConstantVar.appInBackGround) {
			ConstantVar.appInBackGround = true;
			ConstantVar.isFromPasswordprotect = false;
		}
		ConstantVar.appIsNotInFrontGround = false;
		super.onResume();

	}

	@Override
	protected void onStart() {
		//avtivity在前台显示
		ConstantVar.appIsNotInFrontGround = false;
		super.onStart();

	}

	/**
	 * Activity被其他Activity覆盖时执行的操作
	 */
	@Override
	protected void onStop() {
		super.onStop();
		ConstantVar.imageCache.clear();
		System.gc();
		ConstantVar.appInBackGround = Util.isTopActivity(this);
		if (!ConstantVar.appInBackGround) {
			ConstantVar.appInBackGroundFlag = 1;
		}
		ConstantVar.appIsNotInFrontGround = true;
	}

	

}
