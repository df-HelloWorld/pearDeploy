
var datatable;
var basePath = $("#excDataHid").val();
var gewayData;// 所有通道数据
var account = {
    //地址
    url:{
        list_url : ctx + '/inorderpull/list.do',
        dataList_url : ctx + "/inorderpull/dataList.do"
    },


    //页面加载
    indexInit : function (){
        //url同步
        common.updateUrl(this.url);
        this.queryChannelAll();
        this.queryGewayAll();
        this.queryPfGewayAll();
        this.queryGewayCodeAll();


        //代收拉单
        $(".addbtn").live("click",function(){
            window.location.href = ctx + "/bankpool/jumpAdd.do";
        });


    },


    //单选框数据填充
    //查询所有渠道-无分页-单选框选项:
    queryChannelAll:function(){
        var url = basePath + "adminchannel/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            for (var i=0;i<dataList.length>0;i++) {
                if(i!=0&&i%4==0){
                    shtml +="<br>";
                }
                // shtml += "<input type='checkbox' name='channelId' id='channelId' value="+dataList.rows[i].channelId+"> "+dataList.rows[i].channelName;
                shtml += "<input type='radio' name='channelId' id='channelId' value="+dataList[i].id+"> "+dataList[i].channelName+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            $("#channelDiv").html(shtml);
        });
    },


    //下拉框数据填充
    //查询所有通道-无分页-下拉框选项:
    queryGewayAll:function(){
        var url = basePath + "prgeway/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            // var dataList=data;
            gewayData = data;
        });
    },



    //单选框数据填充
    //查询所有平台通道-无分页-单选框选项:
    queryPfGewayAll:function(){
        var url = basePath + "prplatformgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            for (var i=0;i<dataList.length>0;i++) {
                if(i!=0&&i%2==0){
                    shtml +="<br>";
                }
                // shtml += "<input type='checkbox' name='pfGewayCodeId' id='pfGewayCodeId' value="+dataList.rows[i].id+"> "+dataList.rows[i].codeName+"="+ dataList.rows[i].pfGewayCode +"&nbsp;&nbsp;";
                shtml += "<input type='radio' name='pfGewayCodeId' id='pfGewayCodeId' value="+dataList[i].id+"> "+dataList[i].codeName+"="+ dataList[i].pfGewayCode+"="+ dataList[i].moneyRange+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            }
            $("#pfGewayDiv").html(shtml);
        });
    },


    //单选框数据填充
    //查询所有通道码-无分页-单选框选项:
    queryGewayCodeAll:function(){
        var url = basePath + "prgewaycode/dataAllList.do";
        var data = {
        };
        common.ajax(url,data,function(data){
            var dataList=data;
            var shtml="";
            // for (var x=0;x<gewayData.length>0;x++) {
            //     shtml += "<input type='text' name='' id='' value="+gewayData[x].gewayName+">";
            //     shtml +="<br>";
            //     for (var j=0;j<dataList.length>0;j++) {
            //         if (gewayData[x].gewayName == dataList[j].gewayName){
            //             shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' value="+dataList[j].id+"> "+dataList[j].gewayCodeName+"="+ dataList[j].myGewayCode+"="+ dataList[j].moneyRange+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            //         }
            //     }
            //     shtml +="<br>";
            // }
            
            
            // for (var i=0;i<dataList.length>0;i++) {
            //     if(i!=0&&i%2==0){
            //         shtml +="<br>";
            //     }
            //     // shtml += "<input type='checkbox' name='gewayCodeId' id='gewayCodeId' value="+dataList.rows[i].id+"> "+dataList.rows[i].gewayCodeName+"="+ dataList.rows[i].myGewayCode +"&nbsp;&nbsp;";
            //     shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' value="+dataList[i].id+"> "+dataList[i].gewayCodeName+"="+ dataList[i].myGewayCode+"="+ dataList[i].moneyRange+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
            // }



            for (var x=0;x<gewayData.length>0;x++) {
                var count = x + 1;
                if(count%2==0){
                    shtml +="<div style='border: 2px solid red;'>";
                }else {
                    shtml +="<div style='border: 2px solid green;'>";
                }

                // shtml +="<table id='' name='' bordercolor='red'>";
                // shtml +="<input type='text' name='' id='' value="+gewayData[x].gewayName+">";
                shtml +="<div><font color='red'>"+gewayData[x].gewayName+"</font></div>";
                var num = 0;

                for (var j=0;j<dataList.length>0;j++) {
                    if (gewayData[x].gewayName == dataList[j].gewayName){

                        // if(num!=0&&num%4==0){
                        if(num%3==0){
                            shtml +="<br>";
                        }
                        // shtml += "<div>";
                        shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' value="+dataList[j].id+"> "+dataList[j].gewayCodeName+"="+ dataList[j].myGewayCode+"="+ dataList[j].moneyRange+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                        num++;
                        // shtml += "</div>";


                        // sbhtml.innerHTML = "<td><input type='text' name='' id='' value="+gewayData[x].gewayName+"></td>";
                        // sbhtml += "<tr>";
                        // sbhtml +="<td><input type='text' name='' id='' value="+gewayData[x].gewayName+"></td>";
                        // sbhtml +="</tr>";
                        // if(i!=0&&i%4==0){
                        //     shtml +="<br>";
                        // }else{
                        //     shtml +="<tr>";
                        // }
                        // shtml +="<tr>";
                        // shtml +="<td><input type='text' name='' id='' value="+gewayData[x].gewayName+"></td>";
                        // shtml +="</tr>";
                        //
                        // shtml += "<input type='radio' name='gewayCodeId' id='gewayCodeId' value="+dataList[j].id+"> "+dataList[j].gewayCodeName+"="+ dataList[j].myGewayCode+"="+ dataList[j].moneyRange+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";

                    }
                }
                // shtml +="</table>";
                shtml +="</div>";
                shtml +="<br>";
            }




            $("#gewayCodeDiv").html(shtml);
        });
    }



}


$(function(){
    account.indexInit();
})
