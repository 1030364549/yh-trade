
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls!}" method="post">
        <div class="bjui-searchBar">
	        <p>
	        	<span>添加日期：</span><input type="text" name="add_date" id="add_date" value="${(p.params.add_date)!''}" data-toggle="datepicker" data-pattern="yyyy-MM-dd" readonly class="form-control" size="15" style="width:10px;"> &nbsp;
				<span>商户编号：</span><input type="text" name="merno" id="merno" value="${(p.params.merno)!''}" class="form-control" size="15" maxlength="20"> &nbsp;
                <span>商户流水号：</span><input type="text" name="org_serial" id="org_serial" value="${(p.params.org_serial)!''}" class="form-control" size="15" maxlength="20"> &nbsp;
				&nbsp;<span>商户名称：</span> <input type="text" name="mer_name" id="mer_name" value="${(p.params.mer_name)!''}"  class="form-control" size="15" maxlength="20">
                <span>机构编号：</span><input type="text" name="agent_num" id="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="15" maxlength="20"> &nbsp;
                <span>商户状态：</span>
                <select id="trad_status" data-toggle="selectpicker" name="trad_status" selectvl="${(p.params.trad_status)!''}" style="height: 25px;">
                    <option value="">--请选择--</option>
                    <option value="0">禁用</option>
                    <option value="1">启用</option>
                </select>
                <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
                <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
	        </p>
            <p>
                <#--<span><a class="btn btn-blue" href="${baseClass}/change?merno={#bjui-selected}" data-toggle="doajax"  data-confirm-msg = "确定要变更商户状态么？" rel="Merchant_change" width="450" ><i class="fa fa-edit"></i><span>商户关停/开</span></a>&nbsp;&nbsp;</span>-->
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/getSettlement?merno={#bjui-selected}" data-toggle="dialog" rel="Merchant_getSettlement" data-options="{id:'Merchant_getSettlement',title:'商户结算信息查询',width:'840',mask:'true',height:'380'}"><i class="fa fa-edit"></i><span>商户结算信息查询</span></a>&nbsp;</span>
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" id="merchantSchedule" href="${baseClass}/getMerchantSchedule?merno={#bjui-selected}" data-toggle="dialog" rel="Merchant_getMerchantSchedule" data-options="{id:'Merchant_getMerchantSchedule',title:'商户工商信息查询',width:'840',mask:'true',height:'380'}"><i class="fa fa-edit"></i><span>商户工商信息查询</span></a>&nbsp;</span>
                <span><a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" id="downloadImg" href="${baseClass}/downloadImg?merno={#bjui-selected}" data-toggle="doexport" rel="Merchant_downloadImg" ><i class="fa fa-download"></i><span>下载图片压缩包</span></a>&nbsp;</span>
            </p>
       </div>
    <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
            <tr>
            	<th width="5%">商户编号</th>
                <th width="5%">商户名称</th>
                <th width="5%">商户流水号</th>
                <th width="5%">商户类别</th>
                <th width="5%">法人姓名</th>
                <th width="5%">法人证件类型</th>
                <th width="5%">法人证件类型号码</th>
                <th width="5%">展示手机号</th>
                <th width="5%">商户状态</th>
                <th width="5%">商户所属代理商</th>
                <th width="5%">添加日期</th>
                <th width="5%">添加时间</th>
                <th width="5%">机构原商户号</th>
                <th width="5%">商户结算周期</th>
                <th width="5%">商户上传图片</th>
            </tr>
        </thead>
        <tbody>
        <#if p.results ??>
          <#list p.results as list>
           <tr data-id="${list.MERNO}" onclick="checkButton('${(list.MERCHANT_SCHEDULE)!}','${(list.PICTURE)!}')">
               <td >${(list.MERNO)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.MER_NAME)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.ORG_SERIAL)!'<span style="color:gray">暂无</span>'}</td>
               <td >
                   <#switch "${(list.MER_CATEGORY)!}">
                       <#case "0">标准类<#break>
                       <#case "1">优惠类<#break>
                       <#case "2">减免类<#break>
                       <#default>
                   </#switch>
               </td>
               <td >${(list.LEGAL_NAME)!'<span style="color:gray">暂无</span>'}</td>
               <td >
                   <#switch "${(list.LEGAL_CER)!}">
                       <#case "0">身份证<#break>
                       <#case "1">护照<#break>
                       <#default>
                   </#switch>
               </td>
               <td >${(list.SHOW_LEGALCERNO)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.SHOW_LINKPHONE)!'<span style="color:gray">暂无</span>'}</td>
               <td >
                   <#switch "${(list.TRAD_STATUS)!}">
                       <#case "0"><span style="color: red">禁用</span><#break>
                       <#case "1"><span style="color: blue">启用</span><#break>
                       <#default>
                   </#switch>
               </td>
               <td >${(list.AGENT_NO)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.ADD_DATE)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.ADD_TIME)!'<span style="color:gray">暂无</span>'}</td>
               <td >${(list.ORG_MERNO)!'<span style="color:gray">暂无</span>'}</td>
               <td >
                   <#switch "${(list.SETTLE_CYCLE)!}">
                       <#case "0">T0<#break>
                       <#case "1">D1<#break>
                       <#default>
                   </#switch>
               </td>
               <td >
                   <#switch "${(list.PICTURE)!}">
                       <#case "0"><span style="color: red">未上传</span><#break>
                       <#case "1"><span style="color: blue">已上传</span><#break>
                       <#default>
                   </#switch>
               </td>
            </tr>
         </#list>
         </#if>
        </tbody>
    </table>
</div>
</div>
<#include "/common/pages.ftl">
<script>
    function checkButton(schedule,picture){
        //查询工商信息按钮
        if (schedule == null || schedule == ''){
            $("#merchantSchedule").attr("disabled","disabled");
        }else{
            $("#merchantSchedule").removeAttr("disabled");
        }
        //下载图片按钮
        if(picture == 0){
            $("#downloadImg").attr("disabled","disabled");
        }else{
            $("#downloadImg").removeAttr("disabled");
        }
    }
</script>
