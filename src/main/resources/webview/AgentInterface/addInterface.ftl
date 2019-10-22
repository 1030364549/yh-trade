<script language="javascript">

</script>
<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveInterface" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset style="margin-top: 10px;">
			<legend>允许通行接口:</legend>
            <p>
                <label style="width:80px;">接口Text:</label>
                <input name="text" type="text" <#if result ??>value="${(result.TEXT)!'' }"</#if> data-rule="required,length(0~100)" class="required" placeholder="接口Text" />
            </p>
            <p>
                <label style="width:80px;">接口Value:</label>
                <input name="value" type="text" <#if result ??>value="${(result.VALUE)!'' }"</#if>data-rule="required,length(0~100)" class="required" placeholder="接口Value" />
            </p>
            <input type="hidden" name ="id" <#if result ??>value="${(result.ID)!'' }"</#if>>
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn" type="submit" <#if agentinfo??><#if agentinfo.STANDARD_RATED??> style="display:none;" </#if></#if> class="btn-blue">保存</button></li>
    </ul>
</div>
