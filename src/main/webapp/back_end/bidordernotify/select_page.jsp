<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM BidOrderRatingImage: Home</title>

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

<table id="table-1">
   <tr><td><h3>IBM BidOrderRatingImage: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM BidOrderRatingImage: Home</p>

<h3>資料查詢:</h3>



<ul>
  <li><a href='<%=request.getContextPath()%>/back_end/bidorderratingimage/listAllBidOrderRatingImage.jsp'>List</a> all BidOrderRatingImage.  <br><br></li>
  
  <li><a href='<%=request.getContextPath()%>/back_end/bidorderratingimage/addBidOrderRatingImage.jsp'>List</a> 新增 BidOrderRatingImage.  <br><br></li>

<li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorderratingimage/BidOrderRatingImage.do" >
        <b>輸入會員編號 (如1):</b>
        <input type="text" name="imageId" value="${param.imageId}"><font color=red>${errorMsgs.imageId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出">
    </FORM>
</li>

</ul>



<iframe width="560" height="315" src="https://www.youtube.com/embed/jrIR_tHnf70?si=UUh5VAHJNh3KaFUc&playlist=jrIR_tHnf70&loop=1&autoplay=1" title="YouTube video player" frameborder="0" allow="accelerometer; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
  </body>
</html>