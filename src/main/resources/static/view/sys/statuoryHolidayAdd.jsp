<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2018/2/2
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>新增法定节假日</title>
    <link href="/css/bootstrap-3.3.7/bootstrap.min.css" rel="stylesheet" media="screen">
</head>
<body>
<div style="margin: 10px;padding: 10px">
    <form role="form" name="formMain">
        <label class="col-sm-2 control-label">班次名称</label>
        <div class="col-lg-2">
            <input name="bcmc" type="text" class="form-control">
        </div>
        <div class="checkbox">
            <label>

                <input name="sfcr" type="checkbox" name="chkSfcr" id="idSfcr">是否次日
            </label>
        </div>
        <label class="col-sm-2 control-label">上班开始时间</label>
        <input name="sbkssj" type="text" class="form-control">
        <label class="col-sm-2 control-label">上班结束时间</label>
        <input name="sbjssj" type="text" class="form-control">
        <label class="col-sm-2 control-label">休息开始时间</label>
        <input name="ksxxsj" type="text" class="form-control">
        <label class="col-sm-2 control-label">休息结束时间</label>
        <input name="jsxxsj" type="text" class="form-control">
        <button type="button" class="btn btn-default" onclick="add()">
            <span class="glyphicon glyphicon-plus"></span>增加
        </button>
    </form>
</div>
</body>
<script type="text/javascript" src="/js/jquery.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/js/bootstrap-3.3.7/bootstrap.min.js" charset="UTF-8"></script>
<script>
    function add()
    {
        $.ajax({
            type: "POST",
            url: "/shift",
            data: {
                bcmc:document.formMain.bcmc.value,
                sbkssj:document.formMain.sbkssj.value,
                sbjssj:document.formMain.sbjssj.value,
                ksxxsj:document.formMain.ksxxsj.value,
                jsxxsj:document.formMain.jsxxsj.value,
                sfcr:document.formMain.sfcr.checked
            },
            success: function (retMsg) {
                window.opener.queryShifts();
                window.close();
            }
        })
    }
</script>
</html>
