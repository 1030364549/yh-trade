<style>
	.main td {
		width: 400px;
	}
</style>
<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/saveOrUpdate" data-toggle="validate"
		  data-reload-navtab="true" data-alertmsg="false" >
		<fieldset>
			<legend>基本信息</legend>
			<table class="main">
				<tr>
					<td>
						<p>
							<label style="width:120px;">代理商名称:</label>
							<input id="agent_name" type="text" name="agent_name" value="${(agentinfo.AGENT_NAME)!'' }"  maxlength="30" data-rule="required" class="required" />
							<label id="info" style="margin-left:20px" />
						</p>
					</td>
					<td>


						<label style="width:120px;">代理商地区:</label>
						<select name="province" id="province" data-toggle="selectpicker" data-rule="required" class="required"  <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.PROVINCE)!}" </#if> </#if>  data-nextselect="#shiMapcity" data-refurl="/ldagent/Area/findArea?index={value}">
							<option value="">请选择</option>
							<#if areaMap ??>
								<#list areaMap as ms>
									<option value="${(ms.AREA_ID)!}">
										${(ms.AREA_NAME)!}
									</option>
								</#list>
							</#if>
						</select>



						<select name="city" id="shiMapcity" data-toggle="selectpicker"  data-rule="required" class="required" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.CITY)!''}" </#if> </#if> data-nextselect="#j_form_area" data-width="77" data-refurl="/ldagent/Area/findArea?index={value}" data-emptytxt="请选择">
							<option value="">请选择</option>
							<#if shiMap ??>
								<#list shiMap as ms>
									<option value="${(ms.AREA_ID)!}"> ${(ms.AREA_NAME)!}</option>
								</#list>
							</#if>
						</select>

<#--						<p>-->
<#--							<label style="width:120px;">统一社会信用代码:</label>-->
<#--							<input id="commissary" type="text" name="organ_code" value="${(agentinfo.ORGAN_CODE)!'' }" maxlength="20" data-rule="required" class="required" />-->
<#--							<label id="info" style="margin-left:20px" />-->
<#--						</p>-->
					</td>
				</tr>

				<tr>
					<td>
						<p>

							<label style="width:120px;">详细地址:</label>
							<input id="agent_area" type="text" name="agent_area" value="${(agentinfo.AGENT_AREA)!'' }"  maxlength="25" data-rule="required" class="required" />
							<label id="info" style="margin-left:20px" />


							<#--<select name="qu" id="j_form_area" data-toggle="selectpicker" selectvl="${(agentinfo.qu)!}" data-emptytxt="请选择">
                                <option value="">请选择</option>
                                <#if quMap ??>
                                    <#list quMap as ms>
                                        <option value="${(ms.AREA_ID)!}">${(ms.AREA_NAME)!}</option>
                                    </#list>
                                </#if>
                            </select>-->
							<#--<input type="hidden" name="agent_area" id="agent_area" value="${(agentinfo.agent_area)!''}">-->
							<label id="info" style="margin-left:20px" />
						</p>
					</td>

					<td>

					</td>

				</tr>



<#--				<tr>-->
<#--					<td>-->
<#--						<p>-->
<#--							<label style="width:120px;">代理商性质:</label>-->

<#--							<select id="agent_nature" data-toggle="selectpicker" data-width="208" name="agent_nature" <#if agent_num??> selectvl="${(accounts.AGENT_NATURE)!''}" </#if> style="height: 25px;">-->
<#--								<option value="0">经销商</option>-->
<#--								<option value="1">渠道商</option>-->
<#--							</select>-->


<#--							<label id="info" style="margin-left:20px" />-->
<#--						<p>-->
<#--					</td>-->

<#--					<td>-->
<#--						<p>-->
<#--							<label style="width:120px;">工商登记号:</label>-->
<#--							<input id="agent_name" type="text" name="regist_num" value="${(agentinfo.REGIST_NUM)!'' }"  maxlength="20" data-rule="required" class="required" />-->
<#--							<label id="info" style="margin-left:20px" />-->
<#--						</p>-->
<#--					</td>-->

