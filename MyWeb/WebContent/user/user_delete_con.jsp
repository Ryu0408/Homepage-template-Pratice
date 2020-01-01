<%@page import="kr.co.ryu0408.user.model.UserDAO"%>
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
	/*
	1. 폼데이터 처리
	2. 로그인 유효성 검사 하세요.
	3. deleteUser() 메서드를 선언하여 실행
	4. 탈퇴에[ 성공하면 세선을 삭제 후 user_login으로 이동
	5. 탈퇴 실패시 user_mypage로 이동

	*/
	String id = (String)session.getAttribute("user_id");
	String pw = request.getParameter("check_pw");
	
	int result = UserDAO.getInstance().userCheck(id, pw);
	if(result ==0){%>
	<script>
		alert("비밀번호가 틀렸습니다.");
		location.href="user_mypage.jsp";
	</script>
	<%}else {
		UserDAO dao = UserDAO.getInstance();
		if(dao.deleteUser(id)){
			session.invalidate();
			response.sendRedirect("user_login.jsp");
		}else{
			response.sendRedirect("user_mypage.jsp");
		}
	}
%>
</body>
</html>