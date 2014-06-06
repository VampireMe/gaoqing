/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.Random;

/**
 * 轮流执行 10 次的线程
 * @author gaoqing
 * 2014-6-5
 */
public class Turn2Execute10 {

	/**
	 * 构造方法
	 */
	public Turn2Execute10() {
		
	}

	/**
	 * 主线程
	 * @author gaoqing
	 * 2014-6-5
	 * @param args
	 * @return void 空
	 */
	public static void main(String[] args) {
		
		final Turn2Execute10 turn2Execute10 = new Turn2Execute10();
		
		/*
		 * 1、实现子线程执行 10 次
		 * 2、实现主线程执行 10 次
		 */

		//1、实现子线程执行 10 次
		Thread sub = new Thread(new Runnable() {
			public void run() {
					
				for (int i = 0; i < 50; i++) {
					turn2Execute10.subThread();
				}
			}
		});
		
		sub.start();
		
		try {
			Thread.sleep(new Random().nextInt(1000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		turn2Execute10.mainThread();
		
	}
	
	/**
	 * 主线程的方法
	 * @author gaoqing
	 * 2014-6-5
	 * @return void 空
	 */
	public synchronized void mainThread(){
		//2、实现主线程执行 10 次
		for (int i = 0; i < 50; i++) {
			for (int j = 1; j < 101; j++) {
				System.out.println("主线程循环中，循环到：" + j);
				
				//当 j = 10时，释放当前的锁
				if (j == 100) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				//当 j = 9 时，唤醒等待的线程
				if (j == 99) {
					this.notify();
				}
			}
		}
	}
	
	/**
	 * 子线程的方法
	 * @author gaoqing
	 * 2014-6-5
	 * @return void 空
	 */
	public synchronized void subThread(){
		for (int j = 1; j < 11; j++) {
			System.out.println("子线程循环中，循环到：" + j);
			
			//当 j = 10时，释放当前的锁
			if (j == 10) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			//当 j = 9 时，唤醒等待的线程
			if (j == 9) {
				this.notify();
			}
		}
	}

}
