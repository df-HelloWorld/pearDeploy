
var datatable;
var basePath = $("#excDataHid").val();
var agentDataList;// 代理数据
var pfGewayCodeDataList;// 平台通道

var account = {
    //地址
    url:{
        list_url : ctx + '/adminchannel/list.do',
        dataList_url : ctx + "/adminchannel/dataList.do",
        add_url : ctx+ "/adminchannel/add.do",
        update_url : ctx+ "/adminchannel/update.do",
        queryId_url: ctx+ "/adminchannel/getId.do",
        delete_url: ctx+ "/adminchannel/delete.do",
        manyOperation_url: ctx+ "/adminchannel/manyOperation.do",
        reset_secret_key_url: ctx+ "/adminchannel/resetSecretKey.do"

    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        // {"data":"roleName",},
        {"data":"channelName",},
        {"data":"channel",},
        {"data":"totalMoney",},
        {"data":"balance",},
        // {"data":"lockMoney",},
        {"data":"secretKey",},
        {"data":"googleKey",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isGoogle==1){
                    html='<span></span>';
                }else if(oData.isGoogle==2){
                    html=oData.googleKey;
                }
                $(nTd).html(html);
            }
        },
        // {"data":"channelType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.channelType==1){
        //             html='<span>代收</span>';
        //         }else if(oData.channelType==2){
        //             html='<span>代付</span>';
        //         }else if(oData.channelType==3){
        //             html='<span>其它</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"isSynchro",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.isSynchro==1){
        //             html='<span>要同步</span>';
        //         }else if(oData.isSynchro==2){
        //             html='<span>不同步</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataEditServiceChargeBtn " id = "editServiceCharge" directkey="' + oData.id + '" directName="'+oData.channelName+'" href = "javascript:void(0);"> 费率 </a>'
                html += '<a class = "dataTableBtn dataShowBtn " directkey="'+oData.id+'" directName="'+oData.channelName+'" href = "javascript:void(0);"> 分润 </a>'
                html += '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminchannel/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                html += '<a class = "dataTableBtn dataTableDeleteBtn" href="'+ctx+'/adminchannel/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                html += ' <a class = "dataTableBtn dataTableEnableBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">重置秘钥</a>';
                    // +isEnableHtml
                html += ' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        this.queryTotal();
        this.queryAgentAll();
        this.queryPfGewayCodeAll();

        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminchannel/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['accountNum'] = $("#accountNum").val();
            account.condJsonData['channelType'] = $("#channelType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['accountNum'] = "";
            $("#accountNum").val("");
            account.condJsonData['channelType'] = "0";
            $("#channelType").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableResetBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        // //启用/禁用
        // $(".dataTableEnableBtn").live("click",function(){
        //     var id = $(this).attr('directkey');
        //     var isEnable = $(this).attr('directValue');
        //     var data = {
        //         id:id,
        //         isEnable:isEnable
        //     }
        //     common.manyOperation(data);
        // });

        //重置秘钥
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.resetSecretKey(data);
        });

        // 费率编辑
        $("#editServiceCharge").live("click",function(){
            var channelId = $(this).attr('directkey');
            var channelName = $(this).attr('directName');

            showServiceCharge(channelId, channelName);

        });



        // $("#saveServiceCharge").live("click",function(){
        //     var channelId = $(this).attr('directkey');
        //     var serviceCharge = $("#"+channelId).val();
        //     $.ajax({url : ctx+ "/channelplatformgewaycodelink/updateServiceCharge.do",
        //         type : 'post',
        //         dataType : 'json',
        //         data :{
        //             id:channelId,
        //             serviceCharge:serviceCharge
        //         },
        //         success : function(data) {
        //             alert(data.msg);
        //         },
        //         error : function(data) {
        //             art.alert(data.info);
        //         }
        //     });
        // });




        // start

        //点击分润
        $(".dataShowBtn").live("click",function(){
            var channelId = $(this).attr('directkey');
            var agentType = 3;
            var channelName = $(this).attr('directName');


            agentServiceCharge(channelId,channelName);

        });

        // end


    },


    //查询所有平台通道码
    queryPfGewayCodeAll:function(){
        var url = basePath + "channelplatformgewaycodelink/dataAllInfoList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            pfGewayCodeDataList=data;
        });
    },

    //查询所有代理-类型:针对两者
    queryAgentAll:function(){
        var url = basePath + "adminagent/dataAllList.do";
        var data = {
            agentType:3
        };
        common.ajax(url,data,function(data){
            agentDataList=data;
        });
    },

    //汇总数据填充
    //查询所有余额汇总数据
    queryTotal:function(){
        var url = basePath + "adminchannel/totalData.do";
        // var accountNum = $("#accountNum").val();

        var data = {
            // "accountNum":accountNum
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         总额：";
            shtml += "<font color='red'>" + data.totalTotalMoney + "</font>";
            shtml += "      余额：";
            shtml += "<font color='red'>" + data.totalBalance + "</font>";
            $("#totalDiv").html(shtml);
        });
    }


}



