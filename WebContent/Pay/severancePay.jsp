<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>    
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<html> 
<head> 
  <title>급여관리</title> 
  <meta charset="utf-8"> 
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
  <link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
  <style>
  	.hidden-col { display: none;}
  </style>
</head> 
<body> 
	<c:choose>
		<c:when test='${check == "0"}' >
		        <script>
					alert('승인되지않은 회원(담당자에게 승인요청을 해주세요)');
					history.back();
				</script>
		   </c:when>  
		   <c:when test='${msg.master == "1"}' >
		      <nav  class="navbar  navbar-expand-sm  bg-light"> 
				  <ul  class="navbar-nav"> 
				    <li  class="nav-item"> 
				      <a  class="nav-link"  onclick="location.href='${contextPath}/Emp/manager.do'">직원관리</a> 
				    </li> 
				    <li class="nav-item"> 
				    <li>
				      <a class="nav-link"  onclick="location.href='${contextPath}/Payroll/Pay.do'">급여관리</a> 
				    </li> 
				    <li  class="nav-item"> 
				      <a  class="nav-link"  onclick="location.href='${contextPath}/Payroll/severance.do'">퇴직금관리</a> 
				    </li> 
				    <li class="nav-item"> 
				      <a class="nav-link" onclick="location.href='${contextPath}/Ann/manager.do'">연차관리</a> 
				    </li> 
				  </ul> 
				</nav>   
				<div align="right">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Login/logout.do'">로그아웃</button>
				    </div>
			  	</div>
			</c:when>	 
			<c:when test='${msg.master == "0"}'>
			      <nav  class="navbar  navbar-expand-sm  bg-light"> 
					  <ul  class="navbar-nav">  
					    <li class="nav-item"> 
					      <a class="nav-link" onclick="location.href='${contextPath}/Payroll/Pay.do'">급여관리</a> 
					    </li> 
					    <li  class="nav-item"> 
				      <a  class="nav-link"  onclick="location.href='${contextPath}/Payroll/severance.do'">퇴직금관리</a> 
				    </li> 
					    <li class="nav-item"> 
					      <a class="nav-link" onclick="location.href='${contextPath}/Ann/manager.do'">연차관리</a> 
					    </li> 
					  </ul> 
				</nav>   
				<div align="right">
				    <div class="col-sm-offset-2 col-sm-10">
				      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Login/logout.do'">로그아웃</button>
				    </div>
			  	</div>
			</c:when>
	</c:choose>  
	<table align="center" border="1" >
	      <tr align="center" bgcolor="lightgreen">
	         <td width="4%" ><b>이름</b></td>
	         <td width="4%" ><b>직책</b></td>
	         <td width="4%" ><b>퇴직적립금</b></td>   
	   </tr>
	
	<c:choose>
		<c:when test="${empty payInfo}" >
	      <script>
			alert('등록된 직원이 없습니다.');
			history.back();
		</script>
	   </c:when>
	   
	   <c:when test= '${msg.master == "1"}' >
	      <c:forEach  var="mem" items="${payInfo}" >
	      <tr align="center">
	          		<td>${mem.name}</td>
	       			 <td>${mem.pos}</td>
	       			 <td >${mem.ser}</td>             
	       </tr>
	     </c:forEach>
		</c:when>
		<c:when test='${msg.master == "0"}'>
		<c:forEach  var="mem" items="${payInfo }" >
	        <tr align="center">
	          		<td>${mem.name}</td>
	       			 <td>${mem.pos}</td>
	       			 <td >${mem.ser}</td>             
	       </tr>
	     </c:forEach>
	   </c:when>
	</c:choose>
	</table>

	
</body> 
</html>
