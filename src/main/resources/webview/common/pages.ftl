<div class="bjui-pageFooter">
<#if p ??>
    <div class="pages">
        <span>每页&nbsp;</span>
        <div class="selectPagesize">
            <select name="numPerPage"  data-toggle="selectpicker" data-toggle-change="changepagesize" selectvl="${(p.pageSize)!"20"}">
                <option value="10">10</option>
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
            </select>
        </div>
        <span>&nbsp;条，共 ${p.totalRecord} 条&nbsp;&nbsp;第${p.pageNo}/${p.totalPage}页</span>
    </div>
    	<div class="pagination-box" data-toggle="pagination" data-total="${p.totalRecord}" data-page-size="${p.pageSize}" data-page-current="${p.pageNo}">
    </div>
</#if>
</div>
<script type="text/javascript">
</script>
