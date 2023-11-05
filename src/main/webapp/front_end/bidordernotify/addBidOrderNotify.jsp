<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>addBidOrderRatingImage.jsp資料新增 - addBidOrderRatingImage.jsp</title>

<style>
  table#table-1 {
    width: 450px;
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
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
	 <h3>新增addBidOrderRatingImage.jsp資料 - addBidOrderRatingImage.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/MemberCentre.jsp"><img src="images/login2.png" width="100" height="102" border="0">回首頁</a></h4>
		</td></tr>
</table>

<h3>資料新增:</h3>



<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidordernotify/BidOrderNotify.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員編號:</td>
		<td>${user.mbrId}</td>
	</tr>
	<tr>
		<td>競標商品訂單編號:</td>
		<td>${param.bidOrderId}</td>
		<td><input type="TEXT" name="bidOrderId"  value="${param.bidOrderId}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.bidOrderId}</td>
		
	</tr>
  <tr>
    <td>上傳圖片:</td>
    <td>
        <input type="file" id="image" name="image" accept="image/*" onchange="previewImage(this);" value="${param.image}">
    </td>
</tr>
<tr>
    <td>預覽圖片:</td>
    <td>
        <img id="imagePreview" src="" alt="預覽圖片" style="max-width: 200px; max-height: 200px;">
    </td>
</tr>
</table>

<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="bidOrderId" value="${param.bidOrderId}">
<input type="submit" value="送出新增"></FORM>
</body>

<script>
function previewImage(input) {
    var imagePreview = document.getElementById("imagePreview");
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function(e) {
            imagePreview.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        imagePreview.src = "";
    }
}
</script>
</html>