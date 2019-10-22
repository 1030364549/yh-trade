package com.yh.util;

import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

public class ExcelUtil {
	/** 读取的总行数 */
	private int totalRows = 0;
	/** 读取的总列数 */
	private int totalCells = 0;
	/** 起始读取的行数 */
	private int fstrow = 0;
	
	/**
	 *********************************************************.<br>
	 * [方法] readList <br>
	 * [描述] 解析Excel,直接获得数组集合 <br>
	 * [参数] file:文件流 filename:版本.xls或者.xlsx <br>
	 * [返回] List<String[]> <br>
	 * [时间] 2016-1-6 下午3:43:42 <br>
	 *********************************************************.<br>
	 */
	public List<String[]> readList(File file, String fileName) throws Exception{
    	List<String[]> list=new ArrayList<String[]>();
    	List<ArrayList<String>> dataList=read(file,fileName);
    	if(dataList==null){
    		return list;
    	}
    	for (ArrayList<String> innerLst : dataList) {
			StringBuffer rowData = new StringBuffer();
			for (String dataStr : innerLst) {
				rowData.append(",").append(dataStr);
			}
			if (rowData.length() > 0) {
				String arrays[]=rowData.deleteCharAt(0).toString().split(",");
				if(arrays.length>0){
					list.add(arrays);
				}
			}
    	}
    	return list;
    }
	
