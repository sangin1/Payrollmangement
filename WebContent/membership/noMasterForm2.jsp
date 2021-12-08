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
<html>
<head>
<meta charset="utf-8"> 
<title>일반회원가입2</title>
<link rel="stylesheet" type="text/css" href="${contextPath}/bootstrap-3.3.2-dist/css/bootstrap.min.css">
</head>
<body>
<c:choose>
    <c:when test='${check.c_id == "0"}' >
        <script>
			alert('가입되지 않은 회사명 입니다.');
			history.back();
		</script>
   </c:when> 
   <c:when test='${check.c_id != "0"}' >
	   <form name = "frm" class="form-horizontal" method="post"   action="${contextPath}/Login/addNoMaster.do">
		  <div class="form-group">
		    <label for="inputEmail3" class="col-sm-2 control-label">아이디</label>
		    <div class="col-sm-10">
		      <input type="text" name="id" class="form-control" >
		    </div>
		    <label for="inputEmail3" class="col-sm-2 control-label">비밀번호</label>
		    <div class="col-sm-10">
		      <input type="text" name="pwd" class="form-control" >
		    </div>
		    <label for="inputEmail3" class="col-sm-2 control-label">이름</label>
		    <div class="col-sm-10">
		      <input type="text" name="name" class="form-control" >
		    </div>
		    <label for="inputEmail3" class="col-sm-2 control-label">회사코드</label>
		    <div class="col-sm-10">
		      <input type="text" name="c_id" class="form-control" value="${check.c_id}" readonly >
		    </div>
		  </div>
		    <div class="col-sm-offset-2 col-sm-10">
		      <button type="submit" class="btn btn-default">회원가입</button>
		    </div>
		</form>
	</c:when>
</c:choose>
</body>
</html>