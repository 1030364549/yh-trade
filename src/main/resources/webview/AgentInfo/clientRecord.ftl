<#include "/common/pageForm.ftl">
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
            <tr>
				<th width="7%">代理商编号</th>
				<th width="30%">备注说明</th>
				<th width="7%">操作人</th>
				<th width="10%">操作时间</th>
            </tr>
        </thead>
        <tbody>
            <#if p.results ??>
            <#list p.results as list>
				<tr data-id="id=${(list.ID)!}" > 
					<td >${(list.CLIENTNO)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.NOTE)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.OPERATOR_NAME)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.OPERATOR_TIME)!'<span style="color:gray">暂无</span>'}</td>
				</tr>
			  </#list>
			</#if>
        </tbody>
    </table>
</div>
</div>
<#include "/common/pages.ftl">