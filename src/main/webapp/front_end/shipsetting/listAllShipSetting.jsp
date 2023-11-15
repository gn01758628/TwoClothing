<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*"%>


<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<title>物流設定</title>
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
.green-button {
    background-color: green;
    color: white;
    padding: 10px 20px;
    text-align: center;
    text-decoration: none;
    display: block; 
    margin: 0 auto; 
    font-size: 16px;
    cursor: pointer;
    border-radius: 5px;
}
</style>


</head>
<body>
<h1 style="color: red;">物流設定</h1>
<table id="myTable">
	<tr>
		<td>
			 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp' class="green-button">會員中心</a></h4>
		</td>
	</tr>
</table>

<table id="myTable">
<thead>
    <tr>
        <th>收件人姓名</th>
        <th>收件人手機</th>
        <th>收件人地址</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>
    </thead>
<c:choose>
    <c:when test="${not empty ShipSetting}">
        <c:forEach var="ShipSetting" items="${ShipSetting}">
            <tr>
                <td>${ShipSetting.receiveName}</td>
                <td>${ShipSetting.receivePhone}</td>
                <td>${ShipSetting.receiveAddress}</td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="修改">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>

</c:choose>
</table>
    <form method="post" action="<%=request.getContextPath()%>/front_end/shipsetting/addShipSetting.jsp" style="margin-bottom: 0px;">
         <input type="submit" value="新增">
         <input type="hidden" name="action" value="insert">
     </form>
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