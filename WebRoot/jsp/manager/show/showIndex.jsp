<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
    <title>支付地址</title>
    <%@ include file="/jsp/manager/common/head-meta.jsp"%>
    <%@ include file="/jsp/manager/common/js-meta.jsp"%>
</head>
<body>
<script type='text/javascript' charset="utf-8" src='${ctxData}js/common/common2.js'></script>

hello world!
<div id = "jump"></div>

<script type='text/javascript'>
    $(function(){
        var afterUrl =  window.location.search.substring(1);
        var afterEqual = afterUrl.substring(afterUrl.indexOf('=')+1);
        var divID= document.getElementById("jump");
        divID.innerHTML = afterEqual;
    });

</script>


</body>
</html>
