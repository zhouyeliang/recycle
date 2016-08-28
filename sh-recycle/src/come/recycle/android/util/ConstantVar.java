package come.recycle.android.util;

import java.lang.ref.SoftReference;
import java.util.HashMap;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;

/**
 * 鎵?湁闈欐?甯搁噺闆?
 * 
 * @Modified By ZZ
 * @Modified Comments 琛ュ叏闈欐?甯搁噺娉ㄩ噴
 * @Modified Date 2013-09-27
 * 
 */
public final class ConstantVar {
	private ConstantVar() {

	}

	
	public static final String RECYCLE_FRAGMENT_KET = "";
	
	public static final String IMAGE_URL_KEY = "imageUrl";
	
	public static final String RECY_NAME_KEY = "recycleName";
	
	public static final String RECYCLE_TITLE = "分类";
	
	
	public static final String PREF_USERNAME_KEY = "username";
	public static final String PREF_PASSWORD_KEY = "password";
	
	public static final String PREF_USER_NAME = ".recycle";
	
	
	
	
	
	
	/**
	 * 鏃犵敤鐨勯潤鎬佸父閲忥紝寰呴噸鏋勬椂澶勭悊
	 */
	public static int jumpkey = 0;

	public static String AES_KEY = "c4c081c26b4974b0";

	/*------ XML鏂囦欢寮? ------*/
	/**
	 * 鐢ㄦ埛淇℃伅xml鏂囦欢
	 */
	public static final String USERINFOPRE = "userInfo";

	/**
	 * 鐢靛櫒淇℃伅xml鏂囦欢
	 * */

	public static final String SENSINGINFO = "sensingInfo";

	/**
	 * 閰嶇疆甯搁噺淇℃伅xml
	 */
	/*------ XML鏂囦欢缁撴潫 ------*/
	public static final String SET_INFO = "setting";
	/**
	 * 鐢ㄦ埛鍑嗗叆token
	 */
	public static final String USERACCESSTOKEN = "user_access_token";
	/**
	 * 绌哄?
	 */
	public static final String NO_VALUE = "";
	/**
	 * 鐢ㄦ埛Id
	 */
	public static final String USERID = "userId";
	/**
	 * 鐢ㄦ埛鍚?
	 */
	public static final String USERNAME = "username";
	/**
	 * 鏈?悗涓?鐧诲綍鐨勭敤鎴峰悕(鐢ㄤ簬瑙ｅ喅婕旂ず璐︽埛鐧婚檰鍚庯紝鍘熺櫥褰曞悕绉拌瑕嗙洊鐨勯棶棰?
	 */
	public static final String LAST_USERNAME = "lastusername";
	/**
	 * 瀵嗙爜
	 */
	public static final String PASSWORD = "password";
	/**
	 * 鑷姩鐧诲綍
	 */
	public static final String AUTOLOGIN = "autoLogin";
	/**
	 * 鑷姩鐧诲綍鍕鹃?妗嗘渶鍚庝竴娆＄殑鐘舵?
	 */
	public static final String AUTOLOGIN_CHECKBOX_LAST_STATUS = "autoLogin_Checkbox_Last_Status";
	/**
	 * 瀵嗙爜淇濇姢
	 */
	public static final String PASSWORDPROTECT = "passwordProtect";
	/**
	 * 瀵嗙爜淇濇姢璇︾粏淇℃伅
	 */
	public static final String PASSWORDPROTECTDETAIL = "passwordProtectDetail";
	/**
	 * 閭
	 */
	public static final String EMAIL = "email";
	/**
	 * 鐢佃瘽
	 */
	public static final String TEL = "phone";
	/**
	 * 绉侀挜
	 */
	public static final String PRIVATE_KEY = "privateKey";

	/**
	 * 妫?煡缃戠粶鐘舵?
	 */
	public static final String CHECK_NET_WORK = "checkNetwork";
	/**
	 * 琛屼负瀵硅薄鍒濆鍖?
	 */
	public static Intent intent = null;
	/**
	 * 鐧诲綍琛屼负鍒濆鍖?
	 */
	public static Intent logintent = null;
	/**
	 * 绾跨▼姹犺秴鏃舵椂闂?
	 */
	public static final int TIMEOUTCONNECTION = 20000;

	// 鍐呯綉鐜1
//	 public static String BASE_URL = "http://10.10.11.70:20004/";
//	 public static String HTTPS_BASE_URL = "https://10.10.11.70:20004/";

