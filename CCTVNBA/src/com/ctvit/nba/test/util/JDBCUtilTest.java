/**
 * 0.0.0.1
 */
package com.ctvit.nba.test.util;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctvit.nba.util.JDBCUtil;

/**
 * JDBC常用类测试
 * @author 高青
 * 2013-12-31
 */
public class JDBCUtilTest {

	/**
	 * 执行操作前的方法
	 * @author 高青
	 * 2013-12-31
	 * @throws java.lang.Exception
	 * @return void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * 执行操作后的方法
	 * @author 高青
	 * 2013-12-31
	 * @throws java.lang.Exception
	 * @return void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 得到 Connection 对象测试
	 * 2013-12-31
	 * Test method for {@link com.ctvit.nba.util.JDBCUtil#getConnection()}.
	 */
	@Test
	public void testGetConnection() {
		
		Connection connection = JDBCUtil.getConnection();
		
		assertNotNull(connection);
	}

	/**
	 * 得到发送 SQL 语句的 PreparedStatement 对象 
	 * 2013-12-31
	 * Test method for {@link com.ctvit.nba.util.JDBCUtil#getPreparedStatement
	 * (java.sql.Connection, java.lang.String)}.
	 */
	@Test
	public void testGetPreparedStatement() {
		//得到数据库连接对象
		Connection connection = JDBCUtil.getConnection();
		
		//查询语句
		String sql = "select * from TAB_NBA_SCHEDULE";
		
		PreparedStatement preparedStatement = JDBCUtil.getPreparedStatement(connection, sql);
		
		assertNotNull(preparedStatement);
	}

}
