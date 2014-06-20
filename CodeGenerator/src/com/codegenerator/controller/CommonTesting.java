/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import java.io.File;

import com.codegenerator.util.CommonUtil;

/**
 * 
 * @author 高青
 * 2014-6-19
 */
public class CommonTesting {

	/**
	 * 构造方法
	 * 2014-6-19
	 */
	public CommonTesting() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author 高青
	 * 2014-6-19
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		
		String databaseValuePath = CommonUtil.getPropertiesValeByKey("databaseInfoPath");
		
		File files = new File(databaseValuePath);
		
		String[] list = files.list();
		
		for (String string : list) {
			System.out.println(string);
		}

	}

}
