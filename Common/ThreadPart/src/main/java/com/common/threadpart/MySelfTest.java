/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 
 * @author Administrator
 * 2014-6-4
 */
public class MySelfTest {
	
	

	/**
	 * 构造方法
	 */
	public MySelfTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author gaoqing
	 * 2014-6-4
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		/** 共有堵塞队列 */
		final BlockingQueue queue = new LinkedBlockingQueue<Integer>(10);

		//1、产生线程池对象
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		threadPool.execute(new Runnable() {
			
			public void run() {
				int count = 0;
				try {
					while (queue.offer(Integer.valueOf(count), 2, TimeUnit.SECONDS)) {
						try {
							Thread.sleep(1000);
							count ++;
							System.out.println("开始添加了，当前添加的是：" + count);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		//从中取出数据
		threadPool.execute(new Runnable() {
			
			public void run() {
				Integer integer;
				try {
					while (queue.poll(2, TimeUnit.SECONDS) != null) {
						try {
							Thread.sleep(new Random().nextInt(500));
							integer = (Integer) queue.poll(2, TimeUnit.SECONDS);
							System.out.println("取出的值为：" + integer);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
