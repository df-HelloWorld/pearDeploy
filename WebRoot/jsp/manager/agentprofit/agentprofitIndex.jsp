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
                <div class = "condQueryLabelDiv">订单号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">
                </div>

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>

                <%--<div class = "condQueryLabelDiv">平台通道：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<input type ="text" class ="inputCommonSty" id="codeName" name ="codeName">--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">收益类型：</div>
                <div class="formCtrlDiv">
                    <select id="profitType" name="profitType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">固定收益</option>
                        <option value="2">额外收益</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">订单类型：</div>
                <div class="formCtrlDiv">
                    <select id="orderType" name="orderType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">代收</option>
                        <option value="2">代付</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">开始日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayStart" id="curdayStart" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayStart}"/>
                </div>
                <div class = "condQueryLabelDiv">截止日期：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="curdayEnd" id="curdayEnd" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyyMMdd'})" value="${model.curdayEnd}" />
                </div>



                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butExcelExport" class = "buttonClass imginput" value = "Excel导出" />
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>

        <div class="formCtrlDiv" id = "totalDiv">

        </div>

        <tr>
            <th width="130">代理</th>
            <th width="130">渠道</th>
            <th width="140">平台通道</th>
            <th width="150">通道码名</th>
            <th width="180">平台订单</th>
            <th width="130">订单金额</th>
            <th width="130">订单类型</th>
            <th width="120">收益类型</th>
            <th width="100">收益</th>
            <th width="190">创建时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/agentprofit/agentprofit.js'></script>
</body>
</html>
