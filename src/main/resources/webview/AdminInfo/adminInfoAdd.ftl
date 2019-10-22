<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveAdminInfo"
          data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
        <#if agentinfo??><#if agentinfo.admin??><p><span>此机构已配置[${(agentinfo.admin)!''}]用户</span></p></#if></#if>
            <legend>登陆用户信息:</legend>
            <p>
                <label style="width:80px;">登陆用户名:</label>
                <input id="admin_name" type="text" name="admin_name" data-rule="required,length(0~20)" class="required" placeholder="登陆用户名" />
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
				<label style="width:80px;">真实姓名:</label>
                <input id="admin_realname" type="text" name="admin_realname" data-rule="required,length(0~20)" class="required" placeholder="真实姓名" />
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:80px;">是否管理员:</label>
                <input type="radio" name="is_admin" readonly id="doc-radio1" data-toggle="icheck" value="1" checked data-label="否" >
                <input type="radio" name="is_admin" readonly  id="doc-radio2" data-toggle="icheck" value="0" data-label="是">
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:80px;">备注:</label>
                <textarea id="admin_intro" name="admin_intro" style="width:330px;height:100px" tools="simple" data-rule="length(0~100)" placeholder="备注" ></textarea>
            </p>
		</fieldset>
        <input id="obj_no" type="hidden" name="obj_no" value="${(agentinfo.agent_num)!'' }"/>
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
            var url = "${base}/AdminInfo/userCheckName";
            $.post(
                url,
                {
                    admin_name : admin_name
                },
                function(data) {
                    if (data == true) {
                        $('#info').html('<span style="color:red">用户名已存在!</span>');
                        $("#btn").attr("disabled", "disabled");
                    } else {
                        $('#info').html('<span style="color:green">用户名可用!</span>');
                        $("#btn").removeAttr("disabled");
                    }
                },
                "json"
            );
        }
    });
</script>