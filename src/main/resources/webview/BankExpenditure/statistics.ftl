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
        var urls = "${baseClass}/exportStatisticsExcel?"+data;
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
        <div id="search">
            <p>
                &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
                <input type="text"  name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;&nbsp;<span>机构名称：</span>
                <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
                &nbsp;&nbsp;<span>交易日期(起始-截至)：</span>
                <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
                <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
                <input type="hidden" name="showType" value="1" >&nbsp;
                <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
                <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
            </p>
        </div>
        <div style="margin-top: 1px;height: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" onclick="exportExcel(this); return false" width="450" rel="BankExpenditure_exportStatisticsExcel"><i class="fa fa-edit"></i><span>导出Excel</span></a>&nbsp;&nbsp;</span></div>
        </div>
        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
            <tr>
                <th width="12%">交易日期</th>
                <th width="12%">机构编号</th>
                <th width="12%">机构名称</th>
                <th width="12%">交易笔数</th>
                <th width="12%">交易金额</th>
                <th width="12%">标准交易笔数</th>
                <th width="12%">标准交易金额</th>
                <th width="12%">减免交易笔数</th>
                <th width="12%">减免交易金额</th>
                <th width="12%">优惠交易笔数</th>
                <th width="12%">优惠交易金额</th>
                <th width="12%">特殊计费交易笔数</th>
                <th width="12%">特殊计费交易金额</th>
                <th width="12%">借记交易笔数</th>
                <th width="12%">借记交易金额</th>
                <th width="12%">贷记交易笔数</th>
                <th width="12%">贷记交易金额</th>
            </tr>
        </thead>
        <tbody>
            <#if p.results ??>
                <#list p.results as list>
                    <tr>
                        <td>${(list.LOCALDATE)!}</td>
                        <td>${(list.AGENT_NUM)!}</td>
                        <td>${(list.AGENT_NAME)!}</td>
                        <td>${(list.TOTAL)!'0'}</td>
                        <td>${(list.AMOUNT)!'0'}</td>
                        <td>${(list.STANDARD_TOTAL)!'0'}</td>
                        <td>${(list.STANDARD_AMOUNT)!'0'}</td>
                        <td>${(list.REDUCTION_TOTAL)!'0'}</td>
                        <td>${(list.REDUCTION_AMOUNT)!'0'}</td>
                        <td>${(list.DISCOUNT_TOTAL)!'0'}</td>
                        <td>${(list.DISCOUNT_AMOUNT)!'0'}</td>
                        <td>${(list.SPECIAL_TOTAL)!'0'}</td>
                        <td>${(list.SPECIAL_AMOUNT)!'0'}</td>
                        <td>${(list.DEBIT_TOTAL)!'0'}</td>
                        <td>${(list.DEBIT_AMOUNT)!'0'}</td>
                        <td>${(list.CREDIT_TOTAL)!'0'}</td>
                        <td>${(list.CREDIT_AMOUNT)!'0'}</td>
                    </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">