<#--				</tr>-->


			</table>
		</fieldset>



		<fieldset>
			<legend>联系人信息</legend>
			<table class="main">
			<tr>
				<td>

					<p>
						<label style="width:120px;">联系人姓名:</label>
						<input id="agent_name" type="text"  name="link_man" value="${(agentinfo.LINK_MAN)!'' }"  maxlength="20" data-rule="required" class="required" />
						<label id="info" style="margin-left:20px" />
					</p>
				</td>


				<td>
					<p>
						<label style="width:120px;">手机号码:</label>
						<input id="cor_phone" type="text" name="link_phone" value="${(agentinfo.LINK_PHONE)!'' }" maxlength="11"  data-rule="required,mobile" class="required" />
						<label id="info" style="margin-left:20px" />
					</p>
				</td>


			</tr>
			</table>
		</fieldset>



		<fieldset>
			<legend>账户信息</legend>
			<table class="main">
				<tr>
					<td>
						<label style="width:120px;">账户类型:</label>
						<select id="nature" data-toggle="selectpicker"  data-rule="required" class="required"  name="nature"  selectvl="${(accounts.NATURE)!''}"  style="height: 25px;">
							<option value="1">对公</option>
							<option value="2">对私</option>
						</select>
					</td>

					<td>

					</td>

				</tr>


				<div id="div2">

					<tr>
						<td class="div2">

							<p>
								<label style="width:120px;">结算人:</label>
								<input id="screen_name" type="text"  name="screen_name" class="a2"  value="${(accounts.SCREEN_NAME)!'' }" maxlength="20"   />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>

						<td class="div2">
							<p>
								<label style="width:120px;">结算卡:</label>
								<input id="screen_num" type="text" data-rule="number" name="screen_num" class="a2" data-rule="length(14~)" value="${(accounts.SCREEN_NUM)!'' }" maxlength="20"   />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>



					<tr>
						<td class="div2">
							<p>
								<label style="width:120px;">结算人身份证号:</label>
								<input id="screen_idnum" type="text" name="screen_idnum"  data-rule="ID_card"  class="a2"  value="${(accounts.SCREEN_IDNUM)!'' }" maxlength="20"  />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>

						<td class="div2">
							<p>
								<label style="width:120px;">预留手机号:</label>
								<input id="resphone" type="text" name="resphone" data-rule="mobile"  class="a2" value="${(accounts.RESPHONE)!'' }" maxlength="20"   />
								<label id="info" style="margin-left:20px" />
							</p>

						</td>
					</tr>


					<tr>
						<td class="div2">
							<p>
								<label style="width:120px;">总行名称:</label>
								<input type="text" data-tagname="bank_headname"   class="a2"  value="${(accounts.BANK_HEADNAME)!'' }"  data-width="190" data-toggle="tags" data-url="/ldagent/AgentInfo/getBank"  data-max="1" size="15" placeholder="输入关键字，回车提交">
								<label id="info" style="margin-left:20px" />

								<#if agentinfo??>
									<input type="hidden" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }" >
								</#if>
							</p>

						</td>

						<td class="div2">
							<p>
								<label style="width:120px;">开户行名称:</label>
								<input id="screen_name" type="text" name="screen_name"  class="a2" value="${(accounts.SCREEN_NAME)!'' }" maxlength="11"   />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>
				</div>




				<#-- <tr>
                     <td>
                         <p>
                             <label style="width:120px;">结算人:</label> <input id="screen_name"
                                                                            type="text" name="screen_name"
                                                                            value="${(accounts.SCREEN_NAME)!'' }" data-rule="required"
                                                                            class="required" maxlength="20"/> <label id="info" style="margin-left:20px" /><p>
                     </td>
                     <td>
                         <p>
                             <label style="width:120px;">结算卡:</label> <input id="screen_num"
                                                                            type="text" name="screen_num"
                                                                            value="${(accounts.SCREEN_NUM)!'' }"
                                                                            data-rule="required,digits" maxlength="20" class="required" /> <label
                                     id="info" style="margin-left:20px" /><p>
                     </td>
                 </tr>-->
				<div id="div1">

					<tr>
						<td class="div1">
							<p>
								<label style="width:120px;">开户账户名称:</label> <input id="screen_name" class="a1"
																				   type="text" name="screen_name"
																				   value="${(accounts.SCREEN_NAME)!'' }"   maxlength="20"/> <label id="info" style="margin-left:20px" /><p>
						</td>
						<td class="div1">
							<p>
								<label style="width:120px;">开户银行账户:</label> <input id="screen_num" class="a1"
																				   type="text" name="screen_num"   data-rule="length(14~)"
																				   value="${(accounts.SCREEN_NUM)!'' }"
																				    maxlength="20" />
								<label  id="info" style="margin-left:20px" /><p>
						</td>


					</tr>



					<tr>
						<td class="div1">
							<p>
								<label style="width:120px;">总行名称:</label>

								<input type="text" data-tagname="_bank_headname" class="a1"  value="${(accounts.BANK_HEADNAME)!'' }"  data-width="190" data-toggle="tags" data-url="/ldagent/AgentInfo/getBank"  data-max="1" size="15" placeholder="输入关键字，回车提交">

								<#if agentinfo??>
									<input type="hidden" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }" >
								</#if>
								<#--						 <input id="bank_headname"-->
								<#--																		  type="text" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }"-->
								<#--																		  data-rule="required" class="required" maxlength="30" /> <label id="info"-->
								<#--																																		 style="margin-left:20px" />-->
							</p>
						</td>
						<td class="div1">

							<p>
								<label style="width:120px;">支行名称:</label> <input id="bank_name" class="a1"
																				 type="text" name="bank_name" value="${(accounts.BANK_NAME)!'' }"
																				 maxlength="35" /> <label id="info"
							</p>
						</td>
					</tr>



					<tr>
						<td class="div1" >
							<p>
								<label style="width:120px;">账户开户地:</label>


								<select  class="a1" name="bank_address" id="province1"   data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??>  selectvl="${(accounts.bank_address_province)!}" </#if> </#if> data-nextselect="#j_form_citya1231231" data-refurl="/ldagent/Area/findArea?index={value}">
									<option value="">请选择</option>
									<#if areaMap ??>
										<#list areaMap as ms>
											<option value="${(ms.AREA_ID)!}">
												${(ms.AREA_NAME)!}
											</option>
										</#list>
									</#if>
								</select>


								<select  class="a1" name="bank_address" id="j_form_citya1231231" data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(accounts.bank_address_city)!''}" </#if> </#if> data-nextselect="#j_form_areaa1" data-width="77" data-refurl="/ldagent/Area/findArea?index={value}" data-emptytxt="请选择">
									<option value="">请选择</option>
									<#if shiMap1 ??>
										<#list shiMap1 as ms>
											<option value="${(ms.AREA_ID)!}">${(ms.AREA_NAME)!}</option>
										</#list>
									</#if>
								</select>




							</p>
						</td>
						<td class="div1">
							<p>
								<label  style="width:120px;">支付系统行号:</label> <input id="bank_num" class="a1"
																				   type="text" name="bank_num" value="${(accounts.BANK_NUM)!'' }" maxlength="15" />
								<label id="info" style="margin-left:20px" /></p>
						</td>
					</tr>

				</div>


				<#--			 <tr>-->
				<#--				 <td>-->
				<#--					 <p>-->
				<#--						 <label style="width:120px;">预留手机号:</label> <input id="resphone"-->
				<#--																		  type="text" name="resphone" value="${(accounts.RESPHONE)!'' }"-->
				<#--																		  data-rule="required,mobile" class="required" maxlength="11" /> <label id="info"-->
				<#--																																				style="margin-left:20px" />-->
				<#--					 </p>-->
				<#--				 </td>-->
				<#--				 <td>-->

				<#--				 </td>-->
				<#--			 </tr>-->

				<#--			 <tr>-->
				<#--				 <td>-->
				<#--					 <p>-->
				<#--						 <label style="width:120px;">是否全额结算:</label> <select id="isallaudit" data-toggle="selectpicker" data-width="208"-->
				<#--																			name="isallaudit" selectvl="${(accounts.ISALLAUDIT)!''}"-->
				<#--																			style="height: 25px;">-->
				<#--							 <option value="0">否</option>-->
				<#--							 <option value="1">是</option>-->
				<#--						 </select> <label id="info" style="margin-left:20px" /><p>-->

				<#--				 </td>-->
				<#--				 <td>-->
				<#--					 <p>-->
				<#--						 <label style="width:120px;">起始结算金额:</label> <select id="acmoney" data-toggle="selectpicker" data-width="208"-->
				<#--																			name="acmoney" selectvl="${(accounts.ACMONEY)!''}" maxlength="10"-->
				<#--																			style="height: 25px;">-->
				<#--							 <option value="10">10</option>-->
				<#--							 <option value="50">50</option>-->
				<#--						 </select> <label id="info" style="margin-left:20px" /><p>-->

				<#--				 </td>-->
				<#--			 </tr>-->
				<#--			 <tr>-->
				<#--				 <td>-->
				<#--&lt;#&ndash;					 <p>&ndash;&gt;-->
				<#--&lt;#&ndash;						 <label style="width:120px;">账户类型:</label> <select id="nature" data-toggle="selectpicker" data-width="208" onChange="checkstatus();"&ndash;&gt;-->
				<#--&lt;#&ndash;																		  name="nature" selectvl="${(accounts.NATURE)!''}"&ndash;&gt;-->
				<#--&lt;#&ndash;																		  style="height: 25px;">&ndash;&gt;-->
				<#--&lt;#&ndash;							 <option value="2">对私账户</option>&ndash;&gt;-->
				<#--&lt;#&ndash;							 <option value="1">对公账户</option>&ndash;&gt;-->
				<#--&lt;#&ndash;						 </select> <label id="info" style="margin-left:20px" />&ndash;&gt;-->
				<#--&lt;#&ndash;					 </p>&ndash;&gt;-->



				<#--				 </td>-->
				<#--				 <td>-->
				<#--					 <p>-->
				<#--						 <label style="width:120px;">结算周期:</label> <select id="settle_cycle" data-toggle="selectpicker" data-width="208"-->
				<#--																		  name="settle_cycle" selectvl="${(accounts.SETTLE_CYCLE)!''}"-->
				<#--																		  style="height: 25px;">-->
				<#--							 <option value="T1">工作日</option>-->
				<#--							 <option value="D0">实时</option>-->
				<#--						 </select> <label id="info" style="margin-left:20px" />-->

				<#--					 </p>-->

				<#--				 </td>-->
				<#--			 </tr>-->
				<#--			 <tr id="d1">-->
				<#--				 <td >-->
				<#--					 <p>-->
				<#--						 <label style="width:120px;">结算人身份证号:</label> <input id="screen_idnum"-->
				<#--																			type="text" name="screen_idnum" value="${(accounts.SCREEN_IDNUM)!'' }"-->
				<#--																			data-rule="ID_card" maxlength="18" placeholder="对私账户必填"  /> <label-->
				<#--								 id="info" style="margin-left:20px" />-->
				<#--					 </p>-->

				<#--				 </td>-->
				<#--			 </tr>-->
			</table>
		</fieldset>