	// 娴嬭瘯鐜
//	public static String BASE_URL = "http://cn.telehems.com:10004/";
//	public static String HTTPS_BASE_URL = "https://cn.telehems.com:10007/";

	// 姝ｅ紡鐜
	 public static String BASE_URL = "http://cn.telehems.com:10002/";
	 public static String HTTPS_BASE_URL = "https://cn.telehems.com:10011/";

	// app涓嬭浇鍦板潃
	public static String APPDOWNLOAD_URL = "http://cn.telehems.com:10002/appdownload";

	// REST璇锋眰璋冪敤POST鏂规硶
	public static final String POSTSEND = "POSTSEND";
	// REST璇锋眰璋冪敤GET鏂规硶
	public static final String GETSEND = "GETSEND";
	// REST璇锋眰璋冪敤DELETE鏂规硶
	public static final String DELETESEND = "DELETESEND";
	// 绾跨▼姹犳牳蹇冪嚎绋嬫暟
	public static final int COREPOOLSIZE = 4;

	// 绾跨▼姹犳渶澶х嚎绋嬫暟
	public static final int MAXPOOLSIZE = 100;

	// 棰濆绾跨▼绌虹姸鎬佺敓瀛樻椂闂?
	public static final int KEEPALIVETIME = 1000;

	// 闃诲闃熷垪鐨勫ぇ灏?
	public static final int WORKQUEUESIZE = 100;
	/**
	 * rest寮曟搸杩斿洖缁撴灉鏍囪瘑
	 */
	public static final String RESULT = "result";

	/**
	 * handler澶勭悊绫诲瀷鏍囪瘑
	 */
	public static final int HANDLERINGORE = 0;
	public static final int HANDLERTYPEONE = 1;
	public static final int HANDLERTYPETWO = 2;
	public static final int HANDLERTYPETHREE = 3;
	public static final int HANDLERTYPEFOUR = 4;
	public static final int HANDLERTYPEFIVE = 5;
	public static final int HANLDERTYPESIX = 6;
	public static final int HANLDERTYPESEVEN = 7;
	public static final int HANDLERTYPECHECKVERSION = 50;

	public static final String HANLDERTYPEFAST = "500001";
	public static final String HANLDERTYPELOW = "500002";
	public static final String MSGTYPE = "msgType";
	public static final String METHODNAME = "methodName";
	public static final String PARAMETER = "parameter";
	public static final String ROOT = "root";
	public static final String JSONID = "jsonId";
	public static final String RUNSTATE = "runState";
	public static final String SENSETEMPERATURE = "senseTemperature";
	public static final String ATTTEMPERATURE = "attachmentTemperature";
	public static final String STARTTIME = "startTime";
	public static final String ENDTIME = "endTime";
	public static final String VALUE = "value";
	public static final String APPLIANCETYPE = "applianceType";
	public static final String OBJECT = "object";
	public static final String CONTENT = "content";
	public static final String STATESTRING = "stateString";
	public static final String APPLIANCEID = "applianceId";
	public static final String APPLIANCEIDGROUP = "applianceIdGroup";
	public static final String ELEAPPLIANCEIDGROUP = "eleApplianceIdGroup";
	public static final String APPLIANCENAMEGROUP = "applianceNameGroup";
	public static final String FUNCISOPEN = "funcIsOpen";
	public static final String RES = "res";
	public static final String HOUR = "hour";

	// 鎸囦护鐨刪andlerResult
	public static final int HANLDERTYPECOMMAND = 99;
	/**
	 * 杩涘叆rest鎺ュ彛锛屾帴鍙ｆ湰韬甯歌繍琛岃繑鍥為敊璇紝濡傦細瀵嗙爜閿欒銆佺敤鎴蜂笉瀛樺湪绛?
	 */
	public static final int REST_INNER_ERROR = 100;
	public static final int HANDLER_TYPE_NETWORK = 101;

	/**
	 * 杩炰笂浜戠锛屼簯绔病鏁版嵁杩斿洖
	 */
	public static final int CLOUD_WITHOUT_BACK = 102;

	/**
	 * 婕旂ず璐︽埛鏃犳潈鎿嶄綔閿欒
	 */
	public static final int TEST_USER_ERROR = 103;

