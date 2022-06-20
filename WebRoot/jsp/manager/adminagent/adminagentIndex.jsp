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
                <div class = "condQueryLabelDiv">账号：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountNum" name ="accountNum">
                </div>
                <%--<div class = "condQueryLabelDiv">代理类型：</div>--%>
                <%--<div class="formCtrlDiv">--%>
                    <%--<select id="agentType" name="agentType">--%>
                        <%--<option value="0" selected="selected">=请选择=</option>--%>
                        <%--<option value="1">针对渠道</option>--%>
                        <%--<option value="2">针对通道</option>--%>
                        <%--<option value="3">两者针对</option>--%>
                    <%--</select>--%>
                <%--</div>--%>

                <div class = "condQueryLabelDiv">代理类型：</div>
                <div class="formCtrlDiv" id = "agentTypeDiv">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索" />
                </div>
                <div class="searchdiv">
                    <input type = "button" id = "butReset" class = "buttonClass imginput" value = "重置" />
                </div>
                <%--<c:if test="${ACCOUNT.roleId==1}">--%>
                    <%--<div class = "searchdiv">--%>
                        <%--<input type="button" class = "buttonClass imginput addbtn" value="新增账号" style="margin-left: 30px;" >--%>
                    <%--</div>--%>
                <%--</c:if>--%>
                <div class = "searchdiv">
                    <input type="button" class = "buttonClass imginput addbtn" value="新增账号" style="margin-left: 30px;" >
                </div>
            </div>
        </form>
    </div>

    <table class="datatable tables">
        <thead>
        <tr>
            <th width="150">账号名称</th>
            <%--<th width="150">角色</th>--%>
            <th width="150">代理名称</th>
            <th width="150">总额</th>
            <th width="150">余额</th>
            <th width="150">锁定金额</th>
            <%--<th width="150">提现类型</th>--%>
            <th width="150">代理类型</th>
            <th width="150">使用状态</th>
            <th width="400">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/adminagent/adminagent.js'></script>
</body>
</html>
