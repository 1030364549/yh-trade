package com.yh.controller;

import com.yh.aopshiro.BlueLog;
import com.yh.entity.PropertiesData;
import com.yh.service.SysManagerService;
import com.yh.util.Utils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("all")
@Scope("prototype")
@Controller
@RequestMapping("SysManager")
public class SysManagerController extends BaseController {

    @Resource
    private SysManagerService sysManagerService;

    /**
     *
     *********************************************************.<br>
     * [方法] roleList <br>
     * [描述] 角色列表查询<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_roleList")
    @RequestMapping("roleList")
    public String roleList(){
        try {
            Map<String,Object> paramMap=new HashMap<>();
            paramMap.put("belong",PropertiesData.belong);
            List<Map<String, Object>> roleList = sysManagerService.getList("SysManager.getRoleList",paramMap);

            setAttribute("roleList",roleList);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("角色管理:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] roleQx <br>
     * [描述] 跳转角色权限界面 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_roleQx")
    @RequestMapping("roleQx")
    public String roleQx(@RequestParam Integer role_id){
        try {
            String jsonList = sysManagerService.roleQx(getAdminInfo().getAdmin_id(),role_id);

            setAttribute("jsonList", jsonList);
            setAttribute("role_no", role_id);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("跳转角色权限界面:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveRole <br>
     * [描述] 添加/修改用户信息 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_roleList")
    @BlueLog("系统管理,角色管理,添加角色")//模块名称,菜单名称,功能名称
    @RequestMapping("saveRole")
    public @ResponseBody Map saveRole(@RequestParam Map paramMap){
        try {
            paramMap.put("create_name",this.getAdminInfo().getAdmin_name());
            paramMap.put("create_time",Utils.formateDate(0));
            paramMap.put("belong",PropertiesData.belong);
            int result=sysManagerService.saveRole(paramMap);
            return result>0?Success("添加成功", ""):message("添加失败", 1);
        } catch (Exception e) {
            printErrorMessage("添加角色:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] allotCarte <br>
     * [描述] 保存菜单权限 <br>
     * [返回] String <br>
     * [时间] 2018年10月31日 下午5:11:52 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_saveRoleQxCarte")
    @BlueLog("系统管理,角色管理,设置角色菜单权限")//模块名称,菜单名称,功能名称
    @RequestMapping("saveRoleQxCarte")
    public @ResponseBody Map saveRoleQxCarte(@RequestParam Integer role_no,@RequestParam String carteIds){
        try {
            sysManagerService.saveroleQxCarte(role_no, carteIds);
            return Success("设置菜单权限成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("保存菜单权限:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] roleQxButton <br>
     * [描述] 按钮权限界面 <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
    @RequestMapping("roleQxButton")
    public String roleQxButton(@RequestParam Integer role_no){
        try {
            Map<String,Object> resultMap=sysManagerService.roleQxButton(role_no, getAdminInfo().getAdmin_id());
            setAttribute("buttonData", resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("按钮权限界面:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveRoleQxButton <br>
     * [描述] 保存按钮权限 <br>
     * [返回] Map <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_saveRoleQxButton")
    @BlueLog("系统管理,角色管理,设置角色按钮权限")//模块名称,菜单名称,功能名称
    @RequestMapping("saveRoleQxButton")
    public @ResponseBody Map saveRoleQxButton(@RequestParam Integer roleNo,@RequestParam String buttonData){
        try {
            sysManagerService.saveRoleQxButton(roleNo, buttonData);
            return Success("设置角色按钮权限成功", "");
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("保存角色按钮权限:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userList <br>
     * [描述] 登录用户列表查询<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    @RequestMapping("userList")
//    @RequiresPermissions("SysManager_userList")//权限注解
    public String userList(){
        try {
            if ("1".equals(getShowType())){
                this.getPage().getParams().put("belong",0);
                //系统管理员不显示
                this.getPage().getParams().put("is_admin",1);
                sysManagerService.getPageList("SysManager.getUserList",this.getPage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("用户管理:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userAdd <br>
     * [描述] 添加用户<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userAdd")//权限注解
    @RequestMapping("userAdd")
    public String userAdd(){
        try {
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("添加用户:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveUser <br>
     * [描述] 添加用户 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userAdd")
    @BlueLog("系统管理,用户管理,添加用户")//模块名称,菜单名称,功能名称
    @RequestMapping("saveUser")
    public @ResponseBody Map saveUser(@RequestParam Map paramMap){
        try {
            paramMap.put("add_man",this.getAdminInfo().getAdmin_name());
            int result=sysManagerService.saveUser(paramMap);
            return result>0?message("添加成功", 0):message("添加失败", 1);
        } catch (Exception e) {
            printErrorMessage("添加用户:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userCkName <br>
     * [描述] 添加用户--校验用户名 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
    @RequestMapping("userCkName")
    public @ResponseBody boolean userCkName(@RequestParam String admin_name){
        boolean flag=true;
        try {
            int result=sysManagerService.userCkName(admin_name);
            flag = result > 0 ? true : false;
        } catch (Exception e) {
            printErrorMessage("添加用户:",e,this.request);
        }
        return flag;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] updateUserStatus <br>
     * [描述] 启用禁用 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_updateUserStatus")
    @BlueLog("系统管理,用户管理,用户启用/禁用")//模块名称,菜单名称,功能名称
    @RequestMapping("updateUserStatus")
    public @ResponseBody Map updateUserStatus(Integer admin_id,Integer status){
        String message=(status==0?"禁用":"启用");
        try {
            int reStatus=(status==0?1:0);

            int result=sysManagerService.updateUserStatus(admin_id,reStatus);
            message+=(result>0?"成功":"失败");
            this.resFinally=result>0?true:false;
        } catch (Exception e) {
            printErrorMessage("用户启用/禁用:",e,this.request);
            message+="异常";
            this.resFinally=false;
        }
        return message(message, this.resFinally);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] delUser <br>
     * [描述] 删除用户 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_delUser")
    @BlueLog("系统管理,用户管理,删除用户")//模块名称,菜单名称,功能名称
    @RequestMapping("delUser")
    public @ResponseBody Map delUser(Integer admin_id){
        String message="删除用户";
        try {
            int result=sysManagerService.deleteUser(admin_id);
            message+=(result>0?"成功":"失败");
            this.resFinally=result>0?true:false;
        } catch (Exception e) {
            printErrorMessage("删除用户:",e,this.request);
            message+="异常";
            this.resFinally=false;
        }
        return message(message, this.resFinally);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userPass <br>
     * [描述] 重置密码界面<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userPass")
    @RequestMapping("userPass")
    public String userPass(@RequestParam Integer admin_id){
        try {
            Map map=new HashMap();
            map.put("adminId",admin_id);
            Map adminMap=(Map)sysManagerService.getObject("AdminInfo.getAdminById",map);
            setAttribute("adminMap",adminMap);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("重置密码:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] updateUserPass <br>
     * [描述] 重置密码 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userPass")
    @BlueLog("系统管理,用户管理,重置密码")//模块名称,菜单名称,功能名称
    @RequestMapping("updateUserPass")
    public @ResponseBody Map updateUserPass(@RequestParam Map paramMap){
        try {
            paramMap.put("add_man",this.getAdminInfo().getAdmin_name());
            int result=sysManagerService.updateUserPass(paramMap);
            return result>0?message("重置密码成功", 0):message("重置密码失败", 1);
        } catch (Exception e) {
            printErrorMessage("重置密码:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] userRole <br>
     * [描述] 用户角色界面<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userRole")
    @RequestMapping("userRole")
    public String userRole(@RequestParam Integer admin_id){
        try {
            Map paramMap=new HashMap();
            paramMap.put("xadmin_id",admin_id);
            paramMap.put("belong",PropertiesData.belong);
            //获取该用户已有的角色
            List<Map<String, Object>> roleList = sysManagerService.getList("SysManager.getUserRoleList",paramMap);
            setAttribute("roleList",roleList);
            setAttribute("admin_id",admin_id);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("用户角色界面:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveUserRole <br>
     * [描述] 添加用户角色 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_userRole")
    @BlueLog("系统管理,用户管理,设置用户角色")//模块名称,菜单名称,功能名称
    @RequestMapping("saveUserRole")
    public @ResponseBody Map saveUserRole(@RequestParam Map paramMap){
        try {
            paramMap.put("add_man",this.getAdminInfo().getAdmin_name());
            int result=sysManagerService.saveUserRole(paramMap);
            return result>0?message("添加成功", 0):message("添加失败", 1);
        } catch (Exception e) {
            printErrorMessage("添加用户角色:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] cdDept <br>
     * [描述] 部门列表查询<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_cdDept")
    @RequestMapping("cdDept")
    public String cdDept(Map paramMap){
        try {
            List<Map<String, Object>> deptList=sysManagerService.getList("SysManager.getDeptList",paramMap);
            //组装Tree结构
            List<Map<String,Object>> deptDataList=getTreeData(deptList,"0");
            setAttribute("deptDataList",deptDataList);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("部门管理:",e,this.request);
        }
        return this.display();
    }
    /** 组装JSON数据 **/
    public List<Map<String,Object>> getTreeData(List<Map<String, Object>> deptList,String id){
        List<Map<String,Object>> treeList=new ArrayList<>();
        //
        for (Map<String,Object> tmpMap : deptList){
            if (id.equals(tmpMap.get("PARENT_CDID").toString())){
                treeList.add(tmpMap);
                List<Map<String,Object>> childrenList=getTreeData(deptList,tmpMap.get("CD_ID").toString());
                treeList.addAll(childrenList);
            }
        }
        return treeList;
    }
    /**
     *
     *********************************************************.<br>
     * [方法] cdDeptAdd <br>
     * [描述] 添加部门<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_cdDeptAdd")
    @RequestMapping("cdDeptAdd")
    public String cdDeptAdd(Map paramMap){
        try {
            List<Map<String, Object>> deptList=sysManagerService.getList("SysManager.getDeptList",paramMap);
            //组装Tree结构
            List<Map<String,Object>> deptDataList=getTreeData(deptList,"0");
            setAttribute("deptDataList",deptDataList);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("添加部门:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] saveCdDept <br>
     * [描述] 保存部门 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     * [时间] 2018年10月25日 下午4:31:28 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_cdDeptAdd")
    @BlueLog("系统管理,部门管理,添加部门")//模块名称,菜单名称,功能名称
    @RequestMapping("saveCdDept")
    public @ResponseBody Map saveCdDept(@RequestParam Map paramMap){
        try {
            paramMap.put("add_man",this.getAdminInfo().getAdmin_name());

            int result=sysManagerService.saveCdDept(paramMap);
            return result>0?message("添加成功", 0):message("添加失败", 1);
        } catch (Exception e) {
            printErrorMessage("保存部门:",e,this.request);
            return message("异常", 1);
        }
    }
    /**
     *
     *********************************************************.<br>
     * [方法] behaviorList <br>
     * [描述] 行为记录<br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     * [作者] lvl
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_behaviorList")
    @RequestMapping("behaviorList")
    public String behaviorList(){
        try {
            Object startlocaldate=this.getPage().getParams().get("startlocaldate");
            if (startlocaldate!=null && !"".equals(startlocaldate)) {
                Object endlocaldate=this.getPage().getParams().get("endlocaldate");
                if (endlocaldate==null || "".equals(endlocaldate)){
                    this.getPage().getParams().put("endlocaldate",Utils.formateDate(0));
                }
                sysManagerService.getPageList("SysManager.sysLogList", this.getPage());
            }else{
                this.getPage().getParams().put("startlocaldate",Utils.formateDate(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("行为记录:",e,this.request);
        }
        return this.display();
    }
    /**
     *
     *********************************************************.<br>
     * [方法] delcdDept <br>
     * [描述] 删除部门 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] Map <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_delcdDept")
    @BlueLog("系统管理,部门管理,删除部门")//模块名称,菜单名称,功能名称
    @RequestMapping("delcdDept")
    public @ResponseBody Map delcdDept(Integer cd_id){
        String message="删除部门";
        try {
            int result=sysManagerService.delcdDept(cd_id);
            message+=(result>0?"成功":"失败");
            this.resFinally=result>0?true:false;
        } catch (Exception e) {
            printErrorMessage("删除部门:",e,this.request);
            message+="异常";
            this.resFinally=false;
        }
        return message(message, this.resFinally);
    }
    /**
     *
     *********************************************************.<br>
     * [方法] delRole <br>
     * [描述] 删除角色 <br>
     * [返回] String <br>
     * [时间] 2018年10月31日 下午5:11:52 <br>
     *********************************************************.<br>
     */
//    @RequiresPermissions("SysManager_delRole")
    @BlueLog("系统管理,角色管理,删除角色")//模块名称,菜单名称,功能名称
    @RequestMapping("delRole")
    public @ResponseBody Map delRole(@RequestParam String roleIds){
        try {
            int result=sysManagerService.delRole(roleIds);
            return result>0?Success("删除角色成功", ""):message("删除失败",1);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("删除角色:",e,this.request);
            return message("异常", 1);
        }
    }
    public @ResponseBody Map executeSQL(){
        try {
            int result=0;
            return result>0?Success("删除角色成功", ""):message("删除失败",1);
        } catch (Exception e) {
            e.printStackTrace();
            printErrorMessage("删除角色:",e,this.request);
            return message("异常", 1);
        }
    }


}
