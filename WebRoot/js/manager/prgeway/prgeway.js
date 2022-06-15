
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/prgeway/list.do',
        dataList_url : ctx + "/prgeway/dataList.do",
        add_url : ctx+ "/prgeway/add.do",
        update_url : ctx+ "/prgeway/update.do",
        queryId_url: ctx+ "/prgeway/getId.do",
        delete_url: ctx+ "/prgeway/delete.do",
        manyOperation_url: ctx+ "/prgeway/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        // {"data":"secretKey",},
        // {"data":"payId",},
        {"data":"attributeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.attributeType==1){
                    html='<span>代收</span>';
                }else if(oData.attributeType==2){
                    html='<span><font color="red">代付</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"totalMoney",},
        {"data":"leastMoney",},
        // {"data":"balance",},
        // {"data":"lockMoney",},
        // {"data":"gewayType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.gewayType==1){
        //             html='<span>普通通道</span>';
        //         }else if(oData.gewayType==2){
        //             html='<span>预付款通道</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==1){
                    html='<span><font color="red">禁用</font></span>';
                }else if(oData.isEnable==2){
                    html='<span>启用</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        // {"data":"create_time",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.dataType==1){
        //             html='<span>普通</span>';
        //         }else if(oData.dataType==2){
        //             html='<span>分割</span>';
        //         }else if(oData.dataType==3){
        //             html='<span>JSON</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/prgeway/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                html += '<a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        gewayName:null,
        secretKey:null,
        payId:null,
        attributeType:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/prgeway/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayName'] = $("#gewayName").val();
            account.condJsonData['secretKey'] = $("#secretKey").val();
            account.condJsonData['payId'] = $("#payId").val();
            account.condJsonData['attributeType'] = $("#attributeType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayName'] = "";
            account.condJsonData['secretKey'] = "";
            account.condJsonData['payId'] = "";
            account.condJsonData['attributeType'] = "0";

            account.condJsonData['gewayName'] = "";
            $("#gewayName").val("");
            account.condJsonData['secretKey'] = "";
            $("#secretKey").val("");
            account.condJsonData['payId'] = "";
            $("#payId").val("");
            account.condJsonData['attributeType'] = "0";
            $("#attributeType").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableDeleteBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //启用/禁用
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
        });
    }

}

$(function(){
    account.indexInit();
})
