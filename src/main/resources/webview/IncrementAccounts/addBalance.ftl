<div class="bjui-pageContent">
    <form id="subb" method="post" action="${baseClass}/saveBalance"
          data-toggle="validate"  data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
            <input type="hidden" id="agent_num" name="agent_num" value="${(incrementAccounts.AGENT_NUM)!''}">
            <input type="hidden" id="agent_name" name="agent_name" value="${(incrementAccounts.AGENT_NAME)!''}">
            <legend>充值机构名称：<span>${(incrementAccounts.AGENT_NAME)!''}</span></legend>
        <#if incrementAccounts.IS_CURRENCY == 1>
            <input type="hidden" id="insurance_type" name="insurance_type" value="3">
        </#if>
        <#if incrementAccounts.IS_CURRENCY == 2>
            <p>
                <label style="width:80px;">请选择充值项</label>
                <input type="radio" name="insurance_type" readonly id="doc-radio1" data-toggle="icheck" value="1" checked data-label="保险余额" >
                <input type="radio" name="insurance_type" readonly  id="doc-radio2" data-toggle="icheck" value="2" data-label="短信余额">
                <label id="info" style="margin-left:2px" />
            </p>
        </#if>
            <p>
                <label style="width:80px;">充值金额</label>
                <input id="amount" type="text" name="amount"  class="required" data-rule="required" placeholder="请输入金额（元）" />
                <label id="info" style="margin-left:2px; width: 100px;" ><label/>
            </p>


            <p>
                <label style="width:80px;">备注:</label>
                <textarea id="note" name="note" style="width:330px;height:100px" tools="simple" data-rule="length(0~100)" placeholder="备注" ></textarea>
            </p>
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button type="button" id="btn" onclick="sub()" class="btn-blue">保存</button></li>
    </ul>
</div>
<script language="javascript">
    function sub() {
        var reg=/^(-)?\d+(\.\d+)?$/;
        var amount = $("#amount").val();
        if(reg.test(amount)){
            $('#subb').submit();
        }else{
            $('#info').html('<span style="color:red">只能输入正负小数或整数</span>');
        }
    }

</script>