	/**
	 * 杩炰笂浜嗕簯绔紝浣咹甯佷笉瓒筹紝鎿嶄綔澶辫触閿欒浠ｇ爜
	 */
	public static final int HB_NOT_ENOUGH = 104;

	/**
	 * HB涓嶈冻锛屾搷浣滃け璐ワ紝浜戠杩斿洖鐨勯敊璇秷鎭?
	 */
	public static final String HB_NOT_ENOUGH_MSG = "HB_NOT_ENOUGH";

	public static final int HTTP_STATE_SERVER_ERROR = 500;
	
	public static final int HTTP_STATE_NOT_FOUND_ERROR = 404;
	
	public static final int HTTP_STATE_UNAUTHORIZED_ERROR = 401;
	public static final String HTTP_STATE_UNAUTHORIZED_ERROR_CONTENT = "瀹㈡埛绔棶棰橈細Token閿欒鎴栬?杩囨湡";

	// http杩斿洖400閿欒
	public static final int HTTP_STATE_BAD_REQUEST_ERROR = 400;


	/**
	 * 杩炴帴瓒呮椂
	 */
	public static final int LONG_TIME_KEY = 999;
	public static final String LONG_TIME_KEY_CONTENT = "杩炴帴瓒呮椂";
	public static final String LONGTIMEEXCEPTION = "{error:longTimeException}";

	/**
	 * 鎺ュ叆鐐归棶棰?
	 */
	public static final int APN_TYPE_NETWORK = 601;
	public static final String APN_NOT_AVAILABLE_CONTENT = "wap鎺ュ叆鐐逛笉鍙敤锛岃鍒囨崲net鎺ュ叆鐐癸紒";
	public static final String APNERROREXCEPTION = "{error:apnErrorException}";

	// 姝ｅ父閿欒锛氱敤鎴蜂笉瀛樺湪
	public static final String ERROR_NO_USER_FOUND = "ERROR_NO_USER_FOUND";
	// 姝ｅ父閿欒锛氬瘑鐮侀敊璇?
	public static final String ERROR_WRONG_PASSWORD = "ERROR_WRONG_PASSWORD";

	/**
	 * 娴嬭瘯璐︽埛鏃犳潈鎿嶄綔閿欒
	 */
	public static final String TESTUSER_NOT_ALLOW = "TESTUSER_NOT_ALLOW";

	// 姝ｅ父閿欒锛氬瘑鐮侀敊璇?
	public static final String ERROR_WRONG_PASSWORD_STRING = "瀵嗙爜閿欒";
	// token閿欒锛歵oken杩囨湡
	public static final String INVALID_TOKEN = "invalid_token";

	/*------ 璁惧绫诲瀷寮? ------*/
	/**
	 * 绌鸿澶?
	 */
	public static final String NULL_TYPE = "1001000";
	/**
	 * 绫诲瀷涓虹┖璋?
	 */
	public static final String AIRCONDITIONTYPE = "1001001";
	/**
	 * 绫诲瀷涓虹儹姘村櫒
	 */
	public static final String WATERHEATERTYPE = "1001002";
	/**
	 * 楗按鏈?
	 */
	public static final String WATER_DISPENSER_TYPE = "1001003";
	/**
	 * 绫诲瀷涓虹數瑙嗘満
	 */
	public static final String TELEVISIONTYPE = "1001004";
	/**
	 * 绫诲瀷涓虹數鍐扮
	 */
	public static final String ICEBOX_TYPE = "1001005";
	/**
	 * 绫诲瀷涓烘礂琛ｆ満
	 */
	public static final String WASHER = "1001006";
	/**
	 * 绫诲瀷涓哄皬鍘ㄥ疂
	 */
	public static final String SMALL_KITCHEN = "1001007";
	/**
	 * 绫诲瀷鐓ф槑
	 */
	public static final String LIGHT = "1001008";
	/**
	 * 绫诲瀷鏈洪《鐩?
	 */
	public static final String TVSETTOPBOX = "1001009";
	/**
	 * 绫诲瀷鐢靛彇鏆栧櫒
	 */
	public static final String WARM = "1001011";
	/**
	 * 绫诲瀷鐑按寰幆娉?
	 */
	public static final String HOTWATER = "1001010";
	/**
	 * 绫诲瀷绌烘皵鍑?寲鍣ˋir purifier
	 */
	public static final String AIRPURIFIER = "1001012";
	/**
	 * 鍏跺畠鐢靛櫒绫诲瀷
	 */
	public static final String OTHER_TYPE = "1001999";

