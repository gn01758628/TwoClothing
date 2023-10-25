<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
MembersServiceImpl membersServiceImpl = new MembersServiceImpl();
    List<Members> list = membersServiceImpl.getAll();
    pageContext.setAttribute("list",list);
%>


<html>
<head>
<title>�Ҧ����u��� - listAllEmp.jsp</title>

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
		 <h3>�Ҧ����u��� - listAllEmp.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">�^����</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>�|���s��</th>
		<th>�|���m�W</th>
		<th>�|���H�c(�b��)</th>
		<th>�|���K�X���ƭ�</th>
		<th>�b�����A</th>
		<th>�|���j�Y�K</th>
		<th>�|����a�ӳ��Ϥ�01</th>
		<th>�|����a�ӳ��Ϥ�02</th>
		<th>�|���I��</th>
		<th>�|���������]�l�B</th>
		<th>�R�a�����`�P��</th>
		<th>�R�a�����`�H��</th>
		<th>��a�����`�P��</th>
		<th>��a�����`�H��</th>
		<th>�|���̫�n�J�ɶ�</th>
		<th>��a�v������</th>
		<th>�R�a�v������</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="members" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">>
		
		<tr>
		<td>${members.mbrId}</td>
		<td>${members.mbrName}</td>
		<td>${members.email}</td>
		<td>${members.pswdHash}</td>
		<td>${members.mbrStatus}</td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader6?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td><img src="<%=request.getContextPath() %>/DBGifReader7?mbrid=${members.mbrId}" width=100px height=100px></td>
		<td>${members.mbrPoint}</td>
		<td>${members.balance}</td>
		<td>${members.buyStar}</td>
		<td>${members.buyRating}</td>
		<td>${members.sellStar}</td>
		<td>${members.sellRating}</td>
		<td>${members.lastLogin}</td>
		<td>${members.sellScore}</td>
		<td>${members.buyScore}</td>	
			<td>
			  <FORM METHOD="post" ACTION="Members.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�ק�">
			     <input type="hidden" name="mbrId"  value="${members.mbrId}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="Members.do" style="margin-bottom: 0px;">
			     <input type="submit" value="�R��">
			     <input type="hidden" name="mbrId"  value="${members.mbrId}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>