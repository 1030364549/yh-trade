<script>
    var haveAgentCount = false;
    var haveAdminCount = false;
    var agentNature = false;
    var accountstatus = false;
    function changeHaveCoat(agent_count,admin_count,agent_nature,incrementaccountstatus) {
        haveAgentCount = false;
        haveAdminCount = false;
        agentNature = false;
        accountstatus = false;
        $("#addAgentRelated").removeAttr("disabled");
        if (agent_count==6){
            haveAgentCount = true;
            $("#addAgentRelated").attr("disabled","true")
            if(admin_count > 0){
                haveAdminCount = true;
            }
        }
        if(agent_nature == 2){
            agentNature = true;
        }
        if(incrementaccountstatus != 1){
            accountstatus = true;
        }

    }
    function checkAgentCount(e) {
        if (!haveAgentCount){
            $(e).alertmsg("error","请先添加成本");
            e.preventDefault();
            e.stopPropagation();
            return false;
        }
    }
    function checkAdminCount(e) {
        if (!haveAgentCount){
            $(e).alertmsg("error","请先添加成本");
            e.preventDefault();
            e.stopPropagation();
            return false;
        }
        if (!haveAdminCount){
            $(e).alertmsg("error","请先配置用户");
            e.preventDefault();
            e.stopPropagation();
            return false;
        }
    }
    function checkAgentNature(e) {
        if(!agentNature){
            $(e).alertmsg("error","请先开通机构");
            e.preventDefault();
            e.stopPropagation();
            return false;
        }
        if(!accountstatus){
            $(e).alertmsg("error","您已开通");
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
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            <span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp; <span>机构状态：</span>&nbsp;&nbsp;
            <select id="agent_nature" data-toggle="selectpicker"  name="agent_nature"  selectvl="${(p.params.agent_nature)!''}"  style="height: 25px;">
                <option value="">请选择</option>
                <option value="2">开通</option>
                <option value="-999">关闭</option>
            </select>
            &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
            <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div style="margin-top: 1px;height: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/agentInfoAdd"  rel="AgentInfo_agentInfoAdd" data-toggle="dialog" data-options="{id:'AgentInfo_agentInfoAdd',title:'添加机构',width:'950',height:'590'}"><i class="fa fa-plus"></i><span>添加机构</span></a></span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}"  href="${base}/AgentRelated/agentRelatedAdd?agent_num={#bjui-selected}" id="addAgentRelated" rel="AgentRelated_agentRelatedAdd" data-toggle="dialog" data-options="{id:'AgentRelated_agentRelatedAdd',title:'添加成本',width:'750',height:'500'}"><i class="fa fa-plus"></i><span>添加成本</span></a></span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${base}/AdminInfo/adminInfoAdd?agent_num={#bjui-selected}" onclick="return checkAgentCount(event)"  rel="AdminInfo_adminInfoAdd" data-toggle="dialog" data-options="{id:'AdminInfo_adminInfoAdd',title:'配置登陆用户',width:'550',height:'350'}"><i class="fa fa-plus"></i><span>配置登陆用户</span></a></span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${base}/AgentInterface/edit?agent_num={#bjui-selected}" rel="AgentInterface_edit" data-toggle="dialog" data-options="{id:'AgentInterface_edit',title:'配置进件接口',width:'350',height:'450'}"><i class="fa fa-plus"></i><span>机构接口配置</span></a></span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/updateAgentType?id={#bjui-selected}"  onclick="return checkAdminCount(event)" data-toggle="doajax"  data-confirm-msg = "确定要变更机构状态么？" rel="AgentInfo_updateAgentType" width="450" ><i class="fa fa-edit"></i><span>开/关机构</span></a>&nbsp;&nbsp;</span>
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${base}/IncrementAccounts/addAccounts?agent_num={#bjui-selected}"  onclick="return checkAgentNature(event)" rel="IncrementAccounts_addAccounts" data-toggle="dialog" data-options="{id:'IncrementAccounts_addAccounts',title:'开通增值服务',width:'330',height:'200'}"><i class="fa fa-plus"></i><span>开通增值服务</span></a>&nbsp;&nbsp;</span>
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
    <th width=12%">机构名称</th>
    <th width="12%">添加人姓名</th>
    <th width="12%">添加日期</th>
    <th width="12%">机构状态</th>
    <th width="10%">增值服务状态</th>
    <th width="12%">操作</th>
   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.AGENT_NUM)!}" onclick="changeHaveCoat('${(list.AGENT_COUNT)!}','${(list.ADMIN_COUNT)!}','${(list.AGENT_NATURE)!}','${(list.INCREMENTACCOUNTSTATUS)!}')" >
        <td>${(list.AGENT_NUM)!}<#switch "${(list.AGENT_COUNT)!}">
                <#case "0"><span style="color: red">[未配置成本]</span><#break>
                <#case "6"><span style="color: blue">[已配置成本]</span><#break>
                <#default><span style="color: red">[未配置成本]</span><#break>
            </#switch>
        </td>
     <td>${(list.AGENT_NAME)!}<#if list.ADMIN_COUNT??><span style="color: blue">[已配置用户]<#else ><span style="color: red">[未配置用户]</span></#if></td>
     <td>${(list.ADD_NAME)!}</td>
     <td>${(list.LOCALDATE)!}</td>
     <td>
	  <#switch "${(list.AGENT_NATURE)!}">
	   <#case "2"><span style="color: blue">开通</span><#break>
	   <#case "9"><span style="color: red">关闭</span><#break>
	   <#default>
	  </#switch>
	 </td>
     <td>
       <#switch "${(list.INCREMENTACCOUNTSTATUS)!}">
           <#case "1"><span style="color: blue">已开通</span><#break>
           <#default><span style="color: red">未开通</span><#break>
       </#switch>
<#--${(list.INCREMENTACCOUNTSTATUS)!}-->
     </td>
     <td>
         <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/agentInfoAdd?agent_num=${(list.AGENT_NUM)!}&ck=0" data-toggle="dialog"  rel="AgentInfo_agentInfoUpdate" data-options="{id:'AgentInfo_agentInfoUpdate',title:'修改机构信息',width:'950',height:'570'}"><i class="fa fa-edit"></i><span>修改机构信息</span></a>&nbsp;&nbsp;</span>
     </td>
    </tr>
   </#list>
   </#if>
  </tbody>
 </table>
</div>
<#include "/common/pages.ftl">
