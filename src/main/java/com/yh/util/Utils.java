package com.yh.util;

import com.yh.config.PropertiesListenerConfig;
import com.yxhd.encrypt.EncryptUtils;
import net.coobird.thumbnailator.Thumbnails;
import net.sf.json.JSONArray;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.web.multipart.MultipartFile;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.security.Key;
import java.security.MessageDigest;
import java.security.spec.KeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 *********************************************************.<br>
 * [类] Utils <br>
 * [描述] 工具类 <br>
 * [参数] TODO(对参数的描述) <br>
 * [时间] 2017-12-07 下午19:19:19 <br>
 *********************************************************.<br>
 */
@SuppressWarnings("all")
public class Utils {

    public static boolean isEmpty(String str){
        return str==null ||str.isEmpty();
    }

    /**
     *
     *********************************************************.<br>
     * [方法] readCookieMap <br>
     * [描述] 获取Cookie信息 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public static Map<String,Cookie> readCookieMap(HttpServletRequest request){
        Map<String,Cookie> cookieMap=new HashMap<String, Cookie>();
        Cookie[] cookies=request.getCookies();
        if (null!=cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] MD5 <br>
     * [描述] MD5 加密 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public static String MD5(String str){
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] itInput=str.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest md=MessageDigest.getInstance("MD5");
            //使用指定的字节更新摘要
            md.update(itInput);
            //获取密文
            byte[] mdMd5=md.digest();
            //把密文转换成十六进制的字符串形式
            int j=mdMd5.length;
            char[] chr=new char[j*2];
            int k=0;
            for (int i = 0; i < j; i++) {
                byte bt=mdMd5[i];
                chr[k++] = hexDigits[bt >>> 4 & 0xf];
                chr[k++] = hexDigits[bt & 0xf];
            }
            return new String(chr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] addCookie <br>
     * [描述] 设置COOKIE <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public static void addCookie(HttpServletResponse response,String name,
                                 String value,int maxAge){
        Cookie cookie=new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge>0) {
            cookie.setMaxAge(maxAge);
        }
        response.addCookie(cookie);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] formateDate <br>
     * [描述] 格式化日期 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public static String formateDate(int style){
        String date="";
        String type="yyyyMMdd";//默认格式
        switch (style) {
            case 0:
                type="yyyy-MM-dd";
                break;
            case 1:
                type="yyyyMMddHHmmss";
                break;
            case 2:
                type="yyyy-MM-dd HH:mm:ss";
                break;
            case 3:
                type="HH:mm:ss";
                break;
            case 4:
                type="yyyyMMddHHmmssSSS";
                break;
            case 5:
                type="yyyy";
                break;
            case 6:
                type="yyyyMMdd";
                break;
        }
        SimpleDateFormat sdf=new SimpleDateFormat(type);
        date=sdf.format(new Date());
        return date;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] jsonArrayToList <br>
     * [描述] JSON集合转成List <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] List<Map<String,Object>> <br>
     * [时间] 2018年11月8日 下午5:09:55 <br>
     *********************************************************.<br>
     */
    public static List<Map<String,Object>> jsonArrayToList(JSONArray jsonArr){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Iterator<Object> it = jsonArr.iterator();
        while (it.hasNext()) {
            Map<String,Object> ob = (Map<String, Object>) it.next();
            list.add(ob);
        }
        return list;
    }

