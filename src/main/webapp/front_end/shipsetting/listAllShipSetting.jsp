<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.shipsetting.*" %>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <% 
     Object account = session.getAttribute("user");                  // �q session�����X (key) account����
     if (account == null) {                                             // �p�� null, �N��user���n�J�L , �~���H�U�u�@
       session.setAttribute("location", request.getRequestURI());       //*�u�@1 : �P�ɰO�U�ثe��m , �H�K��login.html�n�J���\�� , ��������ɦܦ�����(���t�XLoginHandler.java) 
       response.sendRedirect(request.getContextPath()+"/front_end/members/registerLogin.jsp");   //*�u�@2 : �и�user�h�n�J����(login.html) , �i��n�J 
       return; 
     } 
 %>  

<!DOCTYPE html>



<html>
<head>

<title>listAllShipSetting.jsp</title>

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
		<p><strong>�|���s��:</strong> ${user.mbrId}</p>
		 <h3>�Ҧ����y�]�w - listAllShipSetting.jsp</h3>
		 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp'>�|������</a>
</h4>
	</td></tr>
</table>

<table>
    <tr>
        <th>�|�����y�]�w�s��</th>
        <th>�|���s��</th>
        <th>�|���m�W</th>
        <th>����H�m�W</th>
        <th>����H���</th>
        <th>����H�a�}</th>
    </tr>
    
<c:choose>
    <c:when test="${not empty ShipSetting}">
        <c:forEach var="ShipSetting" items="${ShipSetting}">
            <tr>
                <td>${ShipSetting.shipId}</td>
                <td>${ShipSetting.mbrId}</td>
                <td>${user.mbrName}</td>
                <td>${ShipSetting.receiveName}</td>
                <td>${ShipSetting.receivePhone}</td>
                <td>${ShipSetting.receiveAddress}</td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="�ק�">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td>
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="�R��">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>
    <c:otherwise>
        <tr>
            <td colspan="6">�L���</td>
        </tr>
    </c:otherwise>
</c:choose>
</table>
    <form method="post" action="<%=request.getContextPath()%>/front_end/shipsetting/addShipSetting.jsp" style="margin-bottom: 0px;">
         <input type="submit" value="�s�W">
         <input type="hidden" name="action" value="insert">
     </form>
</body>
</html>