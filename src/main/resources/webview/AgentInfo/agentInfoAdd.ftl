<div class="bjui-pageContent">
    <form method="post" action="${baseClass}/saveAgentInfo"
          data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
            <legend>机构信息:</legend>
            <p>
                <label style="width:80px;">机构名称:</label>
                <input id="agent_name" type="text" value="${(agentinfo.AGENT_NAME)!'' }" name="agent_name" data-rule="required,length(0~60)" maxlength="60" class="required" placeholder="机构名称" />
                <label id="info" style="margin-left:2px" />
				<label style="width:80px;">注册地址:</label>
                <input id="agent_area" type="text" value="${(agentinfo.AGENT_AREA)!'' }" name="agent_area" data-rule="required,length(0~50)" maxlength="50" class="required" placeholder="注册地址" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">法人姓名:</label>
                <input id="commissary" type="text" value="${(agentinfo.COMMISSARY)!'' }" name="commissary" data-rule="required,length(0~30)" maxlength="30" class="required" placeholder="法人姓名" />
                <label id="info" style="margin-left:2px" />
            <p>
            <p>
            	<label style="width:80px;">法人身份证:</label>
            	<input id="identity_num" type="text" value="${(agentinfo.SHOW_IDENTITYNUM)!'' }" name="identity_num"<#if agentinfo??><#if agentinfo.AGENT_NUM??> disabled </#if><#else > data-rule="required,ID_card,length(0~25)" maxlength="25"</#if> class="required" placeholder="法人身份证" />
            	<label id="info" style="margin-left:2px" />
				<label style="width:80px;">社会统一信息码:</label>
				<input id="organ_code" type="text" value="${(agentinfo.ORGAN_CODE)!'' }" name="organ_code" class="required" data-rule="required,length(0~50)" maxlength="50" placeholder="社会统一信息码" />
				<label id="info" style="margin-left:2px" />
				<label style="width:80px;">工商登记号:</label>
				<input id="regist_num" type="text" value="${(agentinfo.REGIST_NUM)!'' }" name="regist_num" class="required" data-rule="required,length(0~50)" maxlength="50" placeholder="工商登记号" />
				<label id="info" style="margin-left:2px" />

			</p>
            <p>
                <label style="width:80px;">联系人姓名:</label>
                <input id="link_man" type="text" value="${(agentinfo.LINK_MAN)!'' }" name="link_man" class="required" data-rule="required,length(0~50)" maxlength="50" placeholder="联系人姓名" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">联系人手机号:</label>
                <input id="link_phone" type="text" value="${(agentinfo.SHOW_RESPHONE)!'' }" name="link_phone" class="required"<#if agentinfo??><#if agentinfo.AGENT_NUM??> disabled </#if><#else >data-rule="required,mobile,length(0~11)" maxlength="11"</#if >  placeholder="联系人手机号" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">联系人邮箱:</label>
                <input id="link_email" type="text" value="${(agentinfo.LINK_EMAIL)!'' }" name="link_email" class="required" data-rule="required,email,length(0~50)" maxlength="50" placeholder="联系人邮箱" />
                <label id="info" style="margin-left:2px" />
			</p>
            <p>
                <label style="width:80px;">保证金金额:</label>
                <input id="deposit_money" type="text" value="${(agentinfo.DEPOSIT_MONEY)!'' }" name="deposit_money" class="required" data-rule="required,digits,length(0~10)" maxlength="10" placeholder="保证金金额" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">起始有效期:</label>
                <input id="begin_valid" type="text" value="${(agentinfo.BEGIN_VALID)!'' }" name="begin_valid" readonly data-toggle="datepicker" data-rule="required,length(0~20)" maxlength="20" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">结束有效期:</label>
                <input id="end_valid" type="text" value="${(agentinfo.END_VALID)!'' }" name="end_valid" readonly data-toggle="datepicker" data-rule="required,length(0~20)" maxlength="20"/>
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:80px;">省市:</label>
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
                <label id="info" style="margin-left:2px" />
			</p>
		</fieldset>
		<fieldset style="margin-top: 10px;">
			<legend>账户信息:</legend>
            <p>
                <label style="width:80px;">总行名称:</label>
                <input id="bank_headname" type="text" value="${(agentinfo.BANK_HEADNAME)!'' }" name="bank_headname" class="required" data-rule="required,length(0~100)" maxlength="100" placeholder="总行名称" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">开户行名称:</label>
                <input id="bank_name" type="text" value="${(agentinfo.BANK_NAME)!'' }" name="bank_name" class="required" data-rule="required,length(0~100)" maxlength="100" placeholder="开户行名称" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">开户行账号:</label>
                <input id="screen_num" type="text" value="${(agentinfo.SHOW_SCREENNUM)!'' }" name="screen_num" class="required"<#if agentinfo??><#if agentinfo.AGENT_NUM??> disabled </#if><#else > data-rule="required,length(6~25)" maxlength="25"</#if> placeholder="开户行账号" />
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:80px;">结算人名称:</label>
                <input id="screen_name" type="text" value="${(agentinfo.SCREEN_NAME)!'' }" name="screen_name" class="required" data-rule="required,length(0~50)" maxlength="50" placeholder="开户人名称" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">支付系统行号:</label>
                <input id="bank_num" type="text" value="${(agentinfo.BANK_NUM)!'' }" name="bank_num" class="required" data-rule="required,length(6~20)" maxlength="20" placeholder="支付系统行号" />
                <label id="info" style="margin-left:2px" />
                <label style="width:80px;">账户开户地:</label>
                <select name="accounts_province" id="accounts_province"  data-rule="required" class="required"  data-toggle="selectpicker"
                <#if agentinfo??> <#if agentinfo.ID??> selectvl="${(agentinfo.ACCOUNTS_PROVINCE)!}" </#if> </#if>  data-nextselect="#accounts_city" data-refurl="${base}/Area/findArea?index={value}">
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
                <select name="accounts_city" id="accounts_city" data-rule="required" class="required" data-toggle="selectpicker"
                <#if agentinfo??> <#if agentinfo.ID??> selectvl="${(agentinfo.ACCOUNTS_CITY)!''}" </#if> </#if> data-nextselect="#j_form_area" data-width="77" data-refurl="${base}/Area/findArea?index={value}" data-emptytxt="请选择">
                    <option value="">请选择</option>
					<#if shiMap1 ??>
                        <#list shiMap1 as ms>
                            <option value="${(ms.AREA_ID)!}"> ${(ms.AREA_NAME)!}</option>
                        </#list>
                    </#if>
                </select>
                </select>
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:120px;">账户类型:</label>
                <input type="radio" name="nature" readonly id="doc-radio1" data-toggle="icheck" value="1" data-label="对公" <#if agentinfo?? ><#if agentinfo.NATURE ??><#if agentinfo.NATURE == 1>checked</#if></#if></#if>>
                <input type="radio" name="nature" readonly  id="doc-radio2" data-toggle="icheck" value="2" data-label="对私" <#if agentinfo?? ><#if agentinfo.NATURE ??><#if agentinfo.NATURE == 2>checked</#if><#else>checked</#if><#else>checked</#if>>
                <label id="info" style="margin-left:2px" />
            </p>
            <p>
                <label style="width:80px;">备注:</label>
                <textarea id="note" name="note" style="width:330px;height:100px" tools="simple" data-rule="length(0~400)" placeholder="备注" >${(agentinfo.ACCOUNTS_NOTE)!}</textarea>
            </p>
        </fieldset>
        <input id="agent_num" type="hidden" name="agent_num" value="${(agentinfo.AGENT_NUM)!'' }"/>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn" type="submit" class="btn-blue">保存</button></li>
    </ul>
</div>