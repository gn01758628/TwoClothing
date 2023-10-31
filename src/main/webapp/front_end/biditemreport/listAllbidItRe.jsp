<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.tonyhsieh.service.*"%>
<%@ page import="com.twoclothing.model.abid.biditemreport.*"%>


 <%
 	BidItemReportServiceImpl bidItemReportServiceImpl = new BidItemReportServiceImpl();
   	List<BidItemReport> list = bidItemReportServiceImpl.getAll();
    pageContext.setAttribute("list",list);
	%>

<html>
<head>
<title>�Ҧ��v�аӫ~���|��� - listAllBiditemreport.jsp</title>

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
		 <h3>�Ҧ����|�ӫ~��� - listAllBiditemreport</h3>
		 <h4><a href="select_page.jsp">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�v�аӫ~���|�s��</th>
		<th>�v�аӫ~�s��</th>
		<th>�|���s��</th>
		<th>���u�s��</th>
		<th>���|���</th>
		<th>���|���e</th>
		<th>�f�֪��A</th>
		<th>�f�֤��</th>
		<th>�f�ֵ��G</th>
		<th>�Ƶ�</th>
		
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="biditemreport" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		
		<tr>
			<td>${biditemreport.reportId}</td>
			<td>${biditemreport.bidItemId}-${biditemreport.bidItem.bidName}</td>
			<td>${biditemreport.mbrId}-${biditemreport.members.mbrName}</td>
			<td>${biditemreport.empId}</td>
			<td>${biditemreport.reportDate}</td>
			<td>${biditemreport.bidDescription}</td>
			<td>
				<c:choose>
       			 <c:when test="${biditemreport.bidStatus == 0}">${biditemreport.bidStatus} - �ݼf��</c:when>
       			 <c:when test="${biditemreport.bidStatus == 1}">${biditemreport.bidStatus} - �w�f��</c:when>
       			 </c:choose>
			</td>
			<td>${biditemreport.auditDate}</td>
			<td>
				 <c:choose>
       			 <c:when test="${biditemreport.result == 0}">${biditemreport.result} - �B��</c:when>
       			 <c:when test="${biditemreport.result == 1}">${biditemreport.result} - ���B��</c:when>
       			 </c:choose>
			</td>
			<td>${biditemreport.note}</td>
			

		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front_end/biditemreport/BidItemReport.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="reportId"  value="${biditemreport.reportId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>

		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>