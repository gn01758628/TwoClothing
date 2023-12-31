<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>
<%@ page import="com.twoclothing.model.department.*"%>

 
	
 

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料修改 - update_bidItRe_input.jsp</title>

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
  
        #preview{
        border: 1px solid lightgray;
        display: inline-block;
        width: 100px;
        min-height: 150px;
        position: relative;
      }
      #preview span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: -1;
        color: lightgray;
      }
      #preview img.preview_img{
        width: 100%;
      }
  
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>競標商品檢舉資料修改 - update_bidItRe_input.jsp</h3>
		 <h4><a href="select_page.jsp">回首頁</a></h4>
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

<FORM METHOD="post" ACTION="BidItemReport.do" name="form1" enctype="multipart/form-data">
<table>
    <tr>
		<td>競標商品檢舉編號:<font color=red><b>*</b></font></td>
		<td>${param.reportId}</td>
	</tr>

   <tr>
		<td>競標商品編號:<font color=red><b>*</b></font></td>
		<td>${param.bidItemId}</td>
	</tr>
	 <tr>
		<td>檢舉會員ID:<font color=red><b>*</b></font></td>
		<td>${param.mbrId}</td>
	</tr>
	
	<tr>
		<td>處理員工ID:</td>
		<td><input type="TEXT" name="empid" value="${param.empId}" size="45" required></td> 
	</tr>
	
	 <tr>
		<td>檢舉日期:<font color=red><b>*</b></font></td>
		<td>${param.reportDate}</td>
	</tr>
	
	
	 <tr>
		<td>檢舉內容:<font color=red><b>*</b></font></td>
		<td>${param.description}</td>
	</tr>
		
	<tr>
		<td>審核狀態:</td>
		<td><select size="1" name="bidstatus">
			<option value="0">待審核</option>
			<option value="1">已審核</option>
		</select></td>
	</tr>
	
	<tr>
		<td>審核日期:<font color=red><b>*</b></font></td>
		<td>${param.auditDate}</td>
	</tr>

	<tr>
		<td>審核結果:</td>
		<td><select size="1" name="result">
			<option value="0">處分</option>
			<option value="1">不處分</option>
		</select></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="note" value="${param.note}" size="45"/></td> 
	</tr>
	
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="reportid" value="${param.reportId}">
<input type="hidden" name="bidItemid" value="${param.bidItemId}">
<input type="hidden" name="mbrid" value="${param.mbrId}">
<input type="hidden" name="reportdate" value="${param.reportDate}">
<input type="hidden" name="description" value="${param.description}">
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