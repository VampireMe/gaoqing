/**
 * 0.0.0.1
 */
package com.common.controlexcel.test;

import com.common.controlexcel.Person;

/**
 * javabean 的测试类
 * @author gaoqing
 * 2014-7-4
 */
public class JavaBeanTest {

	/**
	 * 构造方法
	 */
	public JavaBeanTest() {
		
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-7-4
	 * @param args 参数字符串集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 1、得到指定的 javabean 对象，并将其地址指定到 Obejct 对象中
		 * 2、使用 javabean 的操作，得到其中所有的属性及属性值，将其分别装到两个集合中
		 */
		
		//1、得到指定的 javabean 对象，并将其地址指定到 Obejct 对象中
		Object javabean = new Person("gaoqing", 20, "beijing", 143);
		

	}

}
