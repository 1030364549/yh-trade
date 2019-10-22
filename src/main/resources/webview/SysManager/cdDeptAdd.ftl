<script>
    //树形选择事件
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
        $("#parent_cdid").val(ids);//保存选中的菜单
    }
</script>
<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveCdDept"
          data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
        <fieldset>
            <p>
                <label style="width:80px;">部门名称:</label>
                <input id="cd_name" type="text" name="cd_name" data-rule="required" class="required" placeholder="部门名称" />
                <label id="info" style="margin-left:2px" />
            <p>
            <p>
                <label style="width:80px;">上级部门:</label>
                <input type="text" name="parent_cdids" id="j_ztree_parentcdid" data-toggle="selectztree" size="18" data-tree="#j_select_parentcdid" readonly>
                <input id="parent_cdid" type="hidden" name="parent_cdid" value="0" readonly>
                <ul id="j_select_parentcdid" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-chk-style="radio" data-radio-type="all" data-on-check="S_NodeCheck">
                    <li data-id="0" data-pid="0" >主部门</li>
                    <#if deptDataList ??>
                        <#list deptDataList as item>
                            <!-- 0级 -->
                            <#if item["LVL"] == 0>
							    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="form-button">  ${item["CD_NAME"]} </li>
                            </#if>
                            <!-- 1级 -->
                            <#if item["LVL"] == 1>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 2级 -->
                            <#if item["LVL"] == 2>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 3级 -->
                            <#if item["LVL"] == 3>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 4级 -->
                            <#if item["LVL"] == 4>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 5级 -->
                            <#if item["LVL"] == 5>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 6级 -->
                            <#if item["LVL"] == 6>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 7级 -->
                            <#if item["LVL"] == 7>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 8级 -->
                            <#if item["LVL"] == 8>
                                <#if (deptDataList[item_index + 1]) ?? >
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" <#if (deptDataList[item_index + 1]["LVL"] > item["LVL"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table"  </#if>>${item["CD_NAME"]}</li>
                                <#elseif item_index == (deptDataList?size - 1)>
								    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table" >${item["CD_NAME"]}</li>
                                </#if>
                            </#if>
                            <!-- 9级 -->
                            <#if item["LVL"] == 9>
							    <li data-id="${item['CD_ID']}" data-pid="${item['PARENT_CDID']}" data-tabid="table" data-faicon="table">${item["CD_NAME"]}</li>
                            </#if>
                        </#list>
                    </#if>
                </ul>
            <p>
            <p>
                <label style="width:80px;">备注:</label>
                <textarea class="required" data-rule="required" name="cd_remark" style="width:300px;height:100px" tools="simple" placeholder="备注" ></textarea>
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
