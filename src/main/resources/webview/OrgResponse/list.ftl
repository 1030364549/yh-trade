<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <p>
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>系统流水号：</span>
            <input type="text" name="serial" value="${(p.params.serial)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户编号：</span>
            <input type="text" name="mer_no" value="${(p.params.mer_no)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户名称：</span>
            <input type="text" name="mer_name" value="${(p.params.mer_name)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>响应码：</span>
            <input type="text" name="code" value="${(p.params.code)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
            <input type="text" readonly name="start_createdate" value="${(p.params.start_createdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_createdate" value="${(p.params.end_createdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            <input type="hidden" name="showType" value="1" >&nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="12%">系统流水号</th>
            <th width="12%">商户编号</th>
            <th width="12%">商户名称</th>
            <th width="12%">商户类别</th>
            <th width="12%">机构编号</th>
            <th width="12%">添加日期</th>
            <th width="12%">添加时间</th>
            <th width="12%">响应码</th>
            <th width="12%">响应码解释</th>
            <th width="12%">请求接口方式</th>
        </tr>
        </thead>
        <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.ID)!}" onclick="btnValide(${(list.SM_STATUS)!''})" >
        <td>${(list.SERIAL)!}</td>
        <td>${(list.MER_NO)!}</td>
        <td>${(list.MER_NAME)!}</td>
        <td >
		    <#switch "${(list.MER_CATEGORY)!}">
		    	<#case "0"><span>标准类</span><#break>
		    	<#case "1"><span>优惠类</span><#break>
		    	<#case "2"><span>减免类</span><#break>
		        <#default>
            </#switch>
        </td>
        <td>${(list.AGENT_NUM)!}</td>
        <td>${(list.ADD_DATE)!}</td>
        <td>${(list.ADD_TIME)!}</td>
        <td>${(list.CODE)!}</td>
        <td>${(list.CODEMSG)!}</td>
        <td >
		    <#switch "${(list.MT)!}">
		    	<#case "0"><span>申请</span><#break>
		    	<#case "1"><span>变更</span><#break>
		        <#default>
            </#switch>
        </td>
    </tr>
   </#list>
   </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">
<script>
    function btnValide(status) {
        if (status==0){
            $("#editBtn").removeAttr("disabled");
            $("#cancelBtn").removeAttr("disabled");
        }else{
            $("#editBtn").attr("disabled","disabled");
            $("#cancelBtn").attr("disabled","disabled");
        }
    }
</script>