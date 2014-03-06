/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.json.JSONObject;

import com.sun.tracing.dtrace.ModuleName;

/**
 * 常用方法类
 * @author 高青
 * 2014-1-13
 */
public class CommonUtil {
	
	/**
	 * 得到更新条件（key）名称的字符串
	 * @author 高青
	 * 2014-2-27
	 * @param <T> 泛型类型
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleAConditionMap 内部更新模块和更新条件的 Map 对象
	 * @return String
	 */
	public static <T> String getUpdateConditionNameString(String moduleName, Map<String, Map<String, T>> innerUpdateModuleAConditionMap){
		//更新条件的字符串
		String keyConditions = "";
		
		//得到内部更新模块
		String innerUpdateModule = CommonUtil.getMapKey(innerUpdateModuleAConditionMap);
		
		//得到更新条件的 map 对象
		Map<String, T> updateConditionMap = innerUpdateModuleAConditionMap.get(innerUpdateModule);
		
		String partURL = URLUtil.getModulePartURL(moduleName).get(innerUpdateModule);
		
		//存在更新条件时
		if (updateConditionMap.size() != 0) {
			keyConditions = CommonUtil.getKeyConditions(innerUpdateModuleAConditionMap);
		}else {
			String deleteGetPart = partURL.substring(3, partURL.length());
			keyConditions = deleteGetPart.substring(0, 1).toLowerCase() + deleteGetPart.substring(1, deleteGetPart.length());
		}		
		return keyConditions;
	}
	
	/**
	 * 得到内部更新模块的名称
	 * @author 高青
	 * 2014-2-24
	 * @param innerUpdateModule_otherInfo 内部更新模块和其他信息字符串 
	 * @return innerUpdateModule 内部更新模块的名称
	 */
	public static String getInnerUpdateModuleName(String innerUpdateModule_otherInfo){
		//内部更新模块的名称
		String innerUpdateModule = "";
		
		if (innerUpdateModule_otherInfo != null && !innerUpdateModule_otherInfo.isEmpty()) {
			String[] array = innerUpdateModule_otherInfo.split(",");
			
			//数组中的第一个就是内部更新模块
			innerUpdateModule = array[0];
		}
		return innerUpdateModule;
	}
	
	/**
	 * 得到其他附加信息
	 * @author 高青
	 * 2014-2-24
	 * @param innerUpdateModule_otherInfo 内部更新模块和其他信息字符串 
	 * @return otherInfo 其他附加信息
	 */
	public static String getOtherInfo(String innerUpdateModule_otherInfo){
		//内部更新模块的名称
		String otherInfo = "";
		
		if (innerUpdateModule_otherInfo != null && !innerUpdateModule_otherInfo.isEmpty()) {
			String[] array = innerUpdateModule_otherInfo.split(",");
			
			//数组中的第一个就是内部更新模块
			otherInfo = array[1];
		}
		return otherInfo;
	}
	
	/**
	 * 根据 KEY 的值，得到 JSONObject 中的值，
	 * <p>并将其转为 String 类型</p>
	 * 默认情况（null）下，是 int 类型
	 * @author 高青
	 * 2014-2-11
	 * @param jsonObject JSONObject数据
	 * @param key 得到数据的 key 标识
	 * @param dataType 数据类型
	 * @return result 得到的 String 值
	 */
	public static String getStringValueByKey(JSONObject jsonObject, String key, String dataType){
		//返回的值
		String value = "";
		
		//判断 JSONObject 对象是否为空
		if (jsonObject != null) {
			//判断当前 key 对应的值是否为 null
			if (jsonObject.isNull(key)) {
				value = "empty";
			}else {
				
				//默认情况下，是 int 类型
				if (dataType == null) {
					value = Integer.toString(jsonObject.getInt(key));
					
				//类型是 int 时：
				} else{
					//取得数据类型的第一个字母
					String firstWordOfDataType = dataType.trim().substring(0, 1);	
					
					if (firstWordOfDataType.equals("i") || firstWordOfDataType.equals("I")) {
						value = Integer.toString(jsonObject.getInt(key));
						
					//类型是 double 时：
					}else if (firstWordOfDataType.equals("d") || firstWordOfDataType.equals("D")) {
						value = Double.toString(jsonObject.getDouble(key));
						
					//类型是 String 时：
					}else if (firstWordOfDataType.equals("s") || firstWordOfDataType.equals("S")) {
						value = jsonObject.getString(key);
					
					//类型是 long 时：
					}else if(firstWordOfDataType.equalsIgnoreCase("l")){
						value = String.valueOf(jsonObject.getLong(key));
					}
				}
			}
		}
		return value;
	}
	
