<%--suppress ALL --%>
<%--suppress JSUnusedLocalSymbols --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!doctype html>
<html lang="zh-hant" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${bidItem.bidName}</title>
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
    <!--此頁面的css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/BidItemDetail.css">
    <!--檢舉膜態框css-->
    <style>
    	.report {
        	width: 100%;
        }
        
        .report-body {
  			display: flex;
        	align-items: center;
        	justify-content: center;
        }
        
        .title-description {
        	margin-top: 10px;
        }
        
        td.bidItemId {
        	padding-left: 19px;
        }
        
        .inputDescription {
        	margin-top: 10px;
        	margin-left: 16px;
        }
        
        .modal-footer button:hover, button:focus, button:active, button:visited {
        	outline: none;
	    	box-shadow: none !important;
        }
    </style>
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>

<!--放在最前面-->
<div class="headerHTML"></div>

<c:if test="${bidItem.bidStatus != 4}">
    <div class="container text-center pt-1">
        <h1>此商品並非上架狀態</h1>
    </div>
</c:if>
<!--商品詳情-->
<div class="container pt-5">
    <div class="row">
        <div class="col-md-5">
            <!-- 主要商品圖片 -->
            <div class="text-center">
                <img src="#" class="img-fluid mb-3 bigIMG" alt="商品主圖">
            </div>

            <!-- 縮略圖 -->
            <div class="thumbnails-container">
                <div class="thumbnail-right">
                    <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=1"
                         class="img-fluid smallIMG" alt="商品主圖">
                </div>
                <div class="thumbnail-left">
                    <img src="${pageContext.request.contextPath}/ReadItemIMG/biditem?id=${bidItem.bidItemId}&position=2"
                         class="img-fluid smallIMG" alt="商品附圖">
                </div>
            </div>
        </div>

        <div class="col-md-7">
            <!-- 商品資訊 -->
            <div class="product-info">
                <div class="row mb-3">
                    <div class="col-md-8 d-flex align-items-center">
                        <h2>${bidItem.bidName}</h2>
                    </div>
                    <div class="col-md-4 d-flex justify-content-end">
                        <a href="${pageContext.request.contextPath}/front/chatroom/${bidItem.mbrId}.check"
                           class="link-no-style">
                            <button type="button" class="btn btn-success me-2">聯絡賣家</button>
                        </a>
                        <button type="button" class="btn btn-danger" id="reportBtn" onclick="showDetail(${bidItem.bidItemId})">檢舉</button>
                    </div>
                </div>

                <!-- 倒數計時 -->
                <div class="mb-3 countdown-container">
                    <p id="countdown" class="mb-0">距離結束還有：
                        <span id="days" class="countdown-number"></span><span class="countdown-unit">天</span>
                        <span id="hours" class="countdown-number"></span><span class="countdown-unit">時</span>
                        <span id="minutes" class="countdown-number"></span><span class="countdown-unit">分</span>
                        <span id="seconds" class="countdown-number"></span><span class="countdown-unit">秒</span>
                        <span class="end-date">結束日期：2023-12-06</span>
                    </p>
                </div>

                <div class="mb-3 d-flex align-items-baseline">
                    <div class="fw-bold min-width">
                        <c:if test="${not empty bidRecordList}">
                            目前出價：
                        </c:if>
                        <c:if test="${empty bidRecordList}">
                            起標價：
                        </c:if>
                    </div>
                    <div class="info-value">
                        <span class="currentBid">
                            <c:if test="${not empty bidRecordList}">$
                                ${bidRecordList[0].bidAmount}
                            </c:if>
                            <c:if test="${empty bidRecordList}">
                                ${bidItem.startPrice}
                            </c:if>
                        </span>
                        <span class="bid-count">(${fn:length(bidRecordList)}次出價)</span>
                    </div>
                    <div class="fw-bold min-width">最高出價者：</div>
                    <div class="info-value">${mbrMap[bidRecordList[0].mbrId]}</div>
                    <!--隱藏的直購價-->
                    <c:if test="${not empty bidItem.directPrice}">
                        <span id="directPrice" style="display: none">${bidItem.directPrice}</span>
                    </c:if>
                </div>

                <div class="mb-3 d-flex align-items-baseline">
                    <div class="fw-bold min-width">賣家：</div>
                    <div class="info-value">
                        <a href="${pageContext.request.contextPath}/SellerHome/home?mbrId=${bidItem.mbrId}">
                        ${sellerNameArr[0]}(${sellerNameArr[1]})
                            <strong>前往賣家賣場</strong>
                        </a>
                    </div>
                </div>

                <!-- 出價框 -->
                <div class="mb-3 mt-3">
                    <div class="container">
                        <div class="row align-items-center">
                            <div class="col-5 d-flex justify-content-center">
                                <img src="/TwoClothing/images/HappyBidding.png"
                                     alt="HappyBid" class="img-fluid happyIMG">
                            </div>
                            <div class="col-7 d-flex justify-content-center">
                                <div class="p-3 bid-container">
                                    <div class="row justify-content-end">
                                        <div class="col-12">
                                            <div class="row gx-2 justify-content-end">
                                                <div class="col-9">
                                                    <div class="input-group">
                                                        <span class="input-group-text">$</span>
                                                        <label for="bidAmountInp">
                                                            <input type="number" class="form-control"
                                                                   placeholder="請直接輸入金額" id="bidAmountInp">
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-3">
                                                    <button type="button" style="display: none" data-bs-toggle="modal"
                                                            data-bs-target="#staticBackdrop"
                                                            id="fakeBtn">立即結標
                                                    </button>
                                                    <button type="button" class="btn btn-warning rounded-pill"
                                                            id="bidBtn">
                                                        我要出價
                                                    </button>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12" id="bidHelper">
                                            <div class="row justify-content-end">
                                                <div class="col-auto">
                                                    <div id="bidHelp" style="display:none;">
                                                        最低出價金額$<span id="minRequest"></span>，全站競標出價增額皆為3%
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="col-12" id="wrongHelper">
                                            <div class="row justify-content-end">
                                                <div class="col-auto">
                                                    <div id="wrongMsg" class="h6">
                                                        <!--錯誤訊息-->
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                        <c:if test="${not empty bidItem.directPrice}">
                                            <div class="col-12" id="bidBtn_hr">
                                                <hr>
                                            </div>
                                            <div class="col-12">
                                                <div class="row justify-content-end">
                                                    <div class="col-auto align-vertical">
                                                    <span>直購價：<span class="h3"
                                                                       id="directPriceHelp"></span></span>
                                                    </div>
                                                    <div class="col-auto">
                                                        <button type="button" class="btn btn-danger rounded-pill"
                                                                data-bs-toggle="modal"
                                                                data-bs-target="#staticBackdrop" id="bidDirectBtn">立即結標
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:if>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="mb-3 d-flex align-items-baseline">
                    <div class="fw-bold min-width">新舊程度：</div>
                    <div class="info-value">${grade}</div>
                    <div class="fw-bold min-width">商品尺寸：</div>
                    <div class="info-value">${size}</div>
                </div>

                <div class="mb-3 d-flex align-items-baseline">
                    <div class="fw-bold min-width">商品詳述：</div>
                    <div class="info-value">
                        <pre>${bidItem.detail}</pre>
                    </div>
                </div>

            </div>
        </div>

    </div>
