<script language="javascript">
    function checkRate(e) {
        var reg=/^[C]{1}[A]{1}[P]{1}\d{1,9}[R]{1}\d{1,9}$/;
        var regR=/^[R]{1}\d{1,9}$/;
        var waitReg = $(e).val();
        //判断输入框是否是正确的规则
        if(!reg.test(waitReg) && !regR.test(waitReg)) {
            $(e).nextAll('label').first().html('<span style="color:red" class="error">规则错误</span>');
            $("#btn").attr("disabled", "disabled");
        }else {
            $(e).nextAll('label').first().html("");
            $("#btn").removeAttr("disabled");
        }
    }
</script>
<div class="bjui-pageContent">
    <form method="post" <#if agentinfo??><#if agentinfo.STANDARD_RATED??><#else> action="${baseClass}/saveAgentRelated" </#if></#if>
          data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
        <fieldset style="margin-top: 10px;">
            <legend>机构信息:</legend>
            <p>
                <label style="width:80px;">机构编号:</label>
                <input id="agent_num" type="text" value="${(agentinfo.AGENT_NUM)!'' }" readonly name="agent_num" data-rule="required,length(0~50)" class="required" placeholder="机构编号" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">机构名称:</label>
                <input id="agent_name" type="text" value="${(agentinfo.AGENT_NAME)!'' }" readonly name="agent_name" data-rule="required,length(0~60)" class="required" placeholder="机构名称" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>

            <p>
		</fieldset>
		<fieldset style="margin-top: 10px;">
			<legend>标准类:</legend>
            <p>
                <label style="width:80px;">借记D1费率:</label>
                <input id="debit_standard_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_STANDARD_RATED)!'' }"  <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_standard_rated" class="required" data-rule="required,length(0~50)" placeholder="借记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">借记T0费率:</label>
                <input id="debit_standard_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_STANDARD_RATES)!'' }" <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_standard_rates" class="required" data-rule="required,length(0~50)" placeholder="借记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
            <p>
                <label style="width:80px;">贷记D1费率:</label>
                <input id="credit_standard_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_STANDARD_RATED)!'' }"  <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_standard_rated" class="required" data-rule="required,length(0~50)" placeholder="贷记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">贷记T0费率:</label>
                <input id="credit_standard_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_STANDARD_RATES)!'' }" <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_standard_rates" class="required" data-rule="required,length(0~50)" placeholder="贷记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
        </fieldset>
        <fieldset style="margin-top: 10px;">
            <legend>优惠类:</legend>
            <p>
                <label style="width:80px;">借记D1费率:</label>
                <input id="debit_discount_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_DISCOUNT_RATED)!'' }" <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_discount_rated" class="required" data-rule="required,length(0~50)" placeholder="借记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">借记T0费率:</label>
                <input id="debit_discount_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_DISCOUNT_RATES)!'' }" <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_discount_rates" class="required" data-rule="required,length(0~50)" placeholder="借记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
            <p>
                <label style="width:80px;">贷记D1费率:</label>
                <input id="credit_discount_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_DISCOUNT_RATED)!'' }" <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_discount_rated" class="required" data-rule="required,length(0~50)" placeholder="贷记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">贷记T0费率:</label>
                <input id="credit_discount_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_DISCOUNT_RATES)!'' }" <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_discount_rates" class="required" data-rule="required,length(0~50)" placeholder="贷记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
        </fieldset>
        <fieldset style="margin-top: 10px;">
            <legend>减免类:</legend>
            <p>
                <label style="width:80px;">借记D1费率:</label>
                <input id="debit_reduction_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_REDUCTION_RATED)!'' }" <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_reduction_rated" class="required" data-rule="required,length(0~50)" placeholder="贷记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">借记T0费率:</label>
                <input id="debit_reduction_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.DEBIT_REDUCTION_RATES)!'' }" <#if agentinfo??><#if agentinfo.DEBIT_STANDARD_RATED??> disabled </#if></#if> name="debit_reduction_rates" class="required" data-rule="required,length(0~50)" placeholder="贷记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
            <p>
                <label style="width:80px;">贷记D1费率:</label>
                <input id="credit_reduction_rated" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_REDUCTION_RATED)!'' }" <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_reduction_rated" class="required" data-rule="required,length(0~50)" placeholder="借记D1费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
                <label style="width:80px;">贷记T0费率:</label>
                <input id="credit_reduction_rates" type="text" onkeyup="checkRate(this)" value="${(agentinfo.CREDIT_REDUCTION_RATES)!'' }" <#if agentinfo??><#if agentinfo.CREDIT_STANDARD_RATED??> disabled </#if></#if> name="credit_reduction_rates" class="required" data-rule="required,length(0~50)" placeholder="借记T0费率" />
                <label id="info" style="margin-left:2px;width: 50px" ></label>
            </p>
        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn" type="submit" <#if agentinfo??><#if agentinfo.STANDARD_RATED??> style="display:none;" </#if></#if> class="btn-blue">保存</button></li>
    </ul>
</div>
