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
        <form id = "addSupplierForm">
            <h3><font color="red">*渠道</font></h3>
            <div class="formCtrlDiv" id = "channelDiv">

            </div>
        </br>
        </br>
        </br>
            <h3><font color="red">*平台通道</font></h3>
            <div class="formCtrlDiv"  id = "pfGewayDiv" >

            </div>

            </br>
            </br>
            </br>

            <h1><font color="red">*通道</font></h1>
            <div class="formCtrlDiv" id = "gewayCodeDiv" >

            </div>

            </br>

            <h3><font color="red">*是否计算收益</font></h3>
            <div class="formCtrlDiv">
                <input type="radio" name='isProfit' id='isProfit' value="1" checked="checked">不计算收益
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name='isProfit' id='isProfit' value="2" >计算收益
            </div>


            </br>
            <h3><font color="red">*拉单金额</font></h3>
            <div class="formCtrlDiv">
                <input type ="text" class ="inputCommonSty" id="totalAmount" name ="totalAmount">
            </div>


            </br>
            <h3><font color="red">*是否直接跳转</font></h3>
            <div class="formCtrlDiv">
                <input type="radio" name='isJump' id='isJump' value="1" checked="checked">直接跳转
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="radio" name='isJump' id='isJump' value="2" >返回地址
            </div>


            <li>
                <div class="" style="margin-bottom: 20px; margin-top: 20px;margin-left:200px;">
                    <input type="submit" class="formBtn" value="拉  单" style="background-color: #54D8FE;"/> <span>
						</span> <input type="reset" class="formBtn" value="重  置" style="background-color: #54D8FE;" />
                </div>
            </li>

        </form>
    </div>
    <input type="hidden" id="ctxData" value="${ctxData}">

    <%--<table class="datatable tables">--%>

        <%--<thead>--%>
        <%--&lt;%&ndash;<div class = "condQueryLabelDiv" id = "channelDiv">&ndash;%&gt;--%>

        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<div class = "condQueryLabelDiv"  id = "pfGewayDiv" >&ndash;%&gt;--%>

        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<div class = "condQueryLabelDiv" id = "gewayCodeDiv" >&ndash;%&gt;--%>

        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--</thead>--%>
        <%--<tbody>--%>
        <%--</tbody>--%>
    <%--</table>--%>
</div>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/manager/inorderpull/inorderpull.js'></script>



<script type='text/javascript'>
    $(function(){
        var ctxData = $("#ctxData").val();
        // 在键盘按下并释放及提交后验证提交表单
        $("#addSupplierForm").validate({
            rules:{
                channelId:{
                    required:true,
                },
                totalAmount:{
                    required:true,
                    maxlength:80
                }
            },
            messages: {
                channelId:{
                    required : "渠道不能为空!"
                },
                totalAmount:{
                    required:"拉单金额不能为空!",
                    number:"拉单金额长度最多是80个字符!"
                }
            },

            submitHandler : function() {
                var formData = $("#addSupplierForm").serialize();
                // var isJump = $("#isJump").val();
                var isJump = $('input[name=isJump]:checked').val();
                $.ajax({
                    url : ctx+ "/inorderpull/pull.do",
                    type : 'post',
                    dataType : 'json',
                    data :formData,
                    success : function(data) {
                        // alert("data:" + data);
                        // alert("data.data:" + data.data);
                        if (data.success) {
                            alert("添加成功！！！");
                            // window.location.href = ctx + "/geway/list.do";
                            // window.location.href = ctx + "/geway/list.do";
                            if (isJump == 1){
                                window.open(data.data);
                            }else {
                                window.open(ctxData + "jsp/manager/show/showIndex.jsp?vehicleId="+ data.data);
                            }
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
