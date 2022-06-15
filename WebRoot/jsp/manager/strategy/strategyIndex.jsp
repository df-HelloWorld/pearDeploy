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
                <div class = "condQueryLabelDiv">策略名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="stgName" name ="stgName">
                </div>

                <div class = "condQueryLabelDiv">策略类型：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="stgType" name ="stgType">
                </div>

                <div class = "condQueryLabelDiv">策略Key：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="stgKey" name ="stgKey">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增策略" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="250">策略名称</th>
            <th width="150">策略类型</th>
            <th width="200">策略Key</th>
            <th width="100">Int值</th>
            <th width="250">Str值</th>
            <th width="150">数据类型</th>
            <th width="180">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/strategy/strategy.js'></script>
</body>
</html>
