<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>

<form action="${pageContext.request.contextPath}/members/SendEmailServlet" method="post">
    <input type="hidden" name="action" value="verificationEmail"/>
    <table align="center" width="30%">

        <tr>
            <td>郵箱</td>
           <td><input id="email" type="text" name="email" value="<c:out value='${user.email}' />"/> </td>
        </tr>
        <tr>
            <td align="center" >
            <button id="action" name="action" value ="verificationEmail" type="submit">驗證</button> </td>
      
            <td><button id="cancel_id" type="button">取消</button> </td>
        </tr>
    </table>
</form>


<script>
//获取取消按钮元素
var cancelButton = document.getElementById("cancel_id");

// 添加点击事件处理程序
cancelButton.addEventListener("click", function() {
    window.location.href = "/TwoClothing/index.jsp"; // 返回首页的 URL
});
</script>
</body>
</html>