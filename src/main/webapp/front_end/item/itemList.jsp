<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>itemList</title>
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
/*         * { */
/*             box-sizing: border-box; */
/*         } */

        body { 
             margin: 0; 
             min-height:100vh; 
             height:100%; 
/*              background-color: #f9edf2 !important;  */
         } 

         html { 
             --header-height: 60px; 
             --aside-width: 240px; 
             min-height:100vh; 
             height:100%; 
         }
         div#content-wrap{
			display: flex;
			justify-content: space-between;
			background-color: #f9edf2;
         
         }
         
         div#content-wrap aside.itemSearch{
         	width: 200px;
         	border-right: 1px solid #561729;
    		padding: 20px;
    		background-color: #f9edf2;
    		height:725px;
    		position: sticky;
		    left: 10px;
		    top: 10px;
         }
         div#content-wrap aside.itemSearch a{
         	text-decoration: none;
         	width: 100%;
         }
/*          標籤第一層 */
          div.accordion-item{ 
/*          	height: 500px; */
/* 		    display: flex; */
/* 		    justify-content: flex-start; */
/* 		    align-items: center; */
/* 		    flex-direction: column; */
/* 		    border: 1px solid; */
 		    background-color: #f9edf2; 
          } 
/*          標籤第二層 */
         div.accordion-body{
         	display: flex;
		    flex-direction: column;
		    justify-content: center;
		    align-items: stretch;
		    padding:11px 0px;
         }
         
         div.accordion-body div.hovered{
         	border-radius: 10px;
		    padding: 0 7px;
		    height: 37px;
/* 		    border: 1px solid; */
			display: flex;
		    justify-content: flex-start;
		    align-items: center;
         }
         
         div.accordion-item a{
         	color:#561729;
         }
         
         h5.accordion-header{
         	border-radius: 10px;
            padding: 7px;
         	height: 49px;
         	margin: 5px 0px;
         	font-size: 18px;
         }
        h5.accordion-header:hover{
        	border:0px;
        	border:1px solid #561729; 
			
		}
         
         button.accordion-button{
         	color:#561729;
         }
         
         div.accordion-body div.hovered:hover{
         	border:1px solid #561729;
         }
         
        main.main_itemList {
/*             border: 1px solid red; */
            width: calc(100% - 200px);
/*             margin-left: var(--aside-width); */
            min-height: calc(100vh - var(--header-height));
            padding: 20px;
            height: auto;
		    min-height: 1700px;
		    position: relative;
		    background-color: #f9edf2;
        }

        main.main_itemList ul.itemList {
/*             border: 1px solid green; */
            list-style: none;
            margin: 0;
            padding: 0;
            display: flex;
            flex-wrap: wrap;
            height: auto;
            
        }

        main.main_itemList ul.itemList > li {
/*         	border:1px solid blue; */
            border: 1px solid black;
            background-color: snow; 
            width: calc(25% - 20px); /* 25% of the container width minus margin */
            margin-right: 20px;
            padding: 10px;
            position: relative;
            display: flex;
            flex-direction: column;
/*             height: calc(50% - 20px);; */
			height:296px;
			margin-bottom: 20px;
        }

        main.main_itemList ul.itemList > li:nth-child(4n) {
            margin-right: 0;
        }

        main.main_itemList ul.itemList > li a {
            display: block;
/*             border: 1px solid wheat; */
            text-decoration: none;
/*             width: 100%; */
            height: 100%;
        }

        main.main_itemList ul.itemList > li a div.imgBlock {
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

        img#item_id {
            max-width: 100%;
            max-height: 100%;
        }
        
/*         頁數 */
        div#pagination{
/*         	border:1px solid black; */
        	display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-top: 50px;
		    position: absolute;
		    bottom: 10px;
		    width: 100%;
        }
    
    	div#pagination ul.pagination-list{
	        list-style-type: none;
		    margin: 0px 20px;
		    padding: 0px;
    	
    	}
    	div#pagination ul.pagination-list li{
		    padding: 5px;
