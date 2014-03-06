/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Player;
import com.ctvit.nba.entity.Team;
import com.ctvit.nba.expand.PlayerEnum;
import com.ctvit.nba.expand.TeamEnum;
import com.ctvit.nba.service.PlayerService;
import com.ctvit.nba.service.TeamService;
import com.ctvit.nba.service.impl.PlayerServiceImpl;
import com.ctvit.nba.service.impl.TeamServiceImpl;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;

/**
 * 球队信息的 action 类
 * @author 高青
 * 2014-2-25
 */
public class TeamAction extends BaseAction{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	
	/** 模块名称 */
	private String moduleName;
	
	/** 内部更新模块（链接的唯一标识） */
	private String innerUpdateModule;
	
	/** 返回到前台的 json 格式的字符串 */
	private String json;
	
	/** 选中的球队 id  */
	private String teamIDs;
	
	/** 内部更新条件的 map 对象 */
	private Map<String, String> innerConditionMap;
	
	/** 内部更新模块和更新条件的 map 对象 */
	private Map<String, Map<String, String>> innerUpdateModuleACondtions;
	
	/** 球队的服务类 */
	private TeamService teamService;
	
	/** 球员的服务类 */
	private PlayerService playerService;
	
	/** 球员 ID 字符串集 */
	private String playerIDs;
	
	/**
	 * 初始化对象
	 */
	{
		innerConditionMap = new HashMap<String, String>();
		innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
		teamService = new TeamServiceImpl();
		playerService = new PlayerServiceImpl();
	}
	
	/**
	 * 更新球队的排行信息及赛程信息
	 * @author 高青
	 * 2014-03-05
	 * @return void 空
	 */
	public void updateTeamRankASchedule(){
		//更新球队排名信息及赛程信息标识
		int updateTeamRankAScheduleFlag = 0;
		
		//更新球队排名及赛程信息
		updateTeamRankAScheduleFlag = commonUpdateMethod(teamService, "updateTeamRankASchedule", "team");
		
		//将更新后的结果反馈到前台
		unifyWriteJson2Web(updateTeamRankAScheduleFlag);
	}
	
	/**
	 * <p>更新球员的场均数据统计</p>
	 * <p>分别是对前7场、前15场、前30场、总场数的场均统计</p>
	 * @author 高青
	 * 2014-3-4
	 * @return void 空
	 */
	public void updatePlayerDetailAvgStat(){
		//更新球员的场均数据的标识
		int updatePlayerAvgStatFlag = 0;
		
		//更新球员的场均数据
		updatePlayerAvgStatFlag = commonUpdateMethod(playerService, "updatePlayerAvgStat", "playerAvgStat");
		
		//将更新后数据写到前台
		unifyWriteJson2Web(updatePlayerAvgStatFlag);
	}
	
	/**
	 * 更新球员的详细信息
	 * @author 高青
	 * 2014-2-28
	 * @return void 空
	 */
	public void updatePlayerDetail(){
		//更新球员详细信息的标识
		int updatePlayerDetailFlag = 0;
		
		//更新球员的详细信息
		updatePlayerDetailFlag = commonUpdateMethod(playerService, "updatePlayerDetailInfo", "playerDetail");
		
		//将更新后的结果，组织成 json 写到前台
		unifyWriteJson2Web(updatePlayerDetailFlag);
	}
	
	/**
	 * 更新指定球队下的球员列表的信息 
	 * @author 高青
	 * 2014-2-27
	 * @return void 空
	 */
	public void updateTeamPlayerInfo(){
		//更新成功标识
		int updateTeamPlayerInfoFlag = 0;
		
		//更新数据
		updateTeamPlayerInfoFlag = commonUpdateMethod(playerService, "updateTeamPlayerInfo", "teamPlayers");
		
		//将更新后的结果，写到前台
		unifyWriteJson2Web(updateTeamPlayerInfoFlag);
	}
	
	/**
	 * 球队的球员信息
	 * @author 高青
	 * 2014-2-26
	 * @return void 空
	 */
	public void getTeamPlayerInfo(){
		//组织内部更新模块和更新条件的 Map 对象
		if (teamIDs != null && !teamIDs.equals("")) {
			String[] teamArray = teamIDs.split(",");
			
			//更新到球员个人信息 到 XML 文件
			for (String teamID : teamArray) {
				//设置比赛球队
				if (teamID != null && teamID != "") {
					innerConditionMap.put("teamID", teamID);
				}
				innerUpdateModuleACondtions.put(innerUpdateModule, innerConditionMap);
				
				//得到 url 的数据
				Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
				String url = URLUtil.getURL(finalURLMap);
				String initJSON = URLContentUtil.getURLContent(url);
				
				//得到 JSON 数组
				JSONObject initJsonObject = new JSONObject(initJSON);
				
				//得到 AllTeams 的 JSONArray 对象
				JSONArray allTeamsArray = initJsonObject.getJSONArray("TeamPlayers");
				
				if(allTeamsArray != null && allTeamsArray.length() != 0){
					json = allTeamsArray.toString();
				}else {
					json = 0 + "";
				}
				//写到前台
				writeJson2Web(json);
			}
		}
	}
	
