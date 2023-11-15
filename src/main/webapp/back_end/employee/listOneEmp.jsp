<%@page import="com.twoclothing.model.permissions.Permissions"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>所有員工權限資料 - listAllempmis.jsp</title>

<script src="https://code.jquery.com/jquery-3.5.1.js"></script>                                    <!-- ●●js  for jquery datatables 用 -->
<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.2.1/dist/css/bootstrap.min.css" integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">


<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"searching": false,  //搜尋功能, 預設是開啟
		    "paging": false,     //分頁功能, 預設是開啟
		    "ordering": false,   //排序功能, 預設是開啟
		    "language": {
		    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" 
		    }
		});
	});
</script>
<style type="text/css">
body {
	margin: 2rem ;
}
</style>

</head>

<table id="example" class="display" style="width: 100%">
	<thead >
		<tr>
			<th>員工編號</th>
			<th>員工姓名</th>
			<th>員工地址</th>
			<th>員工EMAIL</th>
			<th>員工電話</th>
			<th>員工部門</th>
			<th>員工狀態</th>
			<th>員工密碼</th>
			<th>員工圖片</th>
			<th>修改</th>
			
		</tr>
	</thead>
	
	<tr>
			<td>${employee.empId}</td>
			<td>${employee.empName}</td>
			<td>${employee.address}</td>
			<td>${employee.email}</td>
			<td>${employee.phone}</td>
			<td>${employee.deptId}-[${employee.department.deptName}]</td>
			<td>
				 <c:choose>
       			 <c:when test="${employee.empStatus == 0}">在職</c:when>
       			 <c:when test="${employee.empStatus == 1}">離職</c:when>
       			 </c:choose>
			</td>
			<td>${employee.pswdHash}</td>
			<td>
				<c:if test="${not empty employee.avatar}">
					<img src="${pageContext.request.contextPath}/ReadIMG?empId=${employee.empId}" width=100px height=100px>
				</c:if>
			
			</td>  
			

			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/employee/Employee.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改">
			     <input type="hidden" name="empId"  value="${employee.empId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
		</tr>
</table>

</body>
</html>