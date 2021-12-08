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
<!DOCTYPE html>
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />  
<html> 
<head> 
  <title>직원관리</title> 
  <meta charset="utf-8"> 
  <meta name="viewport" content="width=device-width, initial-scale=1"> 
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css"> 
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script> 
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script> 
  <link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head> 
<body> 
	<c:choose>
   <c:when test='${msg.master == "1"}' >
      <nav  class="navbar  navbar-expand-sm  bg-light"> 
		  <ul  class="navbar-nav"> 
		    <li  class="nav-item"> 
		      <a  class="nav-link"  onclick="location.href='${contextPath}/Emp/manager.do'">직원관리</a> 
		    </li> 
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
	<c:when test='${msg.master == "0"}'>
	      <nav  class="navbar  navbar-expand-sm  bg-light"> 
			  <ul  class="navbar-nav">  
			    <li class="nav-item"> 
			      <a class="nav-link" onclick="location.href='${contextPath}/Payroll/Pay.do'">급여관리</a> 
			    </li> 
			    <li  class="nav-item"> 
		      <a  class="nav-link"  onclick="location.href='${contextPath}/Payroll/severance.do.do'">퇴직금관리</a> 
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
	<form>
	  	<div class="form-group" align="left">
		    <div class="col-sm-offset-2 col-sm-10">
			  <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Emp/AppForm.do'">직원추가</button>
			  <button type="button" class="btn btn-success" onclick="location.href='${contextPath}/Emp/DelForm.do'">직원삭제</button>
			  <button type="button" class="btn btn-info" onclick="location.href='${contextPath}/Emp/Check.do'">승인요청</button>
			  <button type="button" class="btn btn-warning" onclick="location.href='${contextPath}/Emp/EnrollForm.do'">승인(수정)</button>
	  		</div>
	  	</div>
	</form>
</body> 

</html>

