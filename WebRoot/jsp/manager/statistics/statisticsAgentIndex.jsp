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
                <div class = "condQueryLabelDiv">日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curday" id="curday" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curday}"/>
                </div>

                <div class = "condQueryLabelDiv">代理：</div>
                <div class="formCtrlDiv" id = "agentDiv">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>

        <div class="formCtrlDiv" id = "totalDiv">

        </div>

        <tr>
            <th width="130">日期</th>
            <th width="160">渠道</th>
            <th width="140">总额</th>
            <th width="130">余额</th>
            <%--<th width="140">锁定金额</th>--%>
            <th width="140">跑量金额</th>
            <th width="140">收益</th>
            <th width="120">加金额</th>
            <th width="120">减金额</th>
            <th width="170">提现中的金额</th>
            <th width="170">提现成功金额</th>
            <th width="170">提现失败金额</th>

        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/statistics/statisticsAgent.js'></script>
</body>
</html>
