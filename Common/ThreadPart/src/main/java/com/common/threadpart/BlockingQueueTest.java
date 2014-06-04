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

/**
 * 
 * @author Administrator
 * 2014-6-4
 */
public class BlockingQueueTest {

	/**
	 * 构造方法
	 */
	public BlockingQueueTest() {
	}

	/**
	 * 
	 * @author gaoqing
	 * 2014-6-4
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		 // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);
 
        Producer producer1 = new Producer(queue);
        Producer producer2 = new Producer(queue);
        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);
 
        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();
        // 启动线程
        service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);
        service.execute(consumer);
 
        // 执行10s
        try {
			Thread.sleep(10 * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        producer1.stop();
        producer2.stop();
        producer3.stop();
 
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        // 退出Executor
        service.shutdown();

	}
	
	static class Consumer implements Runnable{
	    public Consumer(BlockingQueue<String> queue) {
	        this.queue = queue;
	    }
	 
	    public void run() {
	        System.out.println("启动消费者线程！");
	        Random r = new Random();
	        boolean isRunning = true;
	        try {
	            while (isRunning) {
	                System.out.println("正从队列获取数据...");
	                String data = queue.poll(2, TimeUnit.SECONDS);
	                if (null != data) {
	                    System.out.println("拿到数据：" + data);
	                    System.out.println("正在消费数据：" + data);
	                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
	                } else {
	                    // 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
	                    isRunning = false;
	                }
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	            Thread.currentThread().interrupt();
	        } finally {
	            System.out.println("退出消费者线程！");
	        }
	    }
	 
	    private BlockingQueue<String> queue;
	    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;
	}
	
	static class Producer implements Runnable{
		  public Producer(BlockingQueue queue) {
		        this.queue = queue;
		    }
		 
		    public void run() {
		        String data = null;
		        Random r = new Random();
		 
		        System.out.println(Thread.currentThread().getName() + "启动生产者线程！");
		        try {
		            while (isRunning) {
		                System.out.println(Thread.currentThread().getName() + "正在生产数据...");
		                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
		 
		                data = "data:" + count.incrementAndGet();
		                System.out.println(Thread.currentThread().getName() + "将数据：" + data + "放入队列...");
		                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
		                    System.out.println("放入数据失败：" + data);
		                }
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		            Thread.currentThread().interrupt();
		        } finally {
		            System.out.println("退出生产者线程！");
		        }
		    }
		 
		    public void stop() {
		        isRunning = false;
		    }
		 
		    private volatile boolean      isRunning               = true;
		    private BlockingQueue queue;
		    private static AtomicInteger  count                   = new AtomicInteger();
		    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;	
	}

}
