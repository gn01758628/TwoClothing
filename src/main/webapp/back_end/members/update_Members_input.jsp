<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>會員評分修改</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css" />
<style type="text/css">
#myTable {
    width: 50%;
    margin: 0 auto; /* 水平置中 */
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

<body bgcolor='white'>
<h1 style="color: red;">會員評分修改</h1>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" name="form1">
    <table id="myTable">
        <thead>
    <tr>
 		<td> <a href='${pageContext.request.contextPath}//members/Members.do?action=getAll'> 回會員列表</a></td>
	</tr>
    <tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
	</tr>
    <tr>
		<td>${param.mbrId}</td>
	</tr>
	<tr>
		<td>會員姓名:</td>
	</tr>
	<tr>
		<td>${param.mbrName}</td>
	</tr>
	<tr>
		<td>會員信箱(帳號):</td>
	</tr>
	<tr>
		<td>${param.email}</td>
	</tr>
	<tr>
		<td>帳號狀態:(0:未驗證,1:正常,2:停權)</td>
	</tr>
	<tr>
		<td>${param.mbrStatus}</td>
	</tr>
	<tr>
		<td><input type="TEXT" name="mbrStatus"  value="${param.mbrStatus}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.mbrStatus}</td>
	</tr>
	<tr>
		<td>賣家權限分數:(1~10分)</td>
	</tr>
	<tr>
		<td>${param.sellScore}</td>
	</tr>
	<tr>
		<td><input type="TEXT" name="sellScore"  value="${param.sellScore}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellScore}</td>
	</tr>
	<tr>
		<td>買家權限分數:(1~10分)</td>
	</tr>
	<tr>
		<td>${param.buyScore}</td>
	</tr>
	<tr>
		<td><input type="TEXT" name="buyScore"  value="${param.buyScore}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyScore}</td>
	</tr>
	<tr>
	<td>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="mbrId" value="${param.mbrId}">
		<input type="submit" value="送出修改">
	</td>
	</tr>
</table>
</FORM>
<br>


</body>
</html>