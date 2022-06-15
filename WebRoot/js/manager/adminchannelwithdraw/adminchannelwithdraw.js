
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/adminchannelwithdraw/list.do',
        dataList_url : ctx + "/adminchannelwithdraw/dataList.do",
        add_url : ctx+ "/adminchannelwithdraw/add.do",
        update_url : ctx+ "/adminchannelwithdraw/update.do",
        queryId_url: ctx+ "/adminchannelwithdraw/getId.do",
        delete_url: ctx+ "/adminchannelwithdraw/delete.do",
        manyOperation_url: ctx+ "/adminchannelwithdraw/manyOperation.do",
        check_url: ctx+ "/adminchannelwithdraw/check.do"
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
        {"data":"channelName",},
        {"data":"money",},
        {"data":"serviceCharge",},
        {"data":"bankName",},
        {"data":"accountName",},
        {"data":"bankCard",},
        {"data":"remark",},
        {"data":"withdrawStatus",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.withdrawStatus==1){
                    html='<span>提现中</span>';
                }else if(oData.withdrawStatus==2){
                    html='<span><font color="red">提现驳回</font></span>';
                }else if(oData.withdrawStatus==3){
                    html='<span>提现成功</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                if (oData.withdrawStatus == 1){
                    html += '<a class = "dataTableBtn dataTableDeleteBtn" id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 未审核 </a>'
                }else {
                    html += '已审核';
                }

                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        channelName:null,
        accountName:null,
        bankCard:null,
        withdrawStatus:0,
        curdayStart:0,
        curdayEnd:0

    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/channelwithdraw/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['channelName'] = $("#channelName").val();
            account.condJsonData['accountName'] = $("#accountName").val();
            account.condJsonData['bankCard'] = $("#bankCard").val();
            account.condJsonData['withdrawStatus'] = $("#withdrawStatus").val();
            account.condJsonData['curdayStart'] = $("#curdayStart").val();
            account.condJsonData['curdayEnd'] = $("#curdayEnd").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['channelName'] = "";
            $("#channelName").val("");
            account.condJsonData['accountName'] = "";
            $("#accountName").val("");
            account.condJsonData['bankCard'] = "";
            $("#bankCard").val("");
            account.condJsonData['withdrawStatus'] = "0";
            $("#withdrawStatus").val("0");
            account.condJsonData['curdayStart'] = "0";
            $("#curdayStart").val("0");
            account.condJsonData['curdayEnd'] = "0";
            $("#curdayEnd").val("0");
            common.showDatas(account.condJsonData,account.list);
        });


        //审核
        $("#edit").live("click",function(){
            var id = $(this).attr('directkey');
            $.ajax({url : ctx+ "/adminchannelwithdraw/getId.do",
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
                        $("#divOrderNo").val(m.orderNo);
                        $("#divChannelName").val(m.channelName);
                        $("#divMoney").val(m.money);
                        $("#divServiceCharge").val(m.serviceCharge);
                        $("#divBankName").val(m.bankName);
                        $("#divAccountName").val(m.accountName);
                        $("#divBankCard").val(m.bankCard);
                        $("#remark").val(m.remark);
                        $("#withdrawExplain").val(m.withdrawExplain);

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


// function appendOption(){
//     var html='<option value="0">==请选择==</option>';
//     html+='<option value="1">提现中</option>';
//     html+='<option value="2">提现驳回</option>';
//     html+='<option value="3">提现成功</option>';
//     $(selectId).html(html);
//
//
// }
