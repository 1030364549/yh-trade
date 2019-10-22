<script>
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <#--<div class="bjui-searchBar">-->
            <p>
                &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
                <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
                <span>机构名称：</span>
                <input type="text" name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
                <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
                <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;

                <input type="hidden" name="showType" value="1" >&nbsp;

                <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
                <a class="btn btn-orange" href="javascript:void(0);" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
            </p>
            <div style="margin-top: 1px;height: 25px">
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}"  href="${base}/AgentRelated/agentRelatedAdd?agent_num={#bjui-selected}" rel="AgentRelated_agentRelatedAdd" data-toggle="dialog" data-options="{id:'AgentRelated_agentRelatedAdd',title:'查看成本',width:'750',height:'500'}"><i class="fa fa-plus"></i><span>查看成本</span></a></span>
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
    <th width="7%">机构编号</th>
    <th width=12%">机构名称</th>
    <th width="12%">添加日期</th>
    <th width="12%">添加人姓名</th>
    <th width="12%">操作</th>
   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.AGENT_NUM)!}">
     <td>${(list.AGENT_NUM)!}</td>
     <td>${(list.AGENT_NAME)!}</td>
     <td>${(list.LOCALDATE)!}</td>
     <td>${(list.ADD_NAME)!}</td>
     <td>
         <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/agentRelatedUpd?agent_num=${(list.AGENT_NUM)!}&ck=0" data-toggle="dialog"  rel="AgentRelated_agentRelatedUpd" data-options="{id:'AgentRelated_agentRelatedUpd',title:'修改成本',width:'750',height:'500'}"><i class="fa fa-edit"></i><span>修改成本</span></a>&nbsp;&nbsp;</span>
     </td>
    </tr>
   </#list>
   </#if>
  </tbody>
 </table>
</div>
<#include "/common/pages.ftl">