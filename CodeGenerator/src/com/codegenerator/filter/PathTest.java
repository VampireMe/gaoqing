/**
 * 0.0.0.1
 */
package com.codegenerator.filter;

import java.io.File;


/**
 * 
 * @author 高青
 * 2014-5-8
 */
public class PathTest {

	
	/**
	 * 构造方法
	 * 2014-5-8
	 */
	public PathTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @author 高青
	 * 2014-5-8
	 * @param 
	 * @return 
	 */
	public static void main(String[] args) {
		 File file=new File(""); 
		 String path=file.getAbsolutePath();
		 
		 File templateFile = new File(path + "/WebContent/Template/");
		
		 System.out.println(path);
		
		if (templateFile.isDirectory()) {
			System.out.println("aaaaa");
		}else {
			System.out.println(templateFile.getAbsolutePath());
		}
		System.out.println(templateFile.getAbsolutePath());
		

	}

}