</div>

<!-- 確認出價模態框 -->
<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
     aria-labelledby="staticBackdropLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="staticBackdropLabel">出價確認</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <p>是否確定出價？出價成功後將無法取消。</p>
                <div class="table-responsive">
                    <table class="table table-hover table-no-border">
                        <tbody>
                        <tr>
                            <th scope="row" class="w-25">下標帳號</th>
                            <td>
                                <c:if test="${not empty sessionScope.user.email}">
                                    ${sessionScope.user.email}
                                </c:if>
                                <c:if test="${empty sessionScope.user.email}">
                                    非會員無法進行出價操作
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th scope="row" class="w-25">出價方式</th>
                            <td id="bidType"></td>
                        </tr>
                        <tr>
                            <th scope="row" class="w-25">出價金額</th>
                            <td id="bidAmount2"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal" id="cancelBid">取消</button>
                <button type="button" class="btn btn-primary" id="commitBid">確定出價</button>
            </div>
        </div>
    </div>
</div>

<!--出價紀錄-->
<div class="container custom-table-container mb-4">
    <div class="row">
        <table class="custom-table custom-table-striped">
            <thead>
            <tr>
                <th>出價者</th>
                <th>出價金額</th>
                <th>出價時間</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="bidRecord" items="${bidRecordList}">
                <tr>
                    <td>${mbrMap[bidRecord.mbrId]}</td>
                    <td>${bidRecord.bidAmount}</td>
                    <td>${bidRecord.bidTime}</td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </div>
