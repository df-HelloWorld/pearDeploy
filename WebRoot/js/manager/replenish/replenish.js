
var datatable;
var basePath = $("#excDataHid").val();
var account = {
    //地址
    url:{
        list_url : ctx + '/replenish/list.do',
        dataList_url : ctx + "/replenish/dataList.do",
        // add_url : ctx+ "/replenish/add.do",
        // update_url : ctx+ "/replenish/update.do",
        queryId_url: ctx+ "/replenish/getId.do",
        // delete_url: ctx+ "/replenish/delete.do",
        manyOperation_url: ctx+ "/replenish/manyOperation.do",
        exportData_url : ctx +  "/replenish/exportData.do",
        check_url: ctx+ "/replenish/check.do"
    },


    //添加修改验证参数
    validate:{
        submitHandler : function() {
            var id = $("#show input[type='hidden']").val();
            var url = "";
            if(id){
                url = account.url.check_url;
            }

            var formData = $("#newFirstStoreForm").serialize();
            $.ajax({
                url : url,
                type : 'post',
                dataType : 'json',
                data :formData,
                success : function(data) {
                    if(data.success){
                        promptMessage ('保存成功！','success',true);
                        common.goList();
                    }else{
                        promptMessage(data.msg, 'warning', false);
                    }

                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
            return false;
            //阻止表单提交
        }
    },



    //列表显示参数
    list:[
        {"data":"myTradeNo",},
        {"data":"outTradeNo",},
        {"data":"channelName",},
        {"data":"codeName",},
        {"data":"gewayCodeName",},
        {"data":"gewayName",},
        {"data":"totalAmount",},
        {"data":"serviceCharge",},
        {"data":"actualMoney",},
        {"data":"pullOrderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.pullOrderStatus==1){
                    html='<span>成功</span>';
                }else if(oData.pullOrderStatus==2){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span><font color="red">放弃</font></span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"orderStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.orderStatus==1){
                    html='<span>初始化</span>';
                }else if(oData.orderStatus==2){
                    html='<span>超时</span>';
                }else if(oData.orderStatus==3){
                    // html='<span><font color="red">失败</font></span>';
                    html='<span>质疑</span>';
                }else if(oData.orderStatus==4){
                    html='<span>成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"pullOrderCodeType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.pullOrderCodeType==1){
                    html='<span>平台</span>';
                }else if(oData.pullOrderCodeType==2){
                    html='<span><font color="red">通道</font></span>';
                }
                $(nTd).html(html);
            }
        },

        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 补单 </a>'
                $(nTd).html(html);
            }
        }



    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        myTradeNo:null,
        outTradeNo:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);


        // common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['myTradeNo'] = $("#myTradeNo").val();
            account.condJsonData['outTradeNo'] = $("#outTradeNo").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['myTradeNo'] = "";
            $("#myTradeNo").val("");
            account.condJsonData['outTradeNo'] = "";
            $("#outTradeNo").val("");
            common.showDatas(account.condJsonData,account.list);
        });

        //补单
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/replenish/getId.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var m = data.data;
                        id = m.id;
                        common.addInit(account.validate);
                        $("#id").val(id);
                        $("#divMyTradeNo").val(m.myTradeNo);
                        $("#divOutTradeNo").val(m.outTradeNo);
                        $("#divChannelName").val(m.channelName);
                        $("#divTotalAmount").val(m.totalAmount);
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

    },



}


$(function(){
    account.indexInit();
})
