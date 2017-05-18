package com.atm.spider.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;

import com.atm.spider.bean.excel.ATMExcelData;

/**
 * 操作Excel 工具类
 */
public class ExcelUtil {
	
	public static List<ATMExcelData> list = new ArrayList<ATMExcelData>();
	
	/**
	 * 读取excel表格内容
	 * @param filePath	excel表格路径
	 * @return
	 */
	@Test
	public static List<ATMExcelData> getData(String filePath) {
		
		if(list != null && list.size() > 0) {
			return list;
		}
		
		// 创建对Excel工作簿文件的引用
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(filePath));
			// 在Excel文档中, 第一张工作表的缺省索引是0
			HSSFSheet sheet = workBook.getSheetAt(0);
			// 获取到Excel文件中的所有行数
			int rows = sheet.getPhysicalNumberOfRows();
			// 遍历行, 索引从0开始, 第0行可用作表头, 不获取
			for (int i = 1; i < rows; i++) {
				// 读取左上端单元格
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if(row != null) {
					ATMExcelData data = new ATMExcelData();
					
					HSSFCell cell0 = row.getCell(0);
					HSSFCell cell1 = row.getCell(1);
					HSSFCell cell2 = row.getCell(2);
					HSSFCell cell3 = row.getCell(3);
					
					if(cell0 == null) {
						data.setBankType("");
					} else {
						data.setBankType(row.getCell(0).toString());
					}
					if(cell1 == null) {
						data.setBranchCode("");
					} else {
						data.setBranchCode(row.getCell(1).toString());
					}
					if(cell2 == null) {
						data.setSsjg("");
					} else {
						data.setSsjg(row.getCell(2).toString());
					}
					if(cell3 == null) {
						data.setSsjg("");
					} else {
						data.setAddress(row.getCell(3).toString());
					}
					list.add(data);
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	

}
