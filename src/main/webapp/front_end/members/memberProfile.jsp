<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>�ӤH���</title>

<style>

  h4 {
    color: red;
    display: inline;
  }
</style>

<style>
  table {
    width: 50%; /* �����e�׶񺡤����� */
    border-collapse: collapse; /* �X�֪����� */
    margin-top: 5px;
    margin-bottom: 5px;
    margin: 0 auto; /* �����m�� */
  }
  th, td {
    border: 1px solid #CCCCFF;
    padding: 10px; /* �վ㤺��Z */
    text-align: center; /* �奻�m�� */
  }
  th {
    background-color: #CCCCFF; /* ���D�I���C�� */
    font-weight: bold; /* �[�ʦr�� */
  }
  img {
    max-width: 100px;
    max-height: 100px;
  }
   table#table-1 {
    width: 50%; /* �P�U�������j�p�ۤǰt */
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">

	<tr><td>
		 
		 <h4><a href="${pageContext.request.contextPath}/MemberCentre.jsp">�|������</a></h4>
	</td></tr>
</table>

<table>
	<tr>
    <th>�|���m�W</th>
    <td>${Members.mbrName}</td>
  </tr>
  <tr>
    <th>�|���H�c(�b��)</th>
    <td>${Members.email}</td>
  </tr>
  <tr>
    <th>�|���K�X���ƭ�</th>
    <td>${Members.pswdHash}</td>
  </tr>
  <tr>
    <th>�|���j�Y�K</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" width=100px height=100px  ></td>
  </tr>
  <tr>
    <th>�|����a�ӳ��Ϥ�01</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width=100px height=100px ></td>
  </tr>
  <tr>
    <th>�|����a�ӳ��Ϥ�02</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width=100px height=100px ></td>
  </tr>


</table> 
<table id="table-1">

	<tr><td>
		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" style="margin-bottom: 0px;">
	      <input type="submit" value="�ק�">
	      <input type="hidden" name="mbrId"  value="${Members.mbrId}">
	      <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	</td></tr>
</table>

</body>
</html>