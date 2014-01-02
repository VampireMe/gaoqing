/**
 * 0.0.0.1
 */
package com.ctvit.nba.test.schedule;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.expand.ScheduleUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 赛程常用类的测试类
 * @author 高青
 * 2013-12-2
 */
public class ScheduleUtilTest {
	
	//赛程常用类对象
	private static ScheduleUtil scheduleUtil;
	
	//参数 Map 对象
	private static Map<String, String> paramMap;

	/**
	 * 测试类操作前的方法
	 * @author 高青
	 * 2013-12-2
	 * @throws java.lang.Exception
	 * @return void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//测试操作前，初始化  赛程常用类 
		scheduleUtil = new ScheduleUtil();
		paramMap = new HashMap<String, String>();
		
		//按天查询
		paramMap.put("day", "2");
		paramMap.put("month", "12");
		
		//当前查询
		//paramMap.put("today", "null");
		
		//按月查询
		//paramMap.put("month", "12");
		
		
		System.out.println("本次测试开始......");
	}

	/**
	 * 测试类，操作后的方法
	 * @author 高青
	 * 2013-12-2
	 * @throws java.lang.Exception
	 * @return void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("本次测试结束......");
	}

	/**
	 * @author 高青
	 * 2013-12-2
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link com.ctvit.nba.util.ScheduleUtil#getPartURL()}.
	 * 测试得到部分 url 的方法
	 */
	@Test
	public void testGetPartURL() {
		
	}

	/**
	 * Test method for {@link com.ctvit.nba.util.ScheduleUtil#getURLByKindsCondition(java.util.Map)}.
	 */
	@Test
	public void testGetURLByKindsCondition() {
		
		//得到 updateMethod_partURL_url_map 对象
		Map<String, Map<String, String>> updateMethod_partURL_url_map = scheduleUtil.getURLByKindsCondition("schedule",paramMap);
		
		//得到更新方式
		String updateMethod = XMLUtil.getUpdateMethod(updateMethod_partURL_url_map);
		
		//得到部分链接地址
		String partURL = XMLUtil.getPartURL(updateMethod_partURL_url_map);
		
		//得到  url 地址
		String url = updateMethod_partURL_url_map.get(updateMethod).get(partURL);
		
		System.out.println(url + "\t" +  "******");
		
		//断言得到的值不为空
		assertNotNull(url);
	}

	/**
	 * Test method for {@link com.ctvit.nba.util.ScheduleUtil#getScheduleListByURL(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGetSchedulesByURL() {
		//得到 updateMethod_partURL_url_map 对象
		Map<String, Map<String, String>> updateMethod_partURL_url_map = scheduleUtil.getURLByKindsCondition("schedule",paramMap);
		
		//得到更新方式
		String updateMethod = XMLUtil.getUpdateMethod(updateMethod_partURL_url_map);
		
		//得到部分链接地址
		String partURL = XMLUtil.getPartURL(updateMethod_partURL_url_map);
		
		//得到  url 地址
		String url = updateMethod_partURL_url_map.get(updateMethod).get(partURL);
		
		List<Schedule> schedulesByURL = XMLUtil.getTListByURL("schedule", partURL, url, null);
		
		assertNotNull(schedulesByURL);
	}

	/**
	 * Test method for {@link com.ctvit.nba.util.ScheduleUtil#getScheduleJsonArray(java.lang.String)}.
	 */
	@Test
	public void testGetScheduleJsonArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.ctvit.nba.util.ScheduleUtil#getSchedule(org.json.JSONObject, java.lang.String)}.
	 */
	@Test
	public void testGetScheduleByJSONObject() {
		fail("Not yet implemented");
	}

}
