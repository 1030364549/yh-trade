<link href="/yhtrade/public/imgZoom/jquery.artZoom.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="/yhtrade/public/imgZoom/jquery.artZoom.min.js" type="text/javascript"></script>
<script>
    $('.artZoom').artZoom({
        path: '/yhtrade/public/imgZoom/images',	// 设置artZoom图片文件夹路径
        preload: true,		// 设置是否提前缓存视野内的大图片
        blur: true,			// 设置加载大图是否有模糊变清晰的效果

        // 语言设置
        left: '左旋转',		// 左旋转按钮文字
        right: '右旋转',		// 右旋转按钮文字
        source: '看原图'		// 查看原图按钮文字
    });
    //删除后刷新本页面
    function reloadDialog(json){
        DWZ.ajaxDone(json);
        $.pdialog.reload_p("MAgentInfo_getPictureInfo");
    }

    function submitBtn(){
        $("#sub").submit();
    }
</script>
<div class="bjui-pageContent tableContent">
 <#if imglist ??>
  <#list imglist as list>
   <table class="table table-bordered table-hover table-striped table-top">
    <thead>
     <tr>
	  <th width="6%">代理商编号</th>
	  <th width="10%">图片类型</th>
	  <th width="6%">添加人</th>
	  <th width="10%">添加时间</th>
     </tr>
    </thead>
    <tbody>
	 <tr data-id="id=${(list.ID)!}" > 
	  <td >${(list.CLIENTNO)!'<span style="color:gray">暂无</span>'}</td>
	  <td >
	   <#switch "${(list.TYPE)!}">
	    <#case "1"><span style="color: blue">身份证正面</span><#break>
	    <#case "2"><span style="color: blue">身份证反面</span><#break>
		<#case "3"><span style="color: blue">手持身份证</span><#break>
		<#case "4"><span style="color: blue">结算卡正面</span><#break>
		<#case "5"><span style="color: blue">营业执照</span><#break>
		<#case "6"><span style="color: blue">店面场景</span><#break>
		   <#case "7"><span style="color: blue">店面门脸</span><#break>
		   <#case "8"><span style="color: blue">店铺收银</span><#break>
		   <#case "9"><span style="color: blue">租赁合同</span><#break>
		   <#case "10"><span style="color: blue">收款签名</span><#break>
		   <#case "11"><span style="color: blue">代理商协议</span><#break>
		   <#case "12"><span style="color: blue">活体照片</span><#break>

		   <#case "13"><span style="color: blue">调单文件</span><#break>
		   <#case "14"><span style="color: blue">税务登记证</span><#break>
		   <#case "14"><span style="color: blue">组织机构代码证</span><#break>
		   <#case "16"><span style="color: blue">开户许可证</span><#break>
		   <#case "17"><span style="color: blue">代理商情况说明表</span><#break>
		<#default>
	   </#switch>

	  </td>
	  <td >${(list.CREATENAME)!'<span style="color:gray">暂无</span>'}</td>
	  <td >${(list.CREATETIME)!'<span style="color:gray">暂无</span>'}</td>
	 </tr>
     <#--<tr><td colspan="5"><img src="${showPath}${(list.FILEID)!}"></td></tr>-->

	 <tr><td colspan="5" align="center">	 <img style="width:80px;height:80px;" alt="点击查看大图（旋转照片）" src="${showPath}${(list.FILEID)!}" class="artZoom imgsss" data-artZoom-show="${showPath}${(list.FILEID)!}"  data-artZoom-source="${showPath}${(list.FILEID)!}"></td></tr>
    </tbody>
   </table>
  </#list>
 </#if>
</div>
