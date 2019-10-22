<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>机构管理平台</title>
<meta name="referrer" content="no-referrer">
<meta name="Keywords" content="demo"/>
<meta name="Description" content="demo"/> 
<!-- bootstrap - css -->
<link href="/yhtrade/public/BJUI/themes/css/bootstrap.css" rel="stylesheet"/>
<!-- core - css -->
<link href="/yhtrade/public/BJUI/themes/css/style.css" rel="stylesheet"/>
<link href="/yhtrade/public/BJUI/themes/blue/core.css" id="bjui-link-theme" rel="stylesheet"/>
<!-- plug - css -->
<link href="/yhtrade/public/BJUI/plugins/kindeditor_4.1.10/themes/default/default.css" rel="stylesheet"/>
<link href="/yhtrade/public/BJUI/plugins/colorpicker/css/bootstrap-colorpicker.min.css" rel="stylesheet"/>
<link href="/yhtrade/public/BJUI/plugins/niceValidator/jquery.validator.css" rel="stylesheet"/>
<link href="/yhtrade/public/BJUI/plugins/bootstrapSelect/bootstrap-select.css" rel="stylesheet"/>
<link href="/yhtrade/public/BJUI/themes/css/FA/css/font-awesome.min.css" rel="stylesheet"/>

<!-- treetable -->
<link href="/yhtrade/public/treetable/css/jquery.treetable.css" rel="stylesheet"/>
<link href="/yhtrade/public/treetable/css/jquery.treetable.theme.default.css" rel="stylesheet"/>
 
<!-- jquery -->
<script src="/yhtrade/public/BJUI/js/jquery-1.7.2.min.js"></script>
<script src="/yhtrade/public/BJUI/js/jquery.cookie.js"></script>
 
<!-- BJUI.all 分模块压缩版 -->
<!-- <script src="/yhtrade/public/BJUI/js/bjui-all.js"></script> -->
<!-- 以下是B-JUI的分模块未压缩版，建议开发调试阶段使用下面的版本 -->

<script src="/yhtrade/public/BJUI/js/bjui-core.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-regional.zh-CN.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-frag.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-extends.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-basedrag.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-slidebar.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-contextmenu.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-navtab.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-dialog.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-taskbar.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-ajax.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-alertmsg.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-pagination.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-util.date.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-datepicker.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-ajaxtab.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-tablefixed.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-tabledit.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-spinner.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-lookup.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-tags.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-upload.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-theme.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-initui.js"></script>
<script src="/yhtrade/public/BJUI/js/bjui-plugins.js"></script>
<!-- zidingyi -->
<script type="text/javascript" src="/yhtrade/public/BJUI/js/my.js"></script>

<!-- plugins -->
<!-- swfupload for uploadify && kindeditor -->
<script src="/yhtrade/public/BJUI/plugins/swfupload/swfupload.js"></script>
<!-- kindeditor -->
<script src="/yhtrade/public/BJUI/plugins/kindeditor_4.1.10/kindeditor-all.min.js"></script>
<script src="/yhtrade/public/BJUI/plugins/kindeditor_4.1.10/lang/zh_CN.js"></script>
<!-- colorpicker -->
<script src="/yhtrade/public/BJUI/plugins/colorpicker/js/bootstrap-colorpicker.min.js"></script>
<!-- ztree -->
<script src="/yhtrade/public/BJUI/plugins/ztree/jquery.ztree.all-3.5.js"></script>
<!-- nice validate -->
<script src="/yhtrade/public/BJUI/plugins/niceValidator/jquery.validator.js"></script>
<script src="/yhtrade/public/BJUI/plugins/niceValidator/jquery.validator.themes.js"></script>
<!-- bootstrap plugins -->
<script src="/yhtrade/public/BJUI/plugins/bootstrap.min.js"></script>
<script src="/yhtrade/public/BJUI/plugins/bootstrapSelect/bootstrap-select.min.js"></script>
<script src="/yhtrade/public/BJUI/plugins/bootstrapSelect/defaults-zh_CN.min.js"></script>
<!-- icheck -->
<script src="/yhtrade/public/BJUI/plugins/icheck/icheck.min.js"></script>
<!-- dragsort -->
<script src="/yhtrade/public/BJUI/plugins/dragsort/jquery.dragsort-0.5.1.min.js"></script>
<!-- other plugins -->
<script src="/yhtrade/public/BJUI/plugins/other/jquery.autosize.js"></script>
<link href="/yhtrade/public/BJUI/plugins/uploadify/css/uploadify.css" rel="stylesheet"/>
<script src="/yhtrade/public/BJUI/plugins/uploadify/scripts/jquery.uploadify.min.js"></script>
<script src="/yhtrade/public/BJUI/plugins/download/jquery.fileDownload.js"></script>

