
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminagent/list.do',
        dataList_url : ctx + "/adminagent/dataList.do",
        add_url : ctx+ "/adminagent/add.do",
        update_url : ctx+ "/adminagent/update.do",
        queryId_url: ctx+ "/adminagent/getId.do",
        delete_url: ctx+ "/adminagent/delete.do",
        manyOperation_url: ctx+ "/adminagent/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        // {"data":"roleName",},
        {"data":"agentName",},
        {"data":"totalMoney",},
        {"data":"balance",},
        {"data":"lockMoney",},
        // {"data":"withdrawType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.withdrawType==1){
        //             html='<span>平台内</span>';
        //         }else if(oData.withdrawType==2){
        //             html='<span><font color="red">平台外</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"agentType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.agentType==1){
        //             html='<span>针对渠道</span>';
        //         }else if(oData.agentType==2){
        //             html='<span>针对通道</span>';
        //         }else if(oData.agentType==3){
        //             html='<span><font color="red">两者针对</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"agentTypeName",},
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
                // var html = '';
                // var isEnableHtml = '';
                // html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/accountagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                //     +'<a class = "dataTableBtn" href="'+ctx+'/accountagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                //     +isEnableHtml
                //     +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/adminagent/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>';
                html += '<a class = "dataTableBtn" href="'+ctx+'/adminagent/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>';
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
        accountNum:null,
        agentType:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);

        this.queryAgentType();// 获取策略中代理类型

        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminagent/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['accountNum'] = $("#accountNum").val();
            account.condJsonData['agentType'] = $("#agentType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['accountNum'] = "";
            $("#accountNum").val("");
            account.condJsonData['agentType'] = "0";
            $("#agentType").val("0");
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
    },

    //下拉框数据填充
    //查询所有代理类型-无分页-下拉框选项:
    queryAgentType:function(){
        var url = basePath + "strategy/dataJsonList.do";
        var data = {
            "stgType":18
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='agentType' name='agentType'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].stgValueTwo+">"+dataList[i].stgValueOne+"</option>";
            }
            shtml +="</select>";
            $("#agentTypeDiv").html(shtml);
        });
    }




}

$(function(){
    account.indexInit();
})
