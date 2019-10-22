package com.yh.ocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.umpay.util.Base64;

public class OCR {
	public static Map<String, Object> httpPostOcr(final String url, File file, final String pictureType) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		    FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			byte[] data;
			data = new byte[fis.available()];
			fis.read(data);
			String base64file = Base64.encode(data);
			String params = "filedata="+ URLEncoder.encode(base64file, "utf-8");
			params += "&pid=" + URLEncoder.encode(pictureType, "utf-8");
			String readByGet = readByPOST(url, params);
			if ("error".equals(readByGet)) {
				resultMap.put("retCode", "0002");
				resultMap.put("retMsg", "the url can not access to");
			}else {
				Map maps = (Map)JSONObject.parse(readByGet);
				resultMap.put("retCode", "0000");
				resultMap.put("data", maps);
				resultMap.put("retMsg", "success");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			resultMap.put("retCode", "0003");
			resultMap.put("retMsg", "picture can not find");
		} catch (IOException e) {
			e.printStackTrace();
			resultMap.put("retCode", "0004");
			resultMap.put("retMsg", "IOException");
		}

		return resultMap;
	}

	private static String readByPOST(String inUrl, String params) throws IOException{
		StringBuffer sbf = new StringBuffer();
		String strRead = null;
		boolean accessTo = false;

		// 1. 得到访问地址的URL
		URL url = new URL(inUrl);

		// 2. 得到网络访问对象java.net.HttpURLConnection
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		// 3. 设置请求参数（过期时间，输入、输出流、访问方式），以流的形式进行连接
		// 设定请求的方法为"POST"，默认是GET
		connection.setRequestMethod("POST");
		// 设置是否从httpUrlConnection读入
		connection.setDoInput(true);
		// 设置是否向HttpURLConnection输出
		connection.setDoOutput(true);
		// 设置超时时间,单位：毫秒
		connection.setConnectTimeout(5000);
		// 连接
		connection.connect();
		// 设置请求参数设置请求参数
		PrintWriter out = new PrintWriter(connection.getOutputStream());
		out.print(params);
		out.flush();

		// 4. 得到响应状态码的返回值 responseCode
		int code = connection.getResponseCode();
		if (code == HttpURLConnection.HTTP_OK) {
			accessTo = true;
			
			InputStream is = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,
					"UTF-8"));
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			
			reader.close();
			is.close();
		}
		
		out.close();
		connection.disconnect();
		if (accessTo) {
			return sbf.toString();
		}else {
			return "error";
		}
	}
	public static void main(String[] args) {
		File file = new File("C:\\Users\\luran\\Desktop\\图片\\银行卡.jpg");
		Map<String, Object> resultMap = httpPostOcr("http://10.10.55.124:8180/OcrWeb/servlet/OcrServlet", file, "4");
		Map maps = (Map) JSON.parse(resultMap.get("data").toString());
		//String date = maps.get("ExpiryDate").toString();
		//date=date.substring(date.length()-8,date.length());
		System.out.println(resultMap);
	}
}