<!-- treetable -->
<script src="/yhtrade/public/treetable/jquery.treetable.js"></script>
<!-- init -->
<script type="text/javascript">
$(function() {
    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
    BJUI.init({
        JSPATH       : '/yhtrade/public/BJUI/',         //[可选]框架路径
        PLUGINPATH   : '/yhtrade/public/BJUI/plugins/', //[可选]插件路径
        loginInfo    : {url:'login_timeout.html', title:'登录', width:400, height:200}, // 会话超时后弹出登录对话框
        statusCode   : {ok:200, error:300, timeout:301}, //[可选]
        ajaxTimeout  : 500000, //[可选]全局Ajax请求超时时间(毫秒)
        pageInfo     : {total:'total', pageCurrent:'pageCurrent', pageSize:'pageSize', orderField:'orderField', orderDirection:'orderDirection'}, //[可选]分页参数
        alertMsg     : {displayPosition:'topcenter', displayMode:'slide', alertTimeout:3000}, //[可选]信息提示的显示位置，显隐方式，及[info/correct]方式时自动关闭延时(毫秒)
        keys         : {statusCode:'statusCode', message:'message'}, //[可选]
        ui           : {
                         windowWidth      : 0, //框架显示宽度，0=100%宽，> 600为则居中显示
                         showSlidebar     : true, //[可选]左侧导航栏锁定/隐藏
                         clientPaging     : true, //[可选]是否在客户端响应分页及排序参数
                         overwriteHomeTab : false //[可选]当打开一个未定义id的navtab时，是否可以覆盖主navtab(我的主页)
                       },
        debug        : true,    // [可选]调试模式 [true|false，默认false]
        theme        : 'sky' // 若有Cookie['bjui_theme'],优先选择Cookie['bjui_theme']。皮肤[五种皮肤:default, orange, purple, blue, red, green]
    });
    
    // main - menu
    $('#bjui-accordionmenu')
        .collapse()
        .on('hidden.bs.collapse', function(e) {
            $(this).find('> .panel > .panel-heading').each(function() {
                var $heading = $(this), $a = $heading.find('> h4 > TracingPrintStream');
                
                if ($a.hasClass('collapsed')) $heading.removeClass('active');
            });
        })
        .on('shown.bs.collapse', function (e) {
            $(this).find('> .panel > .panel-heading').each(function() {
                var $heading = $(this), $a = $heading.find('> h4 > TracingPrintStream');
                
                if (!$a.hasClass('collapsed')) $heading.addClass('active');
            });
        });
        
    $(document).on('click', 'ul.menu-items li > TracingPrintStream', function(e) {
        var $a = $(this), $li = $a.parent(), options = $a.data('options').toObj(), $children = $li.find('> .menu-items-children');
        var onClose = function() {
            $li.removeClass('active');
        };
        var onSwitch = function() {
            $('#bjui-accordionmenu').find('ul.menu-items li').removeClass('switch');
            $li.addClass('switch');
        };
        
        $li.addClass('active');
        if (options) {
            options.url      = $a.attr('href');
            options.onClose  = onClose;
            options.onSwitch = onSwitch;
            if (!options.title) options.title = $a.text();
            
            if (!options.target)
                $a.navtab(options);
            else
                $a.dialog(options);
        }
        if ($children.length) {
            $li.toggleClass('open');
        }
        
        e.preventDefault();
    });
    
    //时钟
    var today = new Date();
    $('#bjui-date').html(today.formatDate('yyyy/MM/dd'));
    setInterval(function() {
        today = new Date(today.setSeconds(today.getSeconds() + 1));
        $('#bjui-clock').html(today.formatDate('HH:mm:ss'));
    }, 1000);
});

