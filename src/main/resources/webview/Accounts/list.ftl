<div class="bjui-pageHeader">
 <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
  <div class="bjui-searchBar">
	  <p>
   <input type="hidden" name="showType" value="1" class="form-control" size="15"> &nbsp;
   <span>开户行名称：</span>
   <input type="text" name="bank_name" value="${(p.params.bank_name)!''}" class="form-control" size="15"> &nbsp;
   <span>结算人：</span>
   <input type="text" name="screen_name" value="${(p.params.screen_name)!''}" class="form-control" size="15"> &nbsp;
   <span>结算卡号(后四位)：</span>
   <input type="text" name="screen_num" value="${(p.params.screen_num)!''}" data-rule="digits"  maxlength="4" class="form-control" size="15"> &nbsp;
   <span>商户/代理商编号：</span>
   <input type="text" name="seq_id" value="${(p.params.seq_id)!''}" class="form-control" size="15"> &nbsp;
	  </p>
	  <p>
	&nbsp;&nbsp;<span>手机号码：</span>&nbsp;&nbsp;&nbsp;
	  <input type="text" name="link_phone" value="${(p.params.link_phone)!''}" class="form-control" size="15" placeholder="精准搜索" style="font-size:12px;"> &nbsp;
	&nbsp;&nbsp;<span>手机号码：</span>&nbsp;&nbsp;&nbsp;
		  <input type="text" name="show_resphone" value="${(p.params.show_resphone)!''}" class="form-control" size="15" placeholder="模糊搜索" style="font-size:12px;"> &nbsp;
   <span>账户类型:</span>
	<select id="nature" data-toggle="selectpicker" name="nature" selectvl="${(p.params.nature)!''}" style="height: 25px;">
	  <option value="">--请选择--</option>
	  <option value="1">对公账户</option>
	  <option value="2">对私账户</option>
   </select> <label id="info" style="margin-left:20px" />
  <span>商户标识:</span>
  <select id="da_marker" data-toggle="selectpicker" name="da_marker" selectvl="${(p.params.da_marker)!''}" style="height: 25px;">
	  <option value="">--请选择--</option>
	  <option value="2">商户</option>
	  <option value="1">代理商</option>
  </select> <label id="info" style="margin-left:20px" />
   <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
   <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
	  </p>
   <div style="margin-top: 1px;">
<#--
    <span><a class="btn btn-blue" href="${baseClass}/accountRecord?{#bjui-selected}" data-toggle="dialog"  rel="BaseinfoClass_allaudit" data-options="{id:'dialog',title:'信息操作记录',width:'1200',height:'600'}"><i class="fa fa-edit"></i><span>操作记录</span></a>&nbsp;&nbsp;</span>
-->
<#--
    <span><TracingPrintStream class="btn btn-blue" href="${baseClass}/allauditView?{#bjui-selected}" data-toggle="dialog"  rel="BaseinfoClass_allaudit" data-options="{id:'dialog',title:'开通全额/取消全额',width:'570',height:'295'}"><i class="fa fa-edit"></i><span>开通全额/取消全额</span></TracingPrintStream>&nbsp;&nbsp;</span>
-->
<#--
    <span><TracingPrintStream class="btn btn-blue" href="${baseClass}/frozenView?{#bjui-selected}"  data-toggle="dialog" rel="BaseinfoClass_frozen" data-options="{id:'dialog',title:'冻结/解冻',width:'570',height:'295'}"><i class="fa fa-edit"></i><span>冻结/解冻</span></TracingPrintStream>&nbsp;&nbsp;</span>
-->
    <span><a class="btn btn-red" href="${baseClass}/add?{#bjui-selected}" data-toggle="dialog"  rel="BaseinfoClass_allaudit" data-options="{id:'dialog',title:'变更账户信息',width:'800',height:'350'}"><i class="fa fa-edit"></i><span>变更账户信息</span></a>&nbsp;&nbsp;</span>
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
				<th width="10%">开户行名称</th>
				<th width="6%">结算人</th>
				<th width="10%">结算账号</th>
				<th width="10%">总行名称</th>
				<th width="10%">支付系统行号</th>
				<th width="7%">银行预留手机号</th>
				<th width="7%">商户/代理商编号</th>
				<th width="7%">身份证号</th>
				<th width="5%">账户标识</th>
				<th width="5%">账户类型</th>
				<th width="5%">起始结算金额</th>
				<th width="5%">账户开户地</th>
				<th width="10%">备注</th>
				<th width="5%">添加人</th>
				<th width="10%">添加时间</th>
            </tr>
        </thead>
        <tbody>
            <#if p.results ??>
            <#list p.results as list>
				<tr data-id="seq_id=${(list.SEQ_ID)!}&da_marker=${(list.DA_MARKER)!}" >
					<td >${(list.BANK_NAME)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.SCREEN_NAME)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.SHOW_SCREENNUM)!'<span style="color:gray">暂无</span>'}</td>
				    <td >${(list.BANK_HEADNAME)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.BANK_NUM)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.SHOW_RESPHONE)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.SEQ_ID)!'<span style="color:gray">暂无</span>'}</td>
				    <td >${(list.SHOW_SCREENIDNUM)!'<span style="color:gray">暂无</span>'}</td>
					<td >
						<#switch "${(list.DA_MARKER)!}">
							<#case "1"><span style="color: black">代理商</span><#break>
							<#case "2"><span style="color: blue">商户</span><#break>
						    <#default>
						</#switch>
					</td>
					<td >
						<#switch "${(list.NATURE)!}">
							<#case "1"><span style="color: red">对公</span><#break>
							<#case "2"><span style="color: blue">对私</span><#break>
						    <#default>
						</#switch>
					</td>
					<td >${(list.ACMONEY)!'<span style="color:gray">暂无</span>'}</td>
				    <td >${(list.BANK_ADDRESS)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.NOTE)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.CREATEMAN)!'<span style="color:gray">暂无</span>'}</td>
					<td >${(list.CREATEDATE)!'<span style="color:gray">暂无</span>'}</td>
				</tr>
			  </#list>
			</#if>
        </tbody>
    </table>
</div>
<#include "/common/pages.ftl">