
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/admininorder/list.do',
        dataList_url : ctx + "/admininorder/dataList.do",
        // add_url : ctx+ "/admininorder/add.do",
        // update_url : ctx+ "/admininorder/update.do",
        // queryId_url: ctx+ "/admininorder/getId.do",
        // delete_url: ctx+ "/admininorder/delete.do",
        manyOperation_url: ctx+ "/admininorder/manyOperation.do",
        exportData_url : ctx +  "/admininorder/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"codeName",},
        {"data":"gewayCodeName",},
        {"data":"gewayName",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"actualMoney",},
        {"data":"pullOrderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.pullOrderStatus==1){
                    html='<span>成功</span>';
                }else if(oData.pullOrderStatus==2){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span>放弃</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>超时</span>';
                }else if(oData.orderStatus==3){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span>质疑</span>';
                }else if(oData.orderStatus==4){
                    // html='<span>成功</span>';
                    html='<span><font color="red">成功</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"pullOrderCodeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.pullOrderCodeType==1){
                    html='<span>平台</span>';
                }else if(oData.pullOrderCodeType==2){
                    html='<span><font color="red">通道</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"replenishType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.replenishType==1){
                    html='<span>不是补单</span>';
                }else if(oData.replenishType==2){
                    html='<span><font color="red">是补单</font></span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"isProfit",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isProfit==1){
                    html='<span><font color="red">不计算</font></span>';
                }else if(oData.isProfit==2){
                    html='<span>计算</span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"sendStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.sendStatus==0){
                    html='<span>初始化</span>';
                }else if(oData.sendStatus==1){
                    html='<span>锁定</span>';
                }else if(oData.sendStatus==2){
                    html='<span><font color="red">失败</font></span>';
                }else if(oData.sendStatus==3){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                if (oData.sendStatus == 2){
                    isEnableHtml = '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);">重发 </a>';
                }else{
                    isEnableHtml = '正常';
                }
                html = isEnableHtml;
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        channelId:0,
        pfGewayCodeId:0,
        gewayCodeId:0,
        gewayId:0,
        orderStatus:0,
        pullOrderStatus:0,
        pullOrderCodeType:0,
        replenishType:0,
        isProfit:0,
        sendStatus:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryChannelAll();
        this.queryPfGewayAll();
        this.queryGewayCodeAll();
        this.queryGewayAll();
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['pfGewayCodeId'] = $("#pfGewayCodeId").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
            account.condJsonData['gewayId'] = $("#gewayId").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['pullOrderStatus'] = $("#pullOrderStatus").val();
            account.condJsonData['pullOrderCodeType'] = $("#pullOrderCodeType").val();
            account.condJsonData['replenishType'] = $("#replenishType").val();
            account.condJsonData['isProfit'] = $("#isProfit").val();
            account.condJsonData['sendStatus'] = $("#sendStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            $("#myTradeNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['pfGewayCodeId'] = "0";
            $("#pfGewayCodeId").val("0");
            account.condJsonData['gewayCodeId'] = "0";
            $("#gewayCodeId").val("0");
            account.condJsonData['gewayId'] = "0";
            $("#gewayId").val("0");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['pullOrderStatus'] = "0";
            $("#pullOrderStatus").val("0");
            account.condJsonData['pullOrderCodeType'] = "0";
            $("#pullOrderCodeType").val("0");
            account.condJsonData['replenishType'] = "0";
            $("#replenishType").val("0");
            account.condJsonData['isProfit'] = "0";
            $("#isProfit").val("0");
            account.condJsonData['sendStatus'] = "0";
            $("#sendStatus").val("0");
            account.condJsonData['curdayStart'] = "";
            $("#curdayStart").val("");
            account.condJsonData['curdayEnd'] = "";
            $("#curdayEnd").val("");
            common.showDatas(account.condJsonData,account.list);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id
            }
            common.cf(data);
        });

        // 数据按照Excel格式导出
        $("#butExcelExport").click(function () {
            common.dataExportExcel($("#condForm"));
        });
    },



    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryChannelAll:function(){
        var url = basePath + "adminchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有平台通道-无分页-下拉框选项:
    queryPfGewayAll:function(){
        var url = basePath + "prplatformgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='pfGewayCodeId' name='pfGewayCodeId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].codeName+"==="+dataList[i].pfGewayCode+"</option>";
            }
            shtml +="</select>";
            $("#pfGewayDiv").html(shtml);
        });
    },



    //下拉框数据填充
    //查询所有通道码-无分页-下拉框选项:
    queryGewayCodeAll:function(){
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayCodeId' name='gewayCodeId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayCodeName+"==="+dataList[i].myGewayCode+"</option>";
            }
            shtml +="</select>";
            $("#gewayCodeDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有通道-无分页-下拉框选项:
    queryGewayAll:function(){
        var url = basePath + "prgeway/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayId' name='gewayId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayName+"</option>";
            }
            shtml +="</select>";
            $("#gewayDiv").html(shtml);
        });
    },




    // //汇总数据填充
    // //查询所有订单汇总数据
    // queryTotal:function(){
    //     var url = basePath + "tpdata/totalData.do";
    //     var myTradeNo = $("#myTradeNo").val();
    //     var outTradeNo = $("#outTradeNo").val();
    //     var tradeStatus = $("#tradeStatus").val();
    //     var sendStatus = $("#sendStatus").val();
    //     var curdayStart = $("#curdayStart").val();
    //     var curdayEnd = $("#curdayEnd").val();
    //     var data = {
    //         "myTradeNo":myTradeNo,
    //         "outTradeNo":outTradeNo,
    //         "tradeStatus":tradeStatus,
    //         "sendStatus":sendStatus,
    //         "curdayStart":curdayStart,
    //         "curdayEnd":curdayEnd
    //     };
    //     common.ajax(url,data,function(data){
    //         var data=data;
    //         var shtml="";
    //         shtml += "汇总：         订单金额：";
    //         shtml += "<font color='red'>" + data.totalMoney + "</font>";
    //         shtml += "      手续费：";
    //         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
    //         shtml += "      实际金额：";
    //         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
    //         $("#totalDiv").html(shtml);
    //     });
    // }

    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "admininorder/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var outTradeNo = $("#outTradeNo").val();
        var channelId = $("#channelId").val();
        var pfGewayCodeId = $("#pfGewayCodeId").val();
        var gewayCodeId = $("#gewayCodeId").val();
        var gewayId = $("#gewayId").val();
        var orderStatus = $("#orderStatus").val();
        var pullOrderStatus = $("#pullOrderStatus").val();
        var pullOrderCodeType = $("#pullOrderCodeType").val();
        var replenishType = $("#replenishType").val();
        var isProfit = $("#isProfit").val();
        var sendStatus = $("#sendStatus").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "myTradeNo":myTradeNo,
            "outTradeNo":outTradeNo,
            "channelId":channelId,
            "pfGewayCodeId":pfGewayCodeId,
            "gewayCodeId":gewayCodeId,
            "gewayId":gewayId,
            "orderStatus":orderStatus,
            "pullOrderStatus":pullOrderStatus,
            "pullOrderCodeType":pullOrderCodeType,
            "replenishType":replenishType,
            "isProfit":isProfit,
            "sendStatus":sendStatus,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            shtml += "      成功订单金额：";
            shtml += "<font color='red'>" + data.totalSucMoney + "</font>";
            shtml += "      订单金额成功率：";
            shtml += "<font color='red'>" + data.totalMoneyRatio + "%</font>";
            shtml += "      实际金额：";
            shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
            shtml += "      成功实际金额：";
            shtml += "<font color='red'>" + data.totalSucActualMoney + "</font>";
            shtml += "      实际金额成功率：";
            shtml += "<font color='red'>" + data.totalActualRatio + "%</font>";
            shtml += "      实际金额手续费：";
            shtml += "<font color='red'>" + data.totalActualServiceCharge + "</font>";
            shtml += "      条数：";
            shtml += "<font color='red'>" + data.totalNum + "</font>";
            shtml += "      成功条数：";
            shtml += "<font color='red'>" + data.totalSucNum + "</font>";
            shtml += "      条数成功率：";
            shtml += "<font color='red'>" + data.totalNumRatio + "%</font>";
            $("#totalDiv").html(shtml);
        });
    }

}

// function outQueryTotal(){
//     var url = basePath + "tpdata/totalData.do";
//     var myTradeNo = $("#myTradeNo").val();
//     var outTradeNo = $("#outTradeNo").val();
//     var tradeStatus = $("#tradeStatus").val();
//     var sendStatus = $("#sendStatus").val();
//     var curdayStart = $("#curdayStart").val();
//     var curdayEnd = $("#curdayEnd").val();
//     var data = {
//         "myTradeNo":myTradeNo,
//         "outTradeNo":outTradeNo,
//         "tradeStatus":tradeStatus,
//         "sendStatus":sendStatus,
//         "curdayStart":curdayStart,
//         "curdayEnd":curdayEnd
//     };
//     common.ajax(url,data,function(data){
//         var data=data;
//         var shtml="";
//         shtml += "汇总：         订单金额：";
//         shtml += "<font color='red'>" + data.totalMoney + "</font>";
//         shtml += "      手续费：";
//         shtml += "<font color='red'>" + data.totalServiceCharge + "</font>";
//         shtml += "      实际金额：";
//         shtml += "<font color='red'>" + data.totalActualMoney + "</font>";
//         $("#totalDiv").html(shtml);
//     });
// }

$(function(){
    account.indexInit();
})