	/*------ 璁惧绫诲瀷缁撴潫 ------*/

	/*------ 鎸囦护闆嗗紑濮?------*/
	// 寮?寚浠?
	public static final String OPENCOMMAND = "1";
	// 鍏虫寚浠?
	public static final String CLOSECOMMAND = "0";
	// 绌鸿皟璋冭妭鎸囦护
	public static final String AIRCOMMAND = "2";
	// 绌鸿皟绠?崟璋冭妭鎸囦护
	public static final String AIRSIMPLECOMMAND = "3";
	// 绌鸿皟3妯″紡绠?崟璋冭妭鎸囦护 String 
	public static final String AIRSTHREEMODELCOMMAND="4";
	// 鏈洪《鐩掕皟鑺傛寚浠?
	public static final String TVCOMMAND="5";
	// 绌烘皵鍑?寲鍣ㄨ皟鑺傛寚浠?
	public static final String AIRPURIFIERCOMMAND="6";

	/*------ 鎸囦护闆嗙粨鏉?------*/

	/*------ 鐢靛櫒鐘舵?寮? ------*/
	/**
	 * 鐢靛櫒鐘舵?
	 */
	public static final int EQUIPMENTSTATENONETWORK = 0;
	public static final int EQUIPMENTSTATEOPEN = 1;
	public static final String EQUIPMENTSTATEOPENSTRING = "鎵撳紑";
	public static final int EQUIPMENTSTATECLOSE = 0;
	public static final String EQUIPMENTSTATECLOSESTRING = "鍏抽棴";
	public static final int EQUIPMENTSTATEWAIT = 2;
	public static final String EQUIPMENTSTATEWAITSTRING = "寰呮満";
	public static final int DEVICE_STATE_OPEN = 1;
	public static final int DEVICE_STATE_CLOSE = 0;

	/*------ 鐢靛櫒鐘舵?缁撴潫 ------*/

	/*------ date鍜宨nt闂存崲绠楀紑濮?------*/
	/**
	 * date鍜宨nt鎹㈢畻
	 */
	public static final int SECONDS = 3600;
	public static final int MINUTE = 60;
	public static final int YEAR = 365;

	/*------ date鍜宨nt闂存崲绠楃粨鏉?------*/

	/*------ 琛ㄧず鏁板瓧鐨刬ndex寮? ------*/
	/**
	 * 琛ㄧず鏁板瓧鐨刬ndex
	 */
	public static final double INDEXOFUNONEHUNDRED = -100.00;
	public static final int INDEXOFUNTWENTY = -20;
	public static final int INDEXOFZERO = 0;
	public static final int INDEXOFONE = 1;
	public static final int INDEXOFTWO = 2;
	/**
	 * 鎺ㄩ?娑堟伅绫诲瀷鈥斺?瀹夊叏娑堟伅
	 */
	public static final int INDEXOFTHREE = 3;
	/**
	 * 鎺ㄩ?娑堟伅绫诲瀷鈥斺?鏅烘帶娑堟伅(鏀逛负闃茬洍娑堟伅)
	 */
	public static final int INDEXOFFOUR = 4;
	/**
	 * 鎺ㄩ?娑堟伅绫诲瀷鈥斺?鐢靛櫒鏁呴殰
	 */
	public static final int INDEXOFFIVE = 5;
	public static final int INDEXOFSIX = 6;
	/**
	 * 鎺ㄩ?娑堟伅绫诲瀷鈥斺?鏀粯瀹濇敮浠樻秷鎭?
	 */
	public static final int INDEXOFSEVEN = 7;
	
	public static final int INDEXOFEIGHT = 8;
	/**
	 * 鎺ㄩ?娑堟伅绫诲瀷鈥斺?绯荤粺鍏憡
	 */
	public static final int INDEXOFNINE = 9;
	public static final int INDEXOFTEN = 10;

