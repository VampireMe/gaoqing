/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步的测试类
 * @author Administrator
 * 2014-6-4
 */
public class SynchronizedTest {

	/**
	 * 构造方法
	 */
	public SynchronizedTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-4
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		Thread thread1 = new Thread(new InnerClass());
		Thread thread2 = new Thread(new InnerClass());

		thread1.start();
		thread2.start();
	}
	
	static Lock lock = new ReentrantLock();
	
	public static class InnerClass implements Runnable{
		int count = 0;
		
		public void run() {
			
			while (true) {
					lock.lock();
					try {
						System.out.println(Thread.currentThread().getName() + "开始了！");
						try {
							Thread.sleep(new Random().nextInt(1000));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						count++;
						System.out.println(count + "\t" + Thread.currentThread().getName() + "结束了！");
						
					} finally{
						lock.unlock();
					}
			}
		}
		
		
	}

}
