/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 交替执行线程
 * （如：
 * 1-2-A-B-3-4-C-D-5-6-E-F-7-8-G-H-9-10-I-J-11-12-K-L-
 * 13-14-M-N-15-16-O-P-17-18-Q-R-19-20-S-T-21-22-U-V-
 * 23-24-W-X-25-26-Y-Z-27-28-29-30-31-32-33-34-35-36-
 * 37-38-39-40-41-42-43-44-45-46-47-48-49-50-51-52-
 * ）
 * @author gaoqing
 * 2014-6-5
 */
public class Turn2Execute {

	/**
	 * 构造方法
	 */
	public Turn2Execute() {
		 
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-5
	 * @param 参数字符串集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 1、得到一个线程池对象
		 * 2、得到 ConcreteExecutor 对象
		 * 3、将具体执行的操作，放到线程池中，启动两个线程，进行并发执行
		 * 4、线程执行 10 秒后,自动停止线程池中的操作
		 */
		
		//1、得到一个线程池对象
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		//2、得到两个 ConcreteExecutor 对象
		final ConcreteExecutor executors = new ConcreteExecutor();
		
		//3、将具体执行的操作，放到线程池中，启动两个线程，进行并发执行
		pool.execute(new Runnable() {
			public void run() {
				executors.printNum();
			}
		});
		
		pool.execute(new Runnable() {
			public void run() {
				executors.printWord();
			}
		});
		
		//4、线程执行 10 秒后,自动停止线程池中的操作
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
	
	/**
	 * 内部类
	 * 具体的执行类
	 * @author gaoqing
	 * 2014-6-5
	 */
	public static class ConcreteExecutor{
		
		/**
		 * 打印 1~52 的数字
		 * @author gaoqing
		 * 2014-6-5
		 * @return void 空
		 */
		public synchronized void printNum(){
			int count = 1;
			int num = 1;
			while (count != 53) {
				//当字母的操作执行完后，就不让出锁了，直接执行当前的操作
				if (word != 91) {
					//当前线程执行两次后，让出 cpu ，让出锁，挂起线程
					if (num == 3) {
						//重置计数器
						num = 1;
						
						try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				
				try {
					Thread.sleep(new Random().nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.print(count + "-");
				
				count++;
				
				//当前线程执行两遍的时候，唤醒其他的等待线程
				if (num == 2) {
					//唤醒其他等待的线程
					notify();
				}
				num++;
			}
		}
		char word = 65;
		/**
		 * 打印 A-Z 的字母
		 * @author gaoqing
		 * 2014-6-5
		 * @return void 空
		 */
		public synchronized void printWord(){
			int num = 1;
			while (word != 91) {
				
				//当前线程执行两次后，让出 cpu ，让出锁，挂起线程
				if (num == 3) {
					//重置计数器
					num = 1;
					
					try {
						
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				try {
					Thread.sleep(new Random().nextInt(1000));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.print(word + "-");
				
				word ++;
				
				//当前线程执行两遍的时候，唤醒其他的等待线程
				if (num == 2 || word == 91) {
					//唤醒其他等待的线程
					notify();
				}				
				num++;
			}
			//确保当前线程执行完后，让出锁
			if (word == 91) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
