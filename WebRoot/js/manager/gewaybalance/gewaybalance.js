
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/gewaybalance/list.do',
        dataList_url : ctx + "/gewaybalance/dataList.do",
        add_url : ctx+ "/gewaybalance/add.do",
        update_url : ctx+ "/gewaybalance/update.do",
        queryId_url: ctx+ "/gewaybalance/getId.do",
        delete_url: ctx+ "/gewaybalance/delete.do",
        manyOperation_url: ctx+ "/gewaybalance/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"gewayName",},
        {"data":"balance",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = '<input type="text" name="balance'+oData.id+'" id="balance'+oData.id+'" value="' + oData.balance + '"/>';
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '<a class = "dataTableBtn dataTableDeleteBtn " onclick="updateBalance('+oData.id+')">保存</a>';
                    html+= '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/gewaybalance/jumpUpdate.do?id='+oData.gewayId+'"> 金额变动</a>'
                        +' <a class = "dataTableBtn dataTableResetBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        stgName:null,
        stgType:0,
        stgKey:null
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        account.queryGewayAll();
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/gewaybalance/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['gewayId'] = $("#gewayId").val();
            // account.condJsonData['codeName'] = $("#codeName").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['gewayId'] = "";
            $("#gewayId").val("");
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
    },
    queryGewayAll:function(){
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
            $("#gewayDiv").html(shtml);
        });
    },


}

$(function(){
    account.indexInit();
})


function  updateBalance(id){
    var  name ="balance"+id;
    if(confirm("确定保存？")){
        var balance=$("#"+name).val()
        var url = ctx + "/gewaybalance/update.do";
        var data = {
            id:id,
            balance:balance
        };
        common.ajax(url,data,function(data){
            if(data.success==true){
                alert(data.msg);
                window.location.href = ctx + "/gewaybalance/list.do";
            }else{
                alert(data.msg);
            }
        });
    }


}
