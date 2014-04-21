/**
 * 0.0.0.1
 */
package com.codegenerator.test.util;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codegenerator.entity.Database;
import com.codegenerator.util.CommonUtil;

/**
 * 常用工具类的测试类
 * @author 高青
 * 2014-4-11
 */
public class CommonUtilTest {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(CommonUtilTest.class);
	
	/**
	 * 测试前的操作方法
	 * @author 高青
	 * 2014-4-11
	 * @return 空
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.codegenerator.util.CommonUtil#getPropertiesValue(java.lang.String)}.
	 */
	@Test
	public void testGetPropertiesValue() {
		String url = "localhost";
		String value = CommonUtil.getPropertiesValue("mysql");
		
		String replace = value.replace("${url}", url);
		
		log.info(value);
		log.info(replace);
		
		assertNotNull(value);
	}
	
	@Test
	public void testGetConnectionURL(){
		//Database database = new Database("mysql", "localhost", "3306", "root", "root", "myDatabase", null);
		Database.init("sqlserver", "localhost", "3306", "root", "root", "myDatabase");
		Database database =Database.getDatabase();
		
		String connectionURL = CommonUtil.getConnectionURL(database);
		
		log.info(connectionURL);
	}

}
