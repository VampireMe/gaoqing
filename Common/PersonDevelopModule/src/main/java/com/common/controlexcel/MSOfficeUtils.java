/**
 * 0.0.0.1
 */
package com.common.controlexcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.SheetBuilder;

/**
 * 微软 office 工具类
 * @author gaoqing
 * 2014-7-2
 */
public class MSOfficeUtils {
	
	/** 日志对象 */
	private static Logger log = Logger.getLogger(MSOfficeUtils.class);

	/**
	 * 构造方法
	 */
	public MSOfficeUtils() {
		
	}
	
	/**
	 * 读取办公套件下的 Excel 文件
	 * @author gaoqing
	 * 2014-7-3
	 * @param 
	 * @return valueList 解析后的结果集（其中内部的 List 对象，存放的是每一行的数据）
	 */
	public static List<List<Object>> readOffice4Excel(String sheetName, String baseFolder, String fileName){
		//初始化结果集
		List<List<Object>> valueList = new ArrayList<List<Object>>();
		
		/*
		 * 1、得到 excel 文件对象的 Workbook 
		 * 2、根据 workbook ，得到其中，相应的值
		 */
		
		//1、得到 excel 文件对象的 Workbook 
		FileInputStream is;
		try {
			is = new FileInputStream(new File(baseFolder + fileName));
			POIFSFileSystem fs = new POIFSFileSystem(is);
			Workbook workbook = new HSSFWorkbook(fs);
			
			//2、根据 workbook ，得到其中，相应的值
			Sheet sheet = null;
			
			//如果传递的页签名称为空，则默认取第一个页签
			if (StringUtils.isBlank(sheetName)) {
				sheet = workbook.getSheetAt(0);
			}else {
				sheet = workbook.getSheet(sheetName);
			}
			
			//得到行数据
			int lastRowNum = sheet.getLastRowNum();
			
			for (int i = 0; i < lastRowNum; i++) {
				Row row = sheet.getRow(i + 1);
				
				//初始化单元格数据集
				List<Object> cellList = new ArrayList<Object>();
				
				//得到当前行的列数
				short lastCellNum = row.getLastCellNum();
				for (int j = 0; j < lastCellNum; j++) {
					Cell cell = row.getCell(j);
					
					Object cellValue = getCellValue(cell);
					
					//添加到单元格集中
					cellList.add(cellValue);
				}
				//将当前行的数据，添加到所有行的集合中
				valueList.add(cellList);
			}
		} catch (FileNotFoundException e) {
			log.info("要解析的文件不存在！");
			e.printStackTrace();
		}catch (IOException e) {
			log.info("得到制定的 io 流时，发生异常！");
			e.printStackTrace();
		}
		return valueList;
	}

	/**
	 * 得到当前列的值
	 * @author gaoqing
	 * 2014-7-7
	 * @param cell 单元格对象
	 * @return cellValue 当前单元格中的值
	 */
	private static Object getCellValue(Cell cell) {
		//初始化单元格的值
		Object cellValue = null;
		
		/*
		 * 1、得到当前单元格的数据类型
		 * 2、使用 switch 动态判断数据类型，并返回相应的值
		 */
		
		//1、得到当前单元格的数据类型
		int cellType = cell.getCellType();
		
		switch (cellType) {
		case Cell.CELL_TYPE_NUMERIC:
			cellValue = cell.getNumericCellValue();
			break;
		case Cell.CELL_TYPE_STRING:
			cellValue = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			cellValue = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			cellValue = cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_ERROR:
			cellValue = cell.getErrorCellValue();
			break;
		case Cell.CELL_TYPE_BLANK:
			cellValue = "";
			break;
		default:
			break;
		}
		return cellValue;
	}

	/**
	 * 生成 Office 办公套件下的 Excel 文件<br/>
	 * @author gaoqing
	 * 2014-7-3
	 * @param sheetNameADataArrMap sheet 名称及当前页签下的数据 Map 对象
	 * @param shouldCreateEmptyCells 是否创建空的单元格
	 * @param baseFolder 文件路径（需要添加完整的路径，如：d:\\test\\）
	 * @param fileName 文件名称（文件名，不需要包含文件的后缀）
	 * @return isCreateSuccess 成功标识
	 */
	public static boolean createOffice4Excel(
			Map<String, Object[][]> sheetNameADataArrMap,
			boolean shouldCreateEmptyCells,
			String baseFolder,
			String fileName){
		//成功标识
		boolean isCreateSuccess = false;
		
		Set<String> sheetNameSet = sheetNameADataArrMap.keySet();
		
		for (String sheetName : sheetNameSet) {
			//得到 Workbook 对象
		   Workbook	workbook = new HSSFWorkbook();
			
			//得到当前页签名称下的数据值
			Object[][] dataCells = sheetNameADataArrMap.get(sheetName);
			
			//初始化 SheetBuilder 对象
			SheetBuilder sheetBuilder = new SheetBuilder(workbook, dataCells);
			sheetBuilder.build();

			//将文件生成到制定的路径下
			try {
				workbook.write(new FileOutputStream(new File(baseFolder + fileName + ".xls")));
				
				isCreateSuccess = true;
			} catch (FileNotFoundException e) {
				log.info("要写入的文件不存在！");
				e.printStackTrace();
			} catch (IOException e) {
				log.info("文件输出流，发生异常！");
				e.printStackTrace();
			}			
		}
		return isCreateSuccess;
	}

