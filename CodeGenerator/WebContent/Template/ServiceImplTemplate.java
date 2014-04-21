/**
 * 0.0.0.1
 */
package ${package}.service.impl;

import java.util.List; 

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.codegenerator.entity.Database;

/**
 * ${module} 的 Dao 接口
 * @author ${author}
 * ${date}
 */
@Service
public class ${module}ServiceImpl implements ${module}Service{
	
	@Resource
	private ${module}Dao ${moduleDao};
	
	@Override
	public int add${module}(${module} ${param}, Database database){
		return this.${moduleDao}.add${module}(${param});
	}
	
	@Override
	public int update${module}(String id, ${module} ${param}){
		return this.${moduleDao}.update${module}(id);
	}
	
	@Override
	public int update${module}(${module} ${param}){
		return this.${moduleDao}.update${module}(${param});
	}
	
	
	@Override
	public int delete(String id){
		return this.${moduleDao}.delete(id);
	}
	
	@Override
	public int delete(${module} ${param}){
		return this.${moduleDao}.delete(${param});
	}
	
	@Override
	public List<${module}> select${module}List(String id){
		return this.${moduleDao}.select${module}List(id);
	}
	
	@Override
	public List<${module}> select${module}List(${module} ${param}){
		return this.${moduleDao}.select${module}List(${param});
	}

}
