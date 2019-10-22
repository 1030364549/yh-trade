<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <div class="bjui-searchBar">
            <span>登录账号：</span>
            <input type="text" name="admin_name" value="${(p.params.admin_name)!''}" class="form-control" size="15">
            <span>真实姓名：</span>
            <input type="text" name="admin_realname" value="${(p.params.admin_realname)!''}" class="form-control" size="15">
            <span class="row-label">状态:</span>
            <span class="row-input">
				<select data-toggle="selectpicker" name="att"
                        selectvl="${(p.params.att)!}">
					<option value="">全部</option>
					<option value="0">启用</option>
					<option value="1">禁用</option>
				</select>
			</span>
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" data-toggle="resetSearchCon" data-icon="undo">清空查询</a>

            <div style="margin-top: 1px;height: 25px">
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/userAdd"  rel="SysManager_userAdd" data-toggle="dialog" data-options="{id:'dialog',title:'添加用户',width:'500',height:'345'}"><i class="fa fa-plus"></i><span>添加用户</span></a></span>
            </div>
            <div class="pull-right">
            </div>
        </div>
    <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="10%">账号</th>
            <th width="10%">真实姓名</th>
            <th width="10%">介绍</th>
            <th width="10%">状态</th>
            <th width="10%">添加日期</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
            <#if p.results ??>
            <#list p.results as list>
				<tr data-id="admin_id=${(list.ADMIN_ID)!}&status=${(list.ATT)!}" >
                    <td >
                        ${(list.ADMIN_NAME)!'<span style="color:gray">暂无</span>'}
                    </td>
                    <td >${(list.ADMIN_REALNAME)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.ADMIN_INTRO)!'<span style="color:gray">暂无</span>'}</td>
                    <td >
						<#if list.ATT ??>
							<#if list.ATT==0>
								<div style="color:green;">启用</div>
                            <#else>
								<div style="color:red;">禁用</div>
                            </#if>
                        </#if>
                    </td>
                    <td >${(list.ADD_TIME)!'<span style="color:gray">暂无</span>'}</td>
                    <td >
                        <a class="btn btn-red permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/updateUserStatus?admin_id=${(list.ADMIN_ID)!}&status=${(list.ATT)!}" rel="SysManager_updateUserStatus" data-toggle="doajax"  data-confirm-msg = "确定<#if list.ATT==0>禁用<#else>启用</#if>吗？" ><i class="fa fa-edit"></i><span>启/禁</span></a>&nbsp;
                        <a class="btn btn-red permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/delUser?admin_id=${(list.ADMIN_ID)!}" rel="SysManager_delUser" data-toggle="doajax"  data-confirm-msg = "确定要删除该用户吗？" ><i class="fa fa-remove"></i><span>删</span></a>&nbsp;
                        <a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/userPass?admin_id=${(list.ADMIN_ID)!}" rel="SysManager_userPass" data-toggle="dialog"  data-options="{id:'SysManager_userPass',title:'重置密码',width:'500',height:'345'}"><i class="fa fa-edit"></i><span>密码</span></a>&nbsp;
                        <a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/userRole?admin_id=${(list.ADMIN_ID)!}" rel="SysManager_userRole" data-toggle="dialog" data-options="{id:'dialog',title:'角色设置',width:'500',height:'606'}"><i class="fa fa-edit"></i><span>角色</span></a>&nbsp;
                    </td>
                </tr>
            </#list>
            </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">