/**
 * 0.0.0.1
 */
package com.ctvit.nba.test.util;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ctvit.nba.util.URLContentUtil;

/**
 * 通过 URL 得到 内容的测试类
 * @author 高青
 * 2013-12-3
 */
public class URLContentUtilTest {
	
	/**
	 * 操作前方法
	 * @author 高青
	 * 2013-12-3
	 * @throws java.lang.Exception
	 * @return void
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	/**
	 * @author 高青
	 * 2013-12-3
	 * @throws java.lang.Exception
	 * @return void
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @author 高青
	 * 2013-12-3
	 * @throws java.lang.Exception
	 * @return void
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * 测试  根据 URL 得到链接中的内容的方法
	 * Test method for {@link com.ctvit.nba.util.URLContentUtil#getURLContent(java.lang.String)}.
	 */
	@Test
	public void testGetURLContent() {
		//得到要访问的  URL 地址
		String url = "http://nba.misports.cn/GlobalBasketBallCenter/DataInterface/ScheduleService/GetDateScheduleList?format=json&part=cntv&partkey=35407F73FA7EBE6B45B4DAE5A303B9F7&random=1&leagueID=1&month=11&day=28";
		
		//得到 链接中的内容
		String urlContent = URLContentUtil.getURLContent(url);
		
		System.out.println(urlContent);
		
		assertNotNull(urlContent);
	}

}
