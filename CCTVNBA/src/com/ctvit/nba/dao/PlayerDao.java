/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao;

import java.util.List;

import com.ctvit.nba.entity.Player;

/**
 * 球员信息的 dao
 * @author 高青
 * 2014-1-20
 */
public interface PlayerDao {
	
	/**
	 * 将球员信息更新到数据库中
	 * @author 高青
	 * 2014-1-21
	 * @param playerPersonalList 球员信息的数据集
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public int updatePlayerPersonal2DB(List<Player> playerPersonalList);
	
	
	
	
	
	
	
}
