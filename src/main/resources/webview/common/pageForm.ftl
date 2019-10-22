<input type="hidden" name="pageNum" value="${(p.pageNo)!"1"}"/>
<input type="hidden" name="totalRecord" value="${(p.totalRecord)!"0"}"/>
<input type="hidden" name="numPerPage" value="${(p.pageSize)!"20"}"/>
<input type="hidden" name="orderField" value="${(p.orderField)!""}" /><!--【可选】查询排序-->
<input type="hidden" name="orderDirection" value="${(p.orderDirection)!""}" /><!--【可选】升序降序-->
<#if p??>
<#list p.params?keys as testKey> 
	<input type="hidden" name="${testKey}" value="${(p.params)[testKey]!''}"/>
</#list>
</#if>
<!-- 菜单ID -->
<input type="hidden" name="carte_id" value="${(carte_id)!'0'}" />
<input type="hidden" name="showType" value="1" />
