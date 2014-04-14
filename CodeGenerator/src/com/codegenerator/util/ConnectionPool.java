/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * 数据库连接池
 * @author 高青
 * 2014-4-11
 */
public class ConnectionPool {
	/** 日志对象 */
	private static Logger log = Logger.getLogger(ConnectionPool.class);
	
	/** 连接池的最小连接数 */
	private static int minConnections;
	
	/** 连接池的最大连接数 */
	private static int maxConnections;
	
	/** 连接池自动增加的数量 */
	private static int autoIncrementConnections;
	
	/** 数据库连接池 */
	private static Vector<ConnectionController> poolConnections;
	
	/** 数据库驱动 */
	private static String databaseDriver;
	
	/** 数据库连接池对象 */
	private static ConnectionPool connectionPool = null;
	
	/**
	 * 初始化属性值
	 */
	static{
		minConnections = Integer.valueOf(CommonUtil.getPropertiesValue("minConnections"));
		maxConnections = Integer.valueOf(CommonUtil.getPropertiesValue("maxConnections"));
		autoIncrementConnections = Integer.valueOf(CommonUtil.getPropertiesValue("autoIncrementConnections"));
		poolConnections = new Vector<ConnectionController>();
	}

	/**
	 * 构造方法
	 * 2014-4-11
	 */
	private ConnectionPool() {
		
	}
	
	/**
	 * 得到唯一的数据库连接池
	 * @author 高青
	 * 2014-04-14
	 */
	public static ConnectionPool getConnectionPool(){
		//判断数据库连接池是否初始化
		if (connectionPool == null) {
			connectionPool = new ConnectionPool();
		}
		return connectionPool;
	}
	
	/**
	 * 得到一个可用的数据库链接
	 * @author 高青
	 * 2014-04-11
	 * @param databaseType 数据库类型
	 * @param url 数据库连接地址
	 * @param user 连接数据的用户名
	 * @param password 连接数据的密码 
	 * @return connection 数据库连接池中的一个可用连接
	 */
	public Connection getConnection(
			String databaseType, 
			String url, 
			String user, 
			String password)
	{
		/*
		 * （1）判断数据库连接池中，是否有空闲的链接，
		 * 如果有，直接从连接池中，取出一个
		 * 如果没有，则重新创建一个数据库链接
		 * （（a）判断当前连接池中的数量是否超过了最大连接数，
		 * 		如果没有，直接创建；
		 * 		如果超过了最大连接数，使用队列，进行排队操作），
		 * （2）并将当前链接放入连接池中
		 */
		
		//初始化数据库链接对象
		Connection connection = null;
		
		//判断连接池中，是否存在空闲的连接
		connection = getEnableConnection(databaseType, url, user, password);
		
		while (connection == null) {
			//暂停五秒钟，再从连接池中取
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log.info("暂停获取数据库连接池中的连接，发生异常！");
				e.printStackTrace();
			}
			connection = getEnableConnection(databaseType, url, user, password);
		}
		
		//判断是否得到了可用的连接,如果没有空闲的连接，则自动的增加连接数
		if (connection == null) {
			log.info("当前数据库连接池中，没有空闲的连接，请稍后重试！");   			//--------------可以编写一个队列，进行排队获取
		}
		
