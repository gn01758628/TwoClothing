<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
	List<Employee> list = employeeServiceImpl.getAllEmployees();
	System.out.println(list);
    pageContext.setAttribute("list",list);
    

%>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

<style>
  table#table-1 {
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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
  
 
  
</style>

</head>
<body bgcolor='white'>

<h4>�����m�߱ĥ� EL ���g�k����:</h4>
<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���u�s��</th>
		<th>���u�a�}</th>
		<th>���uEMAIL</th>
		<th>���u�q��</th>
		<th>���u�m�W</th>
		<th>���u����</th>
		<th>���u���A</th>
		<th>���u�K�X</th>
		<th>���u�Ϥ�</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="employee" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
	
		<tr>
			<td>${employee.empId}</td>
			<td>${employee.address}</td>
			<td>${employee.email}</td>
			<td>${employee.phone}</td>
			<td>${employee.empName}</td>
			<td>${employee.deptId}-[${employee.department.deptName}]</td>
			<td>
				 <c:choose>
       			 <c:when test="${employee.empStatus == 0}">${employee.empStatus} - �b¾</c:when>
       			 <c:when test="${employee.empStatus == 1}">${employee.empStatus} - ��¾</c:when>
       			 </c:choose>
			</td>
			<td>${employee.pswdHash}</td>
			<td><img src="${pageContext.request.contextPath}/ReadIMG?empId=${employee.empId}" width=100px height=100px>
			</td>  
			

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/employee/Employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="empId"  value="${employee.empId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/employee/Employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="empId"  value="${employee.empId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>