<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap5/bootstrap.min.css" />
</head>
<body>

<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>

<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>


				<div class="twzipcode">
    <div class="row">
        <div class="col-md-6">
            <label class="control-label col-form-label">
                縣市
            </label>
            <select data-role="county" class="form-select"></select>
        </div>
        <div class="col-md-6">
            <label class="control-label col-form-label">
                鄉鎮區
            </label>
            <select data-role="district" class="form-select"></select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <label class="control-label col-form-label">
                详细地址
            </label>
            <input type="text" name="address" class="form-control">
        </div>
    </div>
    <input type="hidden" data-role="zipcode" />
</div>
                
<script type="text/javascript">
let twzipcode = new TWzipcode({
    "district": {
        onChange: function (id) {
            console.log(this.nth(id).get());
        }
    }
});



//取得縣市 county（返回字串）
var county = $(selector).twzipcode('get', 'county');

// 取得縣市 county 以及鄉鎮市區 district（返回陣列）
var result = $(selector).twzipcode('get', 'county,district'); // 以 , 字串傳入
var result = $(selector).twzipcode('get', ['county', 'district']);  // 以陣列傳入

// Callback
$(selector).twzipcode('get', function (county, district, zipcode) {
    console.log(county);   // 縣市
    console.log(district); // 鄉鎮市區
    console.log(zipcode);  // 郵遞區號
});
</script>
</body>
</html>