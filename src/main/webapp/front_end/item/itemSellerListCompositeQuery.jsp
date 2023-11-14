<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seller Item List</title>
    
    <style>
        *{
            box-sizing: border-box;
        }
        main.main{
            border: 1px solid black;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            background-color: rgb(224, 224, 224);

        }
        main.main h2{
            margin-top: 40px;
            margin-bottom: 3px;
        }
        table{
            /* border: 2px solid rgb(224, 224, 224); */
            width: 80%;
            height: 600px;
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
        }
        th,td{
            text-align: center;
            padding: 5px;
            width: 90px;
        }
        tr{
            height: 50px;
            /* box-shadow: 5px 2px 5px rgb(208, 208, 208); */
            background-color: rgb(255, 255, 255);
            margin: 5px 0px;
            display: flex;
		    justify-content: center;
		    align-items: center;
        }
        tr.tr_data:hover{
            background-color: white;
            box-shadow: 0px 0px 7px 4px rgba(181, 181, 181, 0.8);

        }
        div a{
            padding: 5px;
            color: white;
            text-decoration: none;
            color:darkblue;
            font-weight: bolder;
            margin: 2px 5px;
        }
        div.page_area{
            /* border: 1px solid red; */
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
            color:rgb(48, 87, 184);
            font-weight: 550;
            border: 1px solid rgb(255, 255, 255);
            padding: 2px;
            margin: 4px;
            border-radius: 50%;
            background-color: darkblue;
            color: white;
        }
        input.input_submit{
            background-color: darkblue;
            color: white;
            border: 0px;
            padding: 5px;
            width: 50px;
            border-radius: 5px;
            
        }
        input.input_submit:hover{
            cursor: pointer;
        }
		img {
			width:50%;
			height:90%;
		}
		.text_color{
			color:#CD5C5C;
		}
	</style>
	
</head>
<body>
    <main class="main">
        <h2>商品列表</h2>
        
            <!-- <c:if test="${itemPageQty > 0}"> -->
                
            <!-- </c:if> -->
        <table>
            <tr>
                <th>圖片</th>
                <th>商品名稱</th>
                <th>商品價格</th>
                <th>新舊程度</th>
                <th>尺寸</th>
                <th>商品狀態</th>
                <th>數量</th>
                <th>修改</th>
            </tr>

            
            <!-- <c:forEach var="item" items="${itemList}"> -->
            
                <tr class="tr_data">
                    <td class="img"><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="product" ></td>
                    <td>${item.itemName}</td>
                    <td>${item.price}</td>
                    <td name="grade">${item.grade}</td>
                    <td name="size">${item.size}</td>
                    <td name="itemStatus">${item.itemStatus}</td>
                    <td name="price">${item.price}</td>
                    
                    
                    <td>
                    <form method="post" action="${pageContext.request.contextPath}/Item/Update">
                        <input class="input_submit" type="submit" value="修改">
                        <input type="hidden" name="itemId"  value="${item.itemId}">
                        <input type="hidden" name="getOneForUpdate"	value="getOne">
                    </form>
                    </td>
                    
                </tr>
            <!-- </c:forEach> -->
            
<!--             <tr class="tr_data"> -->
<%--                 <td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="product" ></td> --%>
<!--                 <td>123</td> -->
<!--                 <td>123</td> -->
<!--                 <td name="grade">2</td> -->
<!--                 <td name="size">3</td> -->
<!--                 <td name="size">3</td> -->
<!--                 <td name="size">2</td> -->
                
<!--                 <td> -->
<%--                 <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/Item/Update"> --%>
<!--                     <input class="input_submit" type="submit" value="修改"> -->
<%--                     <input type="hidden" name="itemId"  value="${item.itemId}"> --%>
<!--                     <input type="hidden" name="getOneForUpdate"	value="getOne"> -->
<!--                 </FORM> -->
<!--                 </td> -->
                
<!--             </tr> -->
<!--             <tr class="tr_data"> -->
<%--                 <td><img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="product" ></td> --%>
<!--                 <td>123</td> -->
<!--                 <td>123</td> -->
<!--                 <td name="grade">2</td> -->
<!--                 <td name="size">3</td> -->
<!--                 <td name="size">3</td> -->
<!--                 <td name="size">2</td> -->
                
<!--                 <td> -->
<%--                 <FORM METHOD="post" ACTION="${pageContext.request.contextPath}/Item/Update"> --%>
<!--                     <input class="input_submit" type="submit" value="修改"> -->
<%--                     <input type="hidden" name="itemId"  value="${item.itemId}"> --%>
<!--                     <input type="hidden" name="getOneForUpdate"	value="getOne"> -->
<!--                 </FORM> -->
<!--                 </td> -->
                
<!--             </tr> -->
            
        </table>  
        <div class="page_area">
            <div class="pagination">
                <c:if test="${pageNow > 1}">
                    <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=1"><<</a>
                </c:if>
                <c:if test="${pageNow - 1 != 0}">
                    <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow - 1}"><</a>
                </c:if>

                <span class="page_now">${pageNow}</span>

                <c:if test="${pageNow + 1 <= itemPageQty}">
                    <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${pageNow + 1}">></a>
                </c:if>
                <c:if test="${pageNow != itemPageQty}">
                    <a href="${pageContext.request.contextPath}/Item/search?choice=searchCondition&page=${itemPageQty}">>></a>
                </c:if>
            </div>
            <c:if test="${itemPageQty > 0}">
            	<span>共${itemPageQty}頁</span>
            </c:if>
        </div>
        
    </main>
	
	<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>	
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
			});
			
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
			});
			
			$("td[name='itemStatus']").each(function () {
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
			
		});
		
	
	</script>
	
</body>
</html>