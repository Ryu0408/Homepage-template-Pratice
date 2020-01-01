<%@page import="kr.co.ryu0408.user.model.UserVO"%>
<%@page import="kr.co.ryu0408.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	/*
		1. 파라미터값 얻어오기
		2. DAO 주소값 얻어오기
		3. 로그인 유효성 검증 메서드(userCheck) 호출하기
		// 아이디가 없으면 스크립트로 경고장 출력 후 뒤로가기 진행
		// 비밀번호가 틀린 경우 비밀번호 틀렸다고 ㄱ경고장 출력 후 뒤로가기
		// 로그인 성공인 경우 user_mypage.jsp로 리다이렉팅
		// 이름과 id값으로 각각 세션 하나씩 생성(user_name, user_id)
	*/
	
	request.setCharacterEncoding("utf-8");

	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	
	UserDAO dao = UserDAO.getInstance();
	
	int result = dao.userCheck(id, pw);
	
	if(result==-1){%>
	<script>
		aleart("아이디가 존재하지 않습니다.");
		history.back();
	</script>
	<%}else if(result==0){%>
	<script>
		aleart("아이디가 비밀번호가 틀렸습니다.");
		history.back();		
	<%}else{ //로그인 성공
		UserVO vo = dao.getMemberInfo(id);
		String name = vo.getName();
	
		session.setAttribute("user_name", name);
		session.setAttribute("user_id", id);
		response.sendRedirect("user_mypage.jsp");
	}
%>