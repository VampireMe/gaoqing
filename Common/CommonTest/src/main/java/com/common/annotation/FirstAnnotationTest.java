/**
 * 0.0.0.1
 */
package com.common.annotation;

/**
 * 第一个注解的处理类
 * @author gaoqing
 * 2014-6-22
 */
public class FirstAnnotationTest {

	/**
	 * 构造方法
	 */
	public FirstAnnotationTest() {
		
	}
	
	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-22
	 * @param args 字符串参数集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 根据 Target 不同，根据反射，得到对应的对象（Class、Method、Constructor、Field）
		 */
		
		FirstAnnotation annotation = FirstAnnotationUse.class.getAnnotation(FirstAnnotation.class);
		
		System.out.println(annotation.value());;
		
		
	}

}