// 费率：展现渠道与通道的具体费率详情
function showServiceCharge(channelId,channelName){
    $("#channelServiceChargeDiv").html("");//清空展现层
    var divChannelName = channelName;

    $("#divChannelName").html(divChannelName);

    $.ajax({url : ctx+ "/channelplatformgewaycodelink/dataAllInfoJsonList.do",
        type : 'post',
        dataType : 'json',
        data :{
            channelId:channelId
        },
        success : function(data) {
            if (data.success) {
                var dataList=data.data;


                // show-div-start

                var shtml="";

                shtml +="<div style='border: 2px solid red;'>";
                shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='channelId' name='channelId' value='"+channelId+"' />";


                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>平台通道</td>";
                shtml +="<td>平台通道码</td>";
                shtml +="<td>通道费率</td>";
                shtml +="<td>使用状态</td>";
                shtml +="<td>费率</td>";
                shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<dataList.length>0;i++) {
                    shtml +="<tr>";
                    shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='channelPlatformGewayCodeLinkId' name='channelPlatformGewayCodeLinkId' value='"+dataList[i].id+"' />";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='codeName' name='codeName' disabled='disabled' value='"+dataList[i].codeName+"'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayCode' name='pfGewayCode' disabled='disabled' value='"+dataList[i].pfGewayCode+"'/> </td>";
                    if(dataList[i].splicing.length>=20){
                        shtml +="<td onmouseover='tooltip.pop(this, \""+dataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].splicing.substring(0,20)+"。。</td>";
                    }else {
                        shtml +="<td onmouseover='tooltip.pop(this, \""+dataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].splicing+"</td>";
                    }
                    if (dataList[i].isEnable == 1){
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='禁用'/> </td>";
                    }else if (dataList[i].isEnable == 2){
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='启用'/> </td>";
                    }
                    shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='updateChannelServiceCharge_"+dataList[i].id+"' name='updateChannelServiceCharge_"+dataList[i].id+"' value='"+dataList[i].serviceCharge+"' /> </td>";

                    shtml +="<td>";

                    // shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickAddServiceCharge("+pfGewayCodeDataList[i].id+","+channelId+",\""+channelName+"\")' >保存 </a>";

                    shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickUpdateChannelServiceCharge("+dataList[i].id+","+dataList[i].pfGewayCodeId+","+dataList[i].channelId+",\""+channelName+"\")' >保存 </a>";
                    if (dataList[i].isEnable==1) {
                        shtml +="<a style='background-color:#00ff00;' class='formBtn' directValue='2' href = 'javascript:void(0);' onclick='onclickChannelIsEnable("+dataList[i].id+",2,"+channelId+",\""+channelName+"\")'>启用 </a>";
                    }else {
                        shtml +="<a style='background-color:#8EA534;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickChannelIsEnable("+dataList[i].id+",1,"+channelId+",\""+channelName+"\")'>禁用 </a>";
                    }


                    shtml += "</td>";
                    shtml +="</tr>";

                }

                shtml +="</table>";



                shtml +="<div>"
                shtml +="<center>";
                // shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickAddServiceCharge("+channelId+",\""+channelName+"\")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('show')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                shtml +="</center>";

                shtml +="</div>";




                shtml +="</div>";


                // show-div-end



                $("#channelServiceChargeDiv").html(shtml);
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




//更新：更新保存费率
function onclickUpdateChannelServiceCharge(id,pfGewayCodeId,channelId,channelName){
    var serviceCharge = $("#updateChannelServiceCharge_" + id).val();

    $.ajax({
        url : ctx+ "/channelplatformgewaycodelink/updateServiceCharge.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            channelId:channelId,
            pfGewayCodeId:pfGewayCodeId,
            serviceCharge:serviceCharge
        },
        success : function(data) {
            if (data.success) {
                alert("更新成功！！！");
                showServiceCharge(channelId,channelName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}


//费率：启用/禁用
function onclickChannelIsEnable(id,isEnable,channelId,channelName){
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
        url : ctx+ "/channelplatformgewaycodelink/manyOperation.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            isEnable:isEnable
        },
        success : function(data) {
            if (data.success) {
                alert(showMsg);
                showServiceCharge(channelId,channelName);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}






function agentServiceCharge(channelId,channelName){
    $("#agentProfitInfoDiv").html("");//清空展现层

    var agentTypeInfo = channelName + "> 两者针对";

    $("#divAgentName").html(agentTypeInfo);

    $.ajax({url : ctx+ "/adminagentprofitdistribution/getAgentProfitDistributionByChannelList.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:channelId
        },
        success : function(data) {
            if (data.success) {
                var dataList=data.data;

                // 两者针对-start

                // show-div-start

                var shtml="";

                shtml +="<div style='border: 2px solid red;'>";
                shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='channelId' name='channelId' value='"+channelId+"' />";

                shtml +="<h3><font color='red'>代理</font></h3>";

                for (var i=0;i<agentDataList.length>0;i++) {
                    if(i!=0&&i%6==0){
                        shtml +="<br/>";
                    }
                    shtml += "<input type='checkbox' name='agentId' id='agentId' value="+agentDataList[i].id+"> "+agentDataList[i].agentName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                }

                shtml +="<br />";
                shtml +="<br />";


                shtml +="<h3><font color='red'>平台通道</font></h3>";
                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>平台通道</td>";
                shtml +="<td>平台通道码</td>";
                shtml +="<td>用户费率</td>";
                shtml +="<td>通道费率</td>";
                shtml +="<td>分润</td>";
                // shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<pfGewayCodeDataList.length>0;i++) {
                    if (pfGewayCodeDataList[i].channelId == channelId){
                        shtml +="<tr>";
                        shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayCodeId' name='pfGewayCodeId' value='"+pfGewayCodeDataList[i].id+"' />";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='codeName' name='codeName' disabled='disabled' value='"+pfGewayCodeDataList[i].codeName+"'/> </td>";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayCode' name='pfGewayCode' disabled='disabled' value='"+pfGewayCodeDataList[i].pfGewayCode+"'/> </td>";
                        shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='"+pfGewayCodeDataList[i].serviceCharge+"' /> </td>";
                        if(pfGewayCodeDataList[i].splicing.length>=20){
                            shtml +="<td onmouseover='tooltip.pop(this, \""+pfGewayCodeDataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+pfGewayCodeDataList[i].splicing.substring(0,20)+"。。</td>";
                        }else {
                            shtml +="<td onmouseover='tooltip.pop(this, \""+pfGewayCodeDataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+pfGewayCodeDataList[i].splicing+"</td>";
                        }
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='addAgentServiceCharge_"+pfGewayCodeDataList[i].id+"' name='addAgentServiceCharge_"+pfGewayCodeDataList[i].id+"'  value=''/> </td>";

                        shtml +="<td>";

                        // shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickAddServiceCharge("+pfGewayCodeDataList[i].pfGewayCodeId+","+channelId+",\""+channelName+"\")' >保存 </a>";


                        shtml += "</td>";
                        shtml +="</tr>";
                    }

                }

                shtml +="</table>";



                shtml +="<div>"
                shtml +="<center>";
                shtml +="<input type='button' style='background-color: #767DC3' class='formBtn' onclick='onclickAddServiceCharge("+channelId+",\""+channelName+"\")' value='保　存' />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                shtml +="<input type=\"reset\" onClick=\"javascript :closeDialog('showTwo')\" style=\"background-color: #767DC3\" class=\"formBtn\" value=\" 返 回 \" />";
                shtml +="</center>";

                shtml +="</div>";




                shtml +="</div>";


                shtml +="<br />";
                shtml +="<br />";

                shtml +="<h2><font color='red'>代理分润详情</font></h2>";

                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>代理</td>";
                // shtml +="<td>渠道</td>";
                shtml +="<td>平台通道</td>";
                shtml +="<td>平台通道码</td>";
                // shtml +="<td>绑定类型</td>";
                shtml +="<td>用户费率</td>";
                shtml +="<td>通道费率</td>";
                shtml +="<td>使用状态</td>";
                shtml +="<td>分润</td>";
                shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<dataList.length>0;i++) {
                    shtml +="<tr>";
                    shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                    shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='agentName' name='agentName' disabled='disabled' value='"+dataList[i].agentName+"' /> </td>";
                    // shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='channelName' name='channelName' disabled='disabled' value='"+dataList[i].channelName+"'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='codeName' name='codeName' disabled='disabled' value='"+dataList[i].codeName+"'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayCode' name='pfGewayCode' disabled='disabled' value='"+dataList[i].pfGewayCode+"'/> </td>";
                    // shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='bindingTypeName' name='bindingTypeName' disabled='disabled' value='两者针对'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayServiceCharge' name='pfGewayServiceCharge' disabled='disabled' value='"+dataList[i].pfGewayServiceCharge+"'/> </td>";
                    if(dataList[i].splicing.length>=20){
                        shtml +="<td onmouseover='tooltip.pop(this, \""+dataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].splicing.substring(0,12)+"。。</td>";
                    }else {
                        shtml +="<td onmouseover='tooltip.pop(this, \""+dataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+dataList[i].splicing+"</td>";
                    }
                    if (dataList[i].isEnable == 1){
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='禁用'/> </td>";
                    }else if (dataList[i].isEnable == 2){
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='启用'/> </td>";
                    }
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='upAgentserviceCharge_"+dataList[i].id+"' name='upAgentserviceCharge_"+dataList[i].id+"'  value='"+dataList[i].serviceCharge+"'/> </td>";
                    shtml +="<td>";
                    shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickUpdateServiceCharge("+dataList[i].id+","+dataList[i].agentId+","+dataList[i].pfGewayCodeId+","+dataList[i].channelId+",\""+channelName+"\")' >保存 </a>";
                    if (dataList[i].isEnable==1) {
                        shtml +="<a style='background-color:#00ff00;' class='formBtn' directValue='2' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",2,"+channelId+",\""+channelName+"\")'>启用 </a>";
                    }else {
                        shtml +="<a style='background-color:#8EA534;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickIsEnable("+dataList[i].id+",1,"+channelId+",\""+channelName+"\")'>禁用 </a>";
                    }
                    shtml +="<a style='background-color:#85140E;' class='formBtn' directValue='1' href = 'javascript:void(0);' onclick='onclickDelete("+dataList[i].id+",1,"+channelId+",\""+channelName+"\")'>删除 </a>";
                    shtml += "</td>";
                    shtml +="</tr>";
                }

                shtml +="</table>";

                // 两者针对-end

                // show-div-end



                $("#agentProfitInfoDiv").html(shtml);
                openDialog("showTwo","");
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}



//添加：批量添加代理利润分层
function onclickAddServiceCharge(channelId,channelName){
    // var serviceCharge_id = "serviceCharge_" + id;

    var agentIdList =[];// 被复选框选中的代理ID集合
    $('input[name="agentId"]:checked').each(function(){
        agentIdList.push($(this).val());
    });

    if (agentIdList.length <= 0){
        alert("请选择代理!");
        return;
    }
    var agentArr = "";
    for (var i=0;i<agentIdList.length>0;i++) {
        agentArr += agentIdList[i] + ",";
    }

    var pfGewayServiceChargeArr = "";
    for (var i=0;i<pfGewayCodeDataList.length>0;i++) {
        var serviceCharge_for = $("#addAgentServiceCharge_" + pfGewayCodeDataList[i].id).val();
        if (serviceCharge_for != undefined && serviceCharge_for.length > 0){
            pfGewayServiceChargeArr += pfGewayCodeDataList[i].pfGewayCodeId + "," + serviceCharge_for + "#";
        }
    }

    if (pfGewayServiceChargeArr == undefined || pfGewayServiceChargeArr.length <= 0){
        alert("请您填写分润值!");
        return;
    }

    // alert("pfGewayServiceChargeArr:" + pfGewayServiceChargeArr);


    var bindingType = 3;

    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/addDataByBindingType.do",
        type : 'post',
        dataType : 'json',
        data :{
            agentArr:agentArr,
            channelId:channelId,
            pfGewayServiceChargeArr:pfGewayServiceChargeArr,
            bindingType:bindingType
        },
        success : function(data) {
            if (data.success) {
                alert("更新成功！！！");
                agentServiceCharge(channelId,channelName);
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
function onclickUpdateServiceCharge(id,agentId,pfGewayCodeId,channelId,channelName){
    // var serviceCharge_id = "serviceCharge_" + id;
    var serviceCharge = $("#upAgentserviceCharge_" + id).val();
    var bindingType = 3;

    $.ajax({
        url : ctx+ "/adminagentprofitdistribution/updateByBindingType.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:id,
            // agentId:agentId,
            channelId:channelId,
            pfGewayCodeId:pfGewayCodeId,
            serviceCharge:serviceCharge
            // bindingType:bindingType
        },
        success : function(data) {
            if (data.success) {
                alert("更新成功！！！");
                agentServiceCharge(channelId,channelName);
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
function onclickIsEnable(id,isEnable,channelId,channelName){
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
                agentServiceCharge(channelId,channelName);
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
function onclickDelete(id,yn,channelId,channelName){
    if(!confirm("确认要删除吗？")){
        return;
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
                agentServiceCharge(channelId,channelName);
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
