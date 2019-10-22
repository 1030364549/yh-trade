<style>
	.dialog .pageHeader, .dialog .pageContent { border-color:#b8d0d6;}
	.dialog .pageContent .pageFormContent { border-color:#b8d0d6; background:#FFF;}
	.pageForm { display:block; overflow:auto;}
	.pageFormContent { display:block; overflow:auto; padding:10px 5px; position:relative;}
	.pageFormContent div.unit {display:block; margin:0; padding:5px 0; position:relative;clear:both;}
</style>
<div data-toggle="dialog" data-width="800" data-height="400" data-id="dialog-normal">
	<div class="bjui-pageContent" >
		<form method="post"  data-toggle="validate" data-reload-navtab="true" >
			<p>
				<label style="width:80px;">用户名：</label>
				<input type="text" id="users" size="18" value = "${(login_user)!}"/>
			</p>
			<p>
				<label style="width:80px;">密码：</label>
				<input type="password" id="pwd" size="18" value = "${(login_password)!}"/>
			<p>
			<p class="unit" style = "float:right;width:100px;">
				<label style = "width:80px;">
                    <input type="checkbox" class="checkboxCtrl" name="remember" id="remember" data-toggle="icheck"
                    <#if remember??>
                        <#if remember=="1">
                            checked
                        </#if>
                    </#if> />记住密码
                </label>
			</p>
		</form>
	</div>
	<div class="bjui-pageFooter">
		<ul>
			<li><button   onclick = "loginDialog();" class="btn-blue">登录</button></li>
		</ul>
	</div>
</div>
<script>
    function loginDialog(){
        var username = $("#users").val();
        var password = $("#pwd").val();
        var ckbox = $("#remember").prop("checked");
        if(ckbox=="checked"){
            ckbox=true;
        }else{
            ckbox=false;
        }
        if(username==""||password==""){
            $(this).alertmsg('warn', '请填写用户名和密码！');
        }else{
            var user={"admin_name" : username,"admin_password" : password, "remember":ckbox, "isLogin":"1"};

            $.ajax({
                type: "POST",
                url: "/yhtrade/AdminInfo/loginUser",
                data: JSON.stringify(user),
                datatype:"json",
                contentType:"application/json;charset=utf-8",//(可以)
                success:function(data){
                    if(data.code=="E0"){
                        $(this).dialog('closeCurrent','');
                        $(this).alertmsg('correct', '登录成功，请继续操作！');
                    }else{
                        $(this).alertmsg('correct', data.codeMsg+"["+data.code+"]");
                    }
                }
            });
        }
    }

</script>