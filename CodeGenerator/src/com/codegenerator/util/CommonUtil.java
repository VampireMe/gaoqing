/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.codegenerator.entity.Database;

/**
 * 常用工具类
 * @author 高青
 * 2014-4-11
 */
public class CommonUtil {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(CommonUtil.class);

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
		ResourceBundle bundle = ResourceBundle.getBundle("connection_pool");
		
		//得到 key 对应的值
		value = bundle.getString(key);
		
		return value;
	}
	
	/**
	 * 得到数据库连接地址
	 * @author 高青
	 * 2014-4-15
	 * @param database 数据库连接实体对象
	 * @return url 数据库连接地址
	 */
	public static String getConnectionURL(Database database){
		//连接数据库的地址
		String url = "";
		
		if (database != null) {
			if (
					database.getDatabaseType() != null && 
					database.getUrl() != null && 
					database.getPort() != null && 
					database.getDatabaseName() != null
				) {
				//得到配置文件中的数据库连接地址
				String settingURL = CommonUtil.getPropertiesValue(database.getDatabaseType());
				
				//将连接中的 ${url}, ${port}, ${databaseName} 替换成指定的值
				url = settingURL.replace("${url}", database.getUrl()).
						replace("${port}", database.getPort()).
						replace("${databaseName}", database.getDatabaseName());
			}else {
				log.info("数据库实体对象的参数绑定有误！");
			}
		}else {
			log.info("数据库实体对象不能为空！");
		}
		return url;
	}

}
