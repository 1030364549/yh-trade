<script>
    function exportExcel(a){
        var flag = false;
        var data = "";
        var i = 0;
        $('#search input').each(function(){
            if($(this).val() != null && $(this).val() !=''){
                flag = true;
                if (i > 0){
                    data += "&";
                }
                data = data + $(this).attr("name")+"="+$(this).val();
                i++;
            }
        });
        if (!flag){
            $('#search').nextAll("input").each(function(){
                if($(this).val() != null && $(this).val() !=''){
                    if (i > 0){
                        data += "&";
                    }
                    data = data + $(this).attr("name")+"="+$(this).val();
                    i++;
                }
            });
        }
        var urls = "${baseClass}/exportExcel?"+data;
        $(a).attr("href", urls);
        $.fileDownload($(a).attr('href'), {
            failCallback: function (responseHtml, url) {
                console.info(responseHtml);
            }
        })
    }
</script>

<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <p>
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            <span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
            <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div style="margin-top: 1px;height: 25pxheight: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" onclick="exportExcel(this); return false" width="450" rel="BankExpenditure_exportExcel"><i class="fa fa-edit"></i><span>导出Excel</span></a>&nbsp;&nbsp;</span></div>
        <div class="pull-right"></div>

        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
 <table class="table table-bordered table-hover table-striped table-top">
  <thead>
   <tr>
    <th width="7%">记录ID</th>
    <th width="7%">机构编号</th>
    <th width="7%">机构名称</th>
    <th width="12%">金额</th>
    <th width=12%">类型</th>
    <th width="12%">增值服务类型</th>
    <th width="12%">创建日期</th>
    <th width="12%">创建时间</th>
    <th width="12%">创建人</th>
    <th width="12%">备注</th>

   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.AGENT_NUM)!}" >
     <td>${(list.INCREMENT_LOG_ID)!}</td>
     <td>${(list.AGENT_NUM)!}</td>
     <td>${(list.AGENT_NAME)!}</td>
     <td>${(list.AMOUNT)!}</td>
        <td>
            <#switch "${(list.TYPE)!}">
                <#case "1"><span style="color: red">充值</span><#break>
                <#case "2"><span style="color: blue">消费</span><#break>
                <#default><span style="color: red">空</span><#break>
            </#switch>
        </td>
        <td>
            <#switch "${(list.INSURANCE_TYPE)!}">
                <#case "1"><span style="color: red">保险</span><#break>
                <#case "2"><span style="color: blue">短信</span><#break>
                <#case "3"><span style="color: black">通用</span><#break>
                <#default><span style="color: red">空</span><#break>
            </#switch>
        </td>
     <td>${(list.CREATE_DATE)!}</td>
     <td>${(list.CREATE_TIME)!}</td>
     <td>${(list.CREATE_NAME)!}</td>
     <td>${(list.NOTE)!}</td>
     <td>
	  <#switch "${(list.AGENT_NATURE)!}">
	   <#case "2"><span style="color: blue">开通</span><#break>
	   <#case "9"><span style="color: red">关闭</span><#break>
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
