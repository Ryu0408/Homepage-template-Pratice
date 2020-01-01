<%@page import="kr.co.ryu0408.user.model.UserVO"%>
<%@page import="kr.co.ryu0408.user.model.UserDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	/*
		1. 폼 액션으로 날아온 폼 데이터들(parameters)을 얻어옴.
		2. DAO객체를 공개된 메서드를 호출하여 주소값을 얻어오기
		3. 회원가입 승인 전에 이미 DB에 존재하는 ID인지 검사.
		String sql = "SELECT * FROM user WHERE user_id=?"
		이미 회우너가입된 id라면 aleart으로 아이디가 중복되었다고 경고한 후 뒤로가기 실행
		4. 가입된 회원이 안리 경우 객체에 포장 후 회원가입을 승인
		회원가입을 축하합니다ㅣ! -> location.href="user_login.jsp"
		회원가입 실패했습니다! -> 뒤로가기
	*/
	
	request.setCharacterEncoding("utf-8");//한글 인코딩
	
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String address = request.getParameter("address");
	
	UserDAO dao = UserDAO.getInstance();
	
	if(dao.confirmId(id)){ %>
		<script>
			alert("아이디가 중복되었습니다.");
			history.back();
		</script>
	<%}else{ //이미 가입된 회원이 없을 경우
		UserVO vo = new UserVO(id, pw, name, email, address);
	
		if(dao.insertMember(vo)){%>
		<script>
			alert("아이디가 중복되었습니다.");
			history.back();
		</script>
		<%}else{ %>
		<script>
			alert("회원가입에 실패했습니다.");
			history.back();
		</script>
		<%}
	}
%>