	/**
	 *********************************************************.<br>
	 * [方法] read <br>
	 * [描述] 解析Excel,根据Excel文件的版本获得相应的实例 <br>
	 * [返回] List<ArrayList<String>> <br>
	 * [时间] 2016-1-6 下午3:45:04 <br>
	 *********************************************************.<br>
	 */
	public List<ArrayList<String>> read(File file, String fileName) throws Exception{
		FileInputStream input=null;
		List<ArrayList<String>> dataList;
		Workbook wb=null;
		try {
			boolean isExcel2003 = true;
			dataList = new ArrayList<ArrayList<String>>();
			input = new FileInputStream(file);
			// 检查文件名是否为空或者是否是Excel格式的文件
			if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				return dataList;
			}
			// 对文件的合法性进行验
			if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
				isExcel2003 = false;
			}
			// 检查文件是否存在
			if (file == null || !file.exists()) {
				return dataList;
			}
			 wb = isExcel2003 ? new HSSFWorkbook(input) : new XSSFWorkbook(input);
			dataList = read(wb);
		}catch (Exception e){
			return null;
		}finally {
			if(input!=null){
				input.close();
			}
			if (wb != null){
				wb.close();
			}
		}
        return dataList;
    }
	
	/**
	 *********************************************************.<br>
	 * [方法] read <br>
	 * [描述] 解析Excel,读取每一行的数据，封装成集合 <br>
	 * [返回] List<ArrayList<String>> <br>
	 * [时间] 2016-1-6 下午3:45:50 <br>
	 *********************************************************.<br>
	 */
	private List<ArrayList<String>> read(Workbook wb) {
        List<ArrayList<String>> dataLst = new ArrayList<ArrayList<String>>();
        Sheet sheet = wb.getSheetAt(0);// 得到第一个shell
        this.totalRows = sheet.getPhysicalNumberOfRows();
        if (this.totalRows >= 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        for (int r=fstrow ; r < this.totalRows; r++) {// 循环Excel的行
            Row row = sheet.getRow(r);
            if (row == null) { continue;}
            ArrayList<String> rowLst = new ArrayList<String>();
            for (short c = 0; c < this.getTotalCells(); c++) { // 循环Excel的列
                Cell cell = row.getCell(c);
                String cellValue = "";
                if (cell == null) {
                    rowLst.add(cellValue);
                    continue;
                }
                if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) { // 处理数字型的,自动去零
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {// 在excel里,日期也是数字,在此要进行判断
                        cellValue = get4yMdHms(cell.getDateCellValue());
                    } else {
                    	DecimalFormat df = new DecimalFormat("0");  
                    	cellValue = df.format(cell.getNumericCellValue()); 
                    }
                } else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {// 处理字符串型
                    cellValue = cell.getStringCellValue();
                } else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {// 处理布尔型
                    cellValue = cell.getBooleanCellValue() + "";
                } else {// 其它数据类型
                    cellValue = cell.toString() + "";
                }
                rowLst.add(cellValue);
            }
            dataLst.add(rowLst);
        }
        return dataLst;
    }
	
	/** 根据日期得到YYYY-MM-DD HH:MM:SS.SSS格式字符串 */
    public static String get4yMdHms(Date date) {
    	 SimpleDateFormat simpleDateFormate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	 return simpleDateFormate.format(date);
    }
    
    /**
	 * 
	 *********************************************************.<br>
	 * [方法] outputExcelFile <br>
	 * [描述] TODO(导出Excel表格) <br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] HSSFWorkbook <br>
	 * [时间] 2014-8-18 下午05:39:56 <br>
	 *********************************************************.<br>
	 */
	@SuppressWarnings("deprecation")
	public static Workbook outputExcelFile(List<String[]> list, InputStream inputStream, String[] names, int[] width) throws Exception{
		InputStream  ss=inputStream;
		Workbook workbook=null;
		try {
			//兼容多种excel导出
			workbook = WorkbookFactory.create(ss);
			Map<String, CellStyle> mapstyle = createStyles(workbook);//标题样式和字体样式
			Cell[] firstcell = new Cell[names.length];
			Sheet hs = workbook.getSheetAt(0);//获得一个sheet

			Row firstrow = hs.createRow((short) 0);//第二行的标题
			for (int i = 0; i < names.length; i++) {
				firstcell[i] = firstrow.createCell((short) i);
				firstcell[i].setCellStyle(mapstyle.get("cell_header_title"));
				firstcell[i].setCellValue(names[i]);
				hs.setColumnWidth(i, width[i] * 256);
			}
			hs.createFreezePane(0, 1);//冻结行
			for (int i = 0; i < list.size(); i++) {
				firstrow = hs.createRow((short) i + 1);//下标为1的开始
				Object[] obj = list.get(i);
				for (int j = 0; j < names.length; j++) {    //循环excel里的列数
					firstcell[j] = firstrow.createCell((short) j);
					firstcell[j].setCellStyle(mapstyle.get("cell_data_default"));//字体样式
					firstcell[j].setCellType(Cell.CELL_TYPE_STRING);
					if (obj[j] == null || obj[j].equals("")) {
						firstcell[j].setCellValue(" ");
					} else {
						firstcell[j].setCellValue(obj[j].toString());
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
	/*******************************get and set***********************************/
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getTotalCells() {
		return totalCells;
	}
	public void setTotalCells(int totalCells) {
		this.totalCells = totalCells;
	}
	public int getFstrow() {
		return fstrow;
	}
	public void setFstrow(int fstrow) {
		this.fstrow = fstrow;
	}
	/*******************************get and set***********************************/


	//
	/**
	 *********************************************************.<br>
	 * [方法] read <br>
	 * [描述] 解析Excel,根据Excel文件的版本获得相应的实例 <br>
	 * [返回] List<ArrayList<String>> <br>
	 * [时间] 2016-1-6 下午3:45:04 <br>
	 *********************************************************.<br>
	 */
	public List<Map<String,Object>> readList(int startRow,MultipartFile file, String fileName, String[] fieldNames) throws Exception{
		FileInputStream fis=null;
		boolean isExcel2003 = true;
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>();
		Workbook wb=null;
		try {
			// 检查文件名是否为空或者是否是Excel格式的文件
			if (fileName == null || !fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
				return dataList;
			}
			// 对文件的合法性进行验
			if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
				isExcel2003 = false;
			}
			// 检查文件是否存在
			if (file == null) {
				return dataList;
			}
			 wb = isExcel2003 ? new HSSFWorkbook(file.getInputStream()) : new XSSFWorkbook(file.getInputStream());
			dataList=readMap(startRow,wb,fieldNames);
		} catch (Exception e) {
			throw e;
		}finally{
			if (fis!=null) {
				fis.close();
			}
			if (wb!=null) {
				wb.close();
			}
		}
		return dataList;
	}
	/**
	 *********************************************************.<br>
	 * [方法] readMap <br>
	 * [描述] 解析Excel,读取每一行的数据，封装成集合 <br>
	 * [返回] List<ArrayList<String>> <br>
	 * [时间] 2016-1-6 下午3:45:50 <br>
	 *********************************************************.<br>
	 */
	private List<Map<String,Object>> readMap(int startRow,Workbook wb,String[] fieldNames) throws Exception{
		List<Map<String,Object>> dataLst = new ArrayList<Map<String,Object>>();

		try {
			Sheet sheet = wb.getSheetAt(0);// 得到第一个shell
			this.totalRows = sheet.getPhysicalNumberOfRows();
			if (this.totalRows >= 1 && sheet.getRow(0) != null) {
				this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
			}
			System.out.println("最后一列:::::"+sheet.getRow(0).getLastCellNum());

			//数量超限
			if (this.totalRows>1000) {
				Map<String,Object> tmpMap=new LinkedHashMap<String,Object>();
				tmpMap.put("rCode", "-1");//超限
				dataLst.add(tmpMap);
				return dataLst;
			}
			Map<String,Object> tmpMap1=new LinkedHashMap<String,Object>();
			tmpMap1.put("rCode", "0");//超限
			dataLst.add(tmpMap1);
			//校验字段和Excel的列数量一致
			for (int r=startRow ; r < this.totalRows; r++) {// 循环Excel的行
				Row row = sheet.getRow(r);
				if (row == null) { continue;}
				Map<String,Object> tmpMap=new LinkedHashMap<String,Object>();
				for (short c = 0; c < fieldNames.length; c++) { // 循环Excel的列
					//如果第一列是空的则不算有效数据，略过
					Cell cell = row.getCell(c);
					String cellValue = "";
					if (c==0) {
						if (cell == null) {
							break;
						}
					}
					if (cell == null) {
						cellValue="";
					}else{
						if (Cell.CELL_TYPE_NUMERIC == cell.getCellType()) { // 处理数字型的,自动去零
							if (HSSFDateUtil.isCellDateFormatted(cell)) {// 在excel里,日期也是数字,在此要进行判断
								cellValue = get4yMdHms(cell.getDateCellValue());
							} else {
								DecimalFormat df = new DecimalFormat("0");
								cellValue = df.format(cell.getNumericCellValue());
							}
						} else if (Cell.CELL_TYPE_STRING == cell.getCellType()) {// 处理字符串型
							cellValue = cell.getStringCellValue();
						} else if (Cell.CELL_TYPE_BOOLEAN == cell.getCellType()) {// 处理布尔型
							cellValue = cell.getBooleanCellValue() + "";
						} else {// 其它数据类型
							cellValue = cell.toString();
						}
					}
					tmpMap.put(fieldNames[c], cellValue.trim());
				}
				if (tmpMap.size()>0) {
					dataLst.add(tmpMap);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return dataLst;
	}

	/**
	 ********************************************************* .<br>
	 * [方法] Excel <br>
	 * [描述] 导出Excel <br>
	 * [参数] 无参 <br>
	 * [返回] String <br>
	 * [时间] 2018年11月12日 上午11:05:37 <br>
	 ********************************************************* .<br>
	 */
	public void exportExcel(List<Map<String, Object>> list,ServletOutputStream out,
		HttpServletResponse response,String[] titles,String[] fildNames,String fileName) throws Exception {

		// 声明一个工作薄
		XSSFWorkbook workbook = new XSSFWorkbook();
		// 生成一个表格
		XSSFSheet sheet = workbook.createSheet(fileName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 产生表格标题行
		XSSFRow row = sheet.createRow(0);
		for (short i = 0; i < titles.length; i++) {
			XSSFCell cell = row.createCell(i);
			XSSFRichTextString text = new XSSFRichTextString(titles[i]);
			cell.setCellValue(text);
		}
		try {
			// 遍历集合数据，产生数据行
			Iterator<Map<String, Object>> it = list.iterator();
			int index = 0;
			while (it.hasNext()) {
				index++;
				row = sheet.createRow(index);
				Map<String, Object> tmp = it.next();
				for (int i = 0; i < fildNames.length; i++) {
					XSSFCell cell = row.createCell(i);

					Object value = tmp.get(fildNames[i]);

					// 判断值的类型后进行强制类型转换
					String textValue = null;
					// 其它数据类型都当作字符串简单处理
					if (value != null && value != "") {
						textValue = value.toString();
					}
					if (textValue != null) {
						XSSFRichTextString richString = new XSSFRichTextString(
								textValue);
						cell.setCellValue(richString);
					}
				}
			}
			getExportedFile(workbook, fileName, response);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
	public void getExportedFile(XSSFWorkbook workbook, String name,
								HttpServletResponse response) throws Exception {
		BufferedOutputStream fos = null;
		try {
			String fileName = name + ".xlsx";
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			fos = new BufferedOutputStream(response.getOutputStream());
			workbook.write(fos);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}


}

	