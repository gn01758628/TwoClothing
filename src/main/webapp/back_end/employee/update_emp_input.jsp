<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>
<%@ page import="com.twoclothing.model.department.*"%>

 <%
	
//  	DepartmentServiceImpl departmentServiceImpl = new DepartmentServiceImpl();
//    	List<Department> list = departmentServiceImpl.getAllDepartment();
//     pageContext.setAttribute("list",list);
	
	%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
<title>員工資料修改 - update_emp_input.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  
        #preview{
        border: 1px solid lightgray;
        display: inline-block;
        width: 100px;
        min-height: 150px;
        position: relative;
      }
      #preview span.text{
        position: absolute;
        display: inline-block;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        z-index: -1;
        color: lightgray;
      }
      #preview img.preview_img{
        width: 100%;
      }
  
</style>

</head>
<body bgcolor='white'>
<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="Employee.do" name="form1" enctype="multipart/form-data">
<table>
    <tr>
		<td>員工編號:<font color=red><b>*</b></font></td>
		<td>${param.formatEmpId}</td>
	</tr>

	<jsp:useBean id="DepartmentServiceImpl" scope="page" class="com.twoclothing.tonyhsieh.service.DepartmentServiceImpl" />
	<tr>
		<td>部門:<font color=red><b>*</b></font></td>
		<td><select size="1" name="deptid">
			<c:forEach var="department" items="${DepartmentServiceImpl.allDepartment}">
				<option value="${department.deptId}"${(param.deptId==employee.deptId)? 'selected':'' } >${department.deptName}
			
			</c:forEach>
		</select></td>
	</tr>
	

	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="empname" value="${param.empName}" size="45"/></td> <td>${errorMsgs.empname}</td>
	</tr>
	<tr>
		<td>電話:</td>
		<td><input type="text" name="phone" value="${param.phone}"  size="45"/></td> <td>${errorMsgs.phone}</td>
	</tr>	
	<tr>
		<td>地址:</td>
		<td>
			<div id="twzipcode">
			</div>
		</td>
		
	</tr>
	<tr>
		<td></td>
		<td><input type="TEXT" name="address"   value="${param.address}"   size="45"/></td> <td>${errorMsgs.address}</td>
	</tr>
	<tr>
		<td>EMAIL:</td>
		<td><input type="TEXT" name="email"   value="${param.email}"   size="45"/></td> <td>${errorMsgs.email}</td>
	</tr>
	<tr>
		<td>圖片:</td>
		<td><img id="employeeAvatar" src="${pageContext.request.contextPath}/CJImageReader/Employee?id=${param.empId}" width="100" height="100" ></td>
		<td><input class="form-control" type="file" id="avatar" name="avatar"  size="45"/>
	   
		</td>
	</tr>
	
	
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="empid" value="${param.empId}">
<input type="submit" value="送出修改"></FORM>
</body>


<script>
	
$(function(){
	$("#twzipcode").twzipcode({
    });
	// 定義一個函數來更新 <input> 元素的值
	function updateAddressInput() {
	    // 獲取county、district和zipcode的值
	    // 抓取 select 元素的值
		let county = $("select[name='county']");
		let district = $("select[name='district']");
		
		let zipcode = $("input[name='zipcode']");
		
		let address = $("input[name='address']");

	    // 將這些值動態添加到<input>元素中
	    address.val(zipcode.val() + county.val() + district.val());
	}

	$("select[name='county'], select[name='district'], input[name='zipcode']").change(function() {
	    updateAddressInput();
	});
	
	// 假設有一個input file元素
	var fileInput = document.getElementById('avatar');

	// 當選擇了檔案時觸發事件
	fileInput.addEventListener('change', function () {
	    // 獲取所選擇的檔案
	    var file = fileInput.files[0];

	    if (file) {
	        // 將圖片元素的src設定為選擇的檔案的URL
	        var employeeImage = document.getElementById('employeeAvatar');
	        employeeImage.src = URL.createObjectURL(file);
	    }
	});

});
</script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>
<!--
<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
 </style> 
-->
<script>



//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//            theme: '',              //theme: 'dark',
//  	       timepicker:false,       //timepicker:true,
//  	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
//  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
//  		   value: '${param.hiredate}', // value:   new Date(),
//            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
//            //startDate:	            '2017/07/10',  // 起始日
//            //minDate:               '-1970-01-01', // 去除今日(不含)之前
//            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
//         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>