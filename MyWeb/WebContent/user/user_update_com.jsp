<%@page import="kr.co.ryu0408.user.model.UserVO"%>
<%@page import="kr.co.ryu0408.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	/*
		1. 폼 데이터를 처리하세요
		2. DAO연동 통해 updateUser()라는 메서드를 호출하여 회우너정보를 수정합니다.
		회원정보 수정을 성공했다면 이름에 대한 세션도 다시 제작해주셔야합니다.
		3. 수정 성공시 "회원정보가 수정되었습니다,"를 스크립트로 출력 후 마이페이지로 이동시키세요
		4. 수정 실패시 "회원정보 수정에 실패했습니다." 출력 후 마이페이지 이동
		히든,으로 아이디 넘어옴
		String sql = "UPDATE user SET user_name=?", user_email=?, user_address=?, WHERE user_id=?"
	*/	
	request.setCharacterEncoding("utf-8");
	UserVO vo = new UserVO();
	vo.setId(request.getParameter("id"));
	vo.setName(request.getParameter("name"));
	vo.setEmail(request.getParameter("email"));
	vo.setAddress(request.getParameter("address"));
	
	UserDAO dao = UserDAO.getInstance();
	
	boolean flag = dao.updateUser(vo);
	
	if(flag){
	session.setAttribute("user_name", request.getParameter("name"));
	%>
	
	<script>
		alert("회원정보가 수정되었습니다.");
		location.href="user_mypage.jsp";	
	</script>
	<%}else{%>
	<script>
		alert("회원정보 수정에 실패했습니다.");
		location.href="user_mypage.jsp";	
	</script>	
	<%}%>
	
