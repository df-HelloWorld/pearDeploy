
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/adminagentprofitdistribution/list.do',
        dataList_url : ctx + "/adminagentprofitdistribution/dataList.do",
        add_url : ctx+ "/adminagentprofitdistributionnew/add.do",
        update_url : ctx+ "/adminagentprofitdistributionnew/update.do",
        queryId_url: ctx+ "/adminagentprofitdistributionnew/getId.do",
        delete_url: ctx+ "/adminagentprofitdistributionnew/delete.do",
        manyOperation_url: ctx+ "/adminagentprofitdistributionnew/manyOperation.do"
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
        this.queryDistributionAll();


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
        var gewayCodeIdValue=$("#gewayCodeIds").val();
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayCodeId' name='gewayCodeId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length;i++) {
                if(gewayCodeIdValue==dataList[i].id){
                    shtml +="<option value="+dataList[i].id+" selected>"+dataList[i].gewayCodeName+"==="+dataList[i].myGewayCode+"</option>";
                }else{
                    shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayCodeName+"==="+dataList[i].myGewayCode+"</option>";
                }

            }
            shtml +="</select>";
            $("#gewayCodeDiv").html(shtml);

            var  bindingType  = $("#bindingType").val();

            if(bindingType==2){
                $("#gewayCodeId").attr("disabled","disabled");
            }
        });
    },

    queryDistributionAll:function(){
        var deployId=$("#deployId").val();
        var channelId=$("#channeIdValue").val();
        var url = basePath + "adminagentprofitdistributionnew/dataAllList.do";
        var data = {
            deployId:deployId,
            channelId:channelId

        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml +="<table border='1' width='100%'>";
                shtml +="<tr>";
                    shtml +="<td>渠道</td>";
                    shtml +="<td>代理</td>";
                    shtml +="<td>通道码名</td>";
                    shtml +="<td>绑定类型</td>";
                    shtml +="<td>利益类型</td>";
                    shtml +="<td>固定费率</td>";
                    shtml +="<td>额外费率</td>";
                    shtml +="<td>使用状态</td>";
                    shtml +="<td>操作</td>";
                shtml +="</tr>";
                for(var i=0;i<dataList.length;i++){
                    shtml +="<tr>";
                    shtml +="<td>"+dataList[i].channelName+"</td>";
                    if(dataList[i].agentName=="null"||dataList[i].agentName==null){
                        shtml +="<td></td>";
                    }else{
                        shtml +="<td>"+dataList[i].agentName+"</td>";
                    }
                    if(dataList[i].gewayCodeName=="null"||dataList[i].gewayCodeName==null){
                        shtml +="<td></td>";
                    }else{
                        shtml +="<td>"+dataList[i].gewayCodeName+"</td>";
                    }
                    if(dataList[i].bindingType==1){
                        shtml +="<td>通道码绑定</td>";
                    }else if(dataList[i].bindingType==2){
                        shtml +="<td>渠道绑定</td>";
                    }else if(dataList[i].bindingType==3){
                        shtml +="<td>两者绑定</td>";
                    }

                    if(dataList[i].serviceChargeType==1){
                        shtml +="<td>固定费率</td>";
                    }else if(dataList[i].serviceChargeType==2){
                        shtml +="<td>额外费率</td>";
                    }
                    shtml +="<td>"+dataList[i].serviceCharge+"</td>";
                    shtml +="<td>"+dataList[i].extraServiceCharge+"</td>";
                    if(dataList[i].isEnable==2){
                        shtml +="<td><span style='color: #1094fa'>开启</span></td>";
                    }else{
                        shtml +="<td><span style='color:red'>关闭</span></td>";
                    }

                    shtml +="<td>" ;
                          if(dataList[i].isEnable==2){
                              shtml += "<a class = \"dataTableBtn  \" onclick='updateType("+dataList[i].id+",1)'> 关闭 </a>" ;
                          }else{
                              shtml +="<a class = \"dataTableBtn  \" onclick='updateType("+dataList[i].id+",2)'> 开启 </a>";
                          }
                          shtml+=     "<a class = \"dataTableBtn dataTableResetBtn\" onclick='deteleInfo("+dataList[i].id+")' >删除 </a>" ;
                          shtml+=  "</td>";
                    shtml +="</tr>";
                }

            shtml +="</table>";
            $("#tabList").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有渠道-无分页-下拉框选项:
    queryChannelAll:function(){
        var channeIdValue=$("#channelIds").val();
        var url = basePath + "adminchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";

            shtml += "<select id='channelId' name='channelId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                if(channeIdValue==dataList[i].id){
                    shtml +="<option value="+dataList[i].id+" selected >"+dataList[i].channelName+"</option>";
                }else{
                    shtml +="<option value="+dataList[i].id+">"+dataList[i].channelName+"</option>";
                }
            }
            shtml +="</select>";
            $("#channelDiv").html(shtml);
            var  bindingType  = $("#bindingType").val();

            if(bindingType==1){
                $("#channelId").attr("disabled","disabled");
            }else if(bindingType==2){
                $("#gewayCodeId").attr("disabled","disabled");
            }
        });
    }
}

$(function(){
    account.indexInit();
})


function  updateType(id,isEnable){
    var deployId=$("#deployId").val();
    var url = basePath + "adminagentprofitdistribution/manyOperation.do";
    var data = {
        isEnable:isEnable,
        id:id
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        if(dataList.success==true){
            window.location.href = basePath + "agentprofitdistributiondeploy/jumpAddInfo.do?id="+deployId;
        }else{
            alert(dataList.msg);
        }
    });
}


function  deteleInfo(id){
    var deployId=$("#deployId").val();
    var url = basePath + "adminagentprofitdistributionnew/delete.do";
    var data = {
        id:id
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        if(dataList.success==true){
            window.location.href = basePath + "agentprofitdistributiondeploy/jumpAddInfo.do?id="+deployId;
        }else{
            alert(dataList.msg);
        }
    });
}

function  add(){
    var deployId=$("#deployId").val();
    var channelId=$("#channelId").val();
    var agentId=$("#agentId").val();
    var gewayCodeId=$("#gewayCodeId").val();
    var bindingType=$("#bindingType").val();
    var serviceChargeType=$("#serviceChargeType").val();
    var serviceCharge=$("#serviceCharge").val();
    var extraServiceCharge=$("#extraServiceCharge").val();
    var url = basePath + "adminagentprofitdistributionnew/add.do";
    var data = {
        deployId:deployId,
        channelId:channelId,
        agentId:agentId,
        gewayCodeId:gewayCodeId,
        bindingType:bindingType,
        serviceChargeType:serviceChargeType,
        serviceCharge:serviceCharge,
        extraServiceCharge:extraServiceCharge
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        if(dataList.success==true){
            window.location.href = basePath + "agentprofitdistributiondeploy/jumpAddInfo.do?id="+deployId;
        }else{
            alert(dataList.msg);
        }
    });
}




