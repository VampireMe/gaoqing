/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库链接常用类
 * @author 高青
 * 2013-12-2
 */
public class JDBCUtil {
	
	/**数据库链接的对象*/
	private static Connection connection = null;
	
	/**
	 * 构造函数私有化
	 */
	private JDBCUtil(){}
	
	/**
	 * 建立数据库链接
	 * @author 高青
	 * 2013-12-2
	 * @return connection Connection链接对象
	 */
	public static Connection getConnection(){
		
		//数据库链接地址
		 String linkURL = CommonUtil.getPath("dbLinkURL");
		
		 //数据库链接用户名
		 String username = "cms";
		
		 //数据库链接用户名密码
		 String password = "cms";
		
		//如果该数据链接对象不为空，表明已经连接过，否则，进行实例化
		 if (connection == null) {
				
			 try {
				 //加载驱动
				Class.forName(CommonUtil.getPath("dbLinkDriver"));
				
				try {
					//建立链接
					connection = DriverManager.getConnection(linkURL, username, password);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	/**
	 * 发送  sql 语句
	 * @author 高青
	 * 2013-12-2
	 * @param connection Connection链接数据库对象
	 * @return statement Statement对象
	 */
	public static Statement getStatement(Connection connection){
		//发送sql语句的对象
		Statement statement = null;
		
		try {
			//发送 sql 语句
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	} 
	
	/**
	 * 发送  sql 语句
	 * @author 高青
	 * 2013-12-2
	 * @param connection Connection链接数据库对象
	 * @param sql 发送的 sql 语句
	 * @return preparedStatement PreparedStatement对象
	 */
	public static PreparedStatement getPreparedStatement(Connection connection, String sql){
		//发送sql语句的对象
		PreparedStatement preparedStatement = null;
		
		try {
			//发送 sql 语句
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	} 
	
	/**
	 * 关闭数据库链接及发送  sql 的流
	 * @author 高青
	 * 2013-12-2
	 * @param connection 数据库链接对象
	 * @param preparedStatement 发送 sql 的对象 
	 * @param resultSet 封装结果的  ResultSet 对象
	 */
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, 
										ResultSet resultSet){
		//关闭封装结果的对象
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭发送sql链接的对象
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭数据库链接
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 关闭数据库链接及发送  sql 的流
	 * @author 高青
	 * 2013-12-2
	 * @param connection 数据库链接对象
	 * @param preparedStatement 发送 sql 的对象 
	 * @param resultSet 封装结果的  ResultSet 对象
	 */
	public static void closeConnection(Connection connection, Statement statement, 
			ResultSet resultSet){
		//关闭封装结果的对象
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭发送sql链接的对象
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		//关闭数据库链接
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
