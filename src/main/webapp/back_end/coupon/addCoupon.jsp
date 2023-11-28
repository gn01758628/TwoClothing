<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>


<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@latest"></script><!-- 引入 SweetAlert2 -->
<title>新增優惠券</title>

<style>
  table#table-1 {
    width: 450px;
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>優惠券新增</h3></td><td>
		 <h4><a href="${pageContext.request.contextPath}/back_end/coupon/CouponServlet.do?action=get_All_Coupon">返回優惠券管理頁面</a></h4>
	</td></tr>
</table>

<h3>資料新增:</h3>

<FORM METHOD="post" ACTION="CouponServlet.do" name="form" >
<table>

	<tr>
		<td>優惠券名稱:</td>
		<td><input type="TEXT" name="cpnName" value="${cpnName}" size="45" required/></td> <td>${errorMsgs.cpnName}</td>
	</tr>
	<tr>
		<td>使用日期:</td>
		
		<td><input type="datetime-local" id="startDate" name="createDate"  value="${createDate}" size="45" required/></td> <td>${errorMsgs.createDate}</td>
	</tr>
	<tr>
		<td>失效日期:</td>
		<td><input type="datetime-local" id="endDate" name="expireDate"  value="${expireDate}" size="45" /></td> <td>${errorMsgs.expireDate}</td>
	</tr>
	<tr>
		<td>折扣類型:<font color=red><b>*</b></font></td>
		<td>
			<select size="1" name="disType">
				<c:forEach var="entry" items="${couponDisTypeMap.entrySet()}">
				    <option value="${entry.key}"${(entry.key==disType)? 'selected':'' } >${entry.value}
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>折扣額度:</td>
		<td><input type="number" name="disValue"  min="1" value="${disValue}"   size="45" required/></td> <td>${errorMsgs.disValue}</td>
	</tr>
	<tr>
		<td>最低金額條件:</td>
		<td><input type="number" name="minAmount" min="0"  value="${not empty minAmount ? minAmount : 0}"   size="45" required/></td> <td>${errorMsgs.minAmount}</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="insert_Coupon">
<input type="submit" id="submit-button" value="送出新增"></FORM>

</body>

<script>
		
	$(document).ready(function() {
		if(${not empty success}){
			Swal.fire({
                title: '成功',
                text: '優惠券新增成功',
                icon: 'success'
            });
		}
	});
	
	// 函數：格式化日期
	function formatDate(date) {
	    return (
	        date.getFullYear() + '-' +
	        ('0' + (date.getMonth() + 1)).slice(-2) + '-' +
	        ('0' + date.getDate()).slice(-2) + 'T' +
	        ('0' + date.getHours()).slice(-2) + ':' +
	        ('0' + date.getMinutes()).slice(-2)
	    );
	}

	// 函數：計算下一分鐘
	function getNextMinute(date) {
	    let nextMinute = new Date(date);
	    nextMinute.setMinutes(nextMinute.getMinutes() + 1);
	    return nextMinute;
	}

	// 函數：設置最小值
	function setMinValue(inputId, minValue) {
	    document.getElementById(inputId).min = formatDate(minValue);
	}

	// 函數：處理日期輸入
	function handleDateInput(inputId, otherInputId) {
	    let inputElement = document.getElementById(inputId);
	    let otherInputElement = document.getElementById(otherInputId);

	    let inputValue = inputElement.value;
	    let otherInputValue = otherInputElement.value;

	    let inputDate = new Date(inputValue);
	    let otherInputDate = new Date(otherInputValue);

	    // 檢查是否有效日期
	    if (isNaN(inputDate)) {
	        console.error("Invalid date input.");
	        // 使用當前時間作為默認值
	        inputDate = new Date();
	    }

	    // 設置最小值
	    setMinValue(inputId, getNextMinute(new Date()));

	    // 如果有其他日期，檢查是否小於當前開始時間
	    if (otherInputDate.getTime() < inputDate.getTime()) {
	        setMinValue(otherInputId, getNextMinute(inputDate));
	    }
	}

	// 頁面最開始的設置
	let startDate = getNextMinute(new Date());
	let endDate = getNextMinute(startDate);

	// 設置最小值
	setMinValue("startDate", startDate);
	setMinValue("endDate", endDate);


	// 監聽開始時間輸入的變化
	document.getElementById("startDate").addEventListener("input", function () {
	    handleDateInput("startDate", "endDate");
	});


        
</script>
</html>