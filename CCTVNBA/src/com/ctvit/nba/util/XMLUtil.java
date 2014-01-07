/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * XML操作常用类
 * @author 高青
 * 2013-12-3
 */
public class XMLUtil {
	
	/**日志对象*/
	private static Logger logger = Logger.getLogger(XMLUtil.class);
	
	/**
	 * 生成  XML 文件
	 * @author 高青
	 * 2013-12-3
	 * @param document Document对象
	 * @param filePath 当前  xml 存放的路径
	 * @return flag 成功标识（0：失败；1：成功） 
	 */
	public static int generateXMLFile(Document document, String fileFolder, String fileName){
		int flag = 0;
		//输出流
		FileOutputStream fileOutputStream = null;
		
		//设置 xml 输出格式
		Format format = Format.getPrettyFormat();
		//设置缩进的空格数
		format.setIndent("   ");
		
		XMLOutputter xmlOutputter = new XMLOutputter(format);
		
		try {
			//生成相应的文件输出流
			fileOutputStream = new FileOutputStream(getFile(fileFolder, fileName));
			
			//将数据写到 xml 文件中
			xmlOutputter.output(document, fileOutputStream);
			
			logger.info("将数据写到 xml 文件操作成功！");
			
			//写入数据成功
			flag = 1;
		} catch (FileNotFoundException e) {
			logger.info("将数据写到 xml 文件操作失败！");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("将数据写到 xml 文件操作失败！");
			e.printStackTrace();
		}finally{
			//关闭文件输出流
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	/**
	 * 得到指定路径的文件
	 * @author 高青
	 * 2013-12-3
	 * @param fileFolder 文件夹
	 * @param fileName xml文件名称
	 * @return file 实例后的 xml 文件
	 */
	public static File getFile(String fileFolder, String fileName){
		
		//生成当前文件夹
		File folder = new File(fileFolder);
		
		//首先判断文件夹是否存在
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
		//生成 文件
		File file = new File(fileFolder + fileName);
		
		return file;
	}
	
	/**
	 * 得到指定的文件夹
	 * @author 高青
	 * 2013-12-4
	 * @param key 得到文件夹的 key 值 
	 * @return folder 文件夹路径
	 */
	public static String getFileFolder(String key){
		//文件夹
		String folder = "";
		
		//得到 指定的文件夹路径
		folder = getPath(key);
		
		return folder;
	}
	
	/**
	 * 得到 xml 文件名
	 * @author 高青
	 * 2013-12-4
	 * @param season 赛季
	 * @param moduleName 模块名
	 * @param updateMethod 更新方式
	 * @return fileName xml文件名 
	 */
	public static String getFileName(String season, String moduleName, String updateMethod){
		String fileName = "";
		
		//得到  xml 的文件名
		fileName = "NBA_" + season + "_" + moduleName + "_" + updateMethod  + ".xml";
		
		return fileName;
	}
	
	/**
	 * 从当前  map 对象中，得到更新方式
	 * @author 高青
	 * 2013-12-4
	 * @param url_updateMethod_partURL_Map 当前链接地址和更新方式及部分链接地址的  Map 对象
	 * @return updateMethod 更新方式
	 */
	public static String getUpdateMethod(Map<String, Map<String, String>> url_updateMethod_partURL_Map){
		//更新方式
		String updateMethod = "";
		
		Set<String> keySet = url_updateMethod_partURL_Map.keySet();
		
		for (String string : keySet) {
			updateMethod = string;
		}
		return updateMethod;
	}
	
	/**
	 * 得到  map 对象中 的 partURL
	 * @author 高青
	 * 2013-12-5
	 * @param url_updateMethod_partURL_Map 当前链接地址和更新方式及部分链接地址的  Map 对象
	 * @return partURL 部分链接地址
	 */
	public static String getPartURL(Map<String, Map<String, String>> url_updateMethod_partURL_Map){
		String partURL = "";
		
		//得到更新方式
		String updateMethod = getUpdateMethod(url_updateMethod_partURL_Map);
		Map<String, String> map = url_updateMethod_partURL_Map.get(updateMethod);
		Set<String> keySet = map.keySet();
		
		for (String string : keySet) {
			partURL = string;
		}
		return partURL;
	}
	
	/**
	 * 得到完整的 url 地址
	 * @author 高青
	 * 2013-12-13
	 * @param url_updateMethod_partURL_Map 当前链接地址和更新方式及部分链接地址的  Map 对象
	 * @return completeURL 完整的url 地址
	 */
	public static String getCompleteURL(Map<String, Map<String, String>> url_updateMethod_partURL_Map){
		String completeURL = "";
		//得到更新方式
		String updateMethod = getUpdateMethod(url_updateMethod_partURL_Map);
		//得到部分地址链接
		String partURL = getPartURL(url_updateMethod_partURL_Map);
		//得到完整的 url 地址
		completeURL = url_updateMethod_partURL_Map.get(updateMethod).get(partURL);
		
		return completeURL;
	}
	
	/**
	 * 更新数据到 xml 文件中
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param updateMethod 更新方式
	 * @param childrenElementList 子元素节点集合
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public static int updateData2XML(String moduleName, String updateMethod, List<Element> childrenElementList){
		int xmlFlag = 0;
		
		//根元素
		Element root = new Element("Msg");
		root.setAttribute(moduleName + "Description", "更新数据");
		root.setAttribute("module", moduleName);
		root.setAttribute("updateMethod", updateMethod);
		
		
		//生成  document 对象，并设置好  根元素
		Document document = new Document(root);
		
		root.addContent(childrenElementList);
		
		//得到 按天更新赛程的文件夹路径及文件名
		String dayFolder = XMLUtil.getFileFolder(moduleName + "_" +  updateMethod + "_folder");
		String fileName = XMLUtil.getFileName("2013-2014", moduleName, updateMethod);
		
		//生成 XML 文件
		xmlFlag = XMLUtil.generateXMLFile(document, dayFolder, fileName);
		
		logger.info("生成XML文件成功！");
		
		return xmlFlag;
	}
	
	/**
	 * 根据URL地址，得到相应的  数据对象
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param updateMethod 更新方式
	 * @param partURL 部分链接地址
	 * @param url 完整链接地址和
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @return tlist 相应类型的数据对象
	 */
	public static <T> List<T> getTListByURL(String moduleName, String updateMethod, String partURL, String url, Map<String, T> tRemarkerAndParamsMap){
		//初始化对象
		List<T> tlist = new ArrayList<T>();
		
		try {
			//得到  jsonArray 对象
			JSONArray tJsonArray = getJsonArray(url,partURL);
			
			for (int i = 0; i < tJsonArray.length(); i++) {
				//得到一个 JSONObject 对象
				JSONObject jsonObject = (JSONObject)tJsonArray.get(i);
				
				T t = getEntityByJSONObject(jsonObject, tRemarkerAndParamsMap, moduleName, updateMethod);
				
				//将当前的  Schedule 对象，放到 List<Schedule> 中
				tlist.add(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tlist;
	}
	
	/**
	 * 通过  字符串的 json 数据，得到其相应的  jsonArray 对象
	 * @author 高青
	 * 2013-12-2
	 * @param url  json 字符串
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
	 * 运用反射的机制，调用对应的方法，并返回相应的 实体类 对象
	 * @author 高青
	 * 2013-12-5
	 * @param <T> 动态实体类
	 * @param jsonObject 封装数据的JSONArray对象
	 * @param tRemarkerAndParamsMap 实体类唯一标识和具体实体类封装的参数
	 * @param moduleName 模块名称
	 * @param updateMethod 更新方式
	 * @return T
	 */
	public static <T> T getEntityByJSONObject(JSONObject jsonObject, Map<String, T> tRemarkerAndParamsMap,
										String moduleName, String updateMethod) throws Exception {
		T t = null;
		//包的前缀名
		String packagePrefixName = getPath("packagePrefixName");
		
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
		Method getMethod = forNameClass.getMethod(invokeMethodName,  String.class, JSONObject.class, Map.class);		
		t = (T) getMethod.invoke(t, updateMethod, jsonObject, tRemarkerAndParamsMap);		
				
		return t;
	}
	
	/**
	 * 得到配置文件中的路径
	 * @author 高青
	 * 2013-12-5
	 * @param key 键的值
	 * @return path 路径地址
	 */
	public static String getPath(String key){
		String path = "";
		
		//得到指定名称下的路径地址
		ResourceBundle resourceBundle = ResourceBundle.getBundle("path");
		path = resourceBundle.getString(key);
		
		return path;
	}
	
}
