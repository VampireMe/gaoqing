/**
 * 0.0.0.1
 */
package com.auto.system;

import java.util.Random;

/**
 * 任务类型的枚举
 * @author 高青
 * 2014-5-20
 */
public enum TaskTypeEnum {
	SELECT("查询任务"), COMMIT("提交任务"), TOGETHER("同步任务");
	
	/** 中文名称 */
	private String CN_NAME; 
	
	private TaskTypeEnum(){
	}
	
	/**
	 * 随机得到任务类型
	 * @author 高青
	 * 2014-6-10
	 * @return TaskTypeEnum 任务类型枚举类对象
	 */
	public static TaskTypeEnum getRandomTaskTypeEnum(){
		//使用随机数判断
		Random random = new Random();
		
		switch (random.nextInt(3)) {
		case 0:
			return TaskTypeEnum.SELECT;
		case 1:
			return TaskTypeEnum.COMMIT;
		default:
			return TaskTypeEnum.TOGETHER;
		}
	}
	
	private TaskTypeEnum(String cn_name){
		this.CN_NAME = cn_name;
	}
	
	public String toString(){
		return CN_NAME;
	}
}
