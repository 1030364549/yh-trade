package com.yh.util;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;

public class ExportExcel
{

  /*/**
   * @Author wuhaotai
   * @Description: excel表导出公共方法
   * @Param: list（数据库数据）, String[] names（数据字段）,String fileName（文件名）,
    * HttpServletResponse response（响应）,Map<String,String> TOP（表头）
   * @Return:
   * @Create: 2019/10/17 11:28
   */
    public static void exportExcel(List  list, String[] names,String fileName, HttpServletResponse response,Map<String,String> TOP) throws Exception{
        OutputStream outStream=null;
        Workbook book = null;
        try {
            book = RfExcelUtil.outputExcelFile(list, names, TOP);
            outStream = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" +java.net.URLEncoder.encode(fileName,"utf-8"));
            book.write(outStream);
            outStream.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            //关闭资源
            if(null!=outStream){
                outStream.close();
            }
            if (null != book){
                book.close();
            }
        }
    }








    public static boolean exportExcel(String fileName, String title, String[] headers, List<Map> dataset, String pattern)
    {
        boolean flag = false;
        Workbook workbook = null;
        if (fileName.endsWith("xlsx"))
        {
            workbook = new XSSFWorkbook();
        } else if (fileName.endsWith("xls"))
        {
            workbook = new HSSFWorkbook();
        } else
        {
            try
            {
                throw new Exception("invalid file name, should be xls or xlsx");
            } catch (Exception e)
            {
                System.out.println("必须是xls或者xlsx结尾的文件.");
                e.printStackTrace();
            }

        }

        Sheet sheet = workbook.createSheet(title);
        CellStyle style = workbook.createCellStyle();

        // 列名
        Row row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++)
        {
            Cell cell = row.createCell(i);
            sheet.setColumnWidth(i, 5000);
            style.setAlignment(CellStyle.ALIGN_CENTER);
            cell.setCellValue(headers[i]);
        }

        Iterator<Map> it = dataset.iterator();
        int index = 0;
        while (it.hasNext())
        {
            index++;
            row = sheet.createRow(index);

            Map map = it.next();
            System.out.println(map.toString());
            Set<String> mapKey = (Set<String>)map.keySet();
            System.out.println(mapKey.toString());
            Iterator<String> iterator = mapKey.iterator();
            System.out.println(iterator.toString());
            int num  = 0;
            while(iterator.hasNext()){
                Cell cell = row.createCell(num);
                num++;
                String key = iterator.next();
                System.out.println(key);
                Object obj = map.get(key);
                if (obj instanceof Date)
                {
                    SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                    cell.setCellValue(sdf.format(obj));
                } else if (obj instanceof Integer)
                {
                    cell.setCellValue((Integer) obj);
                } else if (obj instanceof Double)
                {
                    cell.setCellValue((Double) obj);
                } else
                {
                    cell.setCellValue((String) obj);
                }
            }
        }
        FileOutputStream fos;
        try
        {
            fos = new FileOutputStream(fileName);
            workbook.write(fos);
            workbook.close();
            fos.close();
            flag = true;
        } catch (FileNotFoundException e)
        {
            System.out.println("文件不存在");
            flag = false;
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("文件写入错误");
            flag = false;
            e.printStackTrace();

        }
        return flag;
    }

    public static void main(String[] args) {

        String[] headers = new String[2];
        headers[0]="商户名称";
        headers[1]="索引列";
        List<Map> dataset=new ArrayList<>();
        Map map = new HashMap();
        map.put("1",2);
        map.put("2",3);
        dataset.add(map);
        String pattern="yyyy-MM-dd";
        ExportExcel.exportExcel("D:\\excel\\1.xlsx","第一",headers,dataset,pattern);
    }
    }
