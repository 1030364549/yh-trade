<style>
	.main td {
		width: 400px;
	}
</style>


<link href="/yhtrade/public/imgZoom/jquery.artZoom.css" rel="stylesheet" type="text/css" media="screen"/>
<script src="/yhtrade/public/imgZoom/jquery.artZoom.min.js" type="text/javascript"></script>

<div class="bjui-pageContent">
	<form method="post" action="${baseClass}/saveOrUpdate" data-toggle="validate"
		  data-reload-navtab="true" data-alertmsg="false" data-callback="mycallback1" id="myForm1" >

		<#if agentinfo??>

			<div style="margin:15px auto 0; width:900px;">

					<!-- Tabs -->
					<ul class="nav nav-tabs" role="tablist">
						<li class="active"><a href="#info" role="tab" data-toggle="tab">商户信息</a></li>
						<li><a href="#img" role="tab" data-toggle="tab">商户资质信息</a></li>
					</ul>
					<!-- Tab panes -->
					<div class="tab-content">
						<div class="tab-pane fade active in" id="info">

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
											<p>
												<label style="width:120px;">统一社会信用代码:</label>
												<input id="organ_code" type="text" name="organ_code" value="${(agentinfo.ORGAN_CODE)!'' }" maxlength="20" data-rule="required" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>
										</td>
									</tr>


									<tr>
										<td>
											<p>
												<label style="width:120px;">代理商性质:</label>

												<select id="agent_nature" data-toggle="selectpicker" data-width="208" name="agent_nature" <#if agent_num??> selectvl="${(agentinfo.AGENT_NATURE)!''}" </#if> style="height: 25px;">
													<option value="0">经销商</option>
													<option value="1">渠道商</option>
												</select>
                                                <span style="color:red;">*</span>
											<p>
										</td>

										<td>
                                            <p>
                                                <label style="width:120px;">代理商地区:</label>
                                                <select name="province" id="province"  data-rule="required" class="required"  data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.PROVINCE)!}" </#if> </#if>  data-nextselect="#city" data-refurl="/yhtrade/Area/findArea?index={value}">
                                                    <option value="">请选择</option>
													<#if areaMap ??>
														<#list areaMap as ms>
															<option value="${(ms.AREA_ID)!}">
																${(ms.AREA_NAME)!}
                                                            </option>
														</#list>
													</#if>
                                                </select>
                                                <span style="color:red;">*</span>

                                                <select name="city" id="city" data-rule="required" class="required" data-toggle="selectpicker"  <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.CITY)!''}" </#if> </#if> data-nextselect="#j_form_area" data-width="77" data-refurl="/yhtrade/Area/findArea?index={value}" data-emptytxt="请选择">
                                                    <option value="">请选择</option>
													<#if shiMap ??>
														<#list shiMap as ms>
															<option value="${(ms.AREA_ID)!}"> ${(ms.AREA_NAME)!}</option>
														</#list>
													</#if>
                                                </select>
                                                <span style="color:red;">*</span>
                                                <label id="info" style="margin-left:20px" />
                                            </p>
										</td>

									</tr>

									<#if ck ??>
										<tr>
											<td>
												<p>
													<label style="width:120px;">代理商等级:</label>
													<select id="agent_level" data-toggle="selectpicker"  name="agent_level"  selectvl="${(agentinfo.AGENT_LEVEL)!''}"  style="height: 25px;">
														<option value="">请选择</option>
                                                        <option value="-1">顶级服务商</option>
                                                        <option value="0">服务商</option>
														<option value="1">一级</option>
														<option value="2">二级</option>
														<option value="3">三级</option>
														<option value="4">四级</option>
														<option value="5">五级</option>
														<option value="6">六级</option>
														<option value="7">七级</option>
														<option value="8">八级</option>
														<option value="9">九级</option>
													</select>
													<label id="info" style="margin-left:20px" />
												<p>
											</td>

											<td>
												<p>
													<label style="width:120px;">所属代理商:</label>
													<input id="up_agent_name" type="text" name="up_agent_name" value="${(agentinfo.UP_AGENT_NAME)!'' }"  maxlength="20" data-rule="required" class="required" />
													<label id="info" style="margin-left:20px" />
												</p>
											</td>

										</tr>


										<tr>
											<td>
												<p>
													<label style="width:120px;">结算周期:</label>

													<select id="profit_type" data-toggle="selectpicker"  name="profit_type"  selectvl="${(agentinfo.PROFIT_TYPE)!''}"  style="height: 25px;">
														<option value="">请选择</option>
														<option value="0">日结</option>
														<option value="1">月结</option>
													</select>

													<label id="info" style="margin-left:20px" />
												<p>
											</td>

											<td>
												<p>
													<label style="width:120px;">分润状态:</label>

													<select id="profit_status" data-toggle="selectpicker"  name="profit_status"  selectvl="${(agentinfo.PROFIT_STATUS)!''}"  style="height: 25px;">
														<option value="">请选择</option>
														<option value="0">启用</option>
														<option value="1">关闭本级分润</option>
														<option value="2">关闭本级,级下级分润</option>
													</select>


													<label id="info" style="margin-left:20px" />
												</p>
											</td>

										</tr>
									</#if>

									<tr>
										<td colspan="2">
                                            <p>
                                                <label style="width:120px;">详细地址:</label>
                                                <input id="agent_area" type="text" name="agent_area" value="${(agentinfo.AGENT_AREA)!'' }" size="40" maxlength="25" data-rule="required" class="required" />
                                                <label id="info" style="margin-left:20px" />
                                            </p>
										</td>
									</tr>

									<tr>
										<td>
											<p>
												<label style="width:120px;">法人名称:</label>
												<input id="commissary" type="text" name="commissary" value="${(agentinfo.COMMISSARY)!'' }" maxlength="20" data-rule="required" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>
										</td>

										<td>
											<p>
												<label style="width:120px;">法人身份证号码:</label>
												<input id="identity_num" type="text" name="identity_num" value="${(agentinfo.IDENTITY_NUM)!'' }" maxlength="18"  data-rule="required,ID_card" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>
										</td>

									</tr>


									<tr>
										<td>

											<p>
												<label style="width:120px;">联系人姓名:</label>
												<input id="link_man" type="text" name="link_man" value="${(agentinfo.LINK_MAN)!'' }"  maxlength="20" data-rule="required" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>
										</td>
										<td>
											<p>
												<label style="width:120px;">手机号码:</label>
												<input id="link_phone" type="text" name="link_phone" value="${(agentinfo.LINK_PHONE)!'' }" maxlength="11"  data-rule="required,mobile" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>
										</td>
									</tr>


									<tr>
										<td>
											<p>
												<label style="width:120px;">联系人邮箱:</label>
												<input id="link_email" type="text" name="link_email" value="${(agentinfo.LINK_EMAIL)!'' }" maxlength="25" data-rule="required,email" class="required" />
												<label id="info" style="margin-left:20px" />
											</p>

										</td>
										<td>
											<p>
												<label style="width:120px;">保证金金额(万元):</label>
												<input id="deposit_money" type="text" name="deposit_money" value="${(agentinfo.DEPOSIT_MONEY)!'0' }"  maxlength="10" data-rule="required,digits"  class="required" />
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
                                            <p>
                                                <label style="width:120px;">账户类型:</label>
                                                <select data-toggle="selectpicker" name="nature">
                                                    <option value="1">对公</option>
                                                </select><span style="color:red;">*</span>
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <label style="width:120px;">开户账户名称:</label>
                                                <input id="screen_name" type="text" name="screen_name"
                                                       value="${(accounts.SCREEN_NAME)!'' }" data-rule="required"
                                                       class="required" maxlength="20"/> <label id="info" style="margin-left:20px" />
                                            </p>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <p>
                                                <label style="width:120px;">开户银行账户:</label>
                                                <input id="screen_num" type="text" name="screen_num" value="${(accounts.SCREEN_NUM)!'' }"
                                                       data-rule="required,length(6~25)" maxlength="25" class="required" />
                                                <label id="info" style="margin-left:20px" />
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                                <label style="width:120px;">总行名称:</label>
                                                <input type="text" id="bank_headname" data-tagname="bank_headname"  value="${(accounts.BANK_HEADNAME)!'' }" data-width="190" data-toggle="tags" data-url="/yhtrade/AgentInfo/getBank"  data-max="1" size="15" placeholder="输入关键字，回车提交"><span style="color:red;">*</span>
								<#if agentinfo??>
									<input type="hidden" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }" >
								</#if>
                                            <p>
                                        </td>
                                    </tr>



                                    <tr>
                                        <td>
                                            <p>
                                                <label style="width:120px;">支行名称:</label>
                                                <input id="bank_name" type="text" name="bank_name" value="${(accounts.BANK_NAME)!'' }"
                                                       data-rule="required" class="required" maxlength="35" />
                                                <label id="info" style="margin-left:20px" />
                                            </p>
                                        </td>
                                        <td>

                                            <p>
                                                <label style="width:120px;">账户开户地:</label>
                                                <select name="bank_address" id="province1" data-rule="required" class="required" data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??>  selectvl="${(accounts.bank_address_province)!}" </#if> </#if> data-nextselect="#j_form_city1" data-refurl="/yhtrade/Area/findArea?index={value}">
                                                    <option value="">请选择</option>
													<#if areaMap ??>
														<#list areaMap as ms>
															<option value="${(ms.AREA_ID)!}">
																${(ms.AREA_NAME)!}
															</option>
														</#list>
													</#if>
                                                </select>
                                                <span style="color:red;">*</span>
                                                <select name="bank_address" id="j_form_city1" data-rule="required" class="required" data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(accounts.bank_address_city)!''}" </#if> </#if> data-nextselect="#j_form_area1" data-width="77" data-refurl="/yhtrade/Area/findArea?index={value}" data-emptytxt="请选择">
                                                    <option value="">请选择</option>
													<#if shiMap ??>
														<#list shiMap1 as ms>
															<option value="${(ms.AREA_ID)!}">${(ms.AREA_NAME)!}</option>
														</#list>
													</#if>
                                                </select>
                                                <span style="color:red;">*</span>
                                            </p>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td>
                                            <p>
                                                <label style="width:120px;">支付系统行号:</label>
                                                <input id="bank_num" type="text" name="bank_num" value="${(accounts.BANK_NUM)!'' }"
                                                       data-rule="required,digits" class="required" maxlength="12" />
                                                <label id="info" style="margin-left:20px" />
                                            </p>
                                        </td>
                                        <td>
                                            <p>
                                            </p>
                                        </td>
                                    </tr>
								</table>
							</fieldset>


							<fieldset>
								<legend>其他信息</legend>
								<table class="main">

									<tr>

										<td>
											<label style="width:120px;">销售经理:</label>
											<input type="hidden" id="sm_num" name="t2.sm_num" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE)!'' }" data-rule=required" class="required" readonly="readonly"  >
											<input type="text" id="sm_name" size="15" name="t2.sm_name" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE_SM_NAME)!'' }" data-rule=required" class="required" readonly="readonly"  >
											<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="t2" id="haha1" data-width="1200" data-icon="search" class="btn-blue">查找</button>
											<label id="info" style="margin-left:20px" />
										</td>


										<td>
											<label style="width:120px;">运营经理:</label>
											<input type="hidden" id="sm_num" name="t1.sm_num" data-rule="required" data-target="#haha" value="${(agentinfo.MAINTAIN)!'' }"  data-rule=required"  class="required" readonly="readonly"  >
											<input type="text" id="sm_name" size="15" name="t1.sm_name" data-rule="required" data-target="#haha" value="${(agentinfo.MAINTAIN_SM_NAME)!'' }" data-rule=required" class="required" readonly="readonly"  >
											<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="t1" id="haha" data-width="1200" data-icon="search" class="btn-blue">查找</button>
											<label id="info" style="margin-left:20px" />
										</td>
									</tr>


									<tr>

										<td colspan="2">
											<label style="width:120px;">有效期:</label>
											<input type="text" name="begin_valid" readonly value="${(agentinfo.BEGIN_VALID)!''}" data-toggle="datepicker" data-rule="required" class="form-control,required" size="11"><span style="color:red;">*</span> &nbsp;
											<input type="text" name="end_valid" readonly value="${(agentinfo.END_VALID)!''}" data-toggle="datepicker" data-rule="required" class="form-control,required" size="11"><span style="color:red;">*</span> &nbsp;
										</td>
									</tr>

								</table>
							</fieldset>

							<fieldset>
								<legend>代理商信息</legend>
								<table class="main">
									<td>
										<label style="width:120px;">代理商登录账号:</label>
										<input type="text" id="admin_name" <#if agentinfo??> readonly  unselectable="on" </#if> name="admin_name" data-rule="required" value="${(agentinfo.ADMIN_NAME)!'' }" class="required"  >
										<label id="infod" style="margin-left:20px" />
									</td>

									<td>

									</td>
								</table>
							</fieldset>

						</div>

						<div class="tab-pane fade" id="img">

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
														<#case "15"><span style="color: blue">组织机构代码证</span><#break>
														<#case "16"><span style="color: blue">开户许可证</span><#break>
														<#case "17"><span style="color: blue">代理商情况说明表</span><#break>
														<#default>
													</#switch>

												</td>
												<td >${(list.CREATENAME)!'<span style="color:gray">暂无</span>'}</td>
												<td >${(list.CREATETIME)!'<span style="color:gray">暂无</span>'}</td>
											</tr>
											<#--<tr><td colspan="5"><img src="${showPath}${(list.FILEID)!}"></td></tr>-->
											<tr><td colspan="5" align="center"> <img style="width:80px;height:80px;" alt="点击查看大图（旋转照片）" src="${showPath}${(list.FILEID)!}" class="artZoom imgsss" data-artZoom-show="${showPath}${(list.FILEID)!}"  data-artZoom-source="${showPath}${(list.FILEID)!}"></td></tr>
											</tbody>
										</table>
									</#list>
								</#if>
							</div>

						</div>
					</div>
			</div>

		<#else >
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
							<p>
								<label style="width:120px;">统一社会信用代码:</label>
								<input id="organ_code" type="text" name="organ_code" value="${(agentinfo.ORGAN_CODE)!'' }" maxlength="20" data-rule="required" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>


					<tr>
						<td>
							<p>
								<label style="width:120px;">代理商性质:</label>

								<select id="agent_nature" data-toggle="selectpicker" name="agent_nature" <#if agent_num??> selectvl="${(agentinfo.AGENT_NATURE)!''}" </#if> style="height: 25px;">
									<option value="0">经销商</option>
									<option value="1">渠道商</option>
								</select>
                                <span style="color:red;">*</span>

							<p>
						</td>

                        <td>
                            <p>
                                <label style="width:120px;">代理商等级:</label>
                                <select id="agent_level" data-toggle="selectpicker" name="agent_level" <#if agent_num??> selectvl="${(agentinfo.AGENT_LEVEL)!''}" </#if> style="height: 25px;">
                                    <option value="-1">顶级服务商</option>
                                    <option value="0">服务商</option>
                                    <option value="1">一级代理商</option>
                                </select>
                                <span style="color:red;">*</span>

                            <p>
                        </td>

					</tr>

					<tr>
                        <td >
                            <p>
                                <label style="width:120px;">代理商地区:</label>
                                <select name="province" id="province"  data-rule="required" class="required"  data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.PROVINCE)!}" </#if> </#if>  data-nextselect="#city" data-refurl="/yhtrade/Area/findArea?index={value}">
                                    <option value="">请选择</option>
									<#if areaMap ??>
										<#list areaMap as ms>
                                            <option value="${(ms.AREA_ID)!}">
											${(ms.AREA_NAME)!}
                                            </option>
										</#list>
									</#if>
                                </select>
                                <span style="color:red;">*</span>
                                <select name="city" id="city" data-rule="required" class="required" data-toggle="selectpicker"  <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(agentinfo.CITY)!''}" </#if> </#if> data-nextselect="#j_form_area" data-width="77" data-refurl="/yhtrade/Area/findArea?index={value}" data-emptytxt="请选择">
                                    <option value="">请选择</option>
									<#if shiMap ??>
										<#list shiMap as ms>
                                            <option value="${(ms.AREA_ID)!}"> ${(ms.AREA_NAME)!}</option>
										</#list>
									</#if>
                                </select>
                                <span style="color:red;">*</span>
                                <label id="info" style="margin-left:20px" />
                            </p>
                        </td>
						<td >
                            <p>
                                <label style="width:120px;">详细地址:</label>
                                <input id="agent_area" type="text" name="agent_area" value="${(agentinfo.AGENT_AREA)!'' }" size="25" maxlength="25" data-rule="required" class="required" />
                                <label id="info" style="margin-left:20px" />
                            </p>
						</td>
					</tr>

					<tr>
						<td>
							<p>
								<label style="width:120px;">法人名称:</label>
								<input id="commissary" type="text" name="commissary" value="${(agentinfo.COMMISSARY)!'' }" maxlength="20" data-rule="required" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>

						<td>
							<p>
								<label style="width:120px;">法人身份证号码:</label>
								<input id="identity_num" type="text" name="identity_num" value="${(agentinfo.IDENTITY_NUM)!'' }" maxlength="18"  data-rule="required,ID_card" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>

					</tr>


					<tr>
						<td>

							<p>
								<label style="width:120px;">联系人姓名:</label>
								<input id="link_man" type="text" name="link_man" value="${(agentinfo.LINK_MAN)!'' }"  maxlength="20" data-rule="required" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
						<td>
							<p>
								<label style="width:120px;">手机号码:</label>
								<input id="link_phone" type="text" name="link_phone" value="${(agentinfo.LINK_PHONE)!'' }" maxlength="11"  data-rule="required,mobile" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>


					<tr>
						<td>
							<p>
								<label style="width:120px;">联系人邮箱:</label>
								<input id="link_email" type="text" name="link_email" value="${(agentinfo.LINK_EMAIL)!'' }" maxlength="25" data-rule="required,email" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>

						</td>
						<td>
							<p>
								<label style="width:120px;">保证金金额(万元):</label>
								<input id="deposit_money" type="text" name="deposit_money" value="${(agentinfo.DEPOSIT_MONEY)!'0' }"  maxlength="10" data-rule="required,digits"  class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>

				</table>
			</fieldset>


			<fieldset>
				<legend>账户信息</legend>
				<table class="main">
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
					<tr>
						<td>
							<p>
								<label style="width:120px;">账户类型:</label>
                                <select data-toggle="selectpicker" name="nature">
                                    <option value="1">对公</option>
                                </select>
                                <span style="color:red;">*</span>
							</p>
						</td>
                        <td>
							<p>
								<label style="width:120px;">开户账户名称:</label>
                                <input id="screen_name" type="text" name="screen_name"
                                       value="${(accounts.SCREEN_NAME)!'' }" data-rule="required"
                                       class="required" maxlength="20"/> <label id="info" style="margin-left:20px" />
							</p>
						</td>
					</tr>
					<tr>
						<td>
							<p>
								<label style="width:120px;">开户银行账户:</label>
                                <input id="screen_num" type="text" name="screen_num" value="${(accounts.SCREEN_NUM)!'' }"
                                       data-rule="required,length(6~25)" maxlength="25" class="required" />
								<label id="info" style="margin-left:20px" />
							</p>
						</td>
						<td>
							<p>
								<label style="width:120px;">总行名称:</label>
                                <input type="text" id="bank_headname" data-tagname="bank_headname"  value="${(accounts.BANK_HEADNAME)!'' }" data-width="190" data-toggle="tags" data-url="/yhtrade/AgentInfo/getBank"  data-max="1" size="15" placeholder="输入关键字，回车提交"><span style="color:red;">*</span>
								<#if agentinfo??>
									<input type="hidden" name="bank_headname" value="${(accounts.BANK_HEADNAME)!'' }" >
								</#if>
							<p>
						</td>
					</tr>



					<tr>
						<td>
							<p>
								<label style="width:120px;">支行名称:</label>
                                <input id="bank_name" type="text" name="bank_name" value="${(accounts.BANK_NAME)!'' }"
                                       data-rule="required" class="required" maxlength="35" />
                                <label id="info" style="margin-left:20px" />
							</p>
						</td>
						<td>

							<p>
								<label style="width:120px;">账户开户地:</label>
                                <select name="bank_address" id="province1" data-rule="required" class="required" data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??>  selectvl="${(accounts.bank_address_province)!}" </#if> </#if> data-nextselect="#j_form_city1" data-refurl="/yhtrade/Area/findArea?index={value}">
                                    <option value="">请选择</option>
									<#if areaMap ??>
										<#list areaMap as ms>
											<option value="${(ms.AREA_ID)!}">
												${(ms.AREA_NAME)!}
                                            </option>
										</#list>
									</#if>
                                </select>
                                <span style="color:red;">*</span>
                                <select name="bank_address" id="j_form_city1" data-rule="required" class="required" data-toggle="selectpicker" <#if agentinfo??> <#if agentinfo.AGENT_NUM??> selectvl="${(accounts.bank_address_city)!''}" </#if> </#if> data-nextselect="#j_form_area1" data-width="77" data-refurl="/yhtrade/Area/findArea?index={value}" data-emptytxt="请选择">
                                    <option value="">请选择</option>
									<#if shiMap ??>
										<#list shiMap1 as ms>
											<option value="${(ms.AREA_ID)!}">${(ms.AREA_NAME)!}</option>
										</#list>
									</#if>
                                </select>
                                <span style="color:red;">*</span>
							</p>
						</td>
					</tr>

					<tr>
						<td>
							<p>
								<label style="width:120px;">支付系统行号:</label>
                                <input id="bank_num" type="text" name="bank_num" value="${(accounts.BANK_NUM)!'' }"
                                       data-rule="required,digits" class="required" maxlength="12" />
                                <label id="info" style="margin-left:20px" />
							</p>
						</td>
						<td>
							<p>
							</p>
						</td>
					</tr>
				</table>
			</fieldset>


			<fieldset>
				<legend>其他信息</legend>
				<table class="main">

					<tr>

						<td>
							<label style="width:120px;">销售经理:</label>
							<input type="hidden" id="sm_num" name="t2.sm_num" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE)!'' }" class="required" readonly="readonly"  >
							<input type="text" size="15" id="sm_name" name="t2.sm_name" data-rule="required" data-target="#haha1" value="${(agentinfo.SIGN_SALE_SM_NAME)!'' }" class="required" readonly="readonly"  >
							<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="t2" id="haha1" data-width="1200" data-icon="search" class="btn-blue">查找</button>

						</td>


						<td>
							<label style="width:120px;">运营经理:</label>
							<input type="hidden" id="sm_num" name="t1.sm_num" data-rule="required" data-target="#haha" value="${(agentinfo.MAINTAIN)!'' }" class="required" readonly="readonly"  >
							<input type="text" size="15" id="sm_name" name="t1.sm_name" data-rule="required" data-target="#haha" value="${(agentinfo.MAINTAIN_SM_NAME)!'' }" class="required" readonly="readonly"  >
							<button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="t1" id="haha" data-width="1200" data-icon="search" class="btn-blue">查找</button>

						</td>
					</tr>

					<tr>

						<td colspan="2">
							<label style="width:120px;">有效期:</label>
							<input type="text" name="begin_valid" readonly value="${(agentinfo.BEGIN_VALID)!''}" data-toggle="datepicker" data-rule="required" class="form-control,required" size="11"><span style="color:red;">*</span> &nbsp;
							<input type="text" name="end_valid" readonly value="${(agentinfo.END_VALID)!''}" data-toggle="datepicker" data-rule="required" class="form-control,required" size="11"><span style="color:red;">*</span> &nbsp;
						</td>
					</tr>
				</table>
			</fieldset>

			<fieldset>
				<legend>代理商信息</legend>
				<table class="main">
					<tr>
                        <td colspan="2">
                            <label style="width:120px;">代理商登录账号:</label>
                            <input type="text" id="admin_name1" <#if agentinfo??> readonly  unselectable="on" </#if> name="admin_name" data-rule="required" value="${(agentinfo.ADMIN_NAME)!'' }" class="required"  >
                            <label id="infod" style="margin-left:2px" />
                        </td>
					</tr>
				</table>
			</fieldset>

		</#if>

		<input id="agent_num" type="hidden" name="agent_num" value="${(agentinfo.AGENT_NUM)!'' }"/>
		<input id="agent_status" type="hidden" name="agent_status" value="${(agentinfo.AGENT_STATUS)!'' }"/>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>


		<#if ck ??>

			<#if ck =='1' >
				<span><button type="button" onclick="agentUpdate(${(agentinfo.AGENT_NUM)!'' },3)" class="btn-blue">初步审核</button></span>
				<span><a class="btn btn-red" href="${baseClass}/remark?agent_num=${(agentinfo.AGENT_NUM)!'' }&agent_status=4" data-toggle="dialog" rel="BaseinfoClass_update" data-options="{id:'dialog',title:'代理商打回',width:'500',height:'240'}" id="cancelBtn"><i class="fa fa-edit"></i><span>代理商打回</span></a>&nbsp;&nbsp;</span>
			</#if>

			<#if ck =='2' >
				<span><button type="button" onclick="agentUpdate(${(agentinfo.AGENT_NUM)!'' },0)" class="btn-blue">代理商审核</button></span>

				<span><a class="btn btn-red" href="${baseClass}/remark?agent_num=${(agentinfo.AGENT_NUM)!'' }&agent_status=2" data-toggle="dialog" rel="BaseinfoClass_update" data-options="{id:'dialog',title:'代理商打回',width:'500',height:'240'}" id="cancelBtn"><i class="fa fa-edit"></i><span>代理商打回</span></a>&nbsp;&nbsp;</span>
			</#if>

		<#elseif agentinfo??>
			<li><button id="btn" type="submit" class="btn-blue">保存</button></li>
		<#else>
			<li><button id="btn" type="button" class="btn-blue" onclick="submitBtn()">下一步</button></li>

		</#if>

	</ul>