/* 		    border: 1px solid black; */
		    border-radius: 6px;
		    background-color: #f9edf2;
		    width: 40px;
		    height: 40px;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    border:1px solid #561729;
    		
    	}
    	div#pagination ul.pagination-list li a{
    		text-decoration: none;
		    color:#561729 !important;
		    
    	}
    	div#pagination button#prev-page,button#next-page{
    		background-color: #f9edf2;
    		height:40px;
    		border:1px solid #561729;
    		color:#561729;
    		border-radius: 6px;
    		
    	}
	</style>
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
	<div class="headerHTML"></div>
    
   	<aside class="itemSearch"></aside>
    <main class="main_itemList">
        <ul class="itemList">
        <c:forEach var="item" items="${itemList}">
            <li>
               <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                <div class="imgBlock">
                    <img id="item_id" src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" alt="商品圖片">
                </div>
                <div class="item_info">
                	<span>${item.itemName}</span><span class="price">${item.price}</span>               
                </div>               
               </a> 
            </li>
         </c:forEach>
        </ul>

		<div id="pagination">
		  <button id="prev-page">Previous</button>
		  <ul class="pagination-list"></ul>
		  <button id="next-page">Next</button>
		</div>
		
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
	    $(".itemSearch").load("${pageContext.request.contextPath}/front_end/itemsearch/itemSearch.html");
	</script>
	<script src="${pageContext.request.contextPath}/js/chijung/itemSearch.js"></script>
	
	<script>
    var myList = [
        <c:forEach var="item" items="${itemList}" varStatus="loop">
            {
                itemId: ${item.itemId},
                itemName: '${item.itemName}',
                price: ${item.price},
                tagId: ${item.tagId}
            }<c:if test="${!loop.last}">,</c:if>
        </c:forEach>
    ];
	

		function setupPagination(filteredData) {
	 	  //每頁顯示幾項商品
		  var itemsPerPage = 20;
		  var itemList = $('.itemList');
		  var pagination = $('#pagination .pagination-list');
		  //幾個li商品，獲取list長度
		  var lengthOfList = filteredData.length; 
		  //幾頁
		  var pageCount = Math.ceil(lengthOfList / itemsPerPage);
		  var currentPage= 1;
		  // 生成分頁按鈕
		  function generatePagination() {
		    pagination.empty(); // 清空舊的分頁按鈕
		    pagination.append('<li><a href="#" data-page="' + currentPage + '">' + currentPage + '</a></li>');

		  }
		  // 顯示指定頁碼的內容
		  function showPage(page) {
		    var startIndex = (page - 1) * itemsPerPage;
		    var endIndex = startIndex + itemsPerPage;
// 		    清空原li
		    $('.itemList').empty(); 
		    var contextPath = '${pageContext.request.contextPath}'
		    for (var i = startIndex; i < endIndex && i < filteredData.length; i++) {
		        var itemId = filteredData[i].itemId;
		        var itemName = filteredData[i].itemName;
		        var itemPrice = filteredData[i].price;
        

		        var newItemHTML = '<li>' +
		            '<a href="' + contextPath + '/Itemfront/itemlist?goto=' + itemId + '">' +
		            '<div class="imgBlock">' +
		            '<img id="item_id" src="' + contextPath + '/ReadItemIMG/item?id=' + itemId + '&position=1" alt="商品圖片">' +
		            '</div>' +
		            '<div class="item_info">' +
		            '<span>' + itemName + '</span><span class="price">' + itemPrice + '</span>' +
		            '</div>' +
		            '</a>' +
		            '</li>';

		        // 將新的列表項目添加到列表容器中
		        $('.itemList').append(newItemHTML);
		    }
		    
		    
		    $('.itemList').find('li').slice(startIndex, endIndex).show();
		    pagination.find('a').removeClass('active');
		    pagination.find('a[data-page="' + page + '"]').addClass('active');
		    currentPage = page;
		    generatePagination();
		    updateNavigationButtons();
		  }
		  function updateNavigationButtons() {
		    // 如果是第一頁，禁用「上一頁」按鈕
		    if (currentPage === 1) {
		      $('#prev-page').prop('disabled', true);
		    } else {
		      $('#prev-page').prop('disabled', false);
		    }

		    // 如果是最末頁，禁用「下一頁」按鈕
		    if (currentPage === pageCount) {
		      $('#next-page').prop('disabled', true);
		    } else {
		      $('#next-page').prop('disabled', false);
		    }
		  }
	
		  // 點擊分頁按鈕切換頁面
		  $('#next-page').on('click', function(e) {
		    e.preventDefault();
		    if (currentPage < pageCount) {
		        currentPage++; 
		        showPage(currentPage); 
		    }
		    
		    window.scrollTo({
		        top: 0,
		        behavior: 'smooth' 
		    });
		      
		  });
		  $('#prev-page').on('click', function(e) {
				e.preventDefault();
			    if (currentPage > 1) {
			      showPage(currentPage - 1);
			    }
			    window.scrollTo({
			        top: 0,
			        behavior: 'smooth' 
			    });
		  });
	
		  // 初始化
		  generatePagination(); 
		  showPage(currentPage);
		  updateNavigationButtons(); 
			
		}

	$(document).ready(function() {
		setupPagination(myList);
	    // 使用 jQuery 綁定事件
	    $('.itemSearch').on('click', 'a', function (event) {
	        event.preventDefault();
	        clickedIdSubsList = [];
	        clickedIdParentsList = [];

	        let clickedId = $(this).attr('id');
	        clickedIdSubsList.push(clickedId);
	        findAccordionBody(clickedId);

	        clickedIdParentsList.unshift(clickedId);
	        let accordionBodyId = $(this).closest('.accordion-body').attr('id');
	        if (accordionBodyId) {
	            $(this).parents('.accordion-body').each(function () {
	                clickedIdParentsList.unshift(this.id.replace('c', ''));
	            });
	        }
	        console.log("clickedIdSubsList:"+clickedIdSubsList);
	        console.log("clickedIdParentsList:"+clickedIdParentsList);
//	        let filteredData = filterItemListByIds(clickedIdSubsList, itemList);
	        let filteredData = filterItemListByIds(clickedIdSubsList, myList);
	        // 遍歷 filteredData 並印出每個對象的內容
			filteredData.forEach(function (item) {
			    console.log("Tag ID: " + item.tagId + ", Other Properties: " + JSON.stringify(item));
			});
			setupPagination(filteredData);
	    
	    });

	})
	
	</script>
</body>
</html>