	/**
	 * 更新联赛的球队信息
	 * @author 高青
	 * 2014-2-25
	 * @return void
	 */
	public void updateAllTeamsInfo(){
		int updateAllTeamsInfoFlag = 0;
		
		//得到内部更新模块和更新条件 map 对象
		getInnerUpdateModuleACondtions(innerUpdateModule);
		
		//更新联赛的球队信息
		updateAllTeamsInfoFlag = teamService.updateAllTeamsInfo(moduleName, innerUpdateModuleACondtions, "allTeams", new Team());
		
		//更新成功标识写到前台
		if (updateAllTeamsInfoFlag == 1) {
			json = 1 + "";
		}else {
			json = 0 + "";
		}
		writeJson2Web(json);
	}
	
	/**
	 * 更新成功后，得到联赛球队信息
	 * @author 高青
	 * 2014-2-26
	 * @return void 空
	 */
	public void getAllTeamsInfo(){
		
		//得到更新条件
		getInnerUpdateModuleACondtions(innerUpdateModule);
		
		//得到 url 的数据
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		String url = URLUtil.getURL(finalURLMap);
		String initJSON = URLContentUtil.getURLContent(url);
		
		//得到 JSON 数组
		JSONObject initJsonObject = new JSONObject(initJSON);
		
		//得到 AllTeams 的 JSONArray 对象
		JSONArray allTeamsArray = initJsonObject.getJSONArray("AllTeams");
		
		if(allTeamsArray != null && allTeamsArray.length() != 0){
			json = allTeamsArray.toString();
		}else {
			json = 0 + "";
		}
		//写到前台
		writeJson2Web(json);
	}
	
	/**
	 * 更新全分区球队的信息
	 * @author 高青
	 * 2014-2-25
	 * @return void
	 */
	public void updateDivisionTeamsInfo(){
		
	}
	
