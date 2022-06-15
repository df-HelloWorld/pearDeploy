
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/prrelationtype/list.do',
        dataList_url : ctx + "/prrelationtype/dataList.do",
        add_url : ctx+ "/prrelationtype/add.do",
        update_url : ctx+ "/prrelationtype/update.do",
        queryId_url: ctx+ "/prrelationtype/getId.do",
        delete_url: ctx+ "/prrelationtype/delete.do",
        manyOperation_url: ctx+ "/prrelationtype/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"typeName",},
        {"data":"remark",},
        {"data":"codeName",},
        {"data":"gewayCodeNames",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if(oData.codeName==""||oData.codeName==null){
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/prrelationtype/jumpPtGewayCodeUpdate.do?id='+oData.id+'"> 绑定平台 </a>'
                        +' <a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "'+ctx+'/prrelationtype/jumpGewayCodeUpdate.do?id='+oData.id+'">绑定通道</a>'
                        +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                }else
                html = ''
                    +' <a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "'+ctx+'/prrelationtype/jumpGewayCodeUpdate.do?id='+oData.id+'">绑定通道</a>'
                +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        stgName:null,
        stgType:0,
        stgKey:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/prrelationtype/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['typeName'] = $("#typeName").val();
            // account.condJsonData['codeName'] = $("#codeName").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['typeName'] = "";
            // account.condJsonData['codeName'] = "";
            $("#typeName").val("");
            // $("#codeName").val("");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除
        $(".dataTableResetBtn").live("click",function(){
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
