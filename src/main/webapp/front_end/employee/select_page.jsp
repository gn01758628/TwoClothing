<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>
  <%
	EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
	List<Employee> list = employeeServiceImpl.getAll();
    pageContext.setAttribute("list",list);
	%>
<html>
<head>
<title>Emp: Home</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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

<aside class="sidebar"></aside>
<main>
<table id="table-1">
   <tr><td><h3>Emp: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Emp: Home</p>

<h3>��Ƭd��:</h3>

<%-- ���~��C --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">�Эץ��H�U���~:</font> -->
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
        <b>��J���u�s�� (�p1,2,3,4...):</b>
        <input type="text" name="empId" value="${param.empId}"><font color=red>${errorMsgs.empId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
  </li>


   
  <li>
     <FORM METHOD="post" ACTION="Employee.do" >
       <b>��ܭ��u�s��:</b>
       <select size="1" name="empId">
         <c:forEach var="employee" items="${list}" > 
          <option value="${employee.empId}">${employee.empId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
    </FORM>
  </li>
  
  <li>
     <FORM METHOD="post" ACTION="Employee.do" >
       <b>��ܭ��u�m�W:</b>
       <select size="1" name="empId">
         <c:forEach var="employee" items="${list}" > 
          <option value="${employee.empId}">${employee.empName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>
</main>

<script>
	$(".sidebar").load("/TwoClothing/empCenterA.html");
</script>
</body>
</html>