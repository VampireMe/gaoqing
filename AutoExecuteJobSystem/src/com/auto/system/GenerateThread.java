/**
 * 0.0.0.1
 */
package com.auto.system;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;

/**
 * 生成线程的类
 * @author 高青
 * 2014-5-20
 */
public class GenerateThread {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(GenerateThread.class);
	
	/** 任务集和（堵塞队列） */
	private LinkedBlockingQueue<Task> taskList;
	
	/** 任务集 中是否还有任务 */
	private boolean isExistTask = true;
	
	/** 取到堵塞队列中下标数 */
	private int takeIndex = 0;

	/** 线程是否运行的标识 */
	private boolean isGenerateThreadRunning = true;
	
	/** 当前对象的锁 */
	private Lock lock = new ReentrantLock();
	
	/** SELECT 类型的 Condition 对象 */
	private Condition selectTypeCondition = lock.newCondition();
	
	/** COMMIT 类型的 Condition 对象 */
	private Condition commitTypeCondition = lock.newCondition();
	
	/** TOGETHER 类型的 Condition 对象 */
	private Condition togetherTypeCondition = lock.newCondition();
	
	/**
	 * 默认构造方法
	 * 2014-5-20
	 */
	public GenerateThread() {
		
	}
	
	/**
	 * 执行任务
	 * @author 高青
	 * 2014-6-10
	 * @param taskTypeEnum 任务类型
	 * @return void 空
	 */
	public void executeTask(TaskTypeEnum taskTypeEnum){
		switch (taskTypeEnum) {
		case SELECT:
			executeSelectTask();
			break;
		case COMMIT:
			executeCommitTask();
			break;
		default:
			try {
				executeTogetherTask();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	/**
	 * 执行 TOGETHER 类型的任务
	 * @author 高青
	 * 2014-6-10
	 * @return void 空
	 */
	private void executeTogetherTask() throws Exception{
		/*
		 * 在执行时，每执行三个任务，成功2个，失败一个（抛RuntimeException).
		 * 失败的任务必须在规定的时间后被重新执行，只在第三次执行这个任务时成功。
		 * 成功执行的任务，打印“同步任务”+执行到第几条。 
		 */
		
		int togetherAwaitCount = 1;
		lock.lock();
		while (isExistTask) {
			
			if (togetherAwaitCount == 2) {
				togetherAwaitCount = 1;
				
				//挂起线程
				togetherTypeCondition.await();
			}
			
			Thread.sleep(500);
			
			try{
				log.info("......同步任务开始执行......");
				
				//随机发生异常的次数（确保只能发生一个）
				int randomExceptionCount = 1;
				
				for (int i = 0; i < 3; i++) {
					Task task = taskList.poll();
					
					//判断队列中，是否还有任务
					if (task == null) {
						log.info("在执行同步任务时，当前队列中，没有可取的任务了！");
						isExistTask = false;
					}else {
						
						try {
							
							if (randomExceptionCount == 1) {
								
								//产生一个 0-2 的随机数，当当前随机数与当前循环数 i 相同时，抛出 RuntimeException 异常
								if (new Random().nextInt(3) == i) {
									//记录异常发生的次数
									randomExceptionCount++;
									
									throw new RuntimeException("执行同步任务，发生异常！当前执行的是第 " + i + "个！");
								}
							}
							
							//设置任务状态为成功，记录成功时间
							task.setExecuteStatus(1);
							task.setSuccess(true);
							task.setExecuteTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
							
							log.info("同步任务正在执行，当前执行到了第 " + ++takeIndex);
						} catch (RuntimeException e) {
							log.info("...... 同步任务执行到了第 " + ++takeIndex +"但是执行失败......");
							
							//发生异常的任务，需要根据每种任务类型配置的重试间隔时间重新执行，并记录失败原因。
							task.setFailedReasons("失败的原因是：" + e.toString());
							task.setExecuteStatus(1);
							task.setSuccess(false);
						}
						
						//判断是否有执行失败的任务
						if (task.getExecuteStatus() == 1 && !task.isSuccess()) {
							
							log.info("存在执行失败的任务，正在等待 5 秒后，重新执行！");
							
							//等待制定的时候后，重新执行
							Thread.sleep(5*1000);
							
							//只在第三次执行这个任务时成功
							boolean failedExecuteSuccess = true;
							int failedExecuteCount = 0;
							
							while (failedExecuteSuccess) {
								
								//记录执行的次数
								failedExecuteCount++;
								
								log.info("失败后的任务，继续执行，当前是执行的第 " + failedExecuteCount + "次");
								
								//当执行第三次后，结束当前的执行，
								if (failedExecuteCount == 3) {
									
									//第三次的时候，执行成功
									task.setExecuteStatus(1);
									task.setSuccess(true);
									task.setExecuteTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
									
									log.info("......执行失败的任务，在第三次的时候，执行成功了！......");
									
									failedExecuteSuccess = false;
								}
							}
						}
					}
				}
				togetherAwaitCount++;
				
				selectTypeCondition.signal();
			}finally{
			}
		}
		lock.unlock();
	}

	/**
	 * 执行 COMMIT 类型的任务
	 * @author 高青
	 * 2014-6-10
	 * @return void 空
	 */
	private void executeCommitTask() {
		/*
		 * 打印“提交任务”+执行到第几条，并暂停10秒钟。
		 */
		int commitAwaitCount = 1;
		lock.lock();
		while (isExistTask) {
			
			if (commitAwaitCount == 2) {
				commitAwaitCount = 1;
				
				try {
					commitTypeCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			try{
				log.info("......提交任务开始执行......");
				
				Task task = taskList.poll();
				
				//判断队列中，是否还有任务
				if (task == null) {
					log.info("在执行提交任务时，当前队列中，没有可取的任务了！");
					isExistTask = false;
				}else {
					//设置任务状态为成功，记录成功时间
					task.setExecuteStatus(1);
					task.setSuccess(true);
					task.setExecuteTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					
					log.info("提交任务正在执行，当前执行到了第 " + ++takeIndex);
					
					//暂停 10 秒钟
					try {
						log.info("提交任务执行完成，正在暂停中，暂停时间为 5 秒钟！");
						Thread.sleep(5*1000);
					} catch (InterruptedException e) {
						log.info("在执行提交任务时，暂停期间，发生异常！");
						e.printStackTrace();
					}
				}
				commitAwaitCount++;
				
				//执行了完了，轮到 COMMIT 类型的任务执行
				togetherTypeCondition.signal();
			}finally{
			}
		}
		lock.unlock();
	}

	/**
	 * 执行 SELECT 任务
	 * @author 高青
	 * 2014-6-10
	 * @return void 空
	 */
	private void executeSelectTask() {
		/*
		 * 打印“查询任务”+执行到第几条。
		 */
		
		int selectAwaitCount = 1;
		
		lock.lock();
		while (isExistTask) {
			
			//当前任务第一个执行，在初始时，线程不挂起
			if (selectAwaitCount == 2) {
				try {
					//重置 selectAwaitCount 的值
					selectAwaitCount = 1;
					
					selectTypeCondition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try{
				log.info("......查询任务开始执行......");
				
				Task task = taskList.poll();
				
				//判断队列中，是否还有任务
				if (task == null) {
					log.info("在执行查询任务时， 当前队列中，没有可取的任务了！");
					isExistTask = false;
				}else {
					//设置任务状态为成功，记录成功时间
					task.setExecuteStatus(1);
					task.setSuccess(true);
					task.setExecuteTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
					
					log.info("查询任务正在执行，当前执行到了第 " + ++takeIndex);
				}
				
				selectAwaitCount++;
				
				//查询任务执行完后，执行 提交任务
				commitTypeCondition.signal();
			}finally{
			}
		}
		lock.unlock();
	}

	/**
	 * 生成任务
	 * @author 高青
	 * 2014-5-20
	 * @return taskList 任务集
	 */
	public void generateTask(){
		//初始化任务集对象
		taskList = new LinkedBlockingQueue<Task>();
		
		/*
		 * 每秒钟生成个5任务，每个任务类型是随机选择的三种任务之一
		 * （1）每秒钟生成个5任务，默认执行一分钟
		 * （2）将生成后的任务添加到 taskList
		 */
		
		//保证必须执行完生成线程后，再去执行别的线程
		lock.lock();
		try {
			//（1）每秒钟生成个5任务，默认执行一分钟
			while (isGenerateThreadRunning) {
				log.info("生成任务开始");
				
				//每隔一秒钟执行一次
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					log.info("在生成任务时，休眠一秒钟发生异常！");
					e.printStackTrace();
				}
				
				for (int i = 0; i < 5; i++) {
					//初始化任务对象
					Task task = new Task(TaskTypeEnum.getRandomTaskTypeEnum(), -1, false, null, null);
					
					//将任务添加到任务集中
					taskList.offer(task);
				}
			}
		}finally{
			lock.unlock();
		}
	}

	/**
	 * @return the taskList
	 */
	public LinkedBlockingQueue<Task> getTaskList() {
		return taskList;
	}

	/**
	 * @param taskList the taskList to set
	 */
	public void setTaskList(LinkedBlockingQueue<Task> taskList) {
		this.taskList = taskList;
	}

	/**
	 * @return the isGenerateThreadRunning
	 */
	public boolean isGenerateThreadRunning() {
		return isGenerateThreadRunning;
	}

	/**
	 * @param isGenerateThreadRunning the isGenerateThreadRunning to set
	 */
	public void setGenerateThreadRunning(boolean isGenerateThreadRunning) {
		this.isGenerateThreadRunning = isGenerateThreadRunning;
	}
	
}
