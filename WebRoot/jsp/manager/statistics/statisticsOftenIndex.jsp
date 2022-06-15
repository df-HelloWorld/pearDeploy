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
                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="startCreateTime" id="startCreateTime" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${model.startCreateTime}"/>
                </div>

                <div class = "condQueryLabelDiv">截止时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="endCreateTime" id="endCreateTime" size="10" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${model.endCreateTime}"/>
                </div>

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>

                <div class = "condQueryLabelDiv">通道码：</div>
                <div class="formCtrlDiv" id = "gewayCodeDiv">
                </div>

                <div class = "condQueryLabelDiv">收益计算：</div>
                <div class="formCtrlDiv" >
                    <select id="isProfit" name="isProfit" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">不计算</option>
                        <option value="2">计算</option>
                    </select>
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
            <th width="150">通道名称</th>
            <th width="160">通道码名称</th>
            <th width="140">订单金额</th>
            <th width="170">成功订单金额</th>
            <th width="160">订单金额成功率</th>
            <th width="130">订单数</th>
            <th width="150">成功订单数</th>
            <th width="160">订单数成功率</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/statistics/statisticsOften.js'></script>
</body>
</html>
