
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/frequently/list.do',
        dataList_url : ctx + "/frequently/dataList.do",
        add_url : ctx+ "/frequently/add.do",
        update_url : ctx+ "/frequently/update.do",
        queryId_url: ctx+ "/frequently/getId.do",
        delete_url: ctx+ "/frequently/delete.do",
        manyOperation_url: ctx+ "/frequently/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"channelName",},
        {"data":"clientIp",},
        {"data":"lockRedisKey",},
        {"data":"lockTime",},
        {"data":"lockType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.lockType==1){
                    html='<span><font color="red">锁IP</font></span>';
                }else if(oData.lockType==2){
                    html='<span><font color="green">锁渠道</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"fromInterface",},
        {"data":"isLock",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isLock==1){
                    html='<span><font color="red">已锁</font></span>';
                }else if(oData.isLock==2){
                    html='<span><font color="green">已解</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.isLock == 1 && oData.invalidStatus == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 解锁 </a>';
                }else{
                    html += '正常';
                }
                // if (oData.isLock == 1 && oData.invalidStatus == 1){
                //     if(oData.invalidStatus == 1){
                //         html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 解锁 </a>';
                //     }else{
                //         html += '正常';
                //     }
                // }else {
                //     html += '正常';
                // }
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        channelId:0,
        clientIp:null,
        lockType:0,
        isLock:0,
        invalidStatus:0,
        curdayStart:0,
        curdayEnd:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryChannelAll();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['clientIp'] = $("#clientIp").val();
            account.condJsonData['lockType'] = $("#lockType").val();
            account.condJsonData['isLock'] = $("#isLock").val();
            account.condJsonData['invalidStatus'] = $("#invalidStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['clientIp'] = "";
            $("#clientIp").val("");
            account.condJsonData['lockType'] = "0";
            $("#lockType").val("0");
            account.condJsonData['isLock'] = "0";
            $("#isLock").val("0");
            account.condJsonData['invalidStatus'] = "0";
            $("#invalidStatus").val("0");
            account.condJsonData['curdayStart'] = "";
            $("#curdayStart").val("");
            account.condJsonData['curdayEnd'] = "";
            $("#curdayEnd").val("");

            common.showDatas(account.condJsonData,account.list);
        });

        //启用/暂停
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isLock = $(this).attr('directValue');
            var data = {
                id:id,
                isLock:isLock
            }
            common.unlock(data);
        });

    },

    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryChannelAll:function(){
        var url = basePath + "adminchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
        });
    },


}

$(function(){
    account.indexInit();
})
