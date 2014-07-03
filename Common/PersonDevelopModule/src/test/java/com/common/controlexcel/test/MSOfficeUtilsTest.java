/**
 * 0.0.0.1
 */
package com.common.controlexcel.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.common.controlexcel.MSOfficeUtils;

/**
 * 测试 office 的工具类
 * @author gaoqing
 * 2014-7-2
 */
public class MSOfficeUtilsTest {
	
	@Test
	public void createOffice4ExcelTest(){
		
		//如果是 excel 的话，页签的名称集
		String titleName = "sheet01";
		
		//初始化表头集
		List<String> titleList = new ArrayList<String>();
		titleList.add("姓名");
		titleList.add("年龄");
		titleList.add("地址");
		
		
		//初始化数据集
		List<Object> dataList = new ArrayList<Object>(); 
		for (int i = 0; i < 3; i++) {
			List<Object> innerList = new ArrayList<Object>();
			innerList.add("gaoqing" + i);
			innerList.add(20 + i);
			innerList.add("beijing" + i);
			
			dataList.add(innerList);
		}
		
		List<?>[] infoListArr = {titleList, dataList};
		
		Map<String, List<?>[]> sheetNameAInfoMap = new HashMap<String, List<?>[]>();
		
		//将值添加到当前的 map 对象中
		sheetNameAInfoMap.put(titleName, infoListArr);
		
		boolean isSuccess = MSOfficeUtils.createOffice4Excel(sheetNameAInfoMap, "D:\\code\\", "gaoqing_test");
	}
	
	@Test
	public void createOffice4ExcelTest2(){
		
		//如果是 excel 的话，页签的名称集
		String titleName = "sheet01";
		
		Object[][] dataObjects = new Object[3][4];
		
		String[] titleArr = {"姓名", "年龄", "地址", "身高"};
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				
				if (i == 0) {
					dataObjects[i][j] = titleArr[j];
				}else {
					dataObjects[i][j] = i + " value " + j;
				}
			}
		}
		
		Map<String, Object[][]> sheetNameAInfoMap = new HashMap<String, Object[][]>();
		
		//将值添加到当前的 map 对象中
		sheetNameAInfoMap.put(titleName, dataObjects);
		
		boolean isSuccess = MSOfficeUtils.createOffice4Excel(sheetNameAInfoMap, false, "D:\\code\\", "gaoqing_test_arr");
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
