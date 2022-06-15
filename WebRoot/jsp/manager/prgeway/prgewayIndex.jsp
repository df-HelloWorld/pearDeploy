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
                <div class = "condQueryLabelDiv">通道名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="gewayName" name ="gewayName">
                </div>

                <%--<div class = "condQueryLabelDiv">秘钥key：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<input type ="text" class ="inputCommonSty" id="secretKey" name ="secretKey">--%>
                <%--</div>--%>

                <%--<div class = "condQueryLabelDiv">商铺号：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<input type ="text" class ="inputCommonSty" id="payId" name ="payId">--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">通道属性：</div>
                <div class="formCtrlDiv">
                    <select id="attributeType" name ="attributeType">
                        <option value="0">===请选择===</option>
                        <option value="1">代收</option>
                        <option value="2">代付</option>
                    </select>
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">通道名称</th>
            <%--<th width="150">秘钥key</th>--%>
            <%--<th width="200">商铺号</th>--%>
            <th width="120">通道属性</th>
            <th width="130">总账</th>
            <th width="150">保底金额</th>
            <%--<th width="130">余额</th>--%>
            <%--<th width="150">锁定金额</th>--%>
            <%--<th width="150">通道类型</th>--%>
            <th width="150">是否启用</th>
            <th width="220">创建时间</th>
            <th width="230">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/prgeway/prgeway.js'></script>
</body>
</html>
