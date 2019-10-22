package com.yh.util;

import lombok.experimental.var;
import lombok.val;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class DateUtil {

    public static ThreadLocal<SimpleDateFormat> sdfDate=new ThreadLocal<>();
    public static ThreadLocal<SimpleDateFormat> sdfTime=new ThreadLocal<>();
    public static ThreadLocal<SimpleDateFormat> sdfDateTime=new ThreadLocal<>();
     
    //得到当前的时间
    public static Date getDate() {
        Calendar canlendar = Calendar.getInstance();
        return canlendar.getTime();
    }
     
    //提到指定的millis得到时间
    public static Date getDate(long millis) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(millis);
        return canlendar.getTime();
    }
 
    public static long getMillis() {
        return Calendar.getInstance().getTimeInMillis();
    }
 
    //得到指定日期的字符串(yyyy-MM-dd HH:mm:ss.SSS)
    public static String getDateFormate(Date date, String formate) {
        try {
            SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
            return simpleDateFormate.format(date);
        } catch (Exception e) {
        }
        return "";
    }
 
    //根据日期得到YYYY-MM-DD HH:MM:SS.SSS格式字符串
    public static String get4yMdHmsS(Date date) {
        return DateUtil.getDateFormate(date, "yyyy-MM-dd HH:mm:ss.SSS");
    }
 
    //根据日期得到YYYY-MM-DD HH:MM:SS格式字符串
    public static String get4yMdHms(Date date) {
        return DateUtil.getDateFormate(date, "yyyy-MM-dd HH:mm:ss");
    }
 
    //根据日期得到YYYY-MM-DD HH:MM格式字符串
    public static String get4yMdHm(Date date) {
        return DateUtil.getDateFormate(date, "yyyy-MM-dd HH:mm");
    }
 
    //根据日期得到YYYY-MM-DD格式字符串
    public static String get4yMd(Date date) {
        return DateUtil.getDateFormate(date, "yyyy-MM-dd");
    }
    
    //根据日期得到HH:MM:SS格式字符串
    public static String get4Hms(Date date) {
        return DateUtil.getDateFormate(date, "HH:mm:ss");
    }
     
    //根据日期得到YYYYMMDD格式字符串
    public static String get4yMdaz(Date date) {
        return DateUtil.getDateFormate(date, "yyyyMMdd");
    }
    
    //根据日期得到YYMMDD格式字符串
    public static String get4yMdad(Date date) {
        return DateUtil.getDateFormate(date, "yyMMdd");
    }
    
    public static String get4yMdhmsaz(Date date) {
        return DateUtil.getDateFormate(date, "yyyyMMdd HH:mm:ss");
    }
    
    //把指定字符(yyyy-MM-dd HH:mm:ss.SSS)串转成Date
    public static Date parse4yMdHmsS(String sDate) {
        return DateUtil.parseDate(sDate, "yyyy-MM-dd HH:mm:ss.SSS");
    }
 
    //把指定字符(yyyy-MM-dd HH:mm:ss)串转成Date
    public static Date parse4yMdHms(String sDate) {
        return DateUtil.parseDate(sDate, "yyyy-MM-dd HH:mm:ss");
    }
 
    //把指定字符(yyyy-MM-dd HH:mm)串转成Date
    public static Date parse4yMdHm(String sDate) {
        return DateUtil.parseDate(sDate, "yyyy-MM-dd HH:mm");
    }
 
    //把指定字符(yyyy-MM-dd)串转成Date
    public static Date parse4yMd(String sDate) {
        return DateUtil.parseDate(sDate, "yyyy-MM-dd");
    }
    
    //根据指定格式,把字符串转成日期
    public static Date parseDate(String sDate, String formate) {
        SimpleDateFormat simpleDateFormate = new SimpleDateFormat(formate);
        try {
            return simpleDateFormate.parse(sDate);
        } catch (ParseException e) {
            return null;
        }
    }
 
    //两个长整型的时间相差(时间的毫秒数),可以得到指定的毫秒数,秒数,分钟数,天数
    public static double getDifTwoTime(Date minuendTime, Date subtrahendTime, String tdatestr) {
        if (minuendTime == null || subtrahendTime != null) {
            return DateUtil.getDifTwoTime(minuendTime.getTime(), subtrahendTime.getTime(), tdatestr);
        }
        return 0;
    }
 
    //两个长整型的时间相差(时间的毫秒数),可以得到指定的毫秒数,秒数,分钟数,天数
    public static double getDifTwoTime(long minuendTime, long subtrahendTime, String tdatestr) {
        if (tdatestr == null || tdatestr.equals("")) {
            tdatestr = "MS";
        }
        double temp = 1;
        /** 毫秒数 */
        if ("MS".equalsIgnoreCase(tdatestr)) {
            temp = 1;
        }
        /** 得到秒 */
        if ("S".equalsIgnoreCase(tdatestr)) {
            temp = 1000;
        }
        /** 得到分 */
        if ("M".equalsIgnoreCase(tdatestr)) {
            temp = 1000 * 60;
        }
        /** 得到小时 */
        if ("H".equalsIgnoreCase(tdatestr)) {
            temp = 1000 * 60 * 60;
        }
        /** 得到天 */
        if ("D".equalsIgnoreCase(tdatestr)) {
            temp = 1000 * 60 * 60 * 24;
        }
        return (minuendTime - subtrahendTime) / temp;
    }
 
    //从日期中得到指定部分(YYYY/MM/DD/HH/SS/SSS)数字
    public static int getPartOfTime(Date date, String part) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTime(date);
        if (part.equalsIgnoreCase("Y")) {//得到年
            return canlendar.get(Calendar.YEAR);
        }
        if (part.equalsIgnoreCase("M")) {//得到月
            return canlendar.get(Calendar.MONTH) + 1;
        }
        if (part.equalsIgnoreCase("D")) {//得到日
            return canlendar.get(Calendar.DAY_OF_MONTH);
        }
        if (part.equalsIgnoreCase("H")) {//得到时
            return canlendar.get(Calendar.HOUR_OF_DAY);
        }
        if (part.equalsIgnoreCase("M")) {//得到分
            return canlendar.get(Calendar.MINUTE);
        }
        if (part.equalsIgnoreCase("S")) {//得到秒
            return canlendar.get(Calendar.SECOND);
        }
        if (part.equalsIgnoreCase("MS")) {//得到毫秒
            return canlendar.get(Calendar.MILLISECOND);
        }
        return -1;
    }
    
    
    /**
     * 
     *********************************************************.<br>
     * [方法] getYestertodayMMDD <br>
     * [描述] 获取昨天日期  MMdd格式 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2016-4-19 上午10:33:00 <br>
     *********************************************************.<br>
     */
    public static String getYestertodayMMdd(){
 	   Calendar calendar = Calendar.getInstance();
 	   calendar.setTime(new Date());
 	   calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH)-1);
 	   return getDateFormate(calendar.getTime(),"MMdd");
    }
    
    
    
    /**
     * ********************************************************
     * @Description: 小数的相加
     * @param
     * @return String
     * @date 2014-11-1 下午05:31:59 
     ********************************************************
     */
	public static Double adddouble(Double double1 ,Double double2){
		BigDecimal b1 = new BigDecimal(Double.toString(double1.doubleValue()));  
        BigDecimal b2 = new BigDecimal(Double.toString(double2.doubleValue()));  
		return  b1.add(b2).doubleValue();
	}
	
	/**
     * ********************************************************
     * @Description: 小数的相减
     * @param
     * @return String
     * @date 2014-11-1 下午05:31:59
     ********************************************************
     */
	public static Double reducedouble(Double double1 ,Double double2){
		BigDecimal b1 = new BigDecimal(Double.toString(double1.doubleValue()));  
        BigDecimal b2 = new BigDecimal(Double.toString(double2.doubleValue()));  
		return  b1.subtract(b2).doubleValue();
	}

	public static Double bltwo(Object object){
		DecimalFormat    df   = new DecimalFormat("######0.00"); 
		return  new Double(df.format(object));
	}
    /**
     * @Author 野猪佩奇
     * @Description //小数相除
     * @Date 10:35 2019/5/23
     * @Param [double1, double2]
     * @return java.lang.Double
     **/
    public static Double divide(Double double1 ,Double double2){
        BigDecimal b1 = new BigDecimal(Double.toString(double1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(double2.doubleValue()));
        BigDecimal divide = b1.divide(b2, 4, RoundingMode.HALF_UP);
        return  divide.doubleValue();
    }
    /**
     * @Author 野猪佩奇
     * @Description //panduan
     * @Date 11:20 2019/5/23
     * @Param [str]
     * @return boolean
     **/
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        if(str.indexOf(".")>0){//判断是否有小数点
            if(str.indexOf(".")==str.lastIndexOf(".") && str.split("\\.").length==2){ //判断是否只有一个小数点
                return pattern.matcher(str.replace(".","")).matches();
            }else {
                return false;
            }
        }else {
            return pattern.matcher(str).matches();
        }
    }
    public static String getserial(Date date) {
        return DateUtil.getDateFormate(date, "yyyyMMddHHmmssSSS");
    }

    public static Integer getRandom(){
        return 10000000+new Random().nextInt(90000000);
    }

    public static Double dividezs(Double double1 ,Double double2){
        BigDecimal b1 = new BigDecimal(Double.toString(double1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(double2.doubleValue()));
        BigDecimal decimal = b1.divideToIntegralValue(b2);
        return  decimal.doubleValue();
    }

    public static void main(String[] args){
        String index13="P*0.0069";
        System.out.println(index13.substring(3,index13.length()));
    }

    public static double compare(double a, double b) {
        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        double result=0.0 ;
        if (data1.compareTo(data2) < 0) {
            result = data2.doubleValue();
        }
        if (data1.compareTo(data2) == 0) {
            result = data1.doubleValue();
        }
        if (data1.compareTo(data1) > 0) {
            result = data1.doubleValue();
        }
        return result;
    }

    public static boolean cardCodeVerify(String cardcode) {
        int i = 0;
        String r = "error";
        String lastnumber = "";

        i += Integer.parseInt(cardcode.substring(0, 1)) * 7;
        i += Integer.parseInt(cardcode.substring(1, 2)) * 9;
        i += Integer.parseInt(cardcode.substring(2, 3)) * 10;
        i += Integer.parseInt(cardcode.substring(3, 4)) * 5;
        i += Integer.parseInt(cardcode.substring(4, 5)) * 8;
        i += Integer.parseInt(cardcode.substring(5, 6)) * 4;
        i += Integer.parseInt(cardcode.substring(6, 7)) * 2;
        i += Integer.parseInt(cardcode.substring(7, 8)) * 1;
        i += Integer.parseInt(cardcode.substring(8, 9)) * 6;
        i += Integer.parseInt(cardcode.substring(9, 10)) * 3;
        i += Integer.parseInt(cardcode.substring(10,11)) * 7;
        i += Integer.parseInt(cardcode.substring(11,12)) * 9;
        i += Integer.parseInt(cardcode.substring(12,13)) * 10;
        i += Integer.parseInt(cardcode.substring(13,14)) * 5;
        i += Integer.parseInt(cardcode.substring(14,15)) * 8;
        i += Integer.parseInt(cardcode.substring(15,16)) * 4;
        i += Integer.parseInt(cardcode.substring(16,17)) * 2;
        i = i % 11;
        lastnumber =cardcode.substring(17,18);
        if (i == 0) {
            r = "1";
        }
        if (i == 1) {
            r = "0";
        }
        if (i == 2) {
            r = "x";
        }
        if (i == 3) {
            r = "9";
        }
        if (i == 4) {
            r = "8";
        }
        if (i == 5) {
            r = "7";
        }
        if (i == 6) {
            r = "6";
        }
        if (i == 7) {
            r = "5";
        }
        if (i == 8) {
            r = "4";
        }
        if (i == 9) {
            r = "3";
        }
        if (i == 10) {
            r = "2";
        }
        if (r.equals(lastnumber.toLowerCase())) {
            return true;
        }
        return false;
    }

    public static boolean cardCodeVerifySimple(String cardcode) {
        //第一代身份证正则表达式(15位)
        String isIDCard1 = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
        //第二代身份证正则表达式(18位)
        String isIDCard2 ="^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])((\\d{4})|\\d{3}[A-Z])$";

        //验证身份证
        if (cardcode.matches(isIDCard1) || cardcode.matches(isIDCard2)) {
            return true;
        }
        return false;
    }

     /*
    校验过程：
    1、从卡号最后一位数字开始，逆向将奇数位(1、3、5等等)相加。
    2、从卡号最后一位数字开始，逆向将偶数位数字，先乘以2（如果乘积为两位数，将个位十位数字相加，即将其减去9），再求和。
    3、将奇数位总和加上偶数位总和，结果应该可以被10整除。
    */
    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String bankCard) {
        if(bankCard.length() < 15 || bankCard.length() > 19) {
            return false;
        }
        char bit = getBankCardCheckCode(bankCard.substring(0, bankCard.length() - 1));
        if(bit == 'N'){
            return false;
        }
        return bankCard.charAt(bankCard.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     * @param nonCheckCodeBankCard
     * @return
     */
    public static char getBankCardCheckCode(String nonCheckCodeBankCard){
        if(nonCheckCodeBankCard == null || nonCheckCodeBankCard.trim().length() == 0
                || !nonCheckCodeBankCard.matches("\\d+")) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeBankCard.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    public static boolean isValidDate(String str) {
        boolean convertSuccess=true;
       // 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
       // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.setLenient(false);
            format.parse(str);
        } catch (ParseException e) {
            // e.printStackTrace();
       // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            convertSuccess=false;
        }
        return convertSuccess;
    }

    public static Double xc(Double double1 ,Double double2){
        BigDecimal b1 = new BigDecimal(Double.toString(double1.doubleValue()));
        BigDecimal b2 = new BigDecimal(Double.toString(double2.doubleValue()));
        BigDecimal decimal = b1.multiply(b2);
        return  decimal.doubleValue();
    }


    public static String formatDate(){
        return formatDate(new Date());
    }
    public static String formatDate(Date date){
        var sdf= sdfDate.get();
        if (sdf==null){
            sdf=new SimpleDateFormat("yyyy-MM-dd");
            sdfDate.set(sdf);
        }
        return sdf.format(date);
    }

    public static String formatTime(){
        return formatTime(new Date());
    }
    public static String formatTime(Date date){
        var sdf= sdfTime.get();
        if (sdf==null){
            sdf=new SimpleDateFormat("HH:mm:ss");
            sdfTime.set(sdf);
        }
        return sdf.format(date);
    }

    public static String formatDateTime(){
        return formatDate(new Date());
    }
    public static String formatDateTime(Date date){
        var sdf= sdfDateTime.get();
        if (sdf==null){
            sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdfDateTime.set(sdf);
        }
        return sdf.format(date);
    }

    //导出excel
    public static void exporrtExcel(List<String[]> list, String[] names, int[] widths, HttpServletResponse response) throws Exception{
        OutputStream outStream=null;
        Workbook book=null;
        try {
            ClassPathResource resource = new ClassPathResource("public/excelmodel/exporttuanguanxi.xlsx");
            InputStream inputStream = resource.getInputStream();
             book= ExcelUtil.outputExcelFile(list,inputStream,names,widths);
            outStream = response.getOutputStream();
            response.reset();
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            response.setHeader("Content-Disposition", "attachment;filename=" +"exporttuanguanxi.xlsx");
            book.write(outStream);
            outStream.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            if(null!=outStream){
                outStream.close();
            }
            if(null!=book){
                book.close();
            }
        }
    }
}
