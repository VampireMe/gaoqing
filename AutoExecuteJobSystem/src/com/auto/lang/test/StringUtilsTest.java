/**
 * 0.0.0.1
 */
package com.auto.lang.test;

import org.apache.commons.lang3.StringUtils;

import static java.lang.Math.*;

/**
 * StringUtils 的测试类
 * @author 高青
 * 2014-5-29
 */
public class StringUtilsTest {

	/**
	 * 构造方法
	 * 2014-5-29
	 */
	public StringUtilsTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 主线程方法
	 * @author 高青
	 * 2014-5-29
	 * @param args 字符串参数集
	 * @return 空
	 */
	public static void main(String[] args) {
		String strip = StringUtils.strip("world", "o");
		
		String join = StringUtils.join("wor", "ld");
		
		System.out.println(join);
		
		System.out.println(max(3, 5));
		
		System.out.println(strip);

	}

}
