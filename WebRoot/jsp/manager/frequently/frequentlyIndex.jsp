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
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>
                <div class = "condQueryLabelDiv">IP：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="clientIp" name ="clientIp">
                </div>
                <div class = "condQueryLabelDiv">锁定类型：</div>
                <div class="formCtrlDiv" id = "lockTypeDiv">
                    <select id="lockType" name="lockType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">锁IP</option>
                        <option value="2">锁渠道</option>
                    </select>
                </div>
                <div class = "condQueryLabelDiv">是否解锁：</div>
                <div class="formCtrlDiv" id = "isLockDiv">
                    <select id="isLock" name="isLock" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">已锁</option>
                        <option value="2">已解</option>
                    </select>
                </div>
                <div class = "condQueryLabelDiv">失效状态：</div>
                <div class="formCtrlDiv" id = "invalidStatusDiv">
                    <select id="invalidStatus" name="invalidStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">未失效</option>
                        <option value="2">已失效</option>
                        <option value="3">全部</option>
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

            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">渠道名称</th>
            <th width="200">客户端IP</th>
            <th width="200">redisKey</th>
            <th width="200">释放时间</th>
            <th width="150">锁定类型</th>
            <th width="150">来源</th>
            <th width="130">是否解锁</th>
            <th width="210">创建时间</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/frequently/frequently.js'></script>
</body>
</html>
