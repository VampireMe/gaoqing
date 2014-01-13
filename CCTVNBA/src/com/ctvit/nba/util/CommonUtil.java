/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.util.Map;
import java.util.Set;

/**
 * 常用方法类
 * @author 高青
 * 2014-1-13
 */
public class CommonUtil {
	
	/**
	 * 得到 Map 对象中的 key 值 
	 * @author 高青
	 * 2014-1-13
	 * @param uniqueRemarkerAndConditionMap 更新唯一标识和查询条件map对象的集合数据
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
	
	public static <T> String getConditionRemarker(Map<String, Map<String, T>> uniqueRemarkerAndConditionMap){
		//查询条件标识 
		String conditionRemarker = "";
		
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
				//得到 查询条件的值
				T t = conditionMap.get(condition);
				conditionRemarker += t.toString() + "_";
			}
		}
		//取消最后一个“_”
		conditionRemarker = conditionRemarker.substring(0, conditionRemarker.length() - 1);
		
		return conditionRemarker;
	}
	
	
}
