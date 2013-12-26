/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * 得到 URL 中的内容
 * @author 高青
 * 2013-12-2
 */
public class URLContentUtil {
	
	/**
	 * 得到 URL链接中的内容
	 * @author 高青
	 * 2013-12-3
	 * @param url 链接地址
	 * @return content 链接中的内容
	 */
	public static String getURLContent(String url){
		//URL链接对象
		URL urlObject = null;
		
		//HTTPURLConnection 对象
		HttpURLConnection httpURLConnection = null;
		
		//底层输入流对象
		InputStream inputStream = null;
		//字符流
		InputStreamReader  reader = null;
		//缓冲字符流
		BufferedReader bufferedReader = null;
		
		//初始化内容变量
		String content = "";
		
		try {
			//建立链接
			urlObject = new URL(url);
			try {
				//将得到的对象，强制转为 HttpURLConnection 
				httpURLConnection = (HttpURLConnection) urlObject.openConnection();
				
				//设置访问方式
				//httpURLConnection.setRequestMethod("get");
				//设置允许输出到变量中
				httpURLConnection.setDoOutput(true);
				//建立链接
				httpURLConnection.connect();
				
				//得到当前链接中的输入流
				inputStream = httpURLConnection.getInputStream();
				reader = new InputStreamReader(inputStream, "UTF-8");
				bufferedReader = new BufferedReader(reader);
				
				//存放当前行的字符变量
				String readLine = "";
				StringBuffer stringBuffer = new StringBuffer();
				
				//读取链接中的数据
				while ( (readLine = bufferedReader.readLine()) != null) {
					stringBuffer.append(readLine.trim());
				}
				content = stringBuffer.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}finally{
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (httpURLConnection != null) {
				httpURLConnection.disconnect();
			}
		}
		return content;
	}
}
