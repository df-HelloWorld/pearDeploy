
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/statisticsAgent/list.do',
        dataList_url : ctx + "/statisticsAgent/dataList.do",
        add_url : ctx+ "/statisticsAgent/add.do",
        update_url : ctx+ "/statisticsAgent/update.do",
        queryId_url: ctx+ "/statisticsAgent/getId.do",
        delete_url: ctx+ "/statisticsAgent/delete.do",
        manyOperation_url: ctx+ "/statisticsAgent/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"curday",},
        {"data":"agentName",},
        {"data":"totalMoney",},
        {"data":"balance",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.balance +'</font></span>';
                $(nTd).html(html);
            }
        },
        {"data":"totalAmount",},
        {"data":"profit",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.profit +'</font></span>';
                $(nTd).html(html);
            }
        },
        {"data":"addMoney",},
        {"data":"reduceMoney",},
        {"data":"withdrawIngMoney",},
        {"data":"withdrawSucMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.withdrawSucMoney +'</font></span>';
                $(nTd).html(html);
            }
        },
        {"data":"withdrawFailMoney",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<span><font color="red">'+ oData.withdrawFailMoney +'</font></span>';
                $(nTd).html(html);
            }
        }

    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        curday:0,
        agentId:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        // 查询条件 - 下拉框数据获取
        this.queryAgentAll();
        this.queryTotal();

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['curday'] = $("#curday").val();
            account.condJsonData['agentId'] = $("#agentId").val();
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['curday'] = "";
            $("#curday").val("");
            account.condJsonData['agentId'] = "0";
            $("#agentId").val("0");
            account.queryTotal();
            common.showDatas(account.condJsonData,account.list);
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



    //汇总数据填充
    //查询所有订单汇总数据
    queryTotal:function(){
        var url = basePath + "statisticsAgent/totalData.do";
        var curday = $("#curday").val();
        var agentId = $("#agentId").val();

        var data = {
            "curday":curday,
            "agentId":agentId
        };
        common.ajax(url,data,function(data){
            var data=data;
            var shtml="";
            shtml += "汇总：         总额：";
            shtml += "<font color='red'>" + data.totalTotalMoney + "</font>";
            shtml += "      余额：";
            shtml += "<font color='red'>" + data.totalBalance + "</font>";
            // shtml += "      锁定金额：";
            // shtml += "<font color='red'>" + data.totalLockMoney + "</font>";
            shtml += "      跑量金额：";
            shtml += "<font color='red'>" + data.totalTotalAmount + "</font>";
            shtml += "      收益：";
            shtml += "<font color='red'>" + data.totalProfit + "</font>";
            shtml += "      加金额：";
            shtml += "<font color='red'>" + data.totalAddMoney + "</font>";
            shtml += "      减金额：";
            shtml += "<font color='red'>" + data.totalReduceMoney + "</font>";
            shtml += "      提现中的金额：";
            shtml += "<font color='red'>" + data.totalWithdrawIngMoney + "</font>";
            shtml += "      提现成功金额：";
            shtml += "<font color='red'>" + data.totalWithdrawSucMoney + "</font>";
            shtml += "      提现失败金额：";
            shtml += "<font color='red'>" + data.totalWithdrawFailMoney + "</font>";
            $("#totalDiv").html(shtml);
        });
    },







}

$(function(){
    account.indexInit();
})
