
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/updateagentarea"
		  data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<table>
				<p>
					<label style="width:90px;">代理商授权地区:</label>
					<select name="province" id="province" data-toggle="selectpicker"  multiple  data-width="450" >
						<option value="">请选择</option>
						<#if areaMap ??>
							<#list areaMap as ms>
								<option value="${(ms.AREA_ID)!}">
								${(ms.AREA_NAME)!}
								</option>
							</#list>
						</#if>
					</select>
				<p>
					<input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!'' }"/>
					<input id="provinces" type="hidden" name="provinces" />
			</table>
		</fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
		<li><button id="btn" type="submit" class="btn-blue">确定</button></li>
	</ul>
</div>
<script language="javascript">
    $("#province").change(function(){
        $("input[name=provinces]").val($("#province").val())
    })
</script>