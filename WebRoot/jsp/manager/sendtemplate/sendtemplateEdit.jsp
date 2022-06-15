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
<body onload="loadSendFieldUpdate()">
<div class="col_main">
    <div class="formHeadDiv">
        <h2>编辑请求模板</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="sendTemplate" value="${sendTemplate}"/>
                    <input type="hidden" id="mbId" name="mbId" value="${sendTemplate.id}">
                <h2><font color="red">模板数据</font></h2>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>模板名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="template" name="template"	maxlength="240" value="${sendTemplate.template}" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>通道码：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="gewayCodeId" name="gewayCodeId" >
                            <option value="">===请选择===</option>
                            <c:forEach items="${prGewayCode}" var="dataList">
                                <c:choose>
                                    <c:when test="${sendTemplate.gewayCodeId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.gewayCodeName}===${dataList.myGewayCode}</option>
                                    </c:when>
                                    <c:when test="${sendTemplate.gewayCodeId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.gewayCodeName}===${dataList.myGewayCode}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>是否加密：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isEncryption" name="isEncryption">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.isEncryption == 1}">
                                <option value="1" selected="selected">加密</option>
                                <option value="2">不加密</option>
                            </c:if>
                            <c:if test="${sendTemplate.isEncryption == 2}">
                                <option value="1">加密</option>
                                <option value="2" selected="selected">不加密</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>大小写：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="encryptionWay" name="encryptionWay">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.encryptionWay == 1}">
                                <option value="1" selected="selected">无需大小写</option>
                                <option value="2">小写加密</option>
                                <option value="3">大写加密</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionWay == 2}">
                                <option value="1">无需大小写</option>
                                <option value="2" selected="selected">小写加密</option>
                                <option value="3">大写加密</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionWay == 3}">
                                <option value="1">无需大小写</option>
                                <option value="2">小写加密</option>
                                <option value="3" selected="selected">大写加密</option>
                            </c:if>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>加密类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="encryptionType" name="encryptionType">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.encryptionType == 1}">
                                <option value="1" selected="selected">md5加密</option>
                                <option value="2">base64加密</option>
                                <option value="3">md5+base64</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionType == 2}">
                                <option value="1">md5加密</option>
                                <option value="2" selected="selected">base64加密</option>
                                <option value="3">md5+base64</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionType == 3}">
                                <option value="1">md5加密</option>
                                <option value="2">base64加密</option>
                                <option value="3" selected="selected">md5+base64</option>
                            </c:if>

                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>秘钥放置位置：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="secretKeySeat" name="secretKeySeat">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.secretKeySeat == 1}">
                                <option value="1" selected="selected">存放字符串末尾</option>
                                <option value="2">根据ascii排序存放</option>
                            </c:if>
                            <c:if test="${sendTemplate.secretKeySeat == 2}">
                                <option value="1">存放字符串末尾</option>
                                <option value="2" selected="selected">根据ascii排序存放</option>
                            </c:if>

                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>秘钥key类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="secretKeyType" name="secretKeyType">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.secretKeyType == 1}">
                                <option value="1" selected="selected">无需key存放在字符串最末尾</option>
                                <option value="2">需要key当做参数名赋值秘钥存放在最末尾</option>
                            </c:if>
                            <c:if test="${sendTemplate.secretKeyType == 2}">
                                <option value="1">无需key存放在字符串最末尾</option>
                                <option value="2" selected="selected">需要key当做参数名赋值秘钥存放在最末尾</option>
                            </c:if>

                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>加密排序：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="encryptionSort" name="encryptionSort">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.encryptionSort == 1}">
                                <option value="1" selected="selected">按照指定顺序</option>
                                <option value="2">按照ascii码升序</option>
                                <option value="3">按照ascii码降序</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionSort == 2}">
                                <option value="1">按照指定顺序</option>
                                <option value="2" selected="selected">按照ascii码升序</option>
                                <option value="3">按照ascii码降序</option>
                            </c:if>
                            <c:if test="${sendTemplate.encryptionSort == 3}">
                                <option value="1">按照指定顺序</option>
                                <option value="2">按照ascii码升序</option>
                                <option value="3" selected="selected">按照ascii码降序</option>
                            </c:if>

                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>请求数据方式：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="sendType" name="sendType">
                            <option value="">===请选择===</option>
                            <c:if test="${sendTemplate.sendType == 1}">
                                <option value="1" selected="selected">get</option>
                                <option value="2">post/json</option>
                                <option value="3">post/form</option>
                            </c:if>
                            <c:if test="${sendTemplate.sendType == 2}">
                                <option value="1">get</option>
                                <option value="2" selected="selected">post/json</option>
                                <option value="3">post/form</option>
                            </c:if>
                            <c:if test="${sendTemplate.sendType == 3}">
                                <option value="1">get</option>
                                <option value="2">post/json</option>
                                <option value="3" selected="selected">post/form</option>
                            </c:if>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">请求数据案例：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="sendCase" name="sendCase" cols="40" rows="5">${sendTemplate.sendCase}</textarea>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="remark" name="remark" cols="40" rows="5">${sendTemplate.remark}</textarea>
                    </div>
                </li>







                <%--<li>--%>
                    <%--<div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">--%>

                        <%--<input type="button" style="background-color: #767DC3" class="formBtn" onClick="addTr()" value="新增一行" />--%>

                        <%--<input type="submit" class="formBtn" value="添  加" style="background-color: #54D8FE;"/> <span>--%>
						<%--</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />--%>
                        <%--<input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>--%>
                    <%--</div>--%>
                <%--</li>--%>


                <div>
                     <span style="margin-left: 100px;">
                            <input type="button" style="background-color: #767DC3" class="formBtn" onClick="addTr()" value="新增一行" />
                        </span>
                            <span style="margin-left: 100px;">
                            <input type="submit" class="formBtn" value="更  新" style="background-color: #54D8FE;"/>
                            <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                            <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
                        </span>
                </div>



            </div>
            </ul>
        </form>


        <form id="addSupplierFormTwo">
            <h2><font color="red">字段数据</font></h2>
            <%--<div class="formTextDiv" style="width: 100%;" id="sendFieldDiv">--%>
                <%--<table id='dataDayTable' name='dataDayTable'>--%>
                    <%--<tr>--%>
                        <%--<td>字段名</td>--%>
                        <%--<td>参数名</td>--%>
                        <%--<td>参数值</td>--%>
                        <%--<td>参数值类型</td>--%>
                        <%--<td>是否要加密</td>--%>
                        <%--<td>是否允许为空</td>--%>
                        <%--<td>顺序/位置</td>--%>
                        <%--<td>字段类型</td>--%>
                        <%--<td>操作</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>
            <%--</div>--%>

            <div class="formTextDiv" style="width: 100%;" id="sendFieldDiv">

            </div>
        </form>
    </div>










    </div>



