<script>
    var haveRoleCount = false;
    function changeHaveCoatAdmin(roleCount) {
        haveRoleCount = false;
        if (roleCount > 0){
            haveRoleCount = true;
        }
    }
    function checkRoleCount(e) {
        if (!haveRoleCount){
            $(e).alertmsg("error","请先配置角色");
            e.preventDefault();
            e.stopPropagation();
            return false;
        }
    }
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
            <p>
                &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
                <input type="text"  name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;&nbsp;<span>用户编号：</span>
                <input type="text"  name="admin_name" value="${(p.params.admin_name)!''}" class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;&nbsp;<span>真实姓名：</span>
                <input type="text"  name="admin_realname" value="${(p.params.admin_realname)!''}"  class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp; <span>用户状态：</span>&nbsp;&nbsp;
                <select id="att" data-toggle="selectpicker"  name="att"  selectvl="${(p.params.att)!''}"  style="height: 25px;">
                    <option value="">请选择</option>
                    <option value="0">启用</option>
                    <option value="1">禁用</option>
                </select>
                <input type="hidden" name="showType" value="1" >&nbsp;
                <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
                <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
            </p>
            <div style="margin-top: 1px;height: 25px">
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/updateAdminAtt?id={#bjui-selected}" onclick="checkRoleCount(event)" data-toggle="doajax"  data-confirm-msg = "确定要变更用户状态么？" rel="AdminInfo_updateAdminAtt" width="450" ><i class="fa fa-edit"></i><span>启用/禁用用户</span></a>&nbsp;&nbsp;</span>
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/updateAdminPassword?id={#bjui-selected}" data-toggle="doajax"  data-confirm-msg = "确定要重置密码么？" rel="AdminInfo_updateAdminPassword" width="450" ><i class="fa fa-edit"></i><span>重置密码</span></a>&nbsp;&nbsp;</span>
            </div>
            <div class="pull-right"></div>
        </div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
 <table class="table table-bordered table-hover table-striped table-top">
  <thead>
   <tr>
    <th width="12%">机构编号</th>
    <th width="12%">用户编号</th>
    <th width="12%">真实姓名</th>
    <th width="12%">是否管理员</th>
    <th width="12%">用户状态</th>
    <th width="12%">添加人</th>
    <th width="12%">添加时间</th>
    <th width="12%">备注</th>
    <th width="12%">操作</th>
   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.ADMIN_ID)!}" onclick="changeHaveCoatAdmin(${(list.ROLE_COUNT)!''})" >
     <td>${(list.OBJ_NO)!}</td>
     <td>${(list.ADMIN_NAME)!}<#if list.ROLE_COUNT??><span style="color: blue">[已配置角色]<#else ><span style="color: red">[未配置角色]</span></#if></td>
     <td>${(list.ADMIN_REALNAME)!}</td>
     <td>
	    <#switch "${(list.IS_ADMIN)!}">
	     <#case "0">是<#break>
	     <#case "1">否<#break>
	     <#default>
        </#switch>
     </td>
     <td>
	  <#switch "${(list.ATT)!}">
	   <#case "0"><span style="color: blue">启用</span><#break>
	   <#case "1"><span style="color: red">禁用</span><#break>
	   <#default>
	  </#switch>
	 </td>
     <td>${(list.ADD_MAN)!}</td>
     <td>${(list.ADD_TIME)!}</td>
     <td>${(list.ADMIN_INTRO)!}</td>
     <td>
         <a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${base}/SysManager/userRole?admin_id=${(list.ADMIN_ID)!}" rel="SysManager_userRole" data-toggle="dialog" data-options="{id:'SysManager_userRole',title:'配置角色',width:'500',height:'606'}"><i class="fa fa-edit"></i><span>配置角色</span></a>&nbsp;
     </td>
    </tr>
   </#list>
   </#if>
  </tbody>
 </table>
</div>
<#include "/common/pages.ftl">