<style>
    p.bgBtn:hover{cursor:pointer;}
    .panelBarClass{position: fixed;z-index:1;width: 65%;}
    #tsdiv{height:38px;border-color: #b8d0d6;background-color: #EBF0F5;font-weight:bold;}
    #tsdiv #tsdivspan,h9,#allChecked,#inverseChecked{float:left;}
    #tsdivspan{color:#0000ff;font-size:13px;font-weight:bold;margin-top:7px;margin-left:10px;}
    .buttonContent button{color:#ff0000;font-weight:bold;}
</style>
<div class="pageContent">
    <div id="tsdiv">
        <span id="tsdivspan">[系统]:请先选择左侧菜单进行匹配菜单所属按钮</span>
        <div style = "margin-top:3px;">
            <span id = "allChecked"><div class="buttonActive"><div class="buttonContent">&nbsp; <button>全选 </button> </div></div></span>&nbsp;
            <span id = "inverseChecked"><div class="buttonActive"><div class="buttonContent">&nbsp; <button> 反选 </button> </div></div></span>
        </div>
        <span style = "float:right;margin-right:5%;margin-top:-8px;" id = "saveData"><div class="buttonActive"><div class="buttonContent"><button>点击此处保存按钮权限</button></div></div></span>
    </div>
    <div style = "margin-top:10px;" >
        <form method="post" action="${baseClass}/saveRoleQxButton"  data-toggle="validate" id = "roleDataForm" data-reload-navtab="true"   onsubmit="return $(this).dialog('closeCurrent',true)">
            <input name = "buttonData" value = "" type = "hidden"/>
            <input type="hidden" name="roleNo" value = ""/>
        </form>
		<#if buttonData??>
            <#list buttonData?keys as key >
				<fieldset style="background-color:#EBF0F5;width:98%;margin-top:5px;">
                    <p class = "bgBtn" style="width:100%;border:dashed 1px #999933;height:20px;">
                        <label style="width:80%;font-weight:bold;margin-left:10px;color:#666600;margin-top:3px;">${key}</label>
                        <a class="tle" style="float:right;cursor:pointer;color:#999933;margin-right:10px;margin-top:3px;">-收缩</a>
                    </p>
                    <fieldset style="background-color:#ffffff;float:left;width:98%">
                        <div style="margin:5px;">
                            <label style="color:red;">按钮权限:</label>
                        </div>
                        <div>
                            <p style="width:98%;height:auto">
								<#if (buttonData[key]?size > 0) >
									<#list buttonData[key] as dataMap>
										<label style = "margin-left:5px;">
                                            <input name = "buttonChecked" type="checkbox" <#if (dataMap["checked"]) ??>checked</#if> value = "${(dataMap["BT_ID"])! }">${(dataMap["BT_NAME"])! }
                                        </label>
                                    </#list>
                                <#else>
									<label style = "color:gray;">此菜单下暂无按钮信息</label>
                                </#if>
                            </p>
                        </div>
                        <div style="margin:5px">
                            <div class="divider"></div>
                        </div>
                    </fieldset>
                </fieldset>
            </#list>
        </#if>
    </div>
</div>
<script>
    $(".bgBtn").click(function(){
        $(this).siblings().toggle();
        var aTitle = $(this).find(".tle").text();
        if(aTitle=="-收缩"){
            $(this).find(".tle").html("+展开");
        }else{
            $(this).find(".tle").html("-收缩");
        }
    });
    //全选
    $("#allChecked",document).click(function(){
        $("input[type='checkbox']").prop("checked",true);
    });
    //反选
    $("#inverseChecked",document).click(function(){
        $("input[type='checkbox']").each(function(){
            $(this).prop("checked",!$(this).prop("checked"));
        });
    });
    //保存
    $("#saveData",document).click(function(){
        var buttonData = "";
        $("input[name='buttonChecked']:checked").each(function(){
            buttonData+=","+$(this).val();
        });
        buttonData = buttonData.substring(1);
        var role_no=$("#role_no").val();
        $("input[name='buttonData']").val(buttonData);
        $("input[name='roleNo']").val(role_no);
        $("#roleDataForm").submit();
    });
</script>