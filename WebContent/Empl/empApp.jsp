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
<title>직원추가</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>
<body>
	<form class="form-horizontal" method="post"   action="${contextPath}/Emp/App.do">
	  <div class="form-group">	    
	    <label for="inputEmail3" class="col-sm-2 control-label">회사코드</label>
	    <div class="col-sm-10">
		      <input type="text" name="c_id" class="form-control" value="${msg.c_id}" readonly >
		</div>
		<label for="inputEmail3" class="col-sm-2 control-label">이름</label>
	    <div class="col-sm-10">
	      <input type="text" name="name" class="form-control" >
	    </div>
		<label for="inputEmail3" class="col-sm-2 control-label">직책</label>
	    <div class="col-sm-10">
	      <input type="text" name="pos_name" class="form-control" >
	    </div>
	    <label for="inputEmail3" class="col-sm-2 control-label">입사일(0000-00-00)</label>
	    <div class="col-sm-10">
	      <input type="text" name="date" class="form-control" >
	    </div>
	  </div>
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">직원추가</button>
	    </div>
	</form>
	<div></div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Emp/manager.do'">돌아가기</button>
	    </div>
	 </div>
</body>
</html>