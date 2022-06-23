
var datatable;
var basePath = $("#excDataHid").val();
var channelDataList;// 渠道数据
var gewayCodeDataList;// 通道数据
var account = {
    //地址
    url:{
        list_url : ctx + '/adminagent/list.do',
        dataList_url : ctx + "/adminagent/dataList.do",
        add_url : ctx+ "/adminagent/add.do",
        update_url : ctx+ "/adminagent/update.do",
        queryId_url: ctx+ "/adminagent/getId.do",
        delete_url: ctx+ "/adminagent/delete.do",
        manyOperation_url: ctx+ "/adminagent/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        // {"data":"roleName",},
        {"data":"agentName",},
        {"data":"totalMoney",},
        {"data":"balance",},
        // {"data":"lockMoney",},
        {"data":"agentTypeName",},
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==1){
                    html='<span><font color="red">禁用</font></span>';
                }else if(oData.isEnable==2){
                    html='<span>启用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/adminagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>';
                if (oData.agentType <= 2){
                    html += '<a class = "dataTableBtn dataShowBtn"  directkey="'+oData.id+'"  directValue="'+oData.agentType+'" directName="'+oData.agentName+'" href = "javascript:void(0);"> 分润 </a>';
                }else{
                    html += '<a class = "dataTableBtn dataShowBtn" disabled="disabled"  directkey="'+oData.id+'"  directValue="'+oData.agentType+'" directName="'+oData.agentName+'" href = "javascript:void(0);"> 分润 </a>';
                }
                html += '<a  style="background-color: #767DC3" class = "dataTableBtn" href="'+ctx+'/adminagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }


        // {"data":"id",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html = '';
        //         html += '<a class="formBtn" id = "dataTableResetBtn" style="background-color: #767DC3" href="'+ctx+'/adminagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>';
        //         if (oData.agentType <= 2){
        //             html += '<a class="formBtn" id = "dataShowBtn" style="background-color: #767DC3"  directkey="'+oData.id+'"  directValue="'+oData.agentType+'" directName="'+oData.agentName+'" href = "javascript:void(0);"> 分润 </a>';
        //         }else{
        //             html += '分润';
        //         }
        //         html += '<a class="formBtn" style="background-color: #767DC3" href="'+ctx+'/adminagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>';
        //         if (oData.isEnable == 1){
        //             html += '<a class="formBtn" id ="dataTableEnableBtn" style="background-color: #767DC3" directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
        //         }else {
        //             html += '<a class="formBtn" id ="dataTableEnableBtn" style="background-color: #767DC3" directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
        //         }
        //         html += '<a class="formBtn" id ="dataTableDeleteBtn" style="background-color: #767DC3"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
        //         $(nTd).html(html);
        //     }
        // }

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null,
        agentType:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        this.queryAgentType();// 获取策略中代理类型
        this.queryChannelAll();
        this.queryGewayCodeAll();

        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminagent/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['accountNum'] = $("#accountNum").val();
            account.condJsonData['agentType'] = $("#agentType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['accountNum'] = "";
            $("#accountNum").val("");
            account.condJsonData['agentType'] = "0";
            $("#agentType").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableDeleteBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });



        // start

        //点击分润
        $(".dataShowBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var agentType = $(this).attr('directValue');
            var agentName = $(this).attr('directName');

            if (agentType == 3){
                alert("两者针对类型不在这里配置分润!");
                return;
            }
            if (agentType == 4){
                alert("平台类型的代理无需配置分润!");
                return;
            }

            agentServiceCharge(id,agentType,agentName);

        });

        // end


    },

    //下拉框数据填充
    //查询所有代理类型-无分页-下拉框选项:
    queryAgentType:function(){
        var url = basePath + "strategy/dataJsonList.do";
        var data = {
            "stgType":18
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentType' name='agentType'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].stgValueTwo+">"+dataList[i].stgValueOne+"</option>";
            }
            shtml +="</select>";
            $("#agentTypeDiv").html(shtml);
        });
    },


    //查询所有通道码
    queryGewayCodeAll:function(){
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            gewayCodeDataList=data;
        });
    },

    //查询所有渠道
    queryChannelAll:function(){
        var url = basePath + "adminchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            channelDataList=data;
        });
    }




}


