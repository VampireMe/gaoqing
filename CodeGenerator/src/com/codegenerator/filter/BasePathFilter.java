package com.codegenerator.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

/**
 * 项目路径过滤器
 * @author 高青
 * 2014-04-16
 */
@WebFilter(description = "项目路径的过滤器", urlPatterns = { "/*" })
public class BasePathFilter implements Filter {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(BasePathFilter.class);

    /**
     * 默认的构造方法
     */
    public BasePathFilter() {
       
    }

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		//得到请求的路径
		StringBuffer requestURL = httpServletRequest.getRequestURL();
		//得到 servlet 的路径
		String servletPath = httpServletRequest.getServletPath();
		
		//得到项目的根路径
		String basePath = (String) requestURL.subSequence(0, requestURL.indexOf(servletPath)) + "/";
		
		request.setAttribute("basePath", basePath);
		
		//设置字符串返回的数据类型
		response.setCharacterEncoding("UTF-8");
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
