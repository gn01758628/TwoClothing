<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ include file="buyBidorderBanner.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>賣家評價</title>
    <style>
    	
        body {
            font-family: Arial, sans-serif;
            
        }
        .form-container {
            text-align: center;
            margin-top: 50px;
        }
        label {
            display: block;
            margin: 10px 0;
        }
        .stars {
            display: inline-block;
        }
        .star {
            font-size: 24px;
            cursor: pointer;
            color: #ccc;
            transition: color 0.2s;
        }
        .star:hover,
        .star.active {
            color: #ffcc00;
        }
        select, textarea, input[type="file"] {
            margin-bottom: 10px;
        }

		
		
    </style>
</head>
<body>
    <div class="form-container">
		    <h2>賣家評價</h2>
		    <h2>訂單編號: ${BidOrder.bidOrderId}</h2>
		
     
        <form method="post" action="<%=request.getContextPath()%>/bidorder/BidOrder.do"  enctype="multipart/form-data">
            <label>賣家評價星數：</label>
            <div class="stars" id="starRating">
                <span class="star" onclick="setRating(1)">☆</span>
                <span class="star" onclick="setRating(2)">☆</span>
                <span class="star" onclick="setRating(3)">☆</span>
                <span class="star" onclick="setRating(4)">☆</span>
                <span class="star" onclick="setRating(5)">☆</span>
            </div>
            <input type="hidden" name="sellStar" id="ratingValue" value="0">
            <br>
            <br>

            <label>賣家評價內容：</label>
            <textarea name="sellerRatingDesc" rows="4" cols="50"></textarea>
 
         
          
			<input type="hidden" name="buyMbrId" value="${BidOrder.buyMbrId}">
			<input type="hidden" name="bidOrderId" value="${BidOrder.bidOrderId}">
			<input type="hidden" name="sellMbrId" value="${user.mbrId}"> 
			<input type="hidden" name="action" value="sell_Rating_in"> 
            <input type="submit" value="提交評價">
            
        </form >

    </div>
    <!-- 這裡可以加入你的 JavaScript 連結 -->
    <script>
        function setRating(rating) {
            document.getElementById('ratingValue').value = rating;
            for (let i = 1; i <= 5; i++) {
                const star = document.getElementById('starRating').children[i - 1];
                star.classList.toggle('active', i <= rating);
            }
        }

        
    </script>
</body>
</html>
