<script>
</script>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <p>
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            <span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;

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
    <th width="7%">机构编码</th>
    <th width="12%">机构名称</th>
    <th width=12%">保险账户剩余金额</th>
    <th width="12%">短信账户剩余金额</th>
    <th width="12%">账户剩余金额</th>
    <th width="12%">账户是否通用</th>

   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.AGENT_NUM)!}" >
     <td>${(list.AGENT_NUM)!}</td>
     <td>${(list.AGENT_NAME)!}</td>
     <td>
         <#if "${(list.IS_CURRENCY)!}"=="1">
             <span style="color: red">无</span>
         <#else>
             <span style="color: blue">${(list.INSURANCE_AMOUNT)!}</span>
         </#if>
     </td>
     <td>
        <#if "${(list.IS_CURRENCY)!}"=="1">
            <span style="color: red">无</span>
        <#else>
            <span style="color: blue">${(list.MESSAGE_AMOUNT)!}</span>
        </#if>
     </td>
     <td>${(list.AMOUNT)!}</td>
     <td>
            <#switch "${(list.IS_CURRENCY)!}">
            <#case "1"><span style="color: red">通用</span><#break>
            <#case "2"><span style="color: blue">不通用</span><#break>
            <#default><span style="color: red">不通用</span><#break>
            </#switch>
     </td>
     <#--<td>
         <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/agentInfoAdd?agent_num=${(list.AGENT_NUM)!}&ck=0" data-toggle="dialog"  rel="AgentInfo_agentInfoUpdate" data-options="{id:'AgentInfo_agentInfoUpdate',title:'修改机构信息',width:'950',height:'570'}"><i class="fa fa-edit"></a>&nbsp;&nbsp;</span>
     </td>-->
    </tr>
   </#list>
   </#if>
  </tbody>
 </table>
</div>
<#include "/common/pages.ftl">
