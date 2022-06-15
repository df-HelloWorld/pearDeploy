
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/gewaybalancewithdraw/list.do',
        dataList_url : ctx + "/gewaybalancewithdraw/dataList.do",
        add_url : ctx+ "/gewaybalancewithdraw/add.do",
        update_url : ctx+ "/gewaybalancewithdraw/update.do",
        queryId_url: ctx+ "/gewaybalancewithdraw/getId.do",
        delete_url: ctx+ "/gewaybalancewithdraw/delete.do",
        manyOperation_url: ctx+ "/gewaybalancewithdraw/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"withdrawType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawType==1){
                    html='<span>渠道提现</span>';
                }else if(oData.withdrawType==2){
                    html='<span>pin信息</span>';
                }else if(oData.withdrawType==3){
                    html='<span>添加金额</span>';
                }else if(oData.withdrawType==4){
                    html='<span>核减金额</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"channelName",},
        {"data":"money",},
        {"data":"countType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.countType==1){
                    html='<span>初始化</span>';
                }else if(oData.countType==2){
                    html='<span style="color: #bb0000">成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"remark",},
        {"data":"createTime",}
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
        account.queryGewayAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/gewaybalancewithdraw/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayId'] = $("#gewayId").val();
            // account.condJsonData['codeName'] = $("#codeName").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayId'] = "";
            $("#gewayId").val("");
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
    },
    queryGewayAll:function(){
        var url = ctx + "/prgeway/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayId' name='gewayId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayName+"</option>";
            }
            shtml +="</select>";
            $("#gewayDiv").html(shtml);
        });
    },

}

$(function(){
    account.indexInit();
})
