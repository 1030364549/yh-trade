package com.yh.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ExceptionLog {

	private static Logger loggerRequestI = LogManager.getLogger("resultPack");

	private static Logger loggerResponseI = LogManager.getLogger("responsePack");

	private static Logger loggerE = LogManager.getLogger("exceptionPack");

	private static String newLine = "\r\n";



	/**
	 *
	 ********************************************************* .<br>
	 * [方法] error <br>
	 * [描述] 记录异常通用错误 <br>
	 * [参数] exp <br>
	 * [返回] void <br>
	 * [时间] 2015-11-30 下午3:51:14 <br>
	 ********************************************************* .<br>
	 */
	public static void error(String text , String errorInfo) {
		StringBuffer msg = new StringBuffer();
		msg.append("[接口名称]:").append(text);
		msg.append(newLine);
		msg.append("[Exp]:").append(errorInfo);
		msg.append(newLine);
		loggerE.info(msg.toString());
	}



	/**
	 *
	 ********************************************************* .<br>
	 * [方法] resultInfo <br>
	 * [描述] 记录请求报文 <br>
	 * [参数] 类名   需要存储的数据 <br>
	 * [返回] void <br>
	 * [时间] 2015-11-30 下午3:51:14 <br>
	 ********************************************************* .<br>
	 */
	public static void resultInfo(String text ,String normal, int tag) {
		StringBuffer msg = new StringBuffer();
		msg.append("[接口名称]:").append(text);
		msg.append(newLine);
		msg.append(tag == 0 ? "【请求完整包】：" : "【响应完整包】：").append(normal);
		msg.append(newLine);
		loggerRequestI.info(msg.toString());
	}

}
