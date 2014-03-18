/**
 * 0.0.0.1
 */
package com.ctvit.nba.service;

import java.util.Map;

import com.ctvit.nba.entity.Injury;

/**
 * 球员伤情 service 类
 * @author 高青
 * 2014-3-17
 */
public interface InjuryService {
	/**
	 * 更新球员伤情列表信息
	 * @author 高青
	 * 2014-3-17
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param injury 球员伤情实体类对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateInjuryList(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Injury injury);
	
	/**
	 * 更新球队球员伤情列表信息
	 * @author 高青
	 * 2014-3-18
	 * @param moduleName 模块名称
	 * @param innerUpdateModuleACondtions 内部更新模块名称和更新条件的 Map 对象
	 * @param otherInfo 其他附加信息
	 * @param injury 球员伤情实体类对象
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updateTeamInjuryList(
			String moduleName, 
			Map<String, Map<String, String>> innerUpdateModuleACondtions, 
			String otherInfo,
			Injury injury);
}
