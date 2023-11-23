<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ItemReport</title>
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
    <!--css-->
	<style>
		.main {
			padding-bottom: 20px;
		}
		
		.table thead th, .table tbody td {
			white-space: nowrap;
			padding: 15px 10px;
		}
		
		a {
    		text-decoration: none;
    		color: #000;
		}
		
		a:hover {
			color: #a2b5cd;
		}
		
		.table tbody td p {
			margin: 0;
		}
		
		.table thead th:nth-child(1), .table tbody td:nth-child(1),
		.table thead th:nth-child(5), .table tbody td:nth-child(5),
		.table thead th:nth-child(7), .table tbody td:nth-child(7) {
			min-width: 80px;
	        max-width: 80px;
	    }
	    
	    .table thead th:nth-child(2), .table tbody td:nth-child(2) {
	    	min-width: 100px;
	        max-width: 100px;
	    }
	
	    .table thead th:nth-child(3), .table tbody td:nth-child(3),
	    .table thead th:nth-child(6), .table tbody td:nth-child(6),
	    .table thead th:nth-child(4), .table tbody td:nth-child(4),
	    .table thead th:nth-child(8), .table tbody td:nth-child(8) {
	   		min-width: 190px;
	        max-width: 190px;
	    }
	
		.btn {
 		    border: none;
		    padding: 0;
		    margin: 0;
		    height: 30px;
		    width: 50px;
		    font-size: 14px;
		    text-align: center;
	    }
	    
	    .btn:hover, .btn:focus, .btn:active, .btn:visited {
	    	outline: none;
	    	box-shadow: none !important;
	    }
	    
	    .page-container {
	    	position: absolute;
	    	left: 50%;
    		transform: translateX(-50%);
    		margin-top: 4px;
	    }
	    
	    .btn.page {
	    	color: black;
	    	width: 40px;
	    }
	    
	    .btn.page:hover {
	    	color: rgb(168, 7, 7);
	    }
	</style>
	<!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>
	<div class="headerHTML"></div>

	<div class="container mt-3 mb-4 main">
		<c:if test="${not empty errorMsgs}">
			<font style="color: red">請重新確認</font>
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li style="color: red">${message}</li>
				</c:forEach>
			</ul>
		</c:if>
	
	<!-- 	<div class="page"> -->
	<%-- 		<c:if test="${itemReportPageQty > 0}"> --%>
	<%-- 			<b><font color=red>第${currentPage}/${itemReportPageQty}頁</font></b> --%>
	<%-- 		</c:if> --%>
	<!-- 	</div> -->
	
		<table class="table table-hover">
			<thead class="thead-primary">
				<tr>
					<th>檢舉編號</th>
					<th>商品</th>
					<th>檢舉日期</th>
					<th>檢舉原因</th>
					<th>審核狀態</th>
					<th>審核日期</th>
					<th>審核結果</th>
					<th>備註</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="itemReport" items="${itemReportList}">
					<tr>
						<td>${itemReport.reportId}</td>
						<td>
							<p>
								<c:choose>
									<c:when test="${fn:length(itemNameMap[itemReport.itemId]) > 10}">
										<a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${itemReport.itemId}">${fn:substring(itemNameMap[itemReport.itemId], 0, 10)}...</a>
									</c:when>
									<c:otherwise>
										<a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${itemReport.itemId}">${itemNameMap[itemReport.itemId]}</a>
									</c:otherwise>
								</c:choose>
							</p>
						</td>
						<td>${itemReport.reportDate}</td>
						<td>
							<p>
								<c:choose>
									<c:when test="${fn:length(itemReport.description) > 10}">
										${fn:substring(itemReport.description, 0, 10)}...
									</c:when>
									<c:otherwise>
										${itemReport.description}
									</c:otherwise>
								</c:choose>
							</p>
						</td>
						<td>${rStatusMap[itemReport.rStatus]}</td>
						<td>${itemReport.auditDate}</td>
						<td>${resultMap[itemReport.result]}</td>
						<td>
							<p>
								<c:choose>
									<c:when test="${fn:length(itemReport.note) > 10}">
										${fn:substring(itemReport.note, 0, 10)}...
									</c:when>
									<c:otherwise>
										${itemReport.note}
									</c:otherwise>
								</c:choose>
							</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		<div class="page-container">
			<c:if test="${not empty itemReportList}">
			    <a class="btn page" href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId&page=1">&lt;&lt;</a>
	
			    <c:forEach var="i" begin="1" end="${itemReportPageQty}">
			        <c:choose>
			            <c:when test="${currentPage eq i}">
			                <a class="btn page" href="#">${i}</a>
			            </c:when>
			            <c:otherwise>
			                <a class="btn page" href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId&page=${i}">${i}</a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    
			    <a class="btn page" href="${pageContext.request.contextPath}/front/itemreport?action=getAllByMbrId&page=${itemReportPageQty}">&gt;&gt;</a>
			</c:if>
		</div>
	</div>
	
	<div class="footerHTML"></div>

	<!--bootstrap5 js-->
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <!--jQuery-->
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
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
</body>
</html>
