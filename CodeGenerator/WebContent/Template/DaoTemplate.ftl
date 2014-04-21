/**
 * 0.0.0.1
 */
package ${package}.dao;

import java.util.List; 

import com.codegenerator.entity.Database;

/**
 * ${module} 的 Dao 接口
 * @author ${author}
 * ${date}
 */
public interface ${module}Dao {
	
	/**
	 * 新增 ${table} 表中的数据
	 * @author ${author}
	 * ${date}
	 * @param ${param} 实例化对象数据
	 * @param database 数据库对象
	 * @return ${return} 增加成功标识（0：失败；1：成功）
	 */
	public int add${module}(${module} ${param}, Database database);
	
	/**
	 * 根据主键修改 ${table} 表中的数据
	 * @author ${author}
	 * ${date}
	 * @param id 主键 ID 
	 * @param ${param} 实体类对象
	 * @return ${return} 增加成功标识（0：失败；1：成功）
	 */
	public int update${module}(String id, ${module} ${param});
	
	/**
	 * 根据  ${module}实例对象数据， 修改 ${table} 表中的数据
	 * @author ${author}
	 * ${date}
	 * @param ${param} 实例化对象数据
	 * @return ${return} 增加成功标识（0：失败；1：成功）
	 */
	public int update${module}(${module} ${param});
	
	/**
	 * 根据主键 ID 删除  ${table} 表中的 的数据
	 * @author ${author}
	 * ${date}
	 * @param id 主键 ID 
	 * @return ${return} 增加成功标识（0：失败；1：成功）
	 */
	public int delete(String id);
	
	/**
	 * 根据 ${module}实例对象数据， 删除  ${table} 表中的数据
	 * @author ${author}
	 * ${date}
	 * @param ${param} 实例化对象数据
	 * @return ${return} 增加成功标识（0：失败；1：成功）
	 */
	public int delete(${module} ${param});
	
	/**
	 * 根据 ID 查询 ${table} 表中的数据集
	 * @author ${author}
	 * ${date}
	 * @param id 主键 ID 
	 * @return ${module}List ${module}的数据集
	 */
	public List<${module}> select${module}List(String id);
	
	/**
	 * 根据 ${module} 实例对象数据 ，查询 ${table} 表中的数据集
	 * @author ${author}
	 * ${date}
	 * @param ${param} 实例化对象数据
	 * @return ${module}List ${module}的数据集
	 */
	public List<${module}> select${module}List(${module} ${param});

}
