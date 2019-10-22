$(document).ready(function(){ 
	$(document).keypress(function(e) { 
       if(e.which == 13) { 
    	  login();
       } 
	}); 
	$("#userLogin").click(function(){
		login();
	});
});
//登录标识
var loginSign = false;
//登陆方法
function login(){
	if(checkForm()&&!loginSign){          //验证登录
		//是否为正在登陆
		loginSign = true;
		$("#userLogin").val("正在登录...");
		var name = $("#admin_name").val();
		var pwd = $("#admin_password").val();
		var code = $("#code").val();
		var remember =$("#remember").prop("checked");
		if (remember=="undefined" || remember==undefined) {
			remember=false;
		}else{
			remember=true;
		}
		var user={"admin_name" : name,"admin_password" : pwd,"code" : code, "remember":remember};
		
		 $.ajax({
		    type: "POST",
		    url: "/"+demo_name()+"/AdminInfo/loginUser",
		    data: JSON.stringify(user),
		    datatype:"json",
		    contentType:"application/json;charset=utf-8",//(可以)
			success:function(data){
                changeCode();
				if(data.code=="E0"){
					$("#userLogin").val("正在进入...");
					window.location.href = "/"+demo_name()+"/Index/index";
				}else{
					ds.dialog({
						   title : '消息提示',
						   content : data.codeMsg+"["+data.code+"]",
						   onyes:true,
						   icon : "info.png",
						});
					loginSign = false;
				}
			}
		});
	}
}
 //输入框验证
 function checkForm() {
	 var name = $("#admin_name").val();
	 if (name=='请填写您的用户名！' || name.length <= 0) {
		 ds.dialog({
			   title : '消息提示',
			   content : '用户名不能为空！',
			   onyes:true,
			   icon : "info.png",
			});
		 return false;
	 }  
	 var pass = $("#admin_password").val();
	 if (pass=='请填写您的密码！' || pass.length <= 0) {
		 ds.dialog({
			   title : '消息提示',
			   content : '密码不能为空！',
			   onyes:true,
			   icon : "info.png",
			});
		 return false;
	 }  
	 var code = $("#code").val();
     code="1";
	 if (code.length <= 0) {
		 ds.dialog({
			   title : '消息提示',
			   content : '验证码不能为空！',
			   onyes:true,
			   icon : "info.png",
			});
		 return false;
	 }  
	 return true;
 }
 //获取项目地址
 function demo_name() {
	//var pathName=window.document.location.pathname;
	 //var projectName=pathName.substring(1,pathName.substr(1).indexOf('/')+1);
	 return "yhtrade";
 }
 //改变验证码
 function changeCode(){
	 $("#userLogin").val("登录");
	 $("#validateCode").attr('src', "/"+demo_name()+'/AdminInfo/getRandomImg?date='+new Date().getTime());     
 }