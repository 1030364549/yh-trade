<style>
#jBox {border:solid 1px #CCC; line-height:30px; background:#fff;overflow:hidden;float:left;height:726px;width:80%;}
#tsdiv {height:33px;margin-bottom:10px; padding:0 10px; line-height:30px; font-size:14px; border-bottom:solid 1px #CCC;}
#tsdiv h9 {font-weight: bold;}
#buttonClick li{float:left;}
.form-group {line-height:30px;}
.divider { border-color:#9BCD9B; clear:both; display:block; overflow:hidden; text-indent:-1000px; width:auto; height:1px; padding:4px 0 0 0; margin-bottom:5px; border-style:dotted; border-width:0 0 1px 0;}
</style>
<script>
//添加菜单按钮
function addMenu(){
	$("#contentTitle").text("添加菜单");
	$("#deleteid,#parents,#j_menu_url,#j_menu_rel,#j_menu_name,#j_menu_js,#j_ztree_menus2,#id,#parent_id").val("");
	$("#buttonClick").css("display","none");
	$("#cartesz").click();
}
//删除菜单
function deleteMenu(){
	var j_menu_name = $("#j_menu_name").val();
	$(this).alertmsg('confirm', '确认要删除菜单【'+j_menu_name+'】吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确认', cancelName:'取消', title:'提示信息',okCall:function(){
		$.ajax({
			type: "post",
			url: "/yhtrade/BackCarte/delCarte",
			data: {"id":$("#id").val()},
			error:function(response){
				$(this).alertmsg('error',"异常");
			},
			success:function(data){
				if(data.statusCode=="200"){
					$(this).alertmsg('ok',data.message);
					$(this).navtab('refresh','');
				}else{
					$(this).alertmsg('error',data.message);
				}
			}
		});
	}});
}
//展示菜单详情
function ZtreeClick(event, treeId, treeNode){
	event.preventDefault();
	$("#buttonClick").show();
	$("#j_menu_name").val(treeNode.name);
	$.ajax({
		type: "post",
		url: "/yhtrade/BackCarte/getCartInfo",
		data: {"id":treeNode.ids},
		success:function(data){
			$("#j_menu_url").val(data.HREF); 
			$("#j_menu_rel").val(data.REL);
			$("#j_menu_target").val(data.TARGET); 
			if("0"==data.PARENTS){
				$("#j_ztree_menus2").val("主菜单");
			}else{
				$("#j_ztree_menus2").val(data.PARENTS_NAME);
			}
			$("#parent_id").val(data.PARENT_ID);
			$("#parents").val(data.PARENTS);
			$("#j_menu_js").val(data.CARTE_INTRO);
			$("#id").val(data.ID);
			var val = $("#defaultOpen").val();
			if(val=="#bottonsz"){
				$("#contentTitle").text();
				$("#contentTitle").text(treeNode.name+" >> 按钮管理");
			}else{
				$("#contentTitle").text(treeNode.name);
			}
			if (data.HREF == "#") {
				$("#bottonTab").css("display","none");
			}else{
				$("#bottonTab").css("display","block");
				$("#bottonsz").attr("href","/yhtrade/BackCarte/buttonList?carte_id="+treeNode.ids);
			}
			$(val).click();
		}
	});
}
function openTabDeafault(val){
	if(val=="#cartesz"){
		$("#contentTitle").text($("#j_menu_name").val());
	}else if($("#j_menu_name").val()!=null&&$("#j_menu_name").val()!=""){
		$("#contentTitle").text($("#j_menu_name").val()+" >> 按钮管理");
	}else{
		$("#contentTitle").text("按钮管理");
	}
	$(val).click();
}

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
	$("#parents").val(ids);//保存选中的菜单
}
</script>
<div class="pageContent" style="padding:5px">
	 <form method="post" action="${base}/BackCarte/saveCarte" data-toggle="validate" data-toggle="ajaxform"  >
		<fieldset style="height: 767px;">
			<div>
				<div style="width:240px;height:25px;float:left;">
					<label > 默认打开：</label>
					<select id = "defaultOpen" onchange="openTabDeafault(this.value)" class="form-control">
						<option value="#cartesz">菜单基本设置</option>
						<option value="#bottonsz">按钮\行为管理</option>
					</select>
				</div>
				<div style="height:25px;">
					<ul style="margin-right:10px;display: none;" id="buttonClick">
						<li> <a style="margin-left:2px;" class="btn btn-green" onclick="addMenu();">+ 菜单添加</a></li>
						<li> <a style="margin-left:2px;" class="btn btn-red permission" onclick="deleteMenu();" rel="BackCarte_delCarte" carte_id="${(carte_id)!'0'}" data-divid="#layout-01">- 菜单删除</a></li>
					</ul>
				</div>
			</div>
			<div style="margin-top:10px;height:650px;width:1380px;">
				<!--展示菜单-->
				<div layoutH="60" style="float:left;background:#fff;border:1px solid #CCC;width:240px;overflow:auto;height:726px;">
					<ul id="ztree1" class="ztree" data-toggle="ztree"  data-options="{expandAll: false,onClick: 'ZtreeClick'}">
						<#list carteList as item> 
							<!-- 一级菜单 -->
							<#if item["LEVELS"] == 1>
								<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}"  data-ids="${item['ID']}" data-href="*">  ${item["CARTE_NAME"]} </li>
							</#if>
							<!-- 二级菜单 -->
							<#if item["LEVELS"] == 2>
								<#if (carteList[item_index + 1]) ?? >
									<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}"  data-ids="${item['ID'] }" <#if (carteList[item_index + 1]["LEVELS"] > item["LEVELS"])>  data-faicon="folder-open-o" data-faicon-close="folder-o" data-url="*" <#else> data-tabid="table" data-faicon="table" data-url="#"</#if>>${item["CARTE_NAME"]}</li>
								<#elseif item_index == (carteList?size - 1)>
									<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}"  data-faicon="table"  data-ids="${item['ID'] }" data-url="#">${item["CARTE_NAME"]}</li>
								</#if>
							</#if>
							<!-- 三级菜单 -->
							<#if item["LEVELS"] == 3>
								<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}"  data-ids="${item['ID'] }"  data-tabid="table" data-faicon="table" data-url="#">${item["CARTE_NAME"]}</li>
							</#if>
						</#list>
					</ul>
				</div>
				<!-- 分栏显示菜单-按钮页面 -->
				<div id="jBox" layoutH="60" class="unitBox">
					<div id="tsdiv">
						<h9 >&nbsp;&nbsp;||&nbsp;&nbsp;&gt;&gt;&nbsp;&nbsp;<span id="contentTitle" style="font-size:15px;">添加菜单<span></h9>
					</div>
					<ul id="tabPage" class="nav nav-tabs" role="tablist">
						<li class="active"><a id="cartesz" href="#carte" role="tab" data-toggle="tab" data-reload="true">菜单基本设置</a></li>
						<li id="bottonTab" style="display: none;"><a id="bottonsz" href="/yhtrade/BackCarte/buttonList?carte_id=0" data-toggle="ajaxtab" data-reload="true" data-target="#botton">按钮/行为管理</a></li>
					</ul>
					<div class="tab-content" sytle="height:600px;">
						<!-- 显示-添加菜单页面 -信息 -->
						<div class="tab-pane fade active in" id="carte">
							<div style="margin-left:10px;">
								<div class="form-group">
									<label >菜单名称：</label>
									<input type="text" class="form-control validate[required] required" name="carte_name" data-rule="required" id="j_menu_name" size="30" placeholder="名称" />
								</div>
								<div class="divider"></div>
								<div class="form-group">
									<label >菜单URL：</label>
									<input type="text" class="form-control validate[required] required" name="href" data-rule="required" id="j_menu_url" size="25" placeholder="admin/info" />
								</div>
								<div class="divider"></div>
								<div class="form-group">
									<label >菜单REL：</label>
									<input type="text" class="form-control validate[required] required" data-rule="required" name="rel" size="25" id="j_menu_rel" placeholder="admin_info" />
								</div>
								<div class="divider"></div>
								<div class="form-group">
									<label >打开方式：</label>
									<select name="target" class="form-control" selectvl = "${(map['TARGET'])! }" style="width:150px;" id="j_menu_target">
										<option value="navTab">navTab</option>
										<option value="dialog">dialog</option>
									</select>
								</div>
								<div class="divider"></div>
								<div class="form-group">
									<label >上级菜单：</label>
									<tr>
										<td>
											<input type="text" name="menus" class="form-control validate[required] required" id="j_ztree_menus2" data-rule="required" data-toggle="selectztree" size="25" data-tree="#j_select_tree2" value="主菜单" readonly>
											<input id="id" type="hidden" name="id" value="" readonly><!-- 如果是修改操作          该字段为本菜单ID -->
											<input id="parent_id" type="hidden" name="parent_id" value="" readonly><!-- 如果是修改操作          该字段为本菜单的PARENT_ID -->
											<input id="parents" type="hidden" name="parents" value="0" readonly><!-- 添加修改操作中该字段为   被操作菜单的父级菜单的PARENT_ID-->
											<ul id="j_select_tree2" class="ztree hide" data-toggle="ztree" data-expand-all="false" data-check-enable="true" data-chk-style="radio" data-radio-type="all" data-on-check="S_NodeCheck" data-on-click="S_NodeClick">
												<li data-id="0" data-pid="0" >主菜单</li>
												<#list carteList as item> 
													<!-- 一级菜单 -->
													<#if item["LEVELS"] == 1>
														<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-tabid="form-button">  ${item["CARTE_NAME"]} </li>
													</#if>
													<!-- 二级菜单 -->
													<#if item["LEVELS"] == 2>
														<#if (carteList[item_index + 1]) ?? >
															<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" <#if (carteList[item_index + 1]["LEVELS"] > item["LEVELS"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="table" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?ID=${item['ID'] }" </#if>>${item["CARTE_NAME"]}</li>
														<#elseif item_index == (carteList?size - 1)>
															<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-tabid="table" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?ID=${item['ID'] }" >${item["CARTE_NAME"]}</li>
														</#if>
													</#if>
													<!-- 三级菜单 -->
													<#if item["LEVELS"] == 3>
														<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-url="/yhtrade/${item['HREF'] }/?ID=${item['ID'] }" data-tabid="table" data-faicon="table">${item["CARTE_NAME"]}</li>
													</#if>
												</#list>
											</ul>
										</td>
									</tr>
								</div>
								<div class="divider"></div>
								<div class="form-group">
									<label >菜单介绍：</label>
									<textarea class="editor" name="carte_intro" style="width:600px;height:200px" tools="simple" id="j_menu_js">${(map["CARTE_INTRO"])!}</textarea>
								</div>
								<div class="divider"></div>
								<label >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
								<span style="color:#FF0000;">* 打开方式 和 上级菜单 一经保存后不可更改</span>
								<div class="divider"></div>
								<button style="margin-left:410px;" id="btn" type="submit" carte_id="${(carte_id)!'0'}" rel="BackCarte_saveCarte" class="btn btn-green permission">保存菜单</button>
								<div class="divider"></div>
							</div>
						</div>
						<!--显示按钮-->
						<div class="tab-pane fade" id="botton"></div>
					</div>
				</div>
			</div>
		</fieldset>
	</form>
</div>