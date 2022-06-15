
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/inorder/list.do',
        dataList_url : ctx + "/inorder/dataList.do",
        // add_url : ctx+ "/inorder/add.do",
        // update_url : ctx+ "/inorder/update.do",
        // queryId_url: ctx+ "/inorder/getId.do",
        // delete_url: ctx+ "/inorder/delete.do",
        manyOperation_url: ctx+ "/inorder/manyOperation.do",
        exportData_url : ctx +  "/inorder/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"codeName",},
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
                    isEnableHtml = '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.dataCoreId+'"  directValue="2" href = "javascript:void(0);">重发 </a>';
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
        codeName:null,
        orderStatus:0,
        pullOrderStatus:0,
        replenishType:0,
        sendStatus:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        this.queryTotal();
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['codeName'] = $("#codeName").val();
            account.condJsonData['orderStatus'] = $("#orderStatus").val();
            account.condJsonData['pullOrderStatus'] = $("#pullOrderStatus").val();
            account.condJsonData['replenishType'] = $("#replenishType").val();
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
            account.condJsonData['codeName'] = "";
            $("#codeName").val("");
            account.condJsonData['orderStatus'] = "0";
            $("#orderStatus").val("0");
            account.condJsonData['pullOrderStatus'] = "0";
            $("#pullOrderStatus").val("0");
            account.condJsonData['replenishType'] = "0";
            $("#replenishType").val("0");
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


    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "inorder/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var outTradeNo = $("#outTradeNo").val();
        var codeName = $("#codeName").val();
        var orderStatus = $("#orderStatus").val();
        var pullOrderStatus = $("#pullOrderStatus").val();
        var replenishType = $("#replenishType").val();
        var sendStatus = $("#sendStatus").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();
        var data = {
            "myTradeNo":myTradeNo,
            "outTradeNo":outTradeNo,
            "codeName":codeName,
            "orderStatus":orderStatus,
            "pullOrderStatus":pullOrderStatus,
            "replenishType":replenishType,
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
