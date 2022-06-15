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
<body onload="loadReturnFieldUpdate()">
<div class="col_main">
    <div class="formHeadDiv">
        <h2>编辑返回模板</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="returnTemplate" value="${returnTemplate}"/>
                    <input type="hidden" id="mbId" name="mbId" value="${returnTemplate.id}">
                <h2><font color="red">模板数据</font></h2>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>模板名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="template" name="template"	maxlength="240" value="${returnTemplate.template}" />
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
                                    <c:when test="${returnTemplate.gewayCodeId == dataList.id}">
                                        <option selected="selected" value="${dataList.id}">${dataList.gewayCodeName}===${dataList.myGewayCode}</option>
                                    </c:when>
                                    <c:when test="${returnTemplate.gewayCodeId != dataList.id}">
                                        <option value="${dataList.id}">${dataList.gewayCodeName}===${dataList.myGewayCode}</option>
                                    </c:when>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>返回数据类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="dataType" name="dataType">
                            <option value="">===请选择===</option>
                            <c:if test="${returnTemplate.dataType == 1}">
                                <option value="1" selected="selected">直接返回</option>
                                <option value="2">一层json</option>
                                <option value="3">二层json</option>
                            </c:if>
                            <c:if test="${returnTemplate.dataType == 2}">
                                <option value="1">直接返回</option>
                                <option value="2" selected="selected">一层json</option>
                                <option value="3">二层json</option>
                            </c:if>
                            <c:if test="${returnTemplate.dataType == 3}">
                                <option value="1">直接返回</option>
                                <option value="2">一层json</option>
                                <option value="3" selected="selected">二层json</option>
                            </c:if>
                        </select>

                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>拉单状态的参数名</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="parameterName" name="parameterName"	maxlength="240" value="${returnTemplate.parameterName}" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>拉单成功的参数值</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="parameterValue" name="parameterValue"	maxlength="240" value="${returnTemplate.parameterValue}" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>拉单成功参数值的数据类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="parameterValueType" name="parameterValueType">
                            <option value="">===请选择===</option>
                            <c:if test="${returnTemplate.parameterValueType == 1}">
                                <option value="1" selected="selected">String类型</option>
                                <option value="2">Int类型</option>
                                <option value="3">Long类型</option>
                            </c:if>
                            <c:if test="${returnTemplate.parameterValueType == 2}">
                                <option value="1">String类型</option>
                                <option value="2" selected="selected">Int类型</option>
                                <option value="3">Long类型</option>
                            </c:if>
                            <c:if test="${returnTemplate.parameterValueType == 3}">
                                <option value="1">String类型</option>
                                <option value="2">Int类型</option>
                                <option value="3" selected="selected">Long类型</option>
                            </c:if>
                        </select>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">返回数据案例：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="returnCase" name="returnCase" cols="40" rows="5">${returnTemplate.returnCase}</textarea>
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="remark" name="remark" cols="40" rows="5">${returnTemplate.remark}</textarea>
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

            <div class="formTextDiv" style="width: 100%;" id="returnFieldDiv">

            </div>
        </form>
    </div>










    </div>



<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/returntemplate/returntemplate.js'></script>
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
                dataType:{
                    required:true
                },
                parameterName:{
                    required:true
                },
                parameterValue:{
                    required:true
                },
                parameterValueType:{
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
                dataType:{
                    required : "返回数据类型不能为空!"
                },
                parameterName:{
                    required : "拉单状态的参数名不能为空!"
                },
                parameterValue:{
                    required : "拉单成功的参数值不能为空!"
                },
                parameterValueType:{
                    required : "拉单成功参数值的数据类型不能为空!"
                }
            },

            submitHandler : function() {
                var id = $("#mbId").val();
                var formDataOne = $("#addSupplierForm").serialize();
                var formDataTwo = $("#addSupplierFormTwo").serialize();

                var dataOne = JSON.stringify(formDataOne);
                var dataTwo = JSON.stringify(formDataTwo);
                $.ajax({
                    url : ctx+ "/returntemplate/update.do",
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
                            window.location.href = ctx + "/returntemplate/list.do";
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