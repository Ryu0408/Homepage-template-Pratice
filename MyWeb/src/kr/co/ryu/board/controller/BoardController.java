package kr.co.ryu.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import kr.co.ryu.board.service.IBoardService;
import kr.co.ryu.board.service.RegisterServiceImpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private IBoardService sv;

    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		uri = uri.substring(conPath.length()+1, uri.lastIndexOf("."));
		System.out.println(uri);
		
		switch(uri) {
		
		case "board/write":
			System.out.println("글쓰기 요청이 들어옴!");
			response.sendRedirect("board_write.jsp");
			break;
			
		case "board/regist":
			System.out.println("글등록 요청이 들어옴!");
			sv = new RegisterServiceImpl();
			sv.execute(request, response);
			response.sendRedirect("list.board");
			break;

		}
	
	
	
	
	
	}
}
