
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/updateAgentStatus"
		  data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<table>
				<p>
					<label style="width:90px;">代理商状态:</label> <select id="agent_status" data-toggle="selectpicker" disabled
																	 name="agent_status" selectvl="${(agent_status)!''}"
																	 style="height: 25px;" >
						<option value="0">正常</option>
						<option value="6">禁用</option>
					</select> <label id="info" style="margin-left:20px" />

					<input type="hidden" name="agent_status" value="${(agent_status)!''}">
				</p>
				<p>
					<label style="width:90px;">修改原因:</label>
					<textarea cols="30" rows="4" data-toggle="autoheight" name="note" value="${(merchinfo.note)!'' }" data-rule="required" class="required" ></textarea>
					<label id="info" style="margin-left:20px" />
				</p>
					<input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!'' }"/>
			</table>
		</fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
		<li><button id="btn" type="submit" class="btn-blue">提交申请</button></li>
	</ul>
</div>