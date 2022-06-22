
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
        {"data":"lockMoney",},
        // {"data":"withdrawType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.withdrawType==1){
        //             html='<span>平台内</span>';
        //         }else if(oData.withdrawType==2){
        //             html='<span><font color="red">平台外</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"agentType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.agentType==1){
        //             html='<span>针对渠道</span>';
        //         }else if(oData.agentType==2){
        //             html='<span>针对通道</span>';
        //         }else if(oData.agentType==3){
        //             html='<span><font color="red">两者针对</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
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
                // var html = '';
                // var isEnableHtml = '';
                // html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/accountagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                //     +'<a class = "dataTableBtn" href="'+ctx+'/accountagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                //     +isEnableHtml
                //     +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/adminagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>';
                if (oData.agentType <= 2){
                    html += '<a class = "dataTableBtn dataShowBtn"  directkey="'+oData.id+'"  directValue="'+oData.agentType+'" directName="'+oData.agentName+'" href = "javascript:void(0);"> 分润 </a>';
                }else{
                    html += '分润';
                }
                html += '<a class = "dataTableBtn" href="'+ctx+'/adminagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
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


            agentServiceCharge(id,agentType,agentName);

            /*var agentTypeInfo = "";
            if (agentType ==1 ){
                agentTypeInfo = agentName + "> 绑定渠道";
            } else if(agentType == 2){
                agentTypeInfo = agentName + "> 通道码";
            }
            $("#divAgentName").html(agentTypeInfo);


            $.ajax({url : ctx+ "/adminagentprofitdistribution/getAgentProfitDistributionList.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var dataList=data.data;


                        if (agentType == 1){
                            // 针对渠道

                            // show-div-start
                            var shtml="";

                            for (var i=0;i<channelDataList.length>0;i++) {
                                for (var j=0;j<dataList.length>0;j++) {
                                    if (dataList[j].channelId == channelDataList[i].id){
                                        channelDataList[i].companyName = "sb";
                                    }
                                }
                            }

                            shtml +="<div style='border: 2px solid red;'>";
                            shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='agentId' name='agentId' value='"+id+"' />";
                            for (var i=0;i<channelDataList.length>0;i++) {
                                if(i!=0&&i%5==0){
                                    shtml +="<br>";
                                }
                                if (channelDataList[i].companyName == "sb"){
                                    // shtml += "<input type='radio' name='channelId' id='channelId' value="+channelDataList[i].id+" disabled > "+channelDataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                                    shtml += "<input type='radio' name='channelId' id='channelId' disabled value="+channelDataList[i].id+"> <font color='red'>"+channelDataList[i].channelName+"</font> &nbsp;&nbsp;&nbsp;&nbsp;";
                                } else {
                                    shtml += "<input type='radio' name='channelId' id='channelId' value="+channelDataList[i].id+"> "+channelDataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                                }
                            }

                            // shtml +="<li style='border-top: none;'>";
                            // shtml +="<div class='formTextDiv'>";
                            // shtml +="<span class='require'><font color='red'>*</font>分润</span>";
                            // shtml +="</div>";
                            // shtml +="<div class='formCtrlDiv'>";
                            // shtml +="<input type='text' class='formInput' id='agentName' name='agentName' />";
                            // shtml +="</div>";
                            // shtml +="</li>";

                            shtml +="<div>"
                            shtml +="<center><font color='red'>*</font>分润：<input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge' value=''/></center>";


                            shtml +="<center>";
                            // shtml +="<center><span style='margin-left: 100px;'></center>";
                            // shtml +="<span style='margin-left: 100px;'>";
                            shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickSaveServiceCharge("+agentType+")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                            // shtml +="<input type='reset' onClick='javascript :closeDialog('show')' style='background-color: #767DC3' class='formBtn' value=' 返 回 ' />";
                            shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('show')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                            // shtml +="</span>";
                            shtml +="</center>";

                            shtml +="</div>";

                            shtml +="</div>";

                            shtml +="<table id='dataDayTable' name='dataDayTable'>";

                            shtml +="<tr>";
                            shtml +="<td>代理</td>";
                            shtml +="<td>渠道</td>";
                            shtml +="<td>绑定类型</td>";
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
                                shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge'  value='"+dataList[i].serviceCharge+"'/> </td>";
                                shtml +="<td>";
                                shtml +="<a class = 'dataTableBtn dataTableSaveByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' href = 'javascript:void(0);'>保存 </a>";
                                if (dataList[i].isEnable==1) {
                                    shtml +="<a class = 'dataTableBtn dataTableEnableByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' directValue='2' href = 'javascript:void(0);'>启用 </a>";
                                }else {
                                    shtml +="<a class = 'dataTableBtn dataTableEnableByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' directValue='1' href = 'javascript:void(0);'>禁用 </a>";
                                }
                                shtml +="<a class = 'dataTableBtn dataTableDeleteByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' />";
                                shtml += "</td>";
                                shtml +="</tr>";
                            }

                            shtml +="</table>";
                            // show-div-end
                        }else if(agentType == 2){

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
            });*/
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



