<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>賣家商城</title>

    <style>
        *{
            box-sizing: border-box;
        }   

        div.container{
            border:1px solid black;
            width:100%;
            height:100%;
            display:flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        div.container div.imgContainer{           
            width:98%;
            height: 600px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            position: relative;
        }
        /* div.container div.imgContainer:hover{
            cursor: pointer;
        } */

        div.container div.imgContainer div.imgdown{
            border:1px solid orange;
            width: 100%;
            height: 100%;
            overflow: hidden;
        }
/* 
        div.container div.imgContainer div.imgup{
            background-image: url('https://images.pexels.com/photos/5120185/pexels-photo-5120185.jpeg?auto=compress&cs=tinysrgb&w=600');
            background-repeat: no-repeat;
            background-size: cover;
            background-position: center center;
            margin-bottom: 13px;
        } */

        div.container div.imgContainer div img{
            width: 100%;
            /* height: 100%; */
        }
        
        div.imgContainer div.outer_avatar{
            border-radius: 50%;
            width: 230px;
            height: 230px;
            background-color: white;
            position: absolute;
            display: flex;
            justify-content: center;
            align-items: center;

            
        }
        div.imgContainer div.avatar{
            width: 90%;
            height: 90%;
            border-radius: 50%;
            background-color: wheat;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }

        div.imgContainer div.outer_avatar div.blouse{
            border-radius: 50%;
        }
        
        div.cate_container{
            border:1px solid blue;
            width: 100%;
            height: 200px;
            display:flex;
            justify-content: center;
            align-items: center;
            flex-direction: row;
            margin: 30px 0;
        }

        div.cate_container > div{
            border:1px solid yellow;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            
        }

        
        div.cate_container div div.cateImgContainer{
            border:1px solid burlywood;
            width: 35%;
            height: 60%;
            margin: 10px 0;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        div.cateImgContainer:hover{
            cursor: pointer;
        }
        
        div.bidItem{
            border:1px solid palegreen;
            background-color: brown;
            position: fixed;
            bottom: 20px;
            right:20px;
            border-radius: 50%;
            width: 120px;
            height: 120px;
            display: flex;
            align-items: center;
            padding: 10px;
            z-index: 1;
            
        }
        div.bidItem a{
            color:beige;
            width: 100%;
            height: 100%;
            display: flex;
            text-decoration: none;
            text-align: center;
            align-items: center;
            padding: 5px;
            justify-content: center;

        }
        div.itemarea{
            display: flex;
            justify-content: center;
            flex-direction: column;
            width:100%;

        }
        div.itemarea ul.itemList{
            width:100%;
            list-style: none;
            display: flex;
            flex-wrap: wrap;
            padding: 0px;
            margin: 0px;

        }
        div.itemarea ul.itemList > li{
            border: 1px solid cornflowerblue;
            flex-grow: 1;
            max-width: 25%;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        div.itemarea ul.itemList >li a{
            display: block;
            border: 1px solid wheat;
            text-decoration: none;
            width: 90%;
            height: 100%;
        }
        div.itemarea ul.itemList li:nth-child(4n){
            margin-left: 0;

        }
        div.itemarea ul.itemList li div.iteminner{
            display: flex;
            flex-direction: row;
            position: relative;
        }

        div.itemarea ul.itemList li div.iteminner span.name{
            position:absolute;
            left: 0px;
            font-size: 22px;
        }
        div.itemarea ul.itemList li div.iteminner span.price{
            position:absolute;
            right: 0px;
            font-size: 22px;
        }

        div.itemarea ul.itemList >li a div.imgBlock{
            border:1px solid orchid;
            font-size: 0;
            text-align: center;
            position:relative;
            width: 100%;
            height: 90%;
        }

        
        div.itemarea ul.itemList >li a div.imgBlock img {
            max-width: 100%;
            max-height: 100%;
            width: 100%;
            height: 100%;
            object-fit: cover;
            overflow: hidden;
        }

        div.cate_container div div.cateImgContainer img{
            max-width: 100%;
            max-height: 100%;
            object-fit: cover;
            overflow: hidden;
        }

        span {
            text-align: center;
            color: black;
        }


    </style>
</head>
<body>
    <div class="container">
        <div class="imgContainer">
            <!-- <div class="imgup">
                 <img src="https://images.pexels.com/photos/5120185/pexels-photo-5120185.jpeg?auto=compress&cs=tinysrgb&w=600" alt="image1">
            </div> -->
            <div class="outer_avatar">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/images/avatar/avatar02.jpg" alt="avatar">
                </div>
            </div>
            <div class="imgdown">
                <img src="${pageContext.request.contextPath}/images/sellerHome/background7.jpg" alt="image2">
            </div>
        </div>
        <div class="cate_container">
            <div class="blouse">
                <div class="cateImgContainer">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/blouse.jpg" alt="">
                </div>
                <span>上衣</span>
            </div>
            <div class="bottoms">
                <div class="cateImgContainer">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/bottoms.jpg" alt="">
                </div>
                <span>下身</span>
            </div>
            <div class="accessories">
                <div class="cateImgContainer">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/accessories.jpg" alt="">
                </div>
                <span>飾品</span>
            </div>
            <div class="other">
                <div class="cateImgContainer">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/other.jpg" alt="">
                </div>
                <span>其他</span>
            </div>
        </div>
        <div class="bidItem">
            <a href="#">商品競標中<br>去看看</a>
        </div>
        <div class="itemarea">
            <ul class="itemList">          
                <li>
                   <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                    <div class="imgBlock">
                        <img src="https://i.pinimg.com/474x/a4/e8/ab/a4e8abc6b3be5c34838b38b1f79ae9cf.jpg" alt="商品圖片">
                    </div>
                    <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>
                    </a> 
                </li>       
                <li>
                   <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                    <div class="imgBlock">
                        <img src="https://i.pinimg.com/474x/56/b8/fb/56b8fb178431122290dead1640b565ed.jpg" alt="商品圖片">
                    </div>
                    <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                   </a> 
                </li>       
                <li>
                   <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                    <div class="imgBlock">
                        <img src="https://i.pinimg.com/474x/e9/65/77/e9657726fc752333cd028405d70475e6.jpg" alt="商品圖片">
                    </div>
                    <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                   </a> 
                </li>
                <li>
                   <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                    <div class="imgBlock">
                        <img src="https://i.pinimg.com/474x/0a/fc/c7/0afcc73023eb12978902cac6e859b1f9.jpg" alt="商品圖片">
                    </div>
                    <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                   </a> 
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                     <div class="imgBlock">
                         <img src="https://i.pinimg.com/474x/14/13/52/14135244931196d96fc04d537ae170da.jpg" alt="商品圖片">
                     </div>
                     <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                    </a> 
                 </li>
                 <li>
                    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                     <div class="imgBlock">
                         <img src="https://i.pinimg.com/474x/9b/4c/7e/9b4c7e4a36bbfbf9995d020a29764045.jpg" alt="商品圖片">
                     </div>
                     <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                    </a> 
                 </li>
                 <li>
                    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                     <div class="imgBlock">
                         <img src="https://i.pinimg.com/474x/d2/c5/1f/d2c51fc2e6443aa3f1f6b3b1b2f92b38.jpg" alt="商品圖片">
                     </div>
                     <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                    </a> 
                 </li>
                 <li>
                    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                     <div class="imgBlock">
                         <img src="https://i.pinimg.com/474x/41/cf/53/41cf53f3f94333fc6b491589b9dec3b4.jpg" alt="商品圖片">
                     </div>
                     <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                    </a> 
                 </li>
                 <li>
                    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
                     <div class="imgBlock">
                         <img src="https://i.pinimg.com/474x/dc/9f/0d/dc9f0d7301ded364dfa60e6b47a5911d.jpg" alt="商品圖片">
                     </div>
                     <div class="iteminner">
                        <span class="name">meow</span><span class="price">$100</span>
                    </div>                    </a> 
                 </li>
            </ul>
        </div>


    </div>
</body>
</html>