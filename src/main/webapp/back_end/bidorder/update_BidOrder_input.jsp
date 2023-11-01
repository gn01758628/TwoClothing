<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.twoclothing.gordon.service.*"%>
<%@ page import="com.twoclothing.model.members.*"%>

<%
  // EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>���u��ƭק� - update_emp_input.jsp</title>

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
		 <h3>�v�� - listOnebidorder.jsp</h3>
		 <h4><a href="<%=request.getContextPath()%>/back_end/bidorder/select_page.jsp"><img src="images/login2.png" width="100" height="102" border="0">�^����</a></h4>
	</td></tr>
</table>

<h3>��ƭק�:</h3>


<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/bidorder/BidOrder.do" name="form1">
<table>
    <tr>
		<td>�v�аӫ~�q��s��:</td>
		<td>${param.bidOrderId}</td>
	</tr>
<!--	<tr>
		<td>�v�аӫ~�s��:</td>
		<td>${param.bidItemId}</td>
	</tr>
	<tr>
		<td>�R�a�|���s��:</td>
		<td>${param.buyMbrId}</td>
	</tr>
	<tr>
		<td>��a�|���s��:</td>
		<td>${param.sellMbrId}</td>
	</tr>
-->	<tr>
		<td>�R�a�����P��:</td>
		<td>${param.buyStar}</td>
		<td><input type="TEXT" name="buyStar"  value="${param.buyStar}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyStar}</td>
	</tr>
	<tr>
		<td>�R�a�������e:</td>
		<td>${param.buyerRatingDesc}</td>
		<td><input type="TEXT" name="buyerRatingDesc"  value="${param.buyerRatingDesc}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.buyerRatingDesc}</td>
	</tr>
	<tr>
		<td>��a�����P��:</td>
		<td>${param.sellStar}</td>
		<td><input type="TEXT" name="sellStar"  value="${param.sellStar}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellStar}</td>
	</tr>
	<tr>
		<td>��a�������e:</td>
		<td>${param.sellerRatingDesc}</td>
		<td><input type="TEXT" name="sellerRatingDesc"  value="${param.sellerRatingDesc}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.sellerRatingDesc}</td>
	</tr>
<!--	<tr>
		<td>�q����:</td>
		<td>${param.orderDate}</td>
	</tr>
-->	<tr>
		<td>�I�ڤ覡:(�n���U�Ԧ�)</td>
		<td>${param.payType}</td>
		<td><input type="TEXT" name="payType"  value="${param.payType}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.payType}</td>
	</tr>
	<tr>
		<td>�I�ڸ��:</td>
		<td>${param.payInfo}</td>
		<td><input type="TEXT" name="payInfo"  value="${param.payInfo}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.payInfo}</td>
	</tr>
<!--	<tr>
		<td>�q����B:</td>
		<td>${param.amount}</td>
	</tr>
-->	<tr>
		<td>�q�檬�A:</td>
		<td>${param.orderStatus}</td>
		<td><input type="TEXT" name="orderStatus"  value="${param.orderStatus}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.orderStatus}</td>
	</tr>
	<tr>
		<td>����a�}:</td>
		<td>${param.receiveAddress}</td>
		<td><input type="TEXT" name="receiveAddress"  value="${param.receiveAddress}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receiveAddress}</td>
	</tr>
	<tr>
		<td>����H�m�W:</td>
		<td>${param.receiveName}</td>
		<td><input type="TEXT" name="receiveName"  value="${param.receiveName}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receiveName}</td>
	</tr>
	<tr>
		<td>����H���:</td>
		<td>${param.receivePhone}</td>
		<td><input type="TEXT" name="receivePhone"  value="${param.receivePhone}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.receivePhone}</td>
	</tr>
	<tr>
		<td>�Ƶ�:</td>
		<td>${param.remarks}</td>
		<td><input type="TEXT" name="remarks"  value="${param.remarks}"  size="45"/></td> <td style="color: deeppink;">${errorMsgs.remarks}</td>
	</tr>


	<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.gordon.service.MembersServiceImpl" />
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="bidOrderId" value="${param.bidOrderId}">
<input type="submit" value="�e�X�ק�"></FORM>
</body>



<!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '${param.hiredate}', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
           //startDate:	            '2017/07/10',  // �_�l��
           //minDate:               '-1970-01-01', // �h������(���t)���e
           //maxDate:               '+1970-01-01'  // �h������(���t)����
        });
        
        
   
        // ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

        //      1.�H�U���Y�@�Ѥ��e������L�k���
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.�H�U���Y�@�Ѥ��᪺����L�k���
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>