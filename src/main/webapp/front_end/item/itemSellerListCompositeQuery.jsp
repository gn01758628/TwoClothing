<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>List Item Seller</title>
    
    <style>
		img {
			width:80px;
			height:80px;
		}
	</style>
</head>
<body>
	<h1>商品列表</h1>
		<c:if test="${itemPageQty > 0}">
  			<b><font color=red>第${pageNow}/${itemPageQty}頁</font></b>
		</c:if>
    <table>
        <tr>
        	<th>圖片</th>
            <th>商品名稱</th>
            <th>商品價格</th>
            <th>新舊程度</th>
            <th>尺寸</th>
            <th>描述</th>
        </tr>

        
        <c:forEach var="item" items="${itemList}">
        
	        <tr>
	        	<td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="商品主圖片" ></td>
	            <td>${item.itemName}</td>
	            <td>${item.price}</td>
	            <td>${item.grade}</td>
	            <td>${item.size}</td>
	            <td>${item.detail}</td>
	            
	            <td>
				  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/Item/Update" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="itemId"  value="${item.itemId}">
				     <input type="hidden" name="choice"	value="getOne">
				  </FORM>
				</td>
				
	        </tr>
		</c:forEach>
      </table>  
        
    	<c:if test="${pageNow > 1}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow - 1 != 0}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow + 1 <= itemPageQty}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow != itemPageQty}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${itemPageQty}">至最後一頁</a>&nbsp;
	</c:if>
	<br>
	
</body>
</html>