<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${Members.mbrName}</title>
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

        div.body_container{
/*             border:1px solid black; */
            width:100%;
            height:100%;
            display:flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }
        
        div.body_container div.imgContainer{           
            width:98%;
            height: 600px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
            position: relative;
        }

        div.body_container div.imgContainer div.imgdown{
/*             border:1px solid orange; */
            width: 100%;
            height: 100%;
            overflow: hidden;
        }

        div.body_container div.imgContainer div img{
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
            background-color: transparent;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow: hidden;
        }

        div.imgContainer div.outer_avatar div.blouse{
            border-radius: 50%;
        }
        
        div.body_container div.mbrAction{
        	width: 100%;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    margin-top: 30px;
        }
        
        div.body_container div.mbrAction button{
        	text-transform: none;
		    margin: 0 30px;
		    width: 180px;
		    height: 50px;
		    background-color: #f9edf2;
		    color: #561729;
		    border-radius: 7px;
		    border: 1px solid;
		    font-size: 20px;
        }
        
        div.body_container div.mbrAction button:hover{
        	background-color:indianred;
        	color:white;
/*         darksalmon khaki */
        }
        
        div.cate_container{
			border-bottom: 1px solid gray;
            width: 97%;
            height: 200px;
            display:flex;
            justify-content: center;
            align-items: center;
            flex-direction: row;
            margin: 30px;
        }

        div.cate_container > div{
/*             border:1px solid black;  */
            width: 25%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;           
        }

        div.cate_container div div.cateImgContainer{
/*             border:1px solid burlywood; */
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
            background-color:rgba(165,42,42,1);;
            position: fixed;
            bottom: 20px;
            right:20px;
            border-radius: 50%;
            width: 100px;
            height: 100px;
            display: flex;
            align-items: center;
            padding: 4px;
            z-index: 1;
            
        }
        