</div>

<!-- 檢舉模態框 -->
<div class="modal fade" id="bidItemReportModal" tabindex="-1" aria-labelledby="bidItemReportModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg report">
		<div class="modal-content">
	      	<div class="modal-header">
	      		<h5 class="modal-title" id="bidItemReportModalLabel">商品檢舉</h5>
	      	</div>
	      		
	      	<div class="modal-body report-body">
			    <div class="card" style="width: 45rem;">
				    <div class="card-body">
					    <table>
							<tr>
								<td>商品編號</td>
								<td id="bidItemId" class="bidItemId"></td>
							</tr>
							<tr>
								<td>
									<div class="title-description">檢舉原因</div></td>
								<td>
									<input type="text" id="inputDescription" class="inputDescription" name="inputDescription" size="63"/>
								</td>
							</tr>
						</table>
				    </div>
		        </div>
		   </div>
		      	
		   <div class="modal-footer">
		      	<button class="btn btn-secondary" id="insert" onclick="insertReport()">送出</button>
			    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">關閉</button>
			</div>
		</div>
	</div>
</div>

<!--放在最後面-->
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

<!--下一個JS檔需要的EL變數-->
<script>
    <c:if test="${bidItem.bidStatus != 4}">
    let isLeagal = false;
    </c:if>

    <c:if test="${bidItem.bidStatus == 4}">
    let isLeagal = true;
    </c:if>

    let needFormat = false;
    <c:if test="${empty bidRecordList}">
    needFormat = true;
    </c:if>

    let endTimeStr = "${bidItem.endTime}";

    let contextPath = "${pageContext.request.contextPath}";

    let bidItemId = ${bidItem.bidItemId};
    
    <% Integer mbrId = (Integer) session.getAttribute("mbrId"); %>
    function showDetail(bidItemId) {
	    event.preventDefault();

	    <c:if test="${mbrId == null}">
        	window.location.href = "${pageContext.request.contextPath}/front_end/members/registerLogin.jsp";
    	</c:if>
    	
    	<c:if test="${mbrId != null}">
	    	<c:if test="${bidItem.bidStatus == 6}">
		        Swal.fire ({
		        	backdrop: false,
		            title: "此商品並非上架狀態",
		            text: "非上架狀態的商品無法操作",
		            icon: "error",
		            confirmButtonText: "OK"
		        });
		        return;
		    </c:if>
    	
		    $('#bidItemId').text(bidItemId);
		    let html = `<li class="list-group-item" id="report">An bidItemreport</li>`;
		    $('#bidItemReportModal').modal('show');
	    </c:if>
	}
	
	function insertReport() {
		if ($('#inputDescription').val() == "") {
			alert("請填寫原因");
			return;
		}
		
		var url = "${pageContext.request.contextPath}/front/biditemreport?action=insert&bidItemId=${bidItem.bidItemId}&bidDescription=" + $('#inputDescription').val();
					
		$.ajax({
			type: "POST",
			url: url,
			success: function (data) {
				$('#bidItemReportModal').modal('hide');
				
				Swal.fire({
					backdrop: false,
			        title: "檢舉成功",
			        text: "請至我的檢舉查看",
			        icon: "success",
			        timer: 2700,
	        		showConfirmButton: false,
			        customClass: {
			        	popup: 'report-custom-popup-class',
			            title: 'custom-title-class',
			        },
			        iconColor: '#b0c4de',
			        didClose: () => {
			            location.reload();
			        }
			    });
			},
			error: function (xhr) {
				console.log(xhr);
			}
		});
	}
</script>
<!--此頁面的JS-->
<script src="${pageContext.request.contextPath}/js/chengHan/BidItemDetail.js"></script>

</body>
</html>
