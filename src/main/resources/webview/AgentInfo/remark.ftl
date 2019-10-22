<div class="bjui-pageContent">
 <form method="post" action="${baseClass}/agentRepulse" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
  <fieldset>
   <p>
	<label style="width:80px;">代理商编号:</label> 
	<input id="agent_num" type="text" name="agent_num" value="${(agent_num)!'' }" readonly="readonly" size="25"/> 
	<label id="info" style="margin-left:20px" />
   </p>
   <p>
    <label style="width:80px;">备注:</label> 
    <textarea class="required" name="note" data-rule="required" maxlength="90" style="width:250px;height:60px" tools="simple" id="note">${(agentinfo.note)!'' }</textarea>
   </p>
  </fieldset>
  <input id="agent_status" type="hidden" name="agent_status" value="${(agent_status)!'' }"/>
 </form>
</div>
<div class="bjui-pageFooter">
 <ul>
  <li><button type="button" class="btn-close">关闭</button></li>
  <li><button id="btn" type="submit" class="btn-blue">保存</button></li>
 </ul>
</div>