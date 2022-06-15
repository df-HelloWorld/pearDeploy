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
                <div class = "condQueryLabelDiv">归属通道ID：</div>
                <div class="formCtrlDiv">
                    <div id="divGewayId"></div>
                    <%--<input type ="text" class ="inputCommonSty" id="gewayId" name ="gewayId">--%>
                </div>

                <div class = "condQueryLabelDiv">通道码名称：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="gewayCodeName" name ="gewayCodeName">
                </div>


                <div class = "condQueryLabelDiv">通道码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="gewayCode" name ="gewayCode">
                </div>

                <div class = "condQueryLabelDiv">我方通道码：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="myGewayCode" name ="myGewayCode">
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
            <th width="160">通道码名称</th>
            <th width="150">通道码</th>
            <th width="160">我方通道码</th>
            <%--<th width="180">上游费率类型</th>--%>
            <th width="180">上游费率</th>
            <th width="180">上游额外费率</th>
            <th width="140">是否启用</th>
            <th width="240">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>






<div id="show" style="display:none;width:100%;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red" style="text-align:center"><div id = "divGewayCodeName"> </div></font></span>
        </h2>
    </div>
    <%--<div  style="padding-right:600px;width:auto;height:auto;">--%>
    <div>
        <form id="newFirstStoreForm">

            <table id="dataDayTable" name="dataDayTable">
                <tr style="text-align:center">
                    <td>通道名称：<input type="text" id="divGewayName" name="divGewayName" disabled="disabled"  /></td>
                    <%--<td>通道码名称：<input type="text" name="divGewayCodeName" disabled="disabled" /></td>--%>
                </tr>

                <tr style="text-align:center">
                    <td>我方通道码：<input type="text" id="divMyGewayCode" name="divMyGewayCode" disabled="disabled" /></td>
                    <td>通道码：<input type="text" id="divGewayCode" name="divGewayCode" disabled="disabled" /></td>
                </tr>
                <tr style="text-align:center">
                    <%--<td>接口地址：<input type="text" id="divInterfaceAds" name="divInterfaceAds" disabled="disabled"/><textarea id="withdrawExplain" name="withdrawExplain" cols="40" rows="5"></textarea></td>--%>
                        <td>接口地址：<textarea id="divInterfaceAds" name="divInterfaceAds" disabled="disabled" cols="40" rows="5"></textarea></td>
                        <td>我方的同步地址：<textarea id="divNotifyUrl" name="divNotifyUrl" disabled="disabled" cols="40" rows="5"></textarea></td>
                </tr>

                <%--<tr>--%>
                    <%--&lt;%&ndash;<td>我方的同步地址：<input type="text" id="divNotifyUrl" name="divNotifyUrl" disabled="disabled"/></td>&ndash;%&gt;--%>
                        <%--<td>我方的同步地址：<textarea id="divNotifyUrl" name="divNotifyUrl" disabled="disabled" cols="40" rows="5"></textarea></td>--%>
                <%--</tr>--%>

                <tr style="text-align:center">
                    <td>请求标识：<input type="text" id="divSendTag" name="divSendTag" disabled="disabled" /></td>
                    <td>成功数据回传标识：<input type="text" id="divSucTag" name="divSucTag" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>总额不扣费率：<input type="text" id="divBigTotalMoney" name="divBigTotalMoney" disabled="disabled" /></td>
                    <td>总额扣费率：<input type="text" id="divTotalMoney" name="divTotalMoney" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>当日跑量金额不扣费率：<input type="text" id="divBigTadayMoney" name="divBigTadayMoney" disabled="disabled" /></td>
                    <td>当日跑量金额扣费率：<input type="text" id="divTadayMoney" name="divTadayMoney" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>上游费率类型：<input type="text" id="divUpServiceChargeType" name="divUpServiceChargeType" disabled="disabled" /></td>
                    <td>上游费率：<input type="text" id="divUpServiceCharge" name="divUpServiceCharge" disabled="disabled" /></td>
                    <td>上游费率之额外费率：<input type="text" id="divUpExtraServiceCharge" name="divUpExtraServiceCharge" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>平台费率类型（只做参考）：<input type="text" id="divServiceChargeType" name="divServiceChargeType" disabled="disabled" /></td>
                    <td>平台费率（只做参考）：<input type="text" id="divServiceCharge" name="divServiceCharge" disabled="disabled" /></td>
                    <td>平台费率之额外费率（只做参考）：<input type="text" id="divExtraServiceCharge" name="divExtraServiceCharge" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>支持金额类型：<input type="text" id="divMoneyType" name="divMoneyType" disabled="disabled" /></td>
                    <td>支持金额：<input type="text" id="divMoneyRange" name="divMoneyRange" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>放量时间（1全天关，具体时间，3全天开）：<input type="text" id="divOpenTime" name="divOpenTime" disabled="disabled" /></td>
                    <td>每日限制放量的金额：<input type="text" id="divDayLimitMoney" name="divDayLimitMoney" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>属性类型：<input type="text" id="divCodeAttributeType" name="divCodeAttributeType" disabled="disabled" /></td>
                    <td>白名单IP：<input type="text" id="divWhiteListIp" name="divWhiteListIp" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>接收同步key：<input type="text" id="divIdentityKey" name="divIdentityKey" disabled="disabled" /></td>
                    <td>请求身份：<input type="text" id="divSendIdentity" name="divSendIdentity" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td>创建时间：<input type="text" id="divCreateTime" name="divCreateTime" disabled="disabled" /></td>
                </tr>

                <tr style="text-align:center">
                    <td></td>
                    <td><input type="reset" onClick="javascript :closeDialog('show')" style="background-color: #767DC3" class="formBtn" value=" 返 回 " /></td>
                    <td></td>
                </tr>
            </table>

        </form>
    </div>
</div>


<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/prgewaycode/prgewaycode.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
