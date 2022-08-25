package com.bidvest.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * 
 * @author Manu
 *
 */

public class ExcelUtility {
	
	/**
	    *   its used to read the data from Excel-Workbook based on below 
	    * @param sheetName
	    * @param rowNum
	    * @param celNum
	    * @return String data

	 * @throws Throwable
	    */
		

		public String getExcelData(String sheetName , int rowNum , int celNum) throws Throwable  {
			FileInputStream fis = new FileInputStream(IConstatnts.EXCELPATH);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			String data = row.getCell(celNum).getStringCellValue();
			return data;	
		}
		/**
		 * used to get the last used row number on specified Sheet
		 * @param sheetName
		 * @return
		 * @throws Throwable
		 */
		public int getRowCount(String sheetName) throws Throwable {
			FileInputStream fis  = new FileInputStream(IConstatnts.EXCELPATH);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			wb.close();
			return sh.getLastRowNum();
		}
		/**
		 * used to write data back to Excel based on below parameter
		 * @param sheetName
		 * @param rowNum
		 * @param celNum
		 * @param data
		 * @throws Throwable
		 */
		public void setDataExcel(String sheetName , int rowNum, int celNum ,String data) throws Throwable {
			FileInputStream fis  = new FileInputStream(IConstatnts.EXCELPATH);
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet(sheetName);
			Row row = sh.getRow(rowNum);
			Cell cel = row.createCell(celNum);
			cel.setCellValue(data);
			FileOutputStream fos = new FileOutputStream(IConstatnts.EXCELPATH);
			wb.write(fos);
			wb.close();
			
		}


}
