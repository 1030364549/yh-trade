package com.yh.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.google.code.kaptcha.Constants;

/**
 * ************************************
 * 验证码工具类
 * @param <T>
 * @param <PK>
 * ************************************
 */
@SuppressWarnings("all")
public class RandomCodeUtil {
	
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] instance <br>
	 * [描述] 创建静态类<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] String <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public static RandomCodeUtil instance(){
		return new RandomCodeUtil();
	}
	
	public BufferedImage initImage(HttpServletRequest request){
		//设置大小
		int width=90;
		int height=35;
		//在内存中创建图像
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//获取图形上下文
		Graphics g=image.getGraphics();
		//生产随机类
		Random random=new Random();
		//设置背景色
		g.setColor(getRandomColor(210, 260));
		g.fillRect(0, 0, width, 100);
		//设置字体
		g.setFont(new Font("楷体",Font.BOLD,30));
		//随机产生100条干扰线，使图像中的验证码不易被探测到
		g.setColor(getRandomColor(160, 200));
		for (int i = 0; i < 150; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int x1 = random.nextInt(12);
			int y1 = random.nextInt(12);
			g.drawLine(x, y, x+x1, y+y1);
		}
		
		//获取随机产生的验证码
		String randCode="";
		for (int i = 0; i < 4; i++) {
			String rand=getRandomChar();
			randCode=randCode.concat(rand);
			//将验证码显示到图像中
			g.setColor(new Color(20+random.nextInt(110), 20+random.nextInt(110), 20+random.nextInt(110)));
			g.drawString(rand, 13*i+20, 28);
		}
		g.dispose();
		//保存验证码
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, randCode);
		
		return image;
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getRandomColor <br>
	 * [描述] 给定范围获取随机颜色<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Color <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public Color getRandomColor(int fc,int bc){
		Random random=new Random();
		if (fc>255) fc=255;
		if (bc>255) bc=255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r,g,b);
	}
	/**
	 * 
	 *********************************************************.<br>
	 * [方法] getRandomChar <br>
	 * [描述] 获取随机数字或字符<br>
	 * [参数] TODO(对参数的描述) <br>
	 * [返回] Color <br>
	 * [时间] 2017-12-07 下午19:19:19 <br>
	 * [作者] lvl
	 *********************************************************.<br>
	 */
	public static String getRandomChar(){
		String randChar="";
		int index=(int) Math.round(Math.random()*2);
		switch(index){
			case 0:
				//大写字符
				randChar=String.valueOf((char)Math.round(Math.random() * 25 + 65));
				break;
			case 1:
				//小写字符
				randChar=String.valueOf((char)Math.round(Math.random() * 25 + 97));
				break;
			default:
				//数字
				randChar=String.valueOf(Math.round(Math.random() * 9));
				break;
		}
		return randChar;
	}
	
}
