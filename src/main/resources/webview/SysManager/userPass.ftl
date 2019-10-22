<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/updateUserPass"
		data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<p>
				<label style="width:80px;">用户名:</label> ${(adminMap.ADMIN_NAME)!''}
			<p>
			<p>
				<label style="width:80px;">真实姓名:</label> ${(adminMap.ADMIN_REALNAME)!'' }
			</p>
			<p>
				<label style="width:80px;">用户密码:</label> 
				<input type="password" id="admin_pass" name="admin_pass"  class="required" data-rule="required" />
				只能输入英文、数字
				<input type="hidden" name="admin_id" value="${(adminMap.ADMIN_ID)!''}" />
			</p>
			<p>
				<label style="width:80px;">确认密码:</label> 
				<input type="password" id="sadmin_pass" name="sadmin_pass"  class="required" data-rule="required" />
				<label id="info" style="margin-left:20px" />
			</p>
			<p>
				<label style="width:80px;">&nbsp;</label> 
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
$("#sadmin_pass").keyup(function(){
	var reg=new RegExp("^[A-Za-z0-9\u4e00-\u9fa5]+$");
	var admin_pass = $('#admin_pass').val();
	var sadmin_pass = $('#sadmin_pass').val();
	//判断输入框中有内容
	if(!reg.test(admin_pass)) {
		$('#info').html('<span style="color:red">只能输入英文、数字</span>');
		$("#btn").attr("disabled", "disabled");
	}else{
		if(admin_pass==sadmin_pass){
			$('#info').html('<span style="color:green"></span>');
			$("#btn").removeAttr("disabled");
		}else{
			$('#info').html('<span style="color:red">两次密码不一致!</span>');
			$("#btn").attr("disabled", "disabled");
		}
	}
});
</script>