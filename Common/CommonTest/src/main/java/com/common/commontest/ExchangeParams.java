/**
 * 0.0.0.1
 */
package com.common.commontest;

/**
 *交换两个变量
 * @author gaoqing
 * 2014-6-5
 */
public class ExchangeParams {

	/**
	 * 构造方法
	 */
	public ExchangeParams() {
		
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-5
	 * @param args 字符串参数
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 不通过第三方变量，交换 a,b的值
		 */
		int a = 1;
		int b = 2;


		a = a+b;
		b = a-b;
		a = a-b;
		
		System.out.println(a);
		System.out.println(b);

	}

}
