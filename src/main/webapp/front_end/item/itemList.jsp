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
            height:100%;
            
        }

        main.main ul.itemList > li {
        	border:1px solid blue;
            border: 1px solid black;
            background-color: #C0C0C0;
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
            height:calc(100% - 18px);
            background-color: black;
            font-size: 0;
            text-align: center;
            position:relative;
        }

        main.main ul.itemList > li a span{
            color: black;
        }



        main.main ul.itemList > li a span.price{
            position: absolute;
            right: 10px;
        }

        img {
            max-width: 100%;
            max-height: 100%;
        }

        span {
            text-align: center;
            height:30px;
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
                <span>${item.itemName}</span><span class="price">${item.price}</span>
               </a> 
            </li>
         </c:forEach>
<!--             <li> -->
<!--                 <a href=""> -->
<!--                  <div class="imgBlock"> -->
<!--                      <img src="https://dummyimage.com/500x600/fcfcfc/0011ff" alt=""> -->
<!--                  </div> -->
<%--                  <span>${item.itemName}</span><span class="price">${item.price}</span> --%>
<!--                 </a>  -->
<!--              </li> -->
<!--              <li> -->
<!--                 <a href=""> -->
<!--                  <div class="imgBlock"> -->
<!--                      <img src="https://dummyimage.com/500x600/fcfcfc/0011ff" alt=""> -->
<!--                  </div> -->
<%--                  <span>${item.itemName}</span><span class="price">${item.price}</span> --%>
<!--                 </a>  -->
<!--              </li> -->
<!--              <li> -->
<!--                 <a href=""> -->
<!--                  <div class="imgBlock"> -->
<!--                      <img src="https://dummyimage.com/500x600/fcfcfc/0011ff" alt=""> -->
<!--                  </div> -->
<%--                  <span>${item.itemName}</span><span class="price">${item.price}</span> --%>
<!--                 </a>  -->
<!--              </li> -->
  
<!--              <li> -->
<!--                 <a href=""> -->
<!--                  <div class="imgBlock"> -->
<!--                      <img src="https://dummyimage.com/500x600/fcfcfc/0011ff" alt=""> -->
<!--                  </div> -->
<!--                  <span>商品名</span><span class="price">(價錢)</span> -->
<!--                 </a>  -->
<!--              </li> -->
<!--              <li> -->
<!--                 <a href=""> -->
<!--                  <div class="imgBlock"> -->
<!--                      <img src="https://dummyimage.com/500x600/fcfcfc/0011ff" alt=""> -->
<!--                  </div> -->
<!--                  <span>商品名</span><span class="price">(價錢)</span> -->
<!--                 </a>  -->
<!--              </li> -->


        </ul>
        
        <c:if test="${pageNow > 1}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=1">至第一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow - 1 != 0}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${pageNow - 1}">上一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow + 1 <= itemPageQty}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${pageNow + 1}">下一頁</a>&nbsp;
	</c:if>
	<c:if test="${pageNow != itemPageQty}">
		<a href="${pageContext.request.contextPath}/Item/search?choice=getAllList&page=${itemPageQty}">至最後一頁</a>&nbsp;
	</c:if>
    </main>
</body>
</html>