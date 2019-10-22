
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/updateprofitstatus"
		  data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<table>
				<p>
					<label style="width:90px;">分润结算状态:</label>


					<select id="profitstatus" data-toggle="selectpicker" disabled
																	 name="profitstatus" selectvl="${(profitstatus)!''}"
																	   style="height: 25px;" >
						<option value="0">正常分润</option>
						<option value="1">关闭本级分润</option>
					</select> <label id="info" style="margin-left:20px" /><p>

				<input type="hidden" name="profitstatus" value="${(profitstatus)!''}">
				<p>
					<label style="width:90px;">修改原因:</label>
					<textarea cols="30" rows="4" data-toggle="autoheight" name="note" value="${(merchinfo.note)!'' }" data-rule="required" class="required" ></textarea>
					<label id="info" style="margin-left:20px" />
				</p>
					<input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!'' }"/>


<#--
					<input id="profitstatus" type="hidden" name="profitstatus" value="${(trad_status)!'' }"/>
-->
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