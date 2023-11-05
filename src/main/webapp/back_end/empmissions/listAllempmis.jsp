<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.empmissions.*"%>
<%@ page import="com.twoclothing.model.employee.*" %>


 <%
 	EmpMissionsServiceImpl empMissionsServiceImpl = new EmpMissionsServiceImpl();
   	List<EmpMissions> list = empMissionsServiceImpl.getAll();
    pageContext.setAttribute("list",list);
	%>

<html>
<head>
<title>�Ҧ����u�v����� - listAllempmis.jsp</title>

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


<table id="table-1">
	<tr><td>
		 <h3>�Ҧ����u�v����� - listAllempmis</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>���u�s��</th>
		<th>�v���s��</th>

	</tr>
	<c:forEach var="empmissions" items="${list}" >
		
		<tr>
			<td>${empmissions.compositeKey.empId}</td>
			<td>${empmissions.compositeKey.permissionId}</td>
								
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/empmissions/EmpMissions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
	   			 <input type="hidden" name="empid"  value="${empmissions.compositeKey.empId}">
			     <input type="hidden" name="permissionid"  value="${empmissions.compositeKey.permissionId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/empmissions/EmpMissions.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="empid"  value="${empmissions.compositeKey.empId}">
			     <input type="hidden" name="permissionid"  value="${empmissions.compositeKey.permissionId}">
			      <input type="hidden" name="action" value="delete">
			         </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%-- <%@ include file="page2.file" %> --%>

</body>
</html>