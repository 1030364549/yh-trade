
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/updateprofittype"
		  data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<table>
				<p>
					<label style="width:90px;">结算周期:</label> <select id="profittype" data-toggle="selectpicker" disabled
																	 name="profittype" selectvl="${(profittype)!''}"
																	 style="height: 25px;" >
						<option value="0">开通</option>
						<option value="1">关闭</option>
					</select> <label id="info" style="margin-left:20px" />

					<input type="hidden" name="profittype" value="${(profittype)!''}">
				<p>
				<p>
					<label style="width:90px;">修改原因:</label>
					<textarea cols="30" rows="4" data-toggle="autoheight" name="note" value="${(merchinfo.note)!'' }" data-rule="required" class="required" ></textarea>
					<label id="info" style="margin-left:20px" />
				<p>
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