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
	 <p class="cls1">연차승인목록</p>
	   <table align="center" border="1" >
	      <tr align="center" bgcolor="lightgreen">
	         <td width="7%" ><b>이름</b></td>
	         <td width="7%" ><b>직책</b></td>
	         <td width="7%" ><b>날짜</b></td>
	         <td width="7%" ><b>사유</b></td>   
	         <td width="7%" ><b>비고</b></td> 
	         <td width="7%" ><b></b></td>      
	   </tr>
	
	<c:choose>
		<c:when test="${empty annList}" >
	      <script>
			alert('등록된 연차가 없습니다.');
			history.back();
		</script>
	   </c:when>
		   <c:when test='${msg.master == "1"}' > 
			   <c:forEach  var="mem" items="${annList}" >
			      <form class="form-horizontal" method="post"   action="${contextPath}/Ann/Enroll.do">
				      <tr align="center">
				      	 <td ><input type="text" name="name" value="${mem.name}" readonly></td>
		       			 <td ><input type="text" name="pos_name" value="${mem.pos_name}" readonly></td>
		       			 <td ><input type="text" name="date" value="${mem.date}" readonly></td>
		       			 <td ><input type="text" name="reason" value="${mem.reason}"></td>
		       			 <c:choose>
		       			 	<c:when test='${mem.check == "0"}' > 
		       			 		<td ><input type="text" name="check" value="비승인" readonly></td>
		       			 		<td><button type="submit" class="btn btn-default">승인</button></td> 
		       			 	</c:when>
		       			 	<c:when test='${mem.check == "1"}' > 
		       			 		<td ><input type="text" name="check" value="승인" readonly></td>
		       			 	</c:when>
		       			 	<c:when test='${mem.check == "2"}' > 
		       			 		<td ><input type="text" name="check" value="미신청승인" readonly></td>
		       			 	</c:when>
		       			 </c:choose>
				          <td class="hidden-col"><input type="text" name="ann_id" value="${mem.ann_id}"></td>        
				       </tr>
			       </form>	       
		     </c:forEach>
		   </c:when>
		   <c:when test='${msg.master == "0"}' > 
		    <c:forEach  var="mem" items="${annList}" >
			      <tr align="center">
			      	 <td >${mem.name}</td>
	       			 <td >${mem.pos_name}</td>
	       			 <td >${mem.date}</td>
	       			 <td >${mem.reason}</td>
	       			 <c:choose>
		       			 	<c:when test='${mem.check == "0"}' > 
		       			 		<td >비승인</td>
		       			 	</c:when>
		       			 	<c:when test='${mem.check == "1"}' > 
		       			 		<td >승인</td>
		       			 	</c:when>
		       			 	<c:when test='${mem.check == "2"}' > 
		       			 		<td >미신청승인"</td>
		       			 	</c:when>
		       			</c:choose>     
			       </tr>     
		     </c:forEach>
		   </c:when>	
	</c:choose>
	
	</table>  
	<div></div>
	<div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="button" class="btn btn-primary" onclick="location.href='${contextPath}/Ann/manager.do'">돌아가기</button>
	    </div>
	 </div>
</body>
</html>