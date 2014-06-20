/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codegenerator.entity.Database;
import com.codegenerator.util.CommonUtil;
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
		
	}
	
	/**
	 * 得到本地存储的数据库连接信息
	 * @author 高青
	 * 2014-6-20
	 * @param databaseType 数据库类型
	 * @param databaseName 数据库名称
	 * @return ../index 返回的页面标识
	 */
	@RequestMapping("getLocalInfo")
	public String getLocalFileInfo(
			String databaseType, 
			String databaseName,
			HttpServletRequest request,
			HttpServletResponse response
			)
	{
		/*
		 * 1、根据数据库类型及数据库名称，得到本地的数据库连接信息
		 * 2、将得到的数据库连接信息存储到 Database 对象中
		 * 3、将 Database 对象存放到 当前 request 中
		 * 4、将 Database 对象存放到  response 中
		 */
		
		//1、根据数据库类型及数据库名称，得到本地的数据库连接信息
		String databaseInfoStr = getLocalFileInfo2Str(databaseType, databaseName);
		
		if (StringUtils.isBlank(databaseInfoStr)) {
			log.info("当前文件 " + databaseType + "\\" + databaseName + ".txt" + " 中没有任何数据！");
		}else {
			//2、将得到的数据库连接信息存储到 Database 对象中
			String[] databaseInfoStrs = databaseInfoStr.split("_");
			
			Database.init(
					databaseInfoStrs[0], 
					databaseInfoStrs[1], 
					databaseInfoStrs[2], 
					databaseInfoStrs[3], 
					databaseInfoStrs[4], 
					databaseInfoStrs[5], 
					null
					);
			Database database = Database.getDatabase();
			
			// 3、将 Database 对象存放到 当前 request 中
			//4、将 Database 对象存放到  response 中
			storeDatabaseInfo(database, request, response, null);
		}
		
		//返回的页面，会在该 “index” 前面加上当前路径的命名空间“database” 的，而 index 存在在 webContent 路径下
		return "redirect:../index";
	}
	
	/**
	 * 得到本地数据库连接信息，并将其转为 String 字符串
	 * @author 高青
	 * 2014-6-20
	 * @param databaseType 数据库类型
	 * @param databaseName 数据库名称
	 * @return databaseFileInfoStr 数据库连接信息字符串
	 */
	private String getLocalFileInfo2Str(String databaseType, String databaseName) {
		/*
		 * 1、根据指定的数据库类型、数据库名称，得到指定的文件
		 * 2、读取当前文件到 String 中
		 */
		
		String databaseFileInfoStr = "";
		
		//1、根据指定的数据库类型、数据库名称，得到指定的文件
		File file = new File(CommonUtil.getPropertiesValeByKey("databaseInfoPath") + databaseType + File.separator + databaseName + ".txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			
			databaseFileInfoStr = reader.readLine();
		} catch (FileNotFoundException e) {
			log.info("在读取文件时，文件不存在！");
			e.printStackTrace();
		}catch (IOException e) {
			log.info("在读取文件时，发生异常！");
			e.printStackTrace();
		}
		return databaseFileInfoStr;
	}

	/**
	 * 得到选中的数据库连接信息
	 * @author 高青
	 * 2014-6-19
	 * @param databaseName 数据库名称
	 * @param request http 请求对象
	 * @param response http 响应对象
	 * @return json 数据库连接信息的 json 字符串
	 */
	@ResponseBody
	@RequestMapping("getSelected")
	public String getSelectedDatabaseInfo(
			String databaseName, 
			HttpServletRequest request, 
			HttpServletResponse response,
			Model model)
	{
		/*
		 * 1、到执行的文件路径下，找到所有的数据库连接信息文件
		 * 2、将得到的数据库连接类型和数据库文件名称，组织成 json ，传到前台
		 */
		
		//1、到执行的文件路径下，找到所有的数据库连接信息文件
		String databaseValuePath = CommonUtil.getPropertiesValeByKey("databaseInfoPath");
		File files = new File(databaseValuePath);
		
		//得到当前目录下的所有文件夹及文件
		String[] fileADirectories = files.list();
		
		//连接信息字符串
		StringBuffer sb = new StringBuffer();
		
		sb.append("[");
		
		for (String name : fileADirectories) {
			//找到文件夹
			if (name.indexOf(".") == -1) {
				
				//2、将得到的数据库连接类型和数据库文件名称，组织成 json ，传到前台
				List<String> fileList = getFolderFileList(name, databaseValuePath);
				
				//如果当前文件夹下面有存储的文件，则进行存储到 map 中，否则，不进行保存
				if (ListUtils.emptyIfNull(fileList).size() != 0 ) {
					
					sb.append("{");
					sb.append("\"id\":").append("\""+ name +"\",").
					append("\"text\":").append("\""+name+"\",").
					append("\"children\": [");

					for (String fileName : fileList) {
						sb.append("{");
						
						sb.append("\"id\":").append("\""+fileName.substring(0, fileName.indexOf("."))+"\",").
						append("\"text\":").append("\""+fileName.substring(0, fileName.indexOf("."))+"\"");
						
						sb.append("},");
					}
					//截取掉最后一个“,”
					sb.delete(sb.length() - 1, sb.length());
					
					sb.append("]");
					sb.append("},");
				}
			}
		}
		//如果，字符串中，最后一个“，”存在，就截取掉最后一个“,”
		if (sb.lastIndexOf(",") != -1) {
			sb.delete(sb.length() - 1, sb.length());
		}
		sb.append("]");
		
		String json = sb.toString();
		
		return json;
	}
	
	/**
	 * 得到指定文件夹下的所有文件集
	 * @author 高青
	 * 2014-6-20
	 * @param folderName 文件夹名称
	 * @param basePath 基本的存放路径
	 * @return folderFileList 文件夹中的所有文件集
	 */
	private List<String> getFolderFileList(String folderName, String basePath) {
		/*
		 * 通过给定的文件夹名称，得到文件夹中的所有数据库连接信息文件
		 */
		
		List<String> folderFileList = new ArrayList<String>();
		
		//得到当前的文件夹
		File folder = new File(basePath + folderName);
		String[] files = folder.list();
		
		folderFileList = Arrays.asList(files);
		
		return folderFileList;
	}

	/**
	 * 测试连接
	 * @author 高青
	 * 2014-4-22
	 * @param database 前台传递的数据库连接参数对象
	 * @return remarker 测试成功标识（failed：失败；success：成功）
	 */
	@RequestMapping("test")
	@ResponseBody
	public String testConnect(Database database){
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
	 * @param database 前台传递的数据库连接参数对象
	 * @param request http 请求对象
	 * @param response http 响应对象
	 * @return jonArray.toString() 数据库中的所有表json 字符串
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
		
		storeDatabaseInfo(database, request, response, jonArray);
		
		return jonArray.toString();
	}

	/**
	 * 存储数据库连接对象
	 * @author 高青
	 * 2014-6-14
	 * @param database 数据库连接对象
	 * @param request http 请求对象
	 * @param response http 响应对象
	 * @param jonArray 数据库中的所有表和 JSONArray 对象
	 * @return null 空
	 */
	private String storeDatabaseInfo(Database database,
			HttpServletRequest request, HttpServletResponse response,
			JSONArray jonArray) {
		/*
		 * 判断：
		 * （1）如果 session 存在并且存储过 database 数据，则不再进行重复保存
		 * （2）如果，没有，则将数据存储到 Session 中
		 * （3）如果 session中没有数据，则从 request 的 cookies 中，查找，如果有，则不再进行保存
		 * （4）如果没有，将当前 数据库连接信息保存到 cookie 中，发送到客户端
		 * （4）如果 cookie 中也没有，那么判断本地文件是否保存过，如果没有保存过，则存储当前的数据库连接信息
		 */
		
		//去掉 数据库 中表的保存（表是长期变化的，不易于固定保存）
		database.setTable(null);
		
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("database") != null){
			//（1）如果 session 存在并且存储过 database 数据，则不再进行重复保存
			
			log.info("当前回话已经保存过 databse 的数据了，不再进行重复的保存！");
		}else{
			//（2）如果，没有，则将数据存储到 Session 中
			
			session.setAttribute("database", database);
			session.setMaxInactiveInterval(30*60);
		}
		
		/*
		 * 判断：
		 * （1）如果 request 中保存过，则不再进行重复保存
		 * （2）如果，没有，则将数据存储到 Cookie 中
		 */
		Cookie[] cookies = request.getCookies();
		if((cookies != null)){
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("database")) {
					log.info("当前Cookie 中，已经保存过 databse 的数据了，不再进行重复的保存！");
					
					if (jonArray == null) {
						return null;
					}else {
						//（1）如果 request 中保存过，则不再进行重复保存
						return jonArray.toString();
					}
				}
			}
		}
		
		//（2）如果，没有，则将数据存储到 Cookie 中
		String databaseInfoStr = database.getDatabaseType() + "_" + database.getUrl() + "_" + 
				  database.getPort() + "_" +  database.getUser() + "_" + 
				  database.getPassword() + "_" + database.getDatabaseName();
		Cookie databaseInfo = new Cookie("database", databaseInfoStr);
		databaseInfo.setMaxAge(30*60);
		databaseInfo.setHttpOnly(true);
		databaseInfo.setPath("/CodeGenerator/");
		response.addCookie(databaseInfo);
		
		//如果 cookie 中也没有，那么判断本地文件是否保存过，如果没有保存过，则存储当前的数据库连接信息
		storeDatabaseInfo2File(database);
		
		return null;
	}
	
	/**
	 * 存储数据库连接信息到本地文件中
	 * @author 高青
	 * 2014-6-17
	 * @param database 数据库连接信息
	 * @return void 空
	 */
	private void storeDatabaseInfo2File(Database database) {
		/*
		 * 根据数据库名称，判断本地是否存储过该数据库的链接信息
		 * （1）如果，存在，则不再进行存储
		 * （2）如果，不存在，则进行本地化存储
		 */
		
		//得到数据库名称
		String databaseName = database.getDatabaseName();
		//得到数据库类型
		String databaseType = database.getDatabaseType();
		
		//判断当前文件是否存在，如果不存在，则创建一个
		createFolder(databaseType);
		
		//得到本地文件
		String filePath = "";
		File databaseNameFile = new File(filePath =
				CommonUtil.getPropertiesValeByKey("databaseInfoPath") + 
				databaseType + "\\" + 
				databaseName + ".txt");
		
		//判断本地是否存储过该数据库的链接信息
		if(databaseNameFile.exists()){
			
			//（1）如果，存在，则不再进行存储
			log.info("当前数据库 " + databaseName + " 的连接信息，已经存储在 "+ filePath +"中了，不再进行存储！");
		}else {
			
			//（2）如果，不存在，则进行本地化存储
			String toWrite2File = database.getDatabaseType() + "_" + database.getUrl() + "_" + 
					  database.getPort() + "_" +  database.getUser() + "_" + 
					  database.getPassword() + "_" + databaseName;
			
			//将数据库连接信息写到指定的文件中
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(databaseNameFile);
				fos.write(toWrite2File.getBytes());
				
				log.info("将数据库 " + databaseName + " 的连接信息写到本地文件 "+ filePath +"，成功！");
			} catch (FileNotFoundException e) {
				log.info("在写出数据时，文件对象出现异常，可能是写出的文件路径不对！");
				e.printStackTrace();
			} catch (IOException e) {
				log.info("在写出文件时，发生异常！");
				e.printStackTrace();
			}finally{
				
				//关闭文件写出流
				try {
					fos.close();
				} catch (IOException e) {
					log.info("在关闭文件写出流时，发生异常！");
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * <p>创建当前数据库本地存储文件的文件夹</p>
	 * （根据不同的数据库类型，创建不同的文件夹）
	 * @author 高青
	 * 2014-6-19
	 * @param databaseType 数据库连接类型
	 * @return void 空
	 */
	private void createFolder(String databaseType) {
		//得到当前路径下的文件夹
		File folder = new File(CommonUtil.getPropertiesValeByKey("databaseInfoPath") + databaseType);
		
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
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
