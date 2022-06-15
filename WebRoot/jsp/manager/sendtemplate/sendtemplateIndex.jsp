<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>账号列表</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>


    <style type="text/css">
        .label {font-style:normal; font-family:宋体; }
        .errorLabel {font-style:normal; color:red; }
        .errorMessage {font-weight:bold; color:red; }
        .CTitle{
            background:#6795B4;
            padding:5px;
            text-align:left;
            color:#FFFFFF;
            font-size:13px;
            font-family: Verdana, Arial, Helvetica, sans-serif;
        }
    </style>

</head>
<body>
<div class="col_main">
    <div class = "condQueryDiv">
        <form id = "condForm">
            <div class = "condQueryCtrl">
                <div class = "condQueryLabelDiv">模板名称：</div>
                <div class="formCtrlDiv">
                <input type ="text" class ="inputCommonSty" id="template" name ="template">
                </div>

                <div class = "condQueryLabelDiv">通道：</div>
                <div class="formCtrlDiv" id = "gewayCodeDiv">
                </div>

                <div class="searchdiv">
                    <input type = "button" id = "btnQuery" class = "buttonClass imginput" value = "搜索"  />
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
            <th width="100">选择</th>
            <th width="150">模板</th>
            <th width="150">通道</th>
            <%--<th width="120">是否加密</th>--%>
            <th width="130">大小写</th>
            <th width="130">加密类型</th>
            <th width="180">秘钥放置位置</th>
            <%--<th width="180">秘钥key类型</th>--%>
            <th width="100">加密排序</th>
            <th width="150">请求数据方式</th>
            <%--<th width="120">请求案例</th>--%>
            <th width="120">使用状态</th>
            <%--<th width="120">备注</th>--%>
            <th width="220">操作</th>
        </tr>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>




<div id="show" style="display:none;width:100%;">
    <div class="formHeadDiv" >
        <h2>
            <span><font color="red"><div id = "divGewayCodeName"> </div></font></span>
        </h2>
    </div>
    <div  style="padding-right:0px;width:auto;height:auto;">
        <form id="newFirstStoreForm">
            <dl>

                <%--<div class="formTextDiv">--%>

                     <%--<tr>--%>
                         <%--<td>字段名</td>--%>
                         <%--<td>字段</td>--%>
                     <%--</tr>--%>
                <%--</div>--%>

                <div class="formTextDiv" style="width: 100%;" id="sendFieldDiv">

                </div>








                <dd style="border-top: none;">
                    <div class="formTextDiv"></div>
                    <%--<div class="formCtrlDiv">
                        -------------------------------------------------------------------------------
                    </div>--%>
                </dd>
                <dd style=" height: 60px; line-height: 58px;">
                    <div class="formCtrlDiv">
                            <span style="margin-left: 100px;">
								<input type="button" style="background-color: #767DC3" class="formBtn" onClick="addTr()" value="新增一行" />
								<%--<input type="button" style="background-color: #767DC3" class="formBtn" onClick="delTr()" value="删除一行" />--%>
							</span>
							<span style="margin-left: 100px;">
								<input type="button" style="background-color: #767DC3" class="formBtn" onclick="submitHandler()" value="保　存" />
								<%--<input type="reset"  style="background-color: #42425E" class="formBtn" value="重　置" />--%>
								<input type="reset" onClick="javascript :closeDialog('show')" style="background-color: #767DC3" class="formBtn" value=" 返 回 " />
							</span>
                    </div>
                </dd>
            </dl>
        </form>
    </div>
</div>

<div class="formTextDiv" style="visibility: hidden">
    <div class="formTextDiv" style="width: 100px;" id="hiddenFieldTypeDiv">

    </div>
</div>



<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/sendtemplate/sendtemplate.js'></script>
</body>
</html>
<style>
    .formContentDiv form .formCtrlDiv {
        margin-left: 10px;
    }
</style>
