/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.util.ResourceBundle;

/**
 * 常用工具类
 * @author 高青
 * 2014-4-11
 */
public class CommonUtil {

	/**
	 * 构造方法
	 * 2014-4-11
	 */
	private CommonUtil() {
		
	}
	
	/**
	 * 得到数据库连接池配置文件中的 value 值
	 * @author 高青
	 * 2014-4-11
	 * @param key 配置文件中的键
	 * @return value 键对应的值
	 */
	public static String getPropertiesValue(String key){
		//配置文件中的 key 对应的 value 值
		String value = "";
		
		//得到资源簇
		ResourceBundle bundle = ResourceBundle.getBundle("connection_pool.properties");
		
		//得到 key 对应的值
		value = bundle.getString(key);
		
		return value;
	}

}
