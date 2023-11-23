<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>發放優惠券</title>

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

<h3>發放項目新增:</h3>

<FORM METHOD="post" ACTION="CouponServlet.do" name="form" >
<table>

	<tr>
		<td>優惠券名稱:</td>
		<td>${cpnName}"</td>
	</tr>
	<tr>
		<td>發放日期:</td>
		
		<td><input type="datetime-local" id="startDate" name="allotDate"  value="${allotDate}" size="45" required/></td> <td>${errorMsgs.allotDate}</td>
	</tr>
	<tr>
		<td>發放數量:</td>
		<td><input type="number" name="disvalue"  min="0" value="${totalQuantity}"   size="45" required/></td> <td>${errorMsgs.totalQuantity}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="allot_Coupon">

<input type="submit" id="submit-button" value="送出新增"></FORM>${success}

</body>

<script>
		
		
	$(document).ready(function() {
		// 取得現在的日期和時間
	    const now = new Date();

	    // 格式化現在的日期和時間為 YYYY-MM-DDTHH:mm（符合datetime-local輸入的格式）
	    const options = { year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', hour12: false, timeZone: 'Asia/Taipei' };
	    const formattedNow = now.toLocaleString('zh-TW', options)
	        .replace(/\//g, '-')
	        .replace(',','');
	    

	    // 設定開始日期和時間的最小值為現在的日期和時間
	    document.getElementById("startDate").min = formattedNow;

	});
	
        
</script>
</html>