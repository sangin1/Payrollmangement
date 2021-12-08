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
    <c:when test='${msg.index == "0"}' >
        <script>
			alert('아이디 또는 비밀번호가 틀렸습니다.');
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

<form class="form-horizontal" method="post"   action="${contextPath}/Payroll/search.do">
	  <div class="form-group">
	    <label for="inputEmail3" class="col-sm-2 control-label">년(0000)</label>
	    <div class="col-sm-10">
	      <input type="text" name="yearS" class="form-control" >
	    </div>
	  </div>
	  <div class="form-group">
	    <label for="inputPassword3" class="col-sm-2 control-label">달(00)</label>
	    <div class="col-sm-10">
	      <input type="text"  name="monS" class="form-control">
	    </div>
	  </div>
	  <div class="form-group">
	    <div class="col-sm-offset-2 col-sm-10">
	      <button type="submit" class="btn btn-default">검색</button>
	    </div>
	  </div>
</form>

<h3><span class="label label-primary">${year}</span>년 <span class="label label-primary">${mon}</span>월</h3>
	   <table align="center" border="1" >
	      <tr align="center" bgcolor="lightgreen">
	         <td width="4%" ><b>이름</b></td>
	         <td width="4%" ><b>직책</b></td>
	         <td width="4%" ><b>기본급</b></td>   
	         <td width="4%" ><b>주민세</b></td> 
	         <td width="4%" ><b>소득세</b></td> 
	         <td width="4%" ><b>초과수당</b></td>
	         <td width="4%" ><b>보너스</b></td>
	         <td width="4%" ><b>보험</b></td>   
	         <td width="4%" ><b>보조금</b></td>  
	         <td width="4%" ><b>합계</b></td> 
	         <td width="4%" ><b></b></td>      
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
	      <form class="form-horizontal" method="post"   action="${contextPath}/Payroll/update.do">
	      	<tr align="center">		      	
			      	 <td><input size=10 type="text" name="name" value="${mem.name}"></td>
	       			 <td><input size=10 type="text" name="pos" value="${mem.pos}"></td>
	       			 <td ><input size=10 type="text" name="pay" value="${mem.pay}"></td>
	       			 <td><input size=10 type="text" name="tax" value="${mem.tax}"></td>
	       			 <td ><input size=10 type="text" name="incometax" value="${mem.incometax}"></td>
	       			 <td ><input size=10 type="text" name="outpay" value="${mem.outpay}"></td>
	       			 <td ><input size=10 type="text" name="bonus" value="${mem.bonus}"></td>
	       			 <td ><input size=10 type="text" name="insurance" value="${mem.insurance}"></td>
	       			 <td ><input size=10 type="text" name="subsidy" value="${mem.subsidy}"></td>
	       			 <td >${mem.total}</td>
			         <td><button type="submit" class="btn btn-default">저장</button></td> 			      
		      	 	<td class="hidden-col"><input type="text" name="pay_id" value="${mem.pay_id}"></td>
		      	 	<td class="hidden-col"><input type="text" name="emp_id" value="${mem.code}"></td>	
		      	 	<td class="hidden-col"><input type="text" name="pageYear" value="${year}"></td>
		      	 	<td class="hidden-col"><input type="text" name="pageMon" value="${mon}"></td>	      	            
		       </tr> 
	      </form>   
	     </c:forEach>
		</c:when>
		<c:when test='${msg.master == "0"}'>
		<c:forEach  var="mem" items="${payInfo }" >
	        <tr align="center">
	          		<td>${mem.name}</td>
	       			 <td>${mem.pos}</td>
	       			 <td >${mem.pay}</td>
	       			 <td>${mem.tax}</td>
	       			 <td >${mem.incometax}</td>
	       			 <td >${mem.outpay}</td>
	       			 <td >${mem.bonus}</td>
	       			 <td >${mem.insurance}</td>
	       			 <td >${mem.subsidy}</td>
	       			 <td >${mem.total}</td>              
	       </tr>
	     </c:forEach>
	   </c:when>
	</c:choose>
	</table>
</body> 
</html>

