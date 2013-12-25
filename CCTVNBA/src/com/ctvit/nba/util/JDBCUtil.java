/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 数据库链接常用类
 * @author 高青
 * 2013-12-2
 */
public class JDBCUtil {
	
	/**数据库链接的对象*/
	private static Connection connection = null;
	
	/**发送sql语句的对象*/
	private static PreparedStatement preparedStatement = null;
	
	/**
	 * 建立数据库链接
	 * @author 高青
	 * 2013-12-2
	 * @return connection Connection链接对象
	 */
	public static Connection getConnection(){
		//数据库链接地址
		 String linkURL = XMLUtil.getPath("dbLinkURL");
		
		 //数据库链接用户名
		 String username = "scott";
		
		 //数据库链接用户名密码
		 String password = "tiger";
		
		 try {
			 //加载驱动
			Class.forName(XMLUtil.getPath("dbLinkDriver"));
			
			try {
				//建立链接
				connection = DriverManager.getConnection(linkURL, username, password);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	/**
	 * 发送  sql 语句
	 * @author 高青
	 * 2013-12-2
	 * @param connection Connection链接数据库对象
	 * @param sql sql语句
	 * @return preparedStatement PreparedStatement对象
	 */
	public static PreparedStatement getPreparedStatement(Connection connection, String sql){
		try {
			//发送 sql 语句
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
}