/*       div.bidItem a{
            color:beige;
            width: 100%;
            height: 100%;
            display: flex;
            text-decoration: none;
            text-align: center;
            align-items: center;
            padding: 0px;
            justify-content: center;

        }*/
        
        div.itemarea{
            display: flex;
            justify-content: flex-start;
            flex-direction: column;
            width:100%;
            min-height: 1000px;
            

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
/*             border: 1px solid cornflowerblue; */
            flex-grow: 1;
            max-width: 25%;
            margin-bottom: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        
        div.itemarea ul.itemList >li a{
            display: block;
            border: 1px solid black;
            text-decoration: none;
            width: 90%;
            height: 100%;
            padding:27px;
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
            font-size: 14px;
    		margin-top: 2px;
        }
        
        div.itemarea ul.itemList li div.iteminner span.price{
            position:absolute;
            right: 0px;
            font-size: 14px;
            margin-top: 2px;
            
        }

        div.itemarea ul.itemList >li a div.imgBlock{
/*             border:1px solid orchid; */
            font-size: 0;
            text-align: center;
            position:relative;
/*             width: 100%; */
            height: 100%;
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
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
    
</head>
<body>
	<div class="headerHTML"></div>
    <div class="body_container">
        <div class="imgContainer">
            <!-- <div class="imgup">
                 <img src="https://images.pexels.com/photos/5120185/pexels-photo-5120185.jpeg?auto=compress&cs=tinysrgb&w=600" alt="image1">
            </div> -->
            <div class="outer_avatar">
                <div class="avatar">
                    <img src="${pageContext.request.contextPath}/DBGifReader5?mbrid=${Members.mbrId}&imgType=avatar" alt="avatar">
                </div>
            </div>
            <div class="imgdown">
                <img src="${pageContext.request.contextPath}/DBGifReader5?mbrid=${Members.mbrId}&imgType=shopimg01" alt="image2">
            </div>
        </div>
        
        <div class="mbrAction">
        	<button id="followButton" onclick="insertFollow(${Members.mbrId})">關注</button>
        	<button id="followingButton" onclick="deleteFollow(${Members.mbrId})">關注中</button>
        	<button id="blacklistButton" onclick="insertBlackList(${Members.mbrId})">黑名單</button>
        	<button id="blackingButton" onclick="deleteBlackList(${Members.mbrId})">移除黑名單</button>
        </div>
                
        <div class="cate_container">
            <div class="blouse">
                <div class="cateImgContainer" id="blouse">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/blouse.jpg" alt="">
                </div>
                <span>衣服</span>
            </div>
            <div class="bottoms">
                <div class="cateImgContainer" id="bottoms">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/bottoms.jpg" alt="">
                </div>
                <span>褲子</span>
            </div>
            <div class="shoes">
                <div class="cateImgContainer" id="shoes">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/shoes.jpg" alt="">
                </div>
                <span>鞋子</span>
            </div>
            <div class="accessories">
                <div class="cateImgContainer" id="accessories">
                    <img src="${pageContext.request.contextPath}/images/sellerHome/accessories.jpg" alt="">
                </div>
                <span>飾品</span>
            </div>
        </div>
<!--         <div class="bidItem"> -->
<!--             <a href="#">商品競標中<br>去看看</a> -->
<!--         </div> -->
        <div class="itemarea">
            <ul class="itemList">
            <c:forEach var="itemWithCategory" items="${itemListWithCategory}" >      
<%--                <c:forEach var="categoryTags" items="${categoryTagsList}"> --%>
<%--        			 <c:if test="${categoryTags.tagId eq item.tagId}">   --%>
	                <li class="li_area" data-supertagid="${itemWithCategory.categoryTags.superTagId}">
	                   <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${itemWithCategory.item.itemId}">
	                    <div class="imgBlock">
	                        <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${itemWithCategory.item.itemId}&position=1" alt="商品圖片">
	                    </div>
	                    <div class="iteminner">
	                        <span class="name">${itemWithCategory.item.itemName}</span><span class="price">$${itemWithCategory.item.price}</span>
	                    </div>
	                   </a> 
	                </li>
            </c:forEach>
            </ul>
        </div>


    </div>
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
	    $("#blouse").click(function(){
	    	$(".li_area").each(function(){
	            var supertagId = $(this).data("supertagid");
	            if(supertagId === 2){ 
	                $(this).show(); 
	            } else {
	                $(this).hide();
	            }
	    	});
	    });
	    $("#bottoms").click(function(){
	    	$(".li_area").each(function(){
	    		var supertagId = $(this).data("supertagid");
	            if(supertagId === 3){ 
	                $(this).show(); 
	            } else {
	                $(this).hide();
	            }
	    	});
	    });
	    $("#shoes").click(function(){
	    	$(".li_area").each(function(){
	    		var supertagId = $(this).data("supertagid");
	            if(supertagId === 4){ 
	                $(this).show(); 
	            } else {
	                $(this).hide();
	            }
	    	});
	    });
	    $("#accessories").click(function(){
	    	$(".li_area").each(function(){
	    		var supertagId = $(this).data("supertagid");
				console.log(supertagId);
	            if(supertagId === 5){ 
	                $(this).show(); 
	            } else {
	                $(this).hide();
	            }
	    	});
	    });
	    
	    <% Integer mbrId = (Integer) session.getAttribute("mbrId");
	    Integer homembrId = (Integer) session.getAttribute("homembrId"); %>
		
		if (${mbrId} == ${homembrId}) {
		    $("#followButton").hide();
		    $("#followingButton").hide();
		    $("#blacklistButton").hide();
		    $("#blackingButton").hide();
		} else {
			let isFollow = ${isFollow};
		    let isBlackList = ${isBlackList};
			if (isFollow) {
				$("#followButton").hide();
				$("#followingButton").show();
		        $("#blacklistButton").hide();
		        $("#blackingButton").hide();
			} else if (isBlackList) {
				$("#followButton").hide();
				$("#followingButton").hide();
		        $("#blacklistButton").hide();
		        $("#blackingButton").show();
		        Swal.fire ({
		    		backdrop: false,
		    		title: "注意",
		    		text: "此賣家為您的黑名單用戶",
		    		icon: "warning",
		    		timer: 3000,
		    		showConfirmButton: false,
		    	});
			} else {
				$("#followButton").show();
				$("#followingButton").hide();
		        $("#blacklistButton").show();
		        $("#blackingButton").hide();
			}
		}
    });
    
    function insertFollow(mbrId) {
    	event.preventDefault();
    	
    	var url = "${pageContext.request.contextPath}/follow.check?action=insert&mbrId=${Members.mbrId}";
    	
    	$.ajax({
	        type: "POST",
	        url: url,
	        success: function (data) {
	        	$("#followButton").hide();
	        	$("#followingButton").show();
                $("#blacklistButton").hide();
                $("#blackingButton").hide();
 	        	isFollow = true;
	        },
	        error: function (xhr) {
	            if (xhr.status === 403) {
	                window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
	            } else {
	                console.log(xhr);
	            }
	        }
	    });
    }
    
    function deleteFollow(mbrId) {
    	event.preventDefault();
    	
    	var url = "${pageContext.request.contextPath}/follow.check?action=delete&mbrId=${Members.mbrId}";
    	
    	$.ajax({
	        type: "POST",
	        url: url,
	        success: function (data) {
	        	$("#followButton").show();
	        	$("#followingButton").hide();
	            $("#blacklistButton").show();
	            $("#blackingButton").hide();
 	        	isFollow = false;
	        },
	        error: function (xhr) {
	            if (xhr.status === 403) {
	                window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
	            } else {
	                console.log(xhr);
	            }
	        }
	    });
    }
    
    function insertBlackList(mbrId) {
    	event.preventDefault();
    	
    	var url = "${pageContext.request.contextPath}/blacklist.check?action=insert&mbrId=${Members.mbrId}";
    	
    	$.ajax({
	        type: "POST",
	        url: url,
	        success: function (data) {
	        	$("#followButton").hide();
	        	$("#followingButton").hide();
                $("#blacklistButton").hide();
                $("#blackingButton").show();
 	        	isBlackList = true;
	        },
	        error: function (xhr) {
	            if (xhr.status === 403) {
	                window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
	            } else {
	                console.log(xhr);
	            }
	        }
	    });
    }
    
    function deleteBlackList(mbrId) {
    	event.preventDefault();
    	
    	var url = "${pageContext.request.contextPath}/blacklist.check?action=delete&mbrId=${Members.mbrId}";
    	
    	$.ajax({
	        type: "POST",
	        url: url,
	        success: function (data) {
	        	$("#followButton").show();
	        	$("#followingButton").hide();
	            $("#blacklistButton").show();
	            $("#blackingButton").hide();
 	        	isBlackList = false;
	        },
	        error: function (xhr) {
	            if (xhr.status === 403) {
	                window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
	            } else {
	                console.log(xhr);
	            }
	        }
	    });
    }
    </script>
    
</body>
</html>