	/**
	 * <p>通用更新方法</p>
	 * <strong>适合直接生成 XML 文件的通用方法</strong>
	 * @author 高青
	 * 2014-2-13
	 * @param T 泛型类型
	 * @param t 调用方法的实例类
	 * @param methodName 方法名称
	 * @return int commonUpdateFlag 通用成功标识
	 */
	private <T> int commonUpdateMethod(T t, String methodName) {
		//初始化通用成功标识：
		int commonUpdateFlag = 0;
		
		//组织内部更新模块和更新条件的 Map 对象
		if (teamIDs != null && !teamIDs.equals("")) {
			String[] teamArray = teamIDs.split(",");
			
			//更新到球员个人信息 到 XML 文件
			for (String teamID : teamArray) {
				innerConditionMap.put("teamID", teamID);
				
				//设置比赛球队
				if (teamID != null && teamID != "") {
					innerConditionMap.put("teamID", teamID);
				}
				
				innerUpdateModuleACondtions.put(innerUpdateModule, innerConditionMap);
				
				//得到指定类中的更新方法
				Method sepcifyMethod = null;
				try {
					sepcifyMethod = t.getClass().getMethod(methodName, String.class, String.class, Map.class);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				//执行指定的更新方法
				try {
					commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, teamID, innerUpdateModuleACondtions);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return commonUpdateFlag;
	}
	/**
	 * <p>通用更新方法</p>
	 * <strong>适合通过实体类集生成XML文件及保存到数据库的通用方法</strong>
	 * @author 高青
	 * 2014-2-13
	 * @param T 泛型类型
	 * @param TEntity 动态实体类类型
	 * @param t 调用方法的实例类
	 * @param methodName 方法名称
	 * @param updateModuleAlias 更新模块的别名
	 * @param te 实例化后的实体类类型
	 * @return int commonUpdateFlag 通用成功标识
	 */
	private <T>  int commonUpdateMethod(T t, String methodName, String updateModuleAlias) {
		//初始化通用成功标识：
		int commonUpdateFlag = 0;
		//选中的 ID 变量数组
		String[] selectedIDArray = null;
		
		//球队 ID 选中时：
		if (teamIDs != null && !teamIDs.equals("")) {
			selectedIDArray = teamIDs.split(",");
			
		//球员 ID 选中时：
		}else if (playerIDs != null && !playerIDs.equals("")) {
			selectedIDArray = playerIDs.split(",");
		}
		
		//组织内部更新模块和更新条件的 Map 对象
		if (selectedIDArray != null && selectedIDArray.length != 0) {
			
			//更新到球员个人信息 到 XML 文件
			for (String selectedID : selectedIDArray) {
				
				//球队 ID 选中时：
				if (teamIDs != null && !teamIDs.equals("")) {
					innerConditionMap.put("teamID", selectedID);
					
				//球员 ID 选中时：
				}else if (playerIDs != null && !playerIDs.equals("")) {
					innerConditionMap.put("playerID", selectedID);
				}
				innerUpdateModuleACondtions.put(innerUpdateModule, innerConditionMap);
				
				//得到指定类中的更新方法
				Method sepcifyMethod = null;
				
				try {
					//判断当前的更新模块
					if("teamPlayers".equals(updateModuleAlias) || 
						"playerDetail".equals(updateModuleAlias) || 
						"playerAvgStat".equals(updateModuleAlias)){
						Player player = new Player();
						//得到指定的方法
						sepcifyMethod = t.getClass().getMethod(methodName, String.class, String.class, Map.class, String.class, Player.class);
						//调用当前方法
						commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, selectedID, innerUpdateModuleACondtions, updateModuleAlias, player);
					}else if("team".equals(updateModuleAlias)){
						//得到指定的方法
						sepcifyMethod = t.getClass().getMethod(methodName, String.class, String.class, Map.class, String.class, Team.class);
						//调用当前方法
						commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, selectedID, innerUpdateModuleACondtions, updateModuleAlias, new Team());
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return commonUpdateFlag;
	}
	
	/**
	 * <p>统一将 json 写到前台的方法</p>
	 * <b>适合将全部的数据写到前台，再在前台进行挑取数据显示</b>
	 * @author 高青
	 * 2014-2-10
	 * @param update2XMLFlag 更新到 xml 文件成功标识
	 * @return void 空
	 */
	private void unifyWriteJson2Web(int update2XMLFlag) {
		//得到链接中的 JSON 数据
		if (update2XMLFlag == 1) {
			//得到 url 的数据
			Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
			String url = URLUtil.getURL(finalURLMap);
			json = URLContentUtil.getURLContent(url);
		} else {
			json = update2XMLFlag + "";
		}
		//返回更新的数据
		writeJson2Web(json);
	}
	
	/** @return the moduleName */
	@JSON(serialize = false)
	public String getModuleName() {
		return moduleName;
	}

	/** @param moduleName the moduleName to set */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** @return the innerUpdateModule */
	@JSON(serialize = false)
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule the innerUpdateModule to set */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}

	/** @return the json */
	public String getJson() {
		return json;
	}

	/** @param json the json to set */
	public void setJson(String json) {
		this.json = json;
	}

	/** @return the teamIDs */
	@JSON(serialize = false)
	public String getTeamIDs() {
		return teamIDs;
	}

	/** @param teamIDs the teamIDs to set */
	public void setTeamIDs(String teamIDs) {
		this.teamIDs = teamIDs;
	}

	/** @return the innerConditionMap */
	@JSON(serialize = false)
	public Map<String, String> getInnerConditionMap() {
		return innerConditionMap;
	}

	/** @param innerConditionMap the innerConditionMap to set */
	public void setInnerConditionMap(Map<String, String> innerConditionMap) {
		this.innerConditionMap = innerConditionMap;
	}

	/** @return the innerUpdateModuleACondtions */
	@JSON(serialize = false)
	public Map<String, Map<String, String>> getInnerUpdateModuleACondtions(String innerUpdateModule) {
		if (innerUpdateModule != null) {
			
			// 得到  内部 查询条件的Map 对象
			innerConditionMap = getInnerConditionMap();
			
			//赛程模块
			if (moduleName.equals("team")) {
				//将当前的更新模块 和内部查询条件的 Map 对象放到当前对象中
				innerUpdateModuleACondtions.put(TeamEnum.getTeamEnumByName(innerUpdateModule).toString(), innerConditionMap);
			}
			//球员信息模块
			if (moduleName.equals("player")) {
				innerUpdateModuleACondtions.put(PlayerEnum.getPlayerEnumByName(innerUpdateModule).toString(), innerConditionMap);
			}
		}
		return innerUpdateModuleACondtions;
	}

	/** @param innerUpdateModuleACondtions the innerUpdateModuleACondtions to set */
	public void setInnerUpdateModuleACondtions(
			Map<String, Map<String, String>> innerUpdateModuleACondtions) {
		this.innerUpdateModuleACondtions = innerUpdateModuleACondtions;
	}

	/** @return the playerIDs */
	public String getPlayerIDs() {
		return playerIDs;
	}

	/** @param playerIDs the playerIDs to set */
	public void setPlayerIDs(String playerIDs) {
		this.playerIDs = playerIDs;
	}
	
}