function onclickSaveServiceCharge(agentId,agentType,agentName){
    alert("onclick-------agentId:"+ agentId + ",agentType:" + agentType + ",agentName:" + agentName);
    // var agentId = $("#agentId").val();
    var channelId = $('input[name=channelId]:checked').val();
    var serviceCharge = $("#serviceCharge").val();
    var bindingType = "";
    if (agentType == 1){
        bindingType = 2;
    } else if(agentType == 2){
        bindingType = 1;
    }
    alert("agentId:"+ agentId +",channelId:" + channelId + ",serviceCharge:" + serviceCharge + ",agentType:" + agentType + ",bindingType:" + bindingType);


    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/add.do",
        type : 'post',
        dataType : 'json',
        data :{
            agentId:agentId,
            channelId:channelId,
            serviceCharge:serviceCharge
        },
        success : function(data) {
            // alert("data:" + data);
            // alert("data.data:" + data.data);
            if (data.success) {
                alert("添加成功！！！");
                // window.location.href = ctx + "/geway/list.do";
                // window.location.href = ctx + "/geway/list.do";
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
    alert("agentId:"+ agentId + ",agentType:" + agentType + ",agentName:" + agentName);
    var agentTypeInfo = "";
    if (agentType ==1 ){
        agentTypeInfo = agentName + "> 绑定渠道";
    } else if(agentType == 2){
        agentTypeInfo = agentName + "> 通道码";
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


                if (agentType == 1){
                    // 针对渠道

                    // show-div-start
                    var shtml="";

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
                        if(i!=0&&i%5==0){
                            shtml +="<br>";
                        }
                        if (channelDataList[i].companyName == "sb"){
                            // shtml += "<input type='radio' name='channelId' id='channelId' value="+channelDataList[i].id+" disabled > "+channelDataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                            shtml += "<input type='radio' name='channelId' id='channelId' disabled value="+channelDataList[i].id+"> <font color='red'>"+channelDataList[i].channelName+"</font> &nbsp;&nbsp;&nbsp;&nbsp;";
                        } else {
                            shtml += "<input type='radio' name='channelId' id='channelId' value="+channelDataList[i].id+"> "+channelDataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                        }
                    }

                    // shtml +="<li style='border-top: none;'>";
                    // shtml +="<div class='formTextDiv'>";
                    // shtml +="<span class='require'><font color='red'>*</font>分润</span>";
                    // shtml +="</div>";
                    // shtml +="<div class='formCtrlDiv'>";
                    // shtml +="<input type='text' class='formInput' id='agentName' name='agentName' />";
                    // shtml +="</div>";
                    // shtml +="</li>";

                    shtml +="<div>"
                    shtml +="<center><font color='red'>*</font>分润：<input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge' value=''/></center>";


                    shtml +="<center>";
                    // shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickSaveServiceCharge("+agentId+","+agentType+","+agentName+")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickSaveServiceCharge("+agentId+","+agentType+",'"+agentName+"')' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('show')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                    shtml +="</center>";

                    shtml +="</div>";

                    shtml +="</div>";

                    shtml +="<table id='dataDayTable' name='dataDayTable'>";

                    shtml +="<tr>";
                    shtml +="<td>代理</td>";
                    shtml +="<td>渠道</td>";
                    shtml +="<td>绑定类型</td>";
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
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge' name='serviceCharge'  value='"+dataList[i].serviceCharge+"'/> </td>";
                        shtml +="<td>";
                        shtml +="<a class = 'dataTableBtn dataTableSaveByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' href = 'javascript:void(0);'>保存 </a>";
                        if (dataList[i].isEnable==1) {
                            shtml +="<a class = 'dataTableBtn dataTableEnableByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' directValue='2' href = 'javascript:void(0);'>启用 </a>";
                        }else {
                            shtml +="<a class = 'dataTableBtn dataTableEnableByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' directValue='1' href = 'javascript:void(0);'>禁用 </a>";
                        }
                        shtml +="<a class = 'dataTableBtn dataTableDeleteByAgentProfitDistributionBtn'  directkey='"+dataList[i].id+"' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' />";
                        shtml += "</td>";
                        shtml +="</tr>";
                    }

                    shtml +="</table>";
                    // show-div-end
                }else if(agentType == 2){

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




$(function(){
    account.indexInit();
})
