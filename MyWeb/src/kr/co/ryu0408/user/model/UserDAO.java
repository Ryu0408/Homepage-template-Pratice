package kr.co.ryu0408.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDAO implements IUserDAO {
	
	DataSource ds;
	//싱글톤 디자인 패턴
	
	//1. 싱글톤 객체 생성
	private static UserDAO dao = new UserDAO();
	
	private UserDAO() {
		//커넥션 풀 구하기
		//커넥션 객체 안만들어도됨!
		try {
			Context ct = new InitialContext();
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//3. 외부에서 객체 요구시 공개된 메서드를 통해 가지고 있던 DAO객체의 주소값 변환
	
	public static UserDAO getInstance() {
		if(dao==null) {
			dao = new UserDAO();
		}
		return dao;
	}
	
	
	
	//2. 외부에서 객체생성 할 수 없도록 생성자에 private 제한 붙이기
	@Override
	public boolean confirmId(String id)  {
		boolean flag = false;
		//자바 8버전부터 추가된 try안에 객체를 생성하면 try가 끝나면 자동으로 닫아줌.
		String sql = "SELECT * FROM user WHERE user_id=?";
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) flag = true; //아이디가 존재함
			else flag = false; //아이디가 존재함
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public boolean insertMember(UserVO vo) {
		boolean flag = false;
		String sql = "INSERT INTO user VALUES(?,?,?,?,?)";
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			// 객체를 보내주고 있어서 getter를 통해서 받아야함
			pstmt.setString(1, vo.getId());
			pstmt.setString(1, vo.getPw());
			pstmt.setString(1, vo.getName());
			pstmt.setString(1, vo.getEmail());
			pstmt.setString(1, vo.getAddress());
			
			if(pstmt.executeUpdate() == 1) flag = true;
			else flag = false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public int userCheck(String id, String pw) {
		// 아이디가 있는지를 조회하여 그 아이디에 같이 매핑되어있는
		// 비밀번호를 얻은 후, 매개값으로 받은 비밀번호와 같은지 대조하여
		// return값을 조정해 주시면 되겠습니다.
		String sql = "SELECT * FROM user WHERE user_id=?";
		int check = 0;
		
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			// 객체를 보내주고 있어서 getter를 통해서 받아야함
			pstmt.setString(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {//아이디가 존재함
				String dbPw = rs.getString("user_pw");//DB에 저장된 비밀번호
				if(dbPw.contentEquals(pw)) {
					check = 1;
				}else { // 비밀번호가 다를경우
					check = 0;
				}
			}else {//아이디가 존재하지 않음
				check = -1;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public UserVO getMemberInfo(String id) {
		String sql = "SELECT * FROM user WHERE user_id=?";
		UserVO vo = null;
		try(Connection conn = ds.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("user_id"));
				vo.setPw(rs.getString("user_pw"));
				vo.setName(rs.getString("user_name"));
				vo.setEmail(rs.getString("user_email"));
				vo.setAddress(rs.getString("user_address"));
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return vo;
	}

	@Override
	public boolean changePassword(String id, String pw) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(UserVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
