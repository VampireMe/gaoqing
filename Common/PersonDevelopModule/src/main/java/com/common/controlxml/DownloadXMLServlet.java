package com.common.controlxml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;

/**
 * Servlet implementation class DownloadXMLServlet
 */
public class DownloadXMLServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public DownloadXMLServlet() {
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//得到 List<Element> 对象
		 List<Element> elementList = new ArrayList<Element>();
		
		 List<String> elementAttrList = new ArrayList<String>();
		 elementAttrList.add("name");
		 elementAttrList.add("age");
		 elementAttrList.add("address");
		 
		 List<List<?>> elementValueList = new ArrayList<List<?>>();
		 
		 for (int i = 0; i < 3; i++) {
			 List innerList = new ArrayList();
			
			 innerList.add("gaoqing" + i);
			 innerList.add(20 + i);
			 innerList.add("");
			 elementValueList.add(innerList);
		}
		 
		 elementList = XMLUtils.getElementList("children", elementAttrList, elementValueList);
		 
		 Document document = XMLUtils.generateXML("test", elementList);
		 
		 //设置文件下载的名称
		 String fileName = "xml 文件";
		 
		 //定义导出文件的格式
		 String contentType = "application/vnd.xml";
		 
		 String exportFileNameEncoding = new String(fileName.getBytes(), "iso_8859_1");
		 
		 response.setContentType(contentType);
		 response.setHeader("Content-Disposition", "attachment; filename=" + exportFileNameEncoding + "\"");
		 
		 response.resetBuffer();
		
		ServletOutputStream sos = response.getOutputStream();
		sos.write(document.toString().getBytes());
		sos.flush();
		sos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
