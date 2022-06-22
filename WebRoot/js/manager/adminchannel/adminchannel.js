
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/adminchannel/list.do',
        dataList_url : ctx + "/adminchannel/dataList.do",
        add_url : ctx+ "/adminchannel/add.do",
        update_url : ctx+ "/adminchannel/update.do",
        queryId_url: ctx+ "/adminchannel/getId.do",
        delete_url: ctx+ "/adminchannel/delete.do",
        manyOperation_url: ctx+ "/adminchannel/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"accountNum",},
        // {"data":"roleName",},
        {"data":"channelName",},
        {"data":"channel",},
        {"data":"totalMoney",},
        {"data":"balance",},
        {"data":"lockMoney",},
        {"data":"secretKey",},
        {"data":"googleKey",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isGoogle==1){
                    html='<span></span>';
                }else if(oData.isGoogle==2){
                    html=oData.googleKey;
                }
                $(nTd).html(html);
            }
        },
        // {"data":"channelType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.channelType==1){
        //             html='<span>代收</span>';
        //         }else if(oData.channelType==2){
        //             html='<span>代付</span>';
        //         }else if(oData.channelType==3){
        //             html='<span>其它</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        // {"data":"isSynchro",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.isSynchro==1){
        //             html='<span>要同步</span>';
        //         }else if(oData.isSynchro==2){
        //             html='<span>不同步</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                var isEnableHtml = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 费率 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminchannel/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn" href="'+ctx+'/adminchannel/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                    +isEnableHtml
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        accountNum:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/adminchannel/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['accountNum'] = $("#accountNum").val();
            account.condJsonData['channelType'] = $("#channelType").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['accountNum'] = "";
            $("#accountNum").val("");
            account.condJsonData['channelType'] = "0";
            $("#channelType").val("0");
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

        //审核
        $("#edit").live("click",function(){
            var channelId = $(this).attr('directkey');
            $.ajax({url : ctx+ "/channelplatformgewaycodelink/dataAllList.do",
                type : 'post',
                dataType : 'json',
                data :{
                    channelId:channelId
                },
                success : function(data) {
                    var table ="";
                    table+="<table    class='datatable tables'>"
                    table+="<tr>";
                    table+="<td>渠道名称</td>";
                    table+="<td>平台码名称</td>";
                    table+="<td>费率</td>";
                    table+="<td>操作</td>";
                    table+="</tr>";
                    for(var i=0;i<data.length;i++){
                        table+="<tr>";
                        table+="<td>"+data[i].channelName+"</td>";
                        table+="<td>"+data[i].codeName+"</td>";
                        // var  serviceChargeId="serviceCharge"+i;
                        table+="<td><input type='text' id='"+data[i].id+"'  value="+data[i].serviceCharge+"></td>";
                        table+="<td> <input type = 'button' id = 'saveServiceCharge' directkey="+ data[i].id +"  href = 'javascript:void(0);' class = 'buttonClass imginput'value = '保存' /></td>";
                        table+="</tr>";
                    }
                    table+="</table>";

                    document.getElementById("divTable").innerHTML=table;
                    // $("#divTable").innerText=table;
                    // if (data.success) {
                    //     debugger
                    //     var m = data.data;
                    //
                    //
                    openDialog("show","");
                    // } else {
                    //     art.alert(data.msg);
                    // }
                },
                error : function(data) {
                    art.alert(data.info);
                }
            });
        });



        $("#saveServiceCharge").live("click",function(){
            var channelId = $(this).attr('directkey');
            var serviceCharge = $("#"+channelId).val();
            $.ajax({url : ctx+ "/channelplatformgewaycodelink/updateServiceCharge.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:channelId,
                    serviceCharge:serviceCharge
                },
                success : function(data) {
                    alert(data.msg);
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
