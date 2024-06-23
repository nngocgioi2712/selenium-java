package com.vmo.demowebshop.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ExcelUtil {

    private final String currentDir = System.getProperty("user.dir");
    private XSSFWorkbook excelWBook;
    private XSSFSheet excelWSheet;
    private XSSFCell cell;
    private XSSFRow row;

    public ExcelUtil() {

    }

    public ExcelUtil(String excelRelativePath, String sheetName) {

        String absolutePath = String.join(File.separator, currentDir, excelRelativePath);
        try {
            FileInputStream fileIn = new FileInputStream(absolutePath);
            excelWBook = new XSSFWorkbook(fileIn);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public int getRowNum() {
        return excelWSheet.getLastRowNum();
    }

    public int getColNum() {
        return excelWSheet.getRow(0).getLastCellNum();
    }

    public String getCellData(int rowNum, int colNum) {
        DataFormatter df = new DataFormatter();
        try {
             return df.formatCellValue(excelWSheet.getRow(rowNum).getCell(colNum));
        } catch (NullPointerException npe) {
            throw new RuntimeException(npe.getMessage());
        }
    }

    public Object[][] getData() {
        int rowNum = getRowNum();
        int colNum = getColNum();

        Object[][] data = new Object[rowNum][colNum];

        int currentRow = 0;

        for (int i = 1; i <= rowNum; i++) {
            for (int j = 0; j < colNum; j++) {
                data[currentRow][j] = getCellData(i, j);
            }
            currentRow++;
        }
        return data;
    }

}
