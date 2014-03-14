/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import oracle.net.aso.r;

import org.apache.struts2.json.annotations.JSON;
import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Player;
import com.ctvit.nba.entity.Team;
import com.ctvit.nba.expand.PlayerEnum;
import com.ctvit.nba.expand.TeamEnum;
import com.ctvit.nba.service.PlayerService;
import com.ctvit.nba.service.impl.PlayerServiceImpl;
import com.ctvit.nba.service.impl.TeamServiceImpl;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;

/**
 * 球员信息 action 类
 * @author 高青
 * 2014-3-12
 */
public class PlayerAction extends BaseAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 模块名称 */
	private String moduleName;
	
	/** 内部更新模块（链接的唯一标识） */
	private String innerUpdateModule;
	
	/** 返回到前台的 json 格式的字符串 */
	private String json;	
	
	/** 内部更新条件的 map 对象 */
	private Map<String, String> innerConditionMap;
	
	/** 内部更新模块和更新条件的 map 对象 */
	private Map<String, Map<String, String>> innerUpdateModuleACondtions;	
	
	/** 球员的服务类 */
	private PlayerService playerService;
	
	/** 统计类型 */
	private String rankType;
	
	/** 获取前 N 条记录 */
	private String topN;
	
	/** 每页记录数 */
	private String pageSize;
	
	/** 页数第几页 */
	private String pageIndex;
	
	/**
	 * 初始化对象
	 */
	{
		innerConditionMap = new HashMap<String, String>();
		innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
		playerService = new PlayerServiceImpl();
	}
	
	/**
	 * 更新每日球员排行信息
	 * @author 高青
	 * 2014-3-12
	 * @return void 空
	 */
	public void updateTodayPlayerRankInfo(){
		//更新球员 TopN 标识
		int updateTodayPlayerRankFlag = 0;
		
		updateTodayPlayerRankFlag = commonUpdateMethod(playerService, "updateTodayPlayerRankInfo", "todayRank");
		
		//将更新后的结果反馈到前台
		unifyWriteJson2Web(updateTodayPlayerRankFlag);
	}
	
	/**
	 * 更新球员 TopN 排行信息
	 * @author 高青
	 * 2014-3-12
	 * @return void 空
	 */
	public void updatePlayerTopNRankInfo(){
		//更新球员 TopN 标识
		int updatePlayerTopNRankFlag = 0;
		
		updatePlayerTopNRankFlag = commonUpdateMethod(playerService, "updatePlayerTopNRankInfo", "playerRankTopN");
		
		writeJson2Web(updatePlayerTopNRankFlag);
	}
	
	/**
	 * 得到球员 TopN 排行信息
	 * @author 高青
	 * 2014-3-12
	 * @return void 空
	 */
	public void getPlayerTopNRankInfo(){
		commonGetInfoAsTabLeData("PlayerRankTopN");
	}
	
	/**
	 * 通用获取数据的方法（获取表格数据的方法）
	 * @author 高青
	 * 2014-3-12
	 * @param moduleRemarker 模块标识
	 * @return void 空
	 */
	private void commonGetInfoAsTabLeData(String moduleRemarker) {
		//得到更新条件
		getInnerUpdateModuleACondtions(innerUpdateModule);
		
		//得到 url 的数据
		Map<String, Map<String, String>> finalURLMap = URLUtil.getFinalURLMap(moduleName, innerUpdateModuleACondtions);
		String url = URLUtil.getURL(finalURLMap);
		String initJSON = URLContentUtil.getURLContent(url);
		
		//得到 JSON 数组
		JSONObject initJsonObject = new JSONObject(initJSON);
		
		//得到 AllTeams 的 JSONArray 对象
		JSONArray jsonArray = initJsonObject.getJSONArray(moduleRemarker);
		
		if(jsonArray != null && jsonArray.length() != 0){
			json = jsonArray.toString();
		}else {
			json = 0 + "";
		}
		//写到前台
		writeJson2Web(json);
	}
	
	/**
	 * <p>通用更新方法</p>
	 * <strong>适合直接生成 XML 文件的通用方法</strong>
	 * @author 高青
	 * 2014-3-12
	 * @param T 泛型类型
	 * @param t 调用方法的实例类
	 * @param methodName 方法名称
	 * @return int commonUpdateFlag 通用成功标识
	 */
	private <T> int commonUpdateMethod(T t, String methodName) {
		//初始化通用成功标识：
		int commonUpdateFlag = 0;
				
		//得到指定类中的更新方法
		Method sepcifyMethod = null;
		try {
			sepcifyMethod = t.getClass().getMethod(methodName, String.class, Map.class);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		//执行指定的更新方法
		try {
			commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, innerUpdateModuleACondtions);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return commonUpdateFlag;
	}
	/**
	 * <p>通用更新方法</p>
	 * <strong>适合通过实体类集生成XML文件及保存到数据库的通用方法</strong>
	 * @author 高青
	 * 2014-3-12
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
		
		getInnerUpdateModuleACondtions(innerUpdateModule);
				
		//得到指定类中的更新方法
		Method sepcifyMethod = null;
		try {
			//得到指定的方法
			sepcifyMethod = t.getClass().getMethod(methodName, String.class, Map.class, String.class, Player.class);
			//调用当前方法
			commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, innerUpdateModuleACondtions, updateModuleAlias, new Player());
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

	/** @return the innerConditionMap */
	@JSON(serialize = false)
	public Map<String, String> getInnerConditionMap() {
		
		//统计类型参数
		if (rankType != null && !"".equals(rankType)) {
			innerConditionMap.put("rankType", rankType);
		}
		//获取前 N 条记录
		if (topN != null && !"".equals(topN)) {
			innerConditionMap.put("topN", topN);
		}
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

	/** @return the rankType */
	@JSON(serialize = false)
	public String getRankType() {
		return rankType;
	}

	/** @param rankType the rankType to set */
	public void setRankType(String rankType) {
		this.rankType = rankType;
	}

	/** @return the topN */
	@JSON(serialize = false)
	public String getTopN() {
		return topN;
	}

	/** @param topN the topN to set */
	public void setTopN(String topN) {
		this.topN = topN;
	}

	/** @return the pageSize */
	@JSON(serialize = false)
	public String getPageSize() {
		return pageSize;
	}

	/** @param pageSize the pageSize to set */
	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	/** @return the pageIndex */
	@JSON(serialize = false)
	public String getPageIndex() {
		return pageIndex;
	}

	/** @param pageIndex the pageIndex to set */
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}	
	
}
