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
		 <h3>競標 - listOnebidorder.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/bidorder/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" name="form1">
<table>
    <tr>
		<td>競標商品訂單編號:</td>
		<td>${param.bidOrderId}</td>
	</tr>
<!--	<tr>
		<td>競標商品編號:</td>
		<td>${param.bidItemId}</td>
	</tr>
	<tr>
		<td>買家會員編號:</td>
		<td>${param.buyMbrId}</td>
	</tr>
	<tr>
		<td>賣家會員編號:</td>
		<td>${param.sellMbrId}</td>
	</tr>
-->	<tr>
		<td>買家評價星數:</td>
		<td>${param.buyStar}</td>
		<td><input type="TEXT" name="buyStar"  value="${param.buyStar}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyStar}</td>
	</tr>
	<tr>
		<td>買家評價內容:</td>
		<td>${param.buyerRatingDesc}</td>
		<td><input type="TEXT" name="buyerRatingDesc"  value="${param.buyerRatingDesc}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyerRatingDesc}</td>
	</tr>
	<tr>
		<td>賣家評價星數:</td>
		<td>${param.sellStar}</td>
		<td><input type="TEXT" name="sellStar"  value="${param.sellStar}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellStar}</td>
	</tr>
	<tr>
		<td>賣家評價內容:</td>
		<td>${param.sellerRatingDesc}</td>
		<td><input type="TEXT" name="sellerRatingDesc"  value="${param.sellerRatingDesc}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellerRatingDesc}</td>
	</tr>
<!--	<tr>
		<td>訂單日期:</td>
		<td>${param.orderDate}</td>
	</tr>
-->	<tr>
		<td>付款方式:(要做下拉式)</td>
		<td>${param.payType}</td>
		<td><input type="TEXT" name="payType"  value="${param.payType}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.payType}</td>
	</tr>
	<tr>
		<td>付款資料:</td>
		<td>${param.payInfo}</td>
		<td><input type="TEXT" name="payInfo"  value="${param.payInfo}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.payInfo}</td>
	</tr>
<!--	<tr>
		<td>訂單金額:</td>
		<td>${param.amount}</td>
	</tr>
-->	<tr>
		<td>訂單狀態:</td>
		<td>${param.orderStatus}</td>
		<td><input type="TEXT" name="orderStatus"  value="${param.orderStatus}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.orderStatus}</td>
	</tr>
	<tr>
		<td>收件地址:</td>
		<td>${param.receiveAddress}</td>
		<td><input type="TEXT" name="receiveAddress"  value="${param.receiveAddress}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receiveAddress}</td>
	</tr>
	<tr>
		<td>收件人姓名:</td>
		<td>${param.receiveName}</td>
		<td><input type="TEXT" name="receiveName"  value="${param.receiveName}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receiveName}</td>
	</tr>
	<tr>
		<td>收件人手機:</td>
		<td>${param.receivePhone}</td>
		<td><input type="TEXT" name="receivePhone"  value="${param.receivePhone}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receivePhone}</td>
	</tr>
	<tr>
		<td>備註:</td>
		<td>${param.remarks}</td>
		<td><input type="TEXT" name="remarks"  value="${param.remarks}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.remarks}</td>
	</tr>


	<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.gordon.service.MembersServiceImpl" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="bidOrderId" value="${param.bidOrderId}">
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