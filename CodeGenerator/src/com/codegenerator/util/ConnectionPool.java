/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.sql.Connection;
import java.util.Vector;

/**
 * 数据库连接池
 * @author 高青
 * 2014-4-11
 */
public class ConnectionPool {
	
	/** 连接池的最小连接数 */
	private static int minConnections;
	
	/** 连接池的最大连接数 */
	private static int maxConnections;
	
	/** 连接池自动增加的数量 */
	private static int autoIncrementConnections;
	
	/** 数据库连接池 */
	private static Vector<Connection> poolConnections;
	
	/** 数据库驱动 */
	private static String databaseDriver;
	
	/**
	 * 初始化属性值
	 */
	static{
		
	}
	
	
	

	/**
	 * 构造方法
	 * 2014-4-11
	 */
	public ConnectionPool() {
		
	}
	
	/**
	 * 创建数据库连接池
	 * @author 高青
	 * 2014-04-11
	 */
	
	
	
	/**
	 * 得到一个可用的数据库链接
	 * @author 高青
	 * 2014-04-11
	 */
	
	
	/**
	 * 清空连接池中的链接
	 * @author 高青
	 * 2014-04-11
	 */
	
	

}
