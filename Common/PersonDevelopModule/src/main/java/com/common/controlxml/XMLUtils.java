/**
 * 0.0.0.1
 */
package com.common.controlxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * XML 工具类
 * @author gaoqing
 * 2014-6-26
 */
public class XMLUtils {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(XMLUtils.class);

	/**
	 * 构造方法
	 */
	public XMLUtils() {
		
	}
	
	/**
	 * 生成 XML Document 对象
	 * @author gaoqing
	 * 2014-6-26
	 * @param rootName 根节点的名称
	 * @param childList 子元素集
	 * @return document xml Document 对象
	 */
	public static Document generateXML(String rootName, List<Element> childList){
		/*
		 * 将传递过来的数据，封装到 XML 文档中
		 */
		
		//生成 XML 的根元素
		Element rootElement = new Element(rootName);
		Document document = new Document(rootElement);
		
		//向根元素中，添加具体的子元素对象
		rootElement.addContent(childList);
		
		return document;
	}
	
	
	/**
	 * 将数据生成到 xml 文件中
	 * @author gaoqing
	 * 2014-6-26
	 * @param rootName 根节点的名称
	 * @param childList 子元素集
	 * @return isSuccess 成功标识（true: 成功；false: 失败）
	 */
	public static boolean generateXML2File(
			String rootName, 
			List<Element> childList,
			String basePath,
			String fileName)
	{
		//成功标识
		boolean isSuccess = false;
		
		/*
		 * 1、得到 xml 的 Document 对象数据
		 * 2、将 xml 存在内存中的 Document 对象数据，持久到具体的文件中
		 */
		
		//1、得到 xml 的 Document 对象数据
		Document document = generateXML(rootName, childList);
		
		//2、将 xml 存在内存中的 Document 对象数据，持久到具体的文件中
		XMLOutputter xmlOutputter = new XMLOutputter();
		xmlOutputter.setFormat(Format.getPrettyFormat());
		
		//设置输出文件
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(new File(basePath + fileName));
			xmlOutputter.output(document, fos);
			
			//如果执行到此处，为发生异常，则表示执行成功
			isSuccess = true;
		} catch (FileNotFoundException e) {
			log.info("文件输出流输出到执行的文件，发生异常！");
			e.printStackTrace();
		}catch (IOException e) {
			log.info("将 XML 文件写出到制定的文件中，发生异常！");
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				log.info("关闭文件输出流，发生异常！");
				e.printStackTrace();
			}
		}
		return isSuccess;
	}
}
