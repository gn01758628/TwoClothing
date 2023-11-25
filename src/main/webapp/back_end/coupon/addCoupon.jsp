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
		
		<td><input type="datetime-local" id="startDate" name="createDate"  value="${createDate}" size="45" required/></td> <td>${errorMsgs.createdate}</td>
	</tr>
	<tr>
		<td>失效日期:</td>
		<td><input type="datetime-local" id="endDate" name="expireDate"  value="${expireDate}" size="45" required/></td> <td>${errorMsgs.expireDate}</td>
	</tr>
	<tr>
		<td>折扣類型:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="distype">
				<c:forEach var="entry" items="${couponDisTypeMap.entrySet()}">
				    <option value="${entry.key}"${(entry.key==distype)? 'selected':'' } >${entry.value}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>折扣額度:</td>
		<td><input type="number" name="disvalue"  min="0" value="${disvalue}"   size="45" required/></td> <td>${errorMsgs.disvalue}</td>
	</tr>
	<tr>
		<td>最低金額條件:</td>
		<td><input type="number" name="minamount" min="0"  value="${not empty minamount ? minamount : 0}"   size="45" required/></td> <td>${errorMsgs.minamount}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert_Coupon">
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

	    // 設定結束日期和時間的最小值為現在的日期和時間
	    document.getElementById("endDate").min = formattedNow;

	});
	
        
</script>
</html>