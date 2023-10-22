<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.tonyhsieh.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>

<html>
<head>
<title>Emp: Home</title>

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
   <tr><td><h3>Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Emp: Home</p>

<h3>資料查詢:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Emps.  <br><br></li>
  
  
  <li>
    <FORM METHOD="post" ACTION="Employee.do" >
        <b>輸入員工編號 (如1,2,3,4...):</b>
        <input type="text" name="empId" value="${param.empId}"><font color=red>${errorMsgs.empId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>

<%--   <jsp:useBean id="emp" scope="page" class="com.twoclothing.tonyhsieh.EmployeeServiceImpl" /> --%>
   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="Employee.do" > -->
<!--        <b>選擇員工編號:</b> -->
<!--        <select size="1" name="empId"> -->
<%--          <c:forEach var="employee" items="${employee.all}" >  --%>
<%--           <option value="${employee.empId}">${employee.empId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="Employee.do" > -->
<!--        <b>選擇員工姓名:</b> -->
<!--        <select size="1" name="empName"> -->
<%--          <c:forEach var="employee" items="${employee.all}" >  --%>
<%--           <option value="${employee.empId}">${employee.empName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>