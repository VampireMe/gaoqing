/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Player;
import com.ctvit.nba.util.CommonUtil;

/**
 * 球员信息常用方法类
 * @author 高青
 * 2014-1-20
 */
public class PlayerUtil {
	
	/**
	 * 得到球员信息
	 * @author 高青
	 * 2014-1-21
	 * @param innerUpdateModule 内部更新模块名称
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @return player 球员信息对象
	 */
	public static Player getPlayer(String innerUpdateModule, JSONObject playerJsonObject){
		//初始化球员对象 
		Player player = new Player();
		
		if (innerUpdateModule != null && playerJsonObject != null) {
			
			//基本信息部分
			player.setPlayerID(CommonUtil.getIntegerValueByKey(playerJsonObject, "PlayerID").toString());
			player.setPlayerNumber(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "PlayerNumber")));
			player.setTeamID(CommonUtil.getIntegerValueByKey(playerJsonObject, "TeamID").toString());
			player.setTeamENName(playerJsonObject.getString("TeamENName"));
			player.setTeamCNName(playerJsonObject.getString("TeamCNName"));
			player.setTeamCNAlias(playerJsonObject.getString("TeamCNAlias"));
			player.setPlayerCNName(playerJsonObject.getString("PlayerCNName"));
			player.setPlayerCNAlias(playerJsonObject.getString("PlayerCNAlias"));
			player.setFirstName(playerJsonObject.getString("FirstName"));
			player.setLastName(playerJsonObject.getString("LastName"));
			player.setLPortrait(playerJsonObject.getString("LPortrait"));
			player.setSPortrait(playerJsonObject.getString("SPortrait"));
			player.setPositionID(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject, "Position")));
			player.setPositionName(CommonUtil.resoleEmptyParam(CommonUtil.dealWithNull(playerJsonObject,"PositionName")));
			
			//球员数据部分
			player.setRebounds(CommonUtil.getIntegerValueByKey(playerJsonObject, "Rebounds"));
			player.setAssists(CommonUtil.getIntegerValueByKey(playerJsonObject, "Assists"));
			player.setBlocked(CommonUtil.getIntegerValueByKey(playerJsonObject, "Blocked"));
			player.setSteals(CommonUtil.getIntegerValueByKey(playerJsonObject, "Steals"));
			player.setTurnovers(CommonUtil.getIntegerValueByKey(playerJsonObject, "Turnovers"));
			player.setFreeThrowsAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "FreeThrowsAttempted"));
			player.setFreeThrows(CommonUtil.getIntegerValueByKey(playerJsonObject, "FreeThrows"));
			player.setPersonalFouls(CommonUtil.getIntegerValueByKey(playerJsonObject, "PersonalFouls"));
			player.setFieldGoals(CommonUtil.getIntegerValueByKey(playerJsonObject, "FieldGoals"));
			player.setFieldGoalsAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "FieldGoalsAttempted"));
			player.setTechnicalFouls(CommonUtil.getIntegerValueByKey(playerJsonObject, "TechnicalFouls"));
			player.setPoints(CommonUtil.getIntegerValueByKey(playerJsonObject, "Points"));
			player.setThreePointAttempted(CommonUtil.getIntegerValueByKey(playerJsonObject, "ThreePointAttempted"));
			player.setThreePointGoals(CommonUtil.getIntegerValueByKey(playerJsonObject, "ThreePointGoals"));
			player.setGamesStarted(CommonUtil.getIntegerValueByKey(playerJsonObject, "GameStart"));
			player.setMinutes(Double.valueOf(CommonUtil.getIntegerValueByKey(playerJsonObject, "Minutes").toString()));
			
			//内部更新模块
			if (innerUpdateModule != null) {
				player.setInnerUpdateModule(innerUpdateModule);
			}
		}
		return player;
	}



	/**
	 * 得到球员个人信息的实体对象(Player)
	 * @author 高青
	 * 2014-1-28
	 * @param innerUpdateModule 内部更新模块名称
	 * @param playerJsonObject 球员信息的 JsonObject 对象
	 * @param otherInfo 附加信息
	 * @return player 球员信息对象
	 */
	private static Player getPlayerPersonal(String innerUpdateModule, JSONObject playerJsonObject, String otherInfo) {
		//得到公共的 Player 数据信息
		Player player = getPlayer(innerUpdateModule, playerJsonObject);
		
		//其他信息
		if (otherInfo != null) {
			player.setOther(otherInfo);
		}
		return player;
	}
	
	/**
	 * 得到球员个人信息实体对象的集合
	 * @author 高青
	 * 2014-1-21
	 * @param innerUpdateModule 内部更新模块名称
	 * @param urlJsonObject url链接内容的JSONObject 对象
	 * @return playerPersonalList 球员个人信息实体对象集合
	 */
	public static List<Player> getPlayerPersonalList(String innerUpdateModule, JSONObject urlJsonObject){
		//初始化球员个人信息数据集合对象
		List<Player> playerPersonalList = new ArrayList<Player>();
		
		if (urlJsonObject != null) {
			//得到直播球员信息下的球员数据
			JSONObject LivePlayerStatJsonObject = urlJsonObject.getJSONObject("LivePlayerStat");
			
			//得到主队球员的个人信息
			JSONArray homePlayerJsonArray = LivePlayerStatJsonObject.getJSONArray("Home");
			
			//将 homePlayerJsonArray 中的数据添加到 playerPersonalList
			for (int i = 0; i < homePlayerJsonArray.length(); i++) {
				JSONObject homePlayerJsonObject = homePlayerJsonArray.getJSONObject(i);
				
				//封装到 球员个人信息的实体类中
				Player player = getPlayerPersonal(innerUpdateModule, homePlayerJsonObject, "home");
				
				//添加的集合中
				playerPersonalList.add(player);
			}
			
			//得到客队球员的个人信息
			JSONArray visitPlayerJsonArray = LivePlayerStatJsonObject.getJSONArray("Visit");
			
			//将 visitPlayerJsonArray 中的数据添加到 playerPersonalList
			for (int i = 0; i < visitPlayerJsonArray.length(); i++) {
				JSONObject visitPlayerJsonObject = visitPlayerJsonArray.getJSONObject(i);
				
				//封装到 球员个人信息的实体类中
				Player player = getPlayerPersonal(innerUpdateModule, visitPlayerJsonObject, "visit");
				
				//添加的集合中
				playerPersonalList.add(player);
			}
		}
		return playerPersonalList;
	}
	
	/**
	 * 得到球员个人信息的子元素集 
	 * @author 高青
	 * 2014-1-21
	 * @param playerPersonalList 球员个人信息的实体对象集
	 * @return playerPersonalChildrenElementList 球员个人信息的子元素集
	 */
	public static List<Element> getPlayerPersonalChildrenElementList(List<Player> playerPersonalList){
		//初始化球员个人信息子元素集
		List<Element> playerPersonalChildrenElementList = new ArrayList<Element>();
		
		for (Player player : playerPersonalList) {
			Element playerPersonalChildrenElement = getPlayerPersonalChildrenElement(player);
			
			//添加到集合中
			playerPersonalChildrenElementList.add(playerPersonalChildrenElement);
		}
		return playerPersonalChildrenElementList;
	}

	/**
	 * 得到球员个人信息子元素
	 * @author 高青
	 * 2014-1-22
	 * @param player 球员的信息
	 * @return void 
	 */
	private static Element getPlayerPersonalChildrenElement(Player player) {
		//初始化子元素对象
		Element element = new Element("PlayerInfo");
		
		//设置属性
		
		//球员基本信息
		element.setAttribute("PlayerID", player.getPlayerID());
		element.setAttribute("PlayerNumber", player.getPlayerNumber());
		element.setAttribute("TeamID", player.getTeamID());
		element.setAttribute("TeamENName", player.getTeamENName());
		element.setAttribute("TeamCNName", player.getTeamCNName());
		element.setAttribute("TeamCNAlias", player.getTeamCNAlias());
		element.setAttribute("PlayerCNName", player.getPlayerCNName());
		element.setAttribute("PlayerCNAlias", player.getPlayerCNAlias());
		element.setAttribute("FirstName", player.getFirstName());
		element.setAttribute("LastName", player.getLastName());
		element.setAttribute("LPortrait", player.getLPortrait());
		element.setAttribute("SPortrait", player.getSPortrait());
		element.setAttribute("Position", player.getPositionID());
		element.setAttribute("PositionName", player.getPositionName());
		
		//球员数据
		element.setAttribute("Rebounds", CommonUtil.resoleEmptyParam(player.getRebounds()));
		element.setAttribute("Assists", CommonUtil.resoleEmptyParam(player.getAssists()));
		element.setAttribute("Blocked", CommonUtil.resoleEmptyParam(player.getBlocked()));
		element.setAttribute("Steals", CommonUtil.resoleEmptyParam(player.getSteals()));
		element.setAttribute("Turnovers", CommonUtil.resoleEmptyParam(player.getTurnovers()));
		element.setAttribute("FreeThrowsAttempted", CommonUtil.resoleEmptyParam(player.getFreeThrowsAttempted()));
		element.setAttribute("FreeThrows", CommonUtil.resoleEmptyParam(player.getFreeThrows()));
		element.setAttribute("PersonalFouls", CommonUtil.resoleEmptyParam(player.getPersonalFouls()));
		element.setAttribute("FieldGoals", CommonUtil.resoleEmptyParam(player.getFieldGoals()));
		element.setAttribute("FieldGoalsAttempted", CommonUtil.resoleEmptyParam(player.getFieldGoalsAttempted()));
		element.setAttribute("TechnicalFouls", CommonUtil.resoleEmptyParam(player.getTechnicalFouls()));
		element.setAttribute("Points", CommonUtil.resoleEmptyParam(player.getPoints()));
		element.setAttribute("ThreePointAttempted", CommonUtil.resoleEmptyParam(player.getThreePointAttempted())); 
		element.setAttribute("ThreePointGoals", CommonUtil.resoleEmptyParam(player.getThreePointGoals()));
		element.setAttribute("GameStart", CommonUtil.resoleEmptyParam(player.getGamesStarted()));
		element.setAttribute("Minutes", CommonUtil.resoleEmptyParam(player.getMinutes()));
		element.setAttribute("Other", CommonUtil.resoleEmptyParam(player.getOther()));
		
		return element;
	}
	
	
	
	
	
}
