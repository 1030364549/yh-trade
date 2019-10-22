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
        <div id="search">
        <p>
            &nbsp;&nbsp;&nbsp;<span>机构编号：</span>
            <input type="text"  name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>机构名称：</span>
            <input type="text"  name="agent_name" value="${(p.params.agent_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户编号：</span>
            <input type="text"  name="merno" value="${(p.params.merno)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>商户名称：</span>
            <input type="text"  name="mer_name" value="${(p.params.mer_name)!''}"  class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>交易流水号：</span>
            <input type="text"  name="serial" value="${(p.params.serial)!''}" class="form-control" size="11"> &nbsp;
            &nbsp;&nbsp;&nbsp;<span>系统终端号：</span>
            <input type="text"  name="terno" value="${(p.params.terno)!''}" class="form-control" size="11"> &nbsp;
        </p>
        <p>
            &nbsp;&nbsp;&nbsp;<span>卡类型：</span>&nbsp;&nbsp;
            <select  data-toggle="selectpicker"  name="cardtype"  selectvl="${(p.params.cardtype)!''}"  style="height: 25px;">
                <option value="">请选择</option>
                <option value="0">未知</option>
                <option value="1">借记卡</option>
                <option value="2">贷记卡</option>
                <option value="3">预付费卡</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp; <span>结算类型：</span>&nbsp;&nbsp;
            <select data-toggle="selectpicker"  name="sett_type"  selectvl="${(p.params.sett_type)!''}"  style="height: 25px;">
                <option value="">请选择</option>
                <option value="0">S0</option>
                <option value="1">D1</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp; <span>清算状态：</span>&nbsp;&nbsp;
            <select  data-toggle="selectpicker"  name="au_state"  selectvl="${(p.params.au_state)!''}"  style="height: 25px;">
                <option value="">请选择</option>
                <option value="0">未清</option>
                <option value="1">已清</option>
            </select>
            &nbsp;&nbsp;&nbsp;&nbsp; <span>对账状态：</span>&nbsp;&nbsp;
            <select data-toggle="selectpicker"  name="bi_state"  selectvl="${(p.params.bi_state)!''}"  style="height: 25px;">
                <option value="">请选择</option>
                <option value="0">未对账</option>
                <option value="1">已对账</option>
            </select>
            &nbsp;&nbsp;<span>交易日期(起始-截至)：</span>
            <input type="text" readonly name="start_localdate" value="${(p.params.start_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;-
            <input type="text" readonly name="end_localdate" value="${(p.params.end_localdate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
            <input type="hidden" name="showType" value="1" >&nbsp;
            <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
            <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
        </p>
        </div>
        <div style="margin-top: 1px;height: 25pxheight: 25px">
            <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" onclick="exportExcel(this); return false" width="450" rel="BankExpenditure_exportExcel"><i class="fa fa-edit"></i><span>导出Excel</span></a>&nbsp;&nbsp;</span></div>
        <div class="pull-right"></div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
        <tr>
            <th width="6%">机构编号</th>
            <th width="6%">机构名称</th>
            <th width="6%">商户编号</th>
            <th width="6%">商户名称</th>
            <th width="6%">交易流水号</th>
            <th width="6%">交易类型</th>
            <th width="6%">交易日期</th>
            <th width="6%">交易时间</th>
            <th width="6%">交易卡号</th>
            <th width="6%">交易金额</th>
            <th width="6%">交易结果</th>
            <th width="6%">交易状态</th>
            <th width="6%">终端流水号</th>
            <th width="6%">卡类型</th>
            <th width="6%">结算类型</th>
            <th width="6%">清算状态</th>
            <th width="6%">对账状态</th>
            <th width="6%">交易类型</th>
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
        <td>${(list.SERIAL)!}</td>
        <td>${(list.MSGTYPE)!}</td>
        <td>${(list.LOCALDATE)!}</td>
        <td>${(list.LOCALTIME)!}</td>
        <td>${(list.PAN)!}</td>
        <td>${(list.AMOUNT)!}</td>
        <td>${(list.RC)!}</td>
        <td>
	        <#switch "${(list.STATUS)!}">
	            <#case "-1">撤销<#break>
	            <#case "-2">冲正<#break>
	            <#case "0">成功<#break>
	            <#case "1">补录<#break>
	            <#case "2">失败<#break>
	            <#default>
            </#switch>
        </td>
        <td>${(list.STAN)!}</td>
        <td>
	        <#switch "${(list.CARDTYPE)!}">
	            <#case "0">未知<#break>
	            <#case "1">借记卡<#break>
	            <#case "2">贷记卡<#break>
	            <#case "3">预付费卡<#break>
	            <#default>
            </#switch>
        </td>
        <td>
	        <#switch "${(list.SETT_TYPE)!}">
	            <#case "0">S0<#break>
	            <#case "1">D1<#break>
	            <#default>
            </#switch>
        </td>
        <td>
	        <#switch "${(list.AU_STATE)!}">
	            <#case "0">未清<#break>
	            <#case "1">已清<#break>
	            <#default>
            </#switch>
        </td>
        <td>
	        <#switch "${(list.BI_STATE)!}">
	            <#case "0">未对账<#break>
	            <#case "1">已对账<#break>
	            <#default>
            </#switch>
        </td>
        <td>
	        <#switch "${(list.PHONEPAY)!}">
	            <#case "0">普通交易<#break>
	            <#case "1">手机Pay<#break>
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