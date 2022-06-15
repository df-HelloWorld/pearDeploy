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
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="channelName" name ="channelName">
                </div>
                <div class = "condQueryLabelDiv">开户名：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="accountName" name ="accountName">
                </div>
                <div class = "condQueryLabelDiv">银行卡：</div>
                <div class="formCtrlDiv">
                    <input type ="text" class ="inputCommonSty" id="bankCard" name ="bankCard">
                </div>
                <div class = "condQueryLabelDiv">提现状态：</div>
                <div class="formCtrlDiv" id = "moduleTypeDiv">
                    <select id="withdrawStatus" name="withdrawStatus" class='text-input medium-input'>
                        <option value="0" selected="selected">===请选择===</option>
                        <option value="1">提现中</option>
                        <option value="2">提现驳回</option>
                        <option value="3">提现成功</option>
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
            <th width="120">提现金额</th>
            <th width="120">手续费</th>
            <th width="150">银行</th>
            <th width="120">开户名</th>
            <th width="250">银行卡号</th>
            <th width="200">备注</th>
            <th width="150">提现状态</th>
            <th width="200">创建时间</th>
            <th width="120">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>




<div id="show" style="display:none;width:500px;">
    <div class="formHeadDiv">
        <h2>
            <span><font color="red">渠道提现审核</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOrderNo" name="divOrderNo" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        渠道名称
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divChannelName" name="divChannelName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        提现金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divMoney" name="divMoney" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        手续费
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divServiceCharge" name="divServiceCharge" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        银行
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divBankName" name="divBankName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        开户名
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divAccountName" name="divAccountName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        银行卡号
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divBankCard" name="divBankCard" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        <font color="red">*</font>提现状态
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="checkWithdrawStatus" id="checkWithdrawStatus">
                            <option value="0">==请选择==</option>
                            <option value="1">提现中</option>
                            <option value="2">提现驳回</option>
                            <option value="3">提现成功</option>
                        </select>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        备注
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <%--<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"--%>
                               <%--id="remark" name="remark"/>--%>
                        <textarea id="remark" name="remark" cols="40" rows="5"></textarea>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        说明
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <%--<input type="text" style="width: 200px;box-sizing: border-box" class="formInput"--%>
                               <%--id="withdrawExplain" name="withdrawExplain"/>--%>
                        <textarea id="withdrawExplain" name="withdrawExplain" cols="40" rows="5"></textarea>
                    </div>
                </dd>



                <dd style="border-top: none;">
                    <div class="formTextDiv"></div>
                    <%--<div class="formCtrlDiv">
                        -------------------------------------------------------------------------------
                    </div>--%>
                </dd>
                <dd style=" height: 60px; line-height: 58px;">
                    <div class="formCtrlDiv">
							<span style="margin-left: 100px;">
								<input type="submit" style="background-color: #767DC3" class="formBtn" value="保　存" />
								<%--<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />--%>
								<input type="reset" onClick="javascript :closeDialog('show')" style="background-color: #767DC3" class="formBtn" value=" 返 回 " />
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>




<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/adminchannelwithdraw/adminchannelwithdraw.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
