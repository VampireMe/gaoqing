/**
 * 0.0.0.1
 */
package com.codegenerator.test;

import com.codegenerator.util.CommonUtil;

/**
 * 代码生成器的测试类
 * @author 高青
 * 2014-4-10
 */
public class CodeGeneratorTest {

	/**
	 * 构造方法
	 * 2014-4-10
	 */
	public CodeGeneratorTest() {
		// TODO Auto-generated constructor stub
	}
	
	public static void main(String[] args) {
		String propertiesValue = CommonUtil.getPropertiesValue("mysql");
		System.out.println(propertiesValue);
		
	}

}
