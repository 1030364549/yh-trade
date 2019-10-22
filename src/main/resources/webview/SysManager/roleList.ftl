<style>
    #jBoxRole {border:solid 1px #CCC; line-height:30px; background:#fff;overflow:hidden;float:left;height:666px;width:70%;}
    #tsdivRole {height:33px;margin-bottom:10px; padding:0 10px; line-height:30px; font-size:14px; border-bottom:solid 1px #CCC;}
    #tsdivRole h9 {font-weight: bold;}
</style>
<script>
    //添加角色--树形选择事件
    function S_NodeCheck(e, treeId, treeNode){
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        var	nodes = zTree.getCheckedNodes(true);
        var ids = '';
        var names = '';
        for (var i = 0; i < nodes.length; i++) {
            var id=nodes[i].id+"";
            if(id.length==7){
                id="0"+id;
            }
            ids   += ','+ id;
            names += ','+ nodes[i].name;
        }
        if (ids.length > 0) {
            ids = ids.substr(1), names = names.substr(1);
        }

        var $from = $('#'+ treeId).data('fromObj');
        if ($from && $from.length){
            $from.val(names);
        }
        $("#parent_role").val(ids);//保存选中的菜单
    }
    //右侧角色--点击设置权限
    function ZtreeClick(event, treeId, treeNode){
        event.preventDefault();
        var blue=treeNode.blue;
        if("dialog"==blue){
            var url='/yhtrade/SysManager/roleQx?role_id='+treeNode.ids;
            $(this).dialog({id:'mydialog', url:url, title:'设置权限',width:'1000',height:'600'});
        }
    }

    //删除角色
    $("#delRoleBtn",document).click(function () {
        //获取选中的角色编号
        var zTreeObj = $.fn.zTree.getZTreeObj("ztreeRole");
        var nodes = zTreeObj.getCheckedNodes(true);
        var roleId="";
        for (var i = 0; i < nodes.length; i++){
            if ( i == nodes.length -1){
                roleId += nodes[i].id;
            }else{
                roleId += nodes[i].id + ",";
            }
        }

        if(roleId!=""){
            $.ajax({
                type: "post",
                url: "/yhtrade/SysManager/delRole",
                data: {
                    "roleIds":roleId
                },
                error:function(response){
                    $(this).alertmsg('error',"异常");
                },
                success:function(data){
                    if(data.statusCode=="200"){
                        $(this).alertmsg('ok',data.message);
                        //刷新
                        $(this).navtab('refresh');//刷新当前页签
                    }else{
                        $(this).alertmsg('error',data.message);
                    }
                }
            });
        }else{
            $(this).alertmsg('warn',"未选择数据");
        }
    });
