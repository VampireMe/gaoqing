/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 代码生成器的测试类
 * @author 高青
 * 2014-4-10
 */
@Controller
public class CodeGeneratorTest {

	/**
	 * 构造方法
	 * 2014-4-10
	 */
	public CodeGeneratorTest() {
		
	}
	
	@RequestMapping("index")
	public String firstTest(){
		
		System.out.println("SpringMVC 初始化成功！");
		
		return "index";
	}
}