	public static final int INDEXOFELEVEN = 11;
	public static final int INDEXOFTWELVE = 12;
	public static final int INDEXOFTHIRTEEN = 13;
	public static final int INDEXOFFOURTEEN = 14;
	public static final int INDEXOFFIFTEEN = 15;
	public static final int INDEXOFSIXTEEN = 16;
	public static final int INDEXOFTWENTY = 20;
	public static final int INDEXOFTWENTYTHREE = 23;
	public static final int INDEXOFTWENTYFIVE = 25;
	public static final int INDEXOFTHIRTY = 30;
	public static final int INDEXOFFIFTY = 50;
	public static final int INDEXOFFIFTYNINE = 59;
	public static final int INDEXOFSEVENTY = 70;
	public static final int INDEXOFSEVENFIVE = 75;
	public static final int INDEXOFEIGHTY = 80;
	public static final int INDEXOFEIGHTYNINE = 89;
	public static final int INDEXOFNINETY = 90;
	public static final int INDEXOFONEHUNDRED = 100;
	public static final int INDEXOFONESEVENSIX = 176;
	public static final int INDEXOFTWOHUNDRED = 200;
	public static final int INDEXOFTWOTHREEFIVE = 235;
	public static final int INDEXOFTHREEHUNDRED = 300;
	public static final int INDEXOFFOURHUNDRED = 400;
	public static final int INDEXOFFIVEHUNDRED = 500;
	public static final int INDEXOFFIVETHOUSAND = 5000;
	public static final int INDEXOFTWENTYMILLION = 20000000;

	/**
	 * 棰滆壊
	 */
	public static final int COLORONE = Color.argb(100, 249, 64, 64);
	public static final int COLORTWO = Color.argb(100, 0, 255, 0);
	public static final int COLORTHREE = Color.argb(100, 255, 0, 255);
	public static final int COLORFOUR = Color.argb(100, 255, 255, 0);
	public static final int COLORFIVE = Color.argb(100, 0, 255, 255);

	/**
	 * 灞忓箷澶у皬
	 */

	public static final int SCREEWIDTHONE = 480;
	public static final int SCREEWIDTHTWO = 320;

	public static final int SCREEN_HEIGHT_EIGHT_FIVE_FOUR = 854;

	/*------ 琛ㄧず鏁板瓧鐨刬ndex缁撴潫 ------*/

	/*------ 寰呮満鑰楃數瀛楃涓插父閲忓紑濮?------*/
	/**
	 * 寰呮満鑰楃數瀛楃涓插父閲?
	 */
	// 鏂滅嚎
	public static final String VIRGULE = "/";

	public static final double MAGICZEROPFIVE = 0.5;
	public static final int MAGICSIXTEEN = 16;
	public static final int MAGICTHTHOUSANDS = 1000;

	/*------ 寰呮満鑰楃數瀛楃涓插父閲忕粨鏉?------*/
	// 鍍忕礌楂?20
	public static final int HEIGHT_320 = 320;
	// 鍍忕礌楂?60
	public static final int HEIGHT_460 = 460;
	// 鍍忕礌楂?80
	public static final int HEIGHT_480 = 480;
	// 鍍忕礌楂?40
	public static final int HEIGHT_540 = 540;
	// 鍍忕礌楂?40
	public static final int HEIGHT_640 = 640;
	// 鍍忕礌楂?00
	public static final int HEIGHT_800 = 800;
	// 鍍忕礌楂?54
	public static final int HEIGHT_854 = 854;
	// 鍍忕礌楂?60
	public static final int HEIGHT_960 = 960;
	// 鍍忕礌楂?000
	public static final int HEIGHT_1000 = 1000;
	// 鍍忕礌楂?184
	public static final int HEIGHT_1184 = 1184;
	// 鍍忕礌楂?024
	public static final int HEIGHT_1024 = 1024;
	// 鍍忕礌楂?020
	public static final int HEIGHT_1020 = 1020;
	// 鍍忕礌楂?280
	public static final int HEIGHT_1280 = 1280;
	// 鍍忕礌楂?920
	public static final int HEIGHT_1920 = 1920;
	// 鍍忕礌楂?800
	public static final int HEIGHT_1800 = 1800;
	// 鍍忕礌瀹?20
	public static final int WIDTH_320 = 320;
	// 鍍忕礌瀹?80
	public static final int WIDTH_480 = 480;
	// 鍍忕礌瀹?40
	public static final int WIDTH_540 = 540;
	// 鍍忕礌瀹?40
	public static final int WIDTH_640 = 640;
	// 鍍忕礌瀹?00
	public static final int WIDTH_800 = 800;