//新增：新增保存分润-渠道
function onclickSaveServiceCharge(agentId,agentType,agentName){
    // alert("onclick-------agentId:"+ agentId + ",agentType:" + agentType + ",agentName:" + agentName);
    // var agentId = $("#agentId").val();
    var channelId = $('input[name=channelId]:checked').val();
    var serviceCharge = $("#serviceCharge").val();
    var bindingType = "";
    if (agentType == 1){
        bindingType = 2;
    } else if(agentType == 2){
        bindingType = 1;
    }
    // alert("agentId:"+ agentId +",channelId:" + channelId + ",serviceCharge:" + serviceCharge + ",agentType:" + agentType + ",bindingType:" + bindingType);


    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/add.do",
        type : 'post',
        dataType : 'json',
        data :{
            agentId:agentId,
            channelId:channelId,
            serviceCharge:serviceCharge,
            bindingType:bindingType
        },
        success : function(data) {
            if (data.success) {
                alert("添加成功！！！");
                agentServiceCharge(agentId,agentType,agentName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });

}



//新增：新增保存分润-通道码
function onclickSaveServiceChargeByGewayCode(agentId,agentType,agentName){
    var gewayCodeId = $('input[name=gewayCodeId]:checked').val();
    var serviceCharge = $("#serviceCharge").val();
    var bindingType = "";
    if (agentType == 1){
        bindingType = 2;
    } else if(agentType == 2){
        bindingType = 1;
    }


    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/add.do",
        type : 'post',
        dataType : 'json',
        data :{
            agentId:agentId,
            gewayCodeId:gewayCodeId,
            serviceCharge:serviceCharge,
            bindingType:bindingType
        },
        success : function(data) {
            if (data.success) {
                alert("添加成功！！！");
                agentServiceCharge(agentId,agentType,agentName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });

}




function agentServiceCharge(agentId,agentType,agentName){
    $("#agentProfitInfoDiv").html("");//清空展现层

    var agentTypeInfo = "";
    if (agentType ==1 ){
        agentTypeInfo = agentName + "> 绑定渠道";
    } else if(agentType == 2){
        agentTypeInfo = agentName + "> 绑定通道码";
    }
    $("#divAgentName").html(agentTypeInfo);

    $.ajax({url : ctx+ "/adminagentprofitdistribution/getAgentProfitDistributionList.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:agentId
        },
        success : function(data) {
            if (data.success) {
                var dataList=data.data;

                var shtml="";
                if (agentType == 1){
                    // 针对渠道-start

                    // show-div-start


                    for (var i=0;i<channelDataList.length>0;i++) {
                        for (var j=0;j<dataList.length>0;j++) {
                            if (dataList[j].channelId == channelDataList[i].id){
                                channelDataList[i].companyName = "sb";
                            }
                        }
                    }

                    shtml +="<div style='border: 2px solid red;'>";
                    shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='agentId' name='agentId' value='"+agentId+"' />";
                    for (var i=0;i<channelDataList.length>0;i++) {
                        if(i!=0&&i%6==0){
                            shtml +="<br>";
                        }
                        if (channelDataList[i].companyName == "sb"){
                            shtml += "<input type='radio' name='channelId' id='channelId' disabled value="+channelDataList[i].id+"> <font color='red'>"+channelDataList[i].channelName+"</font> &nbsp;&nbsp;&nbsp;&nbsp;";
                        } else {
                            shtml += "<input type='radio' name='channelId' id='channelId' value="+channelDataList[i].id+"> "+channelDataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                        }
                    }

                    shtml +="<div>"
                    shtml +="<center><font color='red'>*</font>分润：<input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge' value=''/></center>";
                    shtml +="<center>";
                    shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickSaveServiceCharge("+agentId+","+agentType+",\""+agentName+"\")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('show')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                    shtml +="</center>";

                    shtml +="</div>";

                    shtml +="</div>";

                    shtml +="<table id='dataDayTable' name='dataDayTable'>";

                    shtml +="<tr>";
                    shtml +="<td>代理</td>";
                    shtml +="<td>渠道</td>";
                    shtml +="<td>绑定类型</td>";
                    shtml +="<td>使用状态</td>";
                    shtml +="<td>分润</td>";
                    shtml +="<td>操作</td>";
                    shtml +="</tr>";
                    for (var i=0;i<dataList.length>0;i++) {
                        shtml +="<tr>";
                        shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                        shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='agentName' name='agentName' disabled='disabled' value='"+dataList[i].agentName+"' /> </td>";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='channelName' name='channelName' disabled='disabled' value='"+dataList[i].channelName+"'/> </td>";
                        if (dataList[i].bindingType == 2){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='bindingTypeName' name='bindingTypeName' disabled='disabled' value='渠道绑定'/> </td>";
                        }else if (dataList[i].bindingType == 1){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='bindingTypeName' name='bindingTypeName' disabled='disabled' value='通道码绑定'/> </td>";
                        }
                        if (dataList[i].isEnable == 1){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='禁用'/> </td>";
                        }else if (dataList[i].isEnable == 2){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='启用'/> </td>";
                        }
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge_"+dataList[i].id+"' name='serviceCharge_"+dataList[i].id+"'  value='"+dataList[i].serviceCharge+"'/> </td>";
                        shtml +="<td>";
                        shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickUpdateServiceCharge("+dataList[i].id+","+dataList[i].channelId+","+dataList[i].gewayCodeId+","+agentId+","+agentType+",\""+agentName+"\")' >保存 </a>";
                        if (dataList[i].isEnable==1) {
                            shtml +="<a style='background-color:#00ff00;' class='formBtn' directValue='2' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",2,"+agentId+","+agentType+",\""+agentName+"\")'>启用 </a>";
                        }else {
                            shtml +="<a style='background-color:#8EA534;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",1,"+agentId+","+agentType+",\""+agentName+"\")'>禁用 </a>";
                        }
                        shtml +="<a style='background-color:#85140E;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickDelete("+dataList[i].id+",1,"+agentId+","+agentType+",\""+agentName+"\")'>删除 </a>";
                        shtml += "</td>";
                        shtml +="</tr>";
                    }

                    shtml +="</table>";

                    // 针对渠道-end

                    // show-div-end
                }else if(agentType == 2){

                    // 针对通道-start

                    // show-div-start

                    for (var i=0;i<gewayCodeDataList.length>0;i++) {
                        for (var j=0;j<dataList.length>0;j++) {
                            if (dataList[j].gewayCodeId == gewayCodeDataList[i].id){
                                gewayCodeDataList[i].sendIdentity = "sb";
                            }
                        }
                    }

                    shtml +="<div style='border: 2px solid red;'>";
                    shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='agentId' name='agentId' value='"+agentId+"' />";
                    for (var i=0;i<gewayCodeDataList.length>0;i++) {
                        if(i!=0&&i%4==0){
                            shtml +="<br>";
                        }
                        if (gewayCodeDataList[i].sendIdentity == "sb"){
                            shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' disabled value="+gewayCodeDataList[i].id+"> <font color='red'>"+gewayCodeDataList[i].gewayName+"=="+gewayCodeDataList[i].gewayCodeName+"</font> &nbsp;&nbsp;&nbsp;&nbsp;";
                        } else {
                            shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' value="+gewayCodeDataList[i].id+"> "+gewayCodeDataList[i].gewayName+"== "+gewayCodeDataList[i].gewayCodeName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                        }
                    }

                    shtml +="<div>"
                    shtml +="<center><font color='red'>*</font>分润：<input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge' value=''/></center>";
                    shtml +="<center>";
                    shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickSaveServiceChargeByGewayCode("+agentId+","+agentType+",\""+agentName+"\")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('show')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                    shtml +="</center>";

                    shtml +="</div>";

                    shtml +="</div>";

                    shtml +="<table id='dataDayTable' name='dataDayTable'>";

                    shtml +="<tr>";
                    shtml +="<td>代理</td>";
                    shtml +="<td>通道</td>";
                    shtml +="<td>通道码名称</td>";
                    shtml +="<td>绑定类型</td>";
                    shtml +="<td>使用状态</td>";
                    shtml +="<td>分润</td>";
                    shtml +="<td>操作</td>";
                    shtml +="</tr>";
                    for (var i=0;i<dataList.length>0;i++) {
                        shtml +="<tr>";
                        shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                        shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='agentName' name='agentName' disabled='disabled' value='"+dataList[i].agentName+"' /> </td>";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='gewayName' name='gewayName' disabled='disabled' value='"+dataList[i].gewayName+"'/> </td>";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='gewayCodeName' name='gewayCodeName' disabled='disabled' value='"+dataList[i].gewayCodeName+"'/> </td>";
                        if (dataList[i].bindingType == 2){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='bindingTypeName' name='bindingTypeName' disabled='disabled' value='渠道绑定'/> </td>";
                        }else if (dataList[i].bindingType == 1){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='bindingTypeName' name='bindingTypeName' disabled='disabled' value='通道码绑定'/> </td>";
                        }
                        if (dataList[i].isEnable == 1){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='禁用'/> </td>";
                        }else if (dataList[i].isEnable == 2){
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='启用'/> </td>";
                        }
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge_"+dataList[i].id+"' name='serviceCharge_"+dataList[i].id+"'  value='"+dataList[i].serviceCharge+"'/> </td>";
                        shtml +="<td>";
                        shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickUpdateServiceCharge("+dataList[i].id+","+dataList[i].channelId+","+dataList[i].gewayCodeId+","+agentId+","+agentType+",\""+agentName+"\")' >保存 </a>";
                        if (dataList[i].isEnable==1) {
                            shtml +="<a style='background-color:#00ff00;' class='formBtn' directValue='2' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",2,"+agentId+","+agentType+",\""+agentName+"\")'>启用 </a>";
                        }else {
                            shtml +="<a style='background-color:#8EA534;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",1,"+agentId+","+agentType+",\""+agentName+"\")'>禁用 </a>";
                        }
                        shtml +="<a style='background-color:#85140E;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickDelete("+dataList[i].id+",1,"+agentId+","+agentType+",\""+agentName+"\")'>删除 </a>";
                        shtml += "</td>";
                        shtml +="</tr>";
                    }

                    shtml +="</table>";

                    // 针对通道-end

                    // show-div-end

                }


                $("#agentProfitInfoDiv").html(shtml);
                openDialog("show","");
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}



//更新：更新保存分润
function onclickUpdateServiceCharge(id,channelId,gewayCodeId,agentId,agentType,agentName){
    // var serviceCharge_id = "serviceCharge_" + id;
    var serviceCharge = $("#serviceCharge_" + id).val();
    var bindingType = "";
    if (agentType == 1){
        bindingType = 2;
    } else if(agentType == 2){
        bindingType = 1;
    }


    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/update.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            agentId:agentId,
            channelId:channelId,
            gewayCodeId:gewayCodeId,
            serviceCharge:serviceCharge,
            bindingType:bindingType
        },
        success : function(data) {
            if (data.success) {
                alert("更新成功！！！");
                agentServiceCharge(agentId,agentType,agentName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}


//分润：启用/禁用
function onclickIsEnable(id,isEnable,agentId,agentType,agentName){
    var showMsg = '';
    if (isEnable == 1){
        if(!confirm("确认要禁用吗？")){
            return;
        }
        showMsg = '禁用成功!';
    }else if(isEnable == 2){
        if(!confirm("确认要启用吗？")){
            return;
        }
        showMsg = '启用成功!';
    }

    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/manyOperation.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            isEnable:isEnable
        },
        success : function(data) {
            if (data.success) {
                alert(showMsg);
                agentServiceCharge(agentId,agentType,agentName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}


//分润：删除
function onclickDelete(id,yn,agentId,agentType,agentName){
    if(!confirm("确认要删除吗？")){
        return;
    }
    for (var i=0;i<channelDataList.length>0;i++) {
        channelDataList[i].companyName = "";
    }

    for (var i=0;i<gewayCodeDataList.length>0;i++) {
        gewayCodeDataList[i].sendIdentity = "";
    }

    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/delete.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            yn:yn
        },
        success : function(data) {
            if (data.success) {
                alert("删除成功！");
                agentServiceCharge(agentId,agentType,agentName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}




// //查询所有渠道-外层
// function queryChannelAllByOut(){
//     $.ajax({
//         url : ctx+ "/adminchannel/dataAllList.do",
//         type : 'post',
//         dataType : 'json',
//         data :{
//         },
//         success : function(data) {
//             if (data.success) {
//                 channelDataList=data;
//             } else {
//                 art.alert(data.msg);
//             }
//         },
//         error : function(data) {
//             art.alert(data.info);
//         }
//     });
// }





$(function(){
    account.indexInit();
})
