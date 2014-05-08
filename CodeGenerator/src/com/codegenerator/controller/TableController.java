/**
 * 0.0.0.1
 */
package com.codegenerator.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;

import com.codegenerator.util.CommonUtil;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 数据表的控制器
 * @author 高青
 * 2014-5-7
 */
@Controller
@RequestMapping("/table")
public class TableController {
	
	private static Logger log = Logger.getLogger(TableController.class);	

	/**
	 * 构造方法
	 * 2014-5-7
	 */
	public TableController() {
		
	}
	
	/**
	 * 通过数据库表，生成制定的代码
	 * @author 高青
	 * 2014-5-7
	 * @param tableName 对应的数据库表的名称
	 * @param packageName 生成代码文件的包名
	 * @param className 生成代码文件的类名
	 * @param authorName 生成代码文件的作者
	 * @return 
	 */
	@RequestMapping("/generateCode")
	@ResponseBody
	public String generateCode(String tableName, String packageName, String className, String authorName, HttpServletRequest request){
		//生成代码成功后反馈的信息
		String rebackInfo = "failed";
		
		String realPath = request.getServletContext().getRealPath("/");
		
		if (tableName != null || tableName != "") {
			
			//初始化 Freemarker 的配置对象
			Configuration configuration = new Configuration();
			//设置模板存放的路径
			try {
				configuration.setDirectoryForTemplateLoading(new File(realPath + "Template\\"));
				
				//设置模板如何查看数据类型
				configuration.setObjectWrapper(new DefaultObjectWrapper());
				configuration.setDefaultEncoding("UTF-8");
			} catch (IOException e1) {
				log.info("设置模板存放位置发生异常！");
				e1.printStackTrace();
			}
			
			try {
				
				//得到制定的生成模板
				Template daoTemplate = configuration.getTemplate("DaoTemplate.ftl");
				daoTemplate.setEncoding("UTF-8");
				Template daoImplTemplate = configuration.getTemplate("DaoTemplateImpl.ftl");
				daoImplTemplate.setEncoding("UTF-8");
				Template serviceTemplate = configuration.getTemplate("ServiceTemplate.ftl");
				serviceTemplate.setEncoding("UTF-8");
				Template serviceImplTemplate = configuration.getTemplate("ServiceImplTemplate.ftl");
				serviceImplTemplate.setEncoding("UTF-8");
				
				//组织数据
				Map<String, Object> root = new HashMap<String, Object>();
				root.put("tableName", "table");
				
				root.put("author", authorName);
				root.put("packageName", packageName);
				root.put("date", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
				root.put("module", className);
				root.put("param", className.substring(0, 1).toLowerCase() + 
						className.substring(1, className.length()));
				root.put("table", tableName);
				root.put("return", "flag");
				
				Map<String, String> innerDataMap = new HashMap<String, String>();
				root.put("innerData", innerDataMap);
				
				//设置输出的路径
				Writer daoWriter = new OutputStreamWriter(new FileOutputStream(new File("d:\\code\\generated\\" + className + "Dao.java")), "UTF-8") ;
				Writer daoImplWriter = new OutputStreamWriter(new FileOutputStream(new File("d:\\code\\generated\\" + className + "DaoImpl.java")), "UTF-8") ;
				Writer serviceWriter = new OutputStreamWriter(new FileOutputStream(new File("d:\\code\\generated\\" + className + "Service.java")), "UTF-8") ;
				Writer serviceImplWriter = new OutputStreamWriter(new FileOutputStream(new File("d:\\code\\generated\\" + className + "ServiceImpl.java")), "UTF-8") ;
				
				try {
					//将模板和数据结合，生成文件
					daoTemplate.process(root, daoWriter);
					daoImplTemplate.process(root, daoImplWriter);
					serviceTemplate.process(root, serviceWriter);
					serviceImplTemplate.process(root, serviceImplWriter);
				} catch (TemplateException e) {
					log.info("生成代码文件发生异常！");
					e.printStackTrace();
				}
				rebackInfo = "success";
			} catch (IOException e) {
				log.info("得到 Freemarker 模板发生异常！");
				e.printStackTrace();
			}
		}
		return rebackInfo;
	}

}
