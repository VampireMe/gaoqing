/**
 * 0.0.0.1
 */
package com.auto.system;


/**
 * 任务类
 * @author 高青
 * 2014-5-20
 */
public class Task {
	
	/** 任务的类型 */
	private TaskTypeEnum type;
	
	/** 任务执行的状态 （-1：未执行；0：正在执行；1：执行完毕） */
	private int executeStatus;
	
	/** 任务执行成功 */
	private boolean isSuccess;
	
	/** 执行成功的时间 */
	private String executeTime;
	
	/** 执行失败的原因 */
	private String failedReasons;

	/**
	 * 默认构造方法
	 * 2014-5-20
	 */
	public Task() {
		
	}

	/**
	 * @param type 任务的类型
	 * @param executeStatus 任务执行的状态 
	 * @param isSuccess 任务执行成功
	 * @param executeTime 执行成功的时间
	 * @param failedReasons 执行失败的原因
	 * 2014-5-20
	 */
	public Task(TaskTypeEnum type, int executeStatus, boolean isSuccess,
			String executeTime, String failedReasons) {
		super();
		this.type = type;
		this.executeStatus = executeStatus;
		this.isSuccess = isSuccess;
		this.executeTime = executeTime;
		this.failedReasons = failedReasons;
	}
	
	/**
	 * 执行任务
	 * @author 高青
	 * 2014-05-30
	 * @param taskTypeEnum 任务的类型
	 * @param index 当前执行任务的索引值
	 * @return void 空
	 */
	public void executeTask(TaskTypeEnum taskTypeEnum, int index){
		switch (taskTypeEnum) {
		
		//查询任务
		case SELECT:
			System.out.println("");
			break;

		default:
			break;
		}
	}

	/**
	 * @return the type
	 */
	public TaskTypeEnum getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(TaskTypeEnum type) {
		this.type = type;
	}

	/**
	 * @return the executeStatus
	 */
	public int getExecuteStatus() {
		return executeStatus;
	}

	/**
	 * @param executeStatus the executeStatus to set
	 */
	public void setExecuteStatus(int executeStatus) {
		this.executeStatus = executeStatus;
	}

	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}

	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	/**
	 * @return the executeTime
	 */
	public String getExecuteTime() {
		return executeTime;
	}

	/**
	 * @param executeTime the executeTime to set
	 */
	public void setExecuteTime(String executeTime) {
		this.executeTime = executeTime;
	}

	/**
	 * @return the failedReasons
	 */
	public String getFailedReasons() {
		return failedReasons;
	}

	/**
	 * @param failedReasons the failedReasons to set
	 */
	public void setFailedReasons(String failedReasons) {
		this.failedReasons = failedReasons;
	}
}
