/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 数据库连接池的定时任务类
 * @author 高青
 * 2014-4-16
 */
public class ConnectionPoolJob implements Job{
	
	/** 日志对象 */
	private Logger log = Logger.getLogger(ConnectionPoolJob.class);
	
	/** 数据库连接池对象 */
	private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	/**
	 * 构造方法
	 * 2014-4-16
	 */
	public ConnectionPoolJob() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("恢复数据库连接池到最初的大小，执行方法开始！");
		
		//进行恢复
		connectionPool.backToOriginConnectionPool();
		
		log.info("恢复数据库连接池到最初的大小，执行方法结束！");
	}
}