		return connection;
	}

	/**
	 * 从数据库连接池中，取出一个可用的连接
	 * @author 高青
	 * 2014-4-14
	 * @param databaseType 数据库类型
	 * @param url 数据库连接地址
	 * @param user 连接数据的用户名
	 * @param password 连接数据的密码 
	 * @return connection 数据连接对象
	 */
	private synchronized Connection getEnableConnection(
			String databaseType, 
			String url, 
			String user, 
			String password)
	{
		//数据库连接对象
		Connection connection = null;
		
		//判断数据库连接池是否初始化过
		if (poolConnections == null || poolConnections.size() == 0) {
			//初始化数据库连接池
			createConnectionPool(databaseType, url, user, password, minConnections);
		}
		
		//循环连接池中的对象
		for (ConnectionController connectionController : poolConnections) {
			//判断当前的连接管控对象是否可用
			if (!connectionController.isBusy()) {
				connection = connectionController.getConnection();
				//设置当前的连接为使用状态
				connectionController.setBusy(true);
				break;
			}
		}
		
		//如果没有获取到空闲的连接，则增加数据库中的连接数
		if (connection == null) {
			createConnectionPool(databaseType, url, user, password, autoIncrementConnections);
		}
		
		return connection;
	}
	
	/**
	 * 创建初始数据库连接池
	 * @author 高青
	 * 2014-04-14
	 * @param databaseType 数据库类型
	 * @param url 数据库连接地址
	 * @param user 连接数据的用户名
	 * @param password 连接数据的密码 
	 * @param addConnectionCounts 添加到数据库连接池中的数量
	 * @return 空
	 */
	private synchronized void createConnectionPool(
			String databaseType, 
			String url, 
			String user, 
			String password, 
			int addConnectionCounts)
	{
		//判断最小连接数是否比最大连接数大
		if (minConnections >= maxConnections) {
			log.info("不符合实际情况，最小数据库连接数竟然比最大数据库连接数大！请修改！");
		}else {
			//创建 minConnections个 数据库连接对象，并添加到连接池中
			for (int i = 0; i < addConnectionCounts; i++) {
				
				//判断当前的连接池容量是否到底最大量
				if (maxConnections <= poolConnections.size()) {
					break;
				}else {
					poolConnections.add(getConnectionController(databaseType, url, user, password));
				}
			}
		}
		log.info("创建数据库连接池成功");
	}
	
	/**
	 * 得到初始状态下的数据连接管控对象
	 * @author 高青
	 * 2014-4-14
	 * @param databaseType 数据库类型
	 * @param url 数据库连接地址
	 * @param user 连接数据的用户名
	 * @param password 连接数据的密码 
	 * @return connectionController 数据连接管控对象
	 */
	private ConnectionController getConnectionController(
			String databaseType, 
			String url, 
			String user, 
			String password)
	{
		//初始化一个连接管控对象
		ConnectionController connectionController = new ConnectionController();
		
		//向管控对象中添加连接对象及状态
		Driver driver = null;
		try {
			try {
				driver = (Driver)Class.forName(CommonUtil.getPropertiesValue(databaseType)).newInstance();
			} catch (InstantiationException e) {
				log.error("实例化当前驱动的驱动器异常！");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				log.error("传入的数据库驱动名称有误，请检查！");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			log.error("加载 " + databaseType + " 的数据库驱动，发生异常！");
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			
			//判断当前数据库的最大连接数和配置的数据库最大连接数的大小关系
			if (connection.getMetaData().getMaxConnections() < maxConnections) {
				//将配置的最大连接数自动调整到数据库的最大连接数
				log.info("设置的数据库连接池的最大连接数超过了数据库的最大承载数，系统自动将其调整为数据库的最大承载数！");
				maxConnections = connection.getMetaData().getMaxConnections();
			}
		} catch (SQLException e) {
			log.error("连接 " + databaseType + " 的数据库时，发生异常！");
			e.printStackTrace();
		}
		connectionController.setConnection(connection);
		connectionController.setBusy(false);
		
		return connectionController;
	}
	
	
	/**
	 * 清空连接池中的链接
	 * @author 高青
	 * 2014-04-11
	 */
	
	
	/**
	 * 连接池中的数据库连接管控内部类<br>
	 * 包含数据库连接对象和当前对象的可用状态
	 * @author 高青
	 * 2014-04-14
	 */
	class ConnectionController{
		/** 数据库连接对象 */
		Connection connection;
		
		/** 当前数据库连接对象的状态 （默认是空闲状态）*/
		boolean isBusy = false;

		/**
		 * @return the connection
		 */
		public Connection getConnection() {
			return connection;
		}

		/**
		 * @param connection the connection to set
		 */
		public void setConnection(Connection connection) {
			this.connection = connection;
		}

		/**
		 * @return the isBusy
		 */
		public boolean isBusy() {
			return isBusy;
		}

		/**
		 * @param isBusy the isBusy to set
		 */
		public void setBusy(boolean isBusy) {
			this.isBusy = isBusy;
		}
	}

}