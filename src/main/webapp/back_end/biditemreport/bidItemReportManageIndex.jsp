<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	
	html {
		--aside-width: 224px;
	}
	
	.compositequery {
		display: flex;
	    align-items: center;
	    justify-content: center;
	    height: 50px;
	    margin: 15px auto 10px auto;
	}
	
	form {
	    display: flex;
 	    justify-content: space-between;
	    width: 90%;
	}
	
	.clear, input[type="submit"] {
		border: 1px solid black;
		border-radius: 2px;
		padding: 0;
		height: 30px;
		width: 50px;
		font-size: 14px;
		text-align: center;
	    cursor: pointer;
	}
	
	.clear {
		margin-left: 10px;
	}
	
	@media screen and (max-width: 410.33px) {
		.clear {
			display: none;
		}
	}
	
	.container {
		margin-right: 0;
		margin-left: 15px;
	}
	
	.table thead th, .table tbody td {
		white-space: nowrap;
		padding: 15px 10px;
	}
	
	.table tbody td p {
		margin: 0;
	}
	
	.table thead th:nth-child(1), .table tbody td:nth-child(1) {
		min-width: 38px;
	    max-width: 38px;
		padding-left: 2px;
	}
	
	.table thead th:nth-child(3), .table tbody td:nth-child(3),
	.table thead th:nth-child(4), .table tbody td:nth-child(4) {
		min-width: 42px;
	    max-width: 42px;
	}
	
	.table thead th:nth-child(7), .table tbody td:nth-child(7),
	.table thead th:nth-child(9), .table tbody td:nth-child(9) {
		min-width: 75px;
	    max-width: 75px;
	}
	
	.table thead th:nth-child(10), .table tbody td:nth-child(10),
	.table thead th:nth-child(11), .table tbody td:nth-child(11) {
		padding-right: 0px;
	}
	
	.table thead th:nth-child(11), .table tbody td:nth-child(11) {
		padding-left: 0;
	}
	    
	.table thead th:nth-child(2), .table tbody td:nth-child(2) {
	    min-width: 120px;
	    max-width: 120px;
	}
	
	.table thead th:nth-child(5), .table tbody td:nth-child(5),
	.table thead th:nth-child(8), .table tbody td:nth-child(8) {
	   	min-width: 180px;
	    max-width: 180px;
	}
	
	.table thead th:nth-child(6), .table tbody td:nth-child(6) {
		min-width: 120px;
	    max-width: 120px;
	}
	
	.table thead th:nth-child(10), .table tbody td:nth-child(10) {
		min-width: 90px;
	    max-width: 90px;
	}
	
	.empty-list-container {
		color: #7A7A7A;
		font-size: 30px;
		margin-top: 45px;
		margin-left: 70px;
	}
	
	.btn {
		border: none;
		padding: 0;
		margin: 0;
		height: 25px;
		width: 40px;
		font-size: 12px;
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
	}
	
	.btn.page {
	    color: black;
	    width: 40px;
	    margin-bottom: 10px;
	}
	
	.btn.page:hover {
	    color: rgb(168, 7, 7);
	}
	
	.btn.page.active {
    	color: rgb(168, 7, 7);
    	text-decoration: underline;
	}
	
	.update, .close {
		height: 30px;
		width: 50px;
		font-size: 14px;
		margin-left: 10px;
	}
	
	::-webkit-scrollbar {
    	width: 0;
	}
	
	.modal-dialog {
        width: 100%;
    }
        
    .modal-body {
  		display: flex;
        align-items: center;
        justify-content: center;
    }
    
    .title-description {
        margin-top: 10px;
    }
        
    .card-body td:nth-child(2) {
    	padding: 3px 0 3px 25px;
    }
    
    .card-description {
    	min-width: 500px;
	    max-width: 500px;
    }
	</style>
