
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelchange/list.do',
        dataList_url : ctx + "/channelchange/dataList.do"
    },
    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"alias",},
        {"data":"channelName",},
        {"data":"money",},
        {"data":"changeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.changeType==1){
                    html +='<span><font color="red">核减金额</font></span>';
                }else if(oData.changeType==2){
                    html+="<span >充值金额</span>"
                }else if(oData.changeType==0){
                    html+="<span >初始化</span>"
                }
                $(nTd).html(html);
            }
        },
        {"data":"dataExplain",},
        {"data":"createTime",}

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        channelName:null,
        money:null,
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
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['money'] = $("#money").val();
            account.condJsonData['changeType'] = $("#changeType").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);

        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            account.condJsonData['channelName'] = "";
            account.condJsonData['money'] = "";
            account.condJsonData['changeType'] = "";
            account.condJsonData['curdayStart'] = "";
            account.condJsonData['curdayEnd'] = "";
            $("#myTradeNo").val("");
            $("#channelName").val("");
            $("#money").val("");
            $("#changeType").val("0");
            $("#curdayStart").val("0");
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });


    },





}

$(function(){
    account.indexInit();
})
