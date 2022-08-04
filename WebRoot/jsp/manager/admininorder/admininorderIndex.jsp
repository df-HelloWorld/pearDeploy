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
                <div class = "condQueryLabelDiv">平台订单：</div>
                <div class="formCtrlDiv">
                <input type ="text" class ="inputCommonSty" id="myTradeNo" name ="myTradeNo">
                </div>

                <div class = "condQueryLabelDiv">商家订单：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="outTradeNo" name ="outTradeNo">
                </div>

                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>

                <div class = "condQueryLabelDiv">平台通道：</div>
                <div class="formCtrlDiv" id = "pfGewayDiv">
                </div>

                <div class = "condQueryLabelDiv">通道码：</div>
                <div class="formCtrlDiv" id = "gewayCodeDiv">
                </div>

                <div class = "condQueryLabelDiv">通道：</div>
                <div class="formCtrlDiv" id = "gewayDiv">
                </div>


                <div class = "condQueryLabelDiv">订单状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="orderStatus" name="orderStatus" class='text-input medium-input'>
                        <option value="-1" selected="selected">=请选择=</option>
                        <option value="1">初始化</option>
                        <option value="2">失败</option>
                        <option value="3">质疑</option>
                        <option value="4">成功</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">拉单状态：</div>
                <div class="formCtrlDiv" >
                    <select id="pullOrderStatus" name="pullOrderStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">拉单成功</option>
                        <option value="2">拉单失败</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">拉单类型：</div>
                <div class="formCtrlDiv" >
                    <select id="pullOrderCodeType" name="pullOrderCodeType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">平台</option>
                        <option value="2">通道</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">补单类型：</div>
                <div class="formCtrlDiv" >
                    <select id="replenishType" name="replenishType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">不是补单</option>
                        <option value="2">是补单</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">收益计算：</div>
                <div class="formCtrlDiv" >
                    <select id="isProfit" name="isProfit" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">不计算</option>
                        <option value="2">计算</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">同步状态：</div>
                <div class="formCtrlDiv" id = "sendStatusDiv">
                <select id="sendStatus" name="sendStatus" class='text-input medium-input'>
                <option value="" selected="selected">=请选择=</option>
                <option value="0">初始化</option>
                <option value="1">锁定</option>
                <option value="2">失败</option>
                <option value="3">成功</option>
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

                <div class = "condQueryLabelDiv">开始时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="createTimeStart" id="createTimeStart" size="20" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${model.createTimeStart}"/>
                </div>
                <div class = "condQueryLabelDiv">截止时间：</div>
                <div class="formCtrlDiv">
                    <input type="text" class ="inputCommonSty" name="createTimeEnd" id="createTimeEnd" size="20" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="${model.createTimeEnd}" />
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
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
        <%--<c:set var="total" value="${total}"/>--%>
        <%--汇总：${total.totalMoney}---${total.totalServiceCharge}---${total.totalActualMoney}--%>
        <tr>
            <th width="150">平台订单</th>
            <th width="150">商家订单</th>
            <th width="120">渠道</th>
            <th width="130">平台</th>
            <th width="130">通道码</th>
            <th width="130">通道</th>
            <th width="120">订单金额</th>
            <th width="100">手续费</th>
            <th width="120">实际金额</th>
            <th width="120">拉单状态</th>
            <th width="120">订单状态</th>
            <th width="120">拉单类型</th>
            <th width="180">拉单时间</th>
            <th width="120">补单类型</th>
            <th width="120">收益计算</th>
            <th width="120">同步状态</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/admininorder/admininorder.js'></script>
</body>
</html>