	/**
	 * 处理当前项的值为 null 的情况
	 * @author 高青
	 * 2014-1-26
	 * @param jsonObject JSONObject数据
	 * @param key 得到数据的 key 标识
	 * @return result key对应的值
	 */
	public static String dealWithNull(JSONObject jsonObject, String key){
		String result = "";
		
		if (jsonObject != null) {
			//判断当前值是否为空
			if (jsonObject.isNull(key)) {
				result = "empty";
			}else {
				result = jsonObject.getString(key);
			}
		}
		return result;
	}
	
	/**
	 * 处理设置 Element 属性时，参数为空时产生的问题，并根据不同类型的参数，返回不同值
	 * @author 高青
	 * 2013-12-23
	 * @param <T> 泛型类型
	 * @param t 泛型参数
	 * @return result 处理后的结果
	 */
	public static <T> String resoleEmptyParam(T t){
		//返回的结果
		String result = "";
		
		/*
		 * 判断传递过来的参数的类型
		 */
		
		//为  String 类型时
		if (t instanceof String) {
			
			//判断当前值为空时
			if (t.equals("") || t== null) {
				result = "empty";
			
			//不为空时
			} else {
				result = (String) t;
			}
		}
		//为  Integer 类型时
		if (t instanceof Integer) {
			
			//当前值为空时
			if (t.equals("") || t.equals(null) || t == null) {
				result = "0";
			
			//不为空时
			} else {
				result = Integer.toString((Integer) t);
			}
		}else if (t instanceof Double) {
			//当前值为空时
			if (t.equals("") || t.equals(null) || t == null) {
				result = "0.0";
			
			//不为空时
			} else {
				result = String.valueOf(t);
			}
		}
		//当没有传递参数时，即不存在时
		if (t == null) {
			result = "empty";
		}
		return result;
	}
	
	/**
	 * <p>根据  key 的值，得到  JSONObject 对象中相应整数值 </p>
	 * 处理从 JSONObject 对象中取数值时的异常 
	 * @author 高青
	 * 2013-12-3
	 * @param jsonObject 封装数据的 JSONObject 对象
	 * @param key key的值
	 * @return value 根据 key 值得到的结果值
	 */
	public static Integer getIntegerValueByKey(JSONObject jsonObject, String key){
		int value = 0;
		
		//首先判断当前 key 的值是否为 Null
		if (jsonObject.isNull(key)) {
			value = 0;
		} else {
			//当前  key 下的值
			Object keyValue = jsonObject.get(key);
			
			//当 当前的值为 null 时，就赋予当前的 value = 0；否则，是其  keyValue 值
			if (keyValue == null || keyValue.toString().equals("null")) {
				value = 0;
			} else {
				value = (Integer)keyValue;
			}
		}
		return value;
	}
	
