/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 堵塞队列的使用
 * @author gaoqing
 * 2014-6-3
 */
public class BlockQueueTest {
	
	/** 共有堵塞队列 */
	private static BlockingQueue queue = new ArrayBlockingQueue(10, true);

	/**
	 * 默认构造方法
	 * @author gaoqing
	 */
	public BlockQueueTest() {
		
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-3
	 * @param args 参数字符集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 1、产生线程池对象
		 * 2、向线程池中添加生产任务
		 * 3、向线程池中添加消费任务
		 */
		
		//1、产生线程池对象
		ExecutorService threadPool = Executors.newCachedThreadPool();
		
		//2、向线程池中添加生产任务
		Productor productor_01 = new Productor(queue);
		Productor productor_02 = new Productor(queue);
		Productor productor_03 = new Productor(queue);
		Productor productor_04 = new Productor(queue);
		Productor productor_05 = new Productor(queue);
		
		threadPool.execute(productor_01);
		threadPool.execute(productor_02);
		threadPool.execute(productor_03);
		threadPool.execute(productor_04);
		threadPool.execute(productor_05);
		
		//3、向线程池中添加消费任务
		Consumer consumer_01 = new Consumer(queue);
		Consumer consumer_02 = new Consumer(queue);
		threadPool.execute(consumer_01);
		threadPool.execute(consumer_02);
		
	}

	/**
	 * 生产者类
	 * @author gaoqing
	 * 2014-6-3
	 */
	static class Productor implements Runnable{
		
		/** 堵塞队列对象 */
		private BlockingQueue queue;

		/**
		 * 默认构造方法
		 * @author gaoqing
		 */
		public Productor() {
			
		}
		
		/**
		 * 构造方法
		 * @author gaoqing
		 * @param queue 堵塞队列对象
		 */
		public Productor(BlockingQueue queue) {
			super();
			this.queue = queue;
		}

		public void run() {
			/*
			 *  1、顺序的向队列中，添加任务
			 */
			System.out.println("线程 " + Thread.currentThread().getName() + "开始向队列中添加任务了！当前的队列中，数据有：" + queue.size());
			
			//当前队列的容量数
			AtomicInteger curcount = new AtomicInteger();
			while (queue.size() != 10 && curcount.get() < 3) {  //表示当前队列已经放满了
				
				try {
					//向队列中添加任务（当前队列的数量）
					queue.put(Integer.valueOf(curcount.getAndIncrement()));
					
					System.out.println("线程 " + Thread.currentThread().getName() + "当前队列中，添加的任务数到达：" + curcount.get());
					
				} catch (InterruptedException e) {
					System.out.println("线程 " + Thread.currentThread().getName() + "向队列中插入任务，发生异常！");
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 消费者类
	 * @author gaoqing
	 * 2014-6-3
	 */
	static class Consumer  implements Runnable{
		
		/** 堵塞队列对象 */
		private BlockingQueue queue;
		
		/**
		 * 默认构造方法
		 * @author gaoqing
		 */
		public Consumer() {
			super();
		}

		/**
		 * 构造方法
		 * @author gaoqing
		 * @param queue 堵塞队列对象
		 */
		public Consumer(BlockingQueue queue) {
			super();
			this.queue = queue;
		}

		public void run() {
			/*
			 * 1、从队列中取出数据
			 */
			System.out.println("线程 " + Thread.currentThread().getName() + "开始从队列中，取出任务了！当前队列的数据量为：" + queue.size());
			
			//每次只取一次
			try {
				Integer take = (Integer) queue.take();
				
				System.out.println("线程 " + Thread.currentThread().getName() + "当前取出的任务为：" + take.intValue());
				
			} catch (InterruptedException e) {
				System.out.println("线程 " + Thread.currentThread().getName() + "取出任务发生异常！");
				e.printStackTrace();
			}
		}
	}
	
}

















