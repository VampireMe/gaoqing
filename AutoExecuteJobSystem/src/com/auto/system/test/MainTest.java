/**
 * 0.0.0.1
 */
package com.auto.system.test;

import java.util.concurrent.ThreadPoolExecutor;

import com.auto.system.GenerateThread;
import com.auto.system.TaskTypeEnum;
import com.auto.system.ThreadPool;

/**
 * 主测试类
 * @author 高青
 * 2014-6-10
 */
public class MainTest {

	/**
	 * 构造方法
	 * 2014-6-10
	 */
	public MainTest() {
		super();
	}

	/**
	 * 主线程方法
	 * @author 高青
	 * 2014-6-10
	 * @param args  字符串参数集
	 * @return void 空
	 */
	public static void main(String[] args) {
		/*
		 * 1、实例化 GenerateThread 对象
		 * 2、得到线程池
		 * 3、执行生成任务的方法
		 * 4、执行三种不同的执行任务
		 */
		
		//1、实例化 GenerateThread 对象
		final GenerateThread generateThread = new GenerateThread();
		
		//2、得到线程池
		ThreadPool threadPool = new ThreadPool();
		ThreadPoolExecutor pool = threadPool.getPool();
		
		//3、执行生成任务的方法
		pool.execute(new Runnable() {
			@Override
			public void run() {
				generateThread.generateTask();
			}
		});
		
		//1 分钟后，停止生成线程
		try {
			Thread.sleep(10*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		generateThread.setGenerateThreadRunning(false);
		
		//4、执行三种不同的执行任务
		//（1）查询任务
		pool.execute(new Runnable() {
			@Override
			public void run() {
				generateThread.executeTask(TaskTypeEnum.SELECT);
			}
		});
		
		//（2）提交任务
		pool.execute(new Runnable() {
			@Override
			public void run() {
				generateThread.executeTask(TaskTypeEnum.COMMIT);
			}
		});
		
		//（3）同步任务
		pool.execute(new Runnable() {
			@Override
			public void run() {
				generateThread.executeTask(TaskTypeEnum.TOGETHER);
			}
		});
		
		// 3 分钟后，停止线程
		try {
			Thread.sleep(2*60*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pool.shutdown();
	}
}
