package com.galaxyeye.websocket.tool.excel; /*
 * Description:com.galaxyeye.websocket.tool.excel
 * @Date Create on 18:03
 * @author "mailto:yangyi@galaxyeye-tech.com">yangyi</a>
 * @Version JDK 1.8
 * @since version 1.0 Copyright 2019/10/8日galaxyeye All Rights Reserved.
 */

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
@Slf4j
public class ExcelUtil {

    public static final String OFFICE_EXCEL_XLS = "xls";
    public static final String OFFICE_EXCEL_XLSX = "xlsx";


    public static String readExcel(String filepath, Integer sheetNo)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        StringBuilder sb = new StringBuilder();
        Workbook workbook = getWorkbook(filepath);
        if (workbook != null) {
            if (sheetNo == null) {
                int numberOfSheets = workbook.getNumberOfSheets();
                for (int i = 0; i < numberOfSheets; i++) {
                    Sheet sheet = workbook.getSheetAt(i);
                    if (sheet == null) {
                        continue;
                    }
                    sb.append(readExcelSheet(sheet));
                }
            } else {
                Sheet sheet = workbook.getSheetAt(sheetNo);
                if (sheet != null) {
                    sb.append(readExcelSheet(sheet));
                }
            }
        }
        return sb.toString();
    }


    public static Workbook getWorkbook(String filepath)
            throws EncryptedDocumentException, InvalidFormatException, IOException {
        InputStream is = null;
        Workbook wb = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffiex = getSuffiex(filepath);
            if (StringUtils.isBlank(suffiex)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            if (OFFICE_EXCEL_XLS.equals(suffiex) || OFFICE_EXCEL_XLSX.equals(suffiex)) {
                try {
                    is = new FileInputStream(filepath);
                    wb = WorkbookFactory.create(is);
                } finally {
                    if (is != null) {
                        is.close();
                    }
                    if (wb != null) {
                        wb.close();
                    }
                }
            } else {
                throw new IllegalArgumentException("该文件非Excel文件");
            }
        }
        return wb;
    }


    private static String getSuffiex(String filepath) {
        if (StringUtils.isBlank(filepath)) {
            return "";
        }
        int index = filepath.lastIndexOf(".");
        if (index == -1) {
            return "";
        }
        return filepath.substring(index + 1, filepath.length());
    }

    private static String readExcelSheet(Sheet sheet) {
        StringBuilder sb = new StringBuilder();
        if(sheet != null){
            int rowNos = sheet.getLastRowNum();
            for (int i = 0; i <= rowNos; i++) {
                Row row = sheet.getRow(i);
                if(row != null){
                    int columNos = row.getLastCellNum();
                    for (int j = 0; j < columNos; j++) {
                        Cell cell = row.getCell(j);
                        if(cell != null){
                            cell.setCellType(CellType.STRING);
                            sb.append(cell.getStringCellValue() + " ");
                        }
                    }
                }
            }
        }

        return sb.toString();
    }


    public static List<String> readTitle(String filepath, int sheetNo)
            throws IOException, EncryptedDocumentException, InvalidFormatException {
        List<String> titles=new ArrayList<>();
        Row returnRow = null;
        Workbook workbook = getWorkbook(filepath);
        if (workbook != null) {
            Sheet sheet = workbook.getSheetAt(sheetNo);
            returnRow = readTitle(sheet);
        }
        Iterator<Cell> cell=returnRow.cellIterator();
        while (cell.hasNext()){
            Cell tmp=cell.next();
            tmp.setCellType(CellType.STRING);
            titles.add(tmp.getStringCellValue().trim());
        }
        return titles;
    }


    public static Row readTitle(Sheet sheet) throws IOException {
        Row returnRow = null;
        int totalRow = sheet.getLastRowNum();// 得到excel的总记录条数
        for (int i = 0; i < totalRow; i++) {// 遍历行
            Row row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            returnRow = sheet.getRow(0);
            break;
        }
        return returnRow;
    }

    public static boolean writeExcel(String filepath, String sheetName, List<String> titles,
                                     List<Map<String, Object>> values) throws IOException {
        boolean success = false;
        OutputStream outputStream = null;
        if (StringUtils.isBlank(filepath)) {
            throw new IllegalArgumentException("文件路径不能为空");
        } else {
            String suffiex = getSuffiex(filepath);
            if (StringUtils.isBlank(suffiex)) {
                throw new IllegalArgumentException("文件后缀不能为空");
            }
            Workbook workbook;
            if ("xls".equals(suffiex.toLowerCase())) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }
            Sheet sheet;
            if (StringUtils.isBlank(sheetName)) {
                sheet = workbook.createSheet();
            } else {
                sheet = workbook.createSheet(sheetName);
            }
            sheet.setDefaultColumnWidth((short) 15);
            Row row = sheet.createRow(0);
            Map<String, Integer> titleOrder = Maps.newHashMap();
            for (int i = 0; i < titles.size(); i++) {
                Cell cell = row.createCell(i);
                String title = titles.get(i);
                cell.setCellValue(title);
                titleOrder.put(title, i);
            }
            Iterator<Map<String, Object>> iterator = values.iterator();
            int index = 1;
            while (iterator.hasNext()) {
                row = sheet.createRow(index);
                Map<String, Object> value = iterator.next();
                for (Map.Entry<String, Object> map : value.entrySet()) {
                    String title = map.getKey().trim();
                    log.info("title="+title);
                    log.info("titleOrder="+ JSON.toJSONString(titleOrder));

                    int i = titleOrder.get(title);
                    Cell cell = row.createCell(i);
                    Object object = map.getValue();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    if (object instanceof Double) {
                        cell.setCellValue((Double) object);
                    } else if (object instanceof Date) {
                        String time = simpleDateFormat.format((Date) object);
                        cell.setCellValue(time);
                    } else if (object instanceof Calendar) {
                        Calendar calendar = (Calendar) object;
                        String time = simpleDateFormat.format(calendar.getTime());
                        cell.setCellValue(time);
                    } else if (object instanceof Boolean) {
                        cell.setCellValue((Boolean) object);
                    } else {
                        if (object != null) {
                            cell.setCellValue(object.toString());
                        }
                    }
                }
                index++;
            }

            try {
                outputStream = new FileOutputStream(filepath);
                workbook.write(outputStream);
                success = true;
            } finally {
                if (outputStream != null) {
                    outputStream.close();
                }
                if (workbook != null) {
                    workbook.close();
                }
            }
            return success;
        }
    }

    private static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = Maps.newHashMap();
        XSSFCellStyle titleStyle = (XSSFCellStyle) wb.createCellStyle();
        titleStyle.setAlignment(HorizontalAlignment.CENTER);
        titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        titleStyle.setLocked(true);
        titleStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        Font titleFont = wb.createFont();
        titleFont.setFontHeightInPoints((short) 16);
        titleFont.setBold(true);
        titleFont.setFontName("微软雅黑");
        titleStyle.setFont(titleFont);
        styles.put("title", titleStyle);
        XSSFCellStyle headerStyle = (XSSFCellStyle) wb.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex()); // 前景色
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND); // 颜色填充方式
        headerStyle.setWrapText(true);
        headerStyle.setBorderRight(BorderStyle.THIN); // 设置边界
        headerStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short) 12);
        headerFont.setColor(IndexedColors.WHITE.getIndex());
        titleFont.setFontName("微软雅黑");
        headerStyle.setFont(headerFont);
        styles.put("header", headerStyle);

        Font cellStyleFont = wb.createFont();
        cellStyleFont.setFontHeightInPoints((short) 12);
        cellStyleFont.setColor(IndexedColors.BLUE_GREY.getIndex());
        cellStyleFont.setFontName("微软雅黑");
        XSSFCellStyle cellStyleA = (XSSFCellStyle) wb.createCellStyle();
        cellStyleA.setAlignment(HorizontalAlignment.CENTER); // 居中设置
        cellStyleA.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleA.setWrapText(true);
        cellStyleA.setBorderRight(BorderStyle.THIN);
        cellStyleA.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderLeft(BorderStyle.THIN);
        cellStyleA.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderTop(BorderStyle.THIN);
        cellStyleA.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setBorderBottom(BorderStyle.THIN);
        cellStyleA.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleA.setFont(cellStyleFont);
        styles.put("cellA", cellStyleA);

        XSSFCellStyle cellStyleB = (XSSFCellStyle) wb.createCellStyle();
        cellStyleB.setAlignment(HorizontalAlignment.CENTER);
        cellStyleB.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyleB.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        cellStyleB.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyleB.setWrapText(true);
        cellStyleB.setBorderRight(BorderStyle.THIN);
        cellStyleB.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderLeft(BorderStyle.THIN);
        cellStyleB.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderTop(BorderStyle.THIN);
        cellStyleB.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setBorderBottom(BorderStyle.THIN);
        cellStyleB.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellStyleB.setFont(cellStyleFont);
        styles.put("cellB", cellStyleB);

        return styles;
    }


    public static void writeExcel(String srcFilepath, String desFilepath)
            throws IOException, EncryptedDocumentException, InvalidFormatException {
        FileOutputStream outputStream = null;
        String suffiex = getSuffiex(desFilepath);
        if (StringUtils.isBlank(suffiex)) {
            throw new IllegalArgumentException("文件后缀不能为空");
        }
        Workbook workbook_des;
        if ("xls".equals(suffiex.toLowerCase())) {
            workbook_des = new HSSFWorkbook();
        } else {
            workbook_des = new XSSFWorkbook();
        }

        Workbook workbook = getWorkbook(srcFilepath);
        if (workbook != null) {
            int numberOfSheets = workbook.getNumberOfSheets();
            for (int k = 0; k < numberOfSheets; k++) {
                Sheet sheet = workbook.getSheetAt(k);
                Sheet sheet_des = workbook_des.createSheet(sheet.getSheetName());
                if (sheet != null) {
                    int rowNos = sheet.getLastRowNum();
                    for (int i = 0; i <= rowNos; i++) {
                        Row row = sheet.getRow(i);
                        Row row_des = sheet_des.createRow(i);
                        if(row != null){
                            int columNos = row.getLastCellNum();
                            for (int j = 0; j < columNos; j++) {
                                Cell cell = row.getCell(j);
                                Cell cell_des = row_des.createCell(j);
                                if(cell != null){
                                    cell.setCellType(CellType.STRING);
                                    cell_des.setCellType(CellType.STRING);

                                    cell_des.setCellValue(cell.getStringCellValue());
                                }
                            }
                        }
                    }
                }

            }
        }

        try {
            outputStream = new FileOutputStream(desFilepath);
            workbook_des.write(outputStream);
        } finally {
            if (outputStream != null) {
                outputStream.close();
            }
            if (workbook != null) {
                workbook_des.close();
            }
        }
    }



    public static List<Map<String, String>> readExcelByStream(InputStream stream, String extString, String[] columns,int sheetIndex) throws Exception {
        Workbook wb = getExcelWb(stream, extString);
        List<Map<String, String>> list = null;
        String cellData = null;
        if(wb != null){
            list = new ArrayList<Map<String,String>>();
            Sheet sheet = wb.getSheetAt(sheetIndex);
            int rownum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int column = row.getPhysicalNumberOfCells();
            int columnTmp=0;
            if(column>=columns.length){
                columnTmp=columns.length;
            }

            for(int i = 1; i < rownum; i++){
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if(row != null){
                    for(int j = 0; j < columnTmp; j++){
                        cellData = String.valueOf(getCellFormatValue(row.getCell(j)));
                        map.put(columns[j], cellData);
                    }
                }else {
                    break;
                }
                list.add(map);
            }

        }
        stream.close();
        wb.close();
        return list;
    }


    public static List<Map<String, String>> readExcelByStream(InputStream stream, String extString,int sheetIndex) throws Exception {
        List<String> listTmp=new ArrayList<>();
        Workbook wb = getExcelWb(stream, extString);
        List<Map<String, String>> list = null;
        String cellData = null;
        if(wb != null){
            list = new ArrayList<Map<String,String>>();
            Sheet sheet = wb.getSheetAt(sheetIndex);
            int rownum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(0);
            int column = row.getPhysicalNumberOfCells();

            for (int j = 0; j < column; j++) {
                Cell cell = row.getCell(j);
                if(cell != null){
                    cell.setCellType(CellType.STRING);
                    listTmp.add(cell.getStringCellValue() + " ");
                }
            }
            String[] columns={};
            columns=listTmp.toArray(columns );

            for(int i = 1; i < rownum; i++){
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if(row != null){
                    for(int j = 0; j < column; j++){
                        cellData = String.valueOf(getCellFormatValue(row.getCell(j)));
                        map.put(columns[j], cellData);
                    }
                }else {
                    break;
                }
                list.add(map);
            }

        }
        stream.close();
        wb.close();
        return list;
    }


    public static List<Map<String, String>> readExcelByStream(InputStream stream, String extString,int sheetIndex,int rowNum) throws Exception {
        List<String> listTmp=new ArrayList<>();
        Workbook wb = getExcelWb(stream, extString);
        List<Map<String, String>> list = null;
        String cellData = null;
        if(wb != null){
            list = new ArrayList<Map<String,String>>();
            Sheet sheet = wb.getSheetAt(sheetIndex);
            int rownum = sheet.getPhysicalNumberOfRows();
            Row row = sheet.getRow(rowNum);
            int column = row.getPhysicalNumberOfCells();

            for (int j = 0; j < column; j++) {
                Cell cell = row.getCell(j);
                if(cell != null){
                    cell.setCellType(CellType.STRING);
                    listTmp.add(cell.getStringCellValue() + " ");
                }
            }
            String[] columns={};
            columns=listTmp.toArray(columns );

            for(int i = rowNum+1; i < rownum; i++){
                Map<String, String> map = new LinkedHashMap<String, String>();
                row = sheet.getRow(i);
                if(row != null){
                    for(int j = 0; j < column; j++){
                        cellData = String.valueOf(getCellFormatValue(row.getCell(j)));
                        if(columns[j].trim().length()<=0 && columns[j].trim().isEmpty()){
                            columns[j]=String.valueOf(j);
                            map.put("title"+String.valueOf(j), cellData);
                        }else {
                            map.put(columns[j].trim(), cellData);
                        }
                    }
                }else {
                    break;
                }
                list.add(map);
            }

        }
        stream.close();
        wb.close();
        return list;
    }
    public static Workbook getExcelWb(InputStream stream, String extString) throws Exception {
        Workbook wb = null;
        if(stream == null){
            return null;
        }

        if(".xls".equals(extString)){
            return wb = new HSSFWorkbook(stream);
        }else if(".xlsx".equals(extString)){
            return wb = new XSSFWorkbook(stream);
        }else{
            return wb = null;
        }

    }

    public static Object getCellFormatValue(Cell cell) {
        Object cellValue = null;
        if(cell != null){
            switch (cell.getCellType()) {
                case NUMERIC:{
                    cellValue = String.valueOf(cell.getNumericCellValue());
                    break;
                }

                case FORMULA:{
                    if(DateUtil.isCellDateFormatted(cell)){
                        cellValue = cell.getDateCellValue();
                    }else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }

                case STRING:{
                    cellValue = cell.getRichStringCellValue();
                    break;
                }

                default:
                    cellValue = "";

            }
        }else {
            cellValue = "";
        }
        return cellValue;
    }


    public static boolean writeExcelByList(String filepath, String sheetName,
               List<Map<String, String>> values,List<String> titles) throws Exception {
        List<Map<String, Object>> t5=new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Map<String, String> h1=values.get(i);
            Map<String, Object> h2=new HashMap<>();
            h2.putAll(h1);
            t5.add(h2);
        }
        return writeExcel(filepath,"Result",titles,t5);
    }


    public static class ExcelUtilTest{

        public static void readExcelXlsx() throws Exception {
            FileInputStream inputStream = new FileInputStream(new File("C:/Users/yangyi/Desktop/test.xlsx"));
            XSSFWorkbook wordBook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = wordBook.getSheetAt(0);
            XSSFRow row = sheet.getRow(2);
            XSSFCell cell = row.getCell(3);
            String value = cell.getStringCellValue();
            System.out.println(value);
            inputStream.close();
            wordBook.close();
        }

        public static void readExcelXls() throws Exception {
            FileInputStream inputStream = new FileInputStream(new File("C:/Users/yangyi/Desktop/test.xls"));
            HSSFWorkbook wordBook = new HSSFWorkbook(inputStream);
            HSSFSheet sheet = wordBook.getSheetAt(0);
            HSSFRow row = sheet.getRow(2);
            HSSFCell cell = row.getCell(3);
            String value = cell.getStringCellValue();
            System.out.println(value);
            inputStream.close();
            wordBook.close();
        }

        public static void writeExcelXlsxOrXls(int sheetIndex,String sheetName) throws Exception {

            File excel = new File("C:/Users/yangyi/Desktop/yy.xlsx");  // 读取文件
            FileOutputStream outputStream = new FileOutputStream(excel);
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet(sheetName);
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell1 = row.createCell(0, CellType.STRING);
            cell1.setCellValue("hellword");
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
            workbook.close();
        }
    }
}
