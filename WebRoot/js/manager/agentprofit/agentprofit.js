
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/agentprofit/list.do',
        dataList_url : ctx + "/agentprofit/dataList.do",
        add_url : ctx+ "/agentprofit/add.do",
        update_url : ctx+ "/agentprofit/update.do",
        queryId_url: ctx+ "/agentprofit/getId.do",
        delete_url: ctx+ "/agentprofit/delete.do",
        manyOperation_url: ctx+ "/agentprofit/manyOperation.do",
        exportData_url : ctx +  "/agentprofit/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"agentName",},
        // {"data":"channelName",},
        {"data":"agentType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.agentType==1){
                    html='<span>'+oData.channelName+'</span>';
                }else if(oData.agentType==2){
                    html='<span><font color="red">xx渠道</font></span>';
                }else if(oData.agentType==3){
                    html='<span>'+oData.channelName+'</span>';
                }else if(oData.agentType==4){
                    html='<span>'+oData.channelName+'</span>';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"codeName",},

        {"data":"agentType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.agentType==1){
                    html='<span>'+oData.codeName+'</span>';
                }else if(oData.agentType==2){
                    html='<span><font color="red">xx平台</font></span>';
                }else if(oData.agentType==3){
                    html='<span>'+oData.codeName+'</span>';
                }else if(oData.agentType==4){
                    html='<span>'+oData.codeName+'</span>';
                }
                $(nTd).html(html);
            }
        },

        // {"data":"gewayCodeName",},
        {"data":"agentType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.agentType==1){
                    html='<span><font color="red">xx通道</font></span>';
                }else if(oData.agentType==2){
                    html='<span>'+oData.gewayCodeName+'</span>';
                }else if(oData.agentType==3){
                    // html='<span>'+oData.gewayCodeName+'</span>';
                    html='<span><font color="red">xx通道</font></span>';
                }else if(oData.agentType==4){
                    html='<span>'+oData.gewayCodeName+'</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"myTradeNo",},
        {"data":"totalAmount",},
        {"data":"orderType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderType==1){
                    html='<span>代收</span>';
                }else if(oData.orderType==2){
                    html='<span><font color="red">代付</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"profitType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.profitType==1){
                    html='<span>固定收益</span>';
                }else if(oData.profitType==2){
                    html='<span><font color="red">额外收益</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"profit",},
        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        channelName:null,
        codeName:null,
        profitType:0,
        orderType:0,
        curdayStart:0,
        curdayEnd:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 查询条件 - 下拉框数据获取
        this.queryTotal();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['codeName'] = $("#codeName").val();
            account.condJsonData['profitType'] = $("#profitType").val();
            account.condJsonData['orderType'] = $("#orderType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            $("#myTradeNo").val("");
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            account.condJsonData['codeName'] = "";
            $("#codeName").val("");
            account.condJsonData['profitType'] = "0";
            $("#profitType").val("0");
            account.condJsonData['orderType'] = "0";
            $("#orderType").val("0");
            account.condJsonData['curdayStart'] = "";
            $("#curdayStart").val("");
            account.condJsonData['curdayEnd'] = "";
            $("#curdayEnd").val("");

            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });


        // 数据按照Excel格式导出
        $("#butExcelExport").click(function () {
            common.dataExportExcel($("#condForm"));
        });

    },


    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "agentprofit/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var channelName = $("#channelName").val();
        var codeName = $("#codeName").val();
        var profitType = $("#profitType").val();
        var orderType = $("#orderType").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();

        var data = {
            "myTradeNo":myTradeNo,
            "channelName":channelName,
            "codeName":codeName,
            "profitType":profitType,
            "orderType":orderType,
            "curdayStart":curdayStart,
            "curdayEnd":curdayEnd
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            shtml += "      收益：";
            shtml += "<font color='red'>" + data.totalProfit + "</font>";
            $("#totalDiv").html(shtml);
        });
    },







}

$(function(){
    account.indexInit();
})
