<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料修改 - update_emp_input.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/members/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" name="form1">
<table>
    <tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td>${param.mbrId}</td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td>${param.mbrName}</td>
	</tr>
	<tr>
		<td>會員信箱(帳號):</td>
		<td>${param.email}</td>
	</tr>
	<tr>
		<td>會員密碼哈希值:</td>
		<td>${param.pswdHash}</td>
	</tr>
	<tr>
		<td>帳號狀態:</td>
		<td>${param.mbrStatus}</td>
	</tr>
	<tr>
		<td>會員大頭貼:</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${param.mbrId}&imgType=avatar" width=100px height=100px  ></td>

	</tr>
	<tr>
		<td>會員賣家商場圖片01:</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${param.mbrId}&imgType=shopimg01" width=100px height=100px ></td>

	</tr>
	<tr>
		<td>會員賣家商場圖片02:</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${param.mbrId}&imgType=shopimg02" width=100px height=100px ></td>
	</tr>
	<tr>
		<td>會員點數:</td>
		<td>${param.mbrPoint}</td>
	</tr>
	<tr>
		<td>會員虛擬錢包餘額:</td>
		<td>${param.balance}</td>
	</tr>
	<tr>
		<td>買家評價總星數:</td>
		<td>${param.buyStar}</td>
	</tr>
	<tr>
		<td>買家評價總人數:</td>
		<td>${param.buyRating}</td>
	</tr>
	<tr>
		<td>賣家評價總星數:</td>
		<td>${param.sellStar}</td>
	</tr>
	<tr>
		<td>賣家評價總人數:</td>
		<td>${param.sellRating}</td>
	</tr>
	<tr>
		<td>會員最後登入時間:</td>
		<td>${param.lastLogin}</td>
	</tr>
	<tr>
		<td>賣家權限分數:</td>
		<td>${param.sellScore}</td>
		<td><input type="TEXT" name="sellScore"  value="${param.sellScore}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellScore}</td>
		
		
	</tr>
	<tr>
		<td>買家權限分數:</td>
		<td>${param.buyScore}</td>
		<td><input type="TEXT" name="buyScore"  value="${param.buyScore}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyScore}</td>
		
	</tr>


	<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.gordon.service.MembersServiceImpl" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mbrId" value="${param.mbrId}">
<input type="submit" value="送出修改"></FORM>
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.hiredate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>