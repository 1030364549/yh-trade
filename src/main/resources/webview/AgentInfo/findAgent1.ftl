<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${url}"
		method="post">
		<div class="bjui-searchBar">
			<span>代理商编号：</span> <input type="text" name="agent_num"
				value="${(p.params.agent_num)!''}" class="form-control" size="15">
			&nbsp; <input type="hidden" name="showType" value="1">
			&nbsp;
			<button type="submit" class="btn-blue" data-icon="search">查询</button>
			<!-- &nbsp; <TracingPrintStream class="btn btn-orange" href="javascript:;"
				onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</TracingPrintStream>&nbsp; -->
			<div style="margin-top: 1px;"></div>
			<div class="pull-right"></div>
		</div>
		<#include "/common/pageForm.ftl">
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-striped table-top">
		<thead>
			<tr>
				<th width="6%">代理商编号</th>
				<th width="10%">代理商名称</th>
				<th width="7%">区域</th>
				<th width="5%">法人姓名</th>
				<th width="10%">身份证</th>
				<th width="7%">联系电话</th>
				<th width="4%">状态</th>
				<th width="4%">等级</th>
				<th width="6%">所属代理商</th>
				<th width="4%">添加人</th>
				<th width="6%">添加日期</th>
				<th width="5%">添加时间</th>
				<th width="12%">备注</th>
				<th width="10">操作</th>
			</tr>
		</thead>
		<tbody>
			<#if p.results ??> <#list p.results as list>
			<tr data-id="AGENT_NUM=${(list.AGENT_NUM)!}">
				<td>${(list.AGENT_NUM)!}</td>
				<td>${(list.AGENT_NAME)!}</td>
				<td>${(list.AGENT_AREA)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.COMMISSARY)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.IDENTITY_NUM)!'<span style="color:gray">无</span>'}
				</td>
				<td>${(list.COR_PHONE)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.AGENT_STATUS)!'<span style="color:gray">无</span>'}
				</td>
				<td>${(list.AGENT_LEVEL)!'<span style="color:gray">无</span>'}
				</td>
				<td>${(list.BELONG_AGENT)!'<span style="color:gray">无</span>'}
				</td>
				<td>${(list.ADD_NAME)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.LOCALDATE)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.LOCALTIME)!'<span style="color:gray">无</span>'}</td>
				<td>${(list.NOTE)!'<span style="color:gray">无</span>'}</td>
				<td><button class="btn-green" data-toggle="lookupback" data-args="{agent_num:'${(list.AGENT_NUM)!}', agent_name:'${(list.AGENT_NAME)!}'}" class="btn btn-blue"  data-icon="check">选择</button></td>
			</tr>
			</#list> </#if>
		</tbody>
	</table>
</div>
</div>
<#include "/common/pages.ftl">
