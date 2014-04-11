/**
 * 0.0.0.1
 */
package com.codegenerator.util;

import java.sql.Connection;

/**
 * jdbc 公用类
 * @author 高青
 * 2014-4-11
 */
public class JDBCUtil {

	/**
	 * 构造方法
	 * 2014-4-11
	 */
	private JDBCUtil() {
		
	}
	
	/**
	 * 得到数据库链接对象
	 * @author 高青
	 * 2014-4-11
	 * @param hostAddr 数据库链接地址
	 * @param port 端口号
	 * @param userName 用户名
	 * @param password 密码
	 * @return connection 数据库链接对象
	 */
	public static Connection getConnection(String hostAddr, String port, String userName, String password){
		//初始化数据库链接对象
		Connection connection = null;
		
		
		return connection;
	}

}
