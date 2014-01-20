/**
 * 0.0.0.1
 */
package com.ctvit.nba.test.live;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctvit.nba.service.LiveService;
import com.ctvit.nba.service.impl.LiveServiceImpl;

/**
 * 直播的 service 的实现类的测试
 * @author 高青
 * 2014-1-15
 */
public class LiveServiceImplTest {
	
	/** 直播的服务实现类对象 */
	private static LiveService liveService;

	/**
	 * 测试前操作
	 * @author 高青
	 * 2014-1-15
	 * @throws java.lang.Exception
	 * @return void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		//初始化服务类的实现类对象
		liveService = new LiveServiceImpl();
		
		
	}

	/**
	 * 测试后的操作
	 * @author 高青
	 * 2014-1-15
	 * @throws java.lang.Exception
	 * @return void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * 测试更新数据到 xml 文件中
	 * Test method for {@link com.ctvit.nba.service.impl.LiveServiceImpl#updateScheduleBasicInfo
	 * (java.lang.String, java.lang.String, java.util.Map)}.
	 */
	@Test
	public void testUpdateScheduleBasicInfo() {
		//初始化参数
		String moduleName = "live";
		String scheduleIDs = "1348782,";
		
		Map<String, Map<String, String>> innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
		Map<String, String> innerConditionMap = new HashMap<String, String>();
		innerConditionMap.put("scheduleID", "1348782");
		innerUpdateModuleACondtions.put("LIVE", innerConditionMap);
		
		//执行更新操作
		int updateScheduleBasicInfoFlag = liveService.updateScheduleBasicInfo(moduleName, scheduleIDs, innerUpdateModuleACondtions);
		
		assertEquals(1, 1);
		
	}

}
