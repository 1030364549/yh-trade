<div class="bjui-pageHeader">
 <form id="pagerForm" data-toggle="ajaxsearch" action="${urls}" method="post">
  <div class="bjui-searchBar">
      <p>
          &nbsp;&nbsp; <span>代理商编号：</span>
          <input type="text" name="agent_num" value="${(p.params.agent_num)!''}" class="form-control" size="15"> &nbsp;

          <span>代理商名称：</span>
          <input type="text" name="agent_name" value="${(p.params.agent_name)!''}" class="form-control" size="15"> &nbsp;
          <#--<span>法人身份证号码(模糊查询)：</span>
          <input   placeholder="输入身份证前十位进行模糊搜索"  type="text" al name="show_identitynum" value="${(p.params.show_identitynum)!''}" class="form-control" size="15"> &nbsp;-->
          <span>法人身份证号码(精准查询)：</span>
          <input   placeholder="输入全部身份证号检索" style="font-size:12px;"  type="text" al name="_identity_num" value="${(p.params._identity_num)!''}" class="form-control" size="15"> &nbsp;
      </p>
      <p>
          &nbsp;&nbsp; <span>销售经理:</span>&nbsp;&nbsp;
          <input type="hidden" style="width: 120px" id="sm_num" name="sign_sale.sm_num"  data-target="#haha1" value="${(p.params.sign_sale)!'' }"  readonly="readonly"  >
          <input type="text" style="width: 120px" id="sm_name" name="sign_sale.sm_name"  data-target="#haha1" value="${(p.params.sign_sale_sm_name)!'' }"  readonly="readonly"  >
          <button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="sign_sale" id="haha1" data-width="1200" data-icon="search" class="btn-blue"></button>

          &nbsp;&nbsp;&nbsp;&nbsp;<span>运营经理:</span>&nbsp;
          <input type="hidden" style="width: 120px"  id="sm_num" name="maintain.sm_num"  data-target="#haha" value="${(p.params.maintain)!'' }"  readonly="readonly"  >
          <input type="text" style="width: 120px" id="sm_name" name="maintain.sm_name"  data-target="#haha1" value="${(p.params.maintain_sm_name)!'' }"  readonly="readonly"  >
          <button href="/yhtrade/Salesman/findSales" data-toggle="lookupbtn" data-title="业务员查询" data-group="maintain" id="haha" data-width="1200" data-icon="search" class="btn-blue"></button>

          &nbsp;&nbsp;&nbsp;&nbsp;<span>登录账号:</span>
          <input type="text" style="width: 120px" id="admin_name" name="admin_name"  data-target="#haha1" value="${(p.params.admin_name)!'' }"    >
      </p>
      <p>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>部门: </span>&nbsp;&nbsp;
          <select name="department" id="province1" data-toggle="selectpicker"  selectvl="${(p.params.department)!}"   data-nextselect="#j_form_department" data-refurl="/yhtrade/Salesman/getCompanydept?comp_id={value}">
              <option value="">请选择</option>
              <#if CompayDeptList ??>
                  <#list CompayDeptList as list>
                      <option value="${(list.CD_ID)!}">
                          ${(list.CD_NAME)!}
                      </option>
                  </#list>
              </#if>
          </select>

          &nbsp;&nbsp;&nbsp;&nbsp;<span>大区: </span>&nbsp;&nbsp;
          <select name="argeaarea" id="j_form_department" data-toggle="selectpicker"   selectvl="${(p.params.argeaarea)!''}"   data-nextselect="#j_form_largearea" data-refurl="/yhtrade/Salesman/getCompanydept?comp_id={value}" data-emptytxt="请选择">
              <option value="">请选择</option>
              <#if ARGEAAREA ??>
                  <#list ARGEAAREA as ms>
                      <option value="${(ms.CD_ID)!}">${(ms.CD_NAME)!}</option>
                  </#list>
              </#if>
          </select>

          &nbsp;&nbsp;&nbsp;&nbsp;<span>分公司: </span>&nbsp;&nbsp;
          <select name="branchoffice" id="j_form_largearea" data-toggle="selectpicker"  selectvl="${(p.params.branchoffice)!''}"  data-nextselect="#j_form_branchoffice"  data-refurl="/yhtrade/Salesman/getCompanydept?comp_id={value}" data-emptytxt="请选择">
              <option value="">请选择</option>
              <#if BRANCHOFFICE ??>
                  <#list BRANCHOFFICE as ms>
                      <option value="${(ms.CD_ID)!}">${(ms.CD_NAME)!}</option>
                  </#list>
              </#if>
          </select>

          &nbsp;&nbsp;&nbsp;&nbsp; <span>审核状态：</span>&nbsp;&nbsp;
          <select id="agent_nature" data-toggle="selectpicker"  name="agent_status"  selectvl="${(p.params.agent_status)!''}"  style="height: 25px;">
              <option value="">请选择</option>
              <option value="1">未提交申请</option>
              <option value="4">已驳回</option>
          </select>
          &nbsp;&nbsp;&nbsp;&nbsp; <span>代理商性质：</span>&nbsp;&nbsp;
          <select id="agent_nature" data-toggle="selectpicker"  name="agent_nature"  selectvl="${(p.params.agent_nature)!''}"  style="height: 25px;">
              <option value="">请选择</option>
              <option value="0">经销商</option>
              <option value="1">渠道商</option>
          </select>
      </p>
      <p>
          &nbsp;&nbsp;&nbsp;<span>添加时间(起始)：</span>
          <input type="text" readonly name="startlocaldate" value="${(p.params.startlocaldate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
          <span>添加时间(截止)：</span>
          <input type="text" readonly name="endlocaldate" value="${(p.params.endlocaldate)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;

          &nbsp;&nbsp;<span>有效期(起始)：</span>
          <input type="text" readonly name="begin_valid" value="${(p.params.begin_valid)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;
          &nbsp;&nbsp;<span>有效期(截止)：</span>
          <input type="text" readonly name="end_valid" value="${(p.params.end_valid)!''}" data-toggle="datepicker" class="form-control" size="11"> &nbsp;

          <input type="hidden" name="showType" value="1" >&nbsp;

           <button type="submit" class="btn-blue" data-icon="search">查询</button>&nbsp;
           <a class="btn btn-orange" href="javascript:;" onclick="$(this).navtab('reloadForm', true)" data-icon="undo">清空查询</a>&nbsp;
      </p>
      <div style="margin-top: 1px;">
	    <span><a class="btn btn-blue" href="${baseClass}/add?agent_num=" data-toggle="dialog" rel="AgentInfo_add" data-options="{id:'dialog',title:'添加代理商',width:'900',height:'700'}"><i class="fa fa-edit"></i><span>添加代理商</span></a>&nbsp;&nbsp;</span>
        <span><a class="btn btn-blue" href="${baseClass}/add?{#bjui-selected}" data-toggle="dialog"  rel="AgentInfo_update" data-options="{id:'dialog',title:'编辑代理商',width:'950',height:'700'}"><i class="fa fa-edit"></i><span>编辑代理商</span></a>&nbsp;&nbsp;</span>
	    <span><a class="btn btn-blue" href="${baseClass}/uploadImg?{#bjui-selected}" data-toggle="dialog"  rel="AgentInfo_uploadImg" data-options="{id:'dialog',title:'上传图片资料',width:'660',height:'500'}"><i class="fa fa-edit"></i><span>上传图片资料</span></a>&nbsp;&nbsp;</span>
	    <span><a class="btn btn-red" href="${baseClass}/delete?{#bjui-selected}" data-toggle="doajax"  data-confirm-msg = "确定要删除代理商信息么？" rel="AgentInfo_delete" width="450" ><i class="fa fa-edit"></i><span>删除代理商</span></a>&nbsp;&nbsp;</span>
      </div>
      <div class="pull-right"></div>
  </div>
  <#include "/common/pageForm.ftl">
 </form>
