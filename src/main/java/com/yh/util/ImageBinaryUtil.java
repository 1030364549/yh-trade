package com.yh.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;
/**
 * 二进制和图片互相转换工具类
 */
public class ImageBinaryUtil {
    private static Logger log = LoggerFactory.getLogger(ImageBinaryUtil.class);
    public static BASE64Encoder encoder = new BASE64Encoder();
    /**
     * 照片转换为二进制流
     * @param file
     * @return
     */
    public static String getImageBinary(File file){
        BufferedImage bi;
        try {
            bi = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bi, "jpg", baos);
            byte[] bytes = baos.toByteArray();
            return encoder.encodeBuffer(bytes).trim();
        } catch (IOException e) {
            log.info("照片转换为二进制流异常");
            e.printStackTrace();
        }
        return null;
    }


}