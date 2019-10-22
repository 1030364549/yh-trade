<script>
    <#--href="${baseClass}/exportExcel"-->
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
        <p id="search">
            &nbsp;&nbsp;&nbsp;<span>终端编号：</span>
            <input type="text" name="posno" value="${(p.params.posno)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>机构名称：</span>
            <input type="text" name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户编号：</span>
            <input type="text" name="merno" value="${(p.params.merno)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户名称：</span>
            <input type="text" name="mer_name" value="${(p.params.mer_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
            <input type="text" readonly name="start_createdate" value="${(p.params.start_createdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_createdate" value="${(p.params.end_createdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div style="margin-top: 1px;height: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" onclick="exportExcel(this); return false" width="450" rel="Terminal_exportExcel" ><i class="fa fa-edit"></i><span>导出Excel</span></a>&nbsp;&nbsp;</span>
        </div>
        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="12%">机构编号</th>
            <th width="12%">机构名称</th>
            <th width="12%">商户编号</th>
            <th width="12%">商户名称</th>
            <th width="12%">终端编号</th>
            <th width="12%">添加日期</th>
        </tr>
        </thead>
        <tbody>
   <#if p.results ??>
   <#list p.results as list>
    <tr data-id="${(list.ID)!}">
        <td>${(list.AGENT_NUM)!}</td>
        <td>${(list.AGENT_NAME)!}</td>
        <td>${(list.MERNO)!}</td>
        <td>${(list.MER_NAME)!}</td>
        <td>${(list.POSNO)!}</td>
        <td>${(list.CREATEDATE)!}</td>
    </tr>
   </#list>
   </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">
