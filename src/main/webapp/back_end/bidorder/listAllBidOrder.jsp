<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.abid.bidorder.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
BidOrderServiceImpl bidOrderServiceImpl = new BidOrderServiceImpl();
    List<BidOrder> list = bidOrderServiceImpl.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllMembers.jsp</title>

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
		 <h3>�v�� - listAllBidOrderjsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/bidorder/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�v�аӫ~�q��s��</th>
		<th>�v�аӫ~�s��</th>
		<th>�R�a�|���s��</th>
		<th>��a�|���s��</th>
		<th>�R�a�����P��</th>
		<th>�R�a�������e</th>
		<th>��a�����P��</th>
		<th>��a�������e</th>
		<th>�q����</th>
		<th>�I�ڤ覡</th>
		<th>�I�ڸ��</th>
		<th>�q����B</th>
		<th>�q�檬�A</th>
		<th>����a�}</th>
		<th>����H�m�W</th>
		<th>����H���</th>
		<th>�Ƶ�</th>
	</tr>
	
<c:choose>
    <c:when test="${not empty list}">	
	<%@ include file="page1.file" %> 
	<c:forEach var="BidOrder" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
		
		<tr>
		<td>${BidOrder.bidOrderId}</td>
		<td>${BidOrder.bidItemId}</td>
		<td>${BidOrder.buyMbrId}</td>
		<td>${BidOrder.sellMbrId}</td>
		<td>${BidOrder.buyStar}</td>
		<td>${BidOrder.buyerRatingDesc}</td>
		<td>${BidOrder.sellStar}</td>
		<td>${BidOrder.sellerRatingDesc}</td>
		<td>${BidOrder.orderDate}</td>
		<td>${BidOrder.payType}</td>
		<td>${BidOrder.payInfo}</td>
		<td>${BidOrder.amount}</td>
		<td>${BidOrder.orderStatus}</td>
		<td>${BidOrder.receiveAddress}</td>
		<td>${BidOrder.receiveName}</td>
		<td>${BidOrder.receivePhone}</td>
		<td>${BidOrder.remarks}</td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="bidOrderId"  value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="bidOrderId"  value="${BidOrder.bidOrderId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
			
		</tr>
		
	</c:forEach>
	<%@ include file="page2.file" %>
	 </c:when>
    <c:otherwise>
        <tr>
            <td colspan="17">�L���</td>
        </tr>
    </c:otherwise>
</c:choose>


	
</table>

</body>
</html>