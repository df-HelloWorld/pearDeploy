<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
    <script type='text/javascript' src='${ctxData}js/plugins/ajaxfileupload.js'></script>
    <link rel="stylesheet" type="text/css" href="${ctxData}css/role.css?v=${version}">
    <style type="text/css">
        .manage-wrap{background-color: #E2E0DB;display: inline-block;vertical-align: top; font-size: 12px;padding: 0;width: 140px;height: 30px;line-height: 30px;margin: 0 20px 10px 0;}
        .manage-wrap > input[type='checkbox']{margin: 0 10px;vertical-align: middle;-webkit-appearance: none;appearance: none;width: 13px;height: 13px;cursor: pointer;background: #fff;border: 1px solid B9BBBE;-webkit-border-radius: 1px;-moz-border-radius: 1px;border-radius: 1px;-webkit-box-sizing: border-box;-moz-box-sizing: border-box;box-sizing: border-box;position: relative;}
        .manage-wrap > input[type=checkbox]:active{border-color: #c6c6c6;background: #ebebeb;}
        .manage-wrap > input[type=checkbox]:checked::after {content: url(${ctxData}images/checkmark.png);display: block;position: absolute;top: -5px;right: 0px;left: -5px}
        .manage-wrap > input[type=checkbox]:focus {outline: none;border-color:#4d90fe;}
        .borderBottom{border-bottom: 1px dashed #e0e0e0;margin-bottom: 10px;padding-bottom: 10px;}
    </style>
</head>
<body>
<div class="col_main">
    <div class="formHeadDiv">
        <h2>新增代理金额调整</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">


            <ul>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>关联订单号</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="myTradeNo" name="myTradeNo"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>名称/别名：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="alias" name="alias"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>代理：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="agentId" name="agentId" >
                            <option value="">===请选择===</option>
                            <c:forEach items="${tp}" var="dataList">
                                <option value="${dataList.id}">${dataList.agentName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>修改金额：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="money" name="money"	maxlength="240" />
                    </div>
                </li>


                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >金额类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="changeType" name="changeType">
                            <option value="0">===请选择===</option>
                            <option value="1">核减金额</option>
                            <option value="2">加金额</option>
                        </select>
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >是否展现：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isShow" name="isShow">
                            <option value="0">===请选择===</option>
                            <option value="1">展现</option>
                            <option value="2">不展现</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >是否是充值：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="isRecharge" name="isRecharge">
                            <option value="0">===请选择===</option>
                            <option value="1">不是充值</option>
                            <option value="2">是充值</option>
                        </select>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" >数据说明：</span>
                    </div>
                    <div class="formCtrlDiv" id = "moduleTypeDiv">
                        <input type="text" class="formInput" id="dataEplain" name="dataEplain"	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">备注</span>
                    </div>
                    <div class="formCtrlDiv">
                        <%--<input type="text" class="formInput" id="remark" name="remark"	maxlength="240" />--%>
                        <textarea class="formInput" id="remark" name="remark"></textarea>
                    </div>
                </li>

                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" value="添  加" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" value=" 返 回 " style="background-color: #54D8FE;"/>
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/adminagentchange/adminagentchange.js'></script>
<script type='text/javascript'>
    $(function(){
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                alias:{
                    required:true,
                    maxlength:80
                },
                agentId:{
                    required:true
                },
                money:{
                    required:true
                }

            },
            messages: {
                alias:{
                    required:"名称/别名：不能为空!",
                    maxlength : "名称/别名：最多是80个字符!"
                },
                agentId:{
                    required : "代理不能为空!"
                },
                money:{
                    required : "通道不能为空!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/adminagentchange/add.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("添加成功！！！");
                            window.location.href = ctx + "/adminagentchange/list.do";
                        } else {
                            art.alert(data.msg);
                        }
                    },
                    error : function(data) {
                        art.alert(data.info);
                    }
                });
                return false;
                //阻止表单提交
            }
        });
    });
</script>
</body>
</html>