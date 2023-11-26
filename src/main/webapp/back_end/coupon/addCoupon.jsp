<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>新增優惠券</title>

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
		 <h3>優惠券新增</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/back_end/coupon/CouponServlet.do?action=get_All_Coupon">返回優惠券管理頁面</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<FORM METHOD="post" ACTION="CouponServlet.do" name="form" >
<table>

	<tr>
		<td>優惠券名稱:</td>
		<td><input type="TEXT" name="cpnName" value="${cpnName}" size="45" required/></td> <td>${errorMsgs.cpnName}</td>
	</tr>
	<tr>
		<td>使用日期:</td>
		
		<td><input type="datetime-local" id="startDate" name="createDate"  value="${createDate}" size="45" required/></td> <td>${errorMsgs.createDate}</td>
	</tr>
	<tr>
		<td>失效日期:</td>
		<td><input type="datetime-local" id="endDate" name="expireDate"  value="${expireDate}" size="45" required/></td> <td>${errorMsgs.expireDate}</td>
	</tr>
	<tr>
		<td>折扣類型:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="disType">
				<c:forEach var="entry" items="${couponDisTypeMap.entrySet()}">
				    <option value="${entry.key}"${(entry.key==disType)? 'selected':'' } >${entry.value}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>折扣額度:</td>
		<td><input type="number" name="disValue"  min="1" value="${disValue}"   size="45" required/></td> <td>${errorMsgs.disValue}</td>
	</tr>
	<tr>
		<td>最低金額條件:</td>
		<td><input type="number" name="minAmount" min="0"  value="${not empty minAmount ? minAmount : 0}"   size="45" required/></td> <td>${errorMsgs.minAmount}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert_Coupon">
<input type="submit" id="submit-button" value="送出新增"></FORM>${success}

</body>

<script>
		
		
	$(document).ready(function() {
		document.getElementById("submit-button").addEventListener("click", function(event) {
				setMinMaxDates();
		});
	});
	
		function setMinMaxDates() {

	        let now = new Date();
	        let startDate = new Date();
	        startDate.setMinutes(startDate.getMinutes() + 1);
	        let endDate = new Date();
	        endDate.setMinutes(endDate.getMinutes() + 2);

	        function formatDate(date) {
	            return (
	                date.getFullYear() + '-' +
	                ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
	                ('0' + date.getDate()).slice(-2) + 'T' +
	                ('0' + date.getHours()).slice(-2) + ':' +
	                ('0' + date.getMinutes()).slice(-2)
	            );
	        }

	        let formattedStartDate = formatDate(startDate);

	        let formattedEndDate = formatDate(endDate);

	        document.getElementById("startDate").min = formattedStartDate;

	        document.getElementById("endDate").min = formattedEndDate;
	    }

        
</script>
</html>