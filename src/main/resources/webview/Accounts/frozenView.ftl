
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/frozen" 
		data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<p>
				<label style="width:80px;">操作:</label> 
				<select data-toggle="selectpicker" name="status"
					selectvl="${(accounts.status)!}">
					<option value="0">解冻商户</option>
					<option value="1">冻结商户</option>
				</select>
				<label id="info" style="margin-left:20px" />
			<p>
			<p>
				<label style="width:80px;">操作原因:</label>
				<textarea rows="5" cols="30" name="note" value="${(accounts.note)!'' }" data-rule="required" class="required" ></textarea> 	
				<label id="info" style="margin-left:20px" />
			<p>
			<input type="hidden" name="id" value="${(accounts.id)!'' }">
			<input type="hidden" name="clientno" value="${(accounts.seq_id)!'' }">
			<input type="hidden" name="obj_type" value="${(accounts.da_marker)!'' }">
		</fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
		<li><button id="btn" type="submit" class="btn-blue">保存</button></li>
	</ul>
</div>
