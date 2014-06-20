/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

import com.codegenerator.entity.Database;

/**
 * 常用工具类
 * @author 高青
 * 2014-4-11
 */
public class CommonUtil {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(CommonUtil.class);
	
	/** common.properties 配置文件对象 */
	private static ResourceBundle resourceBundle = ResourceBundle.getBundle("common");

	/**
	 * 构造方法
	 * 2014-4-11
	 */
	private CommonUtil() {
		
	}
	
	
	/**
	 * 根据 key 的值，得到指定的 properties 文件中的 值
	 * @author 高青
	 * 2014-6-19
	 * @param key 参数 key 
	 * @return value key 对应的值
	 */
	public static String getPropertiesValeByKey(String key){
		String value = "";
		
		try {
			value = resourceBundle.getString(key);
		} catch (Exception e) {
			log.info("得到当前 " + key + " 的 value 时，发生异常！");
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 
	 * 得到 Spring.xml 文件中的制定 bean 的值
	 * @author 高青
	 * 2014-5-7
	 * @param bean
	 * @return object 制定 bean 的值
	 */
	public static Object getBeanObj(String bean){
		Object object = null;
		
		//1、当 xml 文件存放在 WEB-INF 下时，使用此方法
		BeanFactory beanFactory = new FileSystemXmlApplicationContext("WebContent/WEB-INF/codeGenerator-servlet.xml");
		object = beanFactory.getBean(bean);
		
		//2、当 xml 文件存放在 classpath 下时，使用
		/*ApplicationContext context = new ClassPathXmlApplicationContext("codeGenerator-servlet.xml");
		object = context.getBean(bean);*/
		
		return object;
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
