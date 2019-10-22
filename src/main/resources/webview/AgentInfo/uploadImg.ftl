
<style>
    #uploadImgForm form fieldset p{width:99%;}
    .a-upload {
        color: #1E88C7;
        background: #D0EEFF;
        padding: 4px 10px;line-height: 20px;position: relative;
        cursor: pointer;border: 1px solid #ddd; border-radius: 4px;
        display: inline-block;*display: inline;*zoom: 1}
    .a-upload  input {
        position: absolute;font-size: 100px; right: 0;top: 0;opacity: 0;
        filter: alpha(opacity=0);cursor: pointer;
        height: 30px;
    }
    .a-upload:hover {
        background: #AADFFD;
        border-color: #78C3F3;
        color: #004974;
        text-decoration: none;
    }
    .fileStyle{
        height: 30px;
        background-color: white;
    }
</style>

<div class="bjui-pageContent">
    <form method="post" id="uploadImgForm" action="${baseClass}/saveImg"
          data-reload-navtab="true"  data-toggle="validate" data-callback="test" data-alertmsg="false" enctype="multipart/form-data">
        <fieldset>
            <legend>上传图片资料</legend>
            <input type="hidden" name="agent_num" value="${(agent_num)!''}"/>
            <p>
                <label style="width:120px;">身份证正面:</label>
                <input type="text" readonly="readonly"  data-rule="required" class="required" readonly="readonly"  id="one" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="1" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">身份证反面:</label>
                <input type="text" readonly="readonly" id="two"  data-rule="required" class="required" readonly="readonly"  placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="2" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">营业执照:</label>
                <input type="text" readonly="readonly" id="five" data-rule="required"  class="required" readonly="readonly"  placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="5" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">开户许可证:</label>
                <input type="text" readonly="readonly" data-rule="required" class="required" readonly="readonly"   id="Sixteen" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="16" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">门头照外景:</label>
                <input type="text" readonly="readonly" data-rule="required" class="required" readonly="readonly"   id="Seven" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="7" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">门头照内景:</label>
                <input type="text" readonly="readonly"  data-rule="required"  class="required" readonly="readonly"  id="Six" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="6" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">代理商情况说明表:</label>
                <input type="text" readonly="readonly" data-rule="required"  class="required" readonly="readonly"   id="Seventeen" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="17" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">税务登记证:</label>
                <input type="text" readonly="readonly"  id="Fourteen" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="14" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

            <p>
                <label style="width:120px;">组织机构代码证:</label>
                <input type="text" readonly="readonly" id="Fifteen" placeholder="请选择文件" class="fileStyle"  style="background-color: white;" size="30"/>
                <a href="javascript:;" class="a-upload">
                    <input type="file" name="15" accept ="image/*"; onchange="verificationPicFile(this)">
                    点击选择文件
                </a>
            </p>

        </fieldset>
    </form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">关闭</button></li>
        <li><button id="btn1" type="submit" class="btn-blue">提交</button></li>
    </ul>
</div>


<script language="javascript">
    //图片大小验证
    function verificationPicFile(obj) {
        $(obj).parent().prev().val(obj.value);

        var fileSize = 0;
        var fileMaxSize = 1024;//1M
        var filePath = obj.value;
        if(filePath){
            fileSize =obj.files[0].size;
            var size = fileSize / 1024;
            if (size > fileMaxSize) {
                $(selector).alertmsg('error', '文件大小不能大于1M！')
                obj.value = "";
                $(obj).parent().prev().val("");
                return false;
            }else if (size <= 0) {
                $(selector).alertmsg('error', '文件大小不能为0M！')
                obj.value = "";
                $(obj).parent().prev().val("");
                return false;
            }
        }else{
            return false;
        }
    }
    function test(json){

        if(json.statusCode==200){
            //关闭当前页面
            $(this).alertmsg('ok', json.message);
            $(this).dialog('closeCurrent',true);
            //刷新局部页面
            $.CurrentNavtab.find("#pagerForm").submit();
        }else{
            $(this).alertmsg('error',json.message);
            $(this).dialog('closeCurrent',true);
        }
    }
</script>