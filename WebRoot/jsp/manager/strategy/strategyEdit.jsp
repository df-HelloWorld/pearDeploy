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
        <h2>编辑策略</h2>
    </div>
    <div class="formContentDiv">
        <form id="addSupplierForm">
            <ul>
                <c:set var="dl" value="${account}"/>
                <input type="hidden" id="id" name="id" value="${dl.id}">
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>策略名称</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="stgName" name="stgName" value="${dl.stgName}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>策略类型</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="stgType" name="stgType" value="${dl.stgType}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require"><font color="red">*</font>策略Key</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="stgKey" name="stgKey" value="${dl.stgKey}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">策略Int值</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="stgNumValue" name="stgNumValue" value="${dl.stgNumValue}" 	maxlength="240" />
                    </div>
                </li>
                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">策略Str值</span>
                    </div>
                    <div class="formCtrlDiv">
                        <input type="text" class="formInput" id="stgValue" name="stgValue" value="${dl.stgValue}" 	maxlength="240" />
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">策略Str的Big值</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="stgBigValue" name="stgBigValue" cols="70" rows="9">${dl.stgBigValue}</textarea>
                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require" ><font color="red">*</font>数据类型：</span>
                    </div>
                    <div class="formCtrlDiv">
                        <select id="dataType" name="dataType">
                            <option value="">===请选择===</option>
                            <c:if test="${dl.dataType == 1}">
                                <option value="1" selected="selected">普通</option>
                                <option value="2">分割</option>
                                <option value="3">JSON</option>
                            </c:if>
                            <c:if test="${dl.dataType == 2}">
                                <option value="1">普通</option>
                                <option value="2" selected="selected">分割</option>
                                <option value="3">JSON</option>
                            </c:if>
                            <c:if test="${dl.dataType == 3}">
                                <option value="1">普通</option>
                                <option value="2">分割</option>
                                <option value="3" selected="selected">JSON</option>
                            </c:if>
                        </select>

                    </div>
                </li>

                <li style="border-top: none;">
                    <div class="formTextDiv">
                        <span class="require">策略说明</span>
                    </div>
                    <div class="formCtrlDiv">
                        <textarea id="stgExplain" name="stgExplain" cols="70" rows="9">${dl.stgExplain}</textarea>
                    </div>
                </li>



                <li>
                    <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                        <input type="submit" class="formBtn" style="background-color: #54D8FE" value="修 改" /> <span>
						</span> <input type="reset" class="formBtn" style="background-color: #54D8FE" value="重  置" />
                        <input type="button" onClick="javascript :history.back(-1);" class="formBtn" style="background-color: #54D8FE" value=" 返 回 " />
                    </div>
                </li>
            </ul>
        </form>
    </div>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type="text/javascript">
    $(function(){
        //密码输入验证
        $("#addSupplierForm").validate({
            rules:{
                stgName:{
                    required:true,
                    maxlength:80
                },
                stgType:{
                    required:true,
                    maxlength:80
                },
                stgKey:{
                    required:true,
                    maxlength:80
                }
            },
            messages: {
                stgName:{
                    required : "策略名称不能为空!",
                    maxlength : "策略名称长度最多是80个字符!"
                },
                stgType:{
                    required:"策略类型不能为空!",
                    number:"策略类型长度最多是80个字符!"
                },
                stgKey:{
                    required:"策略Key不能为空!",
                    number:"策略Key长度最多是80个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                $.ajax({
                    url : ctx+ "/strategy/update.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        if (data.success) {
                            alert("修改成功！");
                            window.location.href = ctx + "/strategy/list.do";
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