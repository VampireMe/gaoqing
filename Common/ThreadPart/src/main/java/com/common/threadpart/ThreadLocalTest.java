/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 测试类
 * @author gaoqing
 * 2014-6-6
 */
public class ThreadLocalTest {
	
	/** 声明 ThreadLocal 对象 */
	private static ThreadLocal<Integer> local = new ThreadLocal<Integer>(){

		/* (non-Javadoc)
		 * @see java.lang.ThreadLocal#initialValue()
		 */
		@Override
		protected Integer initialValue() {
			return age;
		}
	};
	
	public static Integer getInt(){
		return local.get();
	}
	
	static int age = 20;

	/**
	 * 构造方法
	 */
	public ThreadLocalTest() {
		
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-6
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		ExecutorService pool = Executors.newFixedThreadPool(2);
		final ThreadLocalTest test = new ThreadLocalTest();
		
		final class01 c01 = new class01();
		final class02 c02 = new class02();
		
		pool.execute(new Runnable() {
			
			public void run() {
				c01.changeAge();
			}
		});
		
		pool.execute(new Runnable() {
			
			public void run() {
				
				c02.changeAge();
				
			}
		});
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		pool.shutdown();
		
	}
	
	static class class01{
		public void changeAge(){
			
			int int01 = getInt();
			
			System.out.println("01 中的当前 age 值为：" + ++int01);
		}
	}
	
	static class class02{
		public void changeAge(){
			int int02 = getInt();
			
			System.out.println("02 中的当前 age 值为：" + ++int02);
		}
	}
	
	

}
