
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/channelplatformgewaycodelink/dataAllList.do',
        dataList_url : ctx + "/channelplatformgewaycodelink/dataAllList.do",
        add_url : ctx+ "/channelplatformgewaycodelink/add.do",
        update_url : ctx+ "/channelplatformgewaycodelink/update.do",
        queryId_url: ctx+ "/channelplatformgewaycodelink/getId.do",
        delete_url: ctx+ "/channelplatformgewaycodelink/delete.do",
        manyOperation_url: ctx+ "/channelplatformgewaycodelink/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"channelName",},
        {"data":"codeName",},
        {"data":"serviceCharge",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';

                html = '<input type ="text" class ="inputCommonSty" id="serviceCharge" name ="serviceCharge" value="'+oData.serviceCharge+'">'

                $(nTd).html(html);
            }
        },
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';

                html = html = '<a class = "dataTableBtn dataTableDeleteBtn "id = "edit" directkey="' + oData.id + '" href = "javascript:void(0);"> 费率 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/adminchannel/jumpUpdate.do?op=1&id='+oData.id+'"> 编辑 </a>'
                    +'<a class = "dataTableBtn dataTableDeleteBtn" href="'+ctx+'/adminchannel/jumpUpdate.do?op=2&id='+oData.id+'">重置密码 </a>'
                    +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        channelId:$("#channelId").val()
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
            account.condJsonData['channelId'] = $("#channelId").val();
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
            alert(channelId)
            $.ajax({url : ctx+ "/adminchannelwithdraw/dataAllList.do",
                type : 'post',
                dataType : 'json',
                data :{
                    channelId:id
                },
                success : function(data) {
                    debugger
                    alert(data);
                    // if (data.success) {
                    //     debugger
                    //     var m = data.data;
                    //
                    //
                    //     openDialog("show","");
                    // } else {
                    //     art.alert(data.msg);
                    // }
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
