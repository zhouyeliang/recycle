package com.recycle.android.net.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;

import org.apache.http.util.ByteArrayBuffer;

import android.util.Log;

/**
 * 
 * @author zhouyeliang
 * 
 */
public class HttpRequestUtil {

	private final String TAG = HttpRequestUtil.class.getSimpleName();

	private static final int TIMEOUT = 30 * 1000;

	/**
	 * @param baseurl
	 *            TODO
	 * @param param
	 *            TODO
	 * @param result
	 * @return
	 */
	public static String doHttpRequest(String baseurl)
			throws UnknownHostException, SocketTimeoutException, IOException {
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		Log.e("", "doHttpRequest " + baseurl);
		try {
			url = new URL(baseurl);
			connection = (HttpURLConnection) url.openConnection();
			//connection.setDoInput(true);
			//connection.setDoOutput(true);
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(TIMEOUT);
			connection.setReadTimeout(TIMEOUT);
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			return strBuffer.toString();

		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}


}
