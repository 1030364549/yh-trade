package com.yh.util;


import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 读取Excel
 *
 * @author zengwendong
 */
public class PoiUtil {
    private Workbook wb;
    private Sheet sheet;
    private Row row;

    public PoiUtil(String filepath) {
        if (filepath == null) {
            return;
        }
        String ext = filepath.substring(filepath.lastIndexOf("."));
        try {
            InputStream is = new FileInputStream(filepath);
            if (".xls".equals(ext)) {
                wb = new HSSFWorkbook(is);
            } else if (".xlsx".equals(ext)) {
                wb = new XSSFWorkbook(is);
            } else {
                wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取Excel表格表头的内容
     *
     * @param
     * @return String 表头内容的数组
     * @author zengwendong
     */
    public String[] readExcelTitle() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(0);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            // title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = row.getCell(i).getCellFormula();
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @param
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public Map<String, Map<String, Object>> readExcelContent() throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Map<String, Map<String, Object>> content = new HashMap<String, Map<String, Object>>();

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<String, Object> cellValue = new HashMap<String, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                if(null!=obj && !"".equals(obj)){
                    cellValue.put("key" + j, obj);
                }
                j++;
            }
            if(cellValue.size()>0){
                content.put("key" + i, cellValue);
            }
        }
        return content;
    }


    /**
     * 读取Excel数据内容
     *
     * @param
     * @return Map 包含单元格数据内容的Map对象
     * @author zengwendong
     */
    public Map<String, Map<String, Object>> readExcelContent(int sheetat) throws Exception {
        if (wb == null) {
            throw new Exception("Workbook对象为空！");
        }
        Map<String, Map<String, Object>> content = new HashMap<String, Map<String, Object>>();

        sheet = wb.getSheetAt(sheetat);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();
        // 正文内容应该从第二行开始,第一行为表头的标题
        for (int i = 2; i <= rowNum; i++) {
            row = sheet.getRow(i);
            int j = 0;
            Map<String, Object> cellValue = new HashMap<String, Object>();
            while (j < colNum) {
                Object obj = getCellFormatValue(row.getCell(j));
                cellValue.put("key" + j, obj);
                j++;
            }
            content.put("key" + i, cellValue);
        }
        return content;
    }

