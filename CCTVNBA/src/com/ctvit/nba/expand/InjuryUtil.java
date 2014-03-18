/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import org.jdom2.Element;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.ctvit.nba.entity.Injury;
import com.ctvit.nba.util.CommonUtil;

/**
 * 球员伤情的公用类
 * @author 高青
 * 2014-3-17
 */
public class InjuryUtil {
	
	/**
	 * 得到球员伤情的实体类数据对象
	 * @author 高青
	 * 2014-3-17
	 * @param innerUpdateModule_otherInfo 内部更新模块和其他附加信息的字符串数据
	 * @param injuryJsonObject 球员伤情的 JSONObject 数据对象
	 * @return injury 球员伤情实体数据对象
	 */
	public static Injury getInjury(String innerUpdateModule_otherInfo, JSONObject injuryJsonObject){
		//初始化球员伤情对象
		Injury injury = new Injury();
		
		//得到内部更新模块名称
		String innerUpdateModule = CommonUtil.getInnerUpdateModuleName(innerUpdateModule_otherInfo);
		//得到其他附加信息
		String otherInfo = CommonUtil.getOtherInfo(innerUpdateModule_otherInfo);
		
		if (injuryJsonObject != null && injuryJsonObject.length() != 0) {
			
			//得到球员伤情列表信息
			if ("INJURY_LIST".equals(innerUpdateModule)) {
				getInjuryList(injury, injuryJsonObject, innerUpdateModule);
			}
			
			//球队球员伤情列表信息
			if ("TEAM_INJURY_LIST".equals(innerUpdateModule)) {
				getTeamInjuryList(injury, injuryJsonObject, innerUpdateModule);
			}
		}
		injury.setOther(otherInfo);
		injury.setInnerUpdateModule(innerUpdateModule);
		
		return injury;
	}

	/**
	 * 得到球队球员伤情列表信息
	 * @author 高青
	 * 2014-3-18
	 * @param injury 球员伤情的实体类对象
	 * @param injuryJsonObject 球员伤情的 JSONOBject 数据对象
	 * @param innerUpdateModule 内部更新模块（更新模块的标识）
	 * @return void 空
	 */
	private static void getTeamInjuryList(Injury injury,
			JSONObject injuryJsonObject, String innerUpdateModule) {
		//通用数据设置
		commonInjuryInfo(injury, injuryJsonObject, innerUpdateModule);
	}

	/**
	 * 得到球员伤情列表信息
	 * @author 高青
	 * 2014-3-17
	 * @param injury 球员伤情的实体类对象
	 * @param injuryJsonObject 球员伤情的 JSONOBject 数据对象
	 * @param innerUpdateModule 内部更新模块（更新模块的标识）
	 * @return void 空
	 */
	private static void getInjuryList(Injury injury,
			JSONObject injuryJsonObject, String innerUpdateModule) {
		//通用数据
		commonInjuryInfo(injury, injuryJsonObject, innerUpdateModule);
	}
	


	/**
	 * 得到球员伤情信息的子元素集
	 * @author 高青
	 * 2014-3-17
	 * @param injuryList 球员伤情的实体类数据集
	 * @param updateModuleAlias 更新模块的别名
	 * @return injuryChildrenElementList 球员伤情子元素集
	 */
	public static List<Element> getInjuryInfoChildrenElementList(List<Injury> injuryList, String updateModuleAlias){
		//初始化子元素集
		List<Element> injuryChildrenElementList = new ArrayList<Element>();
		
		if (injuryList != null && injuryList.size() != 0) {
			for (Injury injury : injuryList) {
				
				//球员伤情列表
				if ("injuryList".equals(updateModuleAlias)) {
					bindInjuryListElement(injuryChildrenElementList, injury, updateModuleAlias);
				}
				
				//球队球员伤情列表
				if ("teamInjuryList".equals(updateModuleAlias)) {
					bindTeamInjuryListElement(injuryChildrenElementList, injury, updateModuleAlias);
				}
			}
		}
		return injuryChildrenElementList;
	}
	
	/**
	 * 绑定球队球员伤情列表信息
	 * @author 高青
	 * 2014-3-18
	 * @param injuryChildrenElementList 球员伤情子元素集
	 * @param injury 球员伤情实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindTeamInjuryListElement(
			List<Element> injuryChildrenElementList, Injury injury,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("teamInjuryList");
		
		//设置通用属性
		commonInjuryElementAttr(element, injury, updateModuleAlias);
		
		//添加到子元素集中
		injuryChildrenElementList.add(element);
	}

	/**
	 * 设置球员伤情列表子元素属性
	 * @author 高青
	 * 2014-3-17
	 * @param injuryChildrenElementList 球员伤情子元素集
	 * @param injury 球员伤情实体类数据
	 * @param updateModuleAlias 更新模块的别名
	 * @return void 空
	 */
	private static void bindInjuryListElement(
			List<Element> injuryChildrenElementList, Injury injury,
			String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("injuryList");
		
		//设置通用球员伤情属性
		commonInjuryElementAttr(element, injury, updateModuleAlias);
		
		//添加到子元素子元素集中
		injuryChildrenElementList.add(element);
	}

