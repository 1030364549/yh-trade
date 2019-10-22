<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveAccounts"
          data-toggle="validate"  data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
            <input type="hidden" id="agent_num" name="agent_num" value="${(incrementAccounts.AGENT_NUM)!''}">
            <input type="hidden" id="agent_name" name="agent_name" value="${(incrementAccounts.AGENT_NAME)!''}">
            <legend>开通机构名称：<span>${(incrementAccounts.AGENT_NAME)!''}</span></legend>
            <p><label style="width:150px;">请选择账户类型</label></p>
            <p>
                <input type="radio" name="is_currency" readonly id="doc-radio1" data-toggle="icheck" value="1" checked data-label="通用" >
                <label style="width: 50px;"></label>
                <input type="radio" name="is_currency" readonly  id="doc-radio2" data-toggle="icheck" value="2" data-label="不通用">
                <label id="info" style="margin-left:2px" />
            </p>
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn" type="submit" class="btn-blue">保存</button></li>
    </ul>
</div>
<script language="javascript">

</script>