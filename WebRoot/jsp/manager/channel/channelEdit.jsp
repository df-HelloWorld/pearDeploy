<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <style type="text/css">
        .manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
        .manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
        .manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
    <div class="formHeadDiv">
        <h2>重置密码：如果您要重置登录密码，请输入《登录密码重置》的信息；如果您要重置提现密码，请输入《提现密码重置》的信息.</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <h2><font color="red">登录密码重置</font></h2>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>原始登录密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="passWd" name="passWd"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>新登录密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="resetPassWd" name="resetPassWd"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>确认新登录密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="newResetPassWd" name="newResetPassWd"	maxlength="240" />
                    </div>
                </li>


                <h2><font color="red">提现密码重置</font></h2>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>原始提现密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="withdrawPassWd" name="withdrawPassWd"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>新提现密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="resetWithdrawPassWd" name="resetWithdrawPassWd"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>确认新提现密码</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="newResetWithdrawPassWd" name="newResetWithdrawPassWd"	maxlength="240" />
                    </div>
                </li>


                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="提 交" style="background-color: #54D8FE;"/> <span>
						<%--</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />--%>
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            // rules:{
            //     gewayName:{
            //         required:true,
            //         maxlength:80
            //     },
            //     companyName:{
            //         required:true,
            //         maxlength:80
            //     },
            //     contacts:{
            //         required:true,
            //         maxlength:80
            //     },
            //     payId:{
            //         required:true,
            //         maxlength:80
            //     },
            //     secretKey:{
            //         required:true,
            //         maxlength:80
            //     },
            //     identify:{
            //         required:true,
            //         maxlength:80
            //     },
            //     interfaceAds:{
            //         required:true,
            //         maxlength:255
            //     },
            //     notifyUrl:{
            //         required:true,
            //         maxlength:255
            //     }
            // },
            // messages: {
            //     gewayName:{
            //         required : "通道名称不能为空!",
            //         maxlength : "通道名称长度最多是80个字符!"
            //     },
            //     companyName:{
            //         required:"公司名称不能为空!",
            //         number:"公司名称长度最多是80个字符!"
            //     },
            //     contacts:{
            //         required:"联系人不能为空!",
            //         number:"联系人长度最多是80个字符!"
            //     },
            //     payId:{
            //         required:"商铺号不能为空!",
            //         number:"商铺号长度最多是80个字符!"
            //     },
            //     secretKey:{
            //         required:"秘钥不能为空!",
            //         number:"秘钥长度最多是80个字符!"
            //     },
            //     identify:{
            //         required:"回传标识不能为空!",
            //         number:"回传标识长度最多是80个字符!"
            //     },
            //     interfaceAds:{
            //         required:"接口地址不能为空!",
            //         number:"接口地址长度最多是255个字符!"
            //     },
            //     notifyUrl:{
            //         required:"回传地址不能为空!",
            //         number:"回传地址长度最多是255个字符!"
            //     }
            // },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/channel/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("重置成功！！！");
                            window.location.href = ctx + "/channel/list.do";
                        } else {
                            art.alert(data.msg);
                        }
                    },
                    error : function(data) {
                        art.alert(data.info);
                    }
                });
                return false;
                //阻止表单提交
            }
        });
    });
</script>
</body>
</html>