<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sendtemplate/sendtemplate.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                template:{
                    required:true,
                    maxlength:30
                },
                gewayCodeId:{
                    required:true
                },
                isEncryption:{
                    required:true
                },
                encryptionWay:{
                    required:true
                },
                encryptionType:{
                    required:true
                },
                secretKeySeat:{
                    required:true
                },
                secretKeyType:{
                    required:true
                },
                encryptionSort:{
                    required:true
                },
                sendType:{
                    required:true
                }
            },
            messages: {
                template:{
                    required : "模板名称不能为空!",
                    maxlength : "模板名称长度最多是30个字符!"
                },
                gewayCodeId:{
                    required : "通道码不能为空!"
                },
                isEncryption:{
                    required : "是否要加密不能为空!"
                },
                encryptionWay:{
                    required : "大小写加密方式不能为空!"
                },
                encryptionType:{
                    required : "加密类型不能为空!"
                },
                secretKeySeat:{
                    required : "秘钥放置位置不能为空!"
                },
                secretKeyType:{
                    required : "秘钥key类型不能为空!"
                },
                encryptionSort:{
                    required : "加密排序不能为空!"
                },
                sendType:{
                    required : "请求数据方式不能为空!"
                }
            },

            submitHandler : function() {
                var id = $("#mbId").val();
                var formDataOne = $("#addSupplierForm").serialize();
                var formDataTwo = $("#addSupplierFormTwo").serialize();

                var dataOne = JSON.stringify(formDataOne);
                var dataTwo = JSON.stringify(formDataTwo);
                $.ajax({
                    url : ctx+ "/sendtemplate/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :{
                        dataOne:dataOne,
                        dataTwo:dataTwo,
                        id:id
                    },
                    success : function(data) {
                        if (data.success) {
                            alert("更新成功！！！");
                            window.location.href = ctx + "/sendtemplate/list.do";
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