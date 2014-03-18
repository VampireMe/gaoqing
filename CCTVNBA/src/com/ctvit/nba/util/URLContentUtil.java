/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * 得到 URL 中的内容
 * @author 高青
 * 2013-12-2
 */
public class URLContentUtil {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(URLContentUtil.class);
	
	/**
	 * <p>根据 URL 地址，得到内容，并将其封装到 JSONObject 对象中<p>
	 * 适合用于没有实体类的模块，且不用将数据保存到数据库的情况下
	 * @author 高青
	 * 2014-1-15
	 * @param url url地址
	 * @return urlJsonObject url路径中 JSONObject 格式的内容
	 */
	public static JSONObject getURLJsonObject(String url){
		//得到 url 内容
		String urlContent = getURLContent(url);
		
		//将 url 转为 JSONObject 对象
		JSONObject urlJsonObject = new JSONObject(urlContent);
		
		return urlJsonObject;
	}
	
	
	/**
	 * <p>根据URL地址，得到相应的  数据对象</p>
	 * <strong>适合使用自定义的 key，得到 JSONObject 下的 指定的 JSONArray 对象</strong>
	 * @author 高青
	 * 2014-03-18
	 * @param moduleName 模块名称
	 * @param innerUpdateModule_otherInfo 更新方式和其他附加信息字符串
	 * @param partURL 部分链接地址
	 * @param url 完整链接地址和
	 * @return tlist 相应类型的数据对象
	 */
	public static <T> List<T> getTListByURL(
			String moduleName, 
			String innerUpdateModule_otherInfo, 
			String partURL, 
			String url,
			String jsonObjectKey
			){
		//初始化对象
		List<T> tlist = new ArrayList<T>();
		
		try {
			JSONArray tJsonArray = null;
			
			//得到  jsonArray 对象
			tJsonArray = getJsonArrayBySpecialKey(url,jsonObjectKey);
			
			for (int i = 0; i < tJsonArray.length(); i++) {
				//得到一个 JSONObject 对象
				JSONObject jsonObject = (JSONObject)tJsonArray.get(i);
				
				T t = getEntityByJSONObject(jsonObject, moduleName, innerUpdateModule_otherInfo);
				
				//将当前的  Schedule 对象，放到 List<Schedule> 中
				tlist.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist;
	}
	
	/**
	 * <p>根据URL地址，得到相应的  数据对象</p>
	 * <strong>适合根据 get 部分的链接地址，自动获取JSONObject 下的对应 key 的JSONArray 对象值</strong>
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param innerUpdateModule_otherInfo 更新方式和其他附加信息字符串
	 * @param partURL 部分链接地址
	 * @param url 完整链接地址和
	 * @return tlist 相应类型的数据对象
	 */
	public static <T> List<T> getTListByURL(String moduleName, String innerUpdateModule_otherInfo, String partURL, String url){
		//初始化对象
		List<T> tlist = new ArrayList<T>();
		
		try {
			//得到更新的模块名称
			String innerUpdateModule = CommonUtil.getInnerUpdateModuleName(innerUpdateModule_otherInfo);
			JSONArray tJsonArray = null;
			
			//球员详细信息模块
			if ("PLAYER_DETAIL".equals(innerUpdateModule)) {
				tJsonArray = getData2JsonArray(url, partURL);
			} else {
				//得到  jsonArray 对象
				tJsonArray = getJsonArray(url,partURL);
			}
			
			for (int i = 0; i < tJsonArray.length(); i++) {
				//得到一个 JSONObject 对象
				JSONObject jsonObject = (JSONObject)tJsonArray.get(i);
				
				T t = getEntityByJSONObject(jsonObject, moduleName, innerUpdateModule_otherInfo);
				
				//将当前的  Schedule 对象，放到 List<Schedule> 中
				tlist.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist;
	}
	
	/**
	 * 运用反射的机制，调用对应的方法，并返回相应的 实体类 对象
	 * @author 高青
	 * 2013-12-5
	 * @param <T> 动态实体类
	 * @param jsonObject 封装数据的JSONArray对象
	 * @param moduleName 模块名称
	 * @param innerUpdateModule_otherInfo 更新方式和其他附加信息
	 * @return T
	 */
	private static <T> T getEntityByJSONObject(JSONObject jsonObject, String moduleName, String innerUpdateModule_otherInfo) throws Exception {
		T t = null;
		//包的前缀名
		String packagePrefixName = CommonUtil.getPath("packagePrefixName");
		
		//将传递的 模块名称 的首字母改为大写
		String changedModuleName = moduleName.substring(0, 1).toUpperCase() + moduleName.substring(1);
		
		//调用的方法名称
		String invokeMethodName = "get" + changedModuleName;
		
		//最终的类的全路径
		String changedModuleNameUtil = changedModuleName + "Util";
		String finalClassPath = packagePrefixName + changedModuleNameUtil;
		
		Class<?> forNameClass = Class.forName(finalClassPath);
		
		//实例化对应的类
		t = (T) forNameClass.newInstance();
		
		//调用特定的方法
		Method getMethod = forNameClass.getMethod(invokeMethodName,  String.class, JSONObject.class);		
		t = (T) getMethod.invoke(t, innerUpdateModule_otherInfo, jsonObject);		
				
		return t;
	}
	
	/**
	 * 通过 URL 获取JSONObejct值，<br/>
	 * 并使用指定的 key 值，得到其相应的  jsonArray 对象
	 * @author 高青
	 * 2014-03-18
	 * @param url  json 字符串
	 * @param jsonObjectKey 自定义的获取 JSONArray 的 key 
	 * @return jsonArray JSONArray对象
	 */
	public static JSONArray getJsonArrayBySpecialKey(String url, String jsonObjectKey){
		//初始化数据
		JSONArray jsonArray = new JSONArray();
		
		try {
			//根据 url 地址，得到链接后的   json 数据
			String jsonData = URLContentUtil.getURLContent(url);
			
			//将 json 字符串转成JSONObject
			JSONObject jsonObject = new JSONObject(jsonData);
			
			//得到 JSONArray 对象的  key 值，其中  key 的值是    partURL 去掉  “Get” 的部分
			jsonArray = jsonObject.getJSONArray(jsonObjectKey);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return jsonArray;
	}
	
	/**
	 * 通过  字符串的 json 数据，得到其相应的  jsonArray 对象
	 * @author 高青
	 * 2013-12-2
	 * @param url  json 字符串
	 * @param partURL get部分的链接地址
	 * @return jsonArray JSONArray对象
	 */
	public static JSONArray getJsonArray(String url, String partURL){
		//初始化数据
		JSONArray jsonArray = new JSONArray();
		
		try {
			//根据 url 地址，得到链接后的   json 数据
			String jsonData = URLContentUtil.getURLContent(url);
			
			//将 json 字符串转成JSONObject
			JSONObject jsonObject = new JSONObject(jsonData);
			
			//得到 JSONArray 对象的  key 值，其中  key 的值是    partURL 去掉  “Get” 的部分
			String jsonArrayMarker = partURL.substring(3);
			jsonArray = jsonObject.getJSONArray(jsonArrayMarker);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return jsonArray;
	}
	
	/**
	 * 通过  字符串的 json 数据，得到其相应数据后，将其转为的  jsonArray 对象
	 * @author 高青
	 * 2013-12-2
	 * @param url  json 字符串
	 * @return jsonArray JSONArray对象
	 */
	public static JSONArray getData2JsonArray(String url, String partURL){
		//初始化数据
		JSONArray jsonArray = new JSONArray();
		
		try {
			//根据 url 地址，得到链接后的   json 数据
			String jsonData = URLContentUtil.getURLContent(url);
			
			//将 json 字符串转成JSONObject
			JSONObject jsonObject = new JSONObject(jsonData);
			
			//得到 JSONArray 对象的  key 值，其中  key 的值是    partURL 去掉  “Get” 的部分
			String jsonArrayMarker = partURL.substring(3);
			
			if (jsonObject.get(jsonArrayMarker) != null) {
				//将JSONObject 对象放到 JSONArray 中
				jsonArray.put(jsonObject.get(jsonArrayMarker));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return jsonArray;
	}
	
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