	// 鍍忕礌瀹?60
	public static final int WIDTH_960 = 960;
	// 鍍忕礌瀹?20
	public static final int WIDTH_720 = 720;
	// 鍍忕礌瀹?00
	public static final int WIDTH_700 = 700;
	// 鍍忕礌瀹?50
	public static final int WIDTH_650 = 650;
	// 鍍忕礌瀹?10
	public static final int WIDTH_610 = 610;
	// 鍍忕礌瀹?00
	public static final int WIDTH_600 = 600;
	// 鍍忕礌瀹?00
	public static final int WIDTH_500 = 500;
	// 鍍忕礌瀹?50
	public static final int WIDTH_450 = 450;
	/**
	 * 瀹夊叏绫诲瀷锛氭澶勪互涓枃鎷奸煶涓哄彉閲忓悕
	 */
	// 杩囧帇
	public static final String GUOYA = "255";
	// 娆犲帇
	public static final String QIANYA = "254";
	// 杩囪浇
	public static final String GUOZAI = "253";
	// 婕忕數
	public static final String LOUDIAN = "252";
	// 鍦扮嚎鎮┖涓嶆甯?
	public static final String DIXIANXUANKONG_BUZHENGCHANG = "251";
	// 鍦扮嚎姝ｅ父
	public static final String DIXIANXUANKONG_ZHENGCHANG = "250";

	/*------ 绌鸿皟鎺у埗鐩稿叧甯搁噺寮? ------*/
	/**
	 * 绌鸿皟璋冭妭鍒濆娓╁害
	 */
	public static final String AIRSTARTTEMPERATURE = "16";
	/**
	 * 绌鸿皟娓╁害璋冭妭璺ㄥ害鍊?
	 */
	public static final int AIRBETWEENTEMPERATURE = 16;
	/**
	 * 娓╁害鏄剧ず鈩冪殑浣嶇疆闀垮害
	 */
	public static final int tempeWidth = 20;
	/**
	 * 绌鸿皟妯″紡鎸囦护绫诲瀷
	 */
	public static final String WETSCHEMA = "4";
	public static final String HOTSCHEMA = "3";
	public static final String COOLSCHEMA = "2";
	public static final String WINDSCHEMA = "1";
	public static final String AUTOSCHEMA = "0";

	/**
	 * 绌鸿皟鎸囦护绫诲瀷
	 */
	public static final int ELECTYPE = 0;
	public static final int DEVTYPE = 1;
	public static final int TEMPTYPE = 2;
	public static final int SCHETYPE = 3;
	public static final int SIMPLETYPE = 4;

	/**
	 * 绠?崟绌鸿皟闈㈡澘鍛戒护绫诲瀷
	 */
	public static final String REDUCETEMP = "2";
	public static final String RASETEMP = "1";
	public static final String DEVSIMPLE = "3";
	public static final String SCHEMASIMPLE = "0";
	
	/**
	 * 3妯″紡绠?崟绌鸿皟闈㈡澘鍛戒护绫诲瀷
	 */
	public static final String REDUCE_TEMP = "6";
	public static final String RASE_TEMP = "5";
	public static final String AIR_OPEN = "7";
	public static final String AIR_CLOSE = "8";
	/**
	 * 绌鸿皟鏈?皬鏈?ぇ娓╁害
	 */
	public static final int MAXTEMP = 32;
	public static final int MINTEMP = 16;

	/**
	 * encryptionType锛氬姞瀵嗭紱绫诲瀷
	 * 
	 * 1锛氬叕閽ヤ负key杩涜AES鍔犲瘑锛?
	 * 
	 * 2锛歅RIVATE_KEY涓簁ey杩涜AES鍔犲瘑锛?
	 * 
	 * 3锛氭槑鏂囦紶杈擄紝鎸囩汗楠岃瘉銆丠MAC_MD5鍔犲瘑銆?
	 */
	public static final int ENCRYPTIONTYPE_ONE = 1;
	public static final int ENCRYPTIONTYPE_TWO = 2;
	public static final int ENCRYPTIONTYPE_THREE = 3;
	/**
	 * 澶嶆潅闈㈡澘
	 */
	public static final String COMPLEX_PANEL = "1";
	/**
	 * 绠?崟闈㈡澘
	 */
	public static final String SIMPLE_PANEL = "11";
	/**
	 * 3妯″紡鐨勭畝鍗曢潰鏉?
	 */
	public static final String THREE_MODEL_PANEL = "12";
	/**
	 *  绌烘皵鍑?寲鍣ˋir purifier
	 */
	public static final String AIR_PURIFIER ="31";

