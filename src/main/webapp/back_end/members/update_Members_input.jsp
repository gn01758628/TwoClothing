<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>




<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>�|�������ק�</title>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/v/dt/dt-1.10.20/datatables.min.css" />
<style type="text/css">
#myTable {
    width: 50%;
    margin: 0 auto; /* �����m�� */
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
<h1 style="color: red;">�|�������ק�</h1>
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" name="form1">
    <table id="myTable">
        <thead>
    <tr>
 		<td> <a href='${pageContext.request.contextPath}//members/Members.do?action=getAll'> �^�|���C��</a></td>
	</tr>
    <tr>
		<td>�|���s��:<font color=red><b>*</b></font></td>
	</tr>
    <tr>
		<td>${param.mbrId}</td>
	</tr>
	<tr>
		<td>�|���m�W:</td>
	</tr>
	<tr>
		<td>${param.mbrName}</td>
	</tr>
	<tr>
		<td>�|���H�c(�b��):</td>
	</tr>
	<tr>
		<td>${param.email}</td>
	</tr>
	<tr>
		<td>�b�����A:(0:������,1:���`,2:���v)</td>
	</tr>
	<tr>
		<td>${param.mbrStatus}</td>
	</tr>
	<tr>
		<td><input type="TEXT" name="mbrStatus"  value="${param.mbrStatus}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.mbrStatus}</td>
	</tr>
	<tr>
		<td>��a�v������:(1~10��)</td>
	</tr>
	<tr>
		<td>${param.sellScore}</td>
	</tr>
	<tr>
		<td><input type="TEXT" name="sellScore"  value="${param.sellScore}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellScore}</td>
	</tr>
	<tr>
		<td>�R�a�v������:(1~10��)</td>
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
		<input type="submit" value="�e�X�ק�">
	</td>
	</tr>
</table>
</FORM>
<br>


</body>
</html>