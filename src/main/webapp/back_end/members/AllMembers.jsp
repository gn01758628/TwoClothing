<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員列表</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css" />
<style type="text/css">
  body {
    font-family: 'Arial', sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

h1 {
    color: red;
}

#myTable {
    width: 100%;
    border-collapse: collapse;
    margin-top: 20px;
}

#myTable th, #myTable td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
}

#myTable th {
    background-color: #f2f2f2;
}

#myTable tr:hover {
    background-color: #f5f5f5;
}

/* DataTables styling */
.dataTables_wrapper {
    margin-top: 20px;
}

.dataTables_filter label {
    font-weight: normal;
}

.dataTables_length label {
    font-weight: normal;
}
</style>

</head>
<body>
<h1 style="color: red;">會員列表</h1>
<a href='${pageContext.request.contextPath}/members/Members.do?action=getAll'>後台會員列表</a>

    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="mbrId" value="${param.mbrId}"><font color=red>${errorMsgs.mbrId}</font>
        <input type="hidden" name="action" value="getOne_For_mbrId">
        <input type="submit" value="送出">
    </FORM>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" >
        <b>輸入會員email :</b>
        <input type="text" name="email" value="${param.email}"><font color=red>${errorMsgs.email}</font>
        <input type="hidden" name="action" value="getOne_For_email">
        <input type="submit" value="送出">
    </FORM>

    <table id="myTable">
        <thead>
	  <tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員信箱(帳號)</th>
		<th>會員密碼哈希值</th>
		<th>帳號狀態</th>
		<th>會員大頭貼</th>
		<th>會員賣家商場圖片01</th>
		<th>會員賣家商場圖片02</th>
		<th>會員點數</th>
		<th>會員虛擬錢包餘額</th>
		<th>買家評價總星數</th>
		<th>買家評價總人數</th>
		<th>賣家評價總星數</th>
		<th>賣家評價總人數</th>
		<th>會員最後登入時間</th>
		<th>賣家權限分數</th>
		<th>買家權限分數</th>
		<th>修改</th>
	  </tr>
	 </thead>
<c:choose>
    <c:when test="${not empty Members}">	
    
	<c:forEach var="Members" items="${Members}" >
		<tr>
		<td>${Members.mbrId}</td>
		<td>${Members.mbrName}</td>
		<td>${Members.email}</td>
		<td>******</td>
		<td>${Members.mbrStatus}</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" width=100px height=100px  ></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width=100px height=100px ></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width=100px height=100px ></td>
		<td>${Members.mbrPoint}</td>
		<td>${Members.balance}</td>
		<td>${Members.buyStar}</td>
		<td>${Members.buyRating}</td>
		<td>${Members.sellStar}</td>
		<td>${Members.sellRating}</td>
		<td>${Members.lastLogin}</td>
		<td>${Members.sellScore}</td>
		<td>${Members.buyScore}</td>	
		<td>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" style="margin-bottom: 0px;">
			<input type="submit" value="修改">
			<input type="hidden" name="mbrId"  value="${Members.mbrId}">
			<input type="hidden" name="action"	value="getOne_For_Update">
			</FORM>
		</td>
		</tr>
		 
		
	</c:forEach>
	 </c:when>
  
</c:choose>

</table>
<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script type="text/javascript"
		src="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#myTable').DataTable({

				columnDefs: [
        		    {
        		        targets: -1,
        		        className: 'dt-body-right'
        		    }
        		  ]
			});

		});
	</script>
</body>
</html>