<#--		<fieldset>-->
<#--			<legend>其他信息</legend>-->
<#--			<table class="main">-->

<#--				<tr>-->

<#--					<td>-->
<#--						<label style="width:120px;">签约销售员:</label>-->
<#--						<input type="hidden" id="sm_num" name="t2.sm_num" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE)!'' }" data-rule=required" class="required" readonly="readonly"  >-->
<#--						<input type="text" id="sm_name" name="t2.sm_name" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE_SM_NAME)!'' }" data-rule=required" class="required" readonly="readonly"  >-->
<#--						<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-group="t2" id="haha1" data-width="1200" data-icon="search" class="btn-blue">查找</button>-->
<#--						<label id="info" style="margin-left:20px" />-->
<#--					</td>-->


<#--					<td>-->
<#--						<label style="width:120px;">维护销售员:</label>-->
<#--						<input type="hidden" id="sm_num" name="t1.sm_num" data-rule="required" data-target="#haha" value="${(agentinfo.MAINTAIN)!'' }"  data-rule=required"  class="required" readonly="readonly"  >-->
<#--						<input type="text" id="sm_name" name="t1.sm_name" data-rule="required" data-target="#haha1" value="${(agentinfo.MAINTAIN_SM_NAME)!'' }" data-rule=required" class="required" readonly="readonly"  >-->
<#--						<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-group="t1" id="haha" data-width="1200" data-icon="search" class="btn-blue">查找</button>-->
<#--						<label id="info" style="margin-left:20px" />-->
<#--					</td>-->
<#--				</tr>-->



