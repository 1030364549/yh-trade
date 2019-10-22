<script type="text/javascript">
    $(function(){
        $("[name = type]").val("${(p.params.type)!''}");
        $("[name = insurance_type]").val("${(p.params.insurance_type)!''}");
    });
    function exportExcel_statistics(a){
        var flag = false;
        var data = "";
        var i = 0;
        $('#search_statistics input').each(function(){
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
            $('#search_statistics').nextAll("input").each(function(){
                if($(this).val() != null && $(this).val() !=''){
                    if (i > 0){
                        data += "&";
                    }
                    data = data + $(this).attr("name")+"="+$(this).val();
                    i++;
                }
            });
        }
        var urls = "${baseClass}/exportExcelStatistics?"+data;
        $(a).attr("href", urls);
        $.fileDownload($(a).attr('href'), {
            /*data:$("#pagerForm").serialize(),*/
            failCallback: function (responseHtml, url) {
                console.info(responseHtml);
            }
        });
    }
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <p id="search_statistics">
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            <span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;<span>添加时间(起始-截至)：</span>
            <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>类型：</span>
            <select class="form-control" name="type" style="width:109px">
                <option value="">请选择</option>
                <option value="1">充值</option>
                <option value="2">消费</option>
            </select>
            &nbsp;&nbsp;<span>服务类型：</span>
            <select class="form-control" name="insurance_type" style="width: 109px">
                <option value="">请选择</option>
                <option value="1">保险</option>
                <option value="2">短信</option>
                <option value="3">通用</option>
            </select> &nbsp;&nbsp;&nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        <div style="margin-top: 1px;height: 25pxheight: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" onclick="exportExcel_statistics(this); return false" width="450" rel="IncrementLog_exportExcel"><i class="fa fa-edit"></i><span>导出Excel</span></a>&nbsp;&nbsp;</span>
        </div>
        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="5%">机构编号</th>
            <th width="5%">机构名称</th>
            <th width="5%">总金额</th>
            <th width=5%">类型</th>
            <th width="5%">增值服务类型</th>
            <th width="5%">创建日期</th>
            <th width="5%">总数量</th>
        </tr>
        </thead>
        <tbody>

        <#if p ??>
            <#if p.results ??>
                <#list p.results as list>
                <tr>
                    <td>${(list.AGENT_NUM)!}</td>
                    <td>${(list.AGENT_NAME)!}</td>
                    <td>${(list.SUMAMOUNT)!}</td>
                    <td>
                        <#switch "${(list.TYPE)!}">
                            <#case "1"><span style="color: blue">充值</span><#break>
                            <#case "2"><span style="color: blue">消费</span><#break>
                            <#default>
                        </#switch>
                    </td>
                    <td>
                        <#switch "${(list.INSURANCE_TYPE)!}">
                            <#case "1"><span style="color: blue">保险</span><#break>
                            <#case "2"><span style="color: blue">短信</span><#break>
                            <#case "3"><span style="color: blue">通用</span><#break>
                            <#default>
                        </#switch>
                    </td>
                    <td>${(list.CREATE_DATE)!}</td>
                    <td>${(list.COUNTNUM)!}</td>
                </tr>
                </#list>
            </#if>
        </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">