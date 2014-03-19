/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.json.JSONObject;

import com.ctvit.nba.entity.Transaction;
import com.ctvit.nba.util.CommonUtil;

/**
 * 球员转会通用方法类
 * @author 高青
 * 2014-3-18
 */
public class TransactionUtil {
	
	/**
	 * 得到球员转会的实体类数据对象
	 * @author 高青
	 * 2014-3-18
	 * @param innerUpdateModule_otherInfo 内部更新模块和其他附加信息的字符串数据
	 * @param transactionJsonObject 球员转会的 JSONObject 数据对象
	 * @return transaction 球员转会实体数据对象
	 */	
	public static Transaction getTransaction(
			String innerUpdateModule_otherInfo, 
			JSONObject transactionJsonObject
		){
		//初始化球员转会对象
		Transaction transaction = new Transaction();
		
		//得到内部更新模块名称
		String innerUpdateModule = CommonUtil.getInnerUpdateModuleName(innerUpdateModule_otherInfo);
		//得到其他附加信息
		String otherInfo = CommonUtil.getOtherInfo(innerUpdateModule_otherInfo);
		
		if (transactionJsonObject != null && transactionJsonObject.length() != 0) {
			
			//球员转会列表信息
			if ("TRANSACTION_LIST".equals(innerUpdateModule)) {
				getTransactionList(transaction, transactionJsonObject, innerUpdateModule);
			}
			
			//球队球员转会列表信息
			if ("TEAM_TRANSACTION_LIST".equals(innerUpdateModule)) {
				getTeamTransactionList(transaction, transactionJsonObject, innerUpdateModule);
			}
		}
		transaction.setOther(otherInfo);
		transaction.setInnerUpdateModule(innerUpdateModule);
		
		return transaction;
	}
	
	/**
	 * 得到球队球员转会信息的列表信息
	 * @author 高青
	 * 2014-3-19
	 * @param transaction 球员转会的实体类对象
	 * @param transactionJsonObject 球员转会的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getTeamTransactionList(Transaction transaction,
			JSONObject transactionJsonObject, String innerUpdateModule) {
		//设置通用数据
		commonTransaction(transaction, transactionJsonObject, innerUpdateModule);
	}

	/**
	 * 得到球员转会的子元素集
	 * @author 高青
	 * 2014-3-18
	 * @param transactionList 球员转会的实体类集
	 * @param updateModuleAlias 更新模块的别名
	 * @return transactionChildrenElementList 球员转会子元素集 
	 */
	public static List<Element> getTransactionInfoChildrenElementList(List<Transaction> transactionList, String updateModuleAlias){
		//初始化子元素集
		List<Element> transactionChildrenElementList = new ArrayList<Element>();
		
		if (transactionList != null && transactionList.size() != 0) {
			for (Transaction transaction : transactionList) {
				
				//球员转会列表信息
				if ("transactionList".equals(updateModuleAlias)) {
					bindTransactionListElement(transactionChildrenElementList, transaction, updateModuleAlias);
				}
				
				//球队球员转会列表信息
				if ("teamTransactionList".equals(updateModuleAlias)) {
					bindTeamTransactionListElement(transactionChildrenElementList, transaction, updateModuleAlias);
				}
			}
		}
		return transactionChildrenElementList;
	}

	/**
	 * 绑定球队球员转会信息的子元素属性
	 * @author 高青
	 * 2014-3-19
	 * @param transactionChildrenElementList 球员转会的子元素集
	 * @param transaction 球员转会的实体类对象
	 * @param updateModuleAlias 球员转会的更新模块别名
	 * @return void 空
	 */
	private static void bindTeamTransactionListElement(
			List<Element> transactionChildrenElementList,
			Transaction transaction, String updateModuleAlias) {
		//初始化子元素集
		Element element = new Element("teamTransactionList");
		
		//设置通用子元素属性
		commonTransactionElementAttr(element, transaction, updateModuleAlias);
		
		//添加到子元素集中
		transactionChildrenElementList.add(element);
	}

	/**
	 * 绑定球员转会列表的子元素属性
	 * @author 高青
	 * 2014-3-18
	 * @param transactionChildrenElementList 球员转会的子元素集
	 * @param transaction 球员转会的实体类对象
	 * @param updateModuleAlias 球员转会的更新模块别名
	 * @return void 空
	 */
	private static void bindTransactionListElement(
			List<Element> transactionChildrenElementList,
			Transaction transaction, String updateModuleAlias) {
		//初始化子元素对象
		Element element = new Element("transactionList");
		
		//设置通用属性
		commonTransactionElementAttr(element, transaction, updateModuleAlias);
		
		//添加子元素集中
		transactionChildrenElementList.add(element);
	}

