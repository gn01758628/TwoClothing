<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
//   Members members = (Members) request.getAttribute("members"); //EmpServlet.java(Concroller), 存入req的empVO物件
%>

<html>
<head>
<title>個人資料</title>

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
    <th>會員姓名</th>
    <td>${Members.mbrName}</td>
  </tr>
  <tr>
    <th>會員信箱(帳號)</th>
    <td>${Members.email}</td>
  </tr>
  <tr>
    <th>會員密碼哈希值</th>
    <td>${Members.pswdHash}</td>
  </tr>
  <tr>
    <th>會員大頭貼</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" width=100px height=100px  ></td>
  </tr>
  <tr>
    <th>會員賣家商場圖片01</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" width=100px height=100px ></td>
  </tr>
  <tr>
    <th>會員賣家商場圖片02</th>
    <td><img src="<%=request.getContextPath() %>/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg02" width=100px height=100px ></td>
  </tr>


</table> 
<table id="table-1">

	<tr><td>
		 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do" style="margin-bottom: 0px;">
	      <input type="submit" value="修改">
	      <input type="hidden" name="mbrId"  value="${Members.mbrId}">
	      <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
	</td></tr>
</table>

</body>
</html>