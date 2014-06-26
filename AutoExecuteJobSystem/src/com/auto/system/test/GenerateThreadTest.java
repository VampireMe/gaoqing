/**
 * 0.0.0.1
 */
package com.auto.system.test;

import java.util.concurrent.LinkedBlockingQueue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.auto.system.GenerateThread;
import com.auto.system.Task;

/**
 * 生成线程类的测试类
 * @author 高青
 * 2014-5-21
 */
public class GenerateThreadTest {
	
	//实例化对象
	GenerateThread generateThread = new GenerateThread();
	
	/**
	 * 执行前的方法
	 * @author 高青
	 * 2014-5-21
	 * @return 空
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.auto.system.GenerateThread#generateTask()}.
	 */
	@Test
	public void testGenerateTask() {
		
		new Thread(new Runnable() {
			@Override
			public void run() {
				
				generateThread.generateTask();
				
			}
		}).start();
		
		System.out.println("暂停状态！");
		
		//一分钟后，停止线程
		try {
			Thread.sleep(10*1000);
			
			generateThread.setGenerateThreadRunning(false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("停止生成任务！");
		
		
		LinkedBlockingQueue<Task> taskList = generateThread.getTaskList();
		
		System.out.println(taskList.size());
		
	}
	
	
	
	
}
