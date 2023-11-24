<%@page import="com.twoclothing.model.permissions.Permissions"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script><!-- 引入 SweetAlert2 -->
<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"lengthMenu": [5, 10, 20, 50, 100],
			"pageLength": 10,
			"searching": true,  //搜尋功能, 預設是開啟
		    "paging": true,     //分頁功能, 預設是開啟
		    "ordering": true,   //排序功能, 預設是開啟
		    "language": {
		    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" 
		    },
		    "columnDefs": [
                { "orderable": true, "targets": [0, 1] }, // 前三列不排序
                { "orderDataType": "dom-checkbox", "type": "checkbox", "targets": [2, 3, 4, 5, 6, 7, 8, 9] } // 使用checkbox排序方式的列
            ]
		});
	});
</script>
<style type="text/css">
body {
	margin: 2rem ;
}
</style>

</head>
<body>



<table id="example" class="display" style="width: 100%">
  <thead >
	<tr style="background-color:#CCCCFF">
		<th>員工編號</th>
		<th>員工姓名</th>
		<c:forEach var="permission" items="${pmlist}">
			<th> ${permission.permissionName}</th>
		</c:forEach>
		<th>更新權限</th>
	</tr>
  </thead>
  
  <tbody>
	<c:forEach var="employee" items="${emplist}">
			<tr>
				<td>${employee.empId}</td>
				<td>${employee.empName}</td>
				<c:forEach var="permission" items="${pmlist}">
					<td>
						<input type="checkbox" name="empMissions" value="${permission.permissionId}" 
                       		${fn:substring(employee.empMissions, permission.permissionId - 1, permission.permissionId) eq '1' ? 'checked' : ''}>
					</td>
				</c:forEach>
				<td><input type="submit" class="update-button" value="更新"></td>
			</tr>
	</c:forEach>
			
  </tbody>
</table>


<script>
	$("document").ready(function(){
		$(".update-button").click(function(){
			let empId = $(this).closest("tr").find("td:first-child").text();
			let empMissionsList = [];
			
			$(this).closest("tr").find("input[type=checkbox]:checked").each(function(){
				empMissionsList.push($(this).val());
			});
			
			$.ajax({
			    type: "POST",
			    url: "EmpMissions.do",
			    data:{
			        action: "update",
			        empId: empId,
			        empMissionsList: empMissionsList
			    },
			    success: function(res){
			    	Swal.fire({
    	                title: '成功',
    	                text: '更新成功!',
    	                icon: 'success'
    	            });
			    },
			    error: function(xhr, status, error){
			    	alert("更新失敗：" + error);
			    }
			});

		});
	});
</script>

</body>
</html>