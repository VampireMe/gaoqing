/**
 * 0.0.0.1
 */
package com.ctvit.nba.service.impl;

import java.util.List;
import java.util.Map;

import org.jdom2.Element;
import org.json.JSONObject;

import com.ctvit.nba.dao.PlayerDao;
import com.ctvit.nba.dao.impl.PlayerDaoImpl;
import com.ctvit.nba.entity.Player;
import com.ctvit.nba.expand.PlayerUtil;
import com.ctvit.nba.service.PlayerService;
import com.ctvit.nba.util.CommonUtil;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;
import com.ctvit.nba.util.XMLUtil;

/**
 * 球员信息服务类的实现类
 * @author 高青
 * 2014-1-20
 */
public class PlayerServiceImpl implements PlayerService{
	
	/** 球员信息的 Dao 对象 */
	private PlayerDao playerDao = new PlayerDaoImpl();

	@Override
	public <T> int updatePlayerPersonal2DB(String moduleName,
			String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新成功标识
		int flag = 0;
		
		List<Player> playerPersonalList = getURLContent2PlayerList(moduleName,
				innerUpdateModuleACondtions);
		
		//更新到数据库中
		int updateDBFlag = playerDao.updatePlayerPersonal2DB(playerPersonalList);
		flag = updateDBFlag;
		
		return flag;
	}

	/**
	 * 得到 URL 链接地址中的内容，并封装到 Player 实体集合中
	 * @author 高青
	 * 2014-1-23
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块（唯一链接标识）和更新条件 Map 对象
	 * @return playerPersonalList 球员个人信息集
	 */
	private <T> List<Player> getURLContent2PlayerList(String moduleName,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//得到数据链接地址
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		
		//得到内部链接模块名称（链接的唯一标识）
		String innerUpdateModule = CommonUtil.getInnerUpdateModule(finalURLMap);
		
		//得到最终的 URL 地址
		String url = URLUtil.getURL(finalURLMap);
		
		//得到链接地址的数据集合
		JSONObject urlJsonObject = URLContentUtil.getURLJsonObject(url);
		
		//得到球员信息的实体类集合数据
		List<Player> playerPersonalList = PlayerUtil.getPlayerPersonalList(innerUpdateModule, urlJsonObject);
		
		return playerPersonalList;
	}

	@Override
	public <T> int updatePlayerPersonal2XML(String moduleName,
			String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新成功标识
		int flag = 0;
		
		//得到球员信息的实体类集合数据
		List<Player> playerPersonalList = getURLContent2PlayerList(moduleName, innerUpdateModuleACondtions);
		
		//得到更新球员个人信息 xml 文件的子元素集合对象
		List<Element> playerPersonalChildrenElementList = PlayerUtil.getPlayerPersonalChildrenElementList(playerPersonalList);
		
		//得到数据链接地址
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		
		//得到更新到 xml 文件中的标识符
		String xmlFileNameRemarker = CommonUtil.getInnerUpdateModule(finalURLMap) + "_" + CommonUtil.getConditionRemarker(innerUpdateModuleACondtions);
		
		//更新到 xml 中
		int updateXMLFlag = XMLUtil.updateData2XML(moduleName, xmlFileNameRemarker, playerPersonalChildrenElementList);
		flag = updateXMLFlag;
		
		return flag;
	}
	
	

}
