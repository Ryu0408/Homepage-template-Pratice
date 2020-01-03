package com.jsp.servlet.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.co.ryu0408.user.model.UserDAO;
import kr.co.ryu0408.user.model.UserVO;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 1. 메개값으로 돌아온 request객체에서 입력값을 받아서 변수에 저장
		 * 
		 * 2. UserDAO 객체의 주소값을 받아서 로그인 로직 메서드 호출(userCheck)
		 * 
		 * 3. user.getMemberInfo()메서드를 호출하여 회원의 정보를 얻어온 후 
		 * user_name, user_id 이름으로 ssession을 제작
		 * 
		 * 4. redirect -> user_mypage.jsp
		 */
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		
		int result = dao.userCheck(id, pw);
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String htmlCode;
		if(result==-1){
			htmlCode ="<script>\r\n"
					+ "aleart(\"아이디가 존재하지 않습니다.\");\r\n"
					+ "history.back();"
					+ "</script>";
		}else if(result==0) {
				htmlCode ="<script>\r\n"
						+ "aleart(\"아이디가 비밀번호가 틀렸습니다.\");\r\n"
						+ "history.back();"
						+ "</script>";
			}
		else{ //로그인 성공
			UserVO vo = dao.getMemberInfo(id);
			String name = vo.getName();
			HttpSession session = request.getSession();
			session.setAttribute("user_name", name);
			session.setAttribute("user_id", id);
			response.sendRedirect("user_mypage.jsp");
		}
		out.flush();
		out.close();
	}

}
