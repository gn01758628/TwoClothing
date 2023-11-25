<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.employee.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-twzipcode@1.7.14/jquery.twzipcode.min.js"></script>
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
</style>



</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>員工資料新增</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/back_end/employee/Employee.do?action=get_On_Duty">返回在職員工頁面</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<%-- 錯誤表列 --%>
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font style="color:red">請修正以下錯誤:</font> -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li style="color:red">${message.value}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<%-- </c:if> --%>

<FORM METHOD="post" ACTION="Employee.do" name="form1" enctype="multipart/form-data" autocomplete="off">
<table>
	

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
		<td><input type="TEXT" name="phone"   value="${param.phone}"   size="45"/></td> <td>${errorMsgs.phone}</td>
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
		<td>密碼:</td>
		<td><input type="TEXT" name="pswdhash"   value="${param.pswdHash}"   size="45"/></td> 
	</tr>
	
	<tr>
		<td>圖片:</td>
		<td><input class="form-control" type="file" id="avatar" name="avatar"  size="45"/>
	   
		</td>
	</tr>
	


<!-- 	<tr> -->
<!-- 		<td>部門:<font color=red><b>*</b></font></td> -->
<!-- 		<td><select size="1" name="deptno"> -->
<%-- 			<c:forEach var="deptVO" items="${deptSvc.all}"> --%>
<%-- 				<option value="${deptVO.deptno}" ${(param.deptno==deptVO.deptno)? 'selected':'' } >${deptVO.dname} --%>
<%-- 			</c:forEach> --%>
<!-- 		</select></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" id="submit-button" value="送出新增"></FORM>${success}

</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
//   java.sql.Date hiredate = null;
//   try {
// 	    hiredate = java.sql.Date.valueOf(request.getParameter("hiredate").trim());
//    } catch (Exception e) {
// 	    hiredate = new java.sql.Date(System.currentTimeMillis());
//    }
%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script> --%>
<%-- <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script> --%>

<!-- <style> -->
<!--   .xdsoft_datetimepicker .xdsoft_datepicker { -->
<!--            width:  300px;   /* width:  300px; */ -->
<!--   } -->
<!--   .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box { -->
<!--            height: 151px;   /* height:  151px; */ -->
<!--   } -->
<!-- </style> -->

<script>
	$(function(){
		$("#twzipcode").twzipcode({
        });
		
		// 定義一個函數來更新 <input> 元素的值
		function updateAddressInput() {
		    // 獲取county、district和zipcode的值
		    // 抓取 select 元素的值
			let countyValue = $("select[name='county']").val();
			let districtValue = $("select[name='district']").val();
			
			// 抓取 input 元素的值
			let zipcodeValue = $("input[name='zipcode']").val();



		    // 將這些值動態添加到<input>元素中
		    $("input[name='address']").val(zipcodeValue + countyValue + districtValue);
		}

		$("select[name='county'], select[name='district'], input[name='zipcode']").change(function() {
		    updateAddressInput();
		});
	});
		
		
//         $.datetimepicker.setLocale('zh');
//         $('#f_date1').datetimepicker({
//  	       theme: '',              //theme: 'dark',
// 	       timepicker:false,       //timepicker:true,
// 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
// 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
<%-- 		   value: '<%=hiredate%>', // value:   new Date(), --%>
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