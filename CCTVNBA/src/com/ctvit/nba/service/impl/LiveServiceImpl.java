/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom2.Element;
import org.json.JSONObject;

import com.ctvit.nba.dao.LiveDao;
import com.ctvit.nba.dao.impl.LiveDaoImpl;
import com.ctvit.nba.expand.LiveUtil;
import com.ctvit.nba.service.LiveService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 直播服务类的实现类
 * @author 高青
 * 2014-1-13
 */
public class LiveServiceImpl implements LiveService{
	
	/** 日志对象 */
	private Logger log = Logger.getLogger(LiveServiceImpl.class);
	
	/** 直播（live）的 dao 接口 */
	private LiveDao liveDao = new LiveDaoImpl();

	@Override
	public <T> int updateScheduleBasicInfo(String moduleName,String scheduleIDs, 
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		
		//得到内部更新模块及部分链接地址和最终 URL 对象
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		
		//得到内部更新模块
		String innerUpdateModule = CommonUtil.getInnerUpdateModule(finalURLMap);
		//得到 URL 地址及getURL
		String url = URLUtil.getURL(finalURLMap);
		String partGetUR = URLUtil.getPartGetURL(finalURLMap);
		
		//根据 URL 得到其内容并封装到 List 对象中
		JSONObject urlJsonObject = URLContentUtil.getURLJsonObject(url);
		
		//将选中的赛程更新到数据库中
		int daoUpdateFlag = liveDao.updateScheduleBasicInfo(innerUpdateModule, scheduleIDs);
		
		/*
		 * 将选中的赛程更新到外部的 XML 文件中
		 */
		//组织数据并生成子元素对象集合
		List<Element> childrenElementList = LiveUtil.getChildrenElementList(urlJsonObject, "basicInfo");
		
		//得到更新到 XML 文件名表示
		String xmlFileNameRemarker = innerUpdateModule + "-" + CommonUtil.getConditionRemarker(innerUpdateModuleACondtions);
		
		//更新到  XML 文件中
		int updateData2XMLFlag = XMLUtil.updateData2XML(moduleName, xmlFileNameRemarker, childrenElementList);
		
		return updateData2XMLFlag;
	}

}
