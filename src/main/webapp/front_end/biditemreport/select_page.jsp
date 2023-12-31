<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>

  <%
// 	EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();
// 	List<Employee> list = employeeServiceImpl.getAll();
//     pageContext.setAttribute("list",list);
	%>
<html>
<head>
<title>BidItemReport: Home</title>

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
  <li><a href='listAllbidItRe.jsp'>List</a> all BidItemReport.  <br><br></li>
  
  <h3><b>複合查詢 (使用 Criteria Query)：</b></h3>
  <form action="${pageContext.request.contextPath}/front_end/biditemreport/BidItemReport.do" method="post">
  		<p><label>根據檢舉編號：</label></p>
		<input type="text" name="reportId"><br>
		<p><label>根據競標商品編號：</label></p>
		<input type="text" name="bidItemId"><br>
		<p><label>根據員工ID：</label></p>
		<input type="text" name="empId"><br>
		<p><label>根據競標商品狀態：</label></p>
			<select size="1" name="bidStatus">
				<option value="">---</option>
				<option value="0">待審核</option>
				<option value="1">已審核</option>
			</select></td>
	
		<p><label>根據競標商品處理結果：</label></p>
			<select size="1" name="result">
				<option value="">---</option>
				<option value="0">處分</option>
				<option value="1">不處分</option>
			</select></td>
		
		
  		<p><label>根據檢舉日期間範圍</label></p>
		<input type="date" name="startdate">~<input type="date" name="enddate"><br>
		<p><input type="submit" value="送出"></p>
		<input type="hidden" name="action" value="getCompositeQuery_For_Display">
</form>	

</ul>


<h3>競標商品檢舉新增</h3>

<ul>
  <li><a href='addbidItRe.jsp'>Add</a> a new BidItemReport.</li>
</ul>

</body>
</html>