<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM Members: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Members: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Members: Home</p>

<h3>資料查詢:</h3>



<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Members.  <br><br></li>
  

<li>
    <FORM METHOD="post" ACTION="/TwoClothing/Members.do" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="mbrId" value="${param.mbrId}"><font color=red>${errorMsgs.mbrId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</li>

<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.gordon.service.MembersServiceImpl" />


<li>
    <FORM METHOD="post" ACTION="/TwoClothing/Members.do" >
        <b>選擇會員姓名:</b>
        <input type="text" name="mbrName" value="${param.mbrName}"><font color=red>${errorMsgs.mbrId}</font>

        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</li>


</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Members.</li>
</ul>

</body>
</html>