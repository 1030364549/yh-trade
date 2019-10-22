<style>
	#okcarte{height:38px;color:#ff0000;font-weight:bold;}
</style>
<script>
    $(document).ready(function(){
        var role_no=$("#role_no").val();
        var dataJson=$("#dataJson").val();

        if(dataJson!="-1"){
            //显示按钮
            showButton(role_no);
        }
    });
    //保存菜单权限以及显示按钮
    $("#okcarte",document).click(function(){
        var role_no=$("#role_no").val();
        var retObj = getMenuCheck();
        //保存菜单权限
        $.ajax({
            type: "post",
            url: "/yhtrade/SysManager/saveRoleQxCarte",
            data: {
                "role_no":role_no,
                "carteIds":retObj
            },
            error:function(response){
                $(this).alertmsg('error',"异常");
            },
            success:function(data){
                if(data.statusCode=="200"){
                    $(this).alertmsg('ok',data.message);
                    //显示按钮
                    showButton(role_no);
                }else{
                    $(this).alertmsg('error',data.message);
                }
            }
        });
    });
    //获取菜单选中值
    function getMenuCheck(){
        var nodes = zTreeObj.getCheckedNodes(true);
        var retObj = "";
        for (var i = 0; i < nodes.length; i++){
            if ( i == nodes.length -1){
                retObj += nodes[i].carteId;
            }else{
                retObj += nodes[i].carteId + ",";
            }
        }
        return retObj;
    }
    //查询按钮
    function showButton(role_no){
        $("#jBox5").loadUrl("/yhtrade/SysManager/roleQxButton/?role_no="+role_no+"&select=1",function(data){
            $("#jBox5").find("[layoutH]").layoutH();
        });
    }
</script>
<div class="pageContent" style="padding:5px">
    <fieldset style="height: 560px;">
        <legend style="font-weight:normal;">设置权限</legend>
        <input type="hidden" name="role_no" id="role_no" value = "${(role_no)!}"/>
        <input name="dataJson" id="dataJson" value="${jsonList}" type="hidden"/>
		<#if jsonList=="-1">
			<div style="color:red;font-weight:bold;font-size:30px;height:95%;margin:auto;text-align:center;">该用户不用设置权限!</div>
		<#else>
			<!-- 展示菜单 -->
			<div layoutH="60" style="float:left; display:block;width:240px; border:solid 1px #CCC; line-height:30px; background:#fff;margin-top: 10px;padding-left: 7px;height: 520px;overflow:auto">
                <button type="button" data-icon="plus" class="btn btn-blue" id="okcarte" ><span>请选择菜单，完毕点击这里保存</span></button>
				<#include "roleQxMenu.ftl">
            </div>
			<div id="jBox5" layoutH="80" class="unitBox" style="margin-left:246px;border:solid 1px #CCC; line-height:30px; background:#fff;overflow-y:scroll;margin-top: 10px;height: 520px;">
				<#include "roleQxButton.ftl">
            </div>
		</#if>
    </fieldset>
</div>