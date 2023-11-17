<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>商品資料修改</title>

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料修改</title>
	<style>
        *{
            box-sizing: border-box;
        }
        main.main{
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }
	
	    h3 {
            margin-bottom: 40px;
	        color: #333;
	    }
        form{
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            width: 80%;
            min-height: 80%;
        }
	
	    table {
	        border-collapse: collapse;
	        width: 80%;
	        max-width: 600px;
            height: 650px;
	        margin: 0 auto;

	    }

        table tbody{
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            height:100%;
        }

        table tbody tr{
            flex: 1;
            width:79%;
            display: flex;
/*             justify-content: center; */
            flex-direction: row;
            align-items: center;
        }
        
         tr.imgtr01 td input{
         	width:150px;
         }
         tr.imgtr02 td input{
         	width:150px;
         }

        table tr td:nth-child(1){
            color: #fff;
            width: 100px;
            background-color:rgb(169, 168, 168);
            border-radius: 10px;
            text-align: center;
        }
	
	    table th, table td {
	    	/* width: 0%; */
	        padding: 8px;
	        text-align: left;
	    }
	
	    input[type="text"], select, input[type="file"] {
	        width: 100%;
	        padding: 8px;
            width: 300px;
            border-radius: 10px;
            height: 40px;
            border: 2px solid rgb(101, 101, 101)
	    }
		input[type="file"]{
			border:none;
		}
	
	    input[type="submit"] {
	        background-color: #9bc1e7;
	        color: #fff;
	        padding: 10px 20px;
	        border: none;
	        cursor: pointer;
            border-radius: 7px;
	    }
	
	    input[type="submit"]:hover {
	        background-color: #0057b3c2;
            
	    }
	   	img{
			max-width:100%;
			max-height:100%;
		}
		div#preview_img01{
			height: 70px;
		}
		div#preview_img02{
			height: 70px;
		}
		td.td_img01{
			padding:0px;
			margin-left: 70px;
		}
		td.td_img02{
			padding:0px;
			margin-left: 70px;
		}
		
		label{
			border:1px solid black;
			border-radius:10px;
			color:white;
			background-color:dodgerblue;
			padding:8px;
			cursor: pointer;
		}

	</style>
	
<link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css' rel='stylesheet'></link>

</head>
<body>
    <main class="main">
        <h3>商品資料修改</h3>
        
        <form method="post" action="/TwoClothing/Item/Update" enctype="multipart/form-data">
        <table>
            
<!--                 <td>商品</td> -->
<%--                 <input type="hidden" name="itemId" value="${item.itemId}"> --%>
            
            <tr>
                <td>商品名稱</td>
                <td><input type="text" name="itemName" value="${item.itemName}" size="20"/></td>
            </tr>
            <tr>
                <td>商品價格</td>
                <td><input type="text" name="price" value="${item.price}"></td>
            </tr>
            <tr>
                <td>新舊</td>
                <td>
                <select  name="grade">
<%--                     <option value="${item.grade}" selected>${item.grade}</option><span>請選擇商品新舊程度</span> --%>
                    <option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
                    <option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
                    <option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
                    <option value="3">8成新(有使用痕跡,少量瑕疵)</option>
                    <option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
                    <option value="5">破損商品(需要修補)</option>
                </select>
            </tr>
            <tr>
                <td>尺寸</td>
                <td>
                    <select  name="size">
<%--                         <option value="${item.size}" selected>${item.size}</option>  --%>
                        <option value="8">其他</option>
                        <option value="0">XS(含)以下</option>
                        <option value="1">S</option>
                        <option value="2">M</option>
                        <option value="3">L</option>
                        <option value="4">XL</option>
                        <option value="5">2XL</option>
                        <option value="6">3XL</option>
                        <option value="7">4XL(含)以上</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>商品狀態</td>
                <td>
                    <select name="itemStatus">
<%--                         <option value="${item.itemStatus}"></option> --%>
                        <option value="0">上架</option>
                        <option value="1">下架</option>
                        <option value="2">刪除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>數量</td>
                <td><input type="text" name="quantity"  value="${item.quantity}"></td>
            </tr>
            <tr>
                <td>描述</td>
                <td><input type="text" name="detail"  value="${item.detail}" size="200"/></td>
            </tr>
            
            <tr class="imgtr01">
                <td>主圖片</td>
                <td>
