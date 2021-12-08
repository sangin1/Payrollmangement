<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*,Login.*"
	pageEncoding="UTF-8"
	isELIgnored="false" 
%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("UTF-8");
%>  
<c:set var="contextPath" value="${pageContext.request.contextPath}"  />    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 선택창</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<style>
     .cls1 {
       font-size:50px;
       text-align:center;
     }
  </style>
</head>
<body>
 	<form>
	  	<div class="form-group">
		    <div class="col-sm-offset-2 col-sm-10">
			  <button type="button" class="btn btn-primary btn-lg" onclick="location.href='${contextPath}/Login/masterForm.do'">관리자</button>
			  <button type="button" class="btn btn-success btn-lg" onclick="location.href='${contextPath}/Login/noMasterForm.do'">일반사용자</button>
	  		</div>
	  	</div>
	</form>
	
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Login/loginMain.do'">돌아가기</button>
	    </div>
	  </div>

</body>
</html>