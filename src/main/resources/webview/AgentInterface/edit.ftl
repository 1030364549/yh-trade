<script language="javascript">

</script>
<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveAgentInterface" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
            <legend>机构信息:</legend>
            <p>
                <label style="width:80px;">机构编号:</label>
                <input id="agent_num" type="text" value="${(result.AGENT_NUM)!'' }" readonly name="agent_num" data-rule="required,length(0~50)" class="required" placeholder="机构编号" />
            </p>
            <p>
                <label style="width:80px;">机构名称:</label>
                <input id="agent_name" type="text" value="${(result.AGENT_NAME)!'' }" readonly name="agent_name" data-rule="required,length(0~60)" class="required" placeholder="机构名称" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
		</fieldset>
		<fieldset style="margin-top: 10px;">
			<legend>允许通行接口:</legend>
            <table>
            <#if allowInterface ??>
                <#list allowInterface as list>
                    <tr>
                        <td style="width: 70px"><input type="checkbox" name="allow_interface[]" data-toggle="icheck" <#if result.ALLOW_INTERFACE ??> <#if result.ALLOW_INTERFACE?contains("#"+list.VALUE+"#")>checked</#if> </#if> value="${(list.VALUE)!}" data-label="${(list.TEXT)!}"></td>                        <label id="info" style="margin-left:5px;" ></label>
                    </tr>
                </#list>
            </#if>
            </table>
            <p>
                <label style="width:80px;">机构秘钥:</label>
                <input name="agent_key" type="text" value="${(result.AGENT_KEY)!'' }" <#if result.AGENT_KEY ??> readonly</#if> data-rule="required,length(0~100)" class="required" placeholder="机构秘钥" />
            </p>
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <span><a class="btn btn-blue" carte_id="${(carte_id)!'0'}" href="${base}/AgentInterface/addInterface" id="addInterface" rel="AgentInterface_addInterface" data-toggle="dialog" data-options="{id:'AgentInterface_addInterface',title:'添加通行接口',width:'350',height:'300'}"><i class="fa fa-plus"></i><span>添加通行接口</span></a></span>
        <li><button id="btn" type="submit" <#if agentinfo??><#if agentinfo.STANDARD_RATED??> style="display:none;" </#if></#if> class="btn-blue">保存</button></li>
    </ul>
</div>
