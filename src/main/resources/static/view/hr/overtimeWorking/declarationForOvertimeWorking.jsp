<%@ page import="java.util.List" %>
<%@ page import="com.feng.model.po.hr.Employee" %>
<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2018/1/14
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>加班申报</title>
    <link href="/css/bootstrap-3.3.7/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
    <link rel="stylesheet" type="text/css"
          href="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/css/bootstrap-select.css">
</head>
<%
    List<Employee> list = (List) request.getAttribute("employees");
%>
<body>
<table>
    <tbody>
    <tr>
        <td>申报加班人员：</td>
        <td colspan="3">
            <select id="jbrySelect" class="selectpicker " multiple data-live-search="true" name="jbry">
                <%
                    if (list != null) {
                        for (Employee employee : list) {
                %>
                <option value="<%=employee.getGh()%>"><%=employee.getGh()%>:<%=employee.getXm()%>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </td>
    </tr>
    <tr>
        <td>申报加班开始时间：</td>
        <td>
            <%--<div class="form-group">--%>
            <div class="input-group date form_datetime col-md-5" data-date="2018-01-01T05:25:07Z"
                 data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1" style="width:100%">
                <input class="form-control" size="16" type="text" value="" readonly name="jbkssj" id="iptJbkssj">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="dtp_input1" value=""/><br/>
            <%--</div>--%>
        </td>
        <td>申报加班结束时间：</td>
        <td>
            <%--<div class="form-group">--%>
            <div class="input-group date form_datetime col-md-5" data-date="2018-01-01T05:25:07Z"
                 data-date-format="yyyy-mm-dd hh:ii:ss" data-link-field="dtp_input1" style="width:100%">
                <input class="form-control" size="16" type="text" value="" readonly name="jbjssj" id="iptJbjssj">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
            </div>
            <input type="hidden" id="dtp_input2" value=""/><br/>
            <%--</div>--%>
        </td>
    </tr>
    </tbody>
    <tfoot>
    <tr>
        <td>
            <button onclick="addOverTimeWorking()">添加</button>
        </td>
    </tr>
    </tfoot>
</table>
<div class="container" style="float:  left;padding:  0;">
    <table class="table table-hover" id="tblJbsb">
        <thead>
            <th>工号</th>
            <th>姓名</th>
            <th>申报加班开始时间</th>
            <th>申报加班结束时间</th>
            <th>申报加班时长</th>
        </thead>
        <tbody id="tbodyJbsb">

        </tbody>
    </table>
</div>
<script type="text/javascript" src="/js/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.7/bootstrap.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-datetimepicker/bootstrap-datetimepicker.min.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="/js/locales/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript"
        src="http://cdn.bootcss.com/bootstrap-select/2.0.0-beta1/js/bootstrap-select.js"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        language: 'zh-CN',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1
    });
    $('.selectpicker').selectpicker({
        'selectedText': 'cat',
        'actionsBox': true,
        'language': 'zh-CN',
        'deselectAllText': '全部不选',
        'selectAllText': '选中所有',
        'noneSelectedText': '请选择需要加班的人'
    });

    function addOverTimeWorking() {
        $.ajax({
            type: "POST",
            url: "/overtimeWorking/insert",
            async: false,
            data: {
                jbry: $("#jbrySelect").val().toString(),
                jbkssj: $("#iptJbkssj").val(),
                jbjssj: $("#iptJbjssj").val()
            }
        });
        queryJbry();
    }

    function queryJbry()
    {
        $.ajax({
            type:"GET",
            url:"/overtimeWorking/batchQuery",
            dataType: "json",
            data:{
                jbkssj:$("#iptJbkssj").val()
            },
            success:function (retMsg) {
                refreshTable(retMsg);
            }
        })
    }

    function refreshTable(retMsg)
    {
        var tblObject=$("#tbodyJbsb");
        var tbodyContent='';
        for(var i=0;i<retMsg.length;i++)
        {
            var rowData=retMsg[i];
            var rowContent='';
            rowContent+='<tr class="tr'+rowData.gh+'"><td>'+rowData.gh+'</td><td>'+rowData.xm+'</td><td><input id="rowIptSbjbkssj'+rowData.gh+'" class="form-control" value="'+rowData.sbjbkssj+'"></td><td><input id="rowIptSbjbjssj'+rowData.gh+'" class="form-control" value="'+rowData.sbjbjssj+'"></td><td>'+rowData.sbjbsc+'</td><td><a href="javascript:submitEdit('+rowData.gh+')"><span class="glyphicon glyphicon-ok"></span></a></td><td><a href="javascript:deleteRow('+rowData.gh+')"><span class="glyphicon glyphicon-remove"></span></a></td></tr>';
            tbodyContent+=rowContent;
        }
        // tblObject.innerText=tbodyContent;
        document.getElementById("tbodyJbsb").innerHTML=tbodyContent;
    }

    function submitEdit(para) {
        var sbjbkssj=$("#rowIptSbjbkssj"+para).val();
        var sbjbjssj=$("#rowIptSbjbjssj"+para).val();
        $.ajax({
            type:"POST",
            url:"/overtimeWorking/declaration",
            data:{
                _method:"PUT",
                gh:para,
                sbjbkssj:sbjbkssj,
                sbjbjssj:sbjbjssj
            },
            success:function(){
                queryJbry()
            }
        })
    }
    
    function deleteRow(para)
    {
        $.ajax({
            type:"POST",
            url:"/overtimeWorking/declaration",
            data:{
                _method:"DELETE", // 将请求转变为DELETE请求
                gh:para
            },
            success:function (retMsg) {
                var eleCls=".tr"+para;
                $(eleCls).remove();
            }
        })
    }

    $(
        queryJbry()
    );
</script>
</body>
</html>
