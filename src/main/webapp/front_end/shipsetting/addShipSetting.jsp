<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>

<link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap5/bootstrap.min.css" />
</head>
<body>

<script src="<%=request.getContextPath()%>/js/bootstrap5/bootstrap.bundle.min.js"></script>

<script src="<%=request.getContextPath()%>/js/gordon/twzipcode.js"></script>
<table id="table-1">

	<tr><td>
		 <h3>���y��Ʒs�W</h3></td><td>
		 <h4><a href='${pageContext.request.contextPath}/MemberCentre.jsp'>�|������</a></h4>
	</td></tr>
</table>

<h3>��Ʒs�W:</h3>



<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/shipsetting/Shipsetting.do" name="form1">
<div class="row">
    <div class="col-md-6">
        <label class="control-label col-form-label"> �m�W </label> 
        <input type="text" name="receiveName" class="form-control">
    </div>
    <div class="col-md-6">
        <label class="control-label col-form-label"> ��� </label> 
        <input type="text" name="receivePhone" class="form-control">
        <label style="color: deeppink;">${errorMsgs.receivePhone}</label>
    </div>
</div>

	<div class="twzipcode">
		<div class="row">
			<div class="col-md-6">
				<label class="control-label col-form-label"> ���� </label> <select
					data-role="county" name="county" class="form-select"></select>
			</div>
			<div class="col-md-6">
				<label class="control-label col-form-label"> �m��� </label> <select
					data-role="district" name="district" class="form-select"></select>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<label class="control-label col-form-label"> �ԲӦa�} </label> <input
					type="text" name="address" class="form-control">
			</div>
		</div>
		<input type="hidden" name="zipcode" data-role="zipcode" />
	</div>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="mbrId" value="${user.mbrId}">
<input type="submit" value="�e�X�s�W">
</FORM>

</body>



<script>
let twzipcode = new TWzipcode({
	"district" : {
		onChange : function(id) {
			console.log(this.nth(id).get());
		}
	}
});


var county = $(selector).twzipcode('get', 'county');

// ���o���� county �H�ζm���� district�]��^�}�C�^
var result = $(selector).twzipcode('get', 'county,district'); // �H , �r��ǤJ
var result = $(selector).twzipcode('get', [ 'county', 'district' ]); // �H�}�C�ǤJ

// Callback
$(selector).twzipcode('get', function(county, district, zipcode) {
	console.log(county); // ����
	console.log(district); // �m����
	console.log(zipcode); // �l���ϸ�
});
</script>
</html>