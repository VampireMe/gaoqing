/**
 * 0.0.0.1
 */
package com.auto.system;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author 高青
 * 2014-5-28
 */
public class ThreadPool {
	
	/** 默认线程大小 */
	private static final int DEFAULT_THREAD_NUM = 5;
	
	/** 最大线程大小数 */
	private static final int MAX_THREAD_NUM = 10;
	
	/** 最小线程数 */
	private static final int MIN_THREAD_NUM = 5;	
	
	/** 实例对象 */
	private ThreadPoolExecutor pool = null;

	/**
	 * 构造方法
	 * 2014-5-28
	 */
	public  ThreadPool() {
		
	}

	/**
	 * 初始化线程池
	 * @author 高青
	 * 2014-6-10
	 * @return void 空
	 */
	private void initializeThreadPool() {
		/*
		 * 初始化线程池的大小
		 */
		if (pool == null) {
			pool = new ThreadPoolExecutor(
					DEFAULT_THREAD_NUM, 
					MAX_THREAD_NUM, 
					2, 
					TimeUnit.SECONDS, 
					new SynchronousQueue<Runnable>()
					);
		}
	}

	/**
	 * @return the pool
	 */
	public ThreadPoolExecutor getPool() {
		//初始化线程池对象
		initializeThreadPool();
		
		return pool;
	}

	/**
	 * @param pool the pool to set
	 */
	public void setPool(ThreadPoolExecutor pool) {
		this.pool = pool;
	}
}