</script>
<div class="pageContent" style="padding:5px">
    <form method="post" action="${baseClass}/saveRole" data-toggle="validate" >
        <fieldset style="height: auto;">
            <div style="margin-top:6px;height:650px;width:1380px;">
                <div>
                    <div style="width:100%;height:25px;float:left;">
                        <span> 直接点击角色即可进入权限设置界面</span>
                        <span> <a style="margin-left:2px;" carte_id="${(carte_id)!'0'}" class="btn btn-red permission" rel="SysManager_delRole" id="delRoleBtn">删除</a></span>
                    </div>
                </div>
                <!--展示角色-->
                <div layoutH="60" style="float:left;background:#fff;border:1px solid #CCC;width:300px;overflow:auto;height:666px;">
                    <ul id="ztreeRole" class="ztree" data-toggle="ztree" data-check-enable="true" data-chk-style="radio" data-options="{expandAll: true,onClick: 'ZtreeClick'}">
						<#list roleList as item>
                            <!-- 0级角色 -->
							<#if item["LVL"] == 0>
								<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-ids="${item['ROLE_ID']}" data-href="*">  ${item["ROLE_NAME"]} </li>
                            </#if>
                            <!-- 一级角色 -->
							<#if item["LVL"] == 1>
                                <#if (roleList[item_index + 1]) ?? >
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}"  data-ids="${item['ROLE_ID'] }" <#if (roleList[item_index + 1]["LVL"] > item["LVL"])>  data-faicon="folder-open-o" data-faicon-close="folder-o" data-url="*" <#else> data-blue="dialog" data-tabid="table" data-faicon="table" data-url="*"</#if>>${item["ROLE_NAME"]}</li>
                                <#elseif item_index == (roleList?size - 1)>
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-faicon="table"  data-ids="${item['ROLE_ID'] }" data-blue="dialog" data-url="*">${item["ROLE_NAME"]}</li>
                                </#if>
                            </#if>

                            <!-- 二级角色 -->
							<#if item["LVL"] == 2>
                                <#if (roleList[item_index + 1]) ?? >
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}"  data-ids="${item['ROLE_ID'] }" <#if (roleList[item_index + 1]["LVL"] > item["LVL"])>  data-faicon="folder-open-o" data-faicon-close="folder-o" data-url="*" <#else> data-blue="dialog" data-tabid="table" data-faicon="table" data-url="*"</#if>>${item["ROLE_NAME"]}</li>
                                <#elseif item_index == (roleList?size - 1)>
									<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-faicon="table"  data-ids="${item['ROLE_ID'] }" data-blue="dialog" data-url="*" >${item["ROLE_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 三级角色 -->
							<#if item["LVL"] == 3>
								<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-blue="dialog" data-ids="${item['ROLE_ID'] }" data-tabid="table" data-faicon="table" data-url="*">${item["ROLE_NAME"]}</li>
                            </#if>

                        </#list>
                    </ul>
                </div>
                <!-- 分栏显示菜单-按钮页面 -->
                <div id="jBoxRole" layoutH="60" class="unitBox">
                    <div id="tsdivRole">
                        <h9 >&nbsp;&nbsp;||&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;<span id="contentTitle" style="font-size:15px;">添加角色<span></h9>
                    </div>
                    <div class="divider"></div>
                    <div class="tab-content" sytle="height:600px;">
                        <!-- 显示-添加菜单页面 -信息 -->
                        <div class="tab-pane fade active in" id="role">
                            <div style="margin-left:10px;">
                                <div class="form-group">
                                    <label >角色名称：</label>
                                    <input type="text" class="form-control validate[required] required" name="role_name" data-rule="required" size="30" placeholder="角色名称" />
                                </div>
                                <div class="divider"></div>
                                <div class="form-group">
                                    <label >角色分组：</label>
                                    <tr>
                                        <td>
                                            <input type="text" name="roles" class="form-control validate[required] required" id="j_ztree_roles" data-rule="required" data-toggle="selectztree" size="25" data-tree="#j_select_roles2" value="总部" readonly>
                                            <input id="parent_role" type="hidden" name="parent_role" value="1" readonly>
                                            <ul id="j_select_roles2" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-chk-style="radio" data-radio-type="all" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
												<#list roleList as item>
                                                    <!-- 0级菜单 -->
                                                    <#if item["LVL"] == 0>
														<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-tabid="form-button" data>  ${item["ROLE_NAME"]} </li>
                                                    </#if>
													<!-- 一级菜单 -->
                                                    <#if item["LVL"] == 1>
														<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-tabid="form-button">  ${item["ROLE_NAME"]} </li>
                                                    </#if>
													<!-- 二级菜单 -->
                                                    <#if item["LVL"] == 2>
                                                        <#if (roleList[item_index + 1]) ?? >
															<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" >${item["ROLE_NAME"]}</li>
                                                        <#elseif item_index == (roleList?size - 1)>
															<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-tabid="table" data-faicon="table"  >${item["ROLE_NAME"]}</li>
                                                        </#if>
                                                    </#if>
													<!-- 三级菜单 -->
                                                    <#if item["LVL"] == 3>
														<li data-id="${item['ROLE_ID']}" data-pid="${item['PARENT_ROLE']}" data-tabid="table" data-faicon="table">${item["ROLE_NAME"]}</li>
                                                    </#if>
                                                </#list>
                                            </ul>
                                        </td>
                                    </tr>
                                </div>
                                <div class="divider"></div>
                                <div class="form-group">
                                    <label >角色描述：</label>
                                    <textarea class="editor" name="role_remark" style="width:600px;height:200px" tools="simple" id="j_menu_js">${(map["ROLE_REMARK"])!}</textarea>
                                </div>
                                <div class="divider"></div>
                                <div class="divider"></div>
                                <button style="margin-left:410px;" id="btn" type="submit" carte_id="${(carte_id)!'0'}" rel="SysManager_saveRole" class="btn btn-green permission">保存角色</button>
                                <div class="divider"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </fieldset>
    </form>
</div>