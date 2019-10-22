<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${urls}"
		method="post">
		<div class="bjui-searchBar">
			<span>代理商编号：</span> <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="15">
			&nbsp; <input type="hidden" name="showType" value="1">
			&nbsp;  <input type="hidden" name="parentlevel" value="${parentlevel}">
			<button type="submit" class="btn-blue" data-icon="search">查询</button>
			&nbsp; <!-- <TracingPrintStream class="btn btn-orange" href="javascript:;"
				onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</TracingPrintStream>&nbsp; -->
			<div style="margin-top: 1px;"></div>
			<div class="pull-right"></div>
		</div>
		<#include "/common/pageForm.ftl">
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top" data-toggle="tablefixed">
		<thead>
			<tr>
				<th width="33%">代理商编号</th>
				<th width="33%">代理商名称</th>
				<th width="34">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if p.results ??> <#list p.results as list>
			<tr data-id="AGENT_NUM=${(list.AGENT_NUM)!}">
				<td>${(list.AGENT_NUM)!}</td>
				<td>${(list.AGENT_NAME)!}</td>
				<td><button class="btn-green" data-toggle="lookupback" data-args="{agent_num:'${(list.AGENT_NUM)!}', agent_name:'${(list.AGENT_NAME)!}'}" class="btn btn-blue"  data-icon="check">选择</button></td>
			</tr>
			</#list> </#if>
		</tbody>
	</table>
</div>
</div>
<#include "/common/pages.ftl">
