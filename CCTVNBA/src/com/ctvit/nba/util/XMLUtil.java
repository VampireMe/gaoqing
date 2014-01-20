/**
 * 0.0.0.1
 */
package com.ctvit.nba.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * XML操作常用类
 * @author 高青
 * 2013-12-3
 */
public class XMLUtil {
	
	/**日志对象*/
	private static Logger logger = Logger.getLogger(XMLUtil.class);
	
	/**
	 * 生成  XML 文件
	 * @author 高青
	 * 2013-12-3
	 * @param document Document对象
	 * @param filePath 当前  xml 存放的路径
	 * @return flag 成功标识（0：失败；1：成功） 
	 */
	public static int generateXMLFile(Document document, String fileFolder, String fileName){
		int flag = 0;
		//输出流
		FileOutputStream fileOutputStream = null;
		
		//设置 xml 输出格式
		Format format = Format.getPrettyFormat();
		//设置缩进的空格数
		format.setIndent("   ");
		
		XMLOutputter xmlOutputter = new XMLOutputter(format);
		
		try {
			//生成相应的文件输出流
			fileOutputStream = new FileOutputStream(getFile(fileFolder, fileName));
			
			//将数据写到 xml 文件中
			xmlOutputter.output(document, fileOutputStream);
			
			logger.info("将数据写到 xml 文件操作成功！");
			
			//写入数据成功
			flag = 1;
		} catch (FileNotFoundException e) {
			logger.info("将数据写到 xml 文件操作失败！");
			e.printStackTrace();
		} catch (IOException e) {
			logger.info("将数据写到 xml 文件操作失败！");
			e.printStackTrace();
		}finally{
			//关闭文件输出流
			if (fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}
	
	/**
	 * 得到指定路径的文件
	 * @author 高青
	 * 2013-12-3
	 * @param fileFolder 文件夹
	 * @param fileName xml文件名称
	 * @return file 实例后的 xml 文件
	 */
	public static File getFile(String fileFolder, String fileName){
		
		//生成当前文件夹
		File folder = new File(fileFolder);
		
		//首先判断文件夹是否存在
		if (!folder.exists() || !folder.isDirectory()) {
			folder.mkdirs();
		}
		//生成 文件
		File file = new File(fileFolder + fileName);
		
		return file;
	}
	
	/**
	 * 得到指定的文件夹
	 * @author 高青
	 * 2013-12-4
	 * @param key 得到文件夹的 key 值 
	 * @return folder 文件夹路径
	 */
	public static String getFileFolder(String key){
		//文件夹
		String folder = "";
		
		//得到 指定的文件夹路径
		folder = CommonUtil.getPath(key);
		
		return folder;
	}
	
	/**
	 * 得到 xml 文件名
	 * @author 高青
	 * 2013-12-4
	 * @param season 赛季
	 * @param moduleName 模块名
	 * @param innerUpdateModule 更新方式
	 * @return fileName xml文件名 
	 */
	public static String getFileName(String season, String moduleName, String innerUpdateModule){
		String fileName = "";
		
		//得到  xml 的文件名
		fileName = "NBA_" + season + "_" + moduleName + "_" + innerUpdateModule  + ".xml";
		
		return fileName;
	}
	
	/**
	 * 更新数据到 xml 文件中
	 * @author 高青
	 * 2013-11-29
	 * @param moduleName 模块名称
	 * @param xmlFileNameRemarker 更新方式
	 * @param childrenElementList 子元素节点集合
	 * @return int 更新成功标识（0：失败；1：成功）
	 */
	public static int updateData2XML(String moduleName, String xmlFileNameRemarker, List<Element> childrenElementList){
		int xmlFlag = 0;
		
		//根元素
		Element root = new Element("Msg");
		root.setAttribute(moduleName + "Description", "更新数据");
		root.setAttribute("module", moduleName);
		root.setAttribute("innerUpdateModule", xmlFileNameRemarker);
		
		
		//生成  document 对象，并设置好  根元素
		Document document = new Document(root);
		
		root.addContent(childrenElementList);
		
		/*
		 * 得到 按天更新赛程的文件夹路径及文件名
		 */
		//得到其中的链接标识，作为组织文件存储的文件夹路径
		Pattern pattern = Pattern.compile("\\b\\w+?(?=_)"); //匹配第一个"_"前的字符
		Matcher matcher = pattern.matcher(xmlFileNameRemarker);
		String partURLRemarker = "";
		while (matcher.find()) {
			partURLRemarker = matcher.group();
		}
		matcher.reset();
		
		String dayFolder = XMLUtil.getFileFolder(moduleName + "_" +  partURLRemarker.toLowerCase() + "_folder");
		String fileName = XMLUtil.getFileName("2013-2014", moduleName, xmlFileNameRemarker);
		
		//生成 XML 文件
		xmlFlag = XMLUtil.generateXMLFile(document, dayFolder, fileName);
		
		logger.info("生成XML文件成功！");
		
		return xmlFlag;
	}
	
}
