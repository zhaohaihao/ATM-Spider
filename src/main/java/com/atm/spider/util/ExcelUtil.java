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
 * ����Excel ������
 */
public class ExcelUtil {
	
	public static List<ATMExcelData> list = new ArrayList<ATMExcelData>();
	
	/**
	 * ��ȡexcel�������
	 * @param filePath	excel���·��
	 * @return
	 */
	@Test
	public static List<ATMExcelData> getData(String filePath) {
		
		if(list != null && list.size() > 0) {
			return list;
		}
		
		// ������Excel�������ļ�������
		try {
			HSSFWorkbook workBook = new HSSFWorkbook(new FileInputStream(filePath));
			// ��Excel�ĵ���, ��һ�Ź������ȱʡ������0
			HSSFSheet sheet = workBook.getSheetAt(0);
			// ��ȡ��Excel�ļ��е���������
			int rows = sheet.getPhysicalNumberOfRows();
			// ������, ������0��ʼ, ��0�п�������ͷ, ����ȡ
			for (int i = 1; i < rows; i++) {
				// ��ȡ���϶˵�Ԫ��
				HSSFRow row = sheet.getRow(i);
				// �в�Ϊ��
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
