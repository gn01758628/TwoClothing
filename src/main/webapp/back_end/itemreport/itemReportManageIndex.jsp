<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>ItemReportManageIndex</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap5/bootstrap.min.css">
	<style>
	* {
		box-sizing: border-box;
	}
	
	body {
	    margin: 0;
	    padding: 0;
	}
	
	.compositequery {
		display: flex;
	    align-items: center;
	    justify-content: center;
	    height: 100px;
	    margin-top: 10px;
	    margin-bottom: 10px;
	}
	
	form {
	    display: flex;
	    justify-content: space-between;
	    width: 90%;
	    margin: 0 auto;
	  }
	
	label {
	    display: inline-block;
	    margin-left: 10px;
	    line-height: 30px;
    	height: 30px;
	}
	
	input[type="submit"] {
		height: 30px;
		width: 50px;
		font-size: 14px;
		text-align: center;
	    cursor: pointer;
	    margin-left: 10px;
	}
	</style>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請重新確認</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="compositequery">
		<form method="post" action="${pageContext.request.contextPath}/back/itemreport">
			<label>商品編號</label>
			<input type="text" name="itemId" maxlength=20>
			
			<label>會員編號</label>
			<input type="text" name="mbrId">
	
			<label>員工編號</label>
			<input type="text" name="empId">
			
			<label>審核狀態</label>
			<select name="rStatus">
				<option value="">狀態</option>
				<option value="0">待審核</option>
				<option value="1">已審核</option>
			</select>
			
			<label>審核結果</label>
			<select name="result">
				<option value="">結果</option>
				<option value="0">處分</option>
				<option value="1">不處分</option>
			</select>
			
			<input type="hidden" name="action" value="compositeQuery">
			<input type="submit" value="查詢">
		</form>
	</div>
	<hr>
	
	<div class="container mt-5">
        <table class="table table-hover">
            <thead class="thead-primary">
                <tr>
                    <th>檢舉編號</th>
                    <th>商品編號</th>
                    <th>會員編號</th>
                    <th>員工編號</th>
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
                        <td>${itemReport.itemId}</td>
                        <td>${itemReport.mbrId}</td>
                        <td>${itemReport.empId}</td>
                        <td>${itemReport.reportDate}</td>
                        <td>${itemReport.description}</td>
                        <td>${rStatusMap[itemReport.rStatus]}</td>
                        <td>${itemReport.auditDate}</td>
                        <td>${resultMap[itemReport.result]}</td>
                        <td>${itemReport.note}</td>
                        <td class="no-border">
                        	<c:choose>
                        		<c:when test="${itemReport.rStatus == 1}">
                                    <button class="btn check btn-secondary" onclick="showDetail(${itemReport.reportId})" type="button">查看</button>
                                </c:when>
                                <c:otherwise>
                                    <button class="btn check btn-secondary" onclick="showDetail(${itemReport.reportId})" type="button">審核</button>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="page-container">
			<c:if test="${not empty itemReportList}">
				<a class="btn page" href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=1&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">&lt;&lt;</a>
			    
			    <c:forEach var="i" begin="1" end="${itemReportPageQty}">
			        <c:choose>
			            <c:when test="${currentPage eq i}">
			                <a class="btn page" href="#">${i}</a>
			            </c:when>
			            <c:otherwise>
			                <a class="btn page" href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=${i}&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">${i}</a>
			            </c:otherwise>
			        </c:choose>
			    </c:forEach>
			    
			    <a class="btn page" href="${pageContext.request.contextPath}/back/itemreport?action=${requestScope.action}&page=${itemReportPageQty}&itemId=${itemId}&mbrId=${mbrId}&empId=${empId}&rStatus=${rStatus}&result=${result}">&gt;&gt;</a>
			</c:if>
		</div>
    </div>
</body>
</html>
