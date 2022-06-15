
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/channelplatformgeway/list.do',
        dataList_url : ctx + "/channelplatformgeway/dataList.do"
        // add_url : ctx+ "/channelplatformgeway/add.do",
        // update_url : ctx+ "/channelplatformgeway/update.do",
        // queryId_url: ctx+ "/channelplatformgeway/getId.do",
        // delete_url: ctx+ "/channelplatformgeway/delete.do",
        // manyOperation_url: ctx+ "/channelplatformgeway/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"codeName",},
        {"data":"pfGewayCode",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.pfGewayCode +'</font></span>';
                $(nTd).html(html);
            }
        },
        {"data":"serviceCharge",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.serviceCharge +'</font></span>';
                $(nTd).html(html);
            }
        },
        // {"data":"extraServiceCharge",},
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==1){
                    html='<span><font color="red">关闭</font></span>';
                }else if(oData.isEnable==2){
                    html='<span>开启</span>';
                }
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
