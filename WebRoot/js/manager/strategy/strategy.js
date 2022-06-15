
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/strategy/list.do',
        dataList_url : ctx + "/strategy/dataList.do",
        add_url : ctx+ "/strategy/add.do",
        update_url : ctx+ "/strategy/update.do",
        queryId_url: ctx+ "/strategy/getId.do",
        delete_url: ctx+ "/strategy/delete.do",
        manyOperation_url: ctx+ "/strategy/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"stgName",},
        {"data":"stgType",},
        {"data":"stgKey",},
        {"data":"stgNumValue",},
        {"data":"stgValue",},
        {"data":"dataType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.dataType==1){
                    html='<span>普通</span>';
                }else if(oData.dataType==2){
                    html='<span>分割</span>';
                }else if(oData.dataType==3){
                    html='<span>JSON</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/strategy/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
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
            window.location.href = ctx + "/strategy/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['stgName'] = $("#stgName").val();
            account.condJsonData['stgType'] = $("#stgType").val();
            account.condJsonData['stgKey'] = $("#stgKey").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['stgName'] = "";
            $("#stgName").val("");
            account.condJsonData['condJsonData'] = "0";
            $("#condJsonData").val("0");
            account.condJsonData['stgKey'] = "";
            $("#stgKey").val("");
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
