/**
 * 0.0.0.1
 */
package ${packageName}.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List; 

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.codegenerator.util.ConnectionPool;
import com.codegenerator.entity.Database;

/**
 * ${module} 的 Dao 接口
 * @author ${author}
 * ${date}
 */
@Repository
public class ${module}DaoImpl implements ${module}Dao{
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(${module}DaoImpl.class);
	
	@Override
	public int add${module}(${module} ${param}, Database database){
		//添加成功标识
		int successFlag = 0;
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		
		// *********** 新增的 SQL 语句（手动编写的部分）***********
		String sql = "insert into "+ database.getTable() +" values(?, ?, ......)";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定新增的参数（手动编写的部分）*********** 
			preparedStatement.setString();
			
			
			
			//执行更新操作
			preparedStatement.executeUpdate();			
			
			//如果没有发生异常，则操作成功
			successFlag = 1;
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, null);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return successFlag;
	}
	
	@Override
	public int update${module}(String id, ${module} ${param}){
		//修改成功标识
		int successFlag = 0;
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		
		// *********** 修改的 SQL 语句（手动编写的部分）***********
		String sql = "update "+ database.getTable() +" set ...... where ....= ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定修改的参数（手动编写的部分）*********** 
			preparedStatement.setString();
			
			
			
			//执行更新操作
			preparedStatement.executeUpdate();			
			
			//如果没有发生异常，则操作成功
			successFlag = 1;
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, null);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return successFlag;		
	}
	
	@Override
	public int update${module}(${module} ${param}){
		//修改成功标识
		int successFlag = 0;
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		
		// *********** 修改的 SQL 语句（手动编写的部分）***********
		String sql = "update "+ database.getTable() +" set ...... where .... ";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定修改的参数（手动编写的部分）*********** 
			preparedStatement.setString();
			
			
			
			//执行更新操作
			preparedStatement.executeUpdate();			
			
			//如果没有发生异常，则操作成功
			successFlag = 1;
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, null);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return successFlag;				
	}
	
	@Override
	public int delete(String id){
		//删除成功标识
		int successFlag = 0;
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		
		// *********** 新增的 SQL 语句（手动编写的部分）***********
		String sql = "delete "+ database.getTable() +" where id = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定新增的参数（手动编写的部分）*********** 
			preparedStatement.setString(1, id);
			
			
			//执行更新操作
			preparedStatement.executeUpdate();
			
			//如果没有发生异常，则操作成功
			successFlag = 1;
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, null);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return successFlag;		
	}
	
	@Override
	public int delete(${module} ${param}){
		//删除成功标识
		int successFlag = 0;
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		
		// *********** 新增的 SQL 语句（手动编写的部分）***********
		String sql = "delete "+ database.getTable() +" where ......";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定新增的参数（手动编写的部分）*********** 
			preparedStatement.setString();
			
			
			//执行更新操作
			preparedStatement.executeUpdate();
			
			//如果没有发生异常，则操作成功
			successFlag = 1;
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, null);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return successFlag;				
	}
	
	@Override
	public List<${module}> select${module}List(String id){
		//初始化查询的数据集
		List<${module}> ${param}List = new ArrayList<${module}>();
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// *********** 新增的 SQL 语句（手动编写的部分）***********
		String sql = "select * from  "+ database.getTable() +" where id = ?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定新增的参数（手动编写的部分）*********** 
			preparedStatement.setString(1, id);
			
			
			//执行更新操作
			resultSet = preparedStatement.executeQuery();
			
			//将查询的数据绑定到实体类集中
			bind${module}List(resultSet, ${param}List)
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			if (preparedStatement != null) {
			//关闭连接
			close(preparedStatement, resultSet);
				
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return ${module}List;				
	}
	
	@Override
	public List<${module}> select${module}List(${module} ${param}){
		//初始化查询的数据集
		List<${module}> ${param}List = new ArrayList<${module}>();
		
		//得到数据库连接
		ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
		Connection connection = connectionPool.getConnection(database);
		
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		// *********** 新增的 SQL 语句（手动编写的部分）***********
		String sql = "select * from  "+ database.getTable() +" where ..........";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			// *********** 绑定新增的参数（手动编写的部分）*********** 
			preparedStatement.setString();
			
			
			//执行更新操作
			resultSet = preparedStatement.executeQuery();
			
			//将查询的数据绑定到实体类集中
			bind${module}List(resultSet, ${param}List)
		} catch (SQLException e) {
			log.info("发生数据库访问错误，或者在关闭的连接上调用此方法!");
			e.printStackTrace();
		}finally{
			//关闭连接
			close(preparedStatement, resultSet);
			
			//回收数据库连接到连接池中
			connectionPool.recycleConnection(connection);
		}
		return ${module}List;				
	}
	
	/**
	 * 将查询出来的数据集绑定到实体类集中
	 * @author ${author}
	 * ${date}
	 * @param resultSet 查询的结果集对象
	 * @param ${module}List 实体类集
	 * @return 空 仅进行绑定值操作
	 */
	private void bind${module}List(ResultSet resultSet, List<${module}> ${param}List) throws SQLException{
		//判断当前结果集是否存在
		if(resultSet != null){
			while (resultSet.next()) {
				//初始化实体类对象
				${module} ${param} = new ${module}();
				
				
				// *********** 设置实体属性值 （手动编写实现）***********
				
				
				
				//添加到集合中
				${module}List.add(${param});
			}
		}		
	}
	
	/***
	 * 关闭数据库连接的  preparedStatement、resultSet 对象
	 * @author ${author}
	 * ${date}
	 * @param preparedStatement 发送 SQL 语句的对象
	 * @param resultSet 查询的结果集对象
	 * @param controlType 当前操作类型
	 * @return void 空，仅进行关闭部分连接操作
	 */
	private void close(PreparedStatement preparedStatement, ResultSet resultSet, String controlType){
		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			} catch (SQLException pe) {
				log.info("关闭 "+ controlType  +" 方法时， preparedStatement 发生异常！");
				pe.printStackTrace();
			}
		}
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException re) {
				log.info("关闭  "+ controlType  +"方法时，resultSet 发生异常！");
				re.printStackTrace();
			}				
		}		
	}
	

}
