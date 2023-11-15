<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>itemSellerSearch</title>
    <style>
        *{
            box-sizing: border-box;
        }
        main.main{
            border: 1px solid black;
            width: 100%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;

        }

        div.main_inner{
            border: 2px solid lightgrey;
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.3);
            border-radius: 10px;
            margin: 50px;
            width: 60%;
            height:100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        div.main_inner h2{
            border: 2px solid #134f77;
            border-radius: 20px;
            padding: 5px;
            background: linear-gradient(to right, #4daff0, #ffffff);
            color: #393939;
            width: 180px;
            text-align: center;
        }

        form.form_search{
            width: 100%;
            height: 70%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        div.up_area{
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex: 1;
            padding: 10px;
        }

        div.up_area>*{
            flex: 1;

        }

        div.up_area div.namearea{
            width: 100%;
            padding: 5px 0;
        }

        div.up_area div.price_outer{
            margin-top: 15px;
            width: 100%;
            padding: 5px 0;

        }

        div.up_area div.pricearea {
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            width: 100%;
            
        }

        div.up_area div.pricearea span{
            margin: 0 20px;
        }

        div.up_area label{
            width: 100%;
            text-align: left;
        }

        div.up_area input{
            width: 100%;
            border-radius: 15px;
        }

        div.under_area{
            width: 100%;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            flex: 1;
            padding: 10px;

        }

        div.under_area div.under_up{
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            padding: 5px 0;
        }

        div.under_area div.under_up > div{
            width: 100%;
        }


        div.under_area div.under_up div:nth-child(2){
            margin-left: 45px;

        }

        div.under_area div.under_up div label{
            margin: 10px 0;

        }

        div.under_area div.under_down{
            width: 100%;
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            padding: 5px 0;

        }

        div.under_area div.under_down > div{
            width: 100%;

        }

        div.under_area div.under_down div:nth-child(2){
            margin-left: 45px;
        }

        div.under_area input{
            padding: 7px;
            display: block;
            width: 100%;
            border-radius: 15px;
        }

        input{
            margin: 10px 0;
            padding: 7px;
            border-width: 1px;
        }

        input.btn_submit{
            width: 180px;
            border-radius: 20px;
            border:2px solid #134f77;
            background-color: white;
            font-size: 16px;
        }
        input.btn_submit:hover{
            cursor:pointer;
            background: linear-gradient(to right, #4daff0, #ffffff);

        }
        label{
            padding: 7px;
            border-radius: 15px;
            background-color: #61abdd;
            color: #ffffff;
        }
        select{
            margin: 10px 0;
            padding: 7px;
            border-width: 1px;
            border-radius: 15px;
            width: 100%;
        }
    </style>
</head>
<body>
    <main class="main">
        <div class="main_inner">
            <h2>商品查詢</h2>

            <form class="form_search" method="post" action="/TwoClothing/Item/search">
                <div class="up_area">
                    <div class="namearea">
                        <label>商品名稱</label>
                        <input type="text" name="itemNameSearch">
                    </div>
                    <div class="price_outer">
                        <label>商品價格(低~高)</label>
                        <div class="pricearea">                           
                            <input type="text" name="itemPriceSearchStart"><span>~</span><input type="text" name="itemPriceSearchEnd">
                        </div>
                    </div>
                </div>
                <div class="under_area">
                    <div class="under_up">
                        <div>
                            <label>商品新舊程度</label>
                            <!-- <input type="text" name="itemGrade"> -->
                            <select class="sel_grade" name="itemGrade">
                                <option value="" selected>不拘</option>
                                <option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
                                <option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
                                <option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
                                <option value="3">8成新(有使用痕跡,少量瑕疵)</option>
                                <option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
                                <option value="5">破損商品(需要修補)</option>
                            </select>
                        </div>
                        <div>
                            <label>商品尺寸</label>
                            <!-- <input type="text" name="itemSize"> -->
                            <select class="sel_size" name="itemSize"> 
                                <option value="" selected>不拘</option>
                                <option value="0">XS(含)以下</option>
                                <option value="1">S</option>
                                <option value="2">M</option>
                                <option value="3">L</option>
                                <option value="4">XL</option>
                                <option value="5">2XL</option>
                                <option value="6">3XL</option>
                                <option value="7">4XL(含)以上</option>
                                <option value="8">其他</option>
                            </select>
                        </div>
                    </div>
                    <div class="under_down">
                        <div>
                            <label>商品數量</label>
                            <!-- <input type="text" name="itemQuantity"> -->
                            <select name="itemQuantity">
                                <option value="1" selected>不拘</option>
                                <option value="2">0～5件</option>
                                <option value="3">6件以上</option>
                            </select>
                        </div>                
                        <div>
                            <label>商品狀態</label>
                            <!-- <input type="text" name="itemStatus"> -->
                            <select name="itemStatus">
                                <option value="" selected>不拘</option>
                                <option value="0">上架中</option>
                                <option value="1">下架中</option>
                            </select>
                        </div>
                    </div>
                </div>
                <input type="hidden" name="choice" value="searchCondition">
                <input class="btn_submit" type="submit" value="查詢">
                
            </form>
        </div>
    </main>
	<script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script>
    
    </script>

    
</body>
</html>