<!--                 <td><input class="form-control" type="file" id="image01" name="image01" -->
<!-- 							accept="image/jpeg, image/png"> -->
							
							<label class="btn btn-info form-control">
								<input id="image01" style="display:none;" type="file" name="image01">
								<i class="fa fa-photo"></i> 上傳圖片
							</label>
							
							
				</td>
				<td class="td_img01">
					<div id="preview_img01"></div>
				</td>
				
            </tr>
            <tr class="imgtr02">
                <td>副圖片</td>
                <td>
<!--                 <input class="form-control" type="file" id="image02" name="image02" -->
<!-- 							accept="image/jpeg, image/png"> -->
					<label class="btn btn-info form-control">
						<input id="image02" style="display:none;" type="file" name="image02">
						<i class="fa fa-photo"></i> 上傳圖片
					</label>
							
				</td>
				<td class="td_img02">
					<div id="preview_img02"></div>
				</td>
            </tr>
        
        
        </table>
        <br>
        <input type="hidden" name="forUpdate" value="update">
        <input type="hidden" name="itemId" value="${item.itemId}">
        <input type="submit" value="送出修改"></form>
    </main>	
    
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
	<script>
	
		
		$(document).ready(function() {
			var itemStatus = '${item.itemStatus}';
		    $("select[name='itemStatus'] option").each(function() {
		        if ($(this).val() === itemStatus) {
		            $(this).prop("selected", true);
		        }
		    });
			var grade = '${item.grade}';
		    $("select[name='grade'] option").each(function() {
		        if ($(this).val() === grade) {
		            $(this).prop("selected", true);
		        }
		    });
			var size = '${item.size}';
		    $("select[name='size'] option").each(function() {
		        if ($(this).val() === size) {
		            $(this).prop("selected", true);
		        }
		    });
			
			
			
			$("select[name='size']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("XS(含以下)");
					break;
					case "1":
						$(this).text("S");
					break;
					case "2":
						$(this).text("M");
					break;
					case "3":
						$(this).text("L");
					break;
					case "4":
						$(this).text("XL");
					break;
					case "5":
						$(this).text("2XL");
					break;
					case "6":
						$(this).text("3XL");
					break;
					case "7":
						$(this).text("4XL含以上");
					break;
					case "8":
						$(this).text("其他");
					break;
				}
			});
			
			$("select[name='grade']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						$(this).text("全新");
					break;
					case "1":
						$(this).text("9成5新(未使用，但包裝已拆，吊牌已剪)");
					break;
					case "2":
						$(this).text("9成新(已使用過，但無瑕疵)");
					break;
					case "3":
						$(this).text("8成新(已使用。少量瑕疵)");
					break;
					case "4":
						$(this).text("5成新(明顯瑕疵)");
					break;
					case "5":
						$(this).text("破損商品(需要修補)");
					break;
		
				}
			});
			
			$("select[name='itemStatus']").each(function () {
				let status = $(this).text();
				switch(status){
					case "0":
						 $(this).text("上架");
					break;
					case "1":
						$(this).text("下架");
					break;
					case "2":
						$(this).text("刪除");
					break;
				}
			});
			
			//更改圖片的預覽
			 $("#image01").on("change", function () {
			        var preview_img = $("#preview_img01");
			        preview_img.empty(); 
			        
			        var files = this.files;

			        for (var i = 0; i < files.length; i++) {
			            var reader = new FileReader();
			            var file = files[i];

			            reader.onload = function (e) {
			                var img = $("<img>").attr("src", e.target.result);
			                preview_img.append(img);
			            };

			            reader.readAsDataURL(file);
			        }
			    });
			    $("#image02").on("change", function () {
			        var preview_img = $("#preview_img02");
			        preview_img.empty(); 
			        
			        var files = this.files;

			        for (var i = 0; i < files.length; i++) {
			            var reader = new FileReader();
			            var file = files[i];

			            reader.onload = function (e) {
			                var img = $("<img>").attr("src", e.target.result);
			                preview_img.append(img);
			            };

			            reader.readAsDataURL(file);
			        }
			    });
			
		});

	
	</script>


</body>
</html>