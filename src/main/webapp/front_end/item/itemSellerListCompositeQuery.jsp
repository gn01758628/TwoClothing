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
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
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
	            <td name="grade">${item.grade}</td>
	            <td name="size">${item.size}</td>
	            <td>${item.detail}</td>
	            
	            <td>
				  <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/Item/Update" style="margin-bottom: 0px;">
				     <input type="submit" value="修改">
				     <input type="hidden" name="itemId"  value="${item.itemId}">
				     <input type="hidden" name="getOneForUpdate"	value="getOne">
				  </FORM>
				</td>
				
	        </tr>
		</c:forEach>
		
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
				     <input type="hidden" name="getOneForUpdate"	value="getOne">
				  </FORM>
				</td>
				
	         </tr>
		
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
	
	
	<script>
	
		$(document).ready(function() {
			$("td[name='size']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("XS(含以下)");
					break;
					case "1":
						$(this).text("S");
					break;
					case "2":
						$(this).text("M");
					break;
					case "3":
						$(this).text("L");
					break;
					case "4":
						$(this).text("XL");
					break;
					case "5":
						$(this).text("2XL");
					break;
					case "6":
						$(this).text("3XL");
					break;
					case "7":
						$(this).text("4XL含以上");
					break;
					case "":
						$(this).text("其他");
					break;
				}
			})
		})
		
		$(document).ready(function() {
			$("td[name='grade']").each(function () {
				console.log($("td[name='grade']"));
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("全新");
					break;
					case "1":
						$(this).text("9成5新");
					break;
					case "2":
						$(this).text("9成新");
					break;
					case "3":
						$(this).text("8成新");
					break;
					case "4":
						$(this).text("5成新");
					break;
					case "5":
						$(this).text("需要修補");
					break;
		
				}
			})
		})
		$(document).ready(function() {
			$("select[name='itemStatus']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("上架");
					break;
					case "1":
						$(this).text("下架");
					break;
					case "2":
						$(this).text("刪除");
					break;
				}
			})
		})
	
	</script>
	
</body>
</html>