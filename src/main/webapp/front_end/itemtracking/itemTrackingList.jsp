<%@ page import="com.twoclothing.model.aproduct.itemtracking.*"%>
<%@ page import="com.twoclothing.chi.controller.*"%>
<%@ page import="com.twoclothing.chi.service.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/chi/list.css">
	<title>ItemTracking</title>
	
	<style>    	
    	.delete {
    		background-color: transparent;
		  	border: none;
		  	transform: scale(1.1);
		  	transition: .2s linear;
		}
		
		.delete:hover > .icon {
		  	transform: scale(1.2);
		}
		
		.delete:hover > .icon path {
		  	fill: rgb(168, 7, 7);
		}
    
		.btn {
		    border: none;
		    padding: 0;
		    margin: 0;
		    height: 30px;
		    width: 50px;
		    font-size: 14px;
		    line-height: 30px;
		    text-align: center;
	    }
	    
	    .btn:hover, .btn:focus, .btn:active, .btn:visited {
	    	outline: none;
	    	box-shadow: none !important;
	    }
	    
	    .btn.page {
	    	color: black;
	    	width: 40px;
	    }
	    
	    .btn.page:hover {
	    	color: red;
	    }
	</style>
</head>
<body>
	<a href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId">查詢所有商品追蹤</a>

	<main class="main">
		<ul class="list">
			<c:forEach var="item" items="${itemTrackingList}">
				<li>
					<a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${item.itemId}">
						<div class="image-container">
					    	<img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${item.itemId}&position=1" class="img">
				    	</div>
				    	
	    				<div class="product-info">
						    <span class="name">${item.itemName}</span>
						    <span class="price">${item.price}</span>
						    <form method="post" action="${pageContext.request.contextPath}/itemtrackinglist.check">
						        <button class="delete" type="submit">
						        	<svg viewBox="0 0 15 17.5" height="16" width="14" xmlns="http://www.w3.org/2000/svg" class="icon">
						        		<path transform="translate(-2.5 -1.25)" d="M15,18.75H5A1.251,1.251,0,0,1,3.75,17.5V5H2.5V3.75h15V5H16.25V17.5A1.251,1.251,0,0,1,15,18.75ZM5,5V17.5H15V5Zm7.5,10H11.25V7.5H12.5V15ZM8.75,15H7.5V7.5H8.75V15ZM12.5,2.5h-5V1.25h5V2.5Z" id="Fill"></path>
						        	</svg>
						        </button>
						        <input type="hidden" name="itemId" value="${item.itemId}">
						        <input type="hidden" name="mbrId" value="${sessionScope.mbrId}">
						        <input type="hidden" name="action" value="delete">
						    </form>
						</div>
					</a>
				</li>
			</c:forEach>
		</ul>
		
		<div class="pagination justify-content-center mt-3">
			<a class="btn page" href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=1">&lt;&lt;</a>
			    
			<c:forEach var="i" begin="1" end="${itemTrackingPageQty}">
			    <c:choose>
			        <c:when test="${currentPage eq i}">
			            <a class="btn page" href="#">${i}</a>
			        </c:when>
			        <c:otherwise>
			            <a class="btn page" href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=${i}">${i}</a>
			        </c:otherwise>
			    </c:choose>
			</c:forEach>
			    
			<a class="btn page" href="${pageContext.request.contextPath}/itemtrackinglist.check?action=getAllByMbrId&page=${itemTrackingPageQty}">&gt;&gt;</a>
		</div>
	</main>
	
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
</body>
</html>
