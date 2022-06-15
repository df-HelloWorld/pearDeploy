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
        <h2>编辑策略</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>通道名称：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <%--<input type="text" class="formInput" id="gewayName" name="gewayName"	maxlength="240" />--%>
                        <select id="gewayId" name="gewayId" class='text-input medium-input' >
                            <%--<c:forEach items="${geway}" var="dataList">--%>
                                <%--<option value="${dataList.id}">${dataList.gewayName}</option>--%>
                            <%--</c:forEach>--%>
                            <c:forEach items="${geway}" var="dataList">
                                <c:choose>
                                    <c:when test="${dl.gewayId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.gewayName}</option>
                                    </c:when>
                                    <c:when test="${dl.gewayId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.gewayName}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>通道码名称：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="gewayCodeName" name="gewayCodeName"	value="${dl.gewayCodeName}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>通道码：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="gewayCode" name="gewayCode"	value="${dl.gewayCode}" maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>我方通道码：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="myGewayCode" name="myGewayCode" value="${dl.myGewayCode}"	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">接口地址：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <div class="formCtrlDiv">
                            <input type="text" class="formInput" id="interfaceAds" name="interfaceAds"	value="${dl.interfaceAds}" maxlength="240" />
                        </div>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">同步的接口地址：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="notifyUrl" name="notifyUrl" value="${dl.notifyUrl}"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">请求标识：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="sendTag" name="sendTag"	value="${dl.sendTag}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >成功数据回传标识：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="sucTag" name="sucTag"	value="${dl.sucTag}" maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>上游费率类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="upServiceChargeType" name="upServiceChargeType" >
                            <option value="" >=请选择=</option>
                            <c:if test="${dl.upServiceChargeType == 1}">
                                <option value="1" selected="selected">固定费率</option>
                                <option value="2">额外费率</option>
                            </c:if>
                            <c:if test="${dl.upServiceChargeType == 2}">
                                <option value="1" >固定费率</option>
                                <option value="2" selected="selected">额外费率</option>
                            </c:if>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >上游费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="upServiceCharge" name="upServiceCharge"	value="${dl.upServiceCharge}" maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >上游费率之额外费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="upExtraServiceCharge" name="upExtraServiceCharge"	value="${dl.upExtraServiceCharge}" maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >平台费率类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="serviceChargeType" name="serviceChargeType" >
                            <option value="" >=请选择=</option>
                            <c:if test="${dl.serviceChargeType == 1}">
                                <option value="1" selected="selected">固定费率</option>
                                <option value="2">额外费率</option>
                            </c:if>
                            <c:if test="${dl.serviceChargeType == 2}">
                                <option value="1" >固定费率</option>
                                <option value="2" selected="selected">额外费率</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >平台费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="serviceCharge" name="serviceCharge"	value="${dl.serviceCharge}" maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >平台费率之额外费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="extraServiceCharge" name="extraServiceCharge" value="${dl.extraServiceCharge}"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >支持金额类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="moneyType" name="moneyType" >
                            <option value="" >=请选择=</option>
                            <c:if test="${dl.moneyType == 1}">
                                <option value="1" selected="selected">固定的</option>
                                <option value="2">单一范围</option>
                                <option value="3">多个范围</option>
                            </c:if>
                            <c:if test="${dl.moneyType == 2}">
                                <option value="1" >固定的</option>
                                <option value="2" selected="selected">单一范围</option>
                                <option value="3" >多个范围</option>
                            </c:if>
                            <c:if test="${dl.moneyType == 3}">
                                <option value="1" >固定的</option>
                                <option value="2" >单一范围</option>
                                <option value="3" selected="selected">多个范围</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >支持金额：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="moneyRange" name="moneyRange"	value="${dl.moneyRange}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >放量时间：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="openTime" name="openTime"	value="${dl.openTime}" maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >每日限制放量的金额：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="dayLimitMoney" name="dayLimitMoney"	value="${dl.dayLimitMoney}" maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>属性类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <%--<input type="text" class="formInput" id="gewayName" name="gewayName"	maxlength="240" />--%>
                        <select id="codeAttributeType" name="codeAttributeType" class='text-input medium-input' >
                            <option value="" selected="selected">=请选择=</option>

                            <c:if test="${dl.codeAttributeType == 1}">
                                <option value="1" selected="selected">全类型</option>
                                <option value="2">Android</option>
                                <option value="3">IOS</option>
                            </c:if>
                            <c:if test="${dl.codeAttributeType == 2}">
                                <option value="1" >全类型</option>
                                <option value="2" selected="selected">Android</option>
                                <option value="3" >IOS</option>
                            </c:if>
                            <c:if test="${dl.codeAttributeType == 3}">
                                <option value="1">全类型</option>
                                <option value="2">Android</option>
                                <option value="3" selected="selected">IOS</option>
                            </c:if>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >白名单IP：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="whiteListIp" name="whiteListIp" value="${dl.whiteListIp}"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >请求身份：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="sendIdentity" name="sendIdentity" value="${dl.sendIdentity}"	maxlength="240" />
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
                gewayId:{
                    required:true
                },
                gewayCodeName:{
                    required:true,
                    maxlength:40
                },
                gewayCode:{
                    required:true,
                    maxlength:20
                },
                myGewayCode:{
                    required:true,
                    maxlength:10
                },
                upServiceChargeType:{
                    required:true
                },
                codeAttributeType:{
                    required:true
                }
            },
            messages: {
                gewayId:{
                    required : "请选择通道!",
                },
                gewayCodeName:{
                    required:"通道码码名称不能为空!",
                    number:"通道码码名称长度最多是40个字符!"
                },
                gewayCode:{
                    required:"通道码不能为空!",
                    number:"通道码长度最多是20个字符!"
                },
                myGewayCode:{
                    required:"我方通道码不能为空!",
                    number:"我方通道码长度最多是10个字符!"
                },
                upServiceChargeType:{
                    required : "请选择上游费率类型!",
                },
                codeAttributeType:{
                    required : "请选择属性类型!",
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/prgewaycode/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/prgewaycode/list.do";
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