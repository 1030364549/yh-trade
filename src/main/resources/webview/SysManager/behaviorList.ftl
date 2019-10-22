<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <div class="bjui-searchBar">
            <span>操作人：</span>
            <input type="text" name="username" value="${(p.params.username)!''}" class="form-control" size="15">
            <span>模块名称：</span>
            <input type="text" name="modular" value="${(p.params.modular)!''}" class="form-control" size="15">
            <span>菜单名称：</span>
            <input type="text" name="cartename" value="${(p.params.cartename)!''}" class="form-control" size="15">
            <span>操作内容：</span>
            <input type="text" name="operation" value="${(p.params.operation)!''}" class="form-control" size="15">
            <span>所属平台：</span>
            <select data-toggle="selectpicker" name="belong" selectvl="${(p.params.belong)!}">
                <option value="0">总后台</option>
                <option value="1">代理商</option>
            </select>
            <span>开始日期：</span> <input type="text" name="startlocaldate" value="${(p.params.startlocaldate)!''}" data-toggle="datepicker" class="form-control" size="15"> <input type="hidden" name="showType" value="1">
            &nbsp;&nbsp;&nbsp;<span>结束日期：</span> <input type="text" name="endlocaldate" value="${(p.params.endlocaldate)!''}" data-toggle="datepicker" class="form-control" size="15">


            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>

            <div style="margin-top: 1px;">
                <span style="color:red;font-weight: bold;">开始日期必须选择</span>
                <#--<span><a class="btn btn-red" href="${baseClass}/executeSQL" data-toggle="doajax"  data-confirm-msg = "确定要执行吗？" rel="AgentInfo_delete" ><i class="fa fa-edit"></i><span>执行语句</span></a></span>-->
            </div>
            <div class="pull-right">
            </div>
        </div>
    <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top table-layout" >
        <thead>
        <tr>
            <th width="5%">操作人</th>
            <th width="5%">模块名称</th>
            <th width="5%">菜单名称</th>
            <th width="5%">操作内容</th>
            <th width="10%">方法</th>
            <th width="5%">IP</th>
            <th width="5%">操作时间</th>
            <th width="5%">所属平台</th>
        </tr>
        </thead>
        <tbody>
            <#if p.results ??>
            <#list p.results as list>
				<tr >
                    <td >${(list.USERNAME)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.MODULAR)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.CARTENAME)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.OPERATION)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.METHOD)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.IP)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.CREATEDATE)!''} ${(list.CREATETIME)!''}</td>
                    <td >
                        <#if list.BELONG == 0 >
                            总后台
                        <#else >
                            代理商
                        </#if>
                    </td>
                </tr>
            </#list>
            </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">