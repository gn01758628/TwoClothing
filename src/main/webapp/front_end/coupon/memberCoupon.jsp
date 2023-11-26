<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.text.ParseException" %>

<c:set var="now" value="<%= new SimpleDateFormat(\"yyyy-MM-dd HH:mm:ss.S\").format(new Date()) %>" />

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
    
    <!--側邊連結css-->


    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">


</head>
<body>
<%
	String servletPath = request.getContextPath() + "/MemberCouponServlet.check";
%>
<div class="headerHTML"></div>

<div id="hy_con">
<div id="con_lf">
<!--=============================================插入連結的地方-->

</div>
<div id="con_rh">
<div class="con_rh_con"><br></br>
<table id="example" class="display" style="width: 100%">
	<thead >
		<tr>
			<th>優惠卷編號</th>
			<th>優惠卷名稱</th>
			<th>優惠卷使用日期</th>
			<th>優惠卷失效日期</th>
			<th>折扣類型</th>
			<th>折抵數值</th>
			<th>最低金額條件</th>
			<th>當前狀態</th>
			<th>使用狀態</th>
			
			
		</tr>
	</thead>
	
	
	
	<c:forEach var="coupon" items="${membersCouponDTOList}">
		
		<tr>
			<td>${coupon.couponId}</td>
			<td>${coupon.cpnName}</td>
			<td>${coupon.createDate}</td>
			<td>${coupon.expireDate}</td>
			<td>${couponDisTypeMap[coupon.disType]}</td>
			<td>${coupon.disValue}</td>
			<td>${coupon.minAmount}</td>
			<td>
				<c:choose>
			        <c:when test="${coupon.createDate gt now}">
			        	尚未生效
			        </c:when>
			        <c:when test="${not empty coupon.expireDate and coupon.expireDate lt now}">
						已失效
					</c:when>
			        <c:otherwise>
			        	生效中
			        </c:otherwise>
			    </c:choose>
			</td>
			<td>
				<c:choose>
			        <c:when test="${coupon.couponStatus eq 0}">
			        	可使用
			        </c:when>
			        <c:when test="${not empty coupon.expireDate and coupon.expireDate lt now}">
						已失效
					</c:when>
			        <c:otherwise>
			        	生效中
			        </c:otherwise>
			    </c:choose>
			
			</td>
			
			

		</tr>
	</c:forEach>
</table>
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
	<script	src="https://cdn.datatables.net/1.13.5/js/jquery.dataTables.min.js"></script>              <!-- ●●js  for jquery datatables 用 -->
	<link rel="stylesheet" href="https://cdn.datatables.net/1.13.5/css/dataTables.jqueryui.min.css" /> <!-- ●●css for jquery datatables 用 -->
	
<!--Sweet Alert-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/gordon/memberArea.css">
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
            url: "${pageContext.request.contextPath}/front_end/members/sideMembers.jsp",
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
<!-- ●● jquery datatables 設定 -->
	<script>
		$(document).ready(function() {
			$('#example').DataTable({
				"lengthMenu": [5, 10, 20, 50, 100],
				"searching": true,  //搜尋功能, 預設是開啟
			    "paging": true,     //分頁功能, 預設是開啟
			    "ordering": true,   //排序功能, 預設是開啟
			    "language": {
			    	url: "https://cdn.datatables.net/plug-ins/1.11.3/i18n/zh_Hant.json" ,
			    	"search": "搜尋： (複合查詢請用空白隔開)"
			    },
			    "order": [[ 0, "asc" ]],
			    "columnDefs":[
			    	{
			    		targets:[0], orderable:false
			    	}
			    ]
			    
			});
		});
	</script>
</body>
</html>

