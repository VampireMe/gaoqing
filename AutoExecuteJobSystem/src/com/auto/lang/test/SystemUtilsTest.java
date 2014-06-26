/**
 * 0.0.0.1
 */
package com.auto.lang.test;

import org.apache.commons.lang3.SystemUtils;

/**
 * 系统工具类测试类
 * @author 高青
 * 2014-5-29
 */
public class SystemUtilsTest {

	/**
	 * 构造方法
	 * 2014-5-29
	 */
	public SystemUtilsTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 主线程方法
	 * @author 高青
	 * 2014-5-29
	 * @param 参数字符集
	 * @return 空
	 */
	public static void main(String[] args) {
		System.out.println(SystemUtils.getJavaHome());
		System.out.println(SystemUtils.getJavaIoTmpDir());
		System.out.println(SystemUtils.getUserDir());
		System.out.println(SystemUtils.getUserHome());
		
		//----------------
		
		System.getProperties().list(System.out);
		

	}

}
