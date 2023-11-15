<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>錢包與點數</title>

<style>

  h4 {
    color: red;
    display: inline;
  }
</style>

<style>
  table {
    width: 50%; /* 讓表格寬度填滿父元素 */
    border-collapse: collapse; /* 合併表格邊框 */
    margin-top: 5px;
    margin-bottom: 5px;
    margin: 0 auto; /* 水平置中 */
  }
  th, td {
    border: 1px solid #CCCCFF;
    padding: 10px; /* 調整內邊距 */
    text-align: center; /* 文本置中 */
  }
  th {
    background-color: #CCCCFF; /* 標題背景顏色 */
    font-weight: bold; /* 加粗字體 */
  }
  img {
    max-width: 100px;
    max-height: 100px;
  }
   table#table-1 {
    width: 50%; /* 與下面的表格大小相匹配 */
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">

	<tr><td>
		 
		 <h4><a href="${pageContext.request.contextPath}/MemberCentre.jsp">會員中心</a></h4>
	</td></tr>
</table>

<table>
	<tr>
    <th>買家評價</th>
    <td>
        <script>
            var buyStar = ${Members.buyStar};
            if (buyStar > 0) {
                document.write('☆'.repeat(buyStar));
            } else {
                document.write('無評價');
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
                document.write('無評價');
            }
        </script>
    </td>
</tr>
<<tr>
    <th>賣家評價</th>
    <td>
        <script>
            var sellStar = ${Members.sellStar};
            if (sellStar > 0) {
                document.write('☆'.repeat(sellStar));
            } else {
                document.write('無評價');
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
                document.write('無評價');
            }
        </script>
    </td>
</tr>


</table> 


</body>
</html>