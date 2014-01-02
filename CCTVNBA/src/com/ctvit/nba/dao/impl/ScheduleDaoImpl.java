/**
 * 0.0.0.1
 */
package com.ctvit.nba.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;

import com.ctvit.nba.dao.ScheduleDao;
import com.ctvit.nba.entity.Schedule;
import com.ctvit.nba.util.JDBCUtil;

/**
 * 赛程  Dao 的实现类
 * @author 高青
 * 2013-11-28
 */
public class ScheduleDaoImpl implements ScheduleDao {
	
	private Logger log = Logger.getLogger(ScheduleDaoImpl.class);

	@Override
	public int updateSchedule2DB(List<Schedule> scheduleList) {
		int flag = 0;
		String sql = "";
		
		//操作数据库的对象
		Connection connection = null;
		Statement statement = null;
		
		//赛程编号
		String scheduleID = "";
		
		//执行更新操作
		try {
			connection = JDBCUtil.getConnection();
			
			//关闭自动提交
			connection.setAutoCommit(false);
			//得到 发送 SQL 语句对象
			statement = JDBCUtil.getStatement(connection);
			
			for (Schedule schedule : scheduleList) {
				scheduleID = schedule.getScheduleID();
				
				//判断该条件下的数据是否存在
				Boolean remarker = judgeExsit(schedule.getScheduleID(), connection);
				
				//更新数据库数据  的  sql 语句
				sql = getSQL(schedule, remarker);
				
				statement.addBatch(sql);
			}
			//批量更新
			int[] executeBatch = statement.executeBatch();
			
			System.out.println("更新了 " + executeBatch.length + "条数据！");
			flag = 1;
			//提交操作
			connection.commit();
		} catch (SQLException e) {
			log.info("当前赛程编号为 " + scheduleID + "的，出现异常！");
			
			flag = 0;
			e.printStackTrace();
		}finally{
			JDBCUtil.closeConnection(connection, statement, null);
		}
		return flag;
	}
	
	
	
	/**
	 * 转换值为 NULL 时的值
	 * @author 高青
	 * 2013-12-31
	 * @param value 被转化的值
	 * @return int switched转化后的值
	 */
	public int switchNullSituation(Integer value){
		//转化后的值
		int switched = 0;
		
		if (value != null) {
			switched = value;
		} 
		return switched;
	}
	
