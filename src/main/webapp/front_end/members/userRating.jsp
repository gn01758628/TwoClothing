<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
%>

<html>
<head>
<title>���]�P�I��</title>

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
    <th>�R�a����</th>
    <td>
        <script>
            var buyStar = ${Members.buyStar};
            if (buyStar > 0) {
                document.write('��'.repeat(buyStar));
            } else {
                document.write('�L����');
            }
        </script>
    </td>
    <td>
        <script>
            var buyStar = ${Members.buyStar};
            var buyRating = ${Members.buyRating};
            if (buyStar > 0) {
                var formattedNumber = (buyStar / buyRating).toFixed(1);
                document.write(formattedNumber);
            } else {
                document.write('�L����');
            }
        </script>
    </td>
</tr>
<<tr>
    <th>��a����</th>
    <td>
        <script>
            var sellStar = ${Members.sellStar};
            if (sellStar > 0) {
                document.write('��'.repeat(sellStar));
            } else {
                document.write('�L����');
            }
        </script>
    </td>
    <td>
        <script>
            var sellStar = ${Members.sellStar};
            var sellRating = ${Members.sellRating};
            if (sellStar > 0) {
                var formattedNumber = (sellStar / sellRating).toFixed(1);
                document.write(formattedNumber);
            } else {
                document.write('�L����');
            }
        </script>
    </td>
</tr>


</table> 


</body>
</html>