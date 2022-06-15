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
        <h2>绑定渠道信息</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <input type="hidden" id="deployId" name="deployId" value="${deployId}">
                <input type="hidden" id="bindingType" name="bindingType" value="${bindingType}">
                <input type="hidden" id="gewayCodeIds" name="gewayCodeIds" value="${gewayCodeId}">
                <input type="hidden" id="channelIds" name="channelIds" value="${channelId}">


                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require" ><font color="red">*</font>渠道：</span>--%>
                    <%--</div>--%>

                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" id="typeName" name="typeName"	value="${typeName}" />--%>
                    <%--</div>--%>
                <%--</li>--%>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>渠道：</span>
                    </div>
                    <div class="formCtrlDiv" id = "channelDiv">
                    </div>

                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>通道信息：</span>
                    </div>
                    <div class="formCtrlDiv" id = "gewayCodeDiv">
                    </div>
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>代理信息：</span>
                    </div>
                    <div class="formCtrlDiv" id = "agentDiv">
                    </div>
                </li>

                <%--<li style="border-top: none;">--%>
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require" ><font color="red">*</font>通道信息：</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<input type="text" class="formInput" id="alias" name="alias"	maxlength="240" />--%>
                    <%--</div>--%>
                <%--</li>--%>

                <li style="border-top: none;">
                    <%--<div class="formTextDiv">--%>
                        <%--<span class="require" ><font color="red">*</font>绑定类型：</span>--%>
                    <%--</div>--%>
                    <%--<div class="formCtrlDiv">--%>
                        <%--<select id="bindingType" name="bindingType" >--%>
                            <%--<option value="" selected="selected">=请选择=</option>--%>
                            <%--<option value="1">通道码绑定</option>--%>
                            <%--<option value="2">渠道绑定</option>--%>
                            <%--<option value="3">两者绑定</option>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>利益类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="serviceChargeType" name="serviceChargeType" >
                            <option value="" selected="selected">=请选择=</option>
                            <option value="1">固定费率</option>
                            <option value="2">额外费率</option>
                        </select>
                    </div>
                </li>



                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>固定费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="serviceCharge" name="serviceCharge"	maxlength="240" />
                    </div>

                    <div class="formTextDiv">
                        <span class="require" >额外费率：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="extraServiceCharge" name="extraServiceCharge"	maxlength="240" />
                    </div>
                    <%--<div class="formTextDiv" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">--%>
                    <div class="formTextDiv">
                        <input type="button" class="formBtn" value="添  加" onclick="add()" style="background-color: #54D8FE;"/> <span>
						</span>
                    </div>
                </li>

                <br>
                <br>
                <br>
                <br>

                <%--<li>--%>
                    <div style="width: 100%;text-align:center">
                        <div id="tabList" style="text-align:center;width: 90%; margin: 50px 0 0 50px;">

                        </div>
                    </div>

                <%--</li>--%>

            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/agentprofitdistributiondeploy/agentprofitdistributiondeployUpdate.js'></script>
</body>
</html>