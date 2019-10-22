
 <html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎登录机构管理系统</title>
 
<script language="JavaScript" src="/yhtrade/public/javascript/jquery.js"></script>
<link href="/yhtrade/public/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="/yhtrade/public/css/base.css" />
<script type="text/javascript" src="/yhtrade/public/javascript/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="/yhtrade/public/javascript/dsdialog.js"></script>
<script type="text/javascript" src="/yhtrade/public/javascript/my_login.js"></script>

<script type="text/javascript">
    //防止页面后退
    history.pushState(null, null, document.URL);
    window.addEventListener('popstate', function () {
        history.pushState(null, null, document.URL);
    });
	$(function(){
	    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){
	    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    	});
	});
</script>

</head>

<body class="bodyClass">
<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>  
<div class="logintop">    
    <span>欢迎登录联动优势电子商务有限公司管理平台</span>
    <ul>
    <li><a href="javascript:void(0);" target="_blank"></a></li>
    </ul>    
    </div>
    <div class="loginbody">
    <span class="systemlogo"></span> 
    <div class="loginbox">
    <ul>
    <li><input name="admin_name" id="admin_name" type="text" class="loginuser" value="${login_user!}" placeholder="请输入登录用户名" /></li>
    <li><input name="admin_password"  id="admin_password" type="password" class="loginpwd" value="${login_password!}" placeholder="请输入密码" /></li>
    <li>
    	<input type="text" id="code" name="code" size="12" class="loginuserCus" maxlength="4" />
		<img id="validateCode" src="/yhtrade/AdminInfo/getRandomImg" title="验证码" class="loginpass" border="1" height="30" onclick = "changeCode()" />
		<input name="userLogin" id="userLogin" type="button" class="loginbtn" value="登录"/>
	</li>
	
    <li style = "float:right;margin-right:20%;margin-top:5px;">
            <label style="cursor: pointer;">
    			<input name="remember" id="remember" type="checkbox" 
    			<#if remember??> 
	    			<#if remember=="1"> 
	    				checked="checked"
	    			</#if>
    			</#if> />记住密码
    		</label>
    		</li>
    </ul>
    </div>
    </div>
    <div class="loginbm">Copyright &copy; 2011  联动优势电子商务有限公司  24小时客服：400-810-6899</div>
	</body>
</html>
