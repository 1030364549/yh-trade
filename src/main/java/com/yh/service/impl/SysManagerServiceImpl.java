package com.yh.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yh.dao.SysManagerDao;
import com.yh.entity.Page;
import com.yh.entity.SysManager;
import com.yh.service.SysManagerService;
import com.yh.util.SecurityUtil;
import com.yh.util.Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@SuppressWarnings("all")
@Service
public class SysManagerServiceImpl extends BaseServiceImpl<SysManager,Integer> implements SysManagerService {

    @Resource
    private SysManagerDao sysManagerDao;

    /**
     *
     *********************************************************.<br>
     * [方法] getPageList <br>
     * [描述] 分页查询 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [时间] 2017-12-07 下午19:19:19 <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public void getPageList(String statement,Page page) throws Exception{
        sysManagerDao.getPageList(statement,page);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] getList <br>
     * [描述] 获取集合 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public List<Map<String, Object>> getList(String statement,Object param) throws Exception{
        return sysManagerDao.getList(statement,param);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveRole <br>
     * [描述] 添加角色 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int saveRole(Map params) throws Exception{
        int result=0;
        try {
            //级别
            int levels=1;
            //不是在总部下一级的需要获取当前分组的级别
            if (!"1".equals(params.get("parent_role").toString())){
                levels=sysManagerDao.getInt("SysManager.getRoleLvel",params)+1;
            }
            params.put("lvl",levels);
            result=sysManagerDao.insert("SysManager.saveRole",params);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] roleQx <br>
     * [描述] 设置权限界面 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public String roleQx(Integer adminId,Integer xRole) throws Exception{
        String jsonList=null;
        try {
            Map map=new HashMap();
            map.put("adminId",adminId);
            //查询当前登录用户信息
            Map loginMap = (Map) sysManagerDao.selectOne("AdminInfo.getAdminById",map);
            Integer llvl=1;
            if ("0".equals(loginMap.get("IS_ADMIN").toString())){
                llvl=0;
            }
            map.put("xRole",xRole);
            map.put("belong_terrace",2);
            map.put("gn_type",0);

            List<Map<String,Object>> jsonFormatList = new ArrayList<Map<String,Object>>();

            List<Map<String,Object>> roleList=sysManagerDao.getList("SysManager.getCarteByRole",map);
            for (Map<String,Object> allMap : roleList) {
                //linkedHashMap  有顺序的hashMap
                Map<String,Object> currentMap = new LinkedHashMap<String, Object>();
                //ztree格式中的菜单编号(唯一标示)
                currentMap.put("parentId",allMap.get("PARENT_ID"));
                //ztree格式中的菜单父类编号(父类唯一标示)
                currentMap.put("parent",allMap.get("PARENTS"));
                //ztree格式中的菜单名称
                currentMap.put("name",allMap.get("CARTE_NAME"));
                //ztree格式中的一个属性,存储着菜单id
                currentMap.put("carteId",allMap.get("ID"));

                if (allMap.get("ID").equals(allMap.get("CID"))) {
                    currentMap.put("checked","true");
                }else{
                    currentMap.put("checked","false");
                }
                jsonFormatList.add(currentMap);
            }
            jsonList=new ObjectMapper().writeValueAsString(jsonFormatList);
            jsonList=jsonList.replace("\"", "\'");
        } catch (Exception e) {
            throw e;
        }
        return jsonList;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveroleQxCarte <br>
     * [描述] 保存菜单权限 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
    public void saveroleQxCarte(Integer role_no,String carteIds) throws Exception{
        try {
            String[] carteid=carteIds.split(",");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("role_id", role_no);
            map.put("gn_type",0);
            map.put("carteIds", carteid);
            //删除已有菜单
            sysManagerDao.delete("SysManager.delCarteOrBtByRole", map);
            if (!"".equals(carteIds)){
                //保存菜单
                sysManagerDao.insert("SysManager.saveRoleCarteOrBt", map);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] roleQxButton <br>
     * [描述] 查询用户按钮 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public Map<String,Object> roleQxButton(Integer xrole_no,Integer adminId) throws Exception{
        Map<String,Object> resultMap=new HashMap<String,Object>();
        try {
            Map map=new HashMap();
            map.put("adminId",adminId);
            //查询当前登录用户信息
            Map loginMap = (Map) sysManagerDao.selectOne("AdminInfo.getAdminById",map);
            Integer llvl=1;
            if ("0".equals(loginMap.get("IS_ADMIN").toString())){
                llvl=0;
            }
            map.put("xRole",xrole_no);
            map.put("belong_terrace",2);
            map.put("gn_type1",1);
            map.put("gn_type0",0);
            List<Map<String, Object>> buttonList=sysManagerDao.getList("SysManager.getButtonByCarte", map);
            for (Map<String, Object> btMap : buttonList) {
                List<Map<String, Object>> tmpList=null;
                Object obj=resultMap.get(btMap.get("CARTE_NAME").toString());
                if (obj==null) {
                    tmpList=new ArrayList<Map<String, Object>>();
                }else{
                    tmpList=(List<Map<String, Object>>) obj;
                }

                if (btMap.get("BT_ID").equals(btMap.get("BTID2"))) {
                    btMap.put("checked", "true");
                }

                tmpList.add(btMap);
                resultMap.put(btMap.get("CARTE_NAME").toString(), tmpList);
            }
        } catch (Exception e) {
            throw e;
        }
        return resultMap;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveRoleQxButton <br>
     * [描述] 保存按钮权限 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
    public void saveRoleQxButton(Integer role_no,String buttonData) throws Exception{
        try {
            String[] btDatas=buttonData.split(",");
            Map<String,Object> map=new HashMap<String,Object>();
            map.put("role_id", role_no);
            map.put("gn_type",1);
            map.put("carteIds", btDatas);
            System.out.println("按钮:"+btDatas);
            //删除角色按钮权限
            sysManagerDao.delete("SysManager.delCarteOrBtByRole", map);
            if (!"".equals(buttonData)){
                //保存角色按钮权限
                sysManagerDao.insert("SysManager.saveRoleCarteOrBt", map);
            }
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveUser <br>
     * [描述] 添加用户 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int saveUser(Map params) throws Exception{
        int result=0;
        try {
            String pwd=params.get("admin_pass").toString();
            params.put("belong",0);
            params.put("is_admin",1);
            params.put("att",0);
            params.put("add_time",Utils.formateDate(0));
            params.put("admin_pass",SecurityUtil.sha1Encode(pwd));
            params.put("obj_no","0");
            result=sysManagerDao.insert("AdminInfo.saveUser",params);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userCkName <br>
     * [描述] 添加用户--校验用户名 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int userCkName(String admin_name) throws Exception{
        int result=1;//默认存在
        try {
            result=sysManagerDao.getCount("AdminInfo.getCountByName",admin_name);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] updateUserStatus <br>
     * [描述] 用户管理--启用禁用 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int updateUserStatus(Integer admin_id,Integer status) throws Exception{
        int result=0;
        try {
            Map param=new HashMap();
            param.put("admin_id", admin_id);
            param.put("att", status);
            result=sysManagerDao.update("AdminInfo.HFUpdateAdminSattus",param);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] deleteUser <br>
     * [描述] 用户管理--删除用户 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int deleteUser(Integer admin_id) throws Exception{
        int result=0;
        try {
            //删除用户角色关联
            sysManagerDao.delete("SysManager.delUserRole",admin_id);
            //删除用户
            result = sysManagerDao.delete("AdminInfo.delUserInfo",admin_id);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] getObject <br>
     * [描述] 获取单个数据 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public Object getObject(String statement,Object param) throws Exception{
        return sysManagerDao.selectOne(statement,param);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] updateUserPass <br>
     * [描述] 重置用户的密码 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int updateUserPass(Map param) throws Exception{
        int result=0;
        try {
            String pwd=param.get("admin_pass").toString();
            param.put("admin_pass",SecurityUtil.sha1Encode(pwd));
            result=sysManagerDao.update("AdminInfo.updateAdminPass",param);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveUserRole <br>
     * [描述] 保存用户角色 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int saveUserRole(Map paramMap) throws Exception{
        int result=0;
        try {
            paramMap.put("add_time",Utils.formateDate(0));
            String roles=paramMap.get("roleids").toString();
            //把角色ID组转成数组
            String[] roleids=roles.split(",");
            paramMap.put("roleids",roleids);
            paramMap.put("admin_id",Integer.parseInt(paramMap.get("admin_id").toString()));
            //删除已有的角色
            sysManagerDao.delete("SysManager.delUserRole",paramMap);
            if ("0".equals(roles)){
                result=1;
            }else{
                //添加
                result=sysManagerDao.insert("SysManager.saveUserRole",paramMap);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveCdDept <br>
     * [描述] 保存部门 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int saveCdDept(Map params) throws Exception{
        int result=0;
        try {
            //查询父级 级别
            String parent_cdid=params.get("parent_cdid").toString();
            int lvl=0;
            if (!"0".equals(parent_cdid)){
                int plvl=sysManagerDao.getInt("SysManager.getLvlByDept",params);
                lvl=plvl+1;
            }
            params.put("lvl",lvl);
            params.put("add_date",Utils.formateDate(0));
            result=sysManagerDao.insert("SysManager.saveCompanyDept",params);
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveSysLog <br>
     * [描述] 保存系统行为记录 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public void saveSysLog(Map params) throws Exception{
        try {
            sysManagerDao.insert("SysManager.saveSysLog",params);
        } catch (Exception e) {
            throw e;
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] delcdDept <br>
     * [描述] 删除部门 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int delcdDept(Integer cd_id) throws Exception{
        int result=0;
        try {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("cd_id",cd_id);
            paramMap.put("parent_cdid",cd_id);
            //判断该部门是否有下级部门,有则不能删
            int count=sysManagerDao.getCount("SysManager.getCountByDept",paramMap);
            if (count==0){
                result=sysManagerDao.delete("SysManager.delcdComp",paramMap);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] delRole <br>
     * [描述] 删除角色 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public int delRole(String roleIds) throws Exception{
        int result=0;
        try {
            String[] role_ids=roleIds.split(",");
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("role_ids",role_ids);
            //判断该角色是否有下级角色
            int count=sysManagerDao.getCount("SysManager.getCountByRole",paramMap);
            if (count==0){
                result=sysManagerDao.delete("SysManager.delRole",paramMap);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }


}
