<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveUserRole"
          data-toggle="validate" data-reload-navtab="true" data-alertmsg="false" id="userRoleForm">
        <fieldset>
            <div style="margin-top:6px;height:484px;">
                <!--展示角色-->
                <div layoutH="60" style="width:100%;height:100%;float:left;background:#fff;border:1px solid #CCC;overflow:auto;">
                    <ul id="ztreeUserRole" class="ztree" data-toggle="ztree" data-check-enable="true" data-options="{expandAll: true,onClick: 'ZtreeClickRole'}">
						<#list roleList as item>
                            <!-- 0级角色 -->
							<#if item["LVL"] == 0>
								<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-ids="${item['ROLE_ID']}" >  ${item["ROLE_NAME"]} </li>
							</#if>
                            <!-- 一级角色 -->
							<#if item["LVL"] == 1>
								<#if (roleList[item_index + 1]) ?? >
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}"  data-ids="${item['ROLE_ID'] }" <#if (roleList[item_index + 1]["LVL"] > item["LVL"])>  data-faicon="folder-open-o" data-faicon-close="folder-o"  <#else> data-ckvalue="ck" data-checked="${(item['XROLE_ID'])!false}" data-tabid="table" data-faicon="table" </#if>>${item["ROLE_NAME"]}</li>
								<#elseif item_index == (roleList?size - 1)>
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-faicon="table"  data-ids="${item['ROLE_ID'] }" data-ckvalue="ck" data-checked="${(item['XROLE_ID'])!false}" >${item["ROLE_NAME"]}</li>
								</#if>
							</#if>

                            <!-- 二级角色 -->
							<#if item["LVL"] == 2>
								<#if (roleList[item_index + 1]) ?? >
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}"  data-ids="${item['ROLE_ID'] }" <#if (roleList[item_index + 1]["LVL"] > item["LVL"])>  data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-ckvalue="ck" data-checked="${(item['XROLE_ID'])!false}" data-tabid="table" data-faicon="table" </#if>>${item["ROLE_NAME"]}</li>
								<#elseif item_index == (roleList?size - 1)>
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-faicon="table"  data-ids="${item['ROLE_ID'] }" data-ckvalue="ck" data-checked="${(item['XROLE_ID'])!false}" >${item["ROLE_NAME"]}</li>
								</#if>
							</#if>
                            <!-- 三级角色 -->
							<#if item["LVL"] == 3>
								<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-ckvalue="ck" data-checked="${(item['XROLE_ID'])!false}" data-ids="${item['ROLE_ID'] }" data-tabid="table" data-faicon="table" >${item["ROLE_NAME"]}</li>
							</#if>

						</#list>
                    </ul>
                </div>
            </div>
			<input type="hidden" name="admin_id" value="${(admin_id)!}"/>
			<input type="hidden" name="roleids" id="roleids" />
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn_userRole" type="button" class="btn-blue">保存</button></li>
    </ul>
</div>
<script>
    //保存菜单权限以及显示按钮
    $("#btn_userRole",document).click(function(){
        var role_no=$("#role_no").val();
        var retObj = getRoleCheck();
        if (retObj=="") {
            retObj="0";
        }
        $("#roleids").val(retObj);
        $("#userRoleForm").submit();
    });
    //获取角色选中值
    function getRoleCheck(){
        var zTreeObj=$.fn.zTree.getZTreeObj("ztreeUserRole");
        var nodes = zTreeObj.getCheckedNodes(true);
        var retObj = "";
        for (var i = 0; i < nodes.length; i++){
            if("ck" == nodes[i].ckvalue){
                if ( i == nodes.length -1){
                    retObj += nodes[i].ids;
                }else{
                    retObj += nodes[i].ids + ",";
                }
			}
        }
        return retObj;
    }
</script>