</div>


<script language="javascript">
    $("#admin_name1").keyup(function(){
        var reg=/^[0-9a-zA-Z]+$/;
        var admin_name = $("#admin_name1").val();
        //判断输入框中有内容
        if(!reg.test(admin_name)) {
            $('#infod').html('<span style="color:red">只能输入英文、数字</span>');
            $("#btn").attr("disabled", "disabled");
        }else{
            $('#infod').html('<span style="color:green"></span>');
            $("#btn").removeAttr("disabled");
		}
    });

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

	function checkstatus(){
		var values = document.getElementById("nature").value;
		if(values=="2"){
			document.getElementById("d1").style.display="inline";
		}else{
			document.getElementById("d1").style.display="none";
		}
	}

	function submitBtn() {

			$("#myForm1").submit();
	}

	function mycallback1(json){

		if(json.statusCode==200){
            //$(this).dialog('closeCurrent');//关闭当前弹窗
			$(this).dialog({id:'dialog', url:'/yhtrade/AgentInfo/uploadImg/?agent_num='+json.merno, title:'上传图片资料',width:600,height:500});
		}else{
			$(this).alertmsg('error', json.message);
		}
	}
	//审核
	function agentUpdate(agentnum,agentstatus) {
        $.ajax({
            type: "post",
            url: "${baseClass}/agentUpdate",
            data: {
				"agent_num":agentnum,
				"agent_status":agentstatus
			},
            error:function(response){
                $(this).alertmsg('error',"异常");
            },
            success:function(data){
                if(data.statusCode=="200"){
                    $(this).alertmsg('ok',data.message);
                    $(this).dialog('closeCurrent',true);
                    //刷新局部页面
                    $.CurrentNavtab.find("#pagerForm").submit();
                }else{
                    $(this).alertmsg('error',data.message);
                }
            }
        });
    }

</script>