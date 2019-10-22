package com.yh.service.impl;

import com.yh.dao.MerchantDao;
import com.yh.entity.Merchant;
import com.yh.entity.Page;
import com.yh.fastdfs.FastDFSClient;
import com.yh.service.MerchantService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@SuppressWarnings("all")
@Service
public class MerchantServiceImpl extends BaseServiceImpl<Merchant, Integer>
		implements MerchantService {
    @Resource
    private MerchantDao merchantDao;

	/**
	 *
	 *********************************************************.<br>
	 * [方法] getMerchantList <br>
	 * [描述] 商户查询 <br>
	 * [参数] [Page](对参数的描述) <br>
	 * [返回] <br>
	 * [日期] 2019/7/8
	 * [时间] 11:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@Override
	public void getMerchantList(Page page) throws Exception {
		merchantDao.getPageList("getMerchantList", page);
	}

	/**
	 *
	 *********************************************************.<br>
	 * [方法] change <br>
	 * [描述] 变更商户状态 <br>
	 * [参数] [Map map](对参数的描述) <br>
	 * [返回] int <br>
	 * [日期] 2019/7/8
	 * [时间] 14:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@Override
	public int change(Map map) throws Exception{
		return merchantDao.update("change",map);
	}


	/**
	 *
	 *********************************************************.<br>
	 * [方法] getSettlement <br>
	 * [描述] 商户结算信息查询 <br>
	 * [参数] [String id](对参数的描述) <br>
	 * [返回] String <br>
	 * [日期] 2019/7/8
	 * [时间] 17:50
	 * [作者] mh
	 *********************************************************.<br>
	 */
	@Override
	public Map getSettlement(Map marchant) throws Exception{
		return (Map) merchantDao.selectOne("getSettlement", marchant);
	}

	/**
	 * 获取商户工商信息
	 * @param marchant map
	 * @return Map
	 * @throws Exception e
	 */
	@Override
	public Map<String, Object> getMerchantSchedule(Map marchant) throws Exception {
		return (Map) merchantDao.selectOne("getMerchantSchedule", marchant);
	}

	/**
	 * 获取商户图片地址信息
	 * @param marchant Map
	 * @return
	 * @throws Exception
	 */
	@Override
	public void downloadImg(Map marchant, HttpServletResponse response) throws Exception {
		Map clientImg = (Map) merchantDao.selectOne("getImgAddress", marchant);
		String fileId = String.valueOf(clientImg.get("FILEID"));
//		System.out.println(AutoLoadData.imgurl);
//				String imgUrl = PropertiesListenerConfig.propertiesMap.get("imgurl").toString() +);
		//导出文件名称
		String fileName = String.valueOf(marchant.get("merno")) + ".zip";
		//设置响应
		response.setHeader("content-type", "text/plain");
		response.setHeader("content-type", "application/x-msdownload;");
		response.setContentType("text/plain; charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
		ServletOutputStream out = null;
		try{
			byte[] data = FastDFSClient.getFileByte(fileId);
			//获取输出流
			out = response.getOutputStream();
			if (data != null) {
				out.write(data);
			}
			out.flush();
		}catch (Exception e){
			throw e;
		}finally {
			//关流
			if (out != null){
				out.close();
			}
		}
	}


}
