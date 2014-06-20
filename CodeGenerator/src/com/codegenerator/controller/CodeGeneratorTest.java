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
		
		//判断数据库连接信息是否存在，并根据情况，做出相应的处理
		judgeDatabaseExist(request);
		
		return "index";
	}

	/**
	 * 判断数据库连接对象是否存在
	 * @author 高青
	 * 2014-6-14
	 * @param request http 请求对象
	 * @return null 空
	 */
	private String judgeDatabaseExist(HttpServletRequest request) {
		/*
		 * 1、首先从当前 request 对象中，判断是否存在 session 对象，并判断是否将 database 保存到 session 中
		 * 	（1.1）如果保存过，则直接跳转到页面中
		 * 	（1.2）如果当前 request 对象中，不存在 session 对象，或者没有保存 database 信息，
		 * 		则从 request中取到 cookie ，判断是否保存过 database 信息 
		 * 		（1.2.1）如果 cookie 中保存过 database 对象，则将信息从 cookie 中取出，并保存到 当前 request 中的 session 中
		 */
		
		//1、首先从当前 request 对象中，判断是否存在 session 对象，并判断是否将 database 保存到 session 中
		HttpSession session = request.getSession(false);
		
		//当 当前回话没有关闭时
		if (session != null) {
			if (session.getAttribute("database") != null) {
				
				log.info("已经填写过数据库信息！");
				
				return "index";
			}
		}
		
		//（1.2）如果当前 request 对象中，不存在 session 对象，或者没有保存 database 信息
		Cookie[] cookies = request.getCookies();
		session = request.getSession();
		
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				
				//（1.2.1）如果 cookie 中保存过 database 对象，则将信息从 cookie 中取出，并保存到 当前 request 中的 session 中
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
							null
							);
					
					Database database = Database.getDatabase();
					
					//将 数据库连接对象存放到 session 中(保证再次访问当前页面时，存储信息)
					session.setAttribute("database", database);
					//将得到数据库连接对象保存到本次 request 对象中（保证本次访问时，存储信息）
					request.setAttribute("database", database);
					
					return "index";
				}
			}
		}
		return null;
	}
}
