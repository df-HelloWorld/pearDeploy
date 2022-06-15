
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/agentprofitdistribution/list.do',
        dataList_url : ctx + "/agentprofitdistribution/dataList.do",
        add_url : ctx+ "/agentprofitdistribution/add.do",
        update_url : ctx+ "/agentprofitdistribution/update.do",
        queryId_url: ctx+ "/agentprofitdistribution/getId.do",
        delete_url: ctx+ "/agentprofitdistribution/delete.do",
        manyOperation_url: ctx+ "/agentprofitdistribution/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"agentName",},
        {"data":"agentType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.agentType==1){
                    html='<span><font color="red">xx通道</font></span>';
                }else if(oData.agentType==2){
                    html='<span>'+oData.gewayCodeName+'</span>';
                }else if(oData.agentType==3){
                    html='<span>'+oData.gewayCodeName+'</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"agentType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.agentType==1){
                    html='<span>'+oData.channelName+'</span>';
                }else if(oData.agentType==2){
                    html='<span><font color="red">xx渠道</font></span>';
                }else if(oData.agentType==3){
                    html='<span>'+oData.channelName+'</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"bindingType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.bindingType==1){
                    html='<span>通道码绑定</span>';
                }else if(oData.bindingType==2){
                    html='<span>渠道绑定</span>';
                }else if(oData.bindingType==3){
                    html='<span><font color="red">两者绑定</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"serviceChargeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.serviceChargeType==1){
                    html='<span>固定费率</span>';
                }else if(oData.serviceChargeType==2){
                    html='<span><font color="red">额外费率</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"serviceCharge",},
        {"data":"extraServiceCharge",},
        {"data":"createTime",}
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            common.showDatas(account.condJsonData,account.list);
        });


    }


}

$(function(){
    account.indexInit();
})
