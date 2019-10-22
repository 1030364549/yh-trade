
<div class="bjui-pageContent">
	<form method="post" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false" enctype="multipart/form-data">

        <#--商户结算信息查询-->
        <fieldset style="margin-top: 10px;">
            <legend>商户结算信息查询:</legend>
            <p style="margin-top: 10px;">
                <label style="width:120px;">商户编号:</label>
                <input type="text" name="seq_id" readonly value="${(settlementMap.SEQ_ID)!}"/>
                <label style="width: 120px;margin-left: 40px;">开户行名称:</label>
                <input type="text" name="bank_name" readonly value="${(settlementMap.BANK_NAME)!}"/>

            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">结算账户号:</label>
                <input type="text" name="show_screennum" readonly value="${(settlementMap.SHOW_SCREENNUM)!}"/>
                <label style="width:120px;margin-left: 40px;">开户人名称:</label>
                <input type="text" name="screen_name" readonly value="${(settlementMap.SCREEN_NAME)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">支付系统行号:</label>
                <input type="text" name="bank_num" readonly value="${(settlementMap.BANK_NUM)!}"/>
                <label style="width:120px;margin-left: 40px;">添加时间:</label>
                <input type="text" name="createdate" readonly value="${(settlementMap.CREATEDATE)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">账户类型:</label>
                <select name="nature" readonly disabled selectvl="${(settlementMap.NATURE)!''}" data-toggle="selectpicker" data-width="195" data-emptytxt="请选择">
                    <option value="">请选择</option>
                    <option value="2">对私</option>
                    <option value="1">对公</option>
                </select>
                <label style="width:120px;margin-left: 40px;">添加人:</label>
                <input type="text" name="createman" readonly value="${(settlementMap.CREATEMAN)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px;">结算周期:</label>
                <select name="settle_cycle" readonly disabled selectvl="${(settlementMap.SETTLE_CYCLE)!''}" data-toggle="selectpicker" data-width="195" data-emptytxt="请选择">
                    <option value="">请选择</option>
                    <option value="0">D0</option>
                    <option value="1">T1</option>
                </select>
                <label style="width:120px;margin-left: 40px;"">账户开户地:</label>
                <input type="text" name="area_name" readonly value="${(settlementMap.AREA_NAME)!}"/>
            </p>
            <p style="margin-top: 10px;">
                <label style="width:120px">总行名称:</label>
                <input type="text" name="bank_headname" readonly value="${(settlementMap.BANK_HEADNAME)!}"/>
            </p>
        </fieldset>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close">关闭</button></li>
	</ul>
</div>