	/**
	 * 生成 Office 办公套件下的 Excel 文件<br/>
	 * <strong>此方法，适合将 excel 后台保存到制定的路径下</strong>
	 * @author gaoqing
	 * 2014-7-2
	 * @param sheetNameAInfoMap 页签名称和当前页签下的数据（表头和对应的数据）
	 * <p>其中：<br />
	 * 	  key: 页签名称；<br/>
	 * 	  value: 是一个 List 数组，第一个数据是表头 List 集，<br/>
	 * 	     第二个是对应的数据 List 集, 其中，List 内部，一个个的 List 数据集，此 List 数据集存储的是一行的数据</p>
	 * @param baseFolder 文件路径（需要添加完整的路径，如：d:\\test\\）
	 * @param fileName 文件名称（文件名，不需要包含文件的后缀）
	 * @return isCreateSuccess 成功标识
	 */
	public static  boolean createOffice4Excel(
			Map<String, List<?>[]> sheetNameAInfoMap,
			String baseFolder,
			String fileName) {
		//初始化成功标识
		boolean isCreateSuccess = false;
		
		/*
		 * 1、首先得到初始状态的 wookbook 对象及相关的组件
		 * 2、首先，得到所有的页签，并得到当前页签对应的数据集
		 * 3、在单个页签中，得到 titleList 和 dataList
		 * 4、在 dataList 循环下，循环  titleList 中的值及 dataList 中的每行数据集
		 * （rowDataList，原则上，titleList 和 rowDataList 的数据数是相同的，而且数据是一一对应的）
		 */
		
		//1、首先得到初始状态的 wookbook 对象及相关的组件
		Workbook workbook = new HSSFWorkbook();
		
		if (sheetNameAInfoMap != null && sheetNameAInfoMap.size() != 0) {
			
			//2、首先，得到所有的页签，并得到当前页签对应的数据集
			Set<String> sheetSet = sheetNameAInfoMap.keySet();
			
			for (String sheetName : sheetSet) {
				
				Sheet sheet = workbook.createSheet(sheetName);
				Row titleRow = sheet.createRow(0);
				
				//得到 titleList, 创建 title 
				List<Object> titleList = (List<Object>) sheetNameAInfoMap.get(sheetName)[0];
				for (int i = 0; i < titleList.size(); i++) {
					//创建单元格
					Cell titleCell = titleRow.createCell(i);
					
					//判断，如果值为空，将值替换为后面的值
					setCellValue(titleCell, titleList.get(i));
				}
				
				//得到 dataList, 创建值
				List<Object> dataList = (List<Object>) sheetNameAInfoMap.get(sheetName)[1];
				for (int i = 0; i < dataList.size(); i++) {
					//创建行
					Row valueRow = sheet.createRow(i + 1);
					
					Object value = dataList.get(i);
					
					//设置该行的单元格
					for (int j = 0; j < titleList.size(); j++) {
						Cell valueCell = valueRow.createCell(j);
						
						//判断 dataList 中，值的类型（List<Object> 或者 T 实体类对象）
						if (value instanceof List) {
							setCellValue(valueCell, ((List<Object>)dataList.get(i)).get(j));
						}else {
							log.info("当前传入的值的类型不匹配，请重试！");
						}
					}
				}
				
				//将文件生成到制定的路径下
				try {
					workbook.write(new FileOutputStream(new File(baseFolder + fileName + ".xls")));
					
					isCreateSuccess = true;
				} catch (FileNotFoundException e) {
					log.info("要写入的文件不存在！");
					e.printStackTrace();
				} catch (IOException e) {
					log.info("文件输出流，发生异常！");
					e.printStackTrace();
				}
			}
		}else {
			log.info("传递过来的值为空，不能进行生成 Excel 操作！");
		}
		return isCreateSuccess;
	}
	
    /**
     * 设置单元格的值
     * @author gaoqing
     * 2014-07-03
     * @param cell  当前单元格对象
     * @param value 要设置的值
     * @return void 空
     */
    private static void setCellValue(Cell cell, Object value) {
        if (value == null || cell == null) {
            return;
        } else if (value instanceof Number) {
            double doubleValue = ((Number) value).doubleValue();
            cell.setCellValue(doubleValue);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
        } else if (value instanceof Calendar) {
            cell.setCellValue((Calendar) value);
        } else if (isFormulaDefinition(value)) {
            cell.setCellFormula(getFormula(value));
        } else {
            cell.setCellValue(value.toString());
        }
    }

    /**
     * 判断当前值是否是公式
     * @author gaoqing
     * 2014-7-3
     * @param obj 单元格的值
     * @return true/false 是否是定义格式的标识
     */
    private static boolean isFormulaDefinition(Object obj) {
    	
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() < 2) {
                return false;
            } else {
                return ((String) obj).charAt(0) == '=';
            }
        } else {
            return false;
        }
    }
    
    /**
     * 得到公式的值
     * @author gaoqing
     * 2014-7-3
     * @param obj 单元格的值
     * @return str 公式的值
     */
    private static String getFormula(Object obj) {
        return ((String) obj).substring(1);
    }

}
