package kr.co.ryu.board.model;

import java.util.List;

public interface IBoardDAO {
	
	
	//DB에 작성글을 등록하는 메서드
	public void regist(String writer, String title, String content);
	
	//모든 게시글 정보를 가져오는 메서드(글 목록 리스트)
	public List<BoardVO> listBoard();
	
	//글 상세보기 메서드
	public BoardVO contentBoard(String bId);
}
