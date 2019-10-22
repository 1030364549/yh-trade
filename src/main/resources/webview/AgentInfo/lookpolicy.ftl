<div class="bjui-pageContent tableContent">
 <table class="table table-bordered table-hover table-striped table-top">
  <thead>
   <tr>
       <th width="6%">代理商编号</th>
       <th width="7%">结算点</th>
       <th width="10%">卡类型</th>
       <th width="10%">标准结算点</th>
       <th width="7%">优惠结算点</th>
       <th width="5%">云闪付结算点</th>
       <th width="5%">借记次日封顶结算点</th>
   </tr>
  </thead>
  <tbody>
   <#if list ??>
    <#list list as list>
	 <tr>
        <td >${(list.AGENT_NUM)!}</td>
        <td >
        <#if list.SETTLE_NO=='0'>
            KS
        <#else>
            CR
        </#if>
        </td>
        <td >
        <#if list.CARD_TYPE=='1'>
            借记卡
        <#else>
            贷记卡
        </#if>
        </td>
        <td >${(list.POLICY_NO)!}%</td>
        <td >${(list.Y_POLICY_NO)!'<span style="color:gray">无</span>'}%</td>
        <td >${(list.YSF_POLICY_NO)!'<span style="color:gray">无</span>'}%</td>
        <td >${(list.CAPP_NO)!'<span style="color:gray">无</span>'}</td>
     </tr>
	</#list>
   </#if>
  </tbody>
 </table>
</div>
