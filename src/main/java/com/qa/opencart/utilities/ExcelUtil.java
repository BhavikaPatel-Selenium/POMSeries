package com.qa.opencart.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	public static String TEST_EXCEL_DATA = ".\\src\\test\\resources\\testData\\testData.xls";
	private static Workbook book;
	private static Sheet sheet;
	
	
	public static Object[][] getExcelData(String sheetName) {
		Object data[][]= null;
		try {
			
			FileInputStream ip = new FileInputStream(TEST_EXCEL_DATA);
			book = WorkbookFactory.create(ip);
			sheet = book.getSheet(sheetName);
			
			int rowCount = sheet.getLastRowNum();
			int colCount = sheet.getRow(0).getLastCellNum();
			
			data = new Object[rowCount][colCount];
			
				for(int i = 0; i<rowCount; i++) {			
					for(int j = 0; j<colCount; j++) {
						data[i][j] = sheet.getRow(i+1).getCell(j).toString();
					}
				}	
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (InvalidFormatException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		return data;
	} 
}
	
