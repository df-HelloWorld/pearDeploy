
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminagentprofitdistribution/list.do',
        dataList_url : ctx + "/adminagentprofitdistribution/dataList.do",
        add_url : ctx+ "/adminagentprofitdistribution/add.do",
        update_url : ctx+ "/adminagentprofitdistribution/update.do",
        queryId_url: ctx+ "/adminagentprofitdistribution/getId.do",
        delete_url: ctx+ "/adminagentprofitdistribution/delete.do",
        manyOperation_url: ctx+ "/adminagentprofitdistribution/manyOperation.do"
    },
    //列表显示参数
    list:[
        // {"data":"isEnable",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.isEnable==1){
        //             html='<span><font color="red">xx</font></span>';
        //         }else if(oData.isEnable==2){
        //             html='<span>'+oData.alias+'</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },

        {"data":"alias",},
        {"data":"agentName",},
        {"data":"gewayCodeName",},
        {"data":"channelName",},
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
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/adminagentprofitdistribution/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                html += ' <a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        alias:null,
        agentId:0,
        gewayCodeId:0,
        channelId:0,
        bindingType:0,
        serviceChargeType:0,
        isEnable:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        // 查询条件 - 下拉框数据获取
        this.queryAgentAll()
        this.queryGewayCodeAll();
        this.queryChannelAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminagentprofitdistribution/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['alias'] = $("#alias").val();
            account.condJsonData['agentId'] = $("#agentId").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
            account.condJsonData['channelId'] = $("#channelId").val();
            account.condJsonData['bindingType'] = $("#bindingType").val();
            account.condJsonData['serviceChargeType'] = $("#serviceChargeType").val();
            account.condJsonData['isEnable'] = $("#isEnable").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['alias'] = "";
            $("#alias").val("");
            account.condJsonData['agentId'] = "0";
            $("#agentId").val("0");
            account.condJsonData['gewayCodeId'] = "0";
            $("#gewayCodeId").val("0");
            account.condJsonData['channelId'] = "0";
            $("#channelId").val("0");
            account.condJsonData['bindingType'] = "0";
            $("#bindingType").val("0");
            account.condJsonData['serviceChargeType'] = "0";
            $("#serviceChargeType").val("0");
            account.condJsonData['isEnable'] = "0";
            $("#isEnable").val("0");
            common.showDatas(account.condJsonData,account.list);
        });

        //启用/暂停
        $(".dataTableEnableBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var isEnable = $(this).attr('directValue');
            var data = {
                id:id,
                isEnable:isEnable
            }
            common.manyOperation(data);
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

    },

    //下拉框数据填充
    //查询所有代理-无分页-下拉框选项:
    queryAgentAll:function(){
        var url = basePath + "adminagent/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentId' name='agentId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].agentName+"</option>";
            }
            shtml +="</select>";
            $("#agentDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有通道码-无分页-下拉框选项:
    queryGewayCodeAll:function(){
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayCodeId' name='gewayCodeId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayCodeName+"==="+dataList[i].myGewayCode+"</option>";
            }
            shtml +="</select>";
            $("#gewayCodeDiv").html(shtml);
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
    }



}

$(function(){
    account.indexInit();
})
