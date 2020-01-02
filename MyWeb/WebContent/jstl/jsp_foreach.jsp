<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		int sum = 0;
		for(int i=1;i<=100;i++){
			sum+=i;
		}
		out.println("<h4>1부터 100까지의 누적합 : " + sum + "</h4>");
	%>
	<br>
	
	<c:set var = "total" value="0"/>
	
	<c:forEach var="i" begin="1" end ="100" step="1"><%-- step 생략 시 자동으로 1처리 --%>
		<c:set var="total" value= "${total+i }"/>
	</c:forEach>
	
	<p>1부터 100까지 누적합 : ${total}</p>
	
	
	<hr>
	
	<h4>구구단 4단</h4>
	<% 
		for(int hang=1; hang<=9; hang++){
			out.println(4 + " X " + hang + " = " + 4*hang + "<br>");
		}
	%>
	
	<hr>	
	
	<c:forEach var = "hang" begin = "1" end = "9">
		4 X ${hang } = ${4*hang }<br>
	</c:forEach>
	
	<hr>
	
	<%-- 모든 구구단(2~9)을 jstl 태그로 출력하세요. --%>
	<c:forEach var = "dan" begin = "2" end = "9">
		구구단 ${dan }단단<br><hr>
		<c:forEach var = "hang" begin = "1" end = "9">
			${dan} X ${hang} = ${dan*hang}<br>
		</c:forEach>
		<hr>
	</c:forEach>	
	
	<h4>배열이나 컬렉션 내부의 값 출력</h4>
	
	<c:set var="arr" value="<%= new int[]{1,3,5,7,9}%>"/>
		<c:forEach var = "n" items="${arr}">
			${n} 
		</c:forEach>
	<hr>
	<%
		int[] arr = new int[] {1,3,5,7,9};
		for(int i :arr){
			out.println(i);
		}
	%>
	
	
</body>
</html>