    /**
     *********************************************************.<br>
     * [方法] getWeight <br>
     * [描述] 随机生成秘钥字符串 <br>
     * [参数] 无参 <br>
     * [返回] String <br>
     * [时间] 2018年11月12日 下午2:49:46 <br>
     *********************************************************.<br>
     */
    public static String getWeight() {
        char[] str={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String weight="";
        for (int i = 0; i < 32; i++) {
            weight=weight+str[(int)(Math.random()*16)];
        }
        return weight;
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] bytesXOR <br>
     * [描述] 字节数组异或 <br>
     * [参数] 数据1、数据2 <br>
     * [返回] byte[] <br>
     * [时间] 2015-12-9 下午5:21:22 <br>
     ********************************************************* .<br>
     */
    public static byte[] bytesXOR(byte[] src, byte[] src1) throws Exception {
        int length = src.length;
        if (length != src1.length) {
            return null;
        }
        byte[] result = new byte[length];
        for (int i = 0; i < length; i++) {
            result[i] = byteXOR(src[i], src1[i]);
        }
        return result;
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] byteXOR <br>
     * [描述] 字节异或 <br>
     * [参数] 数据1、数据2 <br>
     * [返回] byte <br>
     * [时间] 2015-12-9 下午5:21:11 <br>
     ********************************************************* .<br>
     */
    private static byte byteXOR(byte src, byte src1) throws Exception {
        return (byte) ((src & 0xFF) ^ (src1 & 0xFF));
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] encrypt <br>
     * [描述] 3des加密 <br>
     * [参数] mab,key <br>
     * [返回] byte[] <br>
     * [时间] 2014-11-17 下午2:04:31 <br>
     ********************************************************* .<br>
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        byte[] newkey = new byte[24];
        System.arraycopy(key, 0, newkey, 0, key.length);
        System.arraycopy(key, 0, newkey, 16, 8);
        Key k = toKey(newkey); // ---这里卡了一下、百度一下可以优化吗
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] deciphering <br>
     * [描述] 3des解密 <br>
     * [参数] mab,key <br>
     * [返回] byte[] <br>
     * [时间] 2014-11-17 下午2:04:49 <br>
     ********************************************************* .<br>
     */
    public static byte[] deciphering(byte[] data, byte[] key) throws Exception {
        byte[] newkey = new byte[24];
        System.arraycopy(key, 0, newkey, 0, key.length);
        System.arraycopy(key, 0, newkey, 16, 8);
        Key k = toKey(newkey);
        Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, k);
        return cipher.doFinal(data);
    }

    private static SecretKey toKey(byte[] key) throws Exception {
        KeySpec dks = new DESedeKeySpec(key); // 这里改为了 DESedeKeySpec
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        return keyFactory.generateSecret(dks);
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] byte2HexStr <br>
     * [描述] bytes转换成十六进制字符串 <br>
     * [参数] byte数组 <br>
     * [返回] String <br>
     * [时间] 2015-12-7 下午5:44:10 <br>
     ********************************************************* .<br>
     */
    public static String byte2HexStr(byte[] b) throws Exception {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            hs = appendField(hs,(stmp.length() == 1) ? appendField("0", stmp): stmp);
        }
        return hs.toUpperCase();
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] hexStr2Bytes <br>
     * [描述] 十六进制字符串转换成bytes <br>
     * [参数] 16进制字符串 <br>
     * [返回] byte[] <br>
     * [时间] 2015-12-7 下午5:44:43 <br>
     ********************************************************* .<br>
     */
    public static byte[] hexStr2Bytes(String src) throws Exception {
        int m = 0, n = 0;
        int l = src.length() / 2;
        byte[] ret = new byte[l];
        for (int i = 0; i < l; i++) {
            m = i * 2 + 1;
            n = m + 1;
            ret[i] = uniteBytes(src.substring(i * 2, m), src.substring(m, n));
        }
        return ret;
    }

    private static byte uniteBytes(String src0, String src1) throws Exception {
        byte b0 = Byte.decode(appendField("0x", src0)).byteValue();
        b0 = (byte) (b0 << 4);
        byte b1 = Byte.decode(appendField("0x", src1)).byteValue();
        byte ret = (byte) (b0 | b1);
        return ret;
    }

    /**
     *
     ********************************************************* .<br>
     * [方法] appendField <br>
     * [描述] 拼接字符串 <br>
     * [参数] 参数1，参数2 <br>
     * [返回] String <br>
     * [时间] 2016-3-10 上午10:43:53 <br>
     ********************************************************* .<br>
     */
    public static String appendField(String one, String two) {
        return new StringBuffer(one).append(two).toString();
    }

    /**
     *********************************************************.<br>
     * [方法] writeTxt <br>
     * [描述] 写入txt文本 <br>
     * [参数] 目标地址、内容 <br>
     * [返回] void <br>
     * [时间] 2018年11月13日 上午11:20:28 <br>
     *********************************************************.<br>
     */
    public static void writeTxt(String filePath,String content) throws Exception {
        BufferedWriter out=null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath,true)));
            out.write(content+"\r\n");
        } catch (Exception e) {
            throw e;
        }finally{
            if(out!=null){
                out.close();
            }
        }
    }
    /**
     *********************************************************.<br>
     * [方法] delFile <br>
     * [描述] 删除文件 <br>
     * [参数] 目标地址、内容 <br>
     * [返回] void <br>
     * [时间] 2018年11月13日 上午11:20:28 <br>
     *********************************************************.<br>
     */
    public static boolean delFile(String path){
        boolean result=false;
        File file=new File(path);
        if(file.exists()&&file.isFile()){
            result=file.delete();
        }
        return result ;

    }

    /**
     * 输出信息
     * @param e
     * @return
     */
    public static String getAllErrorMessage(Exception e){
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            //将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }

        return sw.toString();
    }
    /**
     * @Author 野猪佩奇
     * @Description //图片压缩工具
     * @Date 9:44 2019/4/21
     * @Param [file, newfilepath, filepath, name]
     * @return java.util.Map<java.lang.String,java.lang.Object>
     **/
    public static   Map<String, Object> saveFileAndThumbnail(MultipartFile file, String newfilepath, String filepath, String name,String index,String jpg,String zhfilepath)  {
        System.out.println("进入图片压缩方法");
        // 判断文件是否为空
        if (!file.isEmpty()) {
            //String root = fileUploadPath;
            //String picturePath = "我是保存路径啊哈哈哈哈";
            //String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            //String newName = new Date().getTime()+ext;
            File filedict = new File(filepath);
            File filedict1 = new File(newfilepath);
            File filedict2 = new File(zhfilepath);//压缩文件
            if(!filedict.exists()){
                filedict.mkdirs();
            }
            if(!filedict1.exists()){
                filedict1.mkdirs();
            }
            if(!filedict2.exists()){
                filedict2.mkdirs();
            }
            File targetFile=new File(filepath+name+index);//保存原始的文件路径
            File newFile=new File(newfilepath+name+jpg);//压缩后的文件路径
            File zhfile=new File(zhfilepath+name+jpg);//转化格式后的文件路径
            try {
                file.transferTo(targetFile); //将文件存储
                // 1、先转换成jpg
                Thumbnails.of(targetFile).scale(1f).toFile(zhfile);
                // 2、进行压缩
                Thumbnails.of(zhfile)
                        .scale(1)//指定图片大小    0-1f  1f是原图  --写这个备注的是个笨蛋---yezhupeiqi
                        .outputQuality(0.4)//图片质量  0-1f  1f是原图
                        .toFile(newFile);
                BufferedImage bimg = ImageIO.read(zhfile);
                int width = bimg.getWidth();
                int height = bimg.getHeight();
                BufferedImage thumbnail = ImageIO.read(newFile);
                int thumbnailWidth = thumbnail.getWidth();
                int thumbnailHeight = thumbnail.getHeight();
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("pictureHeight",height);
                map.put("pictureWidth",width);
                map.put("picturePath",filepath);
                map.put("thumbnailHeight",thumbnailHeight);
                map.put("thumbnailWidth",thumbnailWidth);
                map.put("thumbnailPath",newfilepath);
                map.put("imgname",name);
                System.out.println(map.toString());
                return map;
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static boolean IsNull(Object str){
        if(null !=str&&!"".equals(str)&&!"null".equals(str)){
            return false;
        }
        return true;
    }


    public static  Map<String,String> getMapXml(String xml){
        Map<String,String> map=new HashMap<>();
        try {

            Document document = DocumentHelper.parseText(xml);
            Element bookStore = document.getRootElement();
            Iterator it = bookStore.elementIterator();
            while (it.hasNext()) {
                Element book = (Element) it.next();
                Iterator itt = book.elementIterator();
                while (itt.hasNext()) {
                    Element bookChild = (Element) itt.next();
                    String key=bookChild.getStringValue();
                    itt.hasNext();
                    bookChild = (Element) itt.next();
                    String value=bookChild.getStringValue();
                    map.put(key,value);
                }
            }
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return map;
    }


    /**
     * @Author 野猪佩奇
     * @Description //删除文件
     * @Date 17:44 2019/5/12
     * @Param [path]
     * @return boolean
     **/
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
			/*if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
				flag = true;
			}*/
        }
        return flag;
    }
    /**
     * @title 金额转换
     * @param money
     * @return
     */
    public static String dealWithMoney(String money){
        String dealmoney="";
        String lastmoney="";
        if (money.contains(".")) {
            lastmoney=money.substring(money.indexOf(".")+1);
        }
        for (int i = lastmoney.length(); i < 2; i++) {
            money+="0";
        }
        if (money.contains(".")) {
            money=money.substring(0,money.indexOf(".")+3);
        }
        money=money.replace(".","");
        String moneystr="000000000000"+money;
        dealmoney=moneystr.substring(moneystr.length()-12);
        return dealmoney;
    }
    public static void setdistribution_typeMap(Map<String,Object> map){
        String distribution_type=String.valueOf(map.get("distribution_type"));
        if("1".equals(distribution_type)||"2".equals(distribution_type)){
            map.put("mer_type","99");
        }else if("3".equals(distribution_type)||"4".equals(distribution_type)){
            map.put("mer_type","99");
            map.put("card_type","99");
        }
    }


    /**
     *
     *********************************************************.<br>
     * [方法] createRandomString <br>
     * [描述] 获取指定长度随机字符串 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2015-6-16 上午09:35:12 <br>
     *********************************************************.<br>
     */
    public static synchronized String createRandomString(int length) {
        //最后又重复两个0和1，因为需要凑足数组长度为64
        char[] ch = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        Random random = new Random();
        if (length > 0) {
            int index = 0;
            char[] temp = new char[length];
            int num = random.nextInt();
            for (int i = 0; i < length % 5; i++) {
                temp[index++] = ch[num & 15];
                num >>= 6;
            }
            for (int i = 0; i < length / 5; i++) {
                num = random.nextInt();
                for (int j = 0; j < 5; j++) {
                    temp[index++] = ch[num & 15];
                    num >>= 6;
                }
            }
            return new String(temp, 0, length);
        }else if (length == 0) {
            return "";
        }else {
            throw new IllegalArgumentException();
        }
    }

    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || "".equals(hexString)) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }


    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
    /**
     *@描述 下载excel模板共有
     *@参数
     *@返回值
     *@创建人  [WGG]
     *@创建时间 2019-06-13
     *@修改人和其它信息
     */
    public static void cnmonDownLoad(InputStream inputStream,String filename, HttpServletResponse response) throws Exception{
        BufferedInputStream inStrem = null;
        BufferedOutputStream outStream = null;
        try {
            response.reset();
            response.setCharacterEncoding("utf-8");
            // Content-disposition 告诉浏览器以下载的形式打开
            response.setHeader("Content-disposition", "attachment; filename=" + filename);
            // application/ms-excel;charset=utf-8 告诉浏览器下载的文件是excel
            response.setContentType("application/ms-excel");
            //os = new FileInputStream(inputStream);
            inStrem = new BufferedInputStream(inputStream);
            outStream = new BufferedOutputStream(response.getOutputStream());

            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = inStrem.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            outStream.flush();
        } catch (IOException e) {
            throw  e;
        } finally {
            if(outStream!=null){
                outStream.close();
            }
            if(inputStream !=null){
                inputStream.close();
            }
            if(inStrem!=null){
                inStrem.close();
            }
        }
    }

    /**
     *@描述 读取用户上传excel表格数据
     *@参数 MultipartFile 文件 String 临时保存本地路径
     *@返回值  List
     *@创建人  [WGG]
     *@创建时间 2019-06-13
     *@修改人和其它信息
     */
    public static List<String[]>  ReadExcel(MultipartFile mFile,String filePath) throws Exception{
        //读取Excel文件   判断文件格式
        String path = PropertiesListenerConfig.propertiesMap.get(filePath)+String.valueOf(System.currentTimeMillis())+mFile.getOriginalFilename();
        File file=new File(path);
        if (file.getParentFile() != null && !file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if(!file.exists()){
            file.createNewFile();
        }
        //File file = File.createTempFile("prefix", "_" + tuanfile.getOriginalFilename());
        mFile.transferTo(file);
        //读取文件信息
        List<String[]>  list=new ExcelUtil().readList(file,mFile.getOriginalFilename());
        //去除标题栏（商户白名单去除两级）
        if(!"invoiceFile".equals(filePath)) {
            if (list.size() > 0) {
                list.remove(0);
                list.remove(0);
            }
        }
        if(list.size()>0){list.remove(0);}
        if(file.exists()){
            file.delete();
        }
        return list;
    }

    /**
     *@描述  质检上传文件数据格式
     *@参数   List MultipartFile
     *@返回值  String 提示前台页面message
     *@创建人  [WGG]
     *@创建时间 2019-06-13
     *@修改人和其它信息
     */
    public static String checkFileData(List<String[]> list,MultipartFile fileM) throws Exception{
        boolean flag=fileM.getOriginalFilename().endsWith(".xlsx") || fileM.getOriginalFilename().endsWith(".xls");
        if(!flag){
            return "excel文件格式错误";
        }
        if(list.size()<1){
            return "导入失败，数据有误";
        }
        if(list.size()>500){
            return "导入失败，数据不超过500";
        }
        return "ok";
    }
    public static String hiddenCard(String cardnum){
        if(null!=cardnum && !"".equals(cardnum) && !"null".equals(cardnum)){
            cardnum = cardnum.substring(0,6)+"****"+cardnum.substring(cardnum.length()-4);
            return cardnum;
        }else{
            return  "";
        }
    }
    /**
     * @Author: dongjb
     * @Date: 2019/6/25 1:35
     * @Description:   对身份证号、银行卡号、手机号加密反Map
     * @Version: 1.0
     */
    public static Map encryption(String idcard, String bankcard,String phone){
        Map map=new HashMap();
        try {
            if("".equals(bankcard) || null == bankcard || "null".equals(bankcard)){
                bankcard="";
            }else{
                map.put("bankcardjm", EncryptUtils.getEncrypt(bankcard));
                bankcard=bankcard.substring(0,6)+"****"+bankcard.substring(bankcard.length()-4);
            }
            map.put("bankcard", bankcard);

            if("".equals(idcard) || null == idcard || "null".equals(idcard)){
                idcard="";
            }else{
                map.put("idcardjm",EncryptUtils.getEncrypt(idcard));
                idcard=idcard.substring(0,idcard.length()-8)+"****"+idcard.substring(idcard.length()-4);
            }
            map.put("idcard", idcard);

            if("".equals(phone) || null == phone || "null".equals(phone)){
                phone="";
            }else{
                map.put("phonejm",EncryptUtils.getEncrypt(idcard));
                phone = phone.substring(0,3)+"****"+phone.substring(phone.length()-4);
            }
            map.put("phone", phone);
        } catch (Exception e) {
            //账户信息加密异常
        }
        return map;
    }
}
