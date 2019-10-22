
<div class="bjui-pageContent">
	<form method="post" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false" enctype="multipart/form-data">

        <#--商户结算信息查询-->
        <fieldset style="margin-top: 10px;">
            <legend>商户工商信息查询:</legend>
            <p style="margin-top: 10px;">
                <label style="width:120px;">商户编号:</label>
                <input type="text" name="MERNO" readonly value="${(merchantSchedule.MERNO)!}"/>
                <label style="width: 120px;margin-left: 40px;">统一社会信用代码:</label>
                <input type="text" name="bank_name" readonly value="${(merchantSchedule.SOCIAL_REDIT_CODE)!}"/>

            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">信用代码有效期开始时间:</label>
                <input type="text" name="show_screennum" readonly value="${(merchantSchedule.SRC_START_DATE)!}"/>
                <label style="width:120px;margin-left: 40px;">信用代码有效期结束时间:</label>
                <input type="text" name="createdate" readonly value="${(merchantSchedule.SRC_END_DATE)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">主营业务:</label>
                <input type="text" name="bank_num" readonly value="${(merchantSchedule.MAIN_BUSINESS)!}"/>
                <label style="width:120px;margin-left: 40px;">经营范围:</label>
                <input type="text" name="createdate" readonly value="${(merchantSchedule.BUSINESS_SCOPE)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">开始营业时间:</label>
                <input type="text" name="bank_num" readonly value="${(merchantSchedule.BUSINESS_STARTTIME)!}"/>
                <label style="width:120px;margin-left: 40px;">结束营业时间:</label>
                <input type="text" name="createdate" readonly value="${(merchantSchedule.BUSINESS_ENDTIME)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">注册资金:</label>
                <input type="text" name="bank_num" readonly value="${(merchantSchedule.REGISTERED_CAPITAL)!}"/>
                <label style="width:120px;margin-left: 40px;">注册地址:</label>
                <input type="text" name="createdate" readonly value="${(merchantSchedule.SHOP_ADDRESS)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">成立日期:</label>
                <input type="text" name="bank_num" readonly value="${(merchantSchedule.ESTABLISH_DATE)!}"/>
                <label style="width:120px;margin-left: 40px;">协议编号:</label>
                <input type="text" name="screen_name" readonly value="${(merchantSchedule.AGREEMENT_NO)!}"/>
            </p>
        </fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
	</ul>
</div>