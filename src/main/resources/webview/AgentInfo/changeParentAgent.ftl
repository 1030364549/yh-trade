<div class="bjui-pageContent">
 <form method="post" action="${baseClass}/updateAgentparent" data-toggle="validate" data-reload-navtab="true" data-alertmsg="false">
  <fieldset>
   <p>
    <label style="width:80px;">所属代理商:</label>
    <input type="text" id="agent_num" name="t2.agent_num"  data-target="#agent" value="${(merchinfo.agent_num)!'' }" data-rule="required" class="required" size="15" readonly="readonly">
    <button href="/yhtrade/AgentInfo/findParentAgent?parent_level=${(level)!''}" id="agent" data-toggle="lookupbtn" data-group="t2" data-width="1200" data-icon="search" class="btn-blue">查找</button>
    <label id="info" style="margin-left:20px" /><p>
   </p>
   <p>
    <label style="width:80px;">备注:</label>
    <textarea class="required" name="note" style="width:250px;height:60px" tools="simple" id="note">${(agentinfo.note)!'' }</textarea>
   </p>
  </fieldset>
  <input id="agent_status" type="hidden" name="agent_status" value="${(agent_status)!'' }"/>
  <input id="agent_num" type="hidden" name="agent_num" value="${(agent_num)!'' }"/>
 </form>
</div>
<div class="bjui-pageFooter">
 <ul>
  <li><button type="button" class="btn-close">关闭</button></li>
  <li><button id="btn" type="submit" class="btn-blue">保存</button></li>
 </ul>
</div>