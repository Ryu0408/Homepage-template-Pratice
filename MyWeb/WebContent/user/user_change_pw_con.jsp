<%@page import="kr.co.ryu0408.user.model.UserDAO"%>
<%@page import="kr.co.ryu0408.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
	1. 폼데이터를 처리하세요(기존 비번, 변경 비번)
	2. dao 주소값 받아오시고 userCheck()메서드를 활용해서 기존 비번과 아이디 정보를 바탕으로
	해당 비밀번호를 일치하는지를 확인하세요
	
	3. 일치한다면 비밀번호를 바꾸는 로직 changePassword()를 실행.
	4. "비밀번호가 정상적으로 변경되었습니다."를 <script>로 출력 후 mypage로 이동
	5. 일치하지 않는다면 "현재 비밀번호가 다릅니다." 출력 후 뒤로가기"
	6. 비밀번호 변경에 실패하면 "비밀번호 변경에 실패했습니다." 출력후 mypage로 이동
	
	String sql = "UPDATE user SET user_pw=? WHERE user_id=?"
*/
	request.setCharacterEncoding("utf-8");
	String oldpw = request.getParameter("old_pw");
	String newpw = request.getParameter("new_user_pw");
	String id = (String)session.getAttribute("user_id");
	
	UserDAO dao = UserDAO.getInstance();
	
	if(dao.userCheck(id, oldpw)==1){
		dao.changePassword(id, newpw);
		%>
		<script>
			alert("비밀번호가 정상적으로 변경되었습니다.");
			lcation.href="user_mypage.jsp";
		</script>
	<%}else if(dao.userCheck(id, oldpw)==0){%>
		<script>
		alert("현재 비밀번호가 다릅니다.");
		history.back();
		</script>
	<%}else{%>
		<script>
		alert("비밀번호 변경에 실패했습니니다.");
		history.back();
		</script>
	<%}%>	
	
%>