	/**
	 * <p>根据  key 的值，得到  JSONObject 对象中相应的 double 值 </p>
	 * 处理从 JSONObject 对象中取数值时的异常 
	 * @author 高青
	 * 2013-12-3
	 * @param jsonObject 封装数据的 JSONObject 对象
	 * @param key key的值
	 * @return value 根据 key 值得到的结果值
	 */
	public static Double getDoubleValueByKey(JSONObject jsonObject, String key){
		Double value = 0.0;
		
		//首先判断当前 key 的值是否为 Null
		if (jsonObject.isNull(key)) {
			value = 0.0;
		} else {
			//当前  key 下的值
			Object keyValue = jsonObject.get(key);
			
			//当 当前的值为 null 时，就赋予当前的 value = 0；否则，是其  keyValue 值
			if (keyValue == null || keyValue.toString().equals("null")) {
				value = 0.0;
			} else {
				value = (Double)keyValue;
			}
		}
		return value;
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
	
	/**
	 * 得到 Map 对象中的 key 值 
	 * @author 高青
	 * 2014-1-13
	 * @param uniqueRemarkerAndConditionMap 内部更新模块名称和查询条件map对象的集合数据
	 * @return key map对象的第一key的值
	 */
	public static<T> String getMapKey(Map<String, Map<String, T>> uniqueRemarkerAndConditionMap){
		String key = "";
		
		//计数变量
		int i = 0;
		
		if (uniqueRemarkerAndConditionMap.size() != 0) {
			Set<String> keySet = uniqueRemarkerAndConditionMap.keySet();
			for (String moduleName : keySet) {
				if (i == 0) {
					key = moduleName;
				}
				i++;
			}
		}
		return key;
	}
	
	/**
	 * 得到 Map 对象中的更新条件值的字符串集
	 * @author 高青
	 * 2014-1-14
	 * @param <T> 泛型类型
	 * @param innerUpdateModuleAConditionMap 内部更新模块名称和查询条件map对象的集合数据
	 * @return conditionRemarker 更新条件的字符串集
	 */
	public static <T> String getConditionRemarker(Map<String, Map<String, T>> innerUpdateModuleAConditionMap){
		//查询条件标识 
		String conditionRemarker = "";
		
		//得到更新模块标识
		String updateModuleName = getMapKey(innerUpdateModuleAConditionMap);
		
		//得到查询条的  Map 对象
		Map<String, T> conditionMap = innerUpdateModuleAConditionMap.get(updateModuleName);
		//判断 查询条件的  Map 对象是否为空
		if (conditionMap.size() == 0) {
			//当前查询条件为空的时候，说明当前访问链接中，就不存在查询条件，则查询条件标识就是当前的 链接标识（）
			conditionRemarker = "";
			
		//查询条件的  Map 对象不为空时：
		} else {
			Set<String> conditionKeySet = conditionMap.keySet();
			for (String condition : conditionKeySet) {
				//得到 查询条件的值
				T t = conditionMap.get(condition);
				conditionRemarker += t.toString() + "-";
			}
			//取消最后一个“-”
			conditionRemarker = conditionRemarker.substring(0, conditionRemarker.length() - 1);
		}
		
		return conditionRemarker;
	}
	/**
	 * 得到 Map 对象中的更新条件的字符串集
	 * @author 高青
	 * 2014-2-24
	 * @param <T> 泛型类型
	 * @param uniqueRemarkerAndConditionMap 内部更新模块名称和查询条件map对象的集合数据
	 * @return conditionRemarker 更新条件的字符串集
	 */
	public static <T> String getKeyConditions(Map<String, Map<String, T>> uniqueRemarkerAndConditionMap){
		//查询条件标识 
		String keyConditions = "";
		
		//得到更新模块标识
		String updateModuleName = getMapKey(uniqueRemarkerAndConditionMap);
		
		//得到查询条的  Map 对象
		Map<String, T> conditionMap = uniqueRemarkerAndConditionMap.get(updateModuleName);
		//判断 查询条件的  Map 对象是否为空
		if (conditionMap.size() == 0) {
			//当前查询条件为空的时候，说明当前访问链接中，就不存在查询条件，则查询条件标识就是当前的 链接标识（）
			
			
			//查询条件的  Map 对象不为空时：
		} else {
			Set<String> conditionKeySet = conditionMap.keySet();
			for (String condition : conditionKeySet) {
				keyConditions += condition + "-";
			}
		}
		//取消最后一个“-”
		keyConditions = keyConditions.substring(0, keyConditions.length() - 1);
		
		return keyConditions;
	}
	
	/**
	 * 从当前  map 对象中，得到内部更新模块名称
	 * @author 高青
	 * 2013-12-4
	 * @param finalURL 内部更新模块（链接地址）及get部分链接地址和 URL 的  Map 对象
	 * @return innerUpdateModule 更新方式
	 */
	public static String getInnerUpdateModule(Map<String, Map<String, String>> finalURL){
		//内部更新模块
		String innerUpdateModule = "";
		
		Set<String> keySet = finalURL.keySet();
		
		//计数器
		int countMachine = 0;
		
		for (String updateModule : keySet) {
			if(countMachine == 0){
				innerUpdateModule = updateModule;
			}
			countMachine++;
		}
		return innerUpdateModule;
	}
	
}
