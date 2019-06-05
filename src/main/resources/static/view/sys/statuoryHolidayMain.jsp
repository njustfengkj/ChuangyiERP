<%@ page import="java.util.List" %>
<%@ page import="com.feng.model.po.hr.Shift" %><%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2018/2/1
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>法定节假日管理</title>
    <link href="/css/bootstrap-3.3.7/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css"
          href="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css">
</head>
<%
    List<Shift> list = (List<Shift>) request.getAttribute("shifts");
%>
<body>
<div class="container" style="float:  left;padding:  0;">
    <table class="table table-hover" id="tblJbsb">
        <thead>
        <th>班次id</th>
        <th>班次名称</th>
        <th>上班开始时间</th>
        <th>上班结束时间</th>
        <th>休息开始时间</th>
        <th>休息结束时间</th>
        <th>是否次日</th>
        </thead>
        <tbody id="tbodyShifts">

        </tbody>
    </table>
</div>
</body>
<script type="text/javascript" src="/js/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.7/bootstrap.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript"
        src="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.js"></script>
<script type="text/javascript" src="/js/util/common_func.js" charset="UTF-8"></script>
<script>
    $(
        queryShifts()
    )

    function queryShifts() {
        $.ajax({
            type: "GET",
            url: "/shift/batchQuery",
            dataType: "json",
            data: {},
            success: function (retMsg) {
                refreshTable(retMsg);
            }
        })
    }

    function refreshTable(retMsg)
    {
        var tblObject=$("#tbodyShifts");
        var tbodyContent='';
        for(var i=0;i<retMsg.length;i++)
        {
            var rowData=retMsg[i];
            var rowContent='';
            rowContent+='<tr ondblclick="openEditWindow('+rowData.id+')" id="tr'+rowData.id+'"><td>'+rowData.id+'</td><td>'+rowData.bcmc+'</td><td>'+rowData.sbkssj+'</td><td>'+rowData.sbjssj+'</td><td>'+rowData.ksxxsj+'</td><td>'+rowData.jsxxsj+'</td><td><a href="javascript:deleteOne('+rowData.id+')"><span class="glyphicon glyphicon-remove"></span></a></td></tr>';
            tbodyContent+=rowContent;
        }
        tbodyContent+='<tr ondblclick="openAddWindow()"><td colspan="7"><span class="glyphicon glyphicon-plus"></span>双击这里新增一种班次</td></tr>';
        // tblObject.innerText=tbodyContent;
        document.getElementById("tbodyShifts").innerHTML=tbodyContent;
    }

    function openEditWindow(bcid)
    {
        openwin1024("/shift/getEditPage?bcid="+bcid,"编辑班次",true);
    }

    function openAddWindow()
    {
        openwin1024("/shift/getAddPage","增加班次");
    }

    function deleteOne(bcid)
    {
        $.ajax({
            type: "POST",
            url: "/shift",
            data: {
                _method:"DELETE",
                id:bcid
            },
            success: function (retMsg) {
                var eleId="#tr"+bcid;
                $(eleId).remove();
            }
        })
    }
</script>
</html>