</head>
<body>
	<div class="compositequery">
		<form method="post" action="${pageContext.request.contextPath}/back/biditemreport" onsubmit="removeWhitespace()">
			<input type="text" name="reportId" id="reportIdInput" placeholder="檢舉編號" value="${convertedMap.reportId}">
			
			<input type="text" name="mbrId" id="mbrIdInput" placeholder="會員編號" value="${convertedMap.mbrId}">

			<input type="text" name="empId" id="empIdInput" placeholder="員工編號" value="${convertedMap.empId}">
			
			<select name="bidStatus" id="bidStatusSelect">
				<option value="">審核狀態</option>
				<option value="0">待審核</option>
				<option value="1">已審核</option>
			</select>
			
			<select name="result" id="resultSelect">
				<option value="">審核結果</option>
				<option value="0">處分</option>
				<option value="1">不處分</option>
			</select>
			
			<input type="hidden" name="action" value="compositeQuery">
			<input type="submit" value="查詢">
		</form>
		<button onclick="clearSearch()" class="clear">清空</button>
	</div>
	<hr>
	
	<div class="main">
		<div class="container mt-0">
	        <table class="table table-hover">
	            <thead class="thead-primary">
	                <tr>
	                    <th>檢舉</th>
	                    <th>競標</th>
	                    <th>會員</th>
	                    <th>員工</th>
	                    <th>檢舉日期</th>
	                    <th>檢舉原因</th>
	                    <th>審核狀態</th>
	                    <th>審核日期</th>
	                    <th>審核結果</th>
	                    <th>備註</th>
	                </tr>
	            </thead>
	            <tbody>
	                <c:forEach var="bidItemReport" items="${bidItemReportList}">
	                    <tr>
	                        <td>${bidItemReport.reportId}</td>
	                        <td>
	                        	<p>
									<c:choose>
										<c:when test="${fn:length(bidItemNameMap[bidItemReport.bidItemId]) > 6}">
											${fn:substring(bidItemNameMap[bidItemReport.bidItemId], 0, 6)}...
										</c:when>
										<c:otherwise>
											${bidItemNameMap[bidItemReport.bidItemId]}
										</c:otherwise>
									</c:choose>
								</p>
	                        </td>
	                        <td>${bidItemReport.mbrId}</td>
	                        <td>${bidItemReport.empId}</td>
	                        <td>${bidItemReport.reportDate}</td>
	                        <td>
	                        	<p>
									<c:choose>
										<c:when test="${fn:length(bidItemReport.bidDescription) > 6}">
											${fn:substring(bidItemReport.bidDescription, 0, 6)}...
										</c:when>
										<c:otherwise>
											${bidItemReport.bidDescription}
										</c:otherwise>
									</c:choose>
								</p>
	                        </td>
	                        <td>${statusMap[bidItemReport.bidStatus]}</td>
	                        <td>${bidItemReport.auditDate}</td>
	                        <td>${resultMap[bidItemReport.result]}</td>
	                        <td>
	                        	<p>
									<c:choose>
										<c:when test="${fn:length(bidItemReport.note) > 3}">
											${fn:substring(bidItemReport.note, 0, 3)}...
										</c:when>
										<c:otherwise>
											${bidItemReport.note}
										</c:otherwise>
									</c:choose>
								</p>
	                        </td>
	                        <td class="no-border">
	                        	<c:choose>
	                        		<c:when test="${bidItemReport.bidStatus == 1}">
	                                    <button class="btn check btn-secondary" onclick="showDetail(${bidItemReport.reportId})" type="button">查看</button>
	                                </c:when>
	                                <c:otherwise>
	                                    <button class="btn check btn-secondary" onclick="showDetail(${bidItemReport.reportId})" type="button">審核</button>
	                                </c:otherwise>
	                            </c:choose>
	                        </td>
	                    </tr>
	                </c:forEach>
	            </tbody>
	        </table>
	        
	        <c:if test="${empty bidItemReportList}">
				<div class="empty-list-container">
				    <p>查無檢舉</p>
				</div>
			</c:if>
	
	        <div class="page-container">
				<c:if test="${not empty bidItemReportList}">
					<a class="btn page" href="${pageContext.request.contextPath}/back/biditemreport?action=${convertedMap.action}&page=1&reportId=${convertedMap.reportId}&mbrId=${convertedMap.mbrId}&empId=${convertedMap.empId}&bidStatus=${convertedMap.bidStatus}&result=${convertedMap.result}">&lt;&lt;</a>
				    
				    <c:forEach var="i" begin="1" end="${bidItemReportPageQty}">
				        <c:choose>
				            <c:when test="${currentPage eq i}">
				                <a class="btn page active" href="#">${i}</a>
				            </c:when>
				            <c:otherwise>
				                <a class="btn page" href="${pageContext.request.contextPath}/back/biditemreport?action=${convertedMap.action}&page=${i}&reportId=${convertedMap.reportId}&mbrId=${convertedMap.mbrId}&empId=${convertedMap.empId}&bidStatus=${convertedMap.bidStatus}&result=${convertedMap.result}">${i}</a>
				            </c:otherwise>
				        </c:choose>
				    </c:forEach>
				    
				    <a class="btn page" href="${pageContext.request.contextPath}/back/biditemreport?action=${convertedMap.action}&page=${bidItemReportPageQty}&reportId=${convertedMap.reportId}&mbrId=${convertedMap.mbrId}&empId=${convertedMap.empId}&bidStatus=${convertedMap.bidStatus}&result=${convertedMap.result}">&gt;&gt;</a>
				</c:if>
			</div>
	    </div>
    </div>
    
    <div class="modal fade" id="bidItemReportModal" tabindex="-1" aria-labelledby="bidItemReportModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
	      		<div class="modal-header">
	      			<h5 class="modal-title" id="bidItemReportModalLabel">競標檢舉明細</h5>
	      		</div>
	      		
		      	<div class="modal-body">
			        <div class="card" style="width: 45rem;">
				        <div class="card-body">
					        <table>
								<tr>
									<td>檢舉編號</td>
									<td id="reportId"></td>
								</tr>
								<tr>
									<td>商品編號</td>
									<td id="bidItemId"></td>
								</tr>
								<tr>
									<td>會員編號</td>
									<td id="mbrId"></td>
								</tr>
								<tr>
									<td>員工編號</td>
									<td id="empId"></td>
								</tr>
								<tr>
									<td>檢舉日期</td>
									<td id="reportDate"></td>
								</tr>
								<tr>
									<td>檢舉原因</td>
									<td class="card-description" id="bidDescription"></td>
								</tr>
								<tr>
									<td>審核狀態</td>
									<td id="bidStatus"></td>
								</tr>
								<tr>
									<td>審核日期</td>
									<td id="auditDate"></td>
								</tr>
								<tr>
									<td>審核結果</td>
									<td id="result"></td>
								</tr>
								<tr>
									<td>備註</td>
									<td id="note"></td>
								</tr>
							</table>
				        </div>
		      		</div>
		      	</div>
		      	
		      	<div class="modal-footer">
		      		<button class="btn btn-secondary update" id="update" onclick="update()">確定</button>
			    	<button type="button" class="btn btn-secondary close" data-bs-dismiss="modal">關閉</button>
			    </div>
			</div>
		</div>
	</div>
    
    <script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>
    
    <script>
	    function removeWhitespace() {
	        var inputs = document.getElementsByTagName('input');
	        for (var i = 0; i < inputs.length; i++) {
	            if (inputs[i].type === 'text') {
	                inputs[i].value = inputs[i].value.trim();
	            }
	        }
	    }
    
    	document.getElementById('bidStatusSelect').value = "${convertedMap.bidStatus}";
    	document.getElementById('resultSelect').value = "${convertedMap.result}";
    	
    	function clearSearch() {
    		document.getElementById('reportIdInput').value = '';
    		document.getElementById('mbrIdInput').value = '';
            document.getElementById('empIdInput').value = '';
            document.getElementById('bidStatusSelect').value = '';
            document.getElementById('resultSelect').value = '';
            
            document.querySelector('form').submit();
    	}
    	
    	function showDetail(reportId) {
	        event.preventDefault();
	        let url = "${pageContext.request.contextPath}/back/biditemreport?action=getOne&reportId=" + reportId;

	        fetch(url)
	            .then(function (response) {
	                return response.json();
	            })
	            .then(function (data) {
	                let bidStatus = "";
	                if (data.bidStatus == 0) {
	                	bidStatus = "待審核";
	                } else if (data.bidStatus == 1) {
	                	bidStatus = "已審核";
	                }

	                let result = "";
	                if (data.result == 0) {
	                    result = "處分";
	                } else if (data.result == 1) {
	                    result = "不處分";
	                }
	                
	                $('#reportId').text(data.reportId);
	     	    	$('#bidItemId').text(data.bidItemId);
	     	    	$('#mbrId').text(data.mbrId);
	     	    	$('#empId').text(data.empId);
	     	    	$('#reportDate').text(data.reportDate);
	     	    	$('#bidDescription').text(data.bidDescription);
	     	    	$('#bidStatus').text(bidStatus);
	     	    	$('#auditDate').text(data.auditDate);
	     	    	
	     	    	if (result == "") {
	     	    		$('#result').html(`
	     	    			<select id="selectResult" name="result">
	     	    			<option value="-1">選擇處分</option>
	     	    			<option value="0">處分</option>
	     	    			<option value="1">不處分</option>
	     	    			</select>
	     	    		`);
	     	    		
	     	    		$('#note').html(`
	     	    			<input type="text" id="inputNote" name="note" size="70"/>
		     	    	`);
	     	    		
	     	    		$('#update').show();
	     	    	} else {
	     	    		$('#result').text(result);
	     	    		
	     	    		$('#note').text(data.note);
	     	    		
	     	    		$('#update').hide();
	     	    	}

	     	    	let html = `<li class="list-group-item" id="report">An itemreport</li>`;
	     	    	$('#bidItemReportModal').modal('show');
	            })
	            .catch(function (error) {
	                console.log(error);
	            });
	    }
	    
	    function update() {
	    	if ($('#selectResult').val() == "-1") {
	            alert("請選擇處分");
	            return;
	        }

	    	let data = new FormData();
	    	data.append("reportId", $('#reportId').text());
	    	data.append("bidItemId", $('#bidItemId').text());
	    	data.append("mbrId", $('#mbrId').text());
	    	data.append("result", $('#selectResult').val());
	    	data.append("note", $('#inputNote').val());

	    	fetch(`${pageContext.request.contextPath}/back/biditemreport?action=update`, {
	    		method: 'POST',
	    		body: data})
	    			
	    		.then(function(response) {
	    		    return response.json();
	    		})
	    		.then(function(data) {
	    			$('#bidItemReportModal').modal('hide');
	    			location.reload();
	    		})
	    		.catch(function(error) {
	    			console.log(error);
	    		})
	    }
	</script>
</body>
</html>
