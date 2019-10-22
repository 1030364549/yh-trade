package com.yh.util;

import com.umpay.util.HttpUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class CommonTest {
    private static String uri ="http://10.10.178.151:37999/smsParserService/downsms";
    private static String charset="UTF-8";
    private static String P2PSMSNOTIFY = "{orderDate=02月21日, merId=7001151, calling=15110035397, rpid=20190225SMS00000000, reqDate=20190221, serviceType=01, origAmt=203994, reqamount=, productId=P15420L1, amount=2039.94, balance=00000000000000, retCode=0000, mobileId=18017503588, merName=我来贷, intrType=, enterQTime=1550739543930, reqTime=165903, funCode=P2PSMSNOTIFY, ISNOTIFY=true, requestIp=10.210.1.27, SUBLONGCODE=687, bProductId=B200200H, comAmt=, orderId=UMPAY1902211659020434409491}";
    private static String PUSHSMS = "{rpid=20190225SMS00000001, intrType=FACTOR_COMMON, resModeFlag=DOWNSMS, funCode=PUSHSMS, reqDate=20190225, reqTime=160822, calling=13311131581, ct=尊敬的客户，您本次操作的短信验证码为195094，5分钟内有效。工作人员不会向您索取验证码，请勿泄露。【联动优势】, ISNOTIFY=true}";
    private static String TEST_NOTIFY = "{rpid=20190225SMS00000001, intrType=FACTOR_COMMON, resModeFlag=DOWNSMS, funCode=PUSHSMS, reqDate=20190225, reqTime=160822, calling=15110035397, ct=尊敬的客户，您本次操作的短信验证码为195094，5分钟内有效。工作人员不会向您索取验证码，请勿泄露。【联动优势】, ISTEST=false, notifyUrl=http://baidu.com}";
    private static String TEST_TEST = "{ISTEST=false, rpid=20190225SMS00000001, intrType=FACTOR_COMMON, resModeFlag=DOWNSMS, funCode=PUSHSMS, reqDate=20190225, reqTime=160822, calling=15110035397, ct=尊敬的客户，您本次操作的短信验证码为195094，5分钟内有效。工作人员不会向您索取验证码，请勿泄露。【联动优势】}";

    private static String DO_NOT_RESEND = "{skipResend=true, rpid=20190225SMS00000001, intrType=FACTOR_COMMON, resModeFlag=DOWNSMS, funCode=PUSHSMS, reqDate=20190225, reqTime=160822, calling=15110035397, ct=尊敬的客户，您本次操作的短信验证码为195094，5分钟内有效。工作人员不会向您索取验证码，请勿泄露。【联动优势】, ISNOTIFY=true}";
    private static String RPID_DO_NOT_RETURN = "{rpid=20190225SMS00000001, intrType=FACTOR_COMMON, resModeFlag=DOWNSMS, funCode=PUSHSMS, reqDate=20190225, reqTime=160822, calling=15110035397, ISNOTIFY=true}";
    private static String TEMP_CONTENT_NULL = "{merName=, orderDate=02月21日, merId=7001151, calling=15110035397, rpid=20190225SMS00000000, reqDate=20190221, serviceType=01, origAmt=203994, reqamount=, productId=P15420L1, amount=2039.94, balance=00000000000000, retCode=0000, mobileId=18017503588, intrType=, enterQTime=1550739543930, reqTime=165903, funCode=P2PSMSNOTIFY, ISNOTIFY=true, requestIp=10.210.1.27, SUBLONGCODE=687, bProductId=B200200H, comAmt=, orderId=UMPAY1902211659020434409491}";

    private static String TEST_10659021 = "{outAccountName=, verifyCode=, calling=18514462013, errorRow=0, rpid=UPLM18829803, purpose=null, reqDate=20190606, succRow=4, amt=, mobileId=18773156340, retCode=0000, batchNo=1906060004, intrType=BJ_BTPAY_TZSMS_03, enterQTime=1559802000405, funCode=PUSHSMS, reqTime=142000, ISNOTIFY=true, requestIp=172.16.30.90, called=10658008, inAcctNo=, orderId=}";

    @Test
    public void testE() throws IOException {
        Map<String, Object> maps = convertString(TEST_10659021);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
    }

    /**
     * 正常下发短信
     * @throws IOException
     */
    @Test
    public void testNormal() throws IOException {


     Map<String, Object> maps = convertString(PUSHSMS);

        System.out.println(maps);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);


        System.out.println(retMap);


        assert retMap.get("retCode").equals("0000");
    }

    /**
     * 测试恒通易信通知
     * @throws IOException
     */
    @Test
    public void testTest() throws IOException {
        Map<String, Object> maps = convertString(TEST_TEST);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
    }

    /**
     * 测试恒通易信通知
     * @throws IOException
     */
    @Test
    public void testNotify() throws IOException {
        Map<String, Object> maps = convertString(TEST_NOTIFY);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
    }

    /**
     * 测试短信通道失败自动切换通道，需要将basenum设置成2
     * @throws IOException
     */
    @Test
    public void testChanlAutoChange() throws IOException {
        Map<String, Object> maps = convertString(P2PSMSNOTIFY);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
        maps.put("rpid", "20190225SMS00000001");
        Map<String, Object> retMap1 = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap1);
        assert retMap1.get("retCode").equals("0000");
        maps.put("rpid", "20190225SMS00000002");
        Map<String, Object> retMap2 = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap2);
        assert retMap2.get("retCode").equals("0000");
    }

    /**
     * 测试失败短信rpid不返回
     * @throws IOException
     */
    @Test
    public void testRpidNotReturn() throws IOException {
        Map<String, Object> maps = convertString(RPID_DO_NOT_RETURN);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("00040050");
    }

    /**
     * 测试不重发短信
     * @throws IOException
     */
    @Test
    public void testDonotResend() throws IOException {
        Map<String, Object> maps = convertString(DO_NOT_RESEND);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
    }

    /**
     * 测试模板内容传空报错问题
     * @throws IOException
     */
    @Test
    public void testTempContentNull() throws IOException {
        Map<String, Object> maps = convertString(TEMP_CONTENT_NULL);
        Map<String, Object> retMap = HttpUtil.httpPostXStream2XStream(uri, maps, charset);
        System.out.println(retMap);
        assert retMap.get("retCode").equals("0000");
    }

    public static Map<String, Object> convertString(String a) {
        a =  a.substring(1, a.length()-1);
        Map<String, Object> docType = new HashMap<>();
        StringTokenizer items;
        for(StringTokenizer entrys = new StringTokenizer(a, ", ");entrys.hasMoreTokens();
            docType.put(items.nextToken(), items.hasMoreTokens() ? String.valueOf(items.nextToken()) : "")){
            items = new StringTokenizer(entrys.nextToken(), "=");
        }
        return docType;
    }



}
