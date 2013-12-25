/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ctvit.nba.dao.ScheduleDao;
import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.util.JDBCUtil;

/**
 * 赛程  Dao 的实现类
 * @author 高青
 * 2013-11-28
 */
public class ScheduleDaoImpl implements ScheduleDao {

	@Override
	public int updateSchedule2DB(List<Schedule> scheduleList) {
		int flag = 0;
		String sql = "";
		
		//操作数据库的对象
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		//执行更新操作
		try {
			connection = JDBCUtil.getConnection();
			
			//关闭自动提交
			connection.setAutoCommit(false);
			
			for (Schedule schedule : scheduleList) {
				//判断该条件下的数据是否存在
				Boolean remarker = judgeExsit(schedule.getScheduleID());
				
				//更新数据库数据  的  sql 语句
				sql = getSQL(schedule, remarker);
				preparedStatement = JDBCUtil.getPreparedStatement(connection, sql);
				
				//绑定参数
				boundParams(schedule, preparedStatement);
				preparedStatement.addBatch();
			}
			//批量更新
			preparedStatement.executeBatch();
			flag = 1;
			//提交操作
			connection.commit();
		} catch (SQLException e) {
			flag = 0;
			e.printStackTrace();
		}
		return flag;
	}
	
	/**
	 * 绑定更新数据的参数
	 * @author 高青
	 * 2013-12-5
	 * @param schedule 赛程对象
	 * @param preparedStatement 当前发送 SQL 对象
	 * @return void 执行绑定操作，不需返回值
	 */
	public void boundParams(Schedule schedule, PreparedStatement preparedStatement){
		//绑定参数
		try {
			preparedStatement.setString(0, schedule.getHomeCNAlias());
			preparedStatement.setString(1, schedule.getVisitingENName());
			preparedStatement.setString(2, schedule.getHomeENName());
			preparedStatement.setString(3, schedule.getHomeCNName());
			preparedStatement.setString(4, schedule.getVisitingSmallLogo());
			preparedStatement.setString(5, schedule.getHomeLargerLogo());
			preparedStatement.setInt(6, schedule.getHomeTeamScore());
			preparedStatement.setString(7, schedule.getVisitingLargerLogo());
			preparedStatement.setString(8, schedule.getHomeSmallLogo());
			preparedStatement.setString(9, schedule.getScheduleID());
			preparedStatement.setInt(10, schedule.getVisitingTeamScore());
			preparedStatement.setString(11, schedule.getHomeENAlias());
			preparedStatement.setString(12, schedule.getStatusCNName());
			preparedStatement.setString(13, schedule.getStatusENName());
			preparedStatement.setString(14, schedule.getVisitingTeamID());
			preparedStatement.setString(15, schedule.getVisitingCNAlias());
			preparedStatement.setDate(16, (Date) schedule.getMatchLocalTime());
			preparedStatement.setDate(17, (Date) schedule.getMatchGTM8Time());
			preparedStatement.setInt(18, schedule.getTotalQuarters());
			preparedStatement.setString(19, schedule.getVisitingCNName());
			preparedStatement.setString(20, schedule.getVisitingENAlias());
			preparedStatement.setString(21, schedule.getMatchTypeCNName());
			preparedStatement.setString(22, schedule.getMatchTypeENName());
			preparedStatement.setString(23, schedule.getHomeTeamID());
			preparedStatement.setString(24, schedule.getBroadcastName());
			preparedStatement.setString(25, schedule.getBestVedio());
			preparedStatement.setString(26, schedule.getBestImage());
			preparedStatement.setString(27, schedule.getRemarker());
			preparedStatement.setInt(28, schedule.getHomeScore());
			preparedStatement.setInt(29, schedule.getVisitingScore());
			preparedStatement.setInt(30, schedule.getQuarter());
			preparedStatement.setString(31, schedule.getOther());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 判断当前条件下的数据，是否存在
	 * @author 高青
	 * 2013-12-5
	 * @param scheduleID 赛程ID
	 * @return flag 存在与否标示符（false：不存在；ture：存在）
	 */
	public Boolean judgeExsit(String scheduleID){
		//默认不存在
		Boolean flag = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			//查询当前条件的数据，是否存在
			connection = JDBCUtil.getConnection();
			String sql = "select * from TAB_NBA_SCHEDULE where ScheduleID = ?";
			preparedStatement = JDBCUtil.getPreparedStatement(connection, sql);
			
			//绑定参数
			preparedStatement.setString(0, scheduleID);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeConnection(connection, preparedStatement, resultSet);
		}
		return flag;
	}
	
	/**
	 * 得到 执行的 sql 语句
	 * @author 高青
	 * 2013-12-5
	 * @param schedule 赛程实体对象
	 * @param remarker 更新或插入的标识
	 * @return sql sql语句
	 */
	public String getSQL(Schedule schedule, Boolean remarker){
		String sql = "";
		
		//更新
		if (remarker) {
			sql = "UPDATE TAB_NBA_SCHEDULE SET " 
			+"HomeCNAlias = ?," + "VisitingENName = ?, "
			+ "HomeENName = ?, " + "HomeCNName = ?, "
			+ "VisitingSmallLogo = ?, " + "HomeLargerLogo = ?, "
			+ "HomeTeamScore = ?, " + "VisitingLargerLogo = ?, "
			+ "HomeSmallLogo = ?, " + "ScheduleID = ?, "
			+ "VisitingTeamScore = ?, " + "HomeENAlias = ?, "
			+ "StatusCNName = ?, " + "StatusENName = ?, "
			+ "VisitingTeamID = ?, " + "VisitingCNAlias = ?, "
			+ "MatchLocalTime = ?, " + "MatchGTM8Time = ?, "
			+ "TotalQuarters = ?, " + "VisitingCNName = ?, "
			+ "VisitingENAlias = ?, " + "MatchTypeCNName = ?, "
			+ "MatchTypeENName = ?, " + "HomeTeamID = ?, "
			+ "BroadcastName = ?, " + "BestVedio = ?, "
			+ "BestImage = ?, " + "Remarker = ?, "
			+ "HomeScore = ?, " + "VisitingScore = ?, "
			+ "Quarter = ?, " + "Other = ? "
			;
			
		//插入
		} else {
			sql = "insert into TAB_NBA_SCHEDULE(HomeCNAlias, VisitingENName, HomeENName, " +
					"HomeCNName, VisitingSmallLogo, HomeLargerLogo, " +
					"HomeTeamScore, VisitingLargerLogo, HomeSmallLogo, " +
					"ScheduleID, VisitingTeamScore, HomeENAlias, " +
					"StatusCNName, StatusENName, VisitingTeamID, " +
					"VisitingCNAlias, MatchLocalTime, MatchGTM8Time, " +
					"TotalQuarters, VisitingCNName, VisitingENAlias, " +
					"MatchTypeCNName, MatchTypeENName, HomeTeamID, " +
					"BroadcastName, BestVedio, BestImage, Remarker, HomeScore, " +
					"VisitingScore, Quarter, Other ) values(" +
					"?,?,?,?,?," +
					"?,?,?,?,?," +
					"?,?,?,?,?," +
					"?,?,?,?,?," +
					"?,?,?,?,?,?,?," +
					"?,?,?,?,?)";
		}
		return sql;
	}

}
