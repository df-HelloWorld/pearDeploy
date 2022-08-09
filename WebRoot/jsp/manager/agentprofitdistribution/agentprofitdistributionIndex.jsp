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
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="120">代理</th>
            <th width="170">平台通道</th>
            <th width="150">通道码名</th>
            <th width="130">渠道</th>
            <th width="140">绑定类型</th>
            <%--<th width="140">利益类型</th>--%>
            <th width="100">费率</th>
            <%--<th width="120">额外费率</th>--%>
            <th width="200">创建时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/agentprofitdistribution/agentprofitdistribution.js'></script>
</body>
</html>
