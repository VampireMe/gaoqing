/**
 * 0.0.0.1
 */
package com.ctvit.nba.action;

import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 
 * @author 高青
 * 2013-12-25
 */
public class BaseAction extends ActionSupport{

	/**可序列化编号*/
	private static final long serialVersionUID = 1L;
	
	/**HTTPServletResponse 对象*/
	private HttpServletResponse response;

}
