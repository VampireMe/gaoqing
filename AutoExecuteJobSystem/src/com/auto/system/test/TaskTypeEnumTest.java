/**
 * 0.0.0.1
 */
package com.auto.system.test;

import org.junit.BeforeClass;
import org.junit.Test;

import com.auto.system.TaskTypeEnum;

/**
 * JobType枚举类的测试类
 * @author 高青
 * 2014-5-20
 */
public class TaskTypeEnumTest {

	/**
	 * 测试前的操作
	 * @author 高青
	 * 2014-5-20
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * Test method for {@link com.auto.system.TaskTypeEnum#toString()}.
	 */
	@Test
	public void testToString() {
		System.out.print(TaskTypeEnum.SELECT.name());
	}

}
