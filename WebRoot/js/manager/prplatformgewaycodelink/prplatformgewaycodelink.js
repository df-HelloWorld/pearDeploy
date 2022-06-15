
var datatable;
var account = {
    //地址
    url:{
        list_url : ctx + '/prplatformgewaycodelink/list.do',
        dataList_url : ctx + "/prplatformgewaycodelink/dataList.do",
        add_url : ctx+ "/prplatformgewaycodelink/add.do",
        update_url : ctx+ "/prplatformgewaycodelink/update.do",
        queryId_url: ctx+ "/prplatformgewaycodelink/getId.do",
        delete_url: ctx+ "/prplatformgewaycodelink/delete.do",
        manyOperation_url: ctx+ "/prplatformgewaycodelink/manyOperation.do"
    },
    //列表显示参数
    list:[
        {"data":"alias",},
        {"data":"gewayCodeNames",},
        {"data":"codeNames",},
        {"data":"ratio",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                let  names ='ratio'+iRow;
                var html = "<input type='text' size='20' id="+names+" name="+names+"  value="+oData.ratio+" />";
                $(nTd).html(html);
            }
        },
        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==0){
                    html='<span style="color: #ff301d">初始化</span>';
                }else if(oData.isEnable==1){
                    html='<span style="color: #ff301d">暂停状态</span>';
                }else if(oData.isEnable==2){
                    html='<span style="color: #2f9833">正常状态</span>';
                }
                $(nTd).html(html);
            }
        },
        {"data":"createTime",},
        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html = html = '<a class = "dataTableBtn dataTableDeleteBtn " href="'+ctx+'/prplatformgewaycodelink/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>'
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
        //添加
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/prplatformgewaycodelink/jumpAdd.do";
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['codeName'] = $("#codeName").val();
            account.condJsonData['pfGewayCode'] = $("#pfGewayCode").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['codeName'] = "";
            account.condJsonData['pfGewayCode'] = "";
            $("#codeName").val("");
            $("#pfGewayCode").val("");
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
    }

}

$(function(){
    account.indexInit();
})
