
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/statisticsOften/list.do',
        dataList_url : ctx + "/statisticsOften/dataList.do",
        add_url : ctx+ "/statisticsOften/add.do",
        update_url : ctx+ "/statisticsOften/update.do",
        queryId_url: ctx+ "/statisticsOften/getId.do",
        delete_url: ctx+ "/statisticsOften/delete.do",
        manyOperation_url: ctx+ "/statisticsOften/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"gewayCodeName",},
        {"data":"orderMoney",},
        {"data":"sucOrderMoney",},
        // {"data":"sucOrderMoneyRatio",},

        {"data":"sucOrderMoneyRatio",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.sucOrderMoneyRatio +'</font></span>';
                $(nTd).html(html);
            }
        },

        {"data":"orderNum",},
        {"data":"sucOrderNum",},
        // {"data":"sucOrderNumRatio",}
        {"data":"sucOrderNumRatio",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.sucOrderNumRatio +'</font></span>';
                $(nTd).html(html);
            }
        },

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        startCreateTime:null,
        endCreateTime:null,
        channelId:0,
        gewayCodeId:0,
        isProfit:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 查询条件 - 下拉框数据获取
        this.queryChannelAll();
        this.queryGewayCodeAll();
        this.queryTotal();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['startCreateTime'] = $("#startCreateTime").val();
            account.condJsonData['endCreateTime'] = $("#endCreateTime").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
            account.condJsonData['isProfit'] = $("#isProfit").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['startCreateTime'] = "";
            $("#startCreateTime").val("");
            account.condJsonData['endCreateTime'] = "";
            $("#endCreateTime").val("");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['gewayCodeId'] = "0";
            $("#gewayCodeId").val("0");
            account.condJsonData['isProfit'] = "0";
            $("#isProfit").val("0");
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
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


    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "statisticsOften/totalData.do";
        var startCreateTime = $("#startCreateTime").val();
        var endCreateTime = $("#endCreateTime").val();
        var channelId = $("#channelId").val();
        var gewayCodeId = $("#gewayCodeId").val();
        var isProfit = $("#isProfit").val();

        var data = {
            "startCreateTime":startCreateTime,
            "endCreateTime":endCreateTime,
            "channelId":channelId,
            "gewayCodeId":gewayCodeId,
            "isProfit":isProfit
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         订单金额：";
            shtml += "<font color='red'>" + data.totalMoney + "</font>";
            shtml += "      成功订单金额：";
            shtml += "<font color='red'>" + data.totalSucMoney + "</font>";
            shtml += "      订单金额成功率：";
            shtml += "<font color='red'>" + data.totalMoneyRatio + "</font>";
            shtml += "      订单数：";
            shtml += "<font color='red'>" + data.totalNum + "</font>";
            shtml += "      成功订单数：";
            shtml += "<font color='red'>" + data.totalSucNum + "</font>";
            shtml += "      订单数成功率：";
            shtml += "<font color='red'>" + data.totalNumRatio + "</font>";
            $("#totalDiv").html(shtml);
        });
    },







}

$(function(){
    account.indexInit();
})
