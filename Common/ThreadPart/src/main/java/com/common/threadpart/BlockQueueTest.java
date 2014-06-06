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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * 堵塞队列的使用
 * @author gaoqing
 * 2014-6-3
 */
public class BlockQueueTest {
	
	/** 共有堵塞队列 */
	private static BlockingQueue<Integer> queue = new LinkedBlockingQueue<Integer>(10);
	


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
		Productor productor_01 = new Productor(queue );
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
		//Consumer consumer_02 = new Consumer(queue);
		threadPool.execute(consumer_01);
		//threadPool.execute(consumer_02);
		
		
		//等待生产线程和消费线程运行 5s 后，停止生产和消费行为
		try {
			Thread.sleep(5*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		productor_01.stop();
		productor_02.stop();
		productor_03.stop();
		productor_04.stop();
		productor_05.stop();
		
		consumer_01.stop();
		
		//等待 5s 后，停止线程池接受线程任务
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		threadPool.shutdown();
		
	}

	/**
	 * 生产者类
	 * @author gaoqing
	 * 2014-6-3
	 */
	static class Productor implements Runnable{
		
		/** 堵塞队列对象 */
		private BlockingQueue queue;
		
		//当前队列的容量数
		private static AtomicInteger curcount = new AtomicInteger();
		
		private boolean isRunning = true;

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
		 * @param curcount 添加统计数
		 */
		public Productor(BlockingQueue queue) {
			super();
			this.queue = queue;
		}

		public void run() {
			/*
			 *  1、顺序的向队列中，添加任务
			 */
			System.out.println("线程 " + Thread.currentThread().getName() + "开始向队列中添加任务了！在为添加前，队列中的数据有：" + queue.size());
				
				try {
					while (isRunning) {  
						try {
							Thread.sleep(new Random().nextInt(1000));
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
					//向队列中添加任务（当前队列的数量）
					queue.put(Integer.valueOf(curcount.incrementAndGet()));
					
					System.out.println("线程 " + Thread.currentThread().getName() + "已经添加完了任务，当前队列中，添加的任务到：" + curcount.get());
					}
				} catch (InterruptedException e) {
					System.out.println("线程 " + Thread.currentThread().getName() + "向队列中插入任务，发生异常！");
					e.printStackTrace();
				}finally{
					System.out.println("生产线程停止了！");
				}
		}
		
		/**
		 * 停止当前线程
		 */
		public void stop(){
			isRunning = false;
		}
	}
	
	/**
	 * 消费者类
	 * @author gaoqing
	 * 2014-6-3
	 */
	static class Consumer  implements Runnable{
		
		/** 堵塞队列对象 */
		private BlockingQueue<Integer> queue;
		
		/**
		 * 默认构造方法
		 * @author gaoqing
		 */
		public Consumer() {
			super();
		}
		
		/** 启动线程标识 */
		private boolean isRunning = true;

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
			
			//当2秒后，仍然没有取到任务，则表明添加任务的操作已经停止
			try {
				while (isRunning) {
					
					Thread.sleep(new Random().nextInt(1000));
					
					/*
					 * 1、从队列中取出数据
					 */
					System.out.println("消费线程 " + Thread.currentThread().getName() + "开始从队列中，取出任务了！当前队列的数据量为：" + queue.size());
					
					//每次只取一次
					try {
						Integer take = queue.poll(2, TimeUnit.SECONDS);
						
						//两秒之后，如果取到值，则继续进行取值，如果取不到，说明当前队列中，没有值了，停止当前消费的操作
						if (take != null) {
							System.out.println("消费线程 " + Thread.currentThread().getName() + "当前取出的任务为：" + take.intValue());
						}else {
							stop();
						}
					} catch (Exception e) {
						System.out.println("消费线程 " + Thread.currentThread().getName() + "取出任务发生异常！");
						e.printStackTrace();
					}
				}
			} catch (InterruptedException e1) {
				System.out.println("消费线程 " + Thread.currentThread().getName() + "取出任务发生异常！");
				e1.printStackTrace();
			}finally{
				System.out.println("消费线程停止了！");
			}
		}
		
		public void stop(){
			isRunning = false;
		}
		
	}
	
}





