/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codegenerator.entity.Database;

/**
 * 代码生成器的测试类
 * @author 高青
 * 2014-4-10
 */
@Controller
public class CodeGeneratorTest {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(CodeGeneratorTest.class);

	/**
	 * 构造方法
	 * 2014-4-10
	 */
	public CodeGeneratorTest() {
		
	}
	
	@RequestMapping("index")
	public String firstTest(HttpServletRequest request, HttpServletResponse response){
		
		//得到当前 request 的 session 对象
		HttpSession session = request.getSession(false);
		
		//当 当前回话没有关闭时
		if (session != null) {
			if (session.getAttribute("database") != null) {
				
				log.info("已经填写过数据库信息！");
				
				return "index";
			}
		}
		
		//当前回话关闭后或者当前 session 失效后
		//得到 Cookie 对象
		Cookie[] cookies = request.getCookies();
		session = request.getSession();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("database")) {
					//得到 Cookie 中，数据库连接的信息
					String databaseInfoStr = cookie.getValue();
					
					String[] databaseInfoArr = databaseInfoStr.split("_");
					
					
					Database.init(
							databaseInfoArr[0], 
							databaseInfoArr[1], 
							databaseInfoArr[2], 
							databaseInfoArr[3], 
							databaseInfoArr[4], 
							databaseInfoArr[5], 
							databaseInfoArr[6]
						);
					
					Database database = Database.getDatabase();
					
					//将 数据库连接对象存放到 session 中
					session.setAttribute("database", database);
					
					break;
				}
			}
		}
		
		System.out.println("SpringMVC 初始化成功！");
		
		return "index";
	}
}
