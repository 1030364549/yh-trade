<link href="/yhtrade/public/treetable/css/jquery.treetable.css" rel="stylesheet"/>
<script>
    var option1 = {
        theme: 'default',//'vsStyle', //显示样式
        expandable: true //可伸缩
    };
    $("#deptTable").treetable(option1);
</script>
<div class="bjui-pageHeader">
    <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
        <div class="bjui-searchBar">
            <div style="margin-top: 1px;height: 25px">
                <span><a class="btn btn-blue permission" href="${baseClass}/cdDeptAdd" carte_id="${(carte_id)!'0'}" rel="SysManager_cdDeptAdd" data-toggle="dialog" data-options="{id:'dialog',title:'添加部门',width:'500',height:'345'}"><i class="fa fa-plus"></i><span>添加部门</span></a></span>
            </div>
            <div class="pull-right">
            </div>
        </div>
        <#include "/common/pageForm.ftl">
    </form>
</div>
<div class="bjui-pageContent tableContent">
    <table class="table table-striped table-bordered table-hover" id="deptTable">
        <thead>
        <tr role="row">
            <th width="10%">部门名称</th>
            <th width="10%">部门描述</th>
            <th width="10%">添加日期</th>
            <th width="10%">操作</th>
        </tr>
        </thead>
        <tbody>
            <#if deptDataList ??>
            <#list deptDataList as list>
				<tr data-tt-id="${list.CD_ID}" data-tt-parent-id="${list.PARENT_CDID}">
                    <td >${(list.CD_NAME)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.CD_REMARK)!'<span style="color:gray">暂无</span>'}</td>
                    <td >${(list.ADD_DATE)!'<span style="color:gray">暂无</span>'}</td>
                    <td >
                        <a class="btn btn-red permission" carte_id="${(carte_id)!'0'}" href="${baseClass}/delcdDept?cd_id=${(list.CD_ID)!}" rel="SysManager_delcdDept" data-toggle="doajax"  data-confirm-msg = "确定要删除该部门吗？" ><i class="fa fa-remove"></i><span>删</span></a>&nbsp;
                    </td>
                </tr>
            </#list>
            </#if>
        </tbody>
    </table>
</div>
</div>