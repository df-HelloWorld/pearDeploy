
function  querygewayCode(){
    var url = ctx + "/prgewaycode/dataAllList.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml+="<table>"
        for(let i =0;i<dataList.length;i++) {
            if (i != 0 && i % 4 === 0) {
                shtml += "<br>";
            }
            shtml += "&nbsp;&nbsp;&nbsp;<input type='checkbox'  name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> <span style='font-size: 20px'>" + dataList[i].gewayCodeName +"（"+ dataList[i].gewayCode+"）--"+dataList[i].myGewayCode+"</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }
        shtml+="</table>"
        $("#divGewayCodeId").html(shtml);
    });
}


function  queryselGewayCode(){
    var url = ctx + "/prrelationtype/dataSelGewayCode.do";
    var pfGewayCodeId=$("#pfGewayCodeId").val();
    var data = {
        pfGewayCodeId:$("#pfGewayCodeId").val()
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        var shtml="";
        shtml+="<table width='100%' border='1'>";
            shtml+="<tr>";
            shtml+="<th><strong style='font-size: 20px'>通道名称</strong></th>";
            shtml+="<th><strong style='font-size: 20px'>通道码名称</strong></th>";
            shtml+="<th><strong style='font-size: 20px'>权重</strong></th>";
            shtml+="<th><strong style='font-size: 20px'>操作</strong></th>";
            shtml+="</tr>";
        for(let i =0;i<dataList.length;i++) {
            shtml+="<tr>";
            let  ratio ="ratio"+dataList[i].id;
            shtml+="<td align='center'><strong style='font-size: 17px'>"+dataList[i].gewayName+"</strong></td>";
            shtml+="<td align='center'><strong style='font-size: 17px'>"+dataList[i].gewayCodeNames+"("+dataList[i].gewayCode+")=="+dataList[i].myGewayCode+"</strong></td>";
            shtml+="<td align='center'><input type='text'id='"+ratio+"' name='"+ratio+"' value='"+dataList[i].ratio+"'></td>";
            shtml+="<td><a class = \"dataTableBtn dataTableDeleteBtn \" onclick='updateRatio("+dataList[i].id+")'> 保存 </a><a class = \"dataTableBtn dataTableDeleteBtn \" onclick='deletefrom("+dataList[i].id+")'> 删除 </a></td>";
            shtml+="</tr>";
        }
        shtml+="</table>";
        $("#tabList").html(shtml);
    });
}


function  add(){
    var obj=document.getElementsByName('gewayCodeId'); //选择所有name="'test'"的对象，返回数组
    var s='';
    for(var i=0; i<obj.length; i++){
        if(obj[i].checked) s+=obj[i].value+','; //如果选中，将value添加到变量s中
    }
    //那么现在来检测s的值就知道选中的复选框的值了
    var url = ctx + "/prplatformgewaycodelink/addLink.do";
    // var relationTypeId=$("#relationTypeId").val();
    var data = {
        pfGewayCodeId:$("#pfGewayCodeId").val(),
        relationType:s
    };
    common.ajax(url,data,function(data){
        window.location.href = ctx + "/prrelationtype/jumpGewayCodeUpdate.do?id="+$("#pfGewayCodeId").val();
    });
}

function  deletefrom(id){
    if(confirm("确定删除这条信息")){
        var data = {
            id:id
        };
        var url = ctx + "/prplatformgewaycodelink/delete.do";
        common.ajax(url,data,function(data){
            alert("删除成功");
            window.location.href = ctx + "/prrelationtype/jumpGewayCodeUpdate.do?id="+$("#pfGewayCodeId").val();
        });
    }

}

function  updateRatio(id){
    let ratio= $("#"+'ratio'+id).val();
    var data = {
        id:id,
        ratio:ratio
    };
    var url = ctx + "/prplatformgewaycodelink/update.do";
    common.ajax(url,data,function(data){
        alert("保存成功");
        window.location.href = ctx + "/prrelationtype/jumpGewayCodeUpdate.do?id="+$("#relationTypeId").val();
    });
}

$(function(){
    querygewayCode();
    queryselGewayCode();
})
