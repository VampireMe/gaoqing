/**
 * 0.0.0.1
 */
package com.ctvit.nba.expand;

/**
 * 球员枚举类型
 * @author 高青
 * 2014-1-20
 */
public enum PlayerEnum {

	/** 获取指定球队球员列表 */
	TEAM_PLAYERS,
	
	/** 查询球员的详细信息 */
	PLAYER_DETAIL,
	
	/** 球员TopN数据排行榜 */
	PLAYER_RANK_TOPN,
	
	/** 球员分页数据排行榜 */
	PLAYER_RANK_PAGE,
	
	/** 每日球员分类排行榜 */
	TODAY_RANK,
	
	/** 本场比赛球员个人数据统计 */
	LIVE_PLAYER_STAT,
	
	/** 本场最佳球员和本场之星 */
	BEST_PLAYER,
	
	/** 本赛季球员场均统计（按位置查询、可选排序条件） */
	PLAY_STAT_AVG,
	
	/** 本赛季该球员场均统计（共四条记录，分别是对前7场、前15场、前30场、总场数的场均统计） */
	PLAYER_AVG_STAT,
	
	/** 本赛季该球员场均统计（共四条记录，分别是对前7场、前15场、前30场、总场数的场均统计）以及该球员的基本信息 */
	PLAYER;
	
	/** 内部更新模块名称（链接地址的唯一标识） */
	private String innerUpdateModule;
	
	private PlayerEnum(){}
	
	private PlayerEnum(String innerUpdateModule){
		this.innerUpdateModule = innerUpdateModule;
	}
	
	/**
	 * 通过内部更新模块名称（链接标识符），得到当前链接的枚举值
	 * @author 高青
	 * 2014-1-20
	 * @param innerUpdateModule 内部更新模块名称
	 * @return PlayerEnum 得到对应的枚举类型
	 */
	public static PlayerEnum getPlayerEnumByName(String innerUpdateModule){
		if (innerUpdateModule != null) {
			if (innerUpdateModule.equals("TEAM_PLAYERS")) {
				return PlayerEnum.TEAM_PLAYERS;
			} else if (innerUpdateModule.equals("PLAYER_DETAIL")) {
				return PlayerEnum.PLAYER_DETAIL;
			}else if (innerUpdateModule.equals("PLAYER_RANK_TOPN")) {
				return PlayerEnum.PLAYER_RANK_TOPN;
			}else if (innerUpdateModule.equals("PLAYER_RANK_PAGE")) {
				return PlayerEnum.PLAYER_RANK_PAGE;
			}else if (innerUpdateModule.equals("TODAY_RANK")) {
				return PlayerEnum.TODAY_RANK;
			}else if (innerUpdateModule.equals("LIVE_PLAYER_STAT")) {
				return PlayerEnum.LIVE_PLAYER_STAT;
			}else if (innerUpdateModule.equals("BEST_PLAYER")) {
				return PlayerEnum.BEST_PLAYER;
			}else if (innerUpdateModule.equals("PLAY_STAT_AVG")) {
				return PlayerEnum.PLAY_STAT_AVG;
			}else if (innerUpdateModule.equals("PLAYER_AVG_STAT")) {
				return PlayerEnum.PLAYER_AVG_STAT;
			}else if (innerUpdateModule.equals("PLAYER")) {
				return PlayerEnum.PLAYER;
			}
		}
		return null;
	}

	/** @return the innerUpdateModule */
	public String getInnerUpdateModule() {
		return innerUpdateModule;
	}

	/** @param innerUpdateModule the innerUpdateModule to set */
	public void setInnerUpdateModule(String innerUpdateModule) {
		this.innerUpdateModule = innerUpdateModule;
	}
	
}
