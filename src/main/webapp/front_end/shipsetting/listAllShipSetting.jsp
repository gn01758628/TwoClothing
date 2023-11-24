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
<p class="rh_title">物流設定</p>


<table id="myTable"class="rh_tab2">
<thead>
    <tr>
        <th width="25%">收件人姓名</th>
        <th>收件人手機</th>
        <th>收件人地址</th>
        <th>修改</th>
        <th>刪除</th>
    </tr>
</thead>
<c:choose>
    <c:when test="${not empty ShipSetting}">
        <c:forEach var="ShipSetting" items="${ShipSetting}">
            <tr>
                <td width="25%">${ShipSetting.receiveName}</td>
                <td width="25%">${ShipSetting.receivePhone}</td>
                <td width="25%">${ShipSetting.receiveAddress}</td>
                <td width="25%">
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="修改">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="action" value="getOne_For_Update">
                    </form>
                </td>
                <td width="25%">
                    <form method="post" action="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" style="margin-bottom: 0px;">
                        <input type="submit" value="刪除">
                        <input type="hidden" name="mbrId"  value="${ShipSetting.mbrId}">
                        <input type="hidden" name="shipId"  value="${ShipSetting.shipId}">
                        <input type="hidden" name="action" value="delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </c:when>

</c:choose>
</table>
    <form method="post" action="<%=request.getContextPath()%>/front_end/shipsetting/addShipSetting.jsp" style="margin-bottom: 0px;">
         <input type="submit" value="新增">
         <input type="hidden" name="action" value="insert">
     </form>

</div>
</div>
</div>

<div class="clear"></div>
<div id="footer"></div>

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
</body>
</html>