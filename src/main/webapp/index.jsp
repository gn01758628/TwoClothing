<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>測試用HTML</title>
<style>
/* 样式用于模态框 */
.modal {
	display: none;
	position: fixed;
	z-index: 1;
	left: 0;
	top: 0;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.7);
}

.modal-content {
	background-color: #fff;
	margin: 15% auto;
	padding: 20px;
	width: 60%;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
}
</style>
</head>
<body>
	<%
	// 获取存储在 session 中的用户数据
	com.twoclothing.model.members.Members user = (com.twoclothing.model.members.Members) session.getAttribute("user");
	%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


	<c:choose>
		<c:when test="${not empty user and not empty user.mbrName}">
			<p>欢迎，${user.mbrName}！</p>
		</c:when>
		<c:when test="${not empty user and not empty user.email}">
			<p>欢迎，${user.email}！</p>
			<form action="${pageContext.request.contextPath}/back_end/members/Members.do" method="post">
        <input type="hidden" name="action" value="logout">
        <button type="submit">登出</button>
    
    </form>
		</c:when>
		<c:otherwise>
			<!-- 在没有 user.mbrName 数据时，不显示 <p> 标签 -->
			<form
				action="${pageContext.request.contextPath}/front_end/members/registerLogin.jsp">
				<button type="submit">登入</button>
			</form>
<!-- 			<form -->
<%-- 				action="${pageContext.request.contextPath}/back_end/members/Members.do" --%>
<!-- 				method="post"> -->
<!-- 				<input type="hidden" name="action" value="logout"> -->
<!-- 				<button type="submit">登出</button> -->
<!-- 			</form> -->
		</c:otherwise>
	</c:choose>

	<h1>測試用首頁</h1>





</body>
</html>