	/**
	 * 球员转会通用子元素属性设置
	 * @author 高青
	 * 2014-3-18
	 * @param element 球员转会子元素对象
	 * @param transaction 球员转会实体类对象
	 * @param updateModuleAlias 球员转会更新模块的别名
	 * @return void 空
	 */
	private static void commonTransactionElementAttr(Element element,
			Transaction transaction, String updateModuleAlias) {
		element.setAttribute("PlayerID", transaction.getPlayerID());
		element.setAttribute("PlayerCNName", transaction.getPlayerCNName());
		element.setAttribute("PlayerCNAlias", transaction.getPlayerCNAlias());
		element.setAttribute("PositionID", transaction.getPositionID());
		element.setAttribute("PositionName", transaction.getPositionName());
		element.setAttribute("PositionDescription", transaction.getPositionDescription());
		element.setAttribute("FirstName", transaction.getFirstName());
		element.setAttribute("LastName", transaction.getLastName());
		element.setAttribute("HandleTypeID", transaction.getHandleTypeID());
		element.setAttribute("HandleTeamID", transaction.getHandleTeamID());
		element.setAttribute("HandleTeamCNName", transaction.getHandleTeamCNName());
		element.setAttribute("HandleTeamCNAlias", transaction.getHandleTeamCNAlias());
		element.setAttribute("HandleTeamENName", transaction.getHandleTeamENName());
		element.setAttribute("HandleTeamENAlias", transaction.getHandleTeamENAlias());
		element.setAttribute("FromTeamID", transaction.getFromTeamID());
		element.setAttribute("FromTeamCNName", transaction.getFromTeamCNName());
		element.setAttribute("FromTeamCNAlias", transaction.getFromTeamCNAlias());
		element.setAttribute("FromTeamENName", transaction.getFromTeamENName());
		element.setAttribute("FromTeamENAlias", transaction.getFromTeamENAlias());
		element.setAttribute("HandleType", transaction.getHandleType());
		element.setAttribute("HandleDate", transaction.getHandleDate().toString());
		element.setAttribute("Summary", transaction.getSummary());
		element.setAttribute("CNSummary", transaction.getcNSummary());
		element.setAttribute("Other", transaction.getOther());
		element.setAttribute("InnerUpdateModule", transaction.getInnerUpdateModule());
	}

	/**
	 * 得到球员转会列表信息
	 * @author 高青
	 * 2014-3-18
	 * @param transaction 球员转会的实体类对象
	 * @param transactionJsonObject 球员转会的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void getTransactionList(Transaction transaction,
			JSONObject transactionJsonObject, String innerUpdateModule) {
		//通用数据
		commonTransaction(transaction, transactionJsonObject, innerUpdateModule);
	}

	/**
	 * 球员转会的通用数据设置
	 * @author 高青
	 * 2014-3-18
	 * @param transaction 球员转会的实体类对象
	 * @param transactionJsonObject 球员转会的 JSONObject 数据对象
	 * @param innerUpdateModule 内部更新模块
	 * @return void 空
	 */
	private static void commonTransaction(Transaction transaction,
			JSONObject transactionJsonObject, String innerUpdateModule) {
		transaction.setPlayerID(CommonUtil.getStringValueByKey(transactionJsonObject, "PlayerID", "int"));
		transaction.setPlayerCNName(CommonUtil.getStringValueByKey(transactionJsonObject, "PlayerCNName", "String"));
		transaction.setPlayerCNAlias(CommonUtil.getStringValueByKey(transactionJsonObject, "PlayerCNAlias", "String"));
		transaction.setPositionID(CommonUtil.getStringValueByKey(transactionJsonObject, "PositionID", "String"));
		transaction.setPositionName(CommonUtil.getStringValueByKey(transactionJsonObject, "PositionName", "String"));
		transaction.setPositionDescription(CommonUtil.getStringValueByKey(transactionJsonObject, "PositionDescription", "String"));
		transaction.setFirstName(CommonUtil.getStringValueByKey(transactionJsonObject, "FirstName", "String"));
		transaction.setLastName(CommonUtil.getStringValueByKey(transactionJsonObject, "LastName", "String"));
		transaction.setHandleTypeID(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTypeID", "int"));
		transaction.setHandleTeamID(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTeamID", "int"));
		transaction.setHandleTeamCNName(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTeamCNName", "String"));
		transaction.setHandleTeamCNAlias(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTeamCNAlias", "String"));
		transaction.setHandleTeamENName(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTeamENName", "String"));
		transaction.setHandleTeamENAlias(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleTeamENAlias", "String"));
		transaction.setFromTeamID(CommonUtil.getStringValueByKey(transactionJsonObject, "FromTeamID", "int"));
		transaction.setFromTeamCNName(CommonUtil.getStringValueByKey(transactionJsonObject, "FromTeamCNName", "String"));
		transaction.setTransactionID(CommonUtil.getStringValueByKey(transactionJsonObject, "TransactionID", "int"));
		transaction.setFromTeamCNAlias(CommonUtil.getStringValueByKey(transactionJsonObject, "FromTeamCNAlias", "String"));
		transaction.setFromTeamENName(CommonUtil.getStringValueByKey(transactionJsonObject, "FromTeamENName", "String"));
		transaction.setFromTeamENAlias(CommonUtil.getStringValueByKey(transactionJsonObject, "FromTeamENAlias", "String"));
		transaction.setHandleType(CommonUtil.getStringValueByKey(transactionJsonObject, "HandleType", "String"));
		transaction.setHandleDate(new Date(transactionJsonObject.getLong("HandleDate")));
		transaction.setSummary(CommonUtil.getStringValueByKey(transactionJsonObject, "Summary", "String"));
		transaction.setcNSummary(CommonUtil.getStringValueByKey(transactionJsonObject, "CNSummary", "String"));
	}
}
