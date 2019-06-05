<%--
  Created by IntelliJ IDEA.
  User: Feng
  Date: 2018/2/11
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="x-ua-compatible" content="ie=7"/>
<meta name="viewport" content="width=device-width, initial-scale=1" /> 
<title>工作日历管理</title>

<link href="/css/calendarAll.css" rel="stylesheet" type="text/css"/>
<link href="/css/skin.css" rel="stylesheet" type="text/css"/>
<link href="/css/fontSize12.css" rel="stylesheet" type="text/css"/>
<link href="/css/calendar.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript" src="/js/jquery.min.js"></script>
<script type="text/javascript" src="/js/calendar.js"></script>

</head>
<body style="height:98%;">


<div class="main" style="height:98%;">

    <div class="pathBar" style="margin-bottom:2px;">
        <div class="pathBarPos">

        </div>
    </div>

    <div id="myrl" style="width:820px; margin-left:auto; margin-right:auto; height:840px; overflow:hidden;">
        <form name=CLD>
            <TABLE class="biao" style="table-layout:fixed" width="800px">
                <TBODY>
                <TR>
                    <TD class="calTit" colSpan=7 style="height:30px;padding-top:3px;text-align:center;">

                        <a href="#" title="上一年" id="nianjian" class="ymNaviBtn lsArrow"></a>
                        <a href="#" title="上一月" id="yuejian" class="ymNaviBtn lArrow"></a>

                        <div style="width:250px; float:left; padding-left:230px;">
                                            <span id="dateSelectionRili" class="dateSelectionRili"
                                                  style="cursor:hand;color: white; border-bottom: 1px solid white;"
                                                  onclick="dateSelection.show()">
											<span id="nian" class="topDateFont"></span><span
                                                    class="topDateFont">年</span><span id="yue"
                                                                                      class="topDateFont"></span><span
                                                    class="topDateFont">月</span>
											<span class="dateSelectionBtn cal_next"
                                                  onclick="dateSelection.show()">▼</span></span> &nbsp;&nbsp;<font id=GZ
                                                                                                                   class="topDateFont"></font>
                        </div>

                        <!--新加导航功能-->
                        <div style="left: 250px; display: none;" id="dateSelectionDiv">
                            <div id="dateSelectionHeader"></div>
                            <div id="dateSelectionBody">
                                <div id="yearList">
                                    <div id="yearListPrev" onclick="dateSelection.prevYearPage()">&lt;</div>
                                    <div id="yearListContent"></div>
                                    <div id="yearListNext" onclick="dateSelection.nextYearPage()">&gt;</div>
                                </div>
                                <div id="dateSeparator"></div>
                                <div id="monthList">
                                    <div id="monthListContent"><span id="SM0" class="month"
                                                                     onclick="dateSelection.setMonth(0)">1</span><span
                                            id="SM1" class="month" onclick="dateSelection.setMonth(1)">2</span><span
                                            id="SM2" class="month" onclick="dateSelection.setMonth(2)">3</span><span
                                            id="SM3" class="month" onclick="dateSelection.setMonth(3)">4</span><span
                                            id="SM4" class="month" onclick="dateSelection.setMonth(4)">5</span><span
                                            id="SM5" class="month" onclick="dateSelection.setMonth(5)">6</span><span
                                            id="SM6" class="month" onclick="dateSelection.setMonth(6)">7</span><span
                                            id="SM7" class="month" onclick="dateSelection.setMonth(7)">8</span><span
                                            id="SM8" class="month" onclick="dateSelection.setMonth(8)">9</span><span
                                            id="SM9" class="month" onclick="dateSelection.setMonth(9)">10</span><span
                                            id="SM10" class="month" onclick="dateSelection.setMonth(10)">11</span><span
                                            id="SM11" class="month curr" onclick="dateSelection.setMonth(11)">12</span>
                                    </div>
                                    <div style="clear: both;"></div>
                                </div>
                                <div id="dateSelectionBtn">
                                    <div id="dateSelectionTodayBtn" onclick="dateSelection.goToday()">今天</div>
                                    <div id="dateSelectionOkBtn" onclick="dateSelection.go()">确定</div>
                                    <div id="dateSelectionCancelBtn" onclick="dateSelection.hide()">取消</div>
                                </div>
                            </div>
                            <div id="dateSelectionFooter"></div>
                        </div>
                        <a href="#" id="nianjia" title="下一年" class="ymNaviBtn rsArrow" style="float:right;"></a>
                        <a href="#" id="yuejia" title="下一月" class="ymNaviBtn rArrow" style="float:right;"></a>
                        <!--	<a id="jintian" href="#" title="今天" class="btn" style="float:right; margin-top:-2px; font-size:12px; text-align:center;">今天</a>-->

                    </TD>
                </TR>
                <TR class="calWeekTit" style="font-size:12px; height:20px;text-align:center;">
                    <TD width="100" class="red">星期日</TD>
                    <TD width="100">星期一</TD>
                    <TD width="100">星期二</TD>
                    <TD width="100">星期三</TD>
                    <TD width="100">星期四</TD>
                    <TD width="100">星期五</TD>
                    <TD width="100" class="red">星期六</TD>
                </TR>
                <SCRIPT language="JavaScript">

                    var gNum;
                    for (var i = 0; i < 6; i++) {
                        document.write('<tr style="table-layout:fixed" align=center height="50" id="tt">');
                        for (var j = 0; j < 7; j++) {
                            gNum = i * 7 + j ;
                            <!--onMouseOver="mOvr(this,' + gNum + ');"  onMouseOut="mOut(this);"-->
                            document.write('<td  id="GD' + gNum + '" on="0" ><font id="SD' + gNum + '" style="font-size:22px;"  face="Arial"');
                            if (j == 0)  document.write('color=red');
                            if (j == 6)
                                document.write('color=red');
                            document.write('  TITLE="">  </font><br><font  id="LD' + gNum + '"  size=2  style="white-space:nowrap;overflow:hidden;cursor:default;">  </font></td>');
                        }
                        document.write('</tr>');
                    }
                </SCRIPT>
                </tbody>
            </TABLE>
            <table class="biao" width="800px" style="margin-top: 10px;">
            <tr>
              <td>
                 <div id="workday" class="cc">
             		<p id="setworkday"><span>设为工作日（默认周一至周五）</span>: </p>
           		</div>
           	 </td>
            </tr>
            <tr>
              <td>
         		<div id="holiday" class="cc">
           			<p id="setholiday"><span>设为假日（默认周六周日和法定假日当天）</span>: </p>
          		</div>
             </td> 
            </tr>
           <!--  <tr>
              <td>
                <div id="delete" class="cc">
                    <p id="deleteSet"><span>删除日期设置</span>: </p>
                </div>
             </td> 
            </tr>  -->
          </table>
          <table  border="1"  cellpadding="5" cellspacing="5" style="margin-top: 10px;width: 80%;height: 30px">
            <tr align="center" style="margin-top: 5px;">
              <td width="80"><input type=button value='提交' class="button6"  onclick=h_submit()></td>
              <td width="80"><input type=button value="重置" class="button6"  onclick=rebuild()></td>
              <td width="20" bgcolor="#cfdff3">&nbsp;</td>
              <td width="80">选 中&nbsp;&nbsp;</td>
              <!-- <td width="20" bgcolor="#CFDFF0">&nbsp;</td>
              <td width="80">今 日</td> -->
              </tr>
        </tr>
      </table>
        </form>
    </div>
</div>

<SCRIPT language="JavaScript">
$("td").click(function(){    //td点击事件；
    var val = $(this).attr("id")  //定义val值为点击的此td的id值；
})
	//提交
function h_submit(){
	if(hDays.length==0){
		alert("提交数据为空！");
		return false;
	}else{
		alert(hDays);
	}
	var data = "$"+hDays;
	$.ajax({
	       url:"saveCalendar",
	       data:{"calendarData":data},
	       type:'POST',
	       cache:false,
	       async:false,
	       success:function(data) {
	           if (data.isError === "1") {
	               layer.alert(data.msg, {icon : 2, time : 5000});
	           } else {
	        	   window.location.reload();
	           }
	       },
	       error : function() {
	           layer.alert('操作失败，请刷新页面后重试！', {icon : 2, time : 5000});
	       }
	   });
}
  //重置
 function rebuild(){
	hDays=[];
	window.location.reload();
}          

 </SCRIPT>
<div id="details" style="margin-top:-1px;"></div>
</body>
</html>
