
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelflowingwater/list.do',
        dataList_url : ctx + "/channelflowingwater/dataList.do",
        // add_url : ctx+ "/channelflowingwater/add.do",
        // update_url : ctx+ "/channelflowingwater/update.do",
        // queryId_url: ctx+ "/channelflowingwater/getId.do",
        // delete_url: ctx+ "/channelflowingwater/delete.do",
        manyOperation_url: ctx+ "/channelflowingwater/manyOperation.do",
        exportData_url : ctx +  "/channelflowingwater/exportData.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"codeName",},
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
        changeType:0,
        curdayStart:0,
        curdayEnd:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
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





}


$(function(){
    account.indexInit();
})