	/**
	 * 通用球员伤情属性
	 * @author 高青
	 * 2014-3-17
	 * @param injuryElement 球员伤情子元素对象
	 * @param injury 球员伤情实体类数据对象
	 * @param otherInfo 其他附加信息
	 * @return void 空
	 */
	private static void commonInjuryElementAttr(Element injuryElement, Injury injury,
			String otherInfo) {
		injuryElement.setAttribute("InjuryID", injury.getInjuryID());
		injuryElement.setAttribute("LeagueID", injury.getLeagueID());
		injuryElement.setAttribute("Season", injury.getSeason());
		injuryElement.setAttribute("LeagueENName", injury.getLeagueENName());
		injuryElement.setAttribute("LeagueENAlias", injury.getLeagueENAlias());
		injuryElement.setAttribute("LeagueCNName", injury.getLeagueCNName());
		injuryElement.setAttribute("LeagueCNAlias", injury.getLeagueCNAlias());
		injuryElement.setAttribute("PlayerID", injury.getPlayerID());
		injuryElement.setAttribute("FirstName", injury.getFirstName());
		injuryElement.setAttribute("LastName", injury.getLastName());
		injuryElement.setAttribute("PlayerCNName", injury.getPlayerCNName());
		injuryElement.setAttribute("PlayerCNAlias", injury.getPlayerCNAlias());
		injuryElement.setAttribute("SPortrait", injury.getsPortrait());
		injuryElement.setAttribute("LPortrait", injury.getlPortrait());
		injuryElement.setAttribute("Information", injury.getInformation());
		injuryElement.setAttribute("CNInformation", injury.getcNInformation());
		injuryElement.setAttribute("InjuryDate", injury.getInjuryDate().toString());
	}

	/**
	 * 通用球员伤情数据设置
	 * @author 高青
	 * 2014-3-17
	 * @param injury 球员伤情实体类数据对象
	 * @param injuryJsonObject 球员伤情 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void commonInjuryInfo(Injury injury,
			JSONObject injuryJsonObject, String innerUpdateModule) {
		injury.setInjuryID(CommonUtil.getStringValueByKey(injuryJsonObject, "InjuryID", "int"));
		injury.setLeagueID(CommonUtil.getStringValueByKey(injuryJsonObject, "LeagueID", "int"));
		injury.setSeason(CommonUtil.getStringValueByKey(injuryJsonObject, "Season", "int"));
		injury.setLeagueENName(CommonUtil.getStringValueByKey(injuryJsonObject, "LeagueENName", "String"));
		injury.setLeagueENAlias(CommonUtil.getStringValueByKey(injuryJsonObject, "LeagueENAlias", "String"));
		injury.setLeagueCNName(CommonUtil.getStringValueByKey(injuryJsonObject, "LeagueCNName", "String"));
		injury.setLeagueCNAlias(CommonUtil.getStringValueByKey(injuryJsonObject, "LeagueCNAlias", "String"));
		injury.setPlayerID(CommonUtil.getStringValueByKey(injuryJsonObject, "PlayerID", "int"));
		injury.setFirstName(CommonUtil.getStringValueByKey(injuryJsonObject, "FirstName", "String"));
		injury.setLastName(CommonUtil.getStringValueByKey(injuryJsonObject, "LastName", "String"));
		injury.setPlayerCNName(CommonUtil.getStringValueByKey(injuryJsonObject, "PlayerCNName", "String"));
		injury.setPlayerCNAlias(CommonUtil.getStringValueByKey(injuryJsonObject, "PlayerCNAlias", "String"));
		injury.setsPortrait(CommonUtil.getStringValueByKey(injuryJsonObject, "SPortrait", "String"));
		injury.setlPortrait(CommonUtil.getStringValueByKey(injuryJsonObject, "LPortrait", "String"));
		injury.setInformation(CommonUtil.getStringValueByKey(injuryJsonObject, "Information", "String"));
		injury.setcNInformation(CommonUtil.getStringValueByKey(injuryJsonObject, "CNInformation", "String"));
		injury.setInjuryDate(new Date(injuryJsonObject.getLong("InjuryDate")));
	}	
	
}	
