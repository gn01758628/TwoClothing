<%@ page import="com.twoclothing.model.aproduct.itemreport.*"%>
<%@ page import="com.twoclothing.chi.controller.*"%>
<%@ page import="com.twoclothing.chi.service.*"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <title>ItemReportList</title>
    
    <style>
		.table thead th {
			white-space: nowrap;
		}
	
	    .table tbody td {
	        width: 90px;
	    }
	
	    .table tbody td:nth-child(5), .table tbody td:nth-child(8) {
	        width: 110px;
	    }
	    
	    .no-border {
	        border: none !important;
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
    <div class="container mt-5">
        <h1 class="text-center">商品檢舉清單</h1>

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

<%--         <c:if test="${not empty errorMsgs}"> --%>
<!--             <ul> -->
<%--                 <c:forEach var="message" items="${errorMsgs}"> --%>
<%--                     <li style="color: red; list-style-type: none">${message}</li> --%>
<%--                 </c:forEach> --%>
<!--             </ul> -->
<%--         </c:if> --%>

        <div class="pagination justify-content-center mt-3">
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
        </div>
    </div>

    <script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js"></script>

    <script>
	    function showDetail(reportId) {
	        event.preventDefault();
	        let url = "${pageContext.request.contextPath}/back/itemreport?action=getOne&reportId=" + reportId;

	        fetch(url)
	            .then(function (response) {
	                return response.json();
	            })
	            .then(function (data) {
	                let status = "";
	                if (data.rStatus == 0) {
	                    status = "待審核";
	                } else if (data.rStatus == 1) {
	                    status = "已審核";
	                }

	                let result = "";
	                if (data.result == 0) {
	                    result = "處分";
	                } else if (data.result == 1) {
	                    result = "不處分";
	                }
	                
	                $('#reportId').text(data.reportId);
	     	    	$('#itemId').text(data.itemId);
	     	    	$('#mbrId').text(data.mbrId);
	     	    	$('#empId').text(data.empId);
	     	    	$('#reportDate').text(data.reportDate);
	     	    	$('#description').text(data.description);
	     	    	$('#status').text(status);
	     	    	$('#auditDate').text(data.auditDate);
	     	    	
	     	    	if (result == "") {
	     	    		$('#result').html(`
	     	    			<select name="result">
	     	    			<option value="-1">選擇處分</option>
	     	    			<option value="0">處分</option>
	     	    			<option value="1">不處分</option>
	     	    			</select>
	     	    		`);
	     	    		
	     	    		$('#note').html(`
	     	    			<input type="text" id="note" name="note" size="70"/>
		     	    	`);
	     	    		
	     	    		$('#update').show();
	     	    	} else {
	     	    		$('#result').text(result);
	     	    		
	     	    		$('#note').text(data.note);
	     	    		
	     	    		$('#update').hide();
	     	    	}

	     	    	let html = `<li class="list-group-item" id="report">An itemreport</li>`;
	     	    	$('#itemReportModal').modal('show');
	            })
	            .catch(function (error) {
	                console.log(error);
	            });
	    }
	    
	    function update() {
	    	console.log("update觸發");
	    	if ($('#result').val() === "-1") {
	            alert("請選擇處分");
	            return;
	        }
	    	
	    	fetch(`${pageContext.request.contextPath}/back/itemreport?action=update`, {
	    			method: 'POST', headers: {'Content-Type': 'application/json',
	    			},
	    			body: JSON.stringify({
	    				reportId: $('#reportId').val(),
	    				result: $('#result').val(),
	    				note: $('#note').val(),
	    				}),
	    			})
	    			.then(function(response) {
	    		        return response.json();
	    		    })
	    		    .then(function(data) {
	    		        $('#itemReportModal').modal('hide');
	    		        location.reload();
	    		    })
	    		    .catch(function(error) {
	    		    	console.log(error);
	    		    });
	    }
	</script>
</body>
	<div class="modal fade" id="itemReportModal" tabindex="-1" aria-labelledby="itemReportModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
	      		<div class="modal-header">
	      			<h5 class="modal-title" id="itemReportModalLabel">商品檢舉明細</h5>
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
									<td id="itemId"></td>
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
									<td id="description"></td>
								</tr>
								<tr>
									<td>審核狀態</td>
									<td id="status"></td>
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
		      		<button class="btn btn-secondary" id="update" onclick="update()">確定</button>
			    	<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
			    </div>
			</div>
		</div>
	</div>
</html>
