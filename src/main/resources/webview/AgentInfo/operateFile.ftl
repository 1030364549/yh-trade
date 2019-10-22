<link rel="stylesheet" type="text/css" href="/yhtrade/public/BJUI/diyUpload/css/webuploader.css">
<link rel="stylesheet" type="text/css"	href="/yhtrade/public/BJUI/diyUpload/css/diyUpload.css">
<script	src="/yhtrade/public/BJUI/diyUpload/js/webuploader.html5only.min.js"	type="text/javascript"></script>
<script src="/yhtrade/public/BJUI/diyUpload/js/diyUpload.js"	type="text/javascript"></script>
<div class="pageContent">
   <div class="pageFormContent" layoutH="36" style="height: 393px; overflow: auto;">
	 <div style="margin-left:10px;font-size:14px;font-weight:bold;margin-top:5px;">
			</div>
			<div style="width:97%;margin-left:1%;height:auto;border:1px solid #7cc5e5;margin-top:5px;border-radius: 12px;">
			    <div id="diyUpload" style="margin:5px;">
					<input type="hidden" name="agent_num" value="${(agent_id)!}"/>
			    </div>
			</div>
			<script type="text/javascript">
				$("#diyUpload").diyUpload({
							url : '/yhtrade/AgentInfo/saveFiles?agent_id='+$('input[name="agent_num"]').val(),
							success : function(data) {
					  			if(data.pic=="0000"){
					  				$('input[name="agent_id"]').val(data.agent_id);
					  				var rel_type= $('#rel_type').val();
					  				var uuurl = $('#button_href').val();
					  				var uuurl1 = $('#button_href1').val();
					  				var pic_search_btn = $('#pic_search_btn');
					  				pic_search_btn.attr('href',uuurl+"?agent_id="+data.agent_id+"&rel_type="+rel_type);
					  				$('#pic_search_btn').css("display","");
					  				$('#pic_search_btn1').attr('href',uuurl1+"?agent_id="+data.agent_id+"&rel_type="+rel_type+"&id="+$("input[name='id']").val());
					  				
					  			   alertMsg.correct("上传图片成功");
					  			   $.pdialog.closeCurrent();
					  			   //刷新页面
					  			   navTab.reloadFlag("dealermostly_info");  
					  			}else{
					  			   alertMsg.error("上传图片失败");
					  			}
							},
							error : function(err) {
							   alertMsg.error("系统异常");
							},
								buttonText : '选择文件',
								chunked : false,
								// 分片大小
								chunkSize : 1024 * 1024,
								//最大上传的文件数量, 总文件大小,单个文件大小(单位字节);
								fileNumLimit : 10,
								fileSizeLimit : 10240 * 1024,
								fileSingleSizeLimit : 3072 * 1024
						});
			  </script>
	</div>
</div>
