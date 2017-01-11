package com.dasol.hello;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BoardMain {

	public static void main(String[] args) {
		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> boardList = new ArrayList<>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		boardList = dao.selectAllData();
		
		System.out.println("글번호" + "\t| " + "게시자" + "\t\t| " +  "제목"
		 + "\t\t\t| " + "게시날짜" + "\t\t\t| " + "조회수");
		for(BoardVO vo : boardList) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println(vo.getNum() + "\t| " + vo.getUsername() + "\t\t| " +  vo.getTitle()
			 + "\t\t\t| " + sdf.format(vo.getTime()) + "\t| " + vo.getHit());
		}
		
//		dao.insertBoard(new BoardVO("luke", "hi~", "hi~ everyone!"));
		
		
		
		
	}

}
