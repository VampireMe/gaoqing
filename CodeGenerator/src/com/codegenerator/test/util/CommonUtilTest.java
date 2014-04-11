/**
 * 0.0.0.1
 */
package com.codegenerator.test.util;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

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
		
		String value = CommonUtil.getPropertiesValue("mysql");
		
		assertNotNull(value);
	}

}
