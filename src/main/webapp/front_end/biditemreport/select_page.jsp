<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>

  <%
// 	EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
// 	List<Employee> list = employeeServiceImpl.getAllEmployees();
//     pageContext.setAttribute("list",list);
	%>
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
   <tr><td><h3>BidItemReport: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for BidItemReport: Home</p>

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
  <li><a href="${pageContext.request.contextPath}/front_end/biditemreport/BidItemReport.do?action=getAll">List</a> all BidItemReport.  <br><br></li>
  
  <h3><b>�ƦX�d�� (�ϥ� Criteria Query)�G</b></h3>
  <form action="${pageContext.request.contextPath}/front_end/biditemreport/BidItemReport.do" method="post">
  		<p><label>�ھ����|�s���G</label></p>
		<input type="text" name="reportId"><br>
		<p><label>�ھ��v�аӫ~�s���G</label></p>
		<input type="text" name="bidItemId"><br>
		<p><label>�ھڭ��uID�G</label></p>
		<input type="text" name="empId"><br>
		<p><label>�ھ��v�аӫ~���A�G</label></p>
			<select size="1" name="bidStatus">
				<option value="">---</option>
				<option value="0">�ݼf��</option>
				<option value="1">�w�f��</option>
			</select></td>
	
		<p><label>�ھ��v�аӫ~�B�z���G�G</label></p>
			<select size="1" name="result">
				<option value="">---</option>
				<option value="0">�B��</option>
				<option value="1">���B��</option>
			</select></td>
		
		
  		<p><label>�ھ����|������d��</label></p>
		<input type="date" name="startdate">~<input type="date" name="enddate"><br>
		<p><input type="submit" value="�e�X"></p>
		<input type="hidden" name="action" value="getCompositeQuery_For_Display">
</form>	
	
		
<!--   <li> -->
<!--     <FORM METHOD="post" ACTION="Employee.do" > -->
<!--         <b>��J���u�s�� (�p1,2,3,4...):</b> -->
<%--         <input type="text" name="empId" value="${param.empId}"><font color=red>${errorMsgs.empId}</font> --%>
<!--         <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--         <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->


   
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="Employee.do" > -->
<!--        <b>��ܭ��u�s��:</b> -->
<!--        <select size="1" name="empId"> -->
<%--          <c:forEach var="employee" items="${list}" >  --%>
<%--           <option value="${employee.empId}">${employee.empId} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--     </FORM> -->
<!--   </li> -->
  
<!--   <li> -->
<!--      <FORM METHOD="post" ACTION="Employee.do" > -->
<!--        <b>��ܭ��u�m�W:</b> -->
<!--        <select size="1" name="empId"> -->
<%--          <c:forEach var="employee" items="${list}" >  --%>
<%--           <option value="${employee.empId}">${employee.empName} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getOne_For_Display"> -->
<!--        <input type="submit" value="�e�X"> -->
<!--      </FORM> -->
<!--   </li> -->
</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Emp.</li>
</ul>

</body>
</html>