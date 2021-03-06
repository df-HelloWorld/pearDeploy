<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">关联名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>
                <div class = "condQueryLabelDiv">平台通道：</div>
                <div class="formCtrlDiv" id = "pfGewayDiv">
                </div>

                <div class = "condQueryLabelDiv">使用状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="isEnable" name="isEnable" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">禁用</option>
                        <option value="2">启用</option>
                    </select>
                </div>
                <div class = "condQueryLabelDiv">平台通道码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="pfGewayCode" name ="pfGewayCode">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="添加关联关系" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="200">关联名称</th>
            <th width="150">渠道名称</th>
            <th width="170">平台通道</th>
            <th width="160">平台通道码</th>
            <th width="100">费率</th>
            <%--<th width="145">额外费率</th>--%>
            <th width="150">使用状态</th>
            <th width="160">创建时间</th>
            <th width="250">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/channelplatformgewaycodelink/channelplatformgewaycodelink.js'></script>
</body>
</html>
