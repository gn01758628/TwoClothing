<%@page import="org.hibernate.internal.build.AllowSysOut"%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.twoclothing.model.members.*"%>

 <% 
     Object account = session.getAttribute("user");                  // 從 session內取出 (key) account的值
     if (account == null) {                                             // 如為 null, 代表此user未登入過 , 才做以下工作
       session.setAttribute("location", request.getRequestURI());       //*工作1 : 同時記下目前位置 , 以便於login.html登入成功後 , 能夠直接導至此網頁(須配合LoginHandler.java) 
       response.sendRedirect(request.getContextPath()+"/front_end/members/registerLogin.jsp");   //*工作2 : 請該user去登入網頁(login.html) , 進行登入 
       return; 
     } 
     
    Object mbrStatus = session.getAttribute("mbrStatus");  
    if ( mbrStatus.toString().equals("0")) {                                            
        response.sendRedirect(request.getContextPath()+"/front_end/members/verificationEmail.jsp");  
        return; 
      } 
    System.out.println("mbrStatus="+mbrStatus);
 %>  
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
    <ul>
    	
    	<li>
    	 
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do">
				<input type="hidden" name="mbrId" value="${user.mbrId}">
				<input type="hidden" name="action" value="memberProfile">
				<input type="submit" value="個人資訊">
			</FORM>
		</li>
    	<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do">
				<input type="hidden" name="mbrId" value="${user.mbrId}">
				<input type="hidden" name="action" value="walletAndPoints">
				<input type="submit" value="錢包與點數">
			</FORM>
		</li>

    	<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/members/Members.do">
				<input type="hidden" name="mbrId" value="${user.mbrId}">
				<input type="hidden" name="action" value="userRating">
				<input type="submit" value="我的評價">
			</FORM>
		</li>
    	<li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do">
				<input type="hidden" name="mbrId" value="${user.mbrId}">
				<input type="hidden" name="action" value="getAll_For_MbrId">
				<input type="submit" value="會員物流設定">
			</FORM>
		</li>
    </ul>
	
    <h2>其他功能</h2>
    <ul>

    	
	
	    <li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
			<input type="hidden" name="buyMbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="buyBidOrder">
			<input type="submit" value="買家競標訂單">
			</FORM>
		</li>
	    <li>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do">
			<input type="hidden" name="sellMbrId" value="${user.mbrId}">
			<input type="hidden" name="action" value="sellBidOrder">
			<input type="submit" value="賣家競標訂單">
			</FORM>
		</li>
		

    </ul>

    <form action="${pageContext.request.contextPath}/members/Members.do" method="post">
        <input type="hidden" name="action" value="logout">
        <button type="submit">登出</button>
   	</form>
   	
   	
   	
   	<a href='${pageContext.request.contextPath}//members/Members.do?action=getAll'>會員後台</a>
</body>
</html>
