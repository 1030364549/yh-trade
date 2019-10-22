<script>
	function deleteButton(){
		$(this).alertmsg('confirm', '确认要删除该按钮吗？',{displayMode:'slide',displayPosition:'topcenter',okName:'确认',cancelName:'取消',title:'提示信息',okCall:function(){
			$.ajax({
				url:"${base}/BackCarte/delButton",
				type:"post",
				data:{"carte_id":#{carte_id},"bt_id":$("#bjui-selected").val()},
				success:function(data){
					if(data.statusCode=="200"){
						$(this).alertmsg('info',"删除成功");
						$("#bottonsz").click();
					}else{
						$(this).alertmsg('error',data.message);
					}
				}
			});
		}});
	}
</script>
<div class="bjui-pageHeader">
	<div class="bjui-searchBar">
		<a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/addButton?carte_id=#{carte_id}" rel="BackCarte_addButton" data-toggle="dialog"  data-options="{id:'dialog',title:'添加按钮',width:'600',height:'300'}"><i class="fa fa-plus"></i><span>添加按钮</span></a>
		<a class="btn btn-blue permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/addButton?carte_id=#{carte_id}&bt_id={#bjui-selected}" rel="BackCarte_addButton" data-toggle="dialog"  data-options="{id:'dialog',title:'修改按钮',width:'600',height:'300'}"><i class="fa fa-plus"></i><span>修改按钮</span></a>
		<a class="btn btn-red permission" carte_id="${(carte_id)!'0'}" onclick="deleteButton()" rel="BackCarte_delButton"><i class="fa fa-times"></i><span>删除按钮</span></a>
	</div>
</div>
<div id="buttonList" class="bjui-pageContent tableContent">
    <table class="table table-bordered table-hover table-striped table-top">
        <thead>
            <tr>
				<th width="8%">按钮名称</th>
				<th width="12%">本级URL</th>
				<th width="12%">子级URL</th>
				<th width="10%">添加时间</th>
				<th width="8%">备注</th>
            </tr>
        </thead>
		<tbody>
    		<#list btList as item>
				<tr  data-id="${item.BT_ID}" > 
					<td >${item.BT_NAME}</td>
					<td >${item.BT_HREF}</td>
					<td >${item.BT_SC_HREF}</td>
					<td >${item.ADDTIME}</td>
					<td >${(item.REMARK)!''}</td>
            	</tr>
            </#list>
		</tbody>
	</table>
</div>