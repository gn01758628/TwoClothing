<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>

  <%
// 	EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
// 	List<Employee> list = employeeServiceImpl.getAll();
//     pageContext.setAttribute("list",list);
	%>
<html>
<head>
<title>EmpMissions: Home</title>

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
   <tr><td><h3>EmpMissions: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for EmpMissions: Home</p>

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
  <li><a href='${pageContext.request.contextPath}/back_end/empmissions/EmpMissions.do?action=getAll'>List</a> all EmpMissions.  <br><br></li>
  
   <li>
    <FORM METHOD="post" ACTION="EmpMissions.do" >
        <b>輸入員工編號 (如1,2,3,4...):</b>
        <input type="text" name="empid" value="${param.empId}">
        <input type="hidden" name="action" value="getAll_By_Empid">
        <input type="submit" value="送出">
    </FORM>
  </li>

  <li>
     <FORM METHOD="post" ACTION="EmpMissions.do" >
       <b>輸入權限編號 (如1,2,3,4...):</b>
        <input type="text" name="permissionid" value="${param.permissionid}">
        <input type="hidden" name="action" value="getAll_By_Permissionid">
        <input type="submit" value="送出">
    </FORM>
  </li>

</ul>


<h3>新增員工權限</h3>

<ul>
  <li><a href='addEmpmis.jsp'>Add</a> a new BidItemReport.</li>
</ul>

</body>
</html>