
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channel/list.do',
        dataList_url : ctx + "/channel/dataList.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        {"data":"channelName",},
        {"data":"channel",},
        {"data":"totalMoney",},
        {"data":"balance",},
        {"data":"lockMoney",},
        {"data":"secretKey",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html +='<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channelwithdraw/jumpAdd.do"> 提现 </a>'
                html +='<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/channel/jumpUpdate.do"> 重置密码 </a>'
                $(nTd).html(html);
            }
        }


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



    }

}

$(function(){
    account.indexInit();
})
