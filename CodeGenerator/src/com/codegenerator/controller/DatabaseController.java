/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codegenerator.entity.Database;
import com.codegenerator.util.ConnectionPool;

/**
 * 数据库连接的控制器
 * @author 高青
 * 2014-4-22
 */
@Controller
@RequestMapping("database")
public class DatabaseController{
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(DatabaseController.class);
	
	/** 初始化数据库的标识 */
	private int initCount = 0;
	
	/**
	 * 构造方法
	 * 2014-4-22
	 */
	public DatabaseController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 测试连接
	 * @author 高青
	 * 2014-4-22
	 * @param 
	 * @return 
	 */
	@RequestMapping("test")
	@ResponseBody
	public String testConnect(Database database, Model model){
		//判断数据库连接是否初始化过了
		if (initCount == 0) {
			log.info("数据库初始化了！");
			
			//初始化数据库
			Database.init(database.getDatabaseType(), database.getUrl(), 
						  database.getPort(), database.getUser(), 
						  database.getPassword(), database.getDatabaseName(), database.getTable());
			
			//标志已经初始化过了
			initCount ++;
		}
		//得到数据库连接
		Database databaseConnect = Database.getDatabase();
		
		//得到数据库连接池
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(databaseConnect);
		//测试数据库连接
		boolean testConnection = ConnectionPool.testConnection(connection, databaseConnect);
		
		//用完之后，将当前连接放到连接池中
		ConnectionPool.recycleConnection(connection);
		
		//返回标识
		String remarker = "failed";
		if (testConnection) {
			remarker = "success";
		}
		return remarker;
	}
	
	/**
	 * 数据库连接
	 * @author 高请
	 * 2014-04-23
	 * 
	 */
	@RequestMapping("connect")
	@ResponseBody
	public String connectDatabase(Database database, HttpServletRequest request, HttpServletResponse response){
		//判断数据库是否初始化过
		if (initCount == 0) {
			log.info("数据库初始化了！");
			
			//初始化数据库
			Database.init(database.getDatabaseType(), database.getUrl(), 
						  database.getPort(), database.getUser(), 
						  database.getPassword(), database.getDatabaseName(), database.getTable());
			
			//标志已经初始化过了
			initCount ++;			
		}
		//得到初始化后的数据库
		Database databaseIns = Database.getDatabase();
		
		//得到当前数据库中的所有表
		List<String> tableNameList = getTableNameList(databaseIns);
		
		//将当前的 List 集合转为 JSON 字符串
		JSONArray jonArray = new JSONArray(tableNameList);
		
		//得到 Session 对象
		HttpSession session = request.getSession();
		session.setAttribute("database", database);
				
		return jonArray.toString();
	}
	
	/**
	 * 得到当前数据库中的所有表
	 * @author 高青
	 * 2014-4-23
	 * @param database 数据库连接属性
	 * @return tableNameList 数据库中的所有表的集合
	 */
	private List<String> getTableNameList(Database database){
		//初始化数据库表名的 list 集
		List<String> tableNameList = new ArrayList<String>();
		
		//得到数据库连接对象
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		//查询出的所有表的结果集
		ResultSet tables = null;
		try {
			//得到数据库的属性对象
			DatabaseMetaData metaData = connection.getMetaData();
			
			//得到所有的表
			tables = metaData.getTables(null, null, null, new String[]{"TABLE"});
			
			while (tables.next()) {
				//得到表名称
				String tableName = tables.getString("TABLE_NAME");
				
				log.info("表名是：" + tableName);
				
				//将当前的表名称添加到集合中
				tableNameList.add(tableName);
			}
		} catch (SQLException e) {
			log.info("得到数据库的 metaData 对象发生异常！");
			e.printStackTrace();
		}finally{
			//回到当前的数据库连接对象
			ConnectionPool.recycleConnection(connection);
			
			//关闭 Resultset 对象
			if (tables != null) {
				try {
					tables.close();
				} catch (SQLException e) {
					log.info("关闭 tables(ResultSet) 对象发生异常！ ");
					e.printStackTrace();
				}
			}
		}
		return tableNameList;
	}
	

}
