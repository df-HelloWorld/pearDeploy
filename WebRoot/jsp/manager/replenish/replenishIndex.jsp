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

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
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
            <th width="130">通道码</th>
            <th width="130">通道</th>
            <th width="120">订单金额</th>
            <th width="100">手续费</th>
            <th width="120">实际金额</th>
            <th width="120">拉单状态</th>
            <th width="120">订单状态</th>
            <th width="120">拉单类型</th>
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
            <span><font color="red">补单</font></span>
        </h2>
    </div>
    <div class="formContentDiv" style="padding-right:0px">
        <form id="newFirstStoreForm">
            <input type="hidden" id="id" name="id" />
            <dl>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        平台订单
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divMyTradeNo" name="divMyTradeNo" disabled="disabled"/>
                    </div>
                </dd>


                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        商家订单
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divOutTradeNo" name="divOutTradeNo" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        渠道
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divChannelName" name="divChannelName" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        订单金额
                    </div>
                    <div class="formCtrlDiv" style="width: 200px;margin-left: 10px;">
                        <input type="text" style="width: 200px;box-sizing: border-box" class="formInput"
                               id="divTotalAmount" name="divTotalAmount" disabled="disabled"/>
                    </div>
                </dd>

                <dd style="border-top: none;">
                    <div class="formTextDiv" style="width: 100px;">
                        <font color="red">*</font>是否计算收益
                    </div>
                    <div class="formCtrlDiv">
                        <select class="formInput" name="isProfit" id="isProfit">
                            <option value="0">==请选择==</option>
                            <option value="1">不计算收益</option>
                            <option value="2">计算收益</option>
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
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/replenish/replenish.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
