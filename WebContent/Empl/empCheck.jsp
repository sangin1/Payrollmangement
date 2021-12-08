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
  </style>
  
</head>
<body>
	 <p class="cls1">승인요청정보</p>
	   <table align="center" border="1" >
	      <tr align="center" bgcolor="lightgreen">
	         <td width="7%" ><b>코드</b></td>
	         <td width="7%" ><b>이름</b></td>        
	   </tr>
	
	<c:choose>
		<c:when test="${empty  membersList}" >
	      <script>
			alert('승인요청이 없습니다.');
			history.back();
		</script>
	   </c:when>
	   <c:when test="${!empty msg}" >
	      <c:forEach  var="mem" items="${membersList }" >
	        <tr align="center">
	          <td>${mem.index }</td>
	          <td>${mem.name }</td>               
	       </tr>
	     </c:forEach>
		</c:when>
	</c:choose>
	
	</table>  
	<div></div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Emp/manager.do'">돌아가기</button>
	    </div>
	 </div>
</body>
</html>