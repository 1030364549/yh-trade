<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/saveOrUpdate"
		data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<fieldset>
			<legend>账户信息</legend>
			<table class="main">
				<tr>
					<td>
						<p>
							<label style="width:90px;">结算人:</label> <input id="screen_name"
																		   type="text" name="screen_name"
																		   value="${(accounts.SCREEN_NAME)!'' }" data-rule="required"
																		   class="required" maxlength="20"/> <label id="info" style="margin-left:20px" /><p>
					</td>
					<td>
						<p>
							<label style="width:90px;">结算卡:</label> <input id="screen_num"
																		   type="text" name="screen_num"
																		   value="${(accounts.SCREEN_NUM)!'' }"
																		   data-rule="required,digits" maxlength="20" class="required" /> <label
									id="info" style="margin-left:20px" /><p>
					</td>
				</tr>
				<tr>
					<td>
						<p>
							<label style="width:90px;">开户行名称:</label> <input id="bank_name"
																			 type="text" name="bank_name" value="${(accounts.BANK_NAME)!'' }"
																			  maxlength="35" /> <label id="info"
																																			style="margin-left:20px" /><p>
					</td>
					<td>
						<p>
							<label style="width:90px;">支付系统行号:</label> <input id="bank_num"
																			  type="text" name="bank_num" value="${(accounts.BANK_NUM)!'' }"
																			  maxlength="15" /> <label
									id="info" style="margin-left:20px" /><p>
					</td>
				</tr>
				<tr>
					<td>
						<p>
							<label style="width:90px;">预留手机号:</label> <input id="resphone"
																			 type="text" name="resphone" value="${(accounts.RESPHONE)!'' }"
																			 data-rule="required,mobile" class="required" maxlength="11" /> <label id="info"
																																				   style="margin-left:20px" /><p>
					</td>
					<td>
						<p>
							<label style="width:90px;">账户开户地:</label> <input id="bank_address"
																			 type="text" name="bank_address" value="${(accounts.BANK_ADDRESS)!'' }"
																			 placeholder="省市" maxlength="35"  /> <label
									id="info" style="margin-left:20px" /><p>
					</td>
				</tr>
				<tr>
					<td>
						<p>
							<label style="width:90px;">总行名称:</label> <input id="bank_headname"
																			type="text" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }"
																			data-rule="required" class="required" maxlength="30" /> <label id="info"
																																		   style="margin-left:20px" /><p>
					</td>
					<td>
						<p>
							<label style="width:90px;">账户状态:</label> <select id="status" data-toggle="selectpicker" data-width="208"
																			 name="status" selectvl="${(accounts.STATUS)!''}"
																			 style="height: 25px;">
								<option value="0">正常</option>
								<option value="1">冻结</option>
							</select> <label id="info" style="margin-left:20px" /><p>
					</td>
				</tr>
				<tr>
					<td>
						<p>
							<label style="width:90px;">结算周期:</label> <select id="settle_cycle" data-toggle="selectpicker" data-width="208"
																			 name="settle_cycle" selectvl="${(accounts.SETTLE_CYCLE)!''}"
																			 style="height: 25px;">
								<option value="T1">工作日</option>
								<option value="D0">实时</option>
							</select> <label id="info" style="margin-left:20px" /><p>

					</td>
					<td>
						<p>
							<label style="width:90px;">起始结算金额:</label> <select id="acmoney" data-toggle="selectpicker" data-width="208"
																			   name="acmoney" selectvl="${(accounts.ACMONEY)!''}" maxlength="10"
																			   style="height: 25px;">
								<option value="10">10</option>
								<option value="50">50</option>
							</select> <label id="info" style="margin-left:20px" /><p>

					</td>
				</tr>
				<tr>
					<td>
						<p>
							<label style="width:90px;">账户类型:</label> <select class="selector"  id="nature" data-toggle="selectpicker" data-width="208" onChange="checkstatus();"
																			 name="nature" selectvl="${(accounts.NATURE)!''}"
																			 style="height: 25px;">
								<option value="1">对公账户</option>
								<option value="2">对私账户</option>
							</select> <label id="info" style="margin-left:20px" /></p>



					</td>
					<td id="d1">
						<p>
							<label style="width:90px;">结算人身份证号:</label> <input id="screen_idnum"
																			   type="text" name="screen_idnum" value="${(accounts.SCREEN_IDNUM)!'' }"
																			   data-rule="ID_card" maxlength="18" placeholder="对私账户必填"  /> <label
									id="info" style="margin-left:20px" />
						</p>

					</td>
				</tr>
				<#--<tr id="d1">

				</tr>-->
			</table>
			<input id="merno" type="hidden" name="merno" value="${(accounts.SEQ_ID)!'' }" />
			<input id="da_marker" type="hidden" name="da_marker" value="${(accounts.DA_MARKER)!'' }" />
			<input id="oldnature" type="hidden" name="oldnature" value="${(accounts.NATURE)!'' }" />
			<input id="oldscreen_name" type="hidden" name="oldscreen_name" value="${(accounts.SCREEN_NAME)!'' }" />
		</fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
		<li><button id="btn" type="submit" class="btn-blue">保存</button></li>
	</ul>
</div>
<script language="javascript">
	//页面初始化
    $(document).ready(function() {
        var values = $("#oldnature").val();
        if(values=="2"){
            document.getElementById("d1").style.display="inline";
        }
        if(values=="1"){
            document.getElementById("d1").style.display="none";
        }
    });

    function checkstatus(){
        var values1 = $(".selector").val();
        if(values1=="2"){
            document.getElementById("d1").style.display="inline";
        }else{
            document.getElementById("d1").style.display="none";
        }
    }
</script>

