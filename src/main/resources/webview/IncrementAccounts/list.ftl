<script>

</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <p>
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            <span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div style="margin-top: 1px;height: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${base}/IncrementAccounts/addBalance?agent_num={#bjui-selected}"  onclick="" rel="IncrementAccounts_addBalance" data-toggle="dialog" data-options="{id:'IncrementAccounts_addBalance',title:'充值增值服务',width:'550',height:'350'}"><i class="fa fa-edit"></i><span>充值增值余额</span></a>&nbsp;&nbsp;</span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}"  href="${baseClass}/modifyAccounts?agent_num={#bjui-selected}"   data-toggle="doajax"  data-confirm-msg = "确定要变更账户状态么？" rel="IncrementAccounts_modifyAccounts" width="450" ><i class="fa fa-edit"></i><span>修改为通用账户</span></a>&nbsp;&nbsp;</span>
        </div>

        <div class="pull-right"></div>
    <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="7%">机构编号</th>
            <th width="7%">机构名称</th>
            <th width=7%">保险账户剩余金额(元)</th>
            <th width="7%">短信账户剩余金额(元)</th>
            <th width="7%">通用账户剩余金额(元)</th>
            <th width="7%">账户是否通用</th>
        </tr>
        </thead>
        <tbody>
        <#if p.results ??>
            <#list p.results as list>
            <tr data-id="${(list.AGENT_NUM)!}">
                <td>${(list.AGENT_NUM)!}</td>
                <td>${(list.AGENT_NAME)!}</td>
                <td>
                    <#if "${(list.IS_CURRENCY)!}" == "2">${(list.INSURANCE_AMOUNT)!}<#else >无</#if>
                </td>
                <td>
                    <#if "${(list.IS_CURRENCY)!}" == "2">${(list.MESSAGE_AMOUNT)!}<#else >无</#if>
                    </td>
                <td>
                    <#if "${(list.IS_CURRENCY)!}" == "1">${(list.AMOUNT)!}<#else >无</#if>
                    </td>
                <td><#switch "${(list.IS_CURRENCY)!}">
                    <#case "1"><span style="color: blue">通用</span><#break>
                    <#case "2"><span style="color: red">不通用</span><#break>
                    <#default>
                </#switch></td>

            </tr>
            </#list>
        </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">