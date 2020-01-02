<%@page import="el.basic.Personn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	Personn person = (Personn)request.getAttribute("p");
%>
	<p>
	# 이름 : <%=person.getName() %><br>
	# 나이 : <%=person.getAge() %><br>
	# 성별 : <%=person.getGender() %><br>
	# 주소 : <%=person.getAddress() %><br>
	</p>
	
	<hr>
	
	<p>
	# Person객체 : ${requestScope.p}<br>
	# 이름 : ${p.name}<br>
	# 나이: ${p.age}세<br>
	# 성별 : ${p.gender}<br>
	# 주소 : ${p.address}<br>
	</p>
	
	
</body>
</html>