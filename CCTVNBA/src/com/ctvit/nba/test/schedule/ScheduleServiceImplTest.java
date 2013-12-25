/**
 * 0.0.0.1
 */
package com.ctvit.nba.test.schedule;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctvit.nba.service.ScheduleService;
import com.ctvit.nba.service.impl.ScheduleServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * 测试  赛程服务的实现类 （ScheduleServiceImpl）
 * @author 高青
 * 2013-12-4
 */
public class ScheduleServiceImplTest {
	
	//赛程服务类 对象
	private static ScheduleService scheduleService;
	
	//参数 Map 对象
	private static Map<String, String> paramMap;

	/**
	 * 测试前的操作
	 * @author 高青
	 * 2013-12-4
	 * @throws java.lang.Exception
	 * @return void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//实例化 赛程服务类
		scheduleService = new ScheduleServiceImpl();
		
		//向参数列表中添加值
		paramMap = new HashMap<String, String>();
		paramMap.put("day", "4");
		paramMap.put("month", "12");
		
		System.out.println( "测试操作，开始中......");
	}

	/**
	 * 测试后的操作
	 * @author 高青
	 * 2013-12-4
	 * @throws java.lang.Exception
	 * @return void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("测试操作，结束中......");
	}

	/**
	 * @author 高青
	 * 2013-12-4
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 更新赛程
	 * Test method for {@link com.ctvit.nba.service.impl.ScheduleServiceImpl#updateSchedule(java.util.Map)}.
	 */
	@Test
	public void testUpdateSchedule() {
		
		int updateSchedule = scheduleService.updateSchedule("schedule", paramMap, null);
		
		System.out.println(updateSchedule);
		
		assertEquals(1, updateSchedule);
		
	}

	/**
	 * Test method for {@link com.ctvit.nba.service.impl.ScheduleServiceImpl#updateSchedule2XML(java.util.List)}.
	 */
	@Test
	public void testUpdateSchedule2XML() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ctvit.nba.service.impl.ScheduleServiceImpl#getChildrenElementList(java.util.List)}.
	 */
	@Test
	public void testGetChildrenElementList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ctvit.nba.service.impl.ScheduleServiceImpl#getChildrenElement(com.ctvit.nba.entity.Schedule)}.
	 */
	@Test
	public void testGetChildrenElement() {
		fail("Not yet implemented");
	}

}