//菜单-事件
function MainMenuClick(event, treeId, treeNode) {
    event.preventDefault();
    
    if (treeNode.isParent) {
        var zTree = $.fn.zTree.getZTreeObj(treeId);
        
        zTree.expandNode(treeNode, !treeNode.open, false, true, true);
        return
    }
    
    if (treeNode.target && treeNode.target == 'dialog')
        $(event.target).dialog({id:treeNode.tabid, url:treeNode.url, title:treeNode.name});
    else
        $(event.target).navtab({id:treeNode.tabid, url:treeNode.url, title:treeNode.name, fresh:treeNode.fresh, external:treeNode.external});
}

function loginUp(){
	$(this).alertmsg('confirm', '确认要注销登录吗？', {displayMode:'slide', displayPosition:'topcenter', okName:'确认', cancelName:'取消', title:'提示信息',okCall:function(){
		window.location.href="/yhtrade/AdminInfo/destroyLogin"
	}});
}
//超时
function overtimeTs(){
	 $(this).dialog({id:'mydialog', url:'${base}/AdminInfo/goLogin', title:'超时登录',width:400, height:198});
}

</script>
</head>
<body>
    <div id="bjui-window">
    <header id="bjui-header">
        <div class="bjui-navbar-header">
            <button type="button" class="bjui-navbar-toggle btn-default" data-toggle="collapse" data-target="#bjui-navbar-collapse">
                <i class="fa fa-bars"></i>
            </button>
            <#--<TracingPrintStream class="bjui-navbar-logo" href="#"><img src="/yhtrade/public/images/logo.png"></TracingPrintStream>-->
        </div>
        <nav id="bjui-navbar-collapse">
            <ul class="bjui-navbar-right">
                <li class="datetime"><div><span id="bjui-date"></span> <span id="bjui-clock"></span></div></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账户 <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${base}/AdminInfo/resetPass?admin_id=${HFBACKSTAGEUSER.admin_id}" data-toggle="dialog" data-options="{id:'dialog',title:'修改密码',width:'450',height:'345'}"><span class="glyphicon glyphicon-lock"></span>修改密码&nbsp;</a></li>
                    </ul>
                </li>
                <li class="dropdown"><a href="javascript:;" onclick="loginUp()" width="130" class="red">&nbsp;<span class="glyphicon glyphicon-off"></span>注销登录</a></li>
                <li class="dropdown"><a href="#" class="dropdown-toggle theme blue" data-toggle="dropdown" title="切换皮肤"><i class="fa fa-tree"></i></a>
                    <ul class="dropdown-menu" role="menu" id="bjui-themes">
                        <li><a href="javascript:;" class="theme_default" data-toggle="theme" data-theme="default">&nbsp;<i class="fa fa-tree"></i> 黑白分明&nbsp;&nbsp;</a></li>
                        <li><a href="javascript:;" class="theme_orange" data-toggle="theme" data-theme="orange">&nbsp;<i class="fa fa-tree"></i> 橘子红了</a></li>
                        <li><a href="javascript:;" class="theme_purple" data-toggle="theme" data-theme="purple">&nbsp;<i class="fa fa-tree"></i> 紫罗兰</a></li>
                        <li class="active"><a href="javascript:;" class="theme_blue" data-toggle="theme" data-theme="blue">&nbsp;<i class="fa fa-tree"></i> 天空蓝</a></li>
                        <li><a href="javascript:;" class="theme_green" data-toggle="theme" data-theme="green">&nbsp;<i class="fa fa-tree"></i> 绿草如茵</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="bjui-hnav">
            <button type="button" class="btn-default bjui-hnav-more-left" title="导航菜单左移"><i class="fa fa-angle-double-left"></i></button>
            <div id="bjui-hnav-navbar-box">
                <ul id="bjui-hnav-navbar">
                	<#assign carte = getCache("menuCarte")>
                  	 <#if carte ??>
                  	 	<#list carte as item>
                  	 		<!--一级菜单-->
                  	 		<#if item["LEVELS"] == 1>
                  	 			<li<#if item_index = 0> class="active" </#if>  ><a href="javascript:;" data-toggle="slidebar"> <#if item_index = 0> <i class="fa fa-check-square-o">  <#else> <i class="fa fa-table"> </#if> </i> ${item["CARTE_NAME"]} </a>
                  	 			<#if (carte[item_index + 1]) ?? >
                  	 				<div class="items hide" data-noinit="true">
                  	 				<ul id="bjui-hnav-tree${item['PARENT_ID']}" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true" data-faicon="table">
                  	 				<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-faicon="folder-open-o" data-faicon-close="folder-o">${item["CARTE_NAME"]}</li>
                  	 			</#if>
                  	 		</#if>
                  	 		
                  	 		<!--二级菜单-->
                  	 		<#if item["LEVELS"] == 2>
                  	 			<#if (carte[item_index + 1]) ?? >
                  	 				<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" <#if (carte[item_index + 1]["LEVELS"] > item["LEVELS"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="${item['PARENT_ID']}" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?carte_id=${item['ID'] }" </#if>>${item["CARTE_NAME"]}</li>
                  	 			<#elseif item_index == (carte?size - 1)>
                  	 				<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-tabid="${item['PARENT_ID']}" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?carte_id=${item['ID'] }" >${item["CARTE_NAME"]}</li>
                  	 			</#if>
                  	 		</#if>
                  	 		
                  	 		<!--三级菜单-->
                  	 		<#if item["LEVELS"] == 3>
                  	 			<#if (carte[item_index + 1]) ?? >
                  	 				<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" <#if (carte[item_index + 1]["LEVELS"] > item["LEVELS"])> data-url="javascript:;" data-faicon="folder-open-o" data-faicon-close="folder-o" <#else> data-tabid="${item['PARENT_ID']}" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?carte_id=${item['ID'] }" </#if>>${item["CARTE_NAME"]}</li>
                  	 			<#elseif item_index == (carte?size - 1)>
                  	 				<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-tabid="${item['PARENT_ID']}" data-faicon="table" data-url="/yhtrade/${item['HREF'] }/?carte_id=${item['ID'] }" >${item["CARTE_NAME"]}</li>
                  	 			</#if>
                  	 		</#if>
                  	 		
                  	 		<!--四级菜单-->
                  	 		<#if item["LEVELS"] == 4>
                  	 			<li data-id="${item['PARENT_ID']}" data-pid="${item['PARENTS']}" data-url="/yhtrade/${item['HREF'] }/?carte_id=${item['ID'] }" data-tabid="${item['PARENT_ID']}" data-faicon="table">${item["CARTE_NAME"]}</li>
                  	 		</#if>
                  	 		
                  	 		<!--结束标签 -->
                  	 		<#if (carte[item_index + 1]) ?? && (carte[item_index + 1]["PARENTS"] == '0') >
								</ul></div></li>
							<#elseif item_index == (carte?size - 1)>
								</ul></div></li>
							</#if>
                  	 	</#list>
                  	 </#if>
                </ul>
            </div>
        </div>
    </header>
    <div id="bjui-container">
        <div id="bjui-leftside">
            <div id="bjui-sidebar-s">
                <div class="collapse"></div>
            </div>
            <div id="bjui-sidebar">
                <div class="toggleCollapse"><h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
                <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">
                </div>
            </div>
        </div>
        <div id="bjui-navtab" class="tabsPage">
            <div class="tabsPageHeader">
                <div class="tabsPageHeaderContent">
                    <ul class="navtab-tab nav nav-tabs">
                        <li data-url=""><a href="javascript:;"><span><i class="fa fa-home"></i> #maintab#</span></a></li>
                    </ul>
                </div>
                <div class="tabsLeft"><i class="fa fa-angle-double-left"></i></div>
                <div class="tabsRight"><i class="fa fa-angle-double-right"></i></div>
                <div class="tabsMore"><i class="fa fa-angle-double-down"></i></div>
            </div>
            <ul class="tabsMoreList">
                <li><a href="javascript:;">#maintab#</a></li>
            </ul>
            <div class="navtab-panel tabsPageContent">
                <div class="navtabPage unitBox">
                    <div class="bjui-pageContent" style="background:#FFF;">
						<span style="font-weight:bold;font-size:16px;">欢迎使用管理平台:${HFBACKSTAGEUSER.admin_name}[${HFBACKSTAGEUSER.admin_realname!}]</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <footer id="bjui-footer"><#--Copyright &copy; 2019　<a href="javascript:void(0);" target="_blank">联动优势电子商务有限公司--></a>
    </footer>
    </div>
</body>
    
</html>