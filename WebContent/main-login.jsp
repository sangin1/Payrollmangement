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
<title>급여관리프로그램</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
<style>
     .cls1 {
       font-size:50px;
       text-align:center;
     }
  </style>
</head>
<body>
<c:choose>
    <c:when test='${check == "1"}' >
        <script>
			alert('존제하는 아이디 입니다.');
			history.back();
		</script>
   </c:when> 
</c:choose>
 <p class="cls1">급여관리 프로그램</p>
	<form class="form-horizontal" method="post"   action="${contextPath}/Login/login.do">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
	    <div class="col-sm-10">
	      <input type="text" name="id" class="form-control" >
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label">비밀번호</label>
	    <div class="col-sm-10">
	      <input type="password"  name="pwd" class="form-control">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">로그인</button>
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" onclick="location.href='${contextPath}/Login/memberForm.do'" class="btn btn-default">회원가입</button>
	    </div>
	  </div>
</form>
</body>
</html>