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
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

	<style>
        *{
            box-sizing: border-box;
        }
        main.main{
            width: 100%;
            height: 690px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
        }
	
	    h3 {
            /* margin-bottom: 40px; */
	        color: #333;
	    }
        form{
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: center;
            width: 90%;
            min-height: 80%;
        }
	
	    table {
	        border-collapse: collapse;
	        width: 80%;
	        max-width: 800px;
            height: 100%;
	        margin: 0 auto;
            width: 100%;
            /* border: 1px solid; */
            border-radius: 10px;
            background-color: whitesmoke;
	    }

        table tbody{
            display: flex;
            justify-content: space-around;
            flex-direction: row;
            align-items: center;
            height:98%;
            /* border: 1px solid;
            border-radius: 10px;
            background-color: whitesmoke; */
            
        }

        /* table tbody div tr{
            flex: 1;
            width:79%;
            display: flex;
             justify-content: center; 
            flex-direction: row;
            align-items: center;
        }*/

        tr.main_left{
            /* border: 1px solid black; */
            display: flex;
            flex-direction: column;
            /* align-items: center; */
            justify-content: space-around;
            height: 400px;
            padding: 10px;
            background-color: white;
            border-radius: 10px;
        }
        tr.main_left input{
            width: 200px;
            margin-bottom: 15px;
        }
        textarea{
            width: 200px;
            width: 100%;
	        padding: 8px;
            border-radius: 10px;
            height: 193px;
            border: 0px solid rgb(101, 101, 101);
            background-color: whitesmoke;
        }
        tr.main_middle{
            /* border: 1px solid black; */
            display: flex;
            flex-direction: column;
    		align-items: stretch;
            justify-content: space-around;
            height: 400px;
            padding: 10px;
            background-color: white;
            border-radius: 10px;
        }
        tr.main_middle td{
        	flex:1;
        	height: 100%; 
        }
        tr.main_middle td input{
            width: 200px;
            margin-bottom: 9px;
        }
        tr.main_middle td select{
            width: 200px;
            margin-bottom: 9px;
            background-color: whitesmoke;
        }
        tr.main_right{
            /* border: 1px solid black; */
            display: flex;
            flex-direction: column;
            /* align-items: center; */
            justify-content: space-between;
            height: 400px;
            width: 200px;
            /* padding: 10px; */
            background-color: whitesmoke;
            border-radius: 10px;
        }

        tr.main_right td{
            /* border: 1px solid black; */
            height: 50%;
            border-radius: 10px;
            background-color: white;
        }
        tr.main_right td:nth-child(1){
            margin-bottom: 10px;
            padding: 10px;
            display: flex;
            flex-direction: column;
            justify-content: space-around;
        }
        tr.main_right td:nth-child(2){
            margin-top: 10px;
            padding: 10px;
            display: flex;
            flex-direction: column;
            justify-content: space-around;

        }
        tr.main_right td label{
            width: 60px;
        }
        
        tfoot td:last-child{
            position: relative;
        }
        
         /* div.imgtr01 td input{
         	width:150px;
         }
         div.imgtr02 td input{
         	width:150px;
         } */

        table div tr td:nth-child(1){
            color: #fff;
            width: 100px;
            background-color:rgb(169, 168, 168);
            border-radius: 10px;
            text-align: center;
        }
	
	    table div tr th, table div tr td {
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
            border: 0px solid rgb(101, 101, 101);
            background-color: whitesmoke;
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
            position: absolute;
            right: 26px;
            bottom: 12px;
	    }
	
	    input[type="submit"]:hover {
	        background-color: steelblue;
            
	    }
	   	img{
			max-width:100%;
			max-height:100%;
		}
		div#preview_img01{
			height: 90px;
		}
		div#preview_img02{
			height: 90px;
		}
		div.td_img01{
			padding:0px;
			/* margin-left: 70px; */
		}
        tr.main_right td label{
            display: inline-block;
            width: 65%;
            /* height: 35px; */
            text-align: center;
            background-color:steelblue;
            color:white;
        }
        tr.main_right td label:hover{
         	background-color:steelblue;
         	
         }
        
        
		div.td_img02{
			padding:0px;
			/* margin-left: 70px; */
		}
		
		label{
			/* border:1px solid black; */
			border-radius:10px;
			color:white;
/* 			background-color:steelblue; */
			padding:8px;
			cursor: pointer;
		}
		div.mb-3{
/* 			height:49px; */
		}

	</style>
</head>
<body>
    <main class="main">
        <h3>商品資料修改</h3>
        
        <form method="post" action="/TwoClothing/Item/Update" enctype="multipart/form-data">
        <table>
            <tbody>
            
