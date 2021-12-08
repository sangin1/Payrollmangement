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
<title>관리자 회원가입1</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>
<body>
	<form class="form-horizontal" method="post"   action="${contextPath}/Login/masterCpCheck.do">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">회사명</label>
	    <div class="col-sm-10">
	      <input type="text" name="id" class="form-control" >
	    </div>
	  </div>
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-primary btn-lg">회사체크</button>
	    </div>
	</form>
	<div class="col-sm-offset-2 col-sm-10">
	      <input type="button" value="돌아가기" class="btn btn-default" onclick="location.href='${contextPath}/Login/loginMain.do'">
	</div>
</body>
</html>