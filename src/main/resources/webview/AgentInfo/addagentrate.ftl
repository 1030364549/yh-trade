<div class="bjui-pageContent">
 <form method="post" action="${baseClass}/saveaddagentrate")}" data-toggle="validate"
 data-reload-navtab="true" data-alertmsg="false" >
	 <fieldset>
		 <legend>参考上级代理商规则信息</legend>
		 <table>
			 <tr>
				 <td>
					 <p>
						 <label style="width:80px;">激活返现设置:</label>
						 <input id="commissary" type="text" name="" value="${(parentmap.ACTIVATE_CASHBACK)!'' }" data-rule="number"  size="10" readonly="readonly"/><span>元</span>
						 <label id="info" style="margin-left:20px" />
					 </p>
				 </td>
				 <td>
					 <p>
						 <label style="width:80px;">高签设置:</label>
						 <input id="commissary" type="text" name="" value="${(parentmap.HIGH_SIGN)!'' }" data-rule="number"  size="10" readonly="readonly"/>
						 <label id="info" style="margin-left:20px" />
					 </p>
				 </td>
				 <td
				 <p>
					 <label style="width:80px;">服务费率:</label>
					 <input id="commissary" type="text" name="" value="${(parentmap.SERVICE_CHARGE)!'' }" data-rule="number"  size="10" readonly="readonly"/><span>%</span>
					 <label id="info" style="margin-left:20px" />
				 </p>
				 </td>
			 </tr>
		 </table>
	 </fieldset>
  <fieldset>
   <legend>本级规则信息设置</legend>
   <table>
	<tr>
	 <td>
	  <p>
	   <label style="width:80px;">激活返现设置:</label>
	   <input id="commissary" type="text" name="activate_cashback" value="${(data.ACTIVATE_CASHBACK)!'' }" data-rule="digits" class="required" size="10"/><span>元</span>
	   <label id="info" style="margin-left:20px" />
	  </p>
	 </td>
		<td>
		 <p>
			 <label style="width:80px;">高签设置:</label>
			 <input id="commissary" type="text" name="high_sign" value="${(data.HIGH_SIGN)!'' }" data-rule="digits" class="required" size="10"/>
			 <label id="info" style="margin-left:20px" />
		 </p>
		</td>
		<td
			 <p>
				 <label style="width:80px;">服务费率:</label>
				 <input id="commissary" type="text" name="service_charge" value="${(data.SERVICE_CHARGE)!'' }" data-rule="number" class="required" size="10"/><span>%</span>
				 <label id="info" style="margin-left:20px" />
			 </p>
	 </td>
		<td
		<p>
			<label style="width:80px;">激活返现标准:</label>
			<input id="commissary" type="text" name="active_money" value="${(data.ACTIVE_MONEY)!'' }" data-rule="number" class="required" size="10"/><span>元</span>
			<label id="info" style="margin-left:20px" />
		</p>
		</td>
	</tr>
   </table>
  </fieldset>
	   <fieldset>
	   <tr>
		   <td>
			   <p>
				   <label style="width:200px;">分润服务费设置规则:</label>
			   </p>

		   </td>
		   <td>
			   <p style="color: red">
                    1、激活返现为整数 2、高签范围 1-9 3、服务费率 0%-17%  请按规则填写
			   </p>
		   </td>
	   </tr>
	   </fieldset>
  <input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!''}"/>
  <#--<input id="agent_status" type="hidden" name="agent_status" value="${(agent_status)!'' }"/>-->
 </form>
</div>
<div class="bjui-pageFooter">
 <ul>
  <li><button type="button" class="btn-close">关闭</button></li>
  <li><button id="btn" type="submit" class="btn-blue">保存</button></li>
 </ul>
</div>
<script language="javascript">
function onChangeSelect(idone,idtwo,qu){
	$('#'+idtwo).empty();
	$("#"+idtwo).append("<option value=''>请选择</option>");
	$('#'+qu).empty();
	$("#"+qu).append("<option value=''>请选择</option>");
	var index=$('#'+idone+' option:selected').val();//选中的值
	findArea(idtwo,index);
}
function findArea(id,index){
	var url = "/yhagent/Area/findArea";
	$.post(url, {
			index : index
		},
		function(data) {
			for(var i=0;i<data.length;i++){
					$("#"+id).append("<option value='"+data[i].AREA_ID+"'>"+data[i].AREA_NAME+"</option>");
			}
		}, "json");
}
function submitBtn(){
	var sheng=$("#sheng option:selected").text();
	var shi=$("#shi option:selected").text();
	var qu=$("#qu option:selected").text();
	sheng=sheng+","+shi+","+qu;
	$("#agent_area").val(sheng);
	$("#myForm").submit();
}
</script>