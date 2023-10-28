<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.department.*"%>
  <%
 	DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
   	List<Department> list = departmentServiceImpl.getAllDepartment();
    pageContext.setAttribute("list",list);
	%>
<html>
<head>
<title>Dept: Home</title>

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
   <tr><td><h3>Dept: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Dept: Home</p>

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
  <li>
  <a href="${pageContext.request.contextPath}/back_end/department/Department.do?action=getAll">List</a> all Dept.  <br><br>
 </li>
  
  
  <li>
    <FORM METHOD="post" ACTION="Department.do" >
        <b>輸入部門編號 (如1,2,3,4...):</b>
        <input type="text" name="deptId" value="${param.deptId}"><font color=red>${errorMsgs.deptId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
  </li>


   
  <li>
     <FORM METHOD="post" ACTION="Department.do" >
       <b>選擇部門編號:</b>
       <select size="1" name="deptId">
         <c:forEach var="department" items="${list}" > 
          <option value="${department.deptId}">${department.deptId}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
  </li>
  
</ul>


<h3>部門管理</h3>

<ul>
  <li><a href='addDept.jsp'>Add</a> a new Dept.</li>
</ul>

</body>
</html>