<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员中心</title>
    <style>
    /* 移除按钮的边框，背景颜色和文本装饰 */
input {
  background: none;
  border: none;
  padding: 0;
  font: inherit;
  text-decoration: underline;
  cursor: pointer;
  color: blue; /* 链接文本颜色 */
}

/* 鼠标悬停时改变文本颜色 */
input:hover {
  color: red; /* 链接文本颜色（悬停时） */
}

/* 按钮激活时改变文本颜色 */
input:active {
  color: green; /* 链接文本颜色（激活时） */
}

/* 按钮失效时改变文本颜色 */
input:disabled {
  color: gray; /* 链接文本颜色（失效时） */
  cursor: not-allowed; /* 鼠标指针样式（失效时） */
}
    
    </style>
</head>
<body>
	<%
	// 获取存储在 session 中的用户数据
	com.twoclothing.model.members.Members user = (com.twoclothing.model.members.Members) session.getAttribute("user");
	%>
	<a href='${pageContext.request.contextPath}/index.jsp'>回首頁</a>
    <h1>会员中心</h1>

    <h2>个人信息</h2>
    <p><strong>會員編號:</strong> ${user.mbrId}</p>
    <p><strong>用户名:</strong> ${user.mbrName}</p>
    <p><strong>邮箱:</strong> ${user.email}</p>
    <!-- 在这里添加更多个人信息字段 -->

    <h2>设置</h2>
	
    <h2>其他功能</h2>
    <ul>

    	
	    <li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do">
				<input type="hidden" name="mbrId" value="${user.mbrId}">
				<input type="hidden" name="action" value="getAll_For_MbrId">
				<input type="submit" value="會員物流設定">
				
			</FORM>
		</li>
		

    </ul>

    <p><a href="logout.jsp">注销</a></p>
</body>
</html>
