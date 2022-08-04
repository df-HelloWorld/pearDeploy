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


                <div class = "condQueryLabelDiv">类型：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="changeType" name="changeType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">付款订单</option>
                        <option value="2">提现</option>
                        <option value="3">提现驳回</option>
                        <option value="4">手动添加</option>
                        <option value="5">手动减少</option>
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
        <tr>
            <th width="150">平台订单</th>
            <th width="150">商家订单</th>
            <th width="120">渠道</th>
            <th width="130">平台</th>
            <th width="130">通道</th>
            <th width="130">通道码</th>
            <th width="120">订单金额</th>
            <th width="100">手续费</th>
            <th width="120">类型</th>
            <th width="120">原金额</th>
            <th width="120">变动金额</th>
            <th width="120">变动后金额</th>
            <th width="120">备注</th>
            <th width="120">时间</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/adminchannelflowingwater/adminchannelflowingwater.js'></script>
</body>
</html>
