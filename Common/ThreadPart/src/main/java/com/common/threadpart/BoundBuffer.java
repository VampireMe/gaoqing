/**
 * 0.0.0.1
 */
package com.common.threadpart;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 堵塞队列类
 * @author gaoqing
 * 2014-6-5
 */
public class BoundBuffer {

	/**
	 * 构造方法
	 */
	public BoundBuffer() {
		
	}
	
	/** 锁对象 */
	private static final Lock LOCK = new ReentrantLock();
	
	/** 放的 Condition 对象 */
	private static Condition put = LOCK.newCondition();
	
	/** 取的 Condition 对象 */
	private static Condition take = LOCK.newCondition();
	
	/** 记录当前容器中的数量 */
	 private int count = 0; 
	 /** 放的数组下标 */
	 private int putIndex = 0;
	 /** 取的数组下标 */
	 private int takeIndex = 0;
	 
	 /** 共有的容器 */
	 private Object[] container = new Object[100];
	 
	 /**
	  * 向容器中放数据
	  * @author gaoqing
	  * 2014-06-05
	  * @param obj 要存放的数据
	  */
	 public void putData(Object obj){
		 //同步当前添加数据操作
		 LOCK.lock();
		 
		 try{
			 //当前容器满的时候，就挂起当前线程
			 while (count == container.length) {
				
				 try {
					put.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			 //向容器中添加数据
			 container[putIndex] = obj;
			 
			 //递增下标
			 putIndex++;
			 //当容器放满后，要从头开始放
			 if (putIndex == container.length) {
				 putIndex = 0;
			 }
			 //记录当前容器的容量
			 count++;
			 
			 //添加完后，容器中就有了数据，就去唤醒等待取的线程
			 take.signal();
		 }finally{
			 LOCK.unlock();
		 }
	 }
	 
	 /**
	  * 从容器中取数据
	  * @author gaoqing
	  * 2014-06-05
	  * @return takeObj 取出的数据
	  */
	 public Object takeData(){
		 LOCK.lock();
		 
		 try{
			 //当容器中，没有数据时，则挂起线程，等待数据
			 while (count == 0) {
				 try {
					 take.await();
				 } catch (InterruptedException e) {
					 e.printStackTrace();
				 }
			 }
			 //取出头部的数据
			 Object takeObj = container[takeIndex];
			 
			 //递增取出的下标
			 takeIndex ++;
			 
			 //如果取到了容器的底部，那么就从头开始取
			 if (takeIndex == container.length) {
				 takeIndex = 0;
			 }
			 count --;
			 
			 //唤醒放的线程
			 put.signal();
			 
			 return takeObj;
		 }finally{
			 LOCK.unlock();
		 }
	 }

}
