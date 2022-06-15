
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/prgewaycode/list.do',
        dataList_url : ctx + "/prgewaycode/dataList.do",
        add_url : ctx+ "/prgewaycode/add.do",
        update_url : ctx+ "/prgewaycode/update.do",
        queryId_url: ctx+ "/prgewaycode/getId.do",
        delete_url: ctx+ "/prgewaycode/delete.do",
        manyOperation_url: ctx+ "/prgewaycode/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"gewayCodeName",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<a id = "gewayCodeInfo" directkey="' + oData.id + '" directValue="' + oData.gewayCodeName + '" href = "javascript:void(0);"> '+oData.gewayCodeName+ '</a>'
                $(nTd).html(html);
            }
        },
        {"data":"gewayCode",},
        {"data":"myGewayCode",},
        // {"data":"upServiceChargeType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.upServiceChargeType==1){
        //             html='<span>固定费率</span>';
        //         }else if(oData.upServiceChargeType==2){
        //             html='<span>额外费率</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"upServiceCharge",},
        {"data":"upExtraServiceCharge",},
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
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/prgewaycode/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
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
        gewayId:0,
        gewayCodeName:null,
        gewayCode:null,
        myGewayCode:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/prgewaycode/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayId'] = $("#gewayId").val();
            account.condJsonData['gewayCodeName'] = $("#gewayCodeName").val();
            account.condJsonData['gewayCode'] = $("#gewayCode").val();
            account.condJsonData['myGewayCode'] = $("#myGewayCode").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayId'] = "0";
            account.condJsonData['gewayCodeName'] = "";
            account.condJsonData['gewayCode'] = "";
            account.condJsonData['myGewayCode'] = "";
            $("#gewayId").val("0");
            $("#gewayCodeName").val("");
            $("#gewayCode").val("");
            $("#myGewayCode").val("");
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

        //通道码详情
        $("#gewayCodeInfo").live("click",function(){
            var id = $(this).attr('directkey');
            var gewayCodeName = $(this).attr('directValue');
            $("#divGewayCodeName").html(gewayCodeName);

            $.ajax({url : ctx+ "/prgewaycode/getById.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        $("#divGewayName").val(m.gewayName);
                        $("#divGewayCodeName").val(m.gewayCodeName);
                        $("#divMyGewayCode").val(m.myGewayCode);
                        $("#divGewayCode").val(m.gewayCode);
                        $("#divInterfaceAds").val(m.interfaceAds);
                        $("#divNotifyUrl").val(m.notifyUrl);
                        $("#divSendTag").val(m.sendTag);
                        $("#divSucTag").val(m.sucTag);
                        $("#divBigTotalMoney").val(m.bigTotalMoney);
                        $("#divTotalMoney").val(m.totalMoney);
                        $("#divBigTadayMoney").val(m.bigTadayMoney);
                        $("#divTadayMoney").val(m.tadayMoney);
                        if (m.upServiceChargeType == 1){
                            $("#divUpServiceChargeType").val("固定费率");
                        }else {
                            $("#divUpServiceChargeType").val("额外费率");
                        }
                        $("#divUpServiceCharge").val(m.upServiceCharge);
                        $("#divUpExtraServiceCharge").val(m.upExtraServiceCharge);
                        if (m.serviceChargeType == 1){
                            $("#divServiceChargeType").val("固定费率");
                        }else {
                            $("#divServiceChargeType").val("额外费率");
                        }
                        $("#divServiceCharge").val(m.serviceCharge);
                        $("#divExtraServiceCharge").val(m.extraServiceCharge);

                        if (m.moneyType == 1){
                            $("#divMoneyType").val("固定的");
                        }else if (m.moneyType == 2) {
                            $("#divMoneyType").val("单一范围");
                        }else if (m.moneyType == 3) {
                            $("#divMoneyType").val("多个范围");
                        }
                        $("#divMoneyRange").val(m.moneyRange);
                        $("#divOpenTime").val(m.openTime);
                        $("#divDayLimitMoney").val(m.dayLimitMoney);
                        if (m.codeAttributeType == 1){
                            $("#divCodeAttributeType").val("全类型");
                        }else if (m.codeAttributeType == 2) {
                            $("#divCodeAttributeType").val("Android");
                        }else if (m.codeAttributeType == 3) {
                            $("#divCodeAttributeType").val("IOS");
                        }
                        $("#divWhiteListIp").val(m.whiteListIp);
                        $("#divIdentityKey").val(m.identityKey);
                        $("#divSendIdentity").val(m.sendIdentity);
                        $("#divCreateTime").val(m.createTime);

                        openDialog("show","");
                    } else {
                        art.alert(data.msg);
                    }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });
        // end




    }

}

function queryGewayAll(){
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
        $("#divGewayId").html(shtml);
    });
}

$(function(){
    account.indexInit();
    queryGewayAll();
})
