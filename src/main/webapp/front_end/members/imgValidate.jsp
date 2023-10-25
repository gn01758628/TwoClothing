
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script type="text/javascript">
    function refresh() {
        console.log("Refresh function called");
        var imgValidate = document.getElementById("imgValidate");
        imgValidate.src = "imgValidate.jsp?" + new Date().getTime();
    }

    </script>
</head>
<body>
    <img id="imgValidate" src="index.jsp" onclick="refresh()">
    <!-- 这里放你的页面内容 -->
</body>
</html>
<%@ page contentType="charset=UTF-8" language="java"
        import ="java.awt.*"
         import ="java.awt.image.BufferedImage"
          import="java.util.*"
         import="javax.imageio.ImageIO"
         pageEncoding="UTF-8"%>
         <%
%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
response.setHeader("Pragma", "no-cache");

//在記憶體中建立影象
  int width=60,height=20;
  BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
  //獲取畫筆
  Graphics g=image.getGraphics();
  //設定背景色
  g.setColor(new Color(200,200,200));
  g.fillRect(0,0,width,height);
  //取隨機產生的驗證碼(4位數字)
  Random rnd=new Random();
  int randNum=rnd.nextInt(8999)+1000;
  String randStr=String.valueOf(randNum);
  //將驗證碼存入session
  session.setAttribute("randStr",randStr);
  //將驗證碼顯示到影象中
  g.setColor(Color.black);
  g.setFont(new Font("", Font.PLAIN,20));
  g.drawString(randStr,10,17);
  //隨機產生100個干擾點，使影象中的驗證碼不易被其他程式探測到
    for (int i = 0; i < 100; i++) {
   
   
        int x=rnd.nextInt(width);
        int y=rnd.nextInt(height);
        g.drawOval(x,y,1,1);
    }
    //輸出影象到頁面
    ImageIO.write(image,"JPEG",response.getOutputStream());
    out.clear();
    out=pageContext.pushBody();

%>

