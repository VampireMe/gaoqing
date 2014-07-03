/**
 * 0.0.0.1
 */
package com.common.controlxml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
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
	 * 解析 XML 文件
	 * @author gaoqing
	 * 2014-7-2
	 * @param 
	 * @return root 根元素对象
	 */
	public static Element analysisXML(InputStream in){
		
		//解析后的值
		Element root = null;
		
		//得到 SAX驱动
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
			Document document = saxBuilder.build(in);
			
			//得到 root 元素
			root = document.getRootElement();
		} catch (JDOMException e) {
			log.info("在得到 Document 对象时，发生异常！");
			e.printStackTrace();
		} catch (IOException e) {
			log.info("文件输入流，输入时发生异常！");
			e.printStackTrace();
		}
		return root;
	}
	
	/**
	 * 得到元素集
	 * @author gaoqing
	 * 2014-7-1
	 * @param elementName 元素名称
	 * @param elementAttrList 元素属性数组
	 * @param elementValueList 元素值的集合
	 * @return elementList 元素集
	 */
	public static List<Element> getElementList(
			String elementName,
			List<String> elementAttrList, 
			List<List<?>> elementValueList
			)
		{
		List<Element> elementList = new ArrayList<Element>();
		
		/*
		 * 1、循环元素值集和，得到其中一个元素的值集
		 * 2、循环元素属性的数组，当前数组的个数必然会和一个元素的值集是一一对应的
		 * 3、将元素添加到元素集中
		 */
		
		//1、循环元素值集和，得到其中一个元素的值集
		for (List<?> valueList : elementValueList) {
			Element element = new Element(elementName);
			
			//2、循环元素属性的数组，当前数组的个数必然会和一个元素的值集是一一对应的
			for (int i = 0; i < elementAttrList.size(); i++) {
				
				element.setAttribute(
						CommonUtils.switch2String(elementAttrList.get(i), "-"), 
						CommonUtils.switch2String(valueList.get(i), "-"));
			}
			//3、将元素添加到元素集中
			elementList.add(element);
		}
		return elementList;
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
			
			//判断 basePath 是否存在文件夹路径标识
			if (!(Character.toString(basePath.charAt(basePath.length() - 1)).equals(File.separator))) {
				basePath = basePath + File.separator;
			}
			
			fos = new FileOutputStream(new File(basePath + fileName + ".xml"));
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
