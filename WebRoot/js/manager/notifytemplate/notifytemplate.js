
var datatable;
var basePath = $("#excDataHid").val();
var notifyFieldTypeData;// 字段类型的下拉框值
var account = {
    //地址
    url:{
        list_url : ctx + '/notifytemplate/list.do',
        dataList_url : ctx + "/notifytemplate/dataList.do",
        add_url : ctx+ "/notifytemplate/add.do",
        update_url : ctx+ "/notifytemplate/update.do",
        queryId_url: ctx+ "/notifytemplate/getId.do",
        delete_url: ctx+ "/notifytemplate/delete.do",
        manyOperation_url: ctx+ "/notifytemplate/manyOperation.do",
        update_notify_field_url: ctx+ "/notifytemplate/updateNotifyField.do",
        delete_field_url: ctx+ "/notifyfield/delete.do"
    },



    //列表显示参数
    list:[

        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                // html += '<a id = "notifyField" directkey="' + oData.id + '" directValue="' + oData.gewayCodeName + '" href = "javascript:void(0);"> '+oData.gewayCodeName+ '</a>'
                html += '<input type="radio" name="notifyTemplateId" id="notifyTemplateId" directkey="' + oData.id + '" value="' + oData.id + '">';
                $(nTd).html(html);
            }
        },


        {"data":"template",},
        // {"data":"gewayCodeName",},

        {"data":"gewayCodeName",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                html += '<a id = "notifyField" directkey="' + oData.id + '" directValue="' + oData.gewayCodeName + '" href = "javascript:void(0);"> '+oData.gewayCodeName+ '</a>'
                $(nTd).html(html);
            }
        },

        {"data":"notifyType",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.notifyType==1){
                    html='<span>get</span>';
                }else if(oData.notifyType==2){
                    html='<span>post/json</span>';
                }else if(oData.notifyType==3){
                    html='<span><font color="red">post/form</font></span>';
                }
                $(nTd).html(html);
            }
        },

        // {"data":"dataType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.dataType==1){
        //             html='<span>直接返回</span>';
        //         }else if(oData.dataType==2){
        //             html='<span>一层json</span>';
        //         }else if(oData.dataType==3){
        //             html='<span><font color="red">二层json</font></span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },

        {"data":"parameterName",},
        {"data":"parameterValue",},
        // {"data":"parameterValueType",
        //     "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
        //         var html="";
        //         if(oData.parameterValueType==1){
        //             html='<span>String类型</span>';
        //         }else if(oData.parameterValueType==2){
        //             html='<span>Int类型</span>';
        //         }else if(oData.parameterValueType==3){
        //             html='<span>Long类型</span>';
        //         }
        //         $(nTd).html(html);
        //     }
        // },

        {"data":"sucTag",},
        // {"data":"failTag",},

        {"data":"isEnable",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html="";
                if(oData.isEnable==1){
                    html='<span><font color="red">禁用</font></span>';
                }else if(oData.isEnable==2){
                    html='启用';
                }
                $(nTd).html(html);
            }
        },
        // {"data":"remark",},

        {"data":"id",
            "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
                var html = '';
                html += '<a class = "dataTableBtn dataTableResetBtn " href="'+ctx+'/notifytemplate/jumpUpdate.do?id='+oData.id+'"> 编辑 </a>';
                if (oData.isEnable == 1){
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="2" href = "javascript:void(0);"> 启用 </a>';
                }else {
                    html += '<a class = "dataTableBtn dataTableEnableBtn"  directkey="'+oData.id+'"  directValue="1" href = "javascript:void(0);"> 禁用 </a>';
                }
                html +=' <a class = "dataTableBtn dataTableDeleteBtn"  directkey="' + oData.id + '" href = "javascript:void(0);">删除 </a>';
                $(nTd).html(html);
            }
        }
    ],
    // 查询条件，aoData是必要的。其他的就是对应的实体类字段名，因为条件查询是把数据封装在实体类中的。
    condJsonData : {
        template:null,
        gewayCodeId:0
    },
    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        this.queryNotifyFieldType();
        this.queryGewayCodeAll();
        //添加
        $(".addbtn").live("click",function(){
            // var id = $("#notifyTemplateId").val();
            var id = $("input[name='notifyTemplateId']:checked").val();
            if (id == undefined){
                window.location.href = ctx + "/notifytemplate/jumpAdd.do?id=0";
            } else{
                window.location.href = ctx + "/notifytemplate/jumpAdd.do?id=" + id;
            }
        });

        // 初始化列表数据
        common.showDatas(this.condJsonData,this.list);
        // 条件查询按钮事件
        $('#btnQuery').click(function() {
            account.condJsonData['template'] = $("#template").val();
            account.condJsonData['gewayCodeId'] = $("#gewayCodeId").val();
            common.showDatas(account.condJsonData,account.list);
        });

        // 重置
        $("#butReset").click(function(){
            account.condJsonData['template'] = "";
            $("#template").val("");
            account.condJsonData['gewayCodeId'] = "0";
            $("#gewayCodeId").val("0");
            common.showDatas(account.condJsonData,account.list);
        });
        //删除-删除模板
        $(".dataTableDeleteBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateStatus(data);
        });

        //删除-删除字段
        $(".dataTableDeleteByFieldBtn").live("click",function(){
            var id = $(this).attr('directkey');
            var data = {
                id:id,
                yn:'1'
            }
            common.updateFieldStatus(data);
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


        //接收字段
        $("#notifyField").live("click",function(){
            var id = $(this).attr('directkey');
            var gewayCodeName = $(this).attr('directValue');
            $("#divGewayCodeName").html(gewayCodeName);

            $.ajax({url : ctx+ "/notifytemplate/getFieldById.do",
                type : 'post',
                dataType : 'json',
                data :{
                    id:id
                },
                success : function(data) {
                    if (data.success) {
                        var dataList=data.data;
                        var fieldTypeList = dataList[0].strategyDataList;
                        var shtml="";
                        shtml +="<table id='dataDayTable' name='dataDayTable'>";

                        shtml +="<tr>";
                        shtml +="<td>字段名</td>";
                        shtml +="<td>参数名</td>";
                        shtml +="<td>参数值</td>";
                        shtml +="<td>参数值类型</td>";
                        shtml +="<td>字段类型</td>";
                        shtml +="<td>存放位置</td>";
                        shtml +="<td>操作</td>";
                        shtml +="</tr>";
                        for (var i=0;i<dataList.length>0;i++) {
                            shtml +="<tr>";
                            shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                            shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='fieldName' name='fieldName' value='"+dataList[i].fieldName+"' /> </td>";
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterName' name='parameterName' value='"+dataList[i].parameterName+"'/> </td>";
                            shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterValue' name='parameterValue' value='"+dataList[i].parameterValue+"'/> </td>";

                            shtml +="<td>";
                            shtml +="<select id='parameterValueType' name='parameterValueType'  class='text-input medium-input'>";
                            if (dataList[i].parameterValueType == 0){
                                shtml +="<option value='' selected='selected'>=请选择=</option>";
                                shtml +="<option value='1'>String类型</option>";
                                shtml +="<option value='2'>Int类型</option>";
                                shtml +="<option value='3'>Long类型</option>";
                            }else if (dataList[i].parameterValueType == 1){
                                shtml +="<option value=''>=请选择=</option>";
                                shtml +="<option value='1' selected='selected'>String类型</option>";
                                shtml +="<option value='2'>Int类型</option>";
                                shtml +="<option value='3'>Long类型</option>";
                            }else if (dataList[i].parameterValueType == 2){
                                shtml +="<option value=''>=请选择=</option>";
                                shtml +="<option value='1'>String类型</option>";
                                shtml +="<option value='2' selected='selected'>Int类型</option>";
                                shtml +="<option value='3'>Long类型</option>";
                            }else if (dataList[i].parameterValueType == 3){
                                shtml +="<option value=''>=请选择=</option>";
                                shtml +="<option value='1'>String类型</option>";
                                shtml +="<option value='2'>Int类型</option>";
                                shtml +="<option value='3' selected='selected'>Long类型</option>";
                            }
                            shtml +="</select>";
                            shtml +="</td>";

                            shtml +="<td>";
                            shtml +="<div class='formCtrlDiv' id = 'fieldTypeDiv'>";
                            shtml +="<select id='fieldType' name='fieldType'  class='text-input medium-input'>";
                            if (dataList[i].fieldType == 0){
                                shtml +="<option value='' selected='selected'>===请选择===</option>";
                            }else{
                                shtml +="<option value=''>===请选择===</option>";
                            }
                            for (var j=0;j<fieldTypeList.length>0;j++) {
                                if (dataList[i].fieldType == fieldTypeList[j].stgValueTwo){
                                    shtml +="<option value="+fieldTypeList[j].stgValueTwo+" selected='selected'>"+fieldTypeList[j].stgValueOne+"</option>";
                                }else {
                                    shtml +="<option value="+fieldTypeList[j].stgValueTwo+">"+fieldTypeList[j].stgValueOne+"</option>";
                                }
                            }
                            shtml +="</select>";
                            shtml +="</div>";
                            shtml +="</td>";

                            shtml +="<td>";
                            shtml +="<select id='seat' name='seat'  class='text-input medium-input'>";
                            if (dataList[i].seat == 0){
                                shtml +="<option value='' selected='selected'>=请选择=</option>";
                                shtml +="<option value='1'>第一层JSON</option>";
                                shtml +="<option value='2'>第二层JSON</option>";
                            }else if (dataList[i].seat == 1){
                                shtml +="<option value=''>=请选择=</option>";
                                shtml +="<option value='1' selected='selected'>第一层JSON</option>";
                                shtml +="<option value='2'>第二层JSON</option>";
                            }else if (dataList[i].seat == 2){
                                shtml +="<option value=''>=请选择=</option>";
                                shtml +="<option value='1'>第一层JSON</option>";
                                shtml +="<option value='2' selected='selected'>第二层JSON</option>";
                            }
                            shtml +="</select>";
                            shtml +="</td>";


                            shtml +="<td><a class = 'dataTableBtn dataTableDeleteByFieldBtn'  directkey='"+dataList[i].id+"' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' /> </td>";
                            shtml +="</tr>";
                        }

                        shtml +="</table>";





                        $("#notifyFieldDiv").html(shtml);
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

    //下拉框数据填充
    //查询所有接收字段的字段类型-无分页-下拉框选项:
    queryNotifyFieldType:function(){
        var url = basePath + "strategy/dataJsonList.do";
        var data = {
            "stgType":7
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            notifyFieldTypeData = data;
            var shtml="";
            shtml += "<select id='fieldType' name='fieldType'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].stgValueTwo+">"+dataList[i].stgValueOne+"</option>";
            }
            shtml +="</select>";
            $("#hiddenFieldTypeDiv").html(shtml);
        });
    },

    //下拉框数据填充
    //查询所有通道码-无分页-下拉框选项:
    queryGewayCodeAll:function(){
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            shtml += "<select id='gewayCodeId' name='gewayCodeId'  class='text-input medium-input'>";
            shtml +="<option value=''>===请选择===</option>";
            for (var i=0;i<dataList.length>0;i++) {
                shtml +="<option value="+dataList[i].id+">"+dataList[i].gewayCodeName+"==="+dataList[i].myGewayCode+"</option>";
            }
            shtml +="</select>";
            $("#gewayCodeDiv").html(shtml);
        });
    }




}

//添加tr列表
function addTr(){

    var tbl = document.all.dataDayTable;
    var rows = tbl.rows.length;
    var tr = tbl.insertRow(rows);
    var rdo0 = tr.insertCell(0);

    rdo0.innerHTML = "<tr>" +
        "<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='fieldName' name='fieldName' value='' placeholder='请输入字段名'/></td>" ;
        // "</tr>";

    var rdo1 = tr.insertCell(1);
    rdo1.innerHTML = "<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterName' name='parameterName' value='' placeholder='请输入参数名'/> </td>";

    var rdo2 = tr.insertCell(2);
    rdo2.innerHTML = "<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterValue' name='parameterValue' value='' placeholder='请输入参数值'/> </td>";

    var rdo3 = tr.insertCell(3);
    rdo3.innerHTML = "<td>" +
        "<select id='parameterValueType' name='parameterValueType'  class='text-input medium-input'>" +
        "<option value='' selected='selected'>=请选择=</option>" +
        "<option value='1'>String类型</option>" +
        "<option value='2'>Int类型</option>" +
        "<option value='3'>Long类型</option>" +
        "</select>" +
        "</td>";



    var shtml_fieldType = "";

    shtml_fieldType += "<td>";
    shtml_fieldType +="<div class='formCtrlDiv' id = 'fieldTypeDiv'>";
    shtml_fieldType +="<select id='fieldType' name='fieldType'  class='text-input medium-input'>";
    shtml_fieldType +="<option value=''>===请选择===</option>";
    for (var j=0;j<notifyFieldTypeData.length>0;j++) {
        shtml_fieldType +="<option value="+notifyFieldTypeData[j].stgValueTwo+">"+notifyFieldTypeData[j].stgValueOne+"</option>";
    }
    shtml_fieldType +="</select>";
    shtml_fieldType +="</div>";
    shtml_fieldType +="</td>";

    var rdo4 = tr.insertCell(4);
    rdo4.innerHTML = shtml_fieldType;

    var rdo5 = tr.insertCell(5);
    rdo5.innerHTML = "<td>" +
        "<select id='seat' name='seat'  class='text-input medium-input'>" +
        "<option value='' selected='selected'>=请选择=</option>" +
        "<option value='1'>第一层JSON</option>" +
        "<option value='2'>第二层JSON</option>" +
        "</select>" +
        "</td>";

    var rdo6 = tr.insertCell(6);
    rdo6.innerHTML = "<td><a class = 'dataTableBtn dataTableResetBtn' style='background: #d43d3d' onclick='delTr(this)' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' /> </td>" +
        "</tr>";

}
//删除tr列表
function delTr(btn){
    $(btn).closest("tr").remove();
}


function submitHandler() {
    var url = account.url.update_notify_field_url;

    var formData = $("#newFirstStoreForm").serialize();



    var sb = JSON.stringify(formData);
    // alert("sb:" + sb);
    // alert("formData:" + formData);
    $.ajax({
        url : url,
        type : 'post',
        // contentType: 'application/json; charset=utf-8',
        dataType : 'json',
        // data :formData,
        data :{
            strArr:sb
        },
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


/**
 * 在复制新增的时候，页面初始化加载接收字段数据
 */
function loadNotifyField(){
    var notifytemplateId = $("#mbId").val();
    $.ajax({url : ctx+ "/notifytemplate/getFieldById.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:notifytemplateId
        },
        success : function(data) {
            if (data.success) {
                var dataList=data.data;
                var fieldTypeList = dataList[0].strategyDataList;
                var shtml="";
                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>字段名</td>";
                shtml +="<td>参数名</td>";
                shtml +="<td>参数值</td>";
                shtml +="<td>参数值类型</td>";
                shtml +="<td>字段类型</td>";
                shtml +="<td>存放位置</td>";
                shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<dataList.length>0;i++) {
                    shtml +="<tr>";
                    // shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                    shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='fieldName' name='fieldName' value='"+dataList[i].fieldName+"' /> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterName' name='parameterName' value='"+dataList[i].parameterName+"'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterValue' name='parameterValue' value='"+dataList[i].parameterValue+"'/> </td>";

                    shtml +="<td>";
                    shtml +="<select id='parameterValueType' name='parameterValueType'  class='text-input medium-input'>";
                    if (dataList[i].parameterValueType == 0){
                        shtml +="<option value='' selected='selected'>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 1){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1' selected='selected'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 2){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2' selected='selected'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 3){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3' selected='selected'>Long类型</option>";
                    }
                    shtml +="</select>";
                    shtml +="</td>";


                    shtml +="<td>";
                    shtml +="<div class='formCtrlDiv' id = 'fieldTypeDiv'>";
                    shtml +="<select id='fieldType' name='fieldType'  class='text-input medium-input'>";
                    if (dataList[i].fieldType == 0){
                        shtml +="<option value='' selected='selected'>===请选择===</option>";
                    }else{
                        shtml +="<option value=''>===请选择===</option>";
                    }
                    for (var j=0;j<fieldTypeList.length>0;j++) {
                        if (dataList[i].fieldType == fieldTypeList[j].stgValueTwo){
                            shtml +="<option value="+fieldTypeList[j].stgValueTwo+" selected='selected'>"+fieldTypeList[j].stgValueOne+"</option>";
                        }else {
                            shtml +="<option value="+fieldTypeList[j].stgValueTwo+">"+fieldTypeList[j].stgValueOne+"</option>";
                        }
                    }
                    shtml +="</select>";
                    shtml +="</div>";
                    shtml +="</td>";


                    shtml +="<td>";
                    shtml +="<select id='seat' name='seat'  class='text-input medium-input'>";
                    if (dataList[i].seat == 0){
                        shtml +="<option value='' selected='selected'>=请选择=</option>";
                        shtml +="<option value='1'>第一层JSON</option>";
                        shtml +="<option value='2'>第二层JSON</option>";
                    }else if (dataList[i].seat == 1){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1' selected='selected'>第一层JSON</option>";
                        shtml +="<option value='2'>第二层JSON</option>";
                    }else if (dataList[i].seat == 2){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>第一层JSON</option>";
                        shtml +="<option value='2' selected='selected'>第二层JSON</option>";
                    }
                    shtml +="</select>";
                    shtml +="</td>";


                    shtml +="<td><a class = 'dataTableBtn dataTableResetBtn' style='background: #d43d3d' onclick='delTr(this)' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' /> </td>";

                    shtml +="</tr>";
                }
                shtml +="</table>";

                $("#notifyFieldDiv").html(shtml);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}




/**
 * 在编辑的时候，页面初始化加载接收字段数据
 */
function loadNotifyFieldUpdate(){
    var notifytemplateId = $("#mbId").val();
    $.ajax({url : ctx+ "/notifytemplate/getFieldById.do",
        type : 'post',
        dataType : 'json',
        data :{
            id:notifytemplateId
        },
        success : function(data) {
            if (data.success) {
                var dataList=data.data;
                var fieldTypeList = dataList[0].strategyDataList;
                var shtml="";
                shtml +="<table id='dataDayTable' name='dataDayTable'>";

                shtml +="<tr>";
                shtml +="<td>字段名</td>";
                shtml +="<td>参数名</td>";
                shtml +="<td>参数值</td>";
                shtml +="<td>参数值类型</td>";
                shtml +="<td>字段类型</td>";
                shtml +="<td>存放位置</td>";
                shtml +="<td>操作</td>";
                shtml +="</tr>";
                for (var i=0;i<dataList.length>0;i++) {
                    shtml +="<tr>";
                    shtml +="<input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='id' name='id' value='"+dataList[i].id+"' />";
                    shtml +="<td><input type='text' style='width: 80px;box-sizing: border-box' class='formInput' id='fieldName' name='fieldName' value='"+dataList[i].fieldName+"' /> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterName' name='parameterName' value='"+dataList[i].parameterName+"'/> </td>";
                    shtml +="<td><input type='text' style='width: 100px;box-sizing: border-box' class='formInput' id='parameterValue' name='parameterValue' value='"+dataList[i].parameterValue+"'/> </td>";

                    shtml +="<td>";
                    shtml +="<select id='parameterValueType' name='parameterValueType'  class='text-input medium-input'>";
                    if (dataList[i].parameterValueType == 0){
                        shtml +="<option value='' selected='selected'>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 1){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1' selected='selected'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 2){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2' selected='selected'>Int类型</option>";
                        shtml +="<option value='3'>Long类型</option>";
                    }else if (dataList[i].parameterValueType == 3){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>String类型</option>";
                        shtml +="<option value='2'>Int类型</option>";
                        shtml +="<option value='3' selected='selected'>Long类型</option>";
                    }
                    shtml +="</select>";
                    shtml +="</td>";


                    shtml +="<td>";
                    shtml +="<div class='formCtrlDiv' id = 'fieldTypeDiv'>";
                    shtml +="<select id='fieldType' name='fieldType'  class='text-input medium-input'>";
                    if (dataList[i].fieldType == 0){
                        shtml +="<option value='' selected='selected'>===请选择===</option>";
                    }else{
                        shtml +="<option value=''>===请选择===</option>";
                    }
                    for (var j=0;j<fieldTypeList.length>0;j++) {
                        if (dataList[i].fieldType == fieldTypeList[j].stgValueTwo){
                            shtml +="<option value="+fieldTypeList[j].stgValueTwo+" selected='selected'>"+fieldTypeList[j].stgValueOne+"</option>";
                        }else {
                            shtml +="<option value="+fieldTypeList[j].stgValueTwo+">"+fieldTypeList[j].stgValueOne+"</option>";
                        }
                    }
                    shtml +="</select>";
                    shtml +="</div>";
                    shtml +="</td>";

                    shtml +="<td>";
                    shtml +="<select id='seat' name='seat'  class='text-input medium-input'>";
                    if (dataList[i].seat == 0){
                        shtml +="<option value='' selected='selected'>=请选择=</option>";
                        shtml +="<option value='1'>第一层JSON</option>";
                        shtml +="<option value='2'>第二层JSON</option>";
                    }else if (dataList[i].seat == 1){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1' selected='selected'>第一层JSON</option>";
                        shtml +="<option value='2'>第二层JSON</option>";
                    }else if (dataList[i].seat == 2){
                        shtml +="<option value=''>=请选择=</option>";
                        shtml +="<option value='1'>第一层JSON</option>";
                        shtml +="<option value='2' selected='selected'>第二层JSON</option>";
                    }
                    shtml +="</select>";
                    shtml +="</td>";

                    shtml +="<td><a class = 'dataTableBtn dataTableResetBtn' style='background: #7B7B7B' onclick='onclickAlert()' href = 'javascript:void(0);'>删除 </a> <input type='hidden' style='width: 100px;box-sizing: border-box' class='formInput' id='yc' name='yc' value='`' /> </td>";

                    shtml +="</tr>";
                }
                shtml +="</table>";

                $("#notifyFieldDiv").html(shtml);
            } else {
                art.alert(data.msg);
            }
        },
        error : function(data) {
            art.alert(data.info);
        }
    });
}


function onclickAlert(){
    alert("编辑模板时,无法删除已存在的字段,请在其它地方删除!");
}




$(function(){
    account.indexInit();
})
