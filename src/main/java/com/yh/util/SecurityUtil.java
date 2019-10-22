package com.yh.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * *********************************
 * 安全加密工具类
 * *********************************
 */
public class SecurityUtil {

    /**
     * SHA1算法
     * @param param
     * @return
     * @throws Exception
     */
    public static String sha1Encode(String param) throws Exception{
        try {
            if (StringUtils.isEmpty(param)){
                return null;
            }else{
                return DigestUtils.sha1Hex(param);
            }
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String args[]) throws Exception {
        try {

            String str = new String("123456");
            System.out.println("原始：" + str);
            System.out.println("SHA后：" + sha1Encode(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
