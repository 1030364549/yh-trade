function initMy(_box){ 
	var $p = $(_box || document);
	/**
     * **********************************************
     * 第三方插件
     * **********************************************
     */
    //按钮权限
    $(".permission",_box).each(function() {
    	var thisO=$(this);
    	var rel = $(this).attr("rel");
    	var carte_id = $(this).attr("carte_id");

    	$.post("/yhtrade/AdminInfo/publicShowButton/",{"rel":rel,"carte_id":carte_id},
    	function(data){
    		if(data=="true"){

    		}else{
                $(thisO).css("display","none");
			}
    	});
    	
    });	
}
