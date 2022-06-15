
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminagentprofit/list.do',
        dataList_url : ctx + "/adminagentprofit/dataList.do",
        add_url : ctx+ "/adminagentprofit/add.do",
        update_url : ctx+ "/adminagentprofit/update.do",
        queryId_url: ctx+ "/adminagentprofit/getId.do",
        delete_url: ctx+ "/adminagentprofit/delete.do",
        manyOperation_url: ctx+ "/adminagentprofit/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"agentName",},
        {"data":"channelName",},
        {"data":"codeName",},
        {"data":"gewayCodeName",},
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
        agentId:0,
        channelId:0,
        pfGewayCodeId:0,
        gewayCodeId:0,
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
        this.queryAgentAll()
        this.queryChannelAll();
        this.queryPfGewayAll();
        this.queryGewayCodeAll();
        this.queryTotal();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['agentId'] = $("#agentId").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['pfGewayCodeId'] = $("#pfGewayCodeId").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
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
            account.condJsonData['agentId'] = "0";
            $("#agentId").val("0");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['pfGewayCodeId'] = "0";
            $("#pfGewayCodeId").val("0");
            account.condJsonData['gewayCodeId'] = "0";
            $("#gewayCodeId").val("0");
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

    },

    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryAgentAll:function(){
        var url = basePath + "adminagent/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].agentName+"</option>";
            }
            shtml +="</select>";
            $("#agentDiv").html(shtml);
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


    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "adminagentprofit/totalData.do";
        var myTradeNo = $("#myTradeNo").val();
        var agentId = $("#agentId").val();
        var channelId = $("#channelId").val();
        var pfGewayCodeId = $("#pfGewayCodeId").val();
        var gewayCodeId = $("#gewayCodeId").val();
        var profitType = $("#profitType").val();
        var orderType = $("#orderType").val();
        var curdayStart = $("#curdayStart").val();
        var curdayEnd = $("#curdayEnd").val();

        var data = {
            "myTradeNo":myTradeNo,
            "agentId":agentId,
            "channelId":channelId,
            "pfGewayCodeId":pfGewayCodeId,
            "gewayCodeId":gewayCodeId,
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