    /**
     * 根据Cell类型设置数据
     *
     * @param cell
     * @return
     * @author zengwendong
     */
    private Object getCellFormatValue(Cell cell) {
        Object cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
                case Cell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (DateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式
                        // data格式是带时分秒的：2013-7-10 0:00:00
                        // cellvalue = cell.getDateCellValue().toLocaleString();
                        // data格式是不带带时分秒的：2013-7-10
                        Date date = cell.getDateCellValue();
                        cellvalue = date;
                    } else {// 如果是纯数字
                        DecimalFormat df = new DecimalFormat("0");
                        // 取得当前Cell的数值
                        cellvalue = df.format(cell.getNumericCellValue());
                    }
                    break;
                }
                case Cell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRING
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                default:// 默认的Cell值
                    cellvalue = "";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }


    /**
     * ********************************************************.<br>
     * [方法] poi <br>
     * [描述] TODO(生成文件) <br>
     * [参数] TODO(list 处理后的list，colunwidth 列宽) <br>
     * [返回] File <br>
     * [时间] 2017年10月31日 上午10:01:20 <br>
     * [作者] 刘浩龙 【LHL】
     * ********************************************************.<br>
     */
    public static File poi(List<Object[]> list, int[] colunwidth, String fielName, String path, String type) throws IOException {
        return poi(list, colunwidth, fielName, path, type, "Sheet1");
    }

    public static File poi(List<Object[]> list, int[] colunwidth, String fielName, String path, String type, String sheetName) throws IOException {
        // 创建HSSFWorkbook对象(excel的文档对象)
        File file = new File(path, fielName + "." + type);
        File isfile = new File(path);
        if (!isfile.exists()) {
            isfile.mkdirs();
        }
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);

        if ("xlsx".equals(type)) {
            XSSFWorkbook wb = new XSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            XSSFSheet sheet = wb.createSheet(sheetName);
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            XSSFCellStyle cellStyle = wb.createCellStyle();
            XSSFFont fontStyle = wb.createFont();
            fontStyle.setFontName("宋体");
            fontStyle.setFontHeightInPoints((short) 14);
            cellStyle.setFont(fontStyle);
            // 在sheet里创建第二行
            for (int i = 0; i < list.size(); i++) {
                XSSFRow row = sheet.createRow(i);
                row.setRowStyle(cellStyle);
                for (int j = 0; j < list.get(i).length; j++) {
                    //设置单元格值
                    row.createCell(j).setCellValue(list.get(i)[j] + "");
                    //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
                    if (colunwidth[j] != 0) {
                        sheet.setColumnWidth(j, 256 * colunwidth[j]);
                    }
                }
            }

            wb.write(fos);
        } else if ("xls".equals(type)) {
            HSSFWorkbook wb = new HSSFWorkbook();
            // 建立新的sheet对象（excel的表单）
            HSSFSheet sheet = wb.createSheet(sheetName);
            // 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
            // 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
            HSSFCellStyle cellStyle = wb.createCellStyle();
            HSSFFont fontStyle = wb.createFont();
            fontStyle.setFontName("宋体");
            fontStyle.setFontHeightInPoints((short) 14);
            cellStyle.setFont(fontStyle);
            // 在sheet里创建第二行
            for (int i = 0; i < list.size(); i++) {
                HSSFRow row = sheet.createRow(i);
                row.setRowStyle(cellStyle);
                for (int j = 0; j < list.get(i).length; j++) {
                    //设置单元格值
                    row.createCell(j).setCellValue(list.get(i)[j] + "");
                    //设置指定列的列宽，256 * 50这种写法是因为width参数单位是单个字符的256分之一
                    if (colunwidth[j] != 0) {
                        sheet.setColumnWidth(j, 256 * colunwidth[j]);
                    }
                }
            }
            wb.write(fos);
        }
        fos.close();
        return file;
    }


    /**
     * ********************************************************.<br>
     * [方法] toList <br>
     * [描述] TODO(处理结果集 生成创建文件的lsit) <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] List<Object[]> <br>
     * [时间] 2017年10月31日 上午10:02:19 <br>
     * [作者] 刘浩龙 【LHL】
     * ********************************************************.<br>
     */
    public static List<Object[]> toList(List<Map<String, Object>> list, Map<String, Map<String, String>> param, String[] names, String[] titles) {
        List<Object[]> dataList = new ArrayList<Object[]>();
        dataList.add(titles);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> data = list.get(i);
            String[] strs = new String[names.length];
            for (int j = 0; j < names.length; j++) {
                String key = names[j];
                if (data.get(key) != null && !"".equals(data.get(key))) {
                    if (param != null && param.get(key) != null && !"".equals(param)) {
                        strs[j] = param.get(key).get(data.get(key).toString());

                    } else {
                        strs[j] = data.get(key).toString();
                    }
                } else {
                    strs[j] = "";
                }
            }
            dataList.add(strs);
        }
        return dataList;
    }


    /**
     * <p>
     * Description:判断时间类型
     * </p>
     *
     * @param sheet    sheet
     * @param i        行
     * @param colIndex 列
     * @return val 返回的string
     */
    public static String toNumeric(XSSFSheet sheet, int i, int colIndex) {
        Cell cell = sheet.getRow(i).getCell(colIndex);
        String val = null;
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                val = cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                Boolean val1 = cell.getBooleanCellValue();
                val = val1.toString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (XSSFDateUtil.isCellDateFormatted(cell)) {     //日期类型
                    val = cell.toString();
                } else {
                    DecimalFormat df = new DecimalFormat("0");
                    val = df.format(cell.getNumericCellValue());                            //数值类型的
                }
                break;
            case Cell.CELL_TYPE_BLANK:
                break;
        }
        return val;
    }

    public static class XSSFDateUtil extends DateUtil {
        protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
            return DateUtil.absoluteDay(cal, use1904windowing);
        }
    }
}
