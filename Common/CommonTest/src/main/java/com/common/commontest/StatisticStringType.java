/**
 * 0.0.0.1
 */
package com.common.commontest;


/**
 * 统计字符类型
 * @author gaoqing
 * 2014-6-5
 */
public class StatisticStringType {

	/**
	 * 构造方法
	 */
	public StatisticStringType() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 主线程方法
	 * @author gaoqing
	 * 2014-6-5
	 * @param args 字符串参数
	 * @return void 空
	 */
	public static void main(String[] args) {
		
		String str = "aa23ad .adf, ?0 sdfa3rf";
		
		/*
		 * 使用正则表达式的形式，统计其类型
		 */
		
		System.out.println("一共有：" + str.length());
		
		//字母数
		int words = 0;
		
		//数字
		int nums = 0;
		
		//其他
		int others = 0;
		
		for (int i = 0; i < str.length(); i++) {
			String curStr = String.valueOf(str.charAt(i));
			
			//字母
			if(curStr.matches("[a-zA-Z]")){
				words++;
			}
			
			//数字
			if(curStr.matches("\\d")){
				nums++;
			}
			
			//其他
			if(curStr.matches("\\W")){
				others++;
			}
		}
		
		System.out.println("其中，字母有：" + words + "\t," + "数字有：" + nums + "\t," + "其他有：" + others);

	}

}