	/**
	 * 判断当前条件下的数据，是否存在
	 * @author 高青
	 * 2013-12-5
	 * @param scheduleID 赛程ID
	 * @param connection 当前数据库连接对象
	 * @return flag 存在与否标示符（false：不存在；ture：存在）
	 */
	public Boolean judgeExsit(String scheduleID, Connection connection){
		//默认不存在
		Boolean flag = false;
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			//查询当前条件的数据，是否存在
			String sql = "select * from TAB_NBA_SCHEDULE where ScheduleID = ?";
			preparedStatement = JDBCUtil.getPreparedStatement(connection, sql);
			
			//绑定参数
			preparedStatement.setString(1, scheduleID);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCUtil.closeConnection(null, preparedStatement, resultSet);
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
			preparedStatement.setString(1, schedule.getHomeCNAlias());
			preparedStatement.setString(2, schedule.getVisitingENName());
			preparedStatement.setString(3, schedule.getHomeENName());
			preparedStatement.setString(4, schedule.getHomeCNName());
			preparedStatement.setString(5, schedule.getVisitingSmallLogo());
			preparedStatement.setString(6, schedule.getHomeLargerLogo());
			preparedStatement.setInt(7, switchNullSituation(schedule.getHomeTeamScore()));
			preparedStatement.setString(8, schedule.getVisitingLargerLogo());
			preparedStatement.setString(9, schedule.getHomeSmallLogo());
			preparedStatement.setString(10, schedule.getScheduleID());
			preparedStatement.setInt(11, switchNullSituation(schedule.getVisitingTeamScore()));
			preparedStatement.setString(12, schedule.getHomeENAlias());
			preparedStatement.setString(13, schedule.getStatusCNName());
			preparedStatement.setString(14, schedule.getStatusENName());
			preparedStatement.setString(15, schedule.getVisitingTeamID());
			preparedStatement.setString(16, schedule.getVisitingCNAlias());
			preparedStatement.setDate(17, new Date(schedule.getMatchLocalTime().getTime()));
			preparedStatement.setDate(18, new Date(schedule.getMatchGTM8Time().getTime()));
			preparedStatement.setInt(19, switchNullSituation(schedule.getTotalQuarters()));
			preparedStatement.setString(20, schedule.getVisitingCNName());
			preparedStatement.setString(21, schedule.getVisitingENAlias());
			preparedStatement.setString(22, schedule.getMatchTypeCNName());
			preparedStatement.setString(23, schedule.getMatchTypeENName());
			preparedStatement.setString(24, schedule.getHomeTeamID());
			preparedStatement.setString(25, schedule.getBroadcastName());
			preparedStatement.setString(26, schedule.getBestVedio());
			preparedStatement.setString(27, schedule.getBestImage());
			preparedStatement.setString(28, schedule.getRemarker());
			preparedStatement.setInt(29, switchNullSituation(schedule.getHomeScore()));
			preparedStatement.setInt(30, switchNullSituation(schedule.getVisitingScore()));
			preparedStatement.setInt(31, switchNullSituation(schedule.getQuarter()));
			preparedStatement.setString(32, schedule.getOther());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 得到 执行的 sql 语句
	 * @author 高青
	 * 2013-12-5
	 * @param remarker 更新或插入的标识
	 * @return sql sql语句
	 */
	public String getSQL(Schedule schedule, Boolean remarker){
		String sql = "";
		
		//更新
		if (remarker) {
			sql = "UPDATE TAB_NBA_SCHEDULE SET " 
			+"HomeCNAlias = '"+schedule.getHomeCNAlias()+"'," 
			+ "VisitingENName = '"+schedule.getVisitingENName()+"', "
			+ "HomeENName = '"+schedule.getHomeENName()+"', " 
			+ "HomeCNName = '"+schedule.getHomeCNName()+"', "
			+ "VisitingSmallLogo = '"+schedule.getVisitingSmallLogo()+"', " 
			+ "HomeLargerLogo = '"+schedule.getHomeLargerLogo()+"', "
			+ "HomeTeamScore = "+switchNullSituation(schedule.getHomeTeamScore())+", " 
			+ "VisitingLargerLogo = '"+schedule.getVisitingLargerLogo()+"', "
			+ "HomeSmallLogo = '"+schedule.getHomeSmallLogo()+"', " + " "
			+ "VisitingTeamScore = "+switchNullSituation(schedule.getVisitingTeamScore())+", " 
			+ "HomeENAlias = '"+schedule.getHomeENAlias()+"', "
			+ "StatusCNName = '"+schedule.getStatusCNName()+"', " 
			+ "StatusENName = '"+schedule.getStatusENName()+"', "
			+ "VisitingTeamID = '"+schedule.getVisitingTeamID()+"', " 
			+ "VisitingCNAlias = '"+schedule.getVisitingCNAlias()+"', "
			+ "MatchLocalTime = to_date('"+new Date(schedule.getMatchLocalTime().getTime())+"','yyyy-MM-dd'), " 
			+ "MatchGTM8Time = to_date('"+new Date(schedule.getMatchGTM8Time().getTime())+"','yyyy-MM-dd'), "
			+ "TotalQuarters = "+switchNullSituation(schedule.getTotalQuarters())+", " 
			+ "VisitingCNName = '"+schedule.getVisitingCNName()+"', "
			+ "VisitingENAlias = '"+schedule.getVisitingENAlias()+"', " 
			+ "MatchTypeCNName = '"+schedule.getMatchTypeCNName()+"', "
			+ "MatchTypeENName = '"+schedule.getMatchTypeENName()+"', " 
			+ "HomeTeamID = '"+schedule.getHomeTeamID()+"', "
			+ "BroadcastName = '"+schedule.getBroadcastName()+"', " 
			+ "BestVedio = '"+schedule.getBestVedio()+"', "
			+ "BestImage = '"+schedule.getBestImage()+"', " 
			+ "Remarker = '"+schedule.getRemarker()+"', "
			+ "HomeScore = "+switchNullSituation(schedule.getHomeScore())+", " 
			+ "VisitingScore = "+switchNullSituation(schedule.getVisitingScore())+", "
			+ "Quarter = "+switchNullSituation(schedule.getQuarter())+", " 
			+ "Other = '"+schedule.getOther()+"' where SCHEDULEID='"+schedule.getScheduleID()+"' "
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
					"VisitingScore, Quarter, Other ) values(" + "'"
					+schedule.getHomeCNAlias()+"', '"
					+schedule.getVisitingENName()+"', '"
					+schedule.getHomeENName()+"', '"
					+schedule.getHomeCNName()+"', '"
					+schedule.getVisitingSmallLogo()+"', " +" '"
					+schedule.getHomeLargerLogo()+"', "
					+switchNullSituation(schedule.getHomeTeamScore())+", '"
					+schedule.getVisitingLargerLogo()+"', '"
					+schedule.getHomeSmallLogo()+"', '"
					+schedule.getScheduleID()+"', " +" "
					+switchNullSituation(schedule.getVisitingTeamScore())+", '"
					+schedule.getHomeENAlias()+"', '"
					+schedule.getStatusCNName()+"', '"
					+schedule.getStatusENName()+"', '"
					+schedule.getVisitingTeamID()+"', " +" '"
					+schedule.getVisitingCNAlias()+"', to_date('"
					+new Date(schedule.getMatchLocalTime().getTime())+"','yyyy-MM-dd'), to_date('"
					+new Date(schedule.getMatchGTM8Time().getTime())+"','yyyy-MM-dd'), "
					+switchNullSituation(schedule.getTotalQuarters())+", '"
					+schedule.getVisitingCNName()+"', " +" '"
					+schedule.getVisitingENAlias()+"', '"
					+schedule.getMatchTypeCNName()+"', '"+
					schedule.getMatchTypeENName()+"', '"
					+schedule.getHomeTeamID()+"', '"
					+schedule.getBroadcastName()+"', '"
					+schedule.getBestVedio()+"', '"
					+schedule.getBestImage()+"', " +" '"
					+schedule.getRemarker()+"', "
					+switchNullSituation(schedule.getHomeScore())+", "
					+switchNullSituation(schedule.getVisitingScore())+", "
					+switchNullSituation(schedule.getQuarter())+", '"
					+schedule.getOther()+"') ";
		}
		return sql;
	}

}
