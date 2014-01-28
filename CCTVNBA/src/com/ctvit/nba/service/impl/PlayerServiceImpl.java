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
	
	/** 得到数据链接地址 */
	private Map<String, Map<String, String>> finalURLMap = null;

	@Override
	public <T> int updatePlayerPersonal2DB(String moduleName,
			String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新成功标识
		int updatePlayerPersonalFlag = 0;
		
		List<Player> playerPersonalList = getURLContent2PlayerList(moduleName,
				innerUpdateModuleACondtions, "playerPersonal");
		
		//更新到数据库中
		int updateDBFlag = playerDao.updatePlayerPersonal2DB(playerPersonalList);
		updatePlayerPersonalFlag = updateDBFlag;
		
		return updatePlayerPersonalFlag;
	}

	/**
	 * 得到 URL 链接地址中的内容，并封装到 Player 实体集合中
	 * @author 高青
	 * 2014-1-23
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块（唯一链接标识）和更新条件 Map 对象
	 * @param updateModuleAlias 更新模块的别名
	 * @return playerPersonalList 球员个人信息集
	 */
	private <T> List<Player> getURLContent2PlayerList(String moduleName,
			Map<String, Map<String, T>> innerUpdateModuleACondtions, String updateModuleAlias) {
		//初始化球员信息数据集
		List<Player> playerList = null;
		
		//得到数据链接地址
		finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		
		//得到内部链接模块名称（链接的唯一标识）
		String innerUpdateModule = CommonUtil.getInnerUpdateModule(finalURLMap);
		
		//得到最终的 URL 地址
		String url = URLUtil.getURL(finalURLMap);
		//得到“get”部分的链接地址
		String partURL = URLUtil.getPartGetURL(finalURLMap);
		
		//得到链接地址的数据集合
		JSONObject urlJsonObject = URLContentUtil.getURLJsonObject(url);
		
		if(updateModuleAlias != null){
			
			//得到球员信息的实体类集合数据
			if (updateModuleAlias.equals("playerPersonal")) {
				playerList = PlayerUtil.getPlayerPersonalList(innerUpdateModule, urlJsonObject);
			}
			//得到本场比赛的最佳球员信息
			if (updateModuleAlias.equals("bestPlayer")) {
				playerList = URLContentUtil.getTListByURL(moduleName, innerUpdateModule, partURL, url);
			}
		}
		return playerList;
	}

	@Override
	public <T> int updatePlayerPersonal2XML(String moduleName,
			String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		//更新成功标识
		int updatePlayerPersonalFlag = 0;
		
		//得到球员信息的实体类集合数据
		List<Player> playerPersonalList = getURLContent2PlayerList(moduleName, innerUpdateModuleACondtions, "playerPersonal");
		
		//得到更新球员个人信息 xml 文件的子元素集合对象
		List<Element> playerPersonalChildrenElementList = PlayerUtil.getPlayerPersonalChildrenElementList(playerPersonalList);
		
		//得到更新到 xml 文件中的标识符
		String xmlFileNameRemarker = CommonUtil.getInnerUpdateModule(finalURLMap) + "-" + CommonUtil.getConditionRemarker(innerUpdateModuleACondtions);
		
		//更新到 xml 中
		int updateXMLFlag = XMLUtil.updateData2XML(moduleName, xmlFileNameRemarker, playerPersonalChildrenElementList);
		updatePlayerPersonalFlag = updateXMLFlag;
		
		return updatePlayerPersonalFlag;
	}

	@Override
	public <T> int updateBestPlayerInfo(String moduleName, String scheduleID,
			Map<String, Map<String, T>> innerUpdateModuleACondtions) {
		int updateBestPlayerInfoFlag = 0;
		
		//得到链接地址中的内容集合
		List<Player> bestPlayerInfoList = getURLContent2PlayerList(moduleName, innerUpdateModuleACondtions, "bestPlayer");
		
		//得到更新球员个人信息 xml 文件的子元素集合对象
		List<Element> playerPersonalChildrenElementList = PlayerUtil.getPlayerPersonalChildrenElementList(bestPlayerInfoList);
		
		//得到更新到 xml 文件中的标识符
		String xmlFileNameRemarker = CommonUtil.getInnerUpdateModule(finalURLMap) + "-" + CommonUtil.getConditionRemarker(innerUpdateModuleACondtions);
		
		//更新到 xml 中
		updateBestPlayerInfoFlag = XMLUtil.updateData2XML(moduleName, xmlFileNameRemarker, playerPersonalChildrenElementList);
		
		return updateBestPlayerInfoFlag;
	}
	
	

}
