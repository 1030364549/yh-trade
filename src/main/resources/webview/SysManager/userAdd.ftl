<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/saveUser"
		data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
      		<p>
				<label style="width:80px;">用户名:</label>
				<input id="admin_name" type="text" name="admin_name" data-rule="required" class="required" placeholder="只能输入英文、数字" />
                <label id="info" style="margin-left:2px" />
			<p>
			<p>
				<label style="width:80px;">用户密码:</label>
				<input type="password" name="admin_pass" data-rule="required" value="123456" class="required" placeholder="默认密码:123456" />
			</p>
			<p>
				<label style="width:80px;">真实姓名:</label>
				<input type="text" name="admin_realname" class="required" data-rule="required" placeholder="用户真实姓名" />
			</p>
			<p>
				<label style="width:80px;">备注:</label> 
				<textarea class="required" data-rule="required" name="admin_intro" style="width:330px;height:100px" tools="simple" id="j_menu_js" placeholder="备注" ></textarea>
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
$("#admin_name").keyup(function(){
	var reg=/^[0-9a-zA-Z]+$/;
	var admin_name = $('#admin_name').val();
	//判断输入框中有内容
	if(!reg.test(admin_name)) {
		$('#info').html('<span style="color:red">只能输入英文、数字</span>');
		$("#btn").attr("disabled", "disabled");
	}else{
		$.ajaxSettings.global = false;
		var url = "/yhtrade/SysManager/userCkName";
		
		$.post(url, {
			admin_name : admin_name
		},
		function(data) {
			if (data==true) {
				$('#info').html('<span style="color:red">用户名已存在!</span>');
				$("#btn").attr("disabled", "disabled");
			} else {
				$('#info').html('<span style="color:green">用户名可用!</span>');
				$("#btn").removeAttr("disabled");
			};
		}, "json");
	}
});
</script>