package com.yh.service;

import com.yh.entity.Page;
import com.yh.entity.SysManager;

import java.util.List;
import java.util.Map;

public interface SysManagerService extends BaseService<SysManager,Integer> {
    /**
     *
     *********************************************************.<br>
     * [方法] getPageList <br>
     * [描述] 分页查询 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] void <br>
     * [作者] lvl
     *********************************************************.<br>
     */
    public void getPageList(String statement, Page page) throws Exception;
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
    public List<Map<String, Object>> getList(String statement, Object param) throws Exception;
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
    public int saveRole(Map params) throws Exception;
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
    public String roleQx(Integer adminId, Integer xRole) throws Exception;
    /**
     *
     *********************************************************.<br>
     * [方法] saveroleQxCarte <br>
     * [描述] 保存菜单权限 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
    public void saveroleQxCarte(Integer role_no, String carteIds) throws Exception;
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
    public Map<String,Object> roleQxButton(Integer xrole_no, Integer adminId) throws Exception;
    /**
     *
     *********************************************************.<br>
     * [方法] saveRoleQxButton <br>
     * [描述] 保存按钮权限 <br>
     * [参数] TODO(对参数的描述) <br>
     * [返回] String <br>
     *********************************************************.<br>
     */
    public void saveRoleQxButton(Integer role_no, String buttonData) throws Exception;
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
    public int saveUser(Map params) throws Exception;
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
    public int userCkName(String admin_name) throws Exception;
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
    public int updateUserStatus(Integer admin_id, Integer status) throws Exception;
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
    public int deleteUser(Integer admin_id) throws Exception;
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
    public Object getObject(String statement, Object param) throws Exception;
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
    public int updateUserPass(Map param) throws Exception;
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
    public int saveUserRole(Map paramMap) throws Exception;
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
    public int saveCdDept(Map params) throws Exception;
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
    public void saveSysLog(Map params) throws Exception;
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
    public int delcdDept(Integer cd_id) throws Exception;
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
    public int delRole(String roleIds) throws Exception;

}
