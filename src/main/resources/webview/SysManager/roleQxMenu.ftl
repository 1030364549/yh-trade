<script>
    var zTreeObj;
    var setting = {
        check:{
            enable:true
        },
        data:{
            simpleData:{
                enable: true,
                idKey: "parentId",
                pIdKey: "parent",
                rootPId: "0"
            }
        }
    };

    var zNodes = eval($("#dataJson").val());
    $(document).ready(function(){
        zTreeObj = $.fn.zTree.init($("#treeDemo"), setting, zNodes);
    });
</script>
<div id="resultBox"></div>
<form method="post" id="treeForm" style = "margin-top:1px;">
    <input type="hidden" name="role_no" value = "${(role_no)!}"/>
    <input type="hidden" id="stringCarte" name="stringCartes"/>
    <p>
        <ul id="treeDemo" class="ztree"></ul>
    </p>
</form>