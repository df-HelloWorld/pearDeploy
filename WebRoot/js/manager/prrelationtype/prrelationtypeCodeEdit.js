
var  divdata;
var  selData;
function  querygewayCode(){
    var url = ctx + "/prgewaycode/dataCodeUpdate.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        var dataList=data;
        divdata=dataList;
        // var shtml="";
        // for(let i =0;i<dataList.length;i++) {
        //     if (i != 0 && i % 6 === 0) {
        //         shtml += "<br>";
        //     }
        //     shtml += "<input type='checkbox' name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> " + dataList[i].myGewayCode + "==" + dataList[i].gewayCode+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        // }
        // $("#divGewayCodeId").html(shtml);
    });
}


function  queryselGewayCode(){
    var url = ctx + "/prrelationtype/dataCodeUpdate.do";
    var data = {
    };
    common.ajax(url,data,function(data){
        selData=data;
        // var shtml="";
        // for(let i =0;i<dataList.length;i++) {
        //     if (i != 0 && i % 6 === 0) {
        //         shtml += "<br>";
        //     }
        //     shtml += "<input type='checkbox' name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> " + dataList[i].myGewayCode + "==" + dataList[i].gewayCode+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        // }
        // $("#divGewayCodeId").html(shtml);
    });
}


$(function(){
    // account.indexInit();
    querygewayCode();
    queryselGewayCode();

    var shtml="";
    for(let i =0;i<divdata.length;i++) {
        if (i != 0 && i % 6 === 0) {
            shtml += "<br>";
        }
        if(selData==undefined||selData=="undefined"){
            shtml += "<input type='checkbox' name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> " + dataList[i].myGewayCode + "==" + dataList[i].gewayCode+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
        }else{
            let  count =0;
            for(let j =0;j<selData.length;j++){
                if(dataList[i].id ==selData[j].pfGewayCodeId){
                    shtml += "<input type='checkbox' selected name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> " + dataList[i].myGewayCode + "==" + dataList[i].gewayCode+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                }
                if(count==){

                }
            }
        }

        shtml += "<input type='checkbox' name='gewayCodeId' id='gewayCodeId' value=" + dataList[i].id + "> " + dataList[i].myGewayCode + "==" + dataList[i].gewayCode+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
    }
    $("#divGewayCodeId").html(shtml);
})
