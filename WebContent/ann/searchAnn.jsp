<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<%
request.setCharacterEncoding("UTF-8");
%>    
<html>
<head>
	<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
   <meta  charset="UTF-8">
   <title>승인대기목록</title>
	<style>
     .cls1 {
       font-size:40px;
       text-align:center;
     }   
     .cls2 {
       font-size:20px;
       text-align:center;
     }
     .cls3 {
       font-size:15px;
       text-align:right;
     }
  </style>
  
</head>
<body>
	 <p class="cls1">남은연차</p>
	 
	   <table align="center" border="1" >
	      <tr align="center" bgcolor="lightgreen">
	         <td width="7%" ><b>이름</b></td>
	         <td width="7%" ><b>직책</b></td>
	         <td width="7%" ><b>남은연차</b></td>  
	   </tr>
	
	    <c:forEach  var="mem" items="${empList}">
		      <tr align="center">
		      	 <td >${mem.name}</td>
       			 <td >${mem.pos_name}</td>
       			 <td >${mem.num}일 남았습니다.</td> 
		       </tr>     
	     </c:forEach>	
	</table>  
	<div></div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Ann/manager.do'">돌아가기</button>
	    </div>
	 </div>
	 <p class="cls3">연차시효</p>
	 <p class="cls3">입사일기준 전날 1년치연차사용기간이 소멸됩니다.</p>
	 <p class="cls3">연차수당은 사용기간 소멸 후 3년후에 소멸됩니다.</p>
</body>
</html>