<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM BidOrder: Home</title>

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





<a href='${pageContext.request.contextPath}/bidorder/BidOrder.do?action=getAll'>後台訂單列表</a>









<table id="table-1">
   <tr><td><h3>IBM BidOrder: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM BidOrder: Home</p>

<h3>資料查詢:</h3>



<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/bidorder/listAllBidOrder.jsp'>List</a> all Members.  <br><br></li>
  

<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" >
        <b>輸入訂單編號 (如1):</b>
        <input type="text" name="bidOrderId" value="${param.bidOrderId}"><font color=red>${errorMsgs.bidOrderId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</li>

<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.gordon.service.MembersServiceImpl" />


<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" >
        <b>選擇會員姓名:</b>
        <input type="text" name="mbrName" value="${param.mbrName}"><font color=red>${errorMsgs.mbrName}</font>

        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</li>


</ul>



<iframe width="560" height="315" src="https://www.youtube.com/embed/jrIR_tHnf70?si=UUh5VAHJNh3KaFUc&playlist=jrIR_tHnf70&loop=1&autoplay=1" title="YouTube video player" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
  </body>
</html>