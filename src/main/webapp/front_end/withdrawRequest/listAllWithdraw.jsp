<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>


<html>
<head>
<title>�|���������]���ڥӽЬd�ߵ��G</title>

<style>
body {
	display: flex;
	justy-content: center;
	flex-direction: column;
	align-items: center;
}

table#table-1 {
	background-color: #BEBEBE;
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
}

table, th, td {
	border: 1px solid #BEBEBE;
}

th, td {
	padding: 5px;
	text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>�|���������]���ڥӽЬd�ߵ��G</h3>
				<h4>
					<a
						href="${pageContext.request.contextPath}/front_end/withdrawRequest/WRMain.jsp">�^����</a>
				</h4>
			</td>
		</tr>
	</table>

	<table>
		<tr>
			<th>���ڥӽнs��</th>
			<th>�|���s��</th>
			<th>���ڪ��B</th>
			<th>�פJ�b��</th>
			<th>�ӽФ��</th>
			<th>�ӽЪ��A</th>
			<th>���u�s��</th>
			<th>�f�֤��</th>
			<th>�Ƶ�</th>


		</tr>
		<%-- 	<%@ include file="page1.file" %>  --%>
		<c:forEach var="withdrawRequest" items="${WRList}">

			<tr>
				<td>${withdrawRequest.wrId}</td>
				<td>${withdrawRequest.mbrId}</td>
				<td>${withdrawRequest.amount}</td>
				<td>${withdrawRequest.mbrAccount}</td>
				<td>${withdrawRequest.reqDate}</td>
				<td name="req_status">${withdrawRequest.reqStatus}</td>
				<td>${withdrawRequest.empId}</td>
				<td>${withdrawRequest.checkDate}</td>
				<td>${withdrawRequest.note}</td>
			</tr>
			
		</c:forEach>
		<tr>
			<td>${withdrawRequest.wrId}</td>
			<td>${withdrawRequest.mbrId}</td>
			<td>${withdrawRequest.amount}</td>
			<td>${withdrawRequest.mbrAccount}</td>
			<td>${withdrawRequest.reqDate}</td>
			<td name="req_status">${withdrawRequest.reqStatus}</td>
			<td>${withdrawRequest.empId}</td>
			<td>${withdrawRequest.checkDate}</td>
			<td>${withdrawRequest.note}</td>
		</tr>

	</table>
	<script>
		$(document).ready(function() {
			$("td[name='req_status']").each(function () {
			let status = $(this).text();
			switch(status){
				case "0":
					$(this).text("�ݼf��");
				break;
				case "1":
					$(this).text("�w�q�L");
				break;
				case "2":
					$(this).text("���q�L");
				break;
				}
			})
		})
	</script>


</body>
</html>