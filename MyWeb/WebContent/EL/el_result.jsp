<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	# data1 : ${sessionScope.data1 }<br>
	# data2 : ${applicationScope.data2 }<br>
	
	<%-- 
	* EL에서 데이터를 참조할 떄 scope영역을 명시하지 않으면
	page -> request -> session -> application 순서대로 검색해서 찾아내서 표현합니다.
	--%>
	# data1 : ${data1 }<br>
	# data2 : ${data2 } <%-- sessoin.data2 먼저 찾아서 표현함
		그래서 reqeust는 {data1}으로 쓰고 그 뒤는 Scope까지 포함해서 쓰는 것이 관례임
	 --%>
</body>
</html>