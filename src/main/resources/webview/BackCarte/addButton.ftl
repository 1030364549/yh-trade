<script>
	 $("#bottonsz").click();
</script>
<div class="bjui-pageContent" >
    <form method="post" action="/yhtrade/BackCarte/saveOrUpdateButton"  data-toggle="validate" data-reload-navtab="false" onsubmit="return $(this).dialog('closeCurrent',true)">
       <fieldset >
			<p>
				<label style="width:80px;">按钮名称：</label> 
				<input type="hidden" name="carte_id" id="carte_id" value="#{carte_id}">
				<input type="hidden" name="bt_id" id="bt_id" value="${(bt.BT_ID)!'' }">
				<input type="text" id="bt_name" name="bt_name" value="${(bt.BT_NAME)!'' }" class = "required"  size="20" placeholder="名称"/>
				<label id="info" style="margin-left:20px"/>
			</p> 
			<p>
				<label style="width:80px;">本级URL：</label> 
				<input type="text" name="bt_href" size="27" value="${(bt.BT_HREF)!'' }"  class="required" placeholder="AdminInfo/info"/>
			</p>
			<p>
				<label style="width:80px;">子级URL：</label> 
				<input type="text" name="bt_sc_href" value="${(bt.BT_SC_HREF)!'' }" size="27" placeholder="AdminInfo/info" class="required"/>&nbsp;
				<span style="color:red;font-size:10px;">没有则输入 "#" </span>
			</p>
			<p>
				<label style="width:80px;">备注：</label> 
				<textarea class="required" name="remark" style="width:200px;height:60px" tools="simple" id="j_menu_js">${(bt.REMARK)!'' }</textarea>
			</p>
		</fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button"   class="btn-close">关闭</button></li>
        <li><button id="btn" type="submit"  class="btn btn-green">保存</button></li>
    </ul>
</div>