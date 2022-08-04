
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminchannelflowingwater/list.do',
        dataList_url : ctx + "/adminchannelflowingwater/dataList.do",
        // add_url : ctx+ "/adminchannelflowingwater/add.do",
        // update_url : ctx+ "/adminchannelflowingwater/update.do",
        // queryId_url: ctx+ "/adminchannelflowingwater/getId.do",
        // delete_url: ctx+ "/adminchannelflowingwater/delete.do",
        manyOperation_url: ctx+ "/adminchannelflowingwater/manyOperation.do",
        exportData_url : ctx +  "/adminchannelflowingwater/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"codeName",},
        {"data":"gewayName",},
        {"data":"gewayCodeName",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"changeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.changeType==1){
                    html='<span>付款订单</span>';
                }else if(oData.changeType==2){
                    html='<span>提现</span>';
                }else if(oData.changeType==3){
                    html='<span>提现驳回</span>';
                }else if(oData.changeType==4){
                    html='<span>手动增加</span>';
                }else if(oData.changeType==5){
                    html='<span>手动减少</span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"oldMoney",},
        {"data":"changeMoney",},
        {"data":"newMoney",},
        {"data":"remark",},

        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null,
        channelId:0,
        pfGewayCodeId:0,
        gewayCodeId:0,
        gewayId:0,
        changeType:0,
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
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['pfGewayCodeId'] = $("#pfGewayCodeId").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
            account.condJsonData['gewayId'] = $("#gewayId").val();
            account.condJsonData['changeType'] = $("#changeType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
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
            account.condJsonData['changeType'] = "0";
            $("#changeType").val("0");
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





}


$(function(){
    account.indexInit();
})