</div>
<div class="bjui-pageContent tableContent">
 <table data-toggle="tablefixed" data-width="100%" data-nowrap="true">
  <thead>
   <tr>
   <th width="26">&nbsp;</th>
    <th width="6%">代理商编号</th>
	<th width="10%">代理商名称</th>
	<th width="7%">区域</th>
	<th width="5%">法人姓名</th>
    <th width="10%">身份证</th>
	<th width="7%">联系电话</th>
	<th width="4%">状态</th>
    <th width="4%">等级</th>
       <th width="6%">登陆账号</th>
   <th width="6%">销售经理</th>
   <th width="6%">运营经理</th>
       <th width="15%">部门-大区-分公司</th>
       <th width="6%">有效期(起始)</th>
       <th width="5%">有效期(截止)</th>
	<th width="4%">添加人</th>
	<th width="10%">添加日期</th>
    <th width="6%">备注</th>
       <th width="6%">操作</th>
   </tr>
  </thead>
  <tbody>
   <#if p.results ??>
    <#list p.results as list>
	 <tr data-id="agent_num=${(list.AGENT_NUM)!}">
         <td><input type="radio" name="agentnumra" disabled data-toggle="icheck" value="${(list.AGENT_NUM)!}"></td>
	  <td >${(list.AGENT_NUM)!}</td>
	  <td >${(list.AGENT_NAME)!}</td>
	  <td >${(list.AREA_NAME)!'<span style="color:gray">无</span>'}</td>
	  <td >${(list.COMMISSARY)!'<span style="color:gray">无</span>'}</td>
	  <td >${(list.SHOW_IDENTITYNUM)!'<span style="color:gray">无</span>'}</td>
	  <td >${(list.LINK_PHONE)!'<span style="color:gray">无</span>'}</td>
	  <td >
        <#if list.AGENT_STATUS==1>
            申请
        <#elseif list.AGENT_STATUS==2>
            初审
        <#elseif list.AGENT_STATUS==3>
            终审
        <#elseif list.AGENT_STATUS==4>
            打回
        <#elseif list.AGENT_STATUS==5>
            注销
        <#elseif list.AGENT_STATUS==0>
            正式
        <#else>
            默认等级
        </#if>
	  </td>
	  <td >
	     <#if list.AGENT_LEVEL==1>
              一级
	        <#elseif list.AGENT_LEVEL==2>
             二级
            <#elseif list.AGENT_LEVEL==3>
             三级
            <#elseif list.AGENT_LEVEL==4>
             四级
            <#elseif list.AGENT_LEVEL==5>
             五级
            <#elseif list.AGENT_LEVEL==6>
             六级
            <#else>
             默认等级
	     </#if>
	  </td>

         <td >${(list.ADMIN_NAME)!'<span style="color:gray">无</span>'}</td>



         <td >${(list.SM_NAME)!'<span style="color:gray">无</span>'}</td>
         <td >${(list.SM_NAME1)!'<span style="color:gray">无</span>'}</td>
         <td >
             ${(list.DEPARTMENTNAME)!'<span style="color:gray">无</span>'}-
             ${(list.ARGEAAREA)!'<span style="color:gray">无</span>'}-
             ${(list.BRANCHOFFICENAME)!'<span style="color:gray">无</span>'}
         </td>

         <td >${(list.BEGIN_VALID)!'<span style="color:gray">无</span>'}</td>
         <td >${(list.END_VALID)!'<span style="color:gray">无</span>'}</td>

      <td >${(list.ADD_NAME)!'<span style="color:gray">无</span>'}</td>
      <td >${(list.LOCALDATE)!'<span style="color:gray">无</span>'} ${(list.LOCALTIME)!'<span style="color:gray">无</span>'}</td>
      <td style="color:red;">${(list.NOTE)!'<span style="color:gray">无</span>'}</td>
         <td>
             <span><a class="btn btn-blue" href="${baseClass}/adddetail?agent_num=${(list.AGENT_NUM)!}&ck=0" data-toggle="dialog"  rel="BaseinfoClass_update" data-options="{id:'dialog',title:'查看代理商',width:'920',height:'800'}"><i class="fa fa-edit"></i><span>查看详情</span></a>&nbsp;&nbsp;</span>

         </td>

     </tr>
	</#list>
   </#if>
  </tbody>
 </table>
</div>
<#include "/common/pages.ftl">