	/*------ 绌鸿皟鎺у埗鐩稿叧甯搁噺缁撴潫 ------*/
	/**
	 * 閲嶄簯绔幏鍙栨椂闂村悗锛屼笉鏂?1锛屽緟countTimeFromCloud >=timeFromCloud 鏃讹紝鍘讳簯绔噸鏂拌幏鍙栧?
	 */
	public static long countTimeFromCloud = 0;
	/**
	 * 涓?紑濮嬫槸鍚﹀凡缁忚幏鍙栧悓姝ユ椂闂?
	 */
	public static boolean isGetTime = false;
	/**
	 * 瀛橀獙璇佺爜鍜岄獙璇佺爜uuid
	 */
	public static String verifyCodeUuid = null;
	public static byte[] verifyCodeValue = null;
	/**
	 * 鏍囧織浣?鐢靛櫒娣诲姞,缂栬緫锛屽垹闄ゆ垚鍔?椤甸潰鍒锋柊
	 */
	public static int applianceAddorEditOk = 0;
	/**
	 * 杞紩鐢?瀹炵幇缂撳瓨
	 */
	public static HashMap<String, SoftReference<Bitmap>> imageCache = new HashMap<String, SoftReference<Bitmap>>();
	public final static String server_url = "https://msp.alipay.com/x.htm";
	/**
	 * 鍚堜綔鍟嗘埛ID銆傜敤绛剧害鏀粯瀹濊处鍙风櫥褰昺s.alipay.com鍚庯紝鍦ㄨ处鎴蜂俊鎭〉闈㈣幏鍙栥?
	 */
	public static final String PARTNER = "2088901792171221";
	/**
	 * 鍟嗘埛鏀舵鐨勬敮浠樺疂璐﹀彿
	 */
	public static final String SELLER = "hems@smtp.telek.com.cn";
	/**
	 * 鍟嗘埛锛圧SA锛夌閽?
	 */
	public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALW+6Mcl8xs6YX6fos3llc64TDMT1MBT4FSbOtVOnHk6wIKwXmNtsnoMOLdBTB52tt8/8j+2shFoMAW2XF+/UkxX7Y0wLE4ysZn/PcdxITML75nziDgb45jCVIeyY/1wmJmjSAWL6IZKQr9QIkcy7i5PgtiKxFx9oooJnzeFw+YFAgMBAAECgYBNIsybEVE/wiR04H3xkzpktTWqDAWk5zRz1DpBJA8nKLdiAEbfPkScpilAJdePqmTtFa/UOM8UsjqHgiFshcAAbL7I/D24oILM6AdSUa7FMIORfp8c/GPxH7aQ6TJQCL80aSwUYJ8th0GD7jp8m4m9n0Iap+RLB7kp4v87RELk1QJBAPAbR7Nl1UNAYWixrqGgUeLkDJYeSlTkxCIYR52U0kCk9drbjQunaEAO47VCFckDojN1btsXoeB3FlKR6aBAxzsCQQDBxq1jKgeZZwdb9rySRUcbUx6uCqDioCEaNIgqpcJgGmUcljRLnQ0oBaT+JuDyIu/LZNuQMH+i/m5nLnQWU7O/AkA9zmfokCATjbEFaCeRx1kM1LNAuU9dBv5N4H4e3D47EbuPuNBX3aICS1LmR7qf4eWQ5XvBvhWXWFBQtOq0q7gdAkEAjvGZzDIgAviuxBdiieBTEQQi+K5cenE3mhYwcI1wsuYluo3PLAxBvUlUqitR+yzsibTwBxDPxxQ7qx/ogOPkDwJAXdCcr39IFidVbzkoRGmgnukt18PhWSW6qUf7bahaL9QQiAxL5Lyx7CZnEywDmxiUDNA7c0LX9DKeYwfcLJ20NQ==";
	/**
	 * 鏀粯瀹濓紙RSA锛夊叕閽?鐢ㄧ绾︽敮浠樺疂璐﹀彿鐧诲綍ms.alipay.com鍚庯紝鍦ㄥ瘑閽ョ鐞嗛〉闈㈣幏鍙栥?
	 */
	public static final String RSA_ALIPAY_PUBLIC = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCW11VQrLjn8JPXoPQ3sHZLdSTMsY4V29Yxa+8b UyAVqw7moAnKzwzO9Gw7WsWj8UICNdr+ZeCBBlSaejWAsvWoa6s8WqiFGatQMAGSF1LumNU3/Suc yRpvK+r5MCR//a6oIkjtBucfmNrJlrkFsMtB98cv46dytRxyjBDebVEEQwIDAQAB";
	/**
	 * 鏀粯瀹濆畨鍏ㄦ敮浠樻湇鍔pk鐨勫悕绉帮紝蹇呴』涓巃ssets鐩綍涓嬬殑apk鍚嶇О涓?嚧
	 */
	public static final String ALIPAY_PLUGIN_NAME = "alipay_plugin_20120428msp.apk";
	/**
	 * 鍔犲湪澶栭儴璁㈠崟鍙峰墠鐨勫墠缂?紝鏀粯瀹?
	 */
	public static final String ALI_TRADE = "ali";
	public static final String LYX_NUMBER = "15869027968";

