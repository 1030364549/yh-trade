<script>
    function passCallBack(json){
        if(json.statusCode==200){
            //关闭当前页面
            $(this).alertmsg('ok', json.message);
            $(this).dialog('closeCurrent',true);
            setTimeout(function(){
                window.location.href = "/yhtrade/AdminInfo/destroyLogin";
            },2000);//执行的动作写进function里面，延迟的时间写在1000那个位置，单位是毫秒
        }else{
            $(this).alertmsg('error', json.message);

        }

    }
</script>
<div class="bjui-pageContent">
	<form method="post" action="${base}/AdminInfo/updateUserPass"
		data-toggle="validate" data-reload-navtab="true" data-callback="passCallBack" data-alertmsg="false">
		<fieldset>
			<p>
				<label style="width:80px;">用户名:</label> ${(adminInfo.admin_name)!''}
			<p>
			<p>
				<label style="width:80px;">真实姓名:</label> ${(adminInfo.admin_realname)!'' }
			</p>
            <p>
                <label style="width:80px;">旧密码:</label>
                <input type="password" id="oldadmin_pass" name="oldadmin_pass" value="" class="required" data-rule="required" />
            </p>
			<p>
				<label style="width:80px;">新密码:</label>
				<input type="password" id="admin_pass" name="admin_pass" value="" class="required" data-rule="required" />
				只能输入英文、数字
				<input type="hidden" name="admin_id" value="${(adminInfo.admin_id)!''}" />
			</p>
			<p>
				<label style="width:80px;">确认密码:</label> 
				<input type="password" id="sadmin_pass" name="sadmin_pass" value="" class="required" data-rule="required" />
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