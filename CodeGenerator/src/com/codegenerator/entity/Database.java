/**
 * 0.0.0.1
 */
package com.codegenerator.entity;


/**
 * 数据库对象
 * @author 高青
 * 2014-4-15
 */
public class Database {
	
	/** 数据库类型 */
	private String databaseType;
	
	/** 连接地址 */
	private String url;
	
	/** 端口 */
	private String port;
	
	/** 用户 */
	private String user;
	
	/** 密码 */
	private String password;
	
	/** 数据库名称 */
	private String databaseName;
	
	/** 数据库表名 */
	private String table;
	
	/** 数据库实例化对象 */
	private static Database database;

	/**
	 * 构造方法
	 * 2014-4-15
	 */
	private Database() {
		
	}
	
	/**
	 * 得到数据库连接对象
	 * @author 高青
	 * 2014-04-21
	 */
	public static Database getDatabase(){
		//判断数据库实例化对象是否初始化
		if (database == null) {
			database = new Database();
		}
		return database;
	}
	
	/**
	 * 初始化数据库
	 * @author 高青
	 * 2014-04-21
	 * @param databaseType 数据库类型
	 * @param url 数据库连接地址
	 * @param port 数据库端口
	 * @param user 用户名
	 * @param password 密码
	 * @param databaseName 数据库名称
	 * @param table 表名
	 */
	public static void init(String databaseType, String url, String port, String user,
			String password, String databaseName, String table){
		if (database == null) {
			database = new Database();
		}
		database.databaseType = databaseType;
		database.url = url;
		database.port = port;
		database.user = user;
		database.password = password;
		database.databaseName = databaseName;
		database.table = table;
	}

	/**
	 * @return the databaseType
	 */
	public String getDatabaseType() {
		return databaseType;
	}

	/**
	 * @param databaseType the databaseType to set
	 */
	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the databaseName
	 */
	public String getDatabaseName() {
		return databaseName;
	}

	/**
	 * @param databaseName the databaseName to set
	 */
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}
}
