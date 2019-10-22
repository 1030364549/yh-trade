package com.yh.cache;

import com.yh.config.SpringUtil;
import com.yh.controller.BaseController;
import com.yh.entity.AdminInfo;
import com.yh.entity.PropertiesData;
import com.yh.service.impl.AdminInfoServiceImpl;
import com.yh.service.impl.BackCarteServiceImpl;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ************************************
 * 缓存操作
 * @param
 * @param
 * ************************************
 */
@SuppressWarnings("all")
public class DataCache  {

    private static CacheHelp cache= RedisCached.getInstance();
    private static final String sqlSpaceName="Cache";
    protected static DataCache dataCache=null;



    /**
     * ************************************
     * 获取cache整体对像
     * @param
     * @param
     * ************************************
     */
    public static CacheHelp getCacheObj(){
        return cache;
    }
    /**
     * ************************************
     * 获取该类唯一实例
     * @param
     * @param
     * ************************************
     */
    public static DataCache getInstance(){
        if (dataCache==null) {
            dataCache=new DataCache();
        }
        return dataCache;
    }
    /**
     * ************************************
     * getCache
     * @param cacheNames 缓存名
     * @param methodName 方法名
     * @param o
     * @param searchKey 要搜索的键名
     * @param searchVl  要搜索的值
     * @return Object
     * ************************************
     */
    public Object getCache(String cacheNames,String methodName,Object o,
                           String searchKey,String searchVl){
        AdminInfo adminInfo=new BaseController().getAdminInfo();
        String admin_name=adminInfo.getAdmin_name();
        if (searchKey!=null&&(searchVl==null || "".equals(searchVl))) {
            return new HashMap<String, String>();
        }
        String cacheName=cacheNames.indexOf(".")!=-1?(cacheNames.split("\\.")[0]):cacheNames;
        String caKey=admin_name+PropertiesData.belong+cacheName;
        //cacheName的名称为AA.BB.CC时，cache_result相当于get("AA");
        Object cache_result=cache.get(caKey);
        try {
            if (cache_result==null) {
                Method[] ms=this.getClass().getMethods();
                for (Method m : ms) {
                    if (m.getName().equals("getCache"+methodName)) {
                        cache_result=m.getParameterAnnotations().length==0?m.invoke(this):m.invoke(this, o);
                        break;
                    }
                }
                cache.add(caKey, cache_result);
            }

            if (searchKey!=null&&searchVl!=null) {
                Map<String,Object> return_result=new HashMap<String,Object>();
                Map<String,Object> current=(Map<String, Object>) cache_result;
                for (String k : current.keySet()) {
                    Map<String,Object> n=(Map<String, Object>) current.get(k);
                    if (n.get(searchKey).toString().equals(searchVl)) {
                        return_result.put(k, n);
                    }
                }
                return return_result!=null?return_result:new HashMap<String,Object>();
            }
            if (cacheNames.indexOf(".")!=-1) {
                String[] names=cacheNames.split("\\.");
                Map<String,Object> current=(Map<String, Object>) cache_result;
                for (int i = 1; i < names.length-1; i++) {
                    if (current.get(names[i])!=null) {
                        current=(Map<String, Object>) current.get(names[i]);
                    }else{
                        return new HashMap<String,Object>();
                    }
                }
                cache_result=current.get(names[names.length-1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new HashMap<String,Object>();
        }
        return cache_result!=null?cache_result:new HashMap<String,Object>();
    }
    /**
     * ************************************
     * getCache
     * @param key 缓存名(getCache+key是方法名)
     * @param args 参数
     * @param searchKey 要搜索的键名
     * @param searchVl  要搜索的值
     * @return Object
     * ************************************
     */
    public Object getCache(String key,String args,String searchKey,String searchVl){
        String methodName=key.split("\\.")[0];
        methodName=methodName.substring(0,1).toUpperCase()+methodName.replaceFirst("\\w", "");
        return this.getCache(key, methodName,args, searchKey, searchVl);
    }

    public Object getCache(String key,String searchKey,String searchVl){
        return this.getCache(key, null, searchKey, searchVl);
    }

    public Object getCache(String key,String args){
        return this.getCache(key, args, null, null);
    }

    public Object getCache(String key){
        return this.getCache(key, null, null, null);
    }




    /**
     *
     *********************************************************.<br>
     * [方法] getCacheMenuCarte <br>
     * [描述] 获取用户菜单 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public List<Map<String,Object>> getCacheMenuCarte(){
        List<Map<String,Object>> carteList=null;
        try {
            AdminInfo adminInfo=new BaseController().getAdminInfo();
            //用户ID
            Integer admin_id=adminInfo.getAdmin_id();
            Map map=new HashMap();
            map.put("admin_id", admin_id);
            map.put("belong_terrace", 2);
            map.put("is_admin", adminInfo.getIs_admin());
            map.put("gn_type",0);
            String admin_name=adminInfo.getAdmin_name();

            //查询菜单
            BackCarteServiceImpl backCarteServiceImpl=SpringUtil.getBean(BackCarteServiceImpl.class);
            carteList=backCarteServiceImpl.getList("BackCarte.HFBackCarte", map);
            AdminInfoServiceImpl adminInfoServiceImpl=SpringUtil.getBean(AdminInfoServiceImpl.class);
            map.put("gn_type1",1);
            List<Map<String,Object>> btList=adminInfoServiceImpl.getList("AdminInfo.getButtonsByAdmin", map);
            Map<String,Object> btResultMap=new HashMap<String,Object>();

            //用于按钮权限
            for (Map<String, Object> btMap : btList) {
                List<Map<String, Object>> tmpList=null;
                String keyStr=admin_name+btMap.get("CARTE_ID");
                Object obj=btResultMap.get(keyStr);
                if (obj==null) {
                    tmpList=new ArrayList<Map<String,Object>>();
                }else{
                    tmpList=(List<Map<String, Object>>) obj;
                }
                tmpList.add(btMap);
                btResultMap.put(keyStr, tmpList);
            }
            cache.add(admin_name+ PropertiesData.belong+"Buttons", btResultMap);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return carteList;
        }
    }

    /**
     * ************************************
     * 获取执行SQL所需要的格式： 命令空间.SQLID
     * @param sqlId
     * @return String
     * ************************************
     */
    public String getSqlName(String sqlId){
        return this.sqlSpaceName+"."+sqlId;
    }

}
