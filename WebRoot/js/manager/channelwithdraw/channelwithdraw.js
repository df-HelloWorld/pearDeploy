
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channelwithdraw/list.do',
        dataList_url : ctx + "/channelwithdraw/dataList.do",
        add_url : ctx+ "/channelwithdraw/add.do",
        update_url : ctx+ "/channelwithdraw/update.do",
        queryId_url: ctx+ "/channelwithdraw/getId.do",
        delete_url: ctx+ "/channelwithdraw/delete.do",
        manyOperation_url: ctx+ "/channelwithdraw/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"channelName",},
        {"data":"money",},
        {"data":"serviceCharge",},
        {"data":"bankName",},
        {"data":"accountName",},
        {"data":"bankCard",},
        {"data":"subbranchName",},
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
        // {"data":"pictureAds",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html = '';
        //         if(oData.pictureAds!=""){
        //             html='<img src="'+oData.pictureAds+'"  style="width: 100px;height: 100px">';
        //             // html='<img id="'+oData.id+'" class="zoomify'+oData.id+'" onclick="imgShow(\''+oData.id+'\')" src="'+oData.pictureAds+'" alt="">';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"createTime",}
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

    },

    //下拉框数据填充
    //查询所有银行卡-无分页-下拉框选项:
    queryBankAll:function(){
        var url = basePath + "channelbank/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='channelBankId' name='channelBankId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+","+dataList[i].bankName+">==="+dataList[i].subbranchName+"==="+dataList[i].accountName+"</option>";
            }
            shtml +="</select>";
            $("#bankDiv").html(shtml);
        });
    }



}

//填充手续费
function countServiceCharge(){
    var money = $("#money").val();
    var balance = $("#balance").val();
    var serviceCharge;
    if (money != null && money.length > 0){
        if (money <= 20000){
            serviceCharge = 3;
        }
        if (money > 20000){
            serviceCharge = 5;
        }
        $("#serviceCharge").attr("value", serviceCharge);
        var totalMoney = money*1+serviceCharge*1;
        if (balance < totalMoney){
            alert("提现余额不足!");
        }
    }
}

// // 只允许提交一次
// function oneSubmit(){
//     document.forms[0].submit();
//     document.getElementById('submitid').disable='disable';
// }

$(function(){
    account.indexInit();
})
