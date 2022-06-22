<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>策略列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">平台通道名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="codeName" name ="codeName">
                </div>

                <div class = "condQueryLabelDiv">平台通道码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="pfGewayCode" name ="pfGewayCode">
                </div>

                <div class = "condQueryLabelDiv">是否跳转：</div>
                <div class="formCtrlDiv">
                    <select id="isJump" name="isJump" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">不跳转</option>
                        <option value="2">跳转</option>
                    </select>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th >平台通道名称</th>
            <th >平台通道码</th>
            <th >支持金额</th>
            <th >绑定订单信息</th>
            <th >是否启用</th>
            <th width="20%">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/prplatformgewaycode/prplatformgewaycode.js'></script>
</body>
</html>
