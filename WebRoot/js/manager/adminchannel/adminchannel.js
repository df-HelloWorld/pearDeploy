
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
        manyOperation_url: ctx+ "/adminchannel/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        // {"data":"roleName",},
        {"data":"channelName",},
        {"data":"channel",},
        {"data":"totalMoney",},
        {"data":"balance",},
        {"data":"lockMoney",},
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
                var isEnableHtml = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 费率 </a>'
                    +'<a class = "dataTableBtn dataShowBtn " directkey="'+oData.id+'" directName="'+oData.channelName+'" href = "javascript:void(0);"> 分润 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminchannel/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn" href="'+ctx+'/adminchannel/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
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

        //审核
        $("#edit").live("click",function(){
            var channelId = $(this).attr('directkey');
            $.ajax({url : ctx+ "/channelplatformgewaycodelink/dataAllList.do",
                type : 'post',
                dataType : 'json',
                data :{
                    channelId:channelId
                },
                success : function(data) {
                    var table ="";
                    table+="<table    class='datatable tables'>"
                    table+="<tr>";
                    table+="<td>渠道名称</td>";
                    table+="<td>平台码名称</td>";
                    table+="<td>费率</td>";
                    table+="<td>操作</td>";
                    table+="</tr>";
                    for(var i=0;i<data.length;i++){
                        table+="<tr>";
                        table+="<td>"+data[i].channelName+"</td>";
                        table+="<td>"+data[i].codeName+"</td>";
                        // var  serviceChargeId="serviceCharge"+i;
                        table+="<td><input type='text' id='"+data[i].id+"'  value="+data[i].serviceCharge+"></td>";
                        table+="<td> <input type = 'button' id = 'saveServiceCharge' directkey="+ data[i].id +"  href = 'javascript:void(0);' class = 'buttonClass imginput'value = '保存' /></td>";
                        table+="</tr>";
                    }
                    table+="</table>";

                    document.getElementById("divTable").innerHTML=table;
                    // $("#divTable").innerText=table;
                    // if (data.success) {
                    //     debugger
                    //     var m = data.data;
                    //
                    //
                    openDialog("show","");
                    // } else {
                    //     art.alert(data.msg);
                    // }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });



        $("#saveServiceCharge").live("click",function(){
            var channelId = $(this).attr('directkey');
            var serviceCharge = $("#"+channelId).val();
            $.ajax({url : ctx+ "/channelplatformgewaycodelink/updateServiceCharge.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:channelId,
                    serviceCharge:serviceCharge
                },
                success : function(data) {
                    alert(data.msg);
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });




        // start

        //点击分润
        $(".dataShowBtn").live("click",function(){
            var channelId = $(this).attr('directkey');
            var agentType = 3;
            var channelName = $(this).attr('directName');


            agentServiceCharge(channelId,agentType,channelName);

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
    }


}



function agentServiceCharge(channelId,agentType,channelName){
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
                alert("lenght:"+ pfGewayCodeDataList.length);

                // 两者针对-start

                // show-div-start

                var shtml="";

                shtml +="<div style='border: 2px solid red;'>";
                shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='channelId' name='channelId' value='"+channelId+"' />";
                for (var i=0;i<agentDataList.length>0;i++) {
                    if(i!=0&&i%6==0){
                        shtml +="<br/>";
                    }
                    shtml += "<input type='checkbox' name='agentId' id='agentId' value="+agentDataList[i].id+"> "+agentDataList[i].agentName+"&nbsp;&nbsp;&nbsp;&nbsp;";
                }

                shtml +="<br />";
                shtml +="<br />";



                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>平台通道</td>";
                shtml +="<td>平台通道码</td>";
                shtml +="<td>用户费率</td>";
                shtml +="<td>通道费率</td>";
                shtml +="<td>分润</td>";
                shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<pfGewayCodeDataList.length>0;i++) {
                    if (pfGewayCodeDataList[i].channelId == channelId){
                        shtml +="<tr>";
                        shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+pfGewayCodeDataList[i].id+"' />";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='codeName' name='codeName' disabled='disabled' value='"+pfGewayCodeDataList[i].codeName+"'/> </td>";
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='pfGewayCode' name='pfGewayCode' disabled='disabled' value='"+pfGewayCodeDataList[i].pfGewayCode+"'/> </td>";
                        shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='' name='' disabled='disabled' value='"+pfGewayCodeDataList[i].serviceCharge+"' /> </td>";
                        if(pfGewayCodeDataList[i].splicing.length>=20){
                            shtml +="<td onmouseover='tooltip.pop(this, \""+pfGewayCodeDataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+pfGewayCodeDataList[i].splicing.substring(0,20)+"。。</td>";
                        }else {
                            shtml +="<td onmouseover='tooltip.pop(this, \""+pfGewayCodeDataList[i].splicing+"\",\""+{offsetY:-25, smartPosition:true}+"\")'> "+pfGewayCodeDataList[i].splicing+"</td>";
                        }
                        shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='serviceCharge_"+pfGewayCodeDataList[i].id+"' name='serviceCharge_"+pfGewayCodeDataList[i].id+"'  value=''/> </td>";

                        shtml +="<td>";
                        // shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='onclickUpdateServiceCharge("+pfGewayCodeDataList[i].id+","+channelId+","+dataList[i].gewayCodeId+","+agentId+","+agentType+",\""+channelName+"\")' >保存 </a>";
                        shtml +="<a style='background-color:#00b7ee;' class='formBtn' href = 'javascript:void(0);' onclick='' >保存 </a>";

                        shtml += "</td>";
                        shtml +="</tr>";
                    }

                }

                shtml +="</table>";





                shtml +="</div>";

                /*shtml +="<table id='dataDayTable' name='dataDayTable'>";

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

                shtml +="</table>";*/

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




/*function channelplatformgewaycodelinkData(channelId){
    $.ajax({url : ctx+ "/channelplatformgewaycodelink/dataAllInfoList.do",
        type : 'post',
        dataType : 'json',
        data :{
            channelId:channelId
        },
        success : function(data) {
            if (data.success) {
                alert("哈哈");
                pfGewayCodeDataList=data.data;


            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}*/




$(function(){
    account.indexInit();
})
