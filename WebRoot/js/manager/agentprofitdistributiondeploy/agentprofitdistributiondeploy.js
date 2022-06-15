
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/agentprofitdistributiondeploy/list.do',
        dataList_url : ctx + "/agentprofitdistributiondeploy/dataList.do",
        add_url : ctx+ "/agentprofitdistributiondeploy/add.do",
        update_url : ctx+ "/agentprofitdistributiondeploy/update.do",
        queryId_url: ctx+ "/agentprofitdistributiondeploy/getId.do",
        delete_url: ctx+ "/agentprofitdistributiondeploy/delete.do",
        manyOperation_url: ctx+ "/agentprofitdistributiondeploy/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"typeName",},
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
        {"data":"channelName",},
        {"data":"gewayCodeNames",},
        {"data":"agentNameOrServiceCharge",},
        {"data":"remark",},
        // {"data":"gewayCodeName",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                    html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/agentprofitdistributiondeploy/jumpAddInfo.do?id='+oData.id+'"> 绑定关系 </a>'
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
            window.location.href = ctx + "/agentprofitdistributiondeploy/jumpAdd.do";
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
