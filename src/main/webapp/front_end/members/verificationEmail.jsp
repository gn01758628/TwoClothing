<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/back_end/members/Members.do" method="post">
    <input type="hidden" name="method" value="regist"/>
    <table align="center" width="30%">

        <tr>
            <td>郵箱</td>
            <td><input id="email_id" type="text" name="email"/> </td>
        </tr>
        <tr>
            <td align="center" >
            <button id="regist_id" type="submit">驗證</button> </td>
      
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