package com.deepblue777.frame.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * [简单描述]
 *
 * @author svenbarnett/3197544360@qq.com
 * @date [v1, 2020-01-09 19:36]
 */

public class ExcelUtil {

    private String filepath;

    private ExcelParseHandler handler;

    public ExcelUtil(String filepath, ExcelParseHandler handler) {
        this.filepath = filepath;
        this.handler = handler;
    }

    public void parse() {
        Workbook wb = readExcel(filepath);
        //获取所有sheet
        int sheetNumber = wb.getNumberOfSheets();
        for (int i = 0; i < sheetNumber; i++) {
            Sheet sheet = wb.getSheetAt(i);
            int lastRowNum = sheet.getLastRowNum();
            for (int j = 0; j <= lastRowNum; j++) {
                Row row = sheet.getRow(j);
                int colnumNumber = row.getPhysicalNumberOfCells();
                Object[] data;
                data = new Object[colnumNumber];
                if (row == null) {
                    break;
                }
                for (int k = 0; k < colnumNumber; k++) {
                    data[k] = row.getCell(k);
                }
                handler.handle(i, j, data);
            }
        }
    }

    /**
     * 读取excel
     *
     * @param filePath
     * @return
     */
    public Workbook readExcel(String filePath) {
        if (filePath == null) {
            return null;
        }
        String extString = filePath.substring(filePath.lastIndexOf("."));
        InputStream is;
        try {
            is = new FileInputStream(filePath);
            if (".xls".equals(extString)) {
                return new HSSFWorkbook(is);
            } else if (".xlsx".equals(extString)) {
                return new XSSFWorkbook(is);
            } else {
                return null;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
