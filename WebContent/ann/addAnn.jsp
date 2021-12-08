<%@ page language="java" contentType="text/html; charset=UTF-8"
	import=" java.util.*"
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
<title>연차신청</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>
<body>
<form class="form-horizontal" method="post"   action="${contextPath}/Ann/addAnnForm.do">
	<div class="form-group">	
	<c:choose>
		 <c:when test='${msg.master == "1"}' > 
		 	<label for="inputEmail3" class="col-sm-2 control-label">직원번호</label>
			    <div class="col-sm-10">
			      <input type="text" name="emp_id" class="form-control" >
			    </div>
		 </c:when>
	</c:choose>  
		<label for="inputEmail3" class="col-sm-2 control-label">사유</label>
	    <div class="col-sm-10">
	      <input type="text" name="reason" class="form-control" >
	    </div>
	    <label for="inputEmail3" class="col-sm-2 control-label">날짜(0000-00-00)</label>
	    <div class="col-sm-10">
	      <input type="text" name="date" class="form-control" >
	    </div>
	  </div>
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">연차추가(신청)</button>
	    </div>
	</form>
	<div></div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Ann/manager.do'">돌아가기</button>
	    </div>
	 </div>
</body>
</html>