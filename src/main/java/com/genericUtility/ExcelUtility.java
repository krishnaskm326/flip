package com.genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ExcelUtility {
/**
 * this method is used to read the data from excel file
 * @param sheetName
 * @param rowNum
 * @param columnNum
 * @return
 * @throws Throwable
 */
	public String readDataFromExcel(String sheetName,int rowNum,int columnNum) throws Throwable {
	FileInputStream	fis=new FileInputStream(IpathConstants.EXCELPATH);
	Workbook wb = WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet(sheetName);
	String value = sh.getRow(rowNum).getCell(columnNum).getStringCellValue();
	return value;
		}
	/**
	 * this method is used to fetch data from excel file in the form of key value pair
	 * @param sheetName
	 * @param rowNum
	 * @param columnNum
	 * @return
	 * @throws Throwable
	 */
	public HashMap<String, String> readmultipleDataFromExcel(String sheetName) throws Throwable {
		FileInputStream	fis=new FileInputStream(IpathConstants.EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		int count=wb.getSheet(sheetName).getLastRowNum();
		HashMap<String,String> map=new HashMap<String, String>();
		for(int i=0;i<=count;i++) {
			String key=wb.getSheet(sheetName).getRow(i).getCell(0).getStringCellValue();
			String value=wb.getSheet(sheetName).getRow(i).getCell(1).getStringCellValue();
			map.put(key, value);
			}
	return map;
	}
	/**
	 * 
	 * @param sheetName
	 * @param eLib
	 * @param driver
	 * @throws Throwable
	 */
	public void fetchMultipleDataUsingHashmap(String sheetName,ExcelUtility eLib,WebDriver driver) throws Throwable {
		HashMap<String, String> map = eLib.readmultipleDataFromExcel(sheetName);
		for(Entry<String, String> set:map.entrySet()) 
		{
			
			driver.findElement(By.name(set.getKey())).sendKeys(set.getValue());
		}
		}

	public void getLastROWNum(String sheetName) throws Throwable {
		FileInputStream	fis=new FileInputStream(IpathConstants.EXCELPATH);
		Workbook wb = WorkbookFactory.create(fis);
		wb.getSheet(sheetName).getLastRowNum();
	}
		/**
		 * this method is used to write data in to the excel
		 * @param sheetName
		 * @param rowNum
		 * @param columnNum
		 * @throws Throwable
		 */
public void writeDataFromExcel(String sheetName,int rowNum,int columnNum,String data) throws Throwable {
	FileInputStream	fis=new FileInputStream(IpathConstants.EXCELPATH);
	Workbook wb = WorkbookFactory.create(fis);
	wb.getSheet(sheetName).getRow(rowNum).getCell(columnNum).setCellValue(data);
FileOutputStream fos = new FileOutputStream(IpathConstants.EXCELPATH);
wb.write(fos);

		
}

	public Object[][] ReadSetOfData(String SheetName) throws Throwable {
		FileInputStream fis=new FileInputStream(IpathConstants.EXCELPATH);
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(SheetName);
		int lastRow=sh.getLastRowNum()+1;
		int lastCell=sh.getRow(0).getLastCellNum();
		Row a = sh.getRow(lastCell);
		  Cell c = a.getCell(lastCell);
		Object[][] obj=new Object[lastRow][lastCell];
		for(int i=0;i<lastRow;i++) {
			for(int j=0;j<lastCell;j++) {
				obj[i][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		return obj;
		
		
	}
}
