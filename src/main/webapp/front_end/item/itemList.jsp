<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>itemList</title>
<style>
        * {
            box-sizing: border-box;
        }

        body {
            margin: 0;
            min-height:100vh;
            height:100%; 
        }

        html {
            --header-height: 60px;
            --aside-width: 240px;
            min-height:100vh;
            height:100%;
        }

        main.main {
            border: 1px solid red;
            width: calc(100% - var(--aside-width));
            margin-left: var(--aside-width);
            min-height: calc(100vh - var(--header-height));
            padding: 20px;
            height:100%; 
        }

        main.main ul.itemList {
            border: 1px solid green;
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            flex-wrap: wrap;
            height:98%;
            
        }

        main.main ul.itemList > li {
        	border:1px solid blue;
            border: 1px solid black;
            background-color: snow; 
            width: calc(25% - 20px); /* 25% of the container width minus margin */
            margin-right: 20px;
            padding: 10px;
            position: relative;
            display: flex;
            flex-direction: column;
            height: calc(50% - 20px);;
        }

        main.main ul.itemList > li:nth-child(4n) {
            margin-right: 0;
        }

        main.main ul.itemList > li a {
            display: block;
            border: 1px solid wheat;
            text-decoration: none;
            width: 100%;
            height: 100%;
        }

        main.main ul.itemList > li a div.imgBlock {
            width:100%;
            height:calc(100% - 30px);
             background-color: white; 
            font-size: 0;
            text-align: center;
            position:relative;
        }
        
        div.item_info{
        	display:flex;
        	justify-content: space-between;
        	padding:2px 4px;
        	align-items: baseline;
        	color:black;
        }
        div.down_area{
        	height:32px;
        	border:1px solid black;
        	text-align:center; 
        }
        div.down_area span{
        	font-size:20px;
        	margin:0 4px;
        }
		a.pagearea span{
			font-size:28px;
		}


        img {
            max-width: 100%;
            max-height: 100%;
        }





</style>

</head>
<body>
    
    <main class="main">
        <ul class="itemList">
        <c:forEach var="item" items="${itemList}">
            <li>
               <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                <div class="imgBlock">
                    <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="商品圖片">
                </div>
                <div class="item_info">
                	<span>${item.itemName}</span><span class="price">${item.price}</span>               
                </div>               
               </a> 
            </li>
         </c:forEach>


        </ul>
	<div class="down_area">      
	    <c:if test="${pageNow > 1}">
			<a class="pagearea" href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=1"><span>&#171;</span></a>&nbsp;
		</c:if>
		<c:if test="${pageNow - 1 != 0}">
			<a class="pagearea" href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${pageNow - 1}"><span>&#8249;</span></a>&nbsp;
		</c:if>
		<span>${pageNow}</span>
		<c:if test="${pageNow + 1 <= itemPageQty}">
			<a class="pagearea" href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${pageNow + 1}"><span>&#8250;</span></a>&nbsp;
		</c:if>
		<c:if test="${pageNow != itemPageQty}">
			<a class="pagearea" href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${itemPageQty}"><span>&#187;</span></a>&nbsp;
		</c:if>
	</div>  
    </main>
</body>
</html>