<!--                 <td>商品</td> -->
<!-- <%--                 <input type="hidden" name="itemId" value="${item.itemId}"> --%> -->
                
                <tr class="main_left">
                    <td>商品名稱</td>
                    <td><input type="text" name="itemName" value="${item.itemName}" size="20"/></td>
                    <td>商品價格</td>
                    <td><input type="text" name="price" value="${item.price}"></td>

                    <td>描述</td>
                    <td><textarea name="detail" >${item.detail}</textarea></td>
                </tr>

                <tr class="main_middle">
                    <td>新舊</td>
                    <td>
                    <select  name="grade">
    <!-- <%--                     <option value="${item.grade}" selected>${item.grade}</option><span>請選擇商品新舊程度</span> --%> -->
                        <option value="0">全新(沒使用過且包裝未拆,吊牌未剪)</option>
                        <option value="1">9成5新(沒有使用痕跡,但包裝已拆,吊牌已剪)</option>
                        <option value="2">9成新(有使用痕跡,但沒有瑕疵)</option>
                        <option value="3">8成新(有使用痕跡,少量瑕疵)</option>
                        <option value="4">5成新(有使用痕跡,明顯瑕疵)</option>
                        <option value="5">破損商品(需要修補)</option>
                    </select>
        
                    <td>尺寸</td>
                    <td>
                        <select  name="size">
    <!-- <%--                         <option value="${item.size}" selected>${item.size}</option>  --%> -->
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
                
                    <td>商品狀態</td>
                    <td>
                        <select name="itemStatus">
    <!-- <%--                         <option value="${item.itemStatus}"></option> --%> -->
                            <option value="0">上架</option>
                            <option value="1">下架</option>
                            <option value="2">刪除</option>
                        </select>
                    </td>
                
                    <td>數量</td>
                    <td><input type="text" name="quantity"  value="${item.quantity}"></td>
                
                    <td>商品類別</td>
                    <td>
						<div class="mb-3">
<!-- 	                        <label for="categorySelect" class="form-label" ></label> -->
	                        <!-- 顯示選擇的完整結構,但不往後傳 -->
	                        <input type="text" class="form-control" id="categorySelect"
	                               aria-describedby="categorySelectHelp" readonly placeholder="${item.tagId}">
	                        <!--儲存標籤的id傳給後端-->
	                        <input type="hidden" id="selectedCategoryId" name="tagId" value="${item.tagId}">
	<!--                         <div id="categorySelectHelp" class="form-text">選擇適當的標籤，讓更多人能找到您的商品</div> -->
	                    </div>
	                    <div class="modal fade" id="categoryModal" tabindex="-1" role="dialog"
	                         aria-labelledby="categoryModalLabel"
	                         aria-hidden="true">
	                        <div class="modal-dialog modal-lg" role="document">
	                            <div class="modal-content">
	                                <div class="modal-header">
	<!--                                     <h5 class="modal-title" id="categoryModalLabel">選擇商品類別標籤</h5> -->
	                                </div>
	                                <div class="modal-body" id="categoryTree">
	                                    <!-- 類別標籤樹狀結構在這裡生成 -->
	                                </div>
	                            </div>
	                        </div>
	                    </div>
                  	</td>
                </tr>
          

                <tr class="main_right"> 

                    <td>
                        <div>主圖片</div>
                        <div class="td_img01">
                            <div id="preview_img01"></div>
                        </div>
                        <div>
        <!--                 <td><input class="form-control" type="file" id="image01" name="image01" -->
        <!-- 							accept="image/jpeg, image/png"> -->
                                    
                            <label class="btn btn-info form-control">
                                <input id="image01" style="display:none;" type="file" name="image01">
                                <i class="fa fa-photo"></i> 上傳圖片
                            </label>
                                    
                                    
                        </div>
                    </td>

                        

                    <td>
                        <div>副圖片</div>
                        <div class="td_img02">
                            <div id="preview_img02"></div>
                        </div>
                        <div>
        <!--                 <input class="form-control" type="file" id="image02" name="image02" -->
        <!-- 							accept="image/jpeg, image/png"> -->
                            <label class="btn btn-info form-control">
                                <input id="image02" style="display:none;" type="file" name="image02">
                                <i class="fa fa-photo"></i> 上傳圖片
                            </label>
                                    
                        </div>
                    </td>
                
                </tr>  
            </tbody>
            <tfoot>
                <tr>
                    <td>
                        <input type="hidden" name="forUpdate" value="update">
                        <input type="hidden" name="itemId" value="${item.itemId}">
                        <input type="submit" value="送出修改">
                    </td>
                </tr>

            </tfoot>
        </table>
        <br>

        </form>
    </main>
    
    <!--bootstrap5 js-->
	<script src="${pageContext.request.contextPath}/js/bootstrap5/popper.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap5/bootstrap.min.js"></script>
    
    <script src="${pageContext.request.contextPath}/js/jQuery/jquery-3.7.1.min.js"></script>
    <script>
    const categoryData = [
        <c:forEach var="tags" items="${applicationScope.categoryTags}" begin="1">
        {id:${tags.tagId}, name: '${tags.categoryName}', parentId:${tags.superTagId}},
        </c:forEach>
    ];
	</script>

	<!--商品類別標籤的js-->
	<script src="${pageContext.request.contextPath}/js/chengHan/addBidCategoryTags.js"></script>
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
				var tagList = ${jsonTag};
				$("#categorySelect").attr('placeholder', tagList.categoryName);
		});

	
	</script>


</body>
</html>