/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ctvit.nba.entity.Transaction;
import com.ctvit.nba.expand.TransactionEnum;
import com.ctvit.nba.service.TransactionService;
import com.ctvit.nba.service.impl.TransactionServiceImpl;
import com.ctvit.nba.util.URLContentUtil;
import com.ctvit.nba.util.URLUtil;


/**
 * 球员转会列表的 action 类
 * @author 高青
 * 2014-3-18
 */
public class TransactionAction extends BaseAction{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	/** 模块名称 */
	private String moduleName;
	
	/** 内部更新模块 */
	private String innerUpdateModule;
	
	/** 球队 ID */
	private String teamID;
	
	/** 返回到前台的 json 格式的字符串 */
	private String json;	
	
	/** 内部更新条件的 map 对象 */
	private Map<String, String> innerConditionMap;
	
	/** 内部更新模块和更新条件的 map 对象 */
	private Map<String, Map<String, String>> innerUpdateModuleACondtions;	
	
	/** 伤情 service */
	private TransactionService transactionService;
	
	/**
	 * 初始化对象
	 */
	{
		innerConditionMap = new HashMap<String, String>();
		innerUpdateModuleACondtions = new HashMap<String, Map<String,String>>();
		transactionService = new TransactionServiceImpl();
	}
	
	/**
	 * 得到球队球员转会信息 
	 * @author 高青
	 * 2014-3-19
	 * @return void 空
	 */
	public void getTeamTransactionList(){
		commonGetInfoAsTabLeData("TeamTransactionList");
	}
	
	/**
	 * 更新球队球员转会信息
	 * @author 高青
	 * 2014-3-19
	 * @return void 空
	 */
	public void updateTeamTransactionList(){
		//更新球队球员转会信息标识
		int updateTeamTransactionListFlag = 0;
		
		//更新球队球员转会
		updateTeamTransactionListFlag = commonUpdateMethod(
				transactionService, 
				"updateTeamTransactionList", 
				"teamTransactionList");
		
		//将更新后的情况反馈到前端
		writeJson2Web(updateTeamTransactionListFlag);
	}
	
	/**
	 * 更新球员转会列表信息
	 * @author 高青
	 * 2014-3-18
	 * @return void 空
	 */
	public void updateTransactionList(){
		//球员转会列表信息的标识
		int updateTransactionFlag = 0;
		
		updateTransactionFlag = commonUpdateMethod(transactionService, "updateTransactionList", "transactionList");
		
		//将更新后的结果反馈到前台
		writeJson2Web(updateTransactionFlag + "");
	}
	
	/**
	 * 得到球员转会列表信息
	 * @author 高青
	 * 2014-3-18
	 * @return void 空
	 */
	public void getTransactionList(){
		commonGetInfoAsTabLeData("TransactionList");
	}
	
	/**
	 * 通用获取数据的方法（获取表格数据的方法）
	 * @author 高青
	 * 2014-3-18
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
	 * 2014-3-18
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
	 * 2014-3-18
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
			sepcifyMethod = t.getClass().getMethod(methodName, String.class, Map.class, String.class, Transaction.class);
			//调用当前方法
			commonUpdateFlag = (Integer) sepcifyMethod.invoke(t, moduleName, innerUpdateModuleACondtions, updateModuleAlias, new Transaction());
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
	public String getModuleName() {
		return moduleName;
	}

	/** @param moduleName the moduleName to set */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/** @return the innerUpdateModule */
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule the innerUpdateModule to set */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}

	/** @return the teamID */
	public String getTeamID() {
		return teamID;
	}

	/** @param teamID the teamID to set */
	public void setTeamID(String teamID) {
		this.teamID = teamID;
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
	public Map<String, String> getInnerConditionMap() {
		//球队 ID
		if (teamID != null && !"".equals(teamID)) {
			innerConditionMap.put("teamID", teamID);
		}
		
		return innerConditionMap;
	}

	/** @param innerConditionMap the innerConditionMap to set */
	public void setInnerConditionMap(Map<String, String> innerConditionMap) {
		this.innerConditionMap = innerConditionMap;
	}

	/** @return the innerUpdateModuleACondtions */
	public Map<String, Map<String, String>> getInnerUpdateModuleACondtions(String innerUpdateModule) {
		
		//得到内部更新条件的 map 数据对象
		getInnerConditionMap();
		
		//根据内部更新模块名称，得到对应的枚举类型的更新标识
		innerUpdateModuleACondtions.put(
				TransactionEnum.getTransactionEnumByName(innerUpdateModule).toString(), 
				innerConditionMap
		);		
		
		return innerUpdateModuleACondtions;
	}

	/** @param innerUpdateModuleACondtions the innerUpdateModuleACondtions to set */
	public void setInnerUpdateModuleACondtions(
			Map<String, Map<String, String>> innerUpdateModuleACondtions) {
		this.innerUpdateModuleACondtions = innerUpdateModuleACondtions;
	}	
}
