<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>IBM Members: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
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

</head>
<body bgcolor='white'>

<table id="table-1">
   <tr><td><h3>IBM Members: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for IBM Members: Home</p>

<h3>��Ƭd��:</h3>



<ul>
  <li><a href='listAllEmp.jsp'>List</a> all Members.  <br><br></li>
  
  
<li>
    <FORM METHOD="post" ACTION="Members.do" >
        <b>��J�|���s�� (�p1):</b>
        <input type="text" name="mbrId" value="${param.mbrId}"><font color=red>${errorMsgs.mbrId}</font>
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
</li>

<jsp:useBean id="MembersServiceImpl" scope="page" class="com.twoclothing.model.members.MembersServiceImpl" />

<li>
    <FORM METHOD="post" ACTION="Members.do" >
        <b>��ܷ|���s��:</b>
<!--         <select size="1" name="mbrId"> -->
<%--             <c:forEach var="Members" items="${MembersServiceImpl.all}" >  --%>
<%--                 <option value="${Members.mbrId}">${Members.mbrId}  �ϥ�Members.mbrName --%>
<%--             </c:forEach> --%>
<!--         </select> -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
</li>

<li>
    <FORM METHOD="post" ACTION="Members.do" >
        <b>��ܷ|���m�W:</b>
<%--         <select size="1" name="mbrName">  �ϥ�mbrName �@���W�� --%>
<%--             <c:forEach var="Members" items="${MembersServiceImpl.all}" > --%>
<%--                 <option value="${Members.mbrId}">${Members.mbrName}  �ϥ�Members.mbrName --%>
<%--             </c:forEach> --%>
<!--         </select> -->
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="�e�X">
    </FORM>
</li>












</ul>


<h3>���u�޲z</h3>

<ul>
  <li><a href='addEmp.jsp'>Add</a> a new Members.</li>
</ul>

</body>
</html>