<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Item List</title>
    
    <!--頁籤icon-->
    <link rel="icon" href="${pageContext.request.contextPath}/images/Mainicon.png" type="image/png">
    <!--bootstrap5 css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
    <!-- google fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+TC:wght@500&display=swap" rel="stylesheet">
    <style>
        *:not([class^="fa-"]) {
            font-family: 'Noto Sans TC', sans-serif !important;
        }
    </style>
    <!-- Font Awesome -->
    <script src="https://kit.fontawesome.com/716afdf889.js" crossorigin="anonymous"></script>
    <!--Sweet Alert-->
    <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css" rel="stylesheet">
    
    <style>
        *{
            box-sizing: border-box;
        }
        main.main{
/*             border: 1px solid black; */
            width: 100%;
            height:500px;
            display: flex;
            justify-content: flex-start;
            align-items: center;
            flex-direction: column;
            background-color: rgb(224, 224, 224);
            min-height: calc(100vh - 309px);

        }
        main.main h3{
            margin-top: 40px;
            margin-bottom: 3px;
        }
        table{
            /* border: 2px solid rgb(224, 224, 224); */
            width: 80%;
            height: 100%;
            border-collapse: collapse;
            background-color: rgb(255, 255, 255);
            margin: 10px;
            border-radius: 20px;
        }
        tbody{
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 15px;
            position: relative;
            min-height: calc(100vh - 409px);
        }
        table th,td{
            text-align: center;
            padding: 5px;
            width: 10%;
        }
        table tr{
            height: 50px;
            /* box-shadow: 5px 2px 5px rgb(208, 208, 208); */
            background-color: rgb(255, 255, 255);
            margin: 10px 0px;
            display: flex;
		    justify-content: center;
		    align-items: center;
		    width: 100%;
        }
        tr.tr_data:hover{
            background-color: white;
            box-shadow: 0px 0px 7px 4px rgba(181, 181, 181, 0.8);

        }
        td.img{
        	display:flex;
        	justify-content: center;
		    align-items: center;
		    padding: 0px;
        }
/*         div a{ */
/*             padding: 5px; */
/*             color: white; */
/*             text-decoration: none; */
/*             color:darkblue; */
/*             font-weight: bolder; */
/*             margin: 2px 5px; */
/*         } */
      /*   div.page_area{ 
			border: 1px solid red; 
            display: flex; 
            justify-content: center; 
            flex-direction: column; 
            align-items: center;
            padding: 10px; 
         }
         div.page_area span{
             margin: 5px 0; 
             font-size: 13px;
             color: darkblue;
         }
         div.pagination{
             margin: 5px 0;

         } 

         div.pagination span.page_now{ 
             display: inline-block; 
             width: 25px; 
             height: 25px; 
             text-align: center; 
             font-size: 18px; 
             font-weight: 550; 
             border: 1px solid rgb(255, 255, 255); 
             padding: 2px; 
             margin: 4px; 
             border-radius: 50%; 
             background-color: darkblue; 
             color: white; 
         } */
         
        form input.input_submit{
            background-color: #561729;
            color: white;
            border: 1px solid #561729;
            padding: 5px;
            width: 50px;
            border-radius: 5px;
            
        }

        form input.input_submit:hover{
            cursor: pointer;
            background-color: #f9edf2;
         	color: #561729;
         	border-color:#561729;
        }
        td.tag_a{
        	width:125px;
        	position: absolute;
    		bottom: 10px;
        }
        a.updateOther{
		    background-color: #f9edf2;
		    color: #561729;
		    padding: 5px;
		    border-radius: 10px;
		    border: 2px solid #f9edf2;
		    text-decoration: none;
        }
        a.updateOther:hover{
        	cursor: pointer;	
        }
		img {
			height:50px;
		}
		.text_color{
			color:#CD5C5C;
		}
	</style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
	
</head>
<body>
	<div class="headerHTML"></div>

    <main class="main">
        <h3>商品列表</h3>
        <table>
            <tr>
                <th>圖片</th>
                <th>商品名稱</th>
                <th>商品價格</th>
                <th>新舊程度</th>
                <th>尺寸</th>
                <th>商品狀態</th>
                <th>數量</th>
                <th>類別</th>  
                <th>修改</th>
            </tr>

            
                <tr class="tr_data">
                    <td class="img"><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="product" >
                    <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=2" alt="product" ></td>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td class="grade">${item.grade}</td>
                    <td class="size">${item.size}</td>
                    <td class="itemStatus">${item.itemStatus}</td>
                    <td class="quantity">${item.quantity}</td>
                    <td class="tagId">${item.tagId}</td>
                    
                    
                    <td>
                    <form method="post" action="${pageContext.request.contextPath}/Item/Update">
                        <input class="input_submit" type="submit" value="修改">
                        <input type="hidden" name="itemId"  value="${item.itemId}">
                        <input type="hidden" name="getOneForUpdate"	value="getOne">
                    </form>
                    </td>
                    
                </tr>
                <tr>
	                <td class="tag_a">
			        	<a href="${pageContext.request.contextPath}/Item/item?choice=search" class="updateOther" type="button">修改其他商品</a>
			        </td>
		        </tr>
        </table>
<!--         <div class="page_area"> -->
<!--             <div class="pagination"> -->
<%--                 <c:if test="${pageNow > 1}"> --%>
<%--                     <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=1"><<</a> --%>
<%--                 </c:if> --%>
<%--                 <c:if test="${pageNow - 1 != 0}"> --%>
<%--                     <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow - 1}"><</a> --%>
<%--                 </c:if> --%>

<%--                 <span class="page_now">${pageNow}</span> --%>

<%--                 <c:if test="${pageNow + 1 <= itemPageQty}"> --%>
<%--                     <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow + 1}">></a> --%>
<%--                 </c:if> --%>
<%--                 <c:if test="${pageNow != itemPageQty}"> --%>
<%--                     <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${itemPageQty}">>></a> --%>
<%--                 </c:if> --%>
<!--             </div> -->
<%--             <c:if test="${itemPageQty > 0}"> --%>
<%--             	<span>共${itemPageQty}頁</span> --%>
<%--             </c:if> --%>
<!--         </div> -->
        
    </main>
	<div class="footerHTML"></div>
	
	<!--bootstrap5 js-->
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
	<!--jQuery-->
	<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
	<!--Sweet Alert-->
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.all.min.js"></script>
	<!--JS loader-->
	<script>
	    $(".headerHTML").load("${pageContext.request.contextPath}/headerHTML.html", function () {
	        // 保證headerHTML加載完才載入header.js
	        $.getScript("${pageContext.request.contextPath}/js/chengHan/header.js");
	    });
	
	    $(".footerHTML").load("${pageContext.request.contextPath}/footerHTML.html");
	</script>
	<script>
	
		$(document).ready(function() {
			$("td.size").each(function () {
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
			});
			
			$("td.grade").each(function () {
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
			});
			
			$("td.itemStatus").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("上架");
					break;
					case "1":
						$(this).text("下架");
						$(this).addClass("text_color");
					break;
					case "2":
						$(this).text("刪除");
						$(this).addClass("text_color");
					break;
				}
			});
			
			var tagList = ${jsonTag};
			$(".tagId").text(tagList.categoryName);

		
		});
		
	
	</script>
	
</body>
</html>