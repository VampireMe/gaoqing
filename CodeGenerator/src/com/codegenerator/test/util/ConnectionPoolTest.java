/**
 * 0.0.0.1
 */
package com.codegenerator.test.util;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import com.codegenerator.entity.Database;
import com.codegenerator.util.ConnectionPool;
import com.codegenerator.util.JDBCUtil;

/**
 * 数据库连接池测试类
 * @author 高青
 * 2014-4-16
 */
public class ConnectionPoolTest {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(ConnectionPoolTest.class);
	
	//设置数据库属性
	
	private Database database = Database.getDatabase();
	
	/** 数据库连接池 */
	private ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	/**
	 * 测试前的操作方法
	 * @author 高青
	 * 2014-4-16
	 * @return 空
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.init("sqlserver", "localhost", "3306", "root", "root", "myDatabase");
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#getConnectionPool()}.
	 */
	@Test
	public void testGetConnectionPool() {

		Connection connection = connectionPool.getConnection(database);
		Connection connection1 = connectionPool.getConnection(database);
		connectionPool.recycleConnection(connection1);
		Connection connection2 = connectionPool.getConnection(database);
		Connection connection3 = connectionPool.getConnection(database);
		connectionPool.recycleConnection(connection3);
		Connection connection4 = connectionPool.getConnection(database);
		Connection connection5 = connectionPool.getConnection(database);
		connectionPool.recycleConnection(connection5);
		Connection connection6 = connectionPool.getConnection(database);
		connectionPool.recycleConnection(connection6);
		Connection connection7 = connectionPool.getConnection(database);
		connectionPool.recycleConnection(connection7);
		Connection connection8 = connectionPool.getConnection(database);
		try {
			log.info(connection.getMetaData().getDriverName());
			log.info(connection.getMetaData().getMaxConnections());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(connection);
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#recycleConnection(java.sql.Connection)}.
	 */
	@Test
	public void testRecycleConnection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#getConnection(com.codegenerator.entity.Database)}.
	 */
	@Test
	public void testGetConnection() {
		
		
		System.out.println("12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234".length());
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#testConnection(java.sql.Connection, com.codegenerator.entity.Database)}.
	 */
	@Test
	public void testTestConnection() {
		Connection connection = connectionPool.getConnection(database);
		boolean testConnection = connectionPool.testConnection(connection, database);
		
		log.info(testConnection);
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#closeConnection(java.sql.Connection)}.
	 */
	@Test
	public void testCloseConnection() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.codegenerator.util.ConnectionPool#clearConnectionPool()}.
	 */
	@Test
	public void testClearConnectionPool() {
		fail("Not yet implemented");
	}
}
