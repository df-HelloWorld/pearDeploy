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
        <h2>编辑平台通道</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>平台通道名称：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="codeName" name="codeName" value="${dl.codeName}"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>平台通道码：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="pfGewayCode" name="pfGewayCode"  value="${dl.pfGewayCode}"	maxlength="240" />
                    </div>
                </li>

                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require"><font color="red">*</font>费率类型：</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<select id="serviceChargeType" name="serviceChargeType" >--%>
                            <%--<option value="" >=请选择=</option>--%>
                            <%--<c:if test="${dl.serviceChargeType == 1}">--%>
                                <%--<option value="1" selected="selected">固定费率</option>--%>
                                <%--<option value="2">额外费率</option>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${dl.serviceChargeType == 2}">--%>
                                <%--<option value="1" >固定费率</option>--%>
                                <%--<option value="2" selected="selected">额外费率</option>--%>
                            <%--</c:if>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require"><font color="red">*</font>费率：</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<div class="formCtrlDiv">--%>
                            <%--<input type="text" class="formInput" id="serviceCharge" name="serviceCharge"  value="${dl.serviceCharge}"	maxlength="240" />--%>
                        <%--</div>--%>
                    <%--</div>--%>
                <%--</li>--%>
                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require">费率之额外费率：</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" id="extraServiceCharge" name="extraServiceCharge"	value="${dl.extraServiceCharge}"  maxlength="240" />--%>
                    <%--</div>--%>
                <%--</li>--%>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="pfGewayCodeType" name="pfGewayCodeType" >
                            <option value="" >=请选择=</option>
                            <c:if test="${dl.pfGewayCodeType == 1}">
                                <option value="1" selected="selected">代收</option>
                                <option value="2">代付</option>
                            </c:if>
                            <c:if test="${dl.pfGewayCodeType == 2}">
                                <option value="1" >代收</option>
                                <option value="2" selected="selected">代付</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >支持金额：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moneyRange" name="moneyRange" value="${dl.moneyRange}"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>是否跳转：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isJump" name="isJump" >
                            <option value="" >=请选择=</option>
                            <c:if test="${dl.isJump == 1}">
                                <option value="1" selected="selected">不跳转</option>
                                <option value="2">跳转</option>
                            </c:if>
                            <c:if test="${dl.isJump == 2}">
                                <option value="1" >不跳转</option>
                                <option value="2" selected="selected">跳转</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >跳转地址（策略的key值）：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="jumpAds" name="jumpAds" value="${dl.jumpAds}"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >缓存时长（单位秒）：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="redisTime" name="redisTime" value="${dl.redisTime}"	maxlength="240" />
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" style="background-color: #54D8FE" value="修 改" /> <span>
						</span> <input type="reset" class="formBtn" style="background-color: #54D8FE" value="重  置" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" style="background-color: #54D8FE" value=" 返 回 " />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                codeName:{
                    required:true,
                    maxlength:40
                },
                pfGewayCode:{
                    required:true,
                    maxlength:20
                },
                serviceChargeType:{
                    required:true
                },
                serviceCharge:{
                    required:true,
                    maxlength:10
                },
                pfGewayCodeType:{
                    required:true
                },
                isJump:{
                    required:true
                }
            },
            messages: {
                codeName:{
                    required : "平台通道名称不能为空!",
                    maxlength : "平台通道名称长度最多是40个字符!"
                },
                pfGewayCode:{
                    required:"平台通道码不能为空!",
                    number:"平台通道码长度最多是20个字符!"
                },
                serviceChargeType:{
                    required:"费率类型不能为空!"
                },
                serviceCharge:{
                    required:"费率不能为空!",
                    number:"费率长度最多是10个字符!"
                },
                pfGewayCodeType:{
                    required:"类型不能为空!"
                },
                isJump:{
                    required:"是否跳转不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/prplatformgewaycode/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/prplatformgewaycode/list.do";
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