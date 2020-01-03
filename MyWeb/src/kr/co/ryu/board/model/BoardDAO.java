package kr.co.ryu.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class BoardDAO implements IBoardDAO {
	
	DataSource ds;
	//싱글톤 패턴
	
	//1. 스스로의 객체 생성
	private static BoardDAO boardDAO = new BoardDAO();
	
	//2. 생성자에 private을 붙여서 외부에서 객체 생성 막기.
	private BoardDAO() {
		try {
			Context ct = new InitialContext();
			ds = (DataSource) ct.lookup("java:comp/env/jdbc/mysql");
		}catch(Exception e) {
		e.printStackTrace();
		}
	}

	//3. 외부에서 객체 주소값 요구 시 공개된 메서드를 통해 DAO의 주소값을 변환
	
	public static BoardDAO getInstance() {
		if(boardDAO == null) {
			boardDAO = new BoardDAO();
		}
		return boardDAO;
	}
	@Override
	public void regist(String writer, String title, String content) {
		String sql = "INSERT INTO my_board(writer, title, content) VALUE(?,?,?)";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, writer);
			pstmt.setString(2, title);
			pstmt.setString(3, content);
			
			pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<BoardVO> listBoard() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BoardVO contentBoard(String bId) {
		// TODO Auto-generated method stub
		return null;
	}

}
