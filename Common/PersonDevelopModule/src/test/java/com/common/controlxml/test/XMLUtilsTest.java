/**
 * 0.0.0.1
 */
package com.common.controlxml.test;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.junit.Assert;
import org.junit.Test;

import com.common.controlxml.XMLUtils;

/**
 * 操作 xml 文件的类的测试类
 * @author gaoqing
 * 2014-7-1
 */
public class XMLUtilsTest {
	

	/**
	 * Test method for {@link com.common.controlxml.XMLUtils#XMLUtils()}.
	 */
	@Test
	public void testXMLUtils() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.common.controlxml.XMLUtils#getElementList(java.lang.String, java.util.List, java.util.List)}.
	 */
	@Test
	public void testGetElementList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.common.controlxml.XMLUtils#generateXML(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testGenerateXML() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link com.common.controlxml.XMLUtils#generateXML2File(java.lang.String, java.util.List, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testGenerateXML2File() {
		
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
		
		boolean isGenerateXML2File = XMLUtils.generateXML2File("test", elementList, "D:"+ File.separator +"code", "testXML");
		
		Assert.assertFalse(!isGenerateXML2File);
	}

}
