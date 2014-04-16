/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.codegenerator.entity.Database;

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
	 * 回收数据库连接对象
	 * @author 高青
	 * 2014-04-15
	 * @param connection 要回收的数据库连接对象
	 * @return 空
	 */
	public static void recycleConnection(Connection connection){
		//遍历连接池中的对象，得到当前对象所对应的 连接管控者
		if (poolConnections == null || poolConnections.size() == 0) {
			log.info("请先初始化数据库连接池，再进行回收连接！");
			return;
		}
		ConnectionController connectionController = getConnectionController(connection);
		//设置当前的数据库连接对象为空闲
		connectionController.setBusy(false);
	}
	
	/**
	 * 回滚数据库连接池到最初的大小 
	 * @author 高青
	 * 2014-4-16
	 * @return 空
	 */
	public static void backToOriginConnectionPool(){
		//判断当前连接池是否存在
		if (poolConnections == null || poolConnections.size() == 0) {
			log.info("请初始数据库连接池！");
			return;
		}
		//判断当前数据库连接池的大小，如果超过最佳容量，则恢复到最初容量大小
		if (poolConnections.size() > minConnections) {
			
			//移除多余的空闲的数据库连接对象
			int beyondConnections = poolConnections.size() - minConnections;
			for (int i = 0; i < beyondConnections; i++) {
				
				ConnectionController freeConnectionController = getConnectionController();
				
				//如果没有空闲的数据连接管控对象，则等待五秒钟，再进行查找
				while (freeConnectionController == null) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						log.info("线程休眠时，发生异常！");
						e.printStackTrace();
					}
					freeConnectionController = getConnectionController();
				}
				
				//如果仍然没有空闲的连接，则提示：没有空闲的连接，保持当前数据库连接池中的数量
				if (freeConnectionController == null) {
					log.info("没有空闲的连接，保持当前数据库连接池中的数量！");
				}else {
					//移除空闲的数据库连接
					poolConnections.removeElement(freeConnectionController);
				}
			}
		}else {
			log.info("数据库连接池的大小，处于最佳容量状态！");
		}
	}

	/**
	 * 得到数据库连接池中的空闲连接管控对象
	 * @author 高青
	 * 2014-4-16
	 * @return freeConnectionController 空闲的数据管控对象
	 */
	private static ConnectionController getConnectionController() {
		//空闲的数据库连接管控对象
		ConnectionController freeConnectionController = null;
		
		for (ConnectionController connectionController : poolConnections) {
			if (!connectionController.isBusy()) {
				freeConnectionController = connectionController;
				
				//找到空闲的管控对象后，就停止查找
				break;
			}
		}
		return freeConnectionController;
	}

	/**
	 * 得到连接管控者
	 * @author 高青
	 * 2014-4-15
	 * @param connection 数据库连接对象
	 * @return connectionController 数据管控对象
	 */
	private static ConnectionController getConnectionController(Connection connection) {
		//初始化数据库管控对象
		ConnectionController connectionController = null;
		
		for (ConnectionController connectionControllerPool : poolConnections) {
			//如果当前的数据库连接对应着数据库管控者的连接，就将该连接的状态改为空闲
			if (connection == connectionControllerPool.getConnection()) {
				
				connectionController = connectionControllerPool;
				
				//找到当前连接后，停止寻找
				break;
			}
		}
		return connectionController;
	}
	
	/**
	 * 得到一个可用的数据库链接
	 * @author 高青
	 * 2014-04-11
	 * @param database 数据库连接对象
	 * @return connection 数据库连接池中的一个可用连接
	 */
	public Connection getConnection(Database database)
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
		connection = getEnableConnection(database);
		
		while (connection == null) {
			//暂停五秒钟，再从连接池中取
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log.info("暂停获取数据库连接池中的连接，发生异常！");
				e.printStackTrace();
			}
			connection = getEnableConnection(database);
		}
		//判断是否得到了可用的连接,如果没有空闲的连接，则自动的增加连接数
		if (connection == null) {
			log.info("当前数据库连接池中，没有空闲的连接，请稍后重试！");   			//--------------可以编写一个队列，进行排队获取
		}
		System.out.println(poolConnections.size());
		return connection;
	}

	/**
	 * 从数据库连接池中，取出一个可用的连接
	 * @author 高青
	 * 2014-4-14
	 * @param database 数据库连接对象
	 * @return connection 数据连接对象
	 */
	private synchronized Connection getEnableConnection(Database database)
	{
		//数据库连接对象
		Connection connection = null;
		
		//判断数据库连接池是否初始化过
		if (poolConnections == null || poolConnections.size() == 0) {
			//初始化数据库连接池
			createConnectionPool(database, minConnections);
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
			createConnectionPool(database, autoIncrementConnections);
		}
		return connection;
	}
	
	/**
	 * 创建初始数据库连接池
	 * @author 高青
	 * 2014-04-14
	 * @param database 数据库连接对象
	 * @param addConnectionCounts 添加到数据库连接池中的数量
	 * @return 空
	 */
	private synchronized void createConnectionPool(Database database, int addConnectionCounts)
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
					poolConnections.add(getConnectionController(database));
				}
			}
		}
		log.info("创建数据库连接池成功");
	}
	
	/**
	 * 得到初始状态下的数据连接管控对象
	 * @author 高青
	 * 2014-4-14
	 * @param database 数据库连接对象
	 * @return connectionController 数据连接管控对象
	 */
	private ConnectionController getConnectionController(Database database)
	{
		//初始化一个连接管控对象
		ConnectionController connectionController = new ConnectionController();
		
		//向管控对象中添加连接对象及状态
		Driver driver = null;
		try {
			//驱动类的标识
			String driverName = database.getDatabaseType() + "_" + "databaseDriver";
			
			try {
				driver = (Driver)Class.forName(CommonUtil.getPropertiesValue(driverName)).newInstance();
				
				//注册 JDBC 
				DriverManager.registerDriver(driver);
			} catch (InstantiationException e) {
				log.error("实例化当前驱动的驱动器异常！");
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				log.error("传入的数据库驱动名称有误，请检查！");
				e.printStackTrace();
			}catch (SQLException e) {
				log.info("注册 JDBC 驱动出现异常！");
			}
		} catch (ClassNotFoundException e) {
			log.error("加载 " + database.getDatabaseType() + " 的数据库驱动，发生异常！");
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(
						CommonUtil.getConnectionURL(database), 
						database.getUser(), 
						database.getPassword()
			);
			
			//判断当前数据库的最大连接数和配置的数据库最大连接数的大小关系
			int databaseMaxConnections = connection.getMetaData().getMaxConnections();
			if (databaseMaxConnections > 0) {
				if (databaseMaxConnections < maxConnections) {
					//将配置的最大连接数自动调整到数据库的最大连接数
					log.info("设置的数据库连接池的最大连接数超过了数据库的最大承载数，系统自动将其调整为数据库的最大承载数！");
					maxConnections = connection.getMetaData().getMaxConnections();
				}
			}
		} catch (SQLException e) {
			log.error("连接 " + database.getDatabaseType() + " 的数据库时，发生异常！");
			e.printStackTrace();
		}
		connectionController.setConnection(connection);
		connectionController.setBusy(false);
		
		return connectionController;
	}
	
	/**
	 * 测试得到的数据库连接是否可用
	 * @author 高青
	 * 2014-04-15
	 * @param connection 当前数据库连接
	 * @param database 数据库连接实体类
	 * @return isEnable 当前连接是否可用
	 */
	public static boolean testConnection(Connection connection, Database database){
		//是否可用
		boolean isEnable = false;
		// sql 语句发送对象
		Statement statement = null;
		
		if (database != null) {
			//判断数据库连接实体对象中，是否有测试的表
			if (database.getTable() != null && !"".equals(database.getTable())) {
				
				try {
					//得到 Statement 对象
					statement = connection.createStatement();
					statement.executeQuery("select * from " + database.getTable());
					
					//只要查询当前表的时候，没有出现异常，就表示数据库配置正确
					isEnable = true;
				} catch (SQLException e) {
					log.error("得到 Statement 对象出现异常！");
					e.printStackTrace();
				}
			}else {
				log.info("测试的数据库表不存在，请重试！");
			}
		}else {
			log.info("数据库实体类不存在，请重试！");
		}
		return isEnable;
	} 
	
	/**
	 * 关闭数据库连接
	 * @author 高青
	 * 2014-04-15
	 */
	public static void closeConnection(Connection connection){
		//首先判断当前连接是否处于工作状态，如果任在执行，则等待五秒，然后关闭
		ConnectionController connectionController = getConnectionController(connection);
		if (connectionController.isBusy()) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				log.info("当前线程处于休眠状态时，发生异常！");
				e.printStackTrace();
			}
		}
		try {
			connection.close();
		} catch (SQLException e) {
			log.info("关闭数据库连接发生异常！");
			e.printStackTrace();
		}
	}
	
	/**
	 * 清空连接池中的链接
	 * @author 高青
	 * 2014-04-11
	 */
	public static void clearConnectionPool(){
		//判断当前的数据库连接池是否存在
		if (poolConnections == null || poolConnections.size() == 0) {
			log.info("当前连接池已经清空！");
			return;
		}
		//判断当前连接池中的连接是否处于工作状态
		for (ConnectionController connectionController : poolConnections) {
			if (connectionController.isBusy()) {
				//等待 5 秒，然后关闭当前
				closeConnection(connectionController.getConnection());
			}
			//移除当前连接
			poolConnections.removeElement(connectionController);
		}
		//置空连接池
		poolConnections = null;
	}
	
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
