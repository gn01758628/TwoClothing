<%@page import="com.google.gson.Gson"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
  
 	<style>
	
	.itemOrderDiv{
		border: 2px solid blue;
	}
	.orderDetailItem{
		border: 1px solid deeppink;
	}
	</style>



<title>已取消訂單</title>
</head>
<body>
<main> 
<div class="b-example-divider"></div>

  <div class="flex-shrink-0 p-3 bg-white" style="width: 280px;">
    <a href="itemorder.do?action= " class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom">
      <svg class="bi me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
      <span class="fs-5 fw-semibold">返回購物頁面</span>
    </a>
    
        <div class="collapse show" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
            <li><a href="itemorder.do?action=buyer&buyer=${buyer}" class="link-dark rounded">所有訂單</a></li>
            <li><a href="itemorder.do?action=buyer0&buyer=${buyer}" class="link-dark rounded">待付款</a></li>
            <li><a href="itemorder.do?action=buyer1&buyer=${buyer}" class="link-dark rounded">待出貨</a></li>
            <li><a href="itemorder.do?action=buyer2&buyer=${buyer}" class="link-dark rounded">待收貨</a></li>
            <li><a href="itemorder.do?action=buyer3&buyer=${buyer}" class="link-dark rounded">已完成</a></li>
            <li><a href="itemorder.do?action=buyer4&buyer=${buyer}" class="link-dark rounded">已取消訂單</a></li>
          </ul>
        </div>
    
  </div>
<div>
AAAAAAAA
</div>
</main>	


</body>
</html>
