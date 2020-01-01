package kr.co.ryu0408.user.model;

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
	public boolean confirmId(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertMember(UserVO vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int userCheck(String id, String pw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserVO getMemberInfo(String id) {
		// TODO Auto-generated method stub
		return null;
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
