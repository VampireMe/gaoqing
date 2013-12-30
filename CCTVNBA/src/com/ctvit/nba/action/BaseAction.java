/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 高青
 * 2013-12-25
 */
public class BaseAction extends ActionSupport{

	/**可序列化编号*/
	private static final long serialVersionUID = 1L;
	
	/**日志对象*/
	private Logger logger = Logger.getLogger(BaseAction.class);
	
	/**
	 * 将Json格式的 数据写到前台
	 * @author 高青
	 * 2013-12-26
	 * @param <T> 定义泛型类型
	 * @param t 泛型参数
	 * @return void null
	 */
	public <T> void writeJson2Web(T t){
		
		//初始化响应对象
		HttpServletResponse response = ServletActionContext.getResponse();
		
		//初始化 输出对象
		PrintWriter writer =  null;
		
		//将信息写到前端
		try {
			//设置输出格式
			response.setContentType("text/html;charset=utf-8");
			//初始化该对象
			writer = response.getWriter();
			response.getWriter().print(t);
			writer.flush();
			
			logger.info("写出数据成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				response.getWriter().close();
			} catch (IOException e) {
				logger.info("json 数据流开始关闭出现异常");
				e.printStackTrace();
			}
		}
	}

}
