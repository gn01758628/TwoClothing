<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>會員點數異動查詢</title>
    <style>
        *{
            box-sizing: border-box;
        }
        main.main_container{
            border: 1px solid black;
            height: 100%;
            margin: 50px;
            display: flex;
            align-items: center;
            flex-direction: column;
            background-color: whitesmoke;
        }
        main.main_container div.bh_number{
            /* border: 1px solid green; */
            font-size: 28px;
            margin-top: 10px;
            margin-bottom: 20px;
            color:white;
            width: 200px;
            font-weight:bold;
            display: flex;
            justify-content: center;
            align-items: center;
            border-radius: 10px;
            background-color: orangered;
            padding: 5px 0;
            
        }
        main.main_container div.bh_number span {
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        main.main_container div.bh_number span.number {
            text-align: center;
            border-radius: 10px;
            background-color: white;
            color: orangered;
            margin-left: 15px;
            padding: 0 10px;
        }
        div.search_date{

            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content:center;
            width: 80%;
            height: 60px;
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
            background-color: lightgray;
            cursor: pointer;
            box-shadow: inset -5px -5px 10px rgba(0, 0, 0, 0.2)
        }

        .clickCondition{
            box-shadow: inset -5px -5px 10px rgba(0, 0, 0, 0.2);
            background-color: lightgray;

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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
    <main class="main_container">
        <h1>會員點數異動查詢</h1>
        <div class="bh_number">
            <span class="text">餘額：<span class="number">$200</span></span>
            
        </div>
        <div class="search_date">
            <div class="search clickCondition">
                <span>全部</span>
            </div>
            <div class="search">
                <span>近一週</span>
            </div>
            <div class="search">
                <span>近一個月</span>
            </div>
            <div class="search">
                <span>近三個月</span>
            </div>
        </div>
        <div class="search_list">
            <table>
                <thead>
                    <tr>
                        <th>編號</th>
                        <th>異動數值</th>
                        <th>異動日期</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="pointHistory" items="${PHList}" varStatus="loop">
                    <tr>
                        <td>${loop.index+1}</td>
                        <td class="value">${pointHistory.changeValue}</td>
                        <td class="dateTime">${pointHistory.changeDate}</td>
                    </tr>
                   </c:forEach>
                </tbody>
            </table>
        </div>

    </main>
    <script>
        $(".search").click(function(){
            $(".search").removeClass("clickCondition");
            $(this).addClass("clickCondition");
			
            
            //判斷點擊時間，是否顯示內容
	        var searchCondition = $(this).find("span").text();
	        var currentTime = new Date().getTime();

	        $(".dateTime").each(function(){
	           var dateObject = new Date($(this).text());
	           var dateTimeMilliseconds = dateObject.getTime();
	
	           if (searchCondition === "全部" || 
	               ((searchCondition === "近一週") && ((currentTime - dateTimeMilliseconds) <= 7 * 24 * 60 * 60 * 1000)) ||
	               (searchCondition === "近一個月" && (currentTime - dateTimeMilliseconds) <= 30 * 24 * 60 * 60 * 1000) ||
	               (searchCondition === "近三個月" && (currentTime - dateTimeMilliseconds) <= 90 * 24 * 60 * 60 * 1000)) {
	               $(this).closest("tr").show();   
	           } else {
	                 $(this).closest("tr").hide();
	           }             
	       });
        });
    </script>
    <script>
    var total = 0;
    $(document).ready(function() {
    	//計算點數總額
    	$(".value").each(function(){
    		console.log("2");
    		let minus = parseInt($(this).text())
    		total+=minus;
    		$(".number").text("$"+total);
    	if (minus < 0) {
            $(this).addClass("negative");
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