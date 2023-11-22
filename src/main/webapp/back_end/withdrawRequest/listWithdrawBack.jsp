<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

	<title>虛擬錢包提款審核</title>

	<style>
		*{
			box-sizing: border-box;
		}
		body{
			height: 100%;
			width: 100%;
		}
		main.main{

			height: 100%;
			width: 100%;
			display: flex;
			align-items: center;
			flex-direction: column;
			padding: 10px;

		}
		main.main div{
			position: relative;
			display: flex;
			align-items: center;
			flex-direction: column;
			height: 100%;
			width: 90%;
		}
		table{
			width: 100%;
			padding: 10px;
			border-collapse: collapse;


		}
		
		form.form_update{
			width:90%;
			height:100%;
			position: relative;
		}
		table thead.head tr{
			border: 1px solid black;
			background-color: rgb(255, 234, 208);
			height: 40px;

		}
		


		table tbody.body tr{
			border: 1px solid black;
			height: 50px;
		}

		table tbody.body tr th, td{
			text-align: center;
		} 

		button{
			width: 100px;
			height: 30px;
			margin: 10px;
			background-color: rgb(255, 234, 208);
			font-size: 16px;
			border: 2px solid gray;
			position: absolute;
    		right: 0px;
		}
		button:hover{
			box-shadow: 2px 2px 4px 1px rgba(0, 0, 0, 0.2);
			cursor: pointer;
		}

		label:hover{
			cursor: pointer;
		}
	</style>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
</head>
<body>

	<main class="main">
		<h2>虛擬錢包提款審核</h2>
	
		<div>
			<form class="form_update" method="post" action="${pageContext.request.contextPath}/WithdrawRequest/Update">
				<table>
					<thead class="head">
						<tr>
							<th>編號</th>
							<th>會員編號</th>
							<th>提款金額</th>
							<th>匯入帳號</th>
							<th>申請日期</th>
							<th>申請狀態</th>
							<th>備註</th>
							<th>審核</th>
						</tr>
					</thead>
					
					<tbody class="body">
						<c:forEach var="withdrawRequest" items="${WRList}" varStatus="loop">
							<tr>
								<td>${loop.index+1}</td>
								<td>${withdrawRequest.mbrId}</td>
								<td>${withdrawRequest.amount}</td>
								<td>${withdrawRequest.mbrAccount}</td>
								<td>${withdrawRequest.reqDate}</td>
								<td class="status">${withdrawRequest.reqStatus}</td>
								<td>${withdrawRequest.note}</td>
								<td>
							<input type="hidden" name="wrId"  value="${withdrawRequest.wrId}">
							<input type="hidden" name="amount" value="${withdrawRequest.amount}">
							
									<label for="pass_${withdrawRequest.wrId}_${loop.index}">
										<input type="radio" name="status_${withdrawRequest.wrId}" id="pass_${withdrawRequest.wrId}_${loop.index}" value="1">
										通過
									</label>
									<label for="dis_${withdrawRequest.wrId}_${loop.index}">
										<input type="radio" name="status_${withdrawRequest.wrId}" id="dis_${withdrawRequest.wrId}_${loop.index}" value="2">
										不通過
									</label>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<input type="hidden" name="choice"	value="UpdateStatus">
				<button type="button" class="submitBtn">確認</button>
			</FORM>
		</div>
	</main>

	<script>
	$(".submitBtn").click(function(){
		var statusarr=[];
		var selectedValue ;
		var wrId ;
		var amount;
		var btnElement = $(this);
		$('input[name^="status_"]:checked').each(function() {			
			selectedValue = $(this).val();
	        wrId = $(this).closest('tr').find('input[name="wrId"]').val();
	        amount = $(this).closest('tr').find('input[name="amount"]').val();
	        console.log(wrId);
	        console.log(amount);
	        statusarr.push({ wrId: wrId, status: selectedValue, amount: amount});
		
		});

		var formDataObject = {};
		statusarr.forEach(function (item, index) {
		    formDataObject['wrId_' + index] = item.wrId;
		    formDataObject['reqStatus_' + index] = item.status;
		    formDataObject['amount_' + index] = item.amount;
		});

   		let formDataUrlEncoded = new URLSearchParams(formDataObject);
   		console.log(formDataUrlEncoded);

		 fetch("${pageContext.request.contextPath}/back_end/BackWithdrawRequest/withdraw?choice=UpdateStatus", {
		    method: "post",
		    body: formDataUrlEncoded
		 })
		 .then(function (response) {
			    console.log(response); // 輸出完整的 response 物件

		 	return response.json();
		 })
		 
		 
		 
		 
		 .then(function (data) {
		 	console.log(data);
		 	switch(data.message){
		 		case "ok":
		 			window.alert("審核成功！");
		 			location.reload();
		 			break;
		 		case "out_of_e_wallet":
		 			window.alert("審核失敗");
		 			location.reload();
		 			break;
		 		default:
		 			window.alert(data.message);
		 		location.reload();
		 			break;
		 	}
		 });
		 
			if($(".status").text() != "0"){
				btnElement.closest("tr").hide();
					console.log($(".status").text());
				}
	        

	});
	
	$(document).ready(function() {
		$(".status").each(function () {
			let status = $(this).text();
			switch(status){
				case "0":
					$(this).text("待審核");
				break;
				case "1":
					$(this).text("已通過");
				break;
				case "2":
					$(this).text("未通過");
				break;
				}
			})
	});
	
	</script>

</body>
</html>