<#--			</table>-->
<#--		</fieldset>-->


		<fieldset>
			<legend>代理商信息</legend>
			<table class="main">
				<td>
					<label style="width:120px;">代理商登录账号:</label>
					<input type="text" id="admin_name" <#if agentinfo??> readonly  unselectable="on" </#if> name="admin_name" data-rule="required" data-target="#haha1" value="${(agentinfo.ADMIN_NAME)!'' }"   >
					<label id="info" style="margin-left:20px" />
				</td>

				<td>

				</td>
			</table>
		</fieldset>

		<input  id="agent_num" type="hidden" name="agent_num" value="${(agentinfo.AGENT_NUM)!'' }"/>
		<input id="agent_status" type="hidden" name="agent_status" value="${(agentinfo.AGENT_STATUS)!'' }"/>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>


		<#if ck ??>
			<#else >
			<li><button id="btn" type="submit" class="btn-blue">保存</button></li>
		</#if>

	</ul>
</div>
<script language="javascript">

	<#if accounts ??>

	$("#nature").attr("disabled","disabled");

	var a=${(accounts.NATURE)!'0' }
	if(a == 1){
		$(".div2").hide();
		$(".a2").each(function (index,item) {
			$(item).attr("disabled", "disabled");
		});
		$(".div1").show();
		$(".a1").each(function (index,item) {
			$(item).attr("disabled", false);
		});
	}else if(a == 2){
		$(".div1").hide();
		$(".a1").each(function (index,item) {
			$(item).attr("disabled", "disabled");
		});
		$(".div2").show();
		$(".a2").each(function (index,item) {
			$(item).attr("disabled", false);
		});
	}
	<#else >
	$(".div2").hide();

	$(".a2").each(function (index,item) {
		$(item).attr("disabled", "disabled");
	});
	function checkstatus(){
		var values = document.getElementById("nature").value;
		if(values=="2"){
			document.getElementById("d1").style.display="inline";
		}else{
			document.getElementById("d1").style.display="none";
		}
	}

	$("#nature").change(function () {
		var nature=$(this).val();
		if(nature=='1'){
			$(".div2").hide();

			$(".a2").each(function (index,item) {
				$(item).attr("disabled", "disabled");
			});
			$(".div1").show();

			$(".a1").each(function (index,item) {
				$(item).attr("disabled", false);
			});
		}else if(nature=='2'){
			$(".div1").hide();
			$(".a1").each(function (index,item) {
				$(item).attr("disabled", "disabled");
			});
			$(".div2").show();
			$(".a2").each(function (index,item) {
				$(item).attr("disabled", false);
			});
		}
	});

	</#if>







</script>