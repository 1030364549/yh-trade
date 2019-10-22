



<style>

	th{
		text-align: center;
	}

</style>
<div class="bjui-pageContent">
	<form action="${baseClass}/saveFPD" id="j_custom_form" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
		<input type="hidden" name="id" value="${(id)!}"/>
		<input type="hidden" name="thisid" value="${(thisid)!}"/>
		<input type="hidden" name="update" value="${(update)!}"/>
		<input type="hidden" name="sign" value="${(sign)!}"/>
		<input type="hidden" name="agent_num" value="${(agent_num)!}"/>
		<input type="hidden" name="agent_level" value="${(agent_level)!}"/>
		<input type="hidden" name="belong_agent" value="${(belong_agent)!}"/>
		<span  style="display: none" id="MyList">${(MyList)!}</span>

		<table class="table table-bordered table-striped table-top" style="text-align: center">

			<thead>
			<tr style="text-align: center" >
				<th>分润类型</th>
				<th>商户类别</th>
				<th>卡类型</th>
				<th>结算类型</th>
				<th>结算底价(%)</th>
				<th>封顶成本(元/笔)</th>
<#--				<th>附加手续费底价(%)</th>-->
<#--				<th>附加手续费底价(元/笔)</th>-->
				<th>分润比例(%)</th>
			</tr>
			</thead>
			<tbody>
			<tr>
				<td class="a0" rowspan="12">银行卡收单</td>
				<td class="a00" rowspan="4">标准类</td>
				<td class="a000" rowspan="2">银联借记卡</td>
				<td class="a0001" >T+1</td>
				<td class="a0001" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-1-settlement_price" value="${(map.fp0001settlement_price)!'' }" /> </td>
				<td class="a0001" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-1-ceiling_cost" value="${(map.fp0001ceiling_cost)!'' }" /> </td>
<#--				<td class="a0001" ></td>-->
<#--				<td class="a0001" ></td>-->
				<td class="a0001" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-1-distribution_ratio" value="${(map.fp0001distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>

				<td class="a0000" >D+0</td>
				<td class="a0000" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-0-settlement_price" value="${(map.fp0000settlement_price)!'' }" /> </td>
				<td class="a0000" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-0-ceiling_cost" value="${(map.fp0000ceiling_cost)!'' }" /> </td>
<#--				<td class="a0000" ><input type="text" data-rule="number" name="fp-0-0-0-0-surcharge" value="${(map.fp0000surcharge)!'' }" /> </td>-->
<#--				<td class="a0000" ><input type="text" data-rule="number" name="fp-0-0-0-0-surcharge_ein" value="${(map.fp0000surcharge_ein)!'' }" /> </td>-->
				<td class="a0000" ><input type="text" data-rule="number,required" class="required" name="fp-0-0-1-0-distribution_ratio" value="${(map.fp0000distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>


				<td class="a001" rowspan="2">银联贷记卡</td>
				<td class="a0011">T+1</td>
				<td class="a0011"><input type="text" data-rule="number,required" class="required" name="fp-0-0-2-1-settlement_price" value="${(map.fp0011settlement_price)!'' }" /> </td>
				<td class="a0011"></td>
<#--				<td class="a0011"></td>-->
<#--				<td class="a0001"></td>-->
				<td class="a0011"><input type="text" data-rule="number,required" class="required" name="fp-0-0-2-1-distribution_ratio" value="${(map.fp0011distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>



				<td class="a0010">D+0</td>
				<td class="a0010"><input type="text" data-rule="number,required" class="required" name="fp-0-0-2-0-settlement_price" value="${(map.fp0010settlement_price)!'' }" /> </td>
				<td class="a0010"></td>
<#--				<td class="a0010"><input type="text" data-rule="number" name="fp-0-0-1-0-surcharge" value="${(map.fp0010surcharge)!'' }" /> </td>-->
<#--				<td class="a0010"><input type="text" data-rule="number" name="fp-0-0-1-0-surcharge_ein" value="${(map.fp0010surcharge_ein)!'' }" /> </td>-->
				<td class="a0010"><input type="text" data-rule="number,required" class="required" name="fp-0-0-2-0-distribution_ratio" value="${(map.fp0010distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>
				<td class="a01" rowspan="4">优惠类</td>
				<td class="a010" rowspan="2">银联借记卡</td>
				<td class="a0101" >T+1</td>
				<td class="a0101" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-1-settlement_price" value="${(map.fp0101settlement_price)!'' }" /> </td>
				<td class="a0101" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-1-ceiling_cost" value="${(map.fp0101ceiling_cost)!'' }" /> </td>
<#--				<td class="a0101" ></td>-->
<#--				<td class="a0101" ></td>-->
				<td class="a0101" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-1-distribution_ratio" value="${(map.fp0101distribution_ratio)!'' }" /> </td>
			</tr>

			<tr>
				<td class="a0100" >D+0</td>
				<td class="a0100" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-0-settlement_price" value="${(map.fp0100settlement_price)!'' }" /></td>
				<td class="a0100" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-0-ceiling_cost" value="${(map.fp0100ceiling_cost)!'' }" /></td>
<#--				<td class="a0100" ><input type="text" data-rule="number" name="fp-0-1-0-0-surcharge" value="${(map.fp0100surcharge)!'' }" /></td>-->
<#--				<td class="a0100" ><input type="text" data-rule="number" name="fp-0-1-0-0-surcharge_ein" value="${(map.fp0100surcharge_ein)!'' }" /></td>-->
				<td class="a0100" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-1-0-distribution_ratio" value="${(map.fp0100distribution_ratio)!'' }" /></td>
			</tr>
			<tr>
				<td class="a011" rowspan="2">银联贷记卡</td>
				<td class="a0111" >T+1</td>
				<td class="a0111" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-2-1-settlement_price" value="${(map.fp0111settlement_price)!'' }" /></td>
				<td class="a0111" ></td>
<#--				<td class="a0111" ></td>-->
<#--				<td class="a0111" ></td>-->
				<td class="a0111" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-2-1-distribution_ratio" value="${(map.fp0111distribution_ratio)!'' }" /></td>
			</tr>
			<tr>



				<td class="a0110" >D+0</td>
				<td class="a0110" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-2-0-settlement_price" value="${(map.fp0110settlement_price)!'' }" /></td>
				<td class="a0110" ></td>
<#--				<td class="a0110" ><input type="text" data-rule="number" name="fp-0-1-1-0-surcharge" value="${(map.fp0110surcharge)!'' }" /></td>-->
<#--				<td class="a0110" ><input type="text" data-rule="number" name="fp-0-1-1-0-surcharge_ein" value="${(map.fp0110surcharge_ein)!'' }" /></td>-->
				<td class="a0110" ><input type="text" data-rule="number,required" class="required" name="fp-0-1-2-0-distribution_ratio" value="${(map.fp0110distribution_ratio)!'' }" /></td>
			</tr>
			<tr>

				<td class="a02" rowspan="4">减免类</td>
				<td class="a020" rowspan="2">银联借记卡</td>
				<td class="a0201" >T+1</td>
				<td class="a0201" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-1-settlement_price" value="${(map.fp0201settlement_price)!'' }" /></td>
				<td class="a0201" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-1-ceiling_cost" value="${(map.fp0201ceiling_cost)!'' }" /></td>
<#--				<td class="a0201" ></td>-->
<#--				<td class="a0201" ></td>-->
				<td class="a0201" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-1-distribution_ratio" value="${(map.fp0201distribution_ratio)!'' }" /></td>
			</tr>
			<tr>



				<td class="a0200" >D+0</td>
				<td class="a0200" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-0-settlement_price" value="${(map.fp0200settlement_price)!'' }" /></td>
				<td class="a0200" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-0-ceiling_cost" value="${(map.fp0200ceiling_cost)!'' }" /></td>
<#--				<td class="a0200" ><input type="text" data-rule="number" name="fp-0-2-0-0-surcharge" value="${(map.fp0200surcharge)!'' }" /></td>-->
<#--				<td class="a0200" ><input type="text" data-rule="number" name="fp-0-2-0-0-surcharge_ein" value="${(map.fp0200surcharge_ein)!'' }" /></td>-->
				<td class="a0200" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-1-0-distribution_ratio" value="${(map.fp0200distribution_ratio)!'' }" /></td>
			</tr>
			<tr>


				<td class="a021" rowspan="2">银联贷记卡</td>
				<td class="a0211" >T+1</td>
				<td class="a0211" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-2-1-settlement_price" value="${(map.fp0211settlement_price)!'' }" /></td>
				<td class="a0211" ></td>
<#--				<td class="a0211" ></td>-->
<#--				<td class="a0211" ></td>-->
				<td class="a0211" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-2-1-distribution_ratio" value="${(map.fp0211distribution_ratio)!'' }" /></td>
			</tr>
			<tr>



				<td class="a0210" >D+0</td>
				<td class="a0210" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-2-0-settlement_price" value="${(map.fp0210settlement_price)!'' }" /></td>
				<td class="a0210" ></td>
<#--				<td class="a0210" ><input type="text" data-rule="number" name="fp-0-2-1-0-surcharge" value="${(map.fp0210surcharge)!'' }" /></td>-->
<#--				<td class="a0210" ><input type="text" data-rule="number" name="fp-0-2-1-0-surcharge_ein" value="${(map.fp0210surcharge_ein)!'' }" /></td>-->
				<td class="a0210" ><input type="text" data-rule="number,required" class="required" name="fp-0-2-2-0-distribution_ratio" value="${(map.fp0210distribution_ratio)!'' }" /></td>
			</tr>





			<tr>
				<td class="a1" rowspan="4" colspan="2">云闪付</td>

				<td class="a1990" rowspan="2">银联借记卡</td>
				<td class="a19901" >T+1</td>
				<td class="a19901" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-1-1-settlement_price" value="${(map.fp19901settlement_price)!'' }" /> </td>
				<td class="a19901" ></td>
<#--				<td class="a19901" ></td>-->
<#--				<td class="a19901" ></td>-->
				<td class="a19901" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-1-1-distribution_ratio" value="${(map.fp19901distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>



				<td class="a19900" >D+0</td>
				<td class="a19900" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-1-0-settlement_price" value="${(map.fp19900settlement_price)!'' }" /> </td>
				<td class="a19900" ></td>
<#--				<td class="a19900" ><input type="text" data-rule="number" name="fp-1-99-0-0-surcharge" value="${(map.fp19900surcharge)!'' }" /> </td>-->
<#--				<td class="a19900" ><input type="text" data-rule="number" name="fp-1-99-0-0-surcharge_ein" value="${(map.fp19900surcharge_ein)!'' }" /> </td>-->
				<td class="a19900" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-1-0-distribution_ratio" value="${(map.fp19900distribution_ratio)!'' }" /> </td>
			</tr>
			<tr>


				<td class="a1991"  rowspan="2">银联贷记卡</td>
				<td class="a19911" >T+1</td>
				<td class="a19911" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-2-1-settlement_price" value="${(map.fp19911settlement_price)!'' }" /> </td>
				<td class="a19911" ></td>
<#--				<td class="a19911" ></td>-->
<#--				<td class="a19911" ></td>-->
				<td class="a19911"><input type="text" data-rule="number,required" class="required" name="fp-1-99-2-1-distribution_ratio" value="${(map.fp19911distribution_ratio)!'' }" /> </td>
			</tr>


			<tr>



				<td class="a19910" >D+0</td>
				<td class="a19910" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-2-0-settlement_price" value="${(map.fp19910settlement_price)!'' }" /> </td>
				<td class="a19910" ></td>
<#--				<td class="a19910" ><input type="text" data-rule="number" name="fp-1-99-1-0-surcharge" value="${(map.fp19910surcharge)!'' }" /> </td>-->
<#--				<td class="a19910" ><input type="text" data-rule="number" name="fp-1-99-1-0-surcharge_ein" value="${(map.fp19910surcharge_ein)!'' }" /> </td>-->
				<td class="a19910" ><input type="text" data-rule="number,required" class="required" name="fp-1-99-2-0-distribution_ratio" value="${(map.fp19910distribution_ratio)!'' }" /> </td>
			</tr>


			<tr>
				<td class="a2"  rowspan="4" colspan="2">银联二维码</td>
				<td class="a2990" rowspan="2">银联借记卡</td>
				<td class="a29901">T+1</td>
				<td class="a29901" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-1-settlement_price" value="${(map.fp29901settlement_price)!'' }" /> </td>
				<td class="a29901" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-1-ceiling_cost" value="${(map.fp29901ceiling_cost)!'' }" /> </td>
<#--				<td class="a29901" ></td>-->
<#--				<td class="a29901" ></td>-->
				<td class="a29901" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-1-distribution_ratio" value="${(map.fp29901distribution_ratio)!'' }" /> </td>
			</tr>


			<tr>



				<td class="a29900" >D+0</td>
				<td class="a29900" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-0-settlement_price" value="${(map.fp29900settlement_price)!'' }" /></td>
				<td class="a29900" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-0-ceiling_cost" value="${(map.fp29900ceiling_cost)!'' }" /></td>
<#--				<td class="a29900" ><input type="text" data-rule="number" name="fp-2-99-0-0-surcharge" value="${(map.fp29900surcharge)!'' }" /></td>-->
<#--				<td class="a29900" ><input type="text" data-rule="number" name="fp-2-99-0-0-surcharge_ein" value="${(map.fp29900surcharge_ein)!'' }" /></td>-->
				<td class="a29900" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-1-0-distribution_ratio" value="${(map.fp29900distribution_ratio)!'' }" /></td>
			</tr>
			<tr>



				<td  class="a2991"  rowspan="2">银联贷记卡</td>
				<td  class="a29911"  >T+1</td>
				<td class="a29911" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-2-1-settlement_price" value="${(map.fp29911settlement_price)!'' }" /></td>
				<td class="a29911" ></td>
<#--				<td class="a29911" ></td>-->
<#--				<td class="a29911" ></td>-->
				<td class="a29911" ><input type="text" data-rule="number,required" class="required" name="fp-2-99-2-1-distribution_ratio" value="${(map.fp29911distribution_ratio)!'' }" /></td>
			</tr>
			<tr>
				<td  class="a29910" >D+0</td>
				<td class="a29910"><input type="text" data-rule="number,required" class="required" name="fp-2-99-2-0-settlement_price" value="${(map.fp29910settlement_price)!'' }" /></td>
				<td class="a29910"></td>
<#--				<td class="a29910"><input type="text" data-rule="number" name="fp-2-99-1-0-surcharge" value="${(map.fp29910surcharge)!'' }" /></td>-->
<#--				<td class="a29910"><input type="text" data-rule="number" name="fp-2-99-1-0-surcharge_ein" value="${(map.fp29910surcharge_ein)!'' }" /></td>-->
				<td class="a29910"><input type="text" data-rule="number,required" class="required" name="fp-2-99-2-0-distribution_ratio" value="${(map.fp29910distribution_ratio)!'' }" /></td>
			</tr>






			<tr>
				<td class="a3" rowspan="2" colspan="3">微信</td>
				<td class="a399991">T+1</td>
				<td class="a399991"><input type="text" data-rule="number,required" class="required" name="fp-3-99-99-1-settlement_price" value="${(map.fp399991settlement_price)!'' }" /></td>
				<td class="a399991"></td>
<#--				<td class="a399991"></td>-->
<#--				<td class="a399991"></td>-->
				<td class="a399991"><input type="text" data-rule="number,required" class="required" name="fp-3-99-99-1-distribution_ratio" value="${(map.fp399991distribution_ratio)!'' }" /></td>
			</tr>
			<tr>
				<td class="a399990" >D+0</td>
				<td class="a399990"><input type="text" data-rule="number,required" class="required" name="fp-3-99-99-0-settlement_price" value="${(map.fp399990settlement_price)!'' }" /></td>
				<td class="a399990"></td>
<#--				<td class="a399990"><input type="text" data-rule="number" name="fp-3-99-99-0-surcharge" value="${(map.fp399990surcharge)!'' }" /></td>-->
<#--				<td class="a399990"><input type="text" data-rule="number" name="fp-3-99-99-0-surcharge_ein" value="${(map.fp399990surcharge_ein)!'' }" /></td>-->
				<td class="a399990"><input type="text" data-rule="number,required" class="required" name="fp-3-99-99-0-distribution_ratio" value="${(map.fp399990distribution_ratio)!'' }" /></td>
			</tr>

			<tr>

				<td class="a4" rowspan="2" colspan="3">支付宝</td>

				<td class="a499991">T+1</td>
				<td class="a499991"><input type="text" data-rule="number,required" class="required" name="fp-4-99-99-1-settlement_price" value="${(map.fp499991settlement_price)!'' }" /></td>
				<td class="a499991"></td>
<#--				<td class="a499991"></td>-->
<#--				<td class="a499991"></td>-->
				<td class="a499991"><input type="text" data-rule="number,required" class="required" name="fp-4-99-99-1-distribution_ratio" value="${(map.fp499991distribution_ratio)!'' }" /></td>
			</tr>
			<tr>

				<td class="a499990">D+0</td>
				<td class="a499990"><input type="text" data-rule="number,required" class="required" name="fp-4-99-99-0-settlement_price" value="${(map.fp499990settlement_price)!'' }" /></td>
				<td class="a499990"></td>
<#--				<td class="a499990"><input type="text" data-rule="number" name="fp-4-99-99-0-surcharge" value="${(map.fp499990surcharge)!'' }" /></td>-->
<#--				<td class="a499990"><input type="text" data-rule="number" name="fp-4-99-99-0-surcharge_ein" value="${(map.fp499990surcharge_ein)!'' }" /></td>-->
				<td class="a499990"><input type="text" data-rule="number,required" class="required" name="fp-4-99-99-0-distribution_ratio" value="${(map.fp499990distribution_ratio)!'' }" /></td>
			</tr>

			</tbody>
		</table>
	</form>
</div>
<div class="bjui-pageFooter">
	<ul>
		<li><button type="button" class="btn-close" data-icon="close">取消</button></li>
		<li><button type="submit" class="btn-default" data-icon="save">保存</button></li>
	</ul>
</div>


