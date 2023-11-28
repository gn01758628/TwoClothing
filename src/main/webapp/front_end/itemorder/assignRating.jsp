<%--suppress ALL --%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="zh-hant" >
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>物流列表</title>
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
    <!--你們自己的css-->
    <!--不是外部檔案也無所謂-->
    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
    
    <style>
		.itemOrderContainer {
		    border: 1px solid #ddd; /* 加上框線 */
		    border-radius: 8px; /* 圓角 */
		    padding: 10px; /* 適度的內邊距 */
		    margin-bottom: 20px; /* 底部間距 */
		}
		
		.orderDetailDiv {
		    display: flex;
		    flex-direction: column;
		    gap: 10px; /* 元素間的垂直間距 */
		}
		
		.orderDetailItem {
		    display: flex;
		    width: 100%; /* 讓 orderDetailItem 與 orderDetailDiv 同寬 */
		    border: 1px solid #ddd; /* 加上框線 */
		    border-radius: 8px; /* 圓角 */
		    padding: 10px; /* 適度的內邊距 */
		}
		
		.orderDetailItem img {
		    width: 100px;
		    height: 100px;
		    border: 1px solid #ddd; /* 圖片加上框線 */
		    border-radius: 8px; /* 圓角 */
		    margin-right: 10px; /* 圖片右邊間距 */
		}
		
		.orderDetailText {
		    display: flex;
		    flex-direction: column;
		    width: 100%; /* 讓 orderDetailText 與 orderDetailItem 同寬 */
		}
		
		.itemOrderContainer p.ms-auto {
		    right: 0px;
		    width:auto;
		}

    </style>
    
    <style>
        /* 新增樣式 */
        .form-container {
            max-width: 100%;
            margin: 0 auto;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            margin-bottom: 20px;
        }

        .form-group label {
            font-weight: bold;
        }

        .form-group input {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        .form-group textarea {
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            resize: vertical; /* 允許垂直調整大小 */
        }

        .form-group button {
            padding: 10px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
    </style>
    
    <style>
	    .rating:not(:checked) > input {
	        position: absolute;
	        appearance: none;
	    }
	
	    .rating:not(:checked) > label {
	        float: right;
	        cursor: pointer;
	        font-size: 30px;
	        color: #666;
	    }
	
	    .rating:not(:checked) > label:before {
	        content: '★';
	    }
	
 	    .rating > input:checked + label:hover, 
 	    .rating > input:checked + label:hover ~ label, 
 	    .rating > input:checked ~ label:hover, 
 	    .rating > input:checked ~ label:hover ~ label, 
 	    .rating > label:hover ~ input:checked ~ label { 
 	        color: #e58e09; 
 	    } */
	
 	    .rating:not(:checked) > label:hover, 
 	    .rating:not(:checked) > label:hover ~ label { 
 	        color: #ff9e0b; 
 	    } 
	
	    .rating > input:checked ~ label {
	        color: #ffa723;
	    }
	    .rating > input{
	        display:none;
	    }
    
    
    </style>
    
    <!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
    
    
    
    
    <!--側邊連結css-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">

    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<div class="headerHTML"></div>

<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->

</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>

<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 
	<div class="status${itemOrder.orderStatus} itemOrderContainer border border-3 p-3">
	    
	    <div class="d-flex flex-column justify-content-end mt-3">
		    <a href="/TwoClothing/SellerHome/home?mbrId=${itemOrder.sellMbrId}" class="mb-2"><button type="button" class="btn btn-primary">查看賣家賣場</button></a>
		</div>
		    
		    
		    
		<c:forEach var="orderDetails" items="${orderDetailsList}">
		
		    <a href="${pageContext.request.contextPath}/Itemfront/itemlist?goto=${orderDetails.compositeKey.itemId}"><!-- 添加這一行 -->
		        <div class="orderDetailDiv">
		            <div class="orderDetailItem">
		                <img src="${pageContext.request.contextPath}/ReadItemIMG/item?id=${orderDetails.compositeKey.itemId}&position=1" alt="商品的第一張圖片" class="mx-auto" loading="lazy" >
		                <div class="orderDetailText">
		                    <pre class="productName">商品名稱 : ${itemMap[orderDetails.compositeKey.itemId].itemName}</pre>
		                    <pre>數量 : ${orderDetails.quantity}</pre>
		                   
							<c:choose>
							    <c:when test="${orderDetails.discountPrice eq 0}">
				                    <pre>總價 : ${orderDetails.price}</pre>
							    </c:when>
							    <c:otherwise>
				                    <pre style="text-decoration: line-through;">總價 : ${orderDetails.price * orderDetails.quantity}</pre>
				                    <pre>折扣價 : ${orderDetails.buyingPrice}</pre>
							    </c:otherwise>
							</c:choose>
														
		                </div>
		            </div>
		        </div>
		    </a>
		    
		</c:forEach>
	    
	     <!-- 新增表單樣式 -->
                <div class="form-container">
                    <h4>訂單詳細資訊</h4>

				    <div class="row">
				        <div class="col-md-6 mb-3">
				            <label for="orderId" class="form-label">訂單編號</label>
				            <input type="text" class="form-control" id="orderId" value="${itemOrder.orderId}" readonly>
				        </div>
				
				        <div class="col-md-6 mb-3">
				            <label for="orderStatus" class="form-label">訂單狀態</label>
				            <input type="text" class="form-control" id="orderStatus" value="${OrderStatusMap[itemOrder.orderStatus]}" readonly>
				        </div>
				    </div>
				    
				    <div class="row">
				        <div class="col-md-6 mb-3">
	                        <label for="finalAmount" class="form-label">訂單金額</label>
	                        <input type="text" class="form-control" id="finalAmount" value="${itemOrder.finalAmount}" readonly>
	                    </div>
	                    
						<div class="col-md-6 mb-3">
				            <label for="payType" class="form-label">付款類型</label>
				            <input type="text" class="form-control" id="payType" value="${itemOrder.payType eq 0 ? '會員錢包扣款' : '信用卡'}" readonly>
				        </div>
				    </div>
				    
				    <div class="row">
				        <div class="col-md-6 mb-3">
				            <label for="receiveName" class="form-label">收件人</label>
				            <input type="text" class="form-control" id="receiveName" value="${itemOrder.receiveName}" readonly>
				        </div>
				
				        <div class="col-md-6 mb-3">
				            <label for="receivePhone" class="form-label">收件人電話</label>
				            <input type="text" class="form-control" id="receivePhone" value="${itemOrder.receivePhone}" readonly>
				        </div>
				    </div>
				    
				    <div class="row">
				    
				        <div class=class="col-md-6 mb-3">
	                        <label for="receiveAddress">收件地址</label>
	                        <input type="text" class="form-control" id="receiveAddress"  rows="4" value="${itemOrder.receiveAddress}" readonly>
	                    </div>
	                    
				        <div class=class="col-md-6 mb-3">
	                        <label for="remarks">訂單備註</label>
	                        <textarea class="form-control" id="remarks"  rows="4" readonly>${itemOrder.remarks}</textarea>
	                    </div>
	                    
				    </div>
				    
				    
				    <form id="editorForm" method="post" action="${pageContext.request.contextPath}/front_end/itemorder/itemorder.check" style="margin-bottom: 0px;">
						
				    	
					    <c:choose>
						    <c:when test="${user.mbrId eq itemOrder.buyMbrId}">
						    	
			                    <div class="form-group">
								    <label for="rating">買家評價</label>
								    <div class="rating">
								        <input value="5" name="buyStar" id="star5" type="radio" >
								        <label title="text" for="star5"></label>
								        <input value="4" name="buyStar" id="star4" type="radio" >
								        <label title="text" for="star4"></label>
								        <input value="3" name="buyStar" id="star3" type="radio" >
								        <label title="text" for="star3"></label>
								        <input value="2" name="buyStar" id="star2" type="radio" >
								        <label title="text" for="star2"></label>
								        <input value="1" name="buyStar" id="star1" type="radio" >
								        <label title="text" for="star1"></label>
								    </div>
								</div>
								<div id="contentSize"></div>
								<div id="warningMessage"></div>
								<div class=class="col-md-6 mb-3">
			                        <label for="buyerRatingDesc">買家評價內容</label>
			                        <textarea id="texteditor" class="buyerRatingDesc form-control" name="content" rows="4"></textarea>
			                    </div>
								
							
							
						    </c:when>
						    <c:when test="${user.mbrId eq itemOrder.sellMbrId}">
			                    
	
									<div class="form-group">
									    <label for="rating">賣家評價</label>
									    <div class="rating">
									        <input value="5" name="sellStar" id="star5" type="radio" >
									        <label title="text" for="star5"></label>
									        <input value="4" name="sellStar" id="star4" type="radio" >
									        <label title="text" for="star4"></label>
									        <input value="3" name="sellStar" id="star3" type="radio" >
									        <label title="text" for="star3"></label>
									        <input value="2" name="sellStar" id="star2" type="radio" >
									        <label title="text" for="star2"></label>
									        <input value="1" name="sellStar" id="star1" type="radio" >
									        <label title="text" for="star1"></label>
									    </div>
									</div>
									<div id="contentSize"></div>
									<div id="warningMessage"></div>
							        <div class=class="col-md-6 mb-3">
				                        <label for="buyerRatingDesc">賣家評價內容</label>
				                        <textarea id="texteditor" class="sellerRatingDesc form-control" name="content" rows="4"></textarea>
				                    </div>
	
						    </c:when>
						</c:choose>
						<div class="form-group">
						
							<input type="hidden" name="orderId" value="${itemOrder.orderId}">
			                <input type="hidden" name="action" value="assign_Rating" >
	                        <button id="submitButton" type="button" class="btn btn-primary" style="margin-top:20px" ">評價訂單</button>
	                    </div>
						
					</form>
                    

                   
                </div>
                
	</div>
	



<!--!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!--> 



</div>
</div>
</div>





<br><br><br><br><br><br><br>
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
    
    $(document).ready(function () {
        // 使用 AJAX 請求加載其他內容
        $.ajax({
            url: "${pageContext.request.contextPath}/front_end/itemorder/sideBuyerOrder.jsp",
            method: "GET",
            success: function (data) {
                $("#con_lf").html(data);
            },
            error: function (xhr, status, error) {
                console.error("Error loading content:", error);
            }
        });
    });
    


</script>

<!-- 引入 CKEditor 5 classic樣板 -->
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/ckeditor.js"></script>
<!-- 引入 CKEditor 5 classic樣板中文語系 -->
<script src="https://cdn.ckeditor.com/ckeditor5/40.0.0/classic/translations/zh.js"></script>

<script>
	let editor;
	
	// CKEditor5 生成
	ClassicEditor
	    .create(document.querySelector('#texteditor'), {
	    	//UI語言設置
	        language: 'zh',
	        //加載 圖片上傳器
	        extraPlugins: [MyAdapterPlugin],
	        //設定 工具列
	        toolbar: ["undo", "redo", "|", "heading", "|", "bold", "italic", "blockQuote", "link", "imageUpload", "|", "bulletedList", "numberedList", 'outdent', 'indent'],
	    })
	    .then(newEditor => {
	    	editor = newEditor;
	    	editor.model.document.on('change:data', () => {
	    		const submitButton = $('#submitButton');
	            const warningMessage = $('#warningMessage');
	
	            editor.model.document.on('change:data', () => {
	            	// 當編輯器內容發生變化時計算內容大小
	                const contentLength = getByteCount(editor.getData()); // 內容的字節數，考慮中文和英文字符差異
	                const contentSizeInMB = contentLength / (1024 * 1024); // 轉換為MB
	                const formattedSize = contentSizeInMB.toFixed(3); // 保留三位小數
	
	                // 顯示內容大小
	                $('#contentSize').text('內容大小：'+formattedSize+' MB');
	
	                // 檢查內容大小是否超過1.9MB
	                if (contentSizeInMB > 1.9) {
	                    // 超過1MB，顯示警告並禁用提交按鈕
	                    warningMessage.text('警告：文本大小已超過儲存上限(1.9MB)');
	                    warningMessage.css('color', 'red');
	                    submitButton.prop('disabled', true);
	                } else {
	                    // 未超過1MB，清除警告並啟用提交按鈕
	                    warningMessage.text('');
	                    submitButton.prop('disabled', false);
	                }
	            });
	            
	        });
	    })
	    .catch(error => {
	        console.error();
	    }
	);
	
	
	// 表單送出按鈕事件處理
	$('#submitButton').on('click', function (event) {
	    event.preventDefault();
	    
		const formData = new FormData();
	    
	    formData.append('orderId','${itemOrder.orderId}');
	    formData.append('action','assign_Rating');
	
	    const content = editor.getData();
	    formData.append('content', content);
	 // 找到被選中的 radio 按鈕
	    const selectedSellerRating = document.querySelector('input[name="sellStar"]:checked');
	    const selectedBuyerRating = document.querySelector('input[name="buyStar"]:checked');

	    // 檢查是否有選中的按鈕
	    if (selectedSellerRating) {
	        let ratingValue = selectedSellerRating.value;
	        formData.append('sellerRating', ratingValue);
	    } 
	
	    if (selectedBuyerRating) {
	        let ratingValue = selectedBuyerRating.value;
	        formData.append('buyerRating', ratingValue);
	    } 
	    
	    // 創建一個FormData對象，用於儲存表單數據
	    
	
	    // 使用$.ajax()发送FormData到服务器的Servlet路径（假设是/upload-form-data）
	    $.ajax({
	        url: '/TwoClothing//front_end/itemorder/itemorder.check',
	        type: 'POST',
	        data: formData,
	        processData: false,
	        contentType: false,
	        success: function (data) {
	        	Swal.fire({
	                title: "評價成功",
	                text: "感謝你的評價!",
	                icon: "success"
	            }).then((result) => {
	            	history.back();
	            });
	        },
	        error: function (xhr, status, error) {
	        	try {
	                // 嘗試解析後端返回的JSON錯誤訊息
	//	                var errorMsgs = JSON.parse(xhr.responseText);
	//	                var errorMsgDiv = $('#errorMsgs'); // 要顯示錯誤訊息的HTML元素
	
	//	                // 清空錯誤訊息
	//	                errorMsgDiv.empty();
	
	//	                // 將錯誤訊息插入到HTML元素中
	//	                for (var key in errorMsgs) {
	//	                    if (errorMsgs.hasOwnProperty(key)) {
	//	                        var errorMsg = errorMsgs[key];
	//	                        errorMsgDiv.append('<p style="color:red;">' + errorMsg + '</p>');
	//	                    }
	//	                }
	        		
	            } catch (e) {
	                // 如果解析JSON失敗，可以顯示一個通用的錯誤訊息
	                console.error('Error parsing JSON response:', e);
	                $('#errorMsgs').text('伺服器錯誤。');
	            }
	        }
	    });
	});
	
//======================================== 以下為function ========================================
	
	function getByteCount(str) {
	    let byteCount = 0;
	    for (let i = 0; i < str.length; i++) {
	        const charCode = str.charCodeAt(i);
	        if (charCode <= 0x7F) {
	            // ASCII字符，佔據1個字節
	            byteCount += 1;
	        } else if (charCode <= 0x7FF) {
	            // 此範圍內的字符，如中文，佔據3個字節
	            byteCount += 3;
	        } else if (charCode <= 0xFFFF) {
	            // 此範圍內的字符，佔據3個字節
	            byteCount += 3;
	        } else {
	            // 其他字符，佔據4個字節（JavaScript中的UTF-16字符）
	            byteCount += 4;
	        }
	    }
	    return byteCount;
	}

	
	class MyUploadAdapter {
	    constructor(loader) {
	        this.loader = loader;
	    }

	    upload() {
	        return new Promise((resolve, reject) => {
	            const reader = this.reader = new window.FileReader();
	            reader.addEventListener('load', () => {
	                const img = new Image();
	                img.src = reader.result;
	            	const userWidth = 200;
	                const userHeight = 200;
	                let maxWidth = img.width;
	                let maxHeight = img.height;

	                
	                if (!isNaN(userWidth) && !isNaN(userHeight)) {
	                    maxWidth = parseInt(userWidth);
	                    maxHeight = parseInt(userHeight);
	                }
	                
	                
	                img.onload = () => {
	                    const canvas = document.createElement('canvas');
	                    const ctx = canvas.getContext('2d');
	                    let width = img.width;
	                    let height = img.height;

	                    // 調整圖片大小
	                    width *= maxWidth / width;
	                    
	                    height *= maxHeight / height;

	                    // 設定Canvas的寬度和高度
	                    canvas.width = width;
	                    canvas.height = height;

	                    // 在Canvas上繪製調整後的圖片
	                    ctx.drawImage(img, 0, 0, width, height);

	                    // 將調整後的圖片轉換為Data URL
	                    const resizedImageData = canvas.toDataURL('image/jpeg'); // 這裡使用jpeg格式，你可以根據需要更改格式

	                    resolve({ default: resizedImageData });
	                };
	            });

	            reader.addEventListener('error', err => {
	                reject(err);
	            });

	            reader.addEventListener('abort', () => {
	                reject();
	            });

	            // 告訴FileReader用url格式讀取文件，用於設定img.src性質
	            this.loader.file.then(file => {
	                reader.readAsDataURL(file);
	            });
	        });
	    }

	    abort() {
	        this.reader.abort();
	    }
	}

	

	function MyAdapterPlugin(editor) {
	    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
	        return new MyUploadAdapter(loader);
	    };
	}



</script>
</body>
</html>