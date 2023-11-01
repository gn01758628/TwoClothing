<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
*{
            box-sizing: border-box;
        }
        body {
            display: flex;
            justify-content: center;
            flex-direction:row;
        }
        div.detailBox{
            /* border: 1px solid black; */
            /* height: 150px; */
            border-radius: 0.5rem;
            background-color: lightgrey;
            width: 1000px;
            padding: 30px 60px;
            display: flex;
            justify-content: space-around;
            align-items: center;
            margin-bottom: 10px;
        }
        div.detailBox > *{
            padding: 0px 10px;
        }
        div.detailBox input[type='checkbox']{
            
        }
        div.detailBox img.itemImg{
            width: 250px;
        }
        div.detailBox div.info{
            height: 100%;
            width: 300px;
            display: flex;
            flex-direction: column;
            padding: 25px 10px;
        }
        div.detailBox div.info p.item-name{
            font-size: 28px;
            margin: 0;
            height: 30%;
            margin-bottom: 5px;
        }
        div#detailBox div.info p.item-description{
            font-size: 16px;
            margin: 0;
            height: 70%;
        }
        div.detailBox table{
            border-collapse: separate;
            border-spacing: 10px;
        }
        div.detailBox table tr.trth th{
            border-bottom: 1px solid black;
            padding-bottom: 5px;
        }
        div.detailBox table tr.trtd{
            text-align: center;
        }
        div.detailBox table tr.trtd input[type='number']{
            padding: 5px;
            width: 50px;
            background: transparent;
            border: 0px;
            outline: none;
            text-align: right;
        }
        div.detailBox div.del-box button{
            padding: 5px;
            cursor: pointer;
        }

        div.rightMain{
            border: 1px solid black;
            padding: 40px;
            margin-left: 40px;
            width: 20%;
            height:calc(100vh - 16px);
            position: relative;
            display: flex;
            justify-content: center;
            background-color: lightgrey;
            border-radius: 0.5rem;
            margin-bottom: 10px;
        }

        div.rightMain div.rightInner{
            border: 1px solid red;
            position: fixed;
			bottom: 20%;
			width: 15%;
            
        }

        div.rightMain div.rightInner > label{
            border: 1px solid blue;
            font-size: 20px;
            display: inline-block;
            margin-bottom: 20px;
            border-bottom: 1px solid gray;
            width: 100%;
            padding-bottom: 20px;        
        }

        div.rightMain div.rightInner > div{
            border: 1px solid black;
            font-size: 20px;
            text-align: center;
            margin: 20px 0px;
            margin-bottom: 34px;
        }

        div.rightMain div.rightInner button{
           width: 100%;
           height: 35px;
        }

        
    </style>
</head>
<body>
    <div class="leftMain">
    <div>您的購物車是空的!</div>
    <c:forEach var="item" items="${itemList}"  varStatus="loop">
        <div class="detailBox">
            <input type="checkbox" id="">
            <img class="itemImg" src="https://dummyimage.com/600x400/000/333.png&text=image+test" alt="圖片">
            <div class="info">
                <p class="item-name">${item.itemName}</p>
                <p class="item-description">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nihil animi doloremque mollitia </p>
            </div>
            <table>
                <tr class="trth">
                    <th style="width: 100px;max-width: 100px;">尺寸</th>
                    <th style="width: 100px;max-width: 100px;">數量</th>
                    <th style="width: 100px;max-width: 100px;">合計</th>
                </tr>
                <tr class="trtd">
                    <td style="width: 100px;max-width: 100px;">${item.size}</td>
                    <td style="width: 100px;max-width: 100px;"><input type="number" min="1" value="${quantities[loop.index]}"></td>
                    <td style="width: 100px;max-width: 100px;">${item.price}</td>
                </tr>
            </table>
        
            <div class="del-box">
                <button>X</button>
            </div>
        </div>
    </c:forEach>
    </div>
    <div class="rightMain">
        <div class="rightInner">
            <label class="checkCoupon"><input type="checkbox" name="" id="coupon">使用優惠券</label>
            <br>
            <label class="checkPoint"><input type="checkbox" name="" id="point">使用會員點數<span>5</span>點</label>
            <div class="count">本次訂單折$<span>50</span></div>
            <div class="total">合計$<span>100</span></div>
            <button name="pay">結帳</button>
        </div>
    </div>
</body>
</html>