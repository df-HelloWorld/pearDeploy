
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/prplatformgewaycode/list.do',
        dataList_url : ctx + "/prplatformgewaycode/dataList.do",
        add_url : ctx+ "/prplatformgewaycode/add.do",
        update_url : ctx+ "/prplatformgewaycode/update.do",
        queryId_url: ctx+ "/prplatformgewaycode/getId.do",
        delete_url: ctx+ "/prplatformgewaycode/delete.do",
        manyOperation_url: ctx+ "/prplatformgewaycode/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"codeName",},
        {"data":"pfGewayCode",},
        // {"data":"serviceChargeType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.serviceChargeType==1){
        //             html='<span>固定费率</span>';
        //         }else if(oData.serviceChargeType==2){
        //             html='<span>额外费率</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"serviceCharge",},
        // {"data":"extraServiceCharge",},
        // {"data":"pfGewayCodeType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.pfGewayCodeType==1){
        //             html='<span>代收</span>';
        //         }else if(oData.pfGewayCodeType==2){
        //             html='<span><font color="red">代付</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"moneyRange",},
        {"data":"jumpAds",},
        {"data":"redisTime",},
        {"data":"gewayNameStr",},
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
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/prrelationtype/jumpGewayCodeUpdate.do?id='+oData.id+'"> 绑定通道 </a>';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/prplatformgewaycode/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                // html += '<a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);

            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        codeName:null,
        pfGewayCode:null,
        isJump:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/prplatformgewaycode/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['codeName'] = $("#codeName").val();
            account.condJsonData['pfGewayCode'] = $("#pfGewayCode").val();
            account.condJsonData['isJump'] = $("#isJump").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['codeName'] = "";
            account.condJsonData['pfGewayCode'] = "";
            account.condJsonData['isJump'] = "0";
            $("#codeName").val("");
            $("#pfGewayCode").val("");
            $("#isJump").val("0");
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
