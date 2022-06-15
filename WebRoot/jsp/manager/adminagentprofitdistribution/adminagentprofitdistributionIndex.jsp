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
                <div class = "condQueryLabelDiv">名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="alias" name ="alias">
                </div>
                <div class = "condQueryLabelDiv">代理：</div>
                <div class="formCtrlDiv" id = "agentDiv">
                </div>

                <div class = "condQueryLabelDiv">通道码：</div>
                <div class="formCtrlDiv" id = "gewayCodeDiv">
                </div>
                <div class = "condQueryLabelDiv">渠道：</div>
                <div class="formCtrlDiv" id = "channelDiv">
                </div>

                <div class = "condQueryLabelDiv">绑定类型：</div>
                <div class="formCtrlDiv">
                    <select id="bindingType" name="bindingType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">通道码绑定</option>
                        <option value="2">渠道绑定</option>
                        <option value="3">两者绑定</option>
                    </select>
                </div>

                <div class = "condQueryLabelDiv">利益类型：</div>
                <div class="formCtrlDiv">
                    <select id="serviceChargeType" name="serviceChargeType" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">固定费率</option>
                        <option value="2">额外费率</option>
                    </select>
                </div>


                <div class = "condQueryLabelDiv">使用状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="isEnable" name="isEnable" class='text-input medium-input'>
                        <option value="0" selected="selected">=请选择=</option>
                        <option value="1">禁用</option>
                        <option value="2">启用</option>
                    </select>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="添加利益分配" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="140">名称</th>
            <th width="120">代理</th>
            <th width="150">通道码名</th>
            <th width="130">渠道</th>
            <th width="140">绑定类型</th>
            <th width="140">利益类型</th>
            <th width="100">费率</th>
            <th width="120">额外费率</th>
            <th width="120">使用状态</th>
            <th width="180">创建时间</th>
            <th width="250">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/adminagentprofitdistribution/adminagentprofitdistribution.js'></script>
</body>
</html>
