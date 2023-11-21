<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員虛擬錢包申請查詢</title>
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
        main.main_container{
            border: 1px solid black;
            min-height: 450px;
/*             margin: 50px; */
            display: flex;
            align-items: center;
            flex-direction: column;
            background-color: whitesmoke;
        }
        
         main.main_container h3{
         	margin-top:20px;
         }

        main.main_container div.search_date{ 
            display: flex; 
            flex-direction: row; 
            align-items: center; 
            justify-content:center; 
			width: 55%;
		    height: 45px;
           	margin: 20px 0px; 
	    } 

	    div.search_date > div{ 
            border: 1px solid gray; 
            font-size: 20px; 
            width: 100%; 
            height: 100%; 
            flex: 1; 
            display: flex; 
            align-items: center; 
            justify-content:center; 
	            
	    } 

        div.search_date > div:hover{
            background-color: #f9edf2;
            cursor: pointer;
            box-shadow: inset -2px -2px 8px rgba(0, 0, 0, 0.2)
        }

        .clickCondition{
            box-shadow: inset -2px -2px 8px rgba(0, 0, 0, 0.2);
            background-color: #f9edf2;

        }

         div.search_date > div:nth-child(4){ 
             border-radius: 0 10px 10px 0; 
         } 
         div.search_date > div:nth-child(1){ 
             border-radius: 10px 0 0 10px; 
         } 

        div.search_list{
            /* border: 1px solid blue; */
            width: 80%;
            margin-top: 20px;
            height: 100%;

        }

        div.search_list table{
            border-collapse: collapse; 
            /* border: 1px solid red; */
            width: 100%;
            height: 100%;


        }
        
        div.search_list table thead{
            border-bottom: 1px solid black;
            /* margin-bottom: 10px; */
        }

        div.search_list table tr{
            width: 100%;
            display: flex;
            align-items: center;
            justify-content:center;
            /* margin-bottom: 15px; */
            
        }

        div.search_list table tr th, td{
            /* border: 1px solid blueviolet; */
            width: 100%;
            height: 100%;
            text-align: center;
            font-size: 18px;
            padding: 12px 0px;
        }

        div.search_list table tbody tr{
            margin: 10px 0px;
            background-color: white;

        }
        div.search_list table tbody tr:hover{
            box-shadow: 5px 5px 10px rgba(219, 219, 219, 0.829);

        }
        .negative{
        	color:red;
        }
    </style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">

</head>
<body>
	<div class="headerHTML"></div>

    <main class="main_container">
        <h3>會員虛擬錢包申請查詢</h3>
        <div class="search_date">
            <div class="search clickCondition">
                <span>全部</span>
            </div>
            <div class="search">
                <span>審核中</span>
            </div>
            <div class="search">
                <span>已通過</span>
            </div>
            <div class="search">
                <span>未通過</span>
            </div>
        </div>
        <div class="search_list">
            <table>
                <thead>
                    <tr>
                        <th>編號</th>
                        <th>提款金額</th>
                        <th>匯入帳號</th>
                        <th>申請狀態</th>
                        <th>申請日期</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="withdrawRequest" items="${WRList}" varStatus="loop">
                    <tr>
                        <td>${loop.index+1}</td>
                        <td class="amount">${withdrawRequest.amount}</td>
                        <td class="account">${withdrawRequest.mbrAccount}</td>
                        <td class="status">${withdrawRequest.reqStatus}</td>
                        <td class="dateTime">${withdrawRequest.reqDate}</td>
                    </tr>
                   </c:forEach>
                </tbody>
            </table>
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
	</script>
    
    <script>
        $(".search").click(function(){
            $(".search").removeClass("clickCondition");
            $(this).addClass("clickCondition");
            var clickCount = 0;
            
            //判斷點擊狀態，是否顯示內容
	        var searchCondition = $(this).find("span").text();

	        $(".status").each(function(){
	           var statusObject = $(this).text();
	           if (searchCondition === "全部" || statusObject === searchCondition || (searchCondition === "審核中" && statusObject === "審核中") || (searchCondition === "已通過" && statusObject === "已通過") || (searchCondition === "未通過" && statusObject === "未通過")) {
	        	   console.log(111);
	        	   clickCount++;
	        	   $(this).closest("tr").show();
	        	   $(this).closest("tr").find("td:first").text(clickCount);
	           } else {
	               $(this).closest("tr").hide();
	           }             
	       });
        });
    </script>
    <script>
    var total = 0;
    $(document).ready(function() {
		$(".status").each(function () {
			let status = $(this).text();
			switch(status){
				case "0":
					$(this).text("審核中");
				break;
				case "1":
					$(this).text("已通過");
				break;
				case "2":
					$(this).text("未通過");
				break;
				}
			})


	
    	//日期時間顯示格式
    	$(".dateTime").each(function(){
    		let time = $(this).text().replace(/\.0$/, '');
    		$(this).text(time);
    	})
    	
		
    })
    </script>
</body>
</html>