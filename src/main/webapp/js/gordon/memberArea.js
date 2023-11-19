let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});


var county = $(selector).twzipcode('get', 'county');

// 取得縣市 county 以及鄉鎮市區 district（返回陣列）
var result = $(selector).twzipcode('get', 'county,district'); // 以 , 字串傳入
var result = $(selector).twzipcode('get', [ 'county', 'district' ]); // 以陣列傳入

// Callback
$(selector).twzipcode('get', function(county, district, zipcode) {
	console.log(county); // 縣市
	console.log(district); // 鄉鎮市區
	console.log(zipcode); // 郵遞區號
});