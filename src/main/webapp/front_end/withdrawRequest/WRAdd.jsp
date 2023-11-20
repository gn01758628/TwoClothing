<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>虛擬錢包提款申請</title>

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
<style>
     *{ 
         box-sizing: border-box; 
     } 
/*     body{ */
/*         display: flex; */
/*         justify-content: center; */
/*         align-items: center; */
/*     } */
    main.container{
        /* border: 1px solid black; */
        background-color:  rgba(230, 192, 192, 0.726);
        display: flex;
        /* justify-content: center; */
        align-items: center;
        flex-direction: column;
/*         height: calc(100vh - 252px); */
        width: 100%;
        margin-top: 150px;
        padding: 20px;
        margin-top:95px;
    }
    
    form.form_add h1 span{
    	background-color: lightgray;
    	color:rgb(232, 138, 107);
    	border-radius: 10px;
    	border:3px solid white;
    	padding:3px;
/*     	margin-top:50px; */
    }

    form.form_add{
        /* border: 1px solid salmon; */
        height: 90%;
        width: 60%;
        display: flex;
        justify-content: center;
        align-items: center;
        flex-direction: column;
    }

    form.form_add div{
        /* border: 1px solid blue; */
        height: 50%;
        width: 40%;
        margin: 20px 0;
        display: flex;
        flex-direction: column;
        justify-content: center;
    }
    
    form.form_add div div.overage{
        border: 3px solid rgb(232, 138, 107);
        width: 100%;
        border-radius: 10px;
        background-color: white;
        color: rgb(232, 138, 107);
        text-align: center;
        font-size: 26px;
        font-weight: bold;
        margin-bottom: 50px;
    }

    form.form_add div span:nth-child(1){
        /* border: 1px solid black; */
        padding: 10px 0;
        width: 100%;
    }

    form.form_add div span div{
        display: inline-block;
        width: 50px;
        height: 50px;
        display: flex;
        justify-content: center;
        align-items: center;
        
    }

    form.form_add div span span:nth-child(2){
        border: 2px solid rgb(255, 255, 255);
        color: rgb(255, 255, 255);
        background-color: rgb(232, 138, 107);
        border-radius: 10px;
        padding: 4px;
        width: 90%;

    }

    form.form_add div span span.star{
        color: rgb(162, 29, 29);
    }

    form.form_add div span div img{
        width: 90%;

    }

    form.form_add div input{
        border-radius: 10px;
        border-color: rgb(223, 128, 128);
        background-color: rgb(245, 245, 245);
        height: 30px;
        width: 100%;
        margin: 20px 0;
    }
    form.form_add div input:focus{
        border-color: lightgray;
    }

    button#submit_form{
        width: 40%;
        height: 50px;
        background-color: rgb(243, 144, 144);
        border-radius: 10px;
        border-color: lightgray;
        margin: 70px 0;
        font-size:18px;
    }
    button#submit_form:hover{
        cursor: pointer;
        box-shadow: 5px 5px 10px rgba(143, 129, 129, 0.829);
    }

</style>
    <!--導覽列css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/header.css">
    <!--頁尾css-->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/chengHan/footer.css">
</head>
<body>
<!--放在最前面-->
<div class="headerHTML"></div>
    <main class="container">
<!--     <div class="err"> -->
<!--     <span></span> -->
<!--     </div> -->
        
        <form class="form_add">
        	<h1>虛擬錢包<span>提款申請</span></h1>
            <div>
                <div class="overage">
                    <span>餘額：$<span>${balance}</span></span>
                </div>
                <span>
                    <div><img src="${pageContext.request.contextPath}/images/withdrawRequestIcon/dollar.png" alt=""></div>
                    <span>提款金額：</span>
                    <span class="star">*</span>
                </span>
                <input type="text" placeholder="輸入金額" name="amount" >
            </div>
            <div>
                <span>
                    <div><img src="${pageContext.request.contextPath}/images/withdrawRequestIcon/user.png" alt=""></div>
                    <span>匯入帳號：</span>
                    <span class="star">*</span>
                </span>             
                <input type="text" placeholder="輸入帳號" name="mbrAccount">
            </div>
            <div>
                <span>
                    <div><img src="${pageContext.request.contextPath}/images/withdrawRequestIcon/note.png" alt=""></div>
                    <span>備註：</span>
                </span>             
                <input type="text" placeholder="備註" name="note">
            </div>

<!--             <input type="hidden"  name="choice" value="AddOne"> -->
            <input type="hidden"  name="choice" value="AddOne">
            <button type="button" id="submit_form" onclick="form_add_submit()">確認送出</button>

        </form>

    </main>
    <!--放在最後面-->
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
	</script>

    <script>
  
    	function form_add_submit(e){
        	
//         	提款金額不可超過餘額
        	let balance=$(".overage span span").text();
        	console.log("/"+balance);
        	let amount = $("input[name='amount']").val();
        	console.log("+"+amount);

        	if(balance<amount){
        		alert("餘額不足");
        		e.preventDefault();
        		return;
        	}
        	
            if($("input[name='amount']").val() === "" || $("input[name='mbrAccount']").val() === ""){
                alert("請填寫完整 !");
        		e.preventDefault();
                return;

            }

	   		var formData = $(".form_add").serialize();
   			
	   		let formDataUrlEncoded = new URLSearchParams(formData);
	   		
	         fetch("${pageContext.request.contextPath}/WithdrawRequest/withdraw?choice=AddOne", {
	             method: "post",
	             body: formDataUrlEncoded
	         })
	         .then(function (response) {
	         	return response.json();
	         })
	         .then(function (data) {
	         	console.log(data);
	         	switch(data.message){
	         		case "ok":
	         			window.alert("已成功申請！");
	         			break;
	         		case "out_of_e_wallet":
	         			window.alert("申請失敗！\n提款金額大於虛擬錢包餘額，請重新輸入提款金額。");
	         			break;
	         		default:
	         			window.alert(data.message);
	         			break;
	         	}
	         });
	         
	        var newBalance = balance - parseFloat($("input[name='amount']").val());
	      
	        $(".overage span span").text(newBalance);

        };

        
        
    </script>
</body>
</html>