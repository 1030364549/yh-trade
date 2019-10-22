package com.yh.util;

import com.yh.config.PropertiesListenerConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ss on 2019/6/28.
 */
public class RfExcelUtil {
    /**
     *
     *********************************************************.<br>
     * [方法] outputExcelFile <br>
     * [描述] TODO(导出Excel表格) <br>
     * [参数] 数据源、输入流、标题 <br>
     * [返回] HSSFWorkbook <br>
     * [时间] 2014-8-18 下午05:39:56 <br>
     *********************************************************.<br>
     */
    @SuppressWarnings("deprecation")
    public static Workbook outputExcelFile(List<Map<String,Object>> list, InputStream inputStream, String[] names_en,Map<String,String> names_cn) throws Exception{
        InputStream  ss=inputStream;
        Workbook workbook=null;
        try {
            //兼容多种excel导出
            workbook = WorkbookFactory.create(ss);
            Map<String, CellStyle> mapstyle = createStyles(workbook);//标题样式和字体样式
            Cell[] firstcell = new Cell[names_en.length];
            Sheet hs = workbook.getSheetAt(0);//获得一个sheet

            Row firstrow = hs.createRow((short) 0);//第二行的标题
            for (int i = 0; i < names_en.length; i++) {
                firstcell[i] = firstrow.createCell((short) i);
                firstcell[i].setCellStyle(mapstyle.get("cell_header_title"));
                firstcell[i].setCellValue(names_cn.get(names_en[i]));
                hs.setColumnWidth(i, 30 * 256);
            }
            hs.createFreezePane(0, 1);//冻结行
            for (int i = 0; i < list.size(); i++) {
                firstrow = hs.createRow((short) i + 1);//下标为1的开始
                Map<String,Object> tmpMap = list.get(i);
                for (int j = 0; j < names_en.length; j++) {    //循环excel里的列数
                    firstcell[j] = firstrow.createCell((short) j);
                    firstcell[j].setCellStyle(mapstyle.get("cell_data_default"));//字体样式
                    firstcell[j].setCellType(Cell.CELL_TYPE_STRING);
                    if (tmpMap.get(names_en[j]) == null || "".equals(tmpMap.get(names_en[j]))) {
                        firstcell[j].setCellValue(" ");
                    } else {
                        firstcell[j].setCellValue(tmpMap.get(names_en[j]).toString());
                    }
                }
            }

        }catch (Exception e){
            return workbook;
        }finally {
            if(ss!=null){
                ss.close();
            }
        }
        return workbook;
    }

    /**
     *
     *********************************************************.<br>
     * [方法] outputExcelFile <br>
     * [参数] 数据源、输入流、标题 <br>
     * [返回] HSSFWorkbook <br>
     * [时间] 2014-8-18 下午05:39:56 <br>
     *********************************************************.<br>
     */
    @SuppressWarnings("deprecation")
    public static Workbook outputExcelFile(List<Map<String,Object>> list, String[] names_en,Map<String,String> names_cn) throws Exception{
        Workbook workbook=null;
        try {
            //兼容多种excel导出
            workbook = new HSSFWorkbook();
            Map<String, CellStyle> mapstyle = createStyles(workbook);//标题样式和字体样式
            Cell[] firstcell = new Cell[names_en.length];
            Sheet hs = workbook.createSheet();//获得一个sheet

            Row firstrow = hs.createRow((short) 0);//第二行的标题
            for (int i = 0; i < names_en.length; i++) {
                firstcell[i] = firstrow.createCell((short) i);
                firstcell[i].setCellValue(names_cn.get(names_en[i]));
                firstcell[i].setCellStyle(mapstyle.get("cell_header_title"));
                hs.setColumnWidth(i, 30 * 256);
            }
            hs.createFreezePane(0, 1);//冻结行
            for (int i = 0; i < list.size(); i++) {
                firstrow = hs.createRow((short) i + 1);//下标为1的开始
                Map<String,Object> tmpMap = list.get(i);
                for (int j = 0; j < names_en.length; j++) {    //循环excel里的列数
                    firstcell[j] = firstrow.createCell((short) j);
                    firstcell[j].setCellType(Cell.CELL_TYPE_STRING);
                    firstcell[j].setCellStyle(mapstyle.get("cell_data_default"));//字体样式
                    if (tmpMap.get(names_en[j]) == null || "".equals(tmpMap.get(names_en[j]))) {
                        firstcell[j].setCellValue(" ");
                    } else {
                        firstcell[j].setCellValue(tmpMap.get(names_en[j]).toString());
                    }
                }
            }

        }catch (Exception e){
            return workbook;
        }
        return workbook;
    }

    /**
     *
     *********************************************************.<br>
     * [方法] createStyles <br>
     * [描述] TODO(设置Excel的标题和样式) <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map<String,HSSFCellStyle> <br>
     * [时间] 2014-8-18 下午05:39:25 <br>
     *********************************************************.<br>
     */
    public static Map<String, CellStyle> createStyles(Workbook wb) {
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        //----------------------标题样式---------------------------
        CellStyle cell_header_title = wb.createCellStyle();
        Font font2 = wb.createFont();
        font2.setFontName("宋体");
        font2.setFontHeightInPoints((short) 13);//设置字体大小
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 粗体
        cell_header_title.setFont(font2);//选择需要用到的字体格式
        cell_header_title.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        cell_header_title.setWrapText(false);// 自动换行
        cell_header_title.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cell_header_title.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cell_header_title.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cell_header_title.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        styles.put("cell_header_title", cell_header_title);

        //-----------------------字体样式---------------------------
        CellStyle cell_data_default = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 11);//设置字体大小
        cell_data_default.setFont(font);//选择需要用到的字体格式
        cell_data_default.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
        cell_data_default.setWrapText(true);// 自动换行
        cell_data_default.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
        cell_data_default.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
        cell_data_default.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
        cell_data_default.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
        styles.put("cell_data_default", cell_data_default);
        return styles;
    }

    /**
     *
     *********************************************************.<br>
     * [方法] outputExcelFile <br>
     * [描述] TODO(导出Excel表格) <br>
     * [参数] 数据源、输入流、标题 <br>
     * [返回] HSSFWorkbook <br>
     * [时间] 2014-8-18 下午05:39:56 <br>
     *********************************************************.<br>
     */
    @SuppressWarnings("deprecation")
    public static Workbook outputExcelFile2(String fullpath,List<Map<String,Object>> list, String[] names_en,Map<String,String> names_cn,int type,String sheetname) throws Exception{
        Workbook workbook=null;
        try {
            //兼容多种excel导出
            String suffiex = getSuffiex(fullpath);
            if ("xls".equals(suffiex.toLowerCase())) {
                workbook = new HSSFWorkbook();
            } else {
                workbook = new XSSFWorkbook();
            }

            Cell[] cell = new Cell[names_en.length];

            Map<String, CellStyle> mapstyle = createStyles(workbook);//标题样式和字体样式

            //创建工作表sheet
            Sheet sheet=workbook.createSheet(sheetname);
            //创建第一行
            Row row=sheet.createRow(0);
            Row row1=sheet.createRow(1);

            //插入第一行数据的表头
            for(int i=0;i<names_en.length;i++){
                cell[i]=row.createCell(i);
                if (i==0){
                    Map<String,Object> tmpMap=list.get(0);
                    cell[i].setCellValue(tmpMap.get("TITLE").toString());
                }
            }
            //合并标题 起始行，结束行，起始列，结束列  行和列都是从0开始计数，且起始结束都会合并
            CellRangeAddress region = new CellRangeAddress(0, 0, 0, names_en.length-1);
            sheet.addMergedRegion(region);

            for(int i=0;i<names_en.length;i++){
                cell[i]=row1.createCell(i);
            }

            //表头
            Row row3=sheet.createRow(2);
            for(int i=0;i<names_en.length;i++){
                cell[i]=row3.createCell(i);
                cell[i].setCellStyle(mapstyle.get("cell_header_title"));
                cell[i].setCellValue(names_cn.get(names_en[i]));
            }

            //写入内容
            for (int i=0;i<list.size();i++){
                Row tmpRow=sheet.createRow(i+3);
                Map<String,Object> tmpMap = list.get(i);
                for (int j = 0; j < names_en.length; j++) {    //循环excel里的列数
                    cell[j] = tmpRow.createCell((short) j);
                    cell[j].setCellStyle(mapstyle.get("cell_data_default"));//字体样式
                    cell[j].setCellType(Cell.CELL_TYPE_STRING);
                    if (tmpMap.get(names_en[j]) == null || "".equals(tmpMap.get(names_en[j]))) {
                        cell[j].setCellValue(" ");
                    } else {
                        cell[j].setCellValue(tmpMap.get(names_en[j]).toString());
                    }
                }
            }
        }catch (Exception e){
            throw e;
        }
        return workbook;
    }

    /**
     * 获取后缀
     * @param filepath filepath 文件全路径
     */
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

    /**
     * 模板名称、目标文件名称、数据源、标题-英文、标题-中文
     * @throws Exception
     */
    public String createExcel(String modelname, String filename, List<Map<String,Object>> list, String[] names_en, Map<String,String> names_cn){
        String result="00";
        FileOutputStream out=null;
        Workbook book=null;
        try {
            ClassPathResource resource = new ClassPathResource("public/excelmodel/"+modelname);
            InputStream inputStream = resource.getInputStream();
             book= outputExcelFile(list,inputStream,names_en,names_cn);
            File ftmp=new File(PropertiesListenerConfig.propertiesMap.get("rfexcelpath").toString());
            if (!ftmp.exists()){
                ftmp.mkdirs();
            }
            out=new FileOutputStream(PropertiesListenerConfig.propertiesMap.get("rfexcelpath")+"/"+ filename);
            book.write(out);
            out.flush();
        } catch (Exception e) {
            result="ex";
        } finally {
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != book){
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    /**
     * 第一行：名称
     * 第二行空
     * 第三行：标题
     * 第四行：开始写入数据
     * 最后一行：合计
     * 目标文件名称、数据源、标题-英文、标题-中文、类型(最后一行是否合计：0-合、1-否)、SheetName
     * @return
     */
    public String createExcel2(String filename, List<Map<String,Object>> list, String[] names_en, Map<String,String> names_cn,int type,String sheetname){
        String result="00";
        FileOutputStream out=null;
        Workbook book=null;
        try {
            File ftmp=new File(PropertiesListenerConfig.propertiesMap.get("rfexcelpath").toString());
            if (!ftmp.exists()){
                ftmp.mkdirs();
            }
            //创建Excel文件
            String fullpath= PropertiesListenerConfig.propertiesMap.get("rfexcelpath").toString()+"/"+filename;
            File file = new File(fullpath);
            if (!file.exists()){
                file.createNewFile();
            }

             book= outputExcelFile2(fullpath,list,names_en,names_cn,type,sheetname);
            out=new FileOutputStream(fullpath);
            book.write(out);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
            result="ex";
        } finally {
            if(null!=out){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != book){
                try {
                    book.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

}