	/**
	 * 鍗囩骇鏍囧織浣嶈嫢涓轰竴鍒欏凡瀛樺湪浠诲姟
	 * */
	public static int appUpdateFlag = 0;
	/**
	 * 鍏ュ彛绫诲瀷 0涓虹洿鎺ユ墦寮?1涓轰粠缇庤閫氭墦寮?
	 * */
	public static int LAUNCHERTYPE = 0;
	/**
	 * apk涓嬭浇閾炬帴鍦板潃
	 * 
	 */
	public static String apkUpdateUrl = BASE_URL + "appdownload";
	/**
	 * 瀵嗙爜淇濇姢椤甸潰鏍囧織浣嶏紝鐢ㄤ簬瀵嗙爜淇濇姢寮?叧锛屽湪杩斿洖鏉ョ殑鏃跺?涓嶈Е鍙戠洃鍚?
	 */
	public static int protectPasswordFlag = 0;
	/**
	 * 鏄惁搴旂敤澶勪簬鍚庡彴 false鍒欏湪鍚庡彴
	 */
	public static boolean appInBackGround = true;

	public static boolean isFromPasswordprotect = false;
	/**
	 * 鍚庡彴鏍囧織浣嶏紝鐢ㄤ簬瀵嗙爜淇濇姢椤甸潰璺宠浆锛岃嫢鏋滄槸0鍒欒鏄庝笉鏄悗鍙板惎鍔?1鐨勮瘽璇存槑鏄悗鍙?
	 */
	public static int appInBackGroundFlag = 0;

	public static boolean appIsNotInFrontGround = true;
	public static int messageR = 1;

	public static int unreadnotification = 0;
	public static int isAirMode = 0;
	public static int isSmart = 0;
	public static int isMatchOpen = 1;
	public static Double temptemp = -100.00;
	public static String newapplianceid = "";
	// 鍖归厤鎴愬姛鍚庝簯绔紶鍏ョ殑鍖归厤鎴愬姛鐢靛櫒id
	public static String matchOverApplianceId = "";

	public static String deleteApplianceId = "";

	
	

	public static final String HANDLER_TYPE_KEY = "";
	
	/**
	 * 鑾峰彇楠岃瘉鐮佺殑绫诲瀷鍜屾柟寮?
	 */
	//1 涓虹粦瀹氾紝2涓鸿В缁?
	public static final String BUND = "1";
	public static final String UNBUND = "2";
	//1锛氱煭淇★紝2锛氶偖绠憋紙缁戝畾缁熶竴涓?锛岃В缁戞湁涓ょ鏂瑰紡锛?
	public static final String PHONE_TYPE = "1";
	public static final String EMAIL_TYPE = "2";
	//榛樿缁戝畾鎵嬫満澶囨敞鍚?
	public static final String 	DEFAULT_PHONE_NAME = "鏈缃娉ㄥ悕";
	
	
	
	//鎺堟潈鍏抽棴鐢靛櫒鐨勬帓鍒楅『搴?
	public static final String 	AUTHORIZE = "1";
	public static final String 	NULL_PROTECT_ID = "2";
	public static final String 	NO_AUTHORIZE = "3";
	//淇濇姢鐢靛櫒涓虹┖
	public static final String 	NULL_PROTECT_NAME = "闀挎寜鏈姞鍏ョ殑鐢靛櫒鎷栫疆姝ゅ";
	
	
	
}
