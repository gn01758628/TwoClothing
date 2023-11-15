<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>修改密碼</title>

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

<h3>修改員工密碼:</h3>

<FORM METHOD="post" ACTION="Employee.do" name="form1" enctype="multipart/form-data" autocomplete="off">
<table>
	<tr>
		<td>員工姓名:</td>
		<td>${empName} </td>
	</tr>
	<tr>
		<td>輸入新密碼:</td>
		<td><input type="TEXT" name="pswdhash"   value="${pswdHash}"   size="45" required/></td> 
	</tr>
	

</table>
<br>
<input type="hidden" name="empId"  value="${empId}">
<input type="hidden" name="action" value="change_Password">
<input type="submit" id="submit-button" value="送出新增"></FORM>${successMsg}

</body>

</html>