/**
 * 0.0.0.1
 */
package com.common.controlxml;


/**
 * 常用工具类包
 * @author gaoqing
 * 2014-7-1
 */
public class CommonUtils {
	
	/**
	 * 构造方法
	 */
	public CommonUtils() {
		
	}
	
	/**
	 * 将传入的值，转为 String 类型的值
	 * @author gaoqing
	 * 2014-7-1
	 * @param originValue 原始的值
	 * @param emptyReplaceStr 原始值为空（null、""）时，替换的字符串
	 * @return switchedValue 转换为 String 类型后的值
	 */
	public static String switch2String(Object originValue, String emptyReplaceStr){
		//转换为 String 类型后的值
		String switchedValue = "";
		
		/*
		 * 1、首先判断原始的值，是否为空
		 * 	1、1、如果为空，则替换为制定的字符
		 * 	1.2、如果不为空，则将其转为 String 类型
		 */
		
		//1、首先判断原始的值，是否为空
		if (originValue == null) {
			switchedValue = emptyReplaceStr;
			
			return switchedValue;
		}else {
			// 将 originValue 转为 String 类型 
			String beforeSwitchStr = originValue.toString();
			
			if (beforeSwitchStr.equals("")) {
				switchedValue = emptyReplaceStr;
				
				return switchedValue;
			}else {
				switchedValue = beforeSwitchStr;
				
				return switchedValue;
			}
		}
	}
	
	
	
	

}
