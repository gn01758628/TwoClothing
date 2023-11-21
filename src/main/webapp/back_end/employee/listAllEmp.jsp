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

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script><!-- 引入 SweetAlert2 -->

<!-- ●● jquery datatables 設定 -->
<script>
	$(document).ready(function() {
		$('#example').DataTable({
			"lengthMenu": [5, 10, 20, 50, 100],
			"searching": true,  //搜尋功能, 預設是開啟
		    "paging": true,     //分頁功能, 預設是開啟
		    "ordering": true,   //排序功能, 預設是開啟
		    "language": {
		    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" ,
		    	"search": "搜尋： (複合查詢請用空白隔開)"
		    },
		    "columnDefs":[
		    	{
		    		targets:[6], orderable:false
		    	}
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
<%
    String servletPath = request.getContextPath() + "/back_end/employee/Employee.do";
%>
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
			<th>員工圖片</th>
			<th>修改</th>
			
		</tr>
	</thead>
	<c:forEach var="employee" items="${empList}">
	
		<tr>
			<td>${employee.formatEmpId}</td>
			<td>${employee.empName}</td>
			<td>${employee.address}</td>
			<td>${employee.email}</td>
			<td>${employee.phone}</td>
			<td>${employee.deptId}-[${employee.department.deptName}]</td>
			<td>
				 <c:choose>
       			 <c:when test="${employee.empStatus == 0}">在職中</c:when>
       			 <c:when test="${employee.empStatus == 1}">已離職</c:when>
       			 </c:choose>
			</td>
			<td>
					<img src="${pageContext.request.contextPath}/CJImageReader/Employee?id=${employee.empId}" width=100px height=100px>
				
			
			</td>  
			

			<td>
			  <FORM METHOD="post" ACTION="<%= servletPath %>" style="margin-bottom: 0px;">
			     <input type="submit" value="修改基本資料">
			     <input type="hidden" name="empId"  value="${employee.empId}">
			     <input type="hidden" name="action"	value="getOne_For_Update">
		     </FORM>
<%-- 		     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/employee/Employee.do" style="margin-bottom: 0px;"> --%>
			     <input id="change_Password_btn" type="submit" value="修改密碼">
<!-- 			     <button type="button" style="border-width">修改密碼</button> -->
<%-- 			     <input type="hidden" name="empId"  value="${employee.empId}"> --%>
<%-- 			     <input type="hidden" name="empName"  value="${employee.empName}"> --%>
<!-- 			     <input type="hidden" id="change_Password_btn" name="action" value="change_Password"> -->
<!-- 		     </FORM> -->
		     <FORM METHOD="post" ACTION="<%= servletPath %>" style="margin-bottom: 0px;right:0;display:fixed;">
			     <c:choose>
	       			 <c:when test="${employee.empStatus == 0}"><input type="submit" value="離職"></c:when>
	       			 <c:when test="${employee.empStatus == 1}"><input type="submit" value="復職"></c:when>
       			 </c:choose>
			     <input type="hidden" name="empId"  value="${employee.empId}">
			     <input type="hidden" name="action"	value="job_Status_Switch">
		     </FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<script>
	$(function(){
		let servletPath = '<%= servletPath %>';
	    $('#change_Password_btn').click(async function(){
	    	// 找到最近的 tr 元素
	        let tr = $(this).closest('tr');
	        // 在 tr 元素中找到名為 empId 的 input 元素
	        let empIdInput = tr.find('input[name="empId"]');
	        // 獲取 empId 的值
	        let empId = empIdInput.val();
	    	
	        const { value: pswdhash, isConfirmed } = await Swal.fire({
	    		  title: "請輸入新密碼",
	    		  input: "text",
	    		  inputAttributes: {
	    		    autocapitalize: "off"
	    		  },
	    		  showCancelButton: true,
	    		  confirmButtonText: "確認",
	    		  cancelButtonText: "取消",
	    		  showLoaderOnConfirm: true,
	    		  preConfirm: async (pswdhash) => {

	    	            if (!pswdhash) {
	    	                Swal.showValidationMessage("密碼不能為空");
	    	                return false;
	    	            }

	    	            // 發送請求到後端進行密碼驗證
	    	            const response = await fetch(servletPath, {
	    	                method: 'POST',
	    	                headers: {
	    	                    'Content-Type': 'application/x-www-form-urlencoded'
	    	                },
	    	                body: 'pswdhash='+pswdhash+'&empId='+empId+'&action=change_Password',
	    	            });

	    	            if (!response.ok) {
	    	                const errorText = await response.text();
	    	                Swal.showValidationMessage('密碼驗證失敗:'+errorText);
	    	                return false;
	    	            }
	    	            
	    	            return pswdhash;
	    	        },
	    	        allowOutsideClick: () => !Swal.isLoading()
	    	    });

	    	    if (isConfirmed) {
	    	        // 在這裡處理密碼驗證成功後的操作
	    	        Swal.fire({
	    	            title: "密碼驗證成功",
	    	            text: '你輸入的密碼是：'+pswdhash,
	    	            icon: "success"
	    	        });
	    	    }
	    });
	});
</script>
</body>
</html>