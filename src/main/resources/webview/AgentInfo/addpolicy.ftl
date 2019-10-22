<div class="bjui-pageContent">
 <form method="post" action="${baseClass}/savePolicy")}" data-toggle="validate"
 data-reload-navtab="true" data-alertmsg="false" >
  <fieldset>
   <legend>分润规则信息</legend>
   <table>
	<tr>
	 <td>
	  <p>
	   <label style="width:200px;">贷记卡/KS/分润结算点:</label>
	  </p>
	 </td>
	 <td>
	  <p>
	   <label style="width:80px;">标准:</label>
	   <input id="commissary" type="text" name="dksbz" value="${(Policy.dksbz)!'' }" data-rule="number" class="required" size="10" />
	   <label id="info" style="margin-left:20px" />
	  </p>
	 </td>
	<td>
		<p>
			<label style="width:80px;">优惠费率:</label>
			<input id="commissary" type="text" name="dksyh" value="${(Policy.dksyh)!'' }" data-rule="number" class="required" size="10"/>
			<label id="info" style="margin-left:20px" />
		</p>
	</td>
	<td>
		<p>
			<label style="width:80px;">云闪付:</label>
			<input id="commissary" type="text" name="dksysf" value="${(Policy.dksysf)!'' }" data-rule="number" class="required" size="10"/>
			<label id="info" style="margin-left:20px" />
		</p>
	</td>
	</tr>
	   <tr>
		   <td>
			   <p>
				   <label style="width:200px;">贷记卡/CR/分润结算点:</label>
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">标准:</label>
				   <input id="commissary" type="text" name="dcrbz" value="${(Policy.dcrbz)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">优惠费率:</label>
				   <input id="commissary" type="text" name="dcryh" value="${(Policy.dcryh)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">云闪付:</label>
				   <input id="commissary" type="text" name="dcrysf" value="${(Policy.dcrysf)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
	   </tr>
	   <tr>
		   <td>
			   <p>
				   <label style="width:200px;">借记卡/KS/分润结算点:</label>
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">标准:</label>
				   <input id="commissary" type="text" name="jksbz" value="${(Policy.jksbz)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">优惠费率:</label>
				   <input id="commissary" type="text" name="jksyh" value="${(Policy.jksyh)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">云闪付:</label>
				   <input id="commissary" type="text" name="jksysf" value="${(Policy.jksysf)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
	   </tr>
	   <tr>
		   <td>
			   <p>
				   <label style="width:200px;">借记卡/CR/分润结算点:</label>
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">标准:</label>
				   <input id="commissary" type="text" name="jcrbz" value="${(Policy.jcrbz)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">优惠费率:</label>
				   <input id="commissary" type="text" name="jcryh" value="${(Policy.jcryh)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">云闪付:</label>
				   <input id="commissary" type="text" name="jcrysf" value="${(Policy.jcrysf)!'' }" data-rule="number" class="required" size="10"/>
				   <label id="info" style="margin-left:20px" />
			   </p>
		   </td>
		   <td>
			   <p>
				   <label style="width:80px;">封顶:</label>
				   <input id="commissary" type="text" name="jcrfd" value="${(Policy.jcrfd)!'' }" data-rule="digits" class="required" size="10"/>
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
				   <label style="width:200px;">分润结算点设置规则:</label>
			   </p>

		   </td>
		   <td>
		          <tr>
				   <td>
					   <p style="color: red">
			           1、添加结算点请注意 公司分润政策阈值 2、请填写费率高于阈值
					   </p>
		          </td>
		          </tr>
		          <tr>
		           <td>
					   <p style="color: red">
					   1、例：结算点为 0.38 代表 0.38%  2、结算点要高于本级代理商结算点 3、优惠费率不能大于标准费率 4、封顶值为整数，费率为小数
					   </p>
				   </td>
				  </tr>

		   </td>
	   </tr>
	   </fieldset>
  <input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!'' }"/>
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