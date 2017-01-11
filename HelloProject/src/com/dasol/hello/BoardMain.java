package com.dasol.hello;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BoardMain {
	public static Scanner scan = new Scanner(System.in);
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	public static void main(String[] args) {
		BoardDAO dao = new BoardDAOImpl();
		List<BoardVO> boardList = new ArrayList<>();

		while(true) {
			boardList = dao.selectAllData();
			readBoardAll(boardList);
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("현재 " + null + "페이지 입니다...");
			System.out.println("[1]게시글쓰기 [2]게시글읽기 [3]게시글수정 [4]게시글삭제 [5]작성자검색 [6]페이지이동 [7]종료");
			String menuChoice = scan.nextLine();
			System.out.println(menuChoice);
			switch(menuChoice) {
			case "1":
				System.out.println("게시글 쓰기..");
			case "2":
				System.out.println("게시글 읽기..");
			case "3":
				System.out.println("게시글 수정..");
			case "4":
				System.out.println("게시글 삭제..");
			case "5":
				System.out.println("작성자 검색..");
			case "6":
				System.out.println("페이지 이동..");
			case "7":
				System.out.println("종료..");
			}
		}
		
		
//		dao.insertBoard(new BoardVO("luke", "hi~", "hi~ everyone!"));
		
		
	}
	
	public static void readBoardAll(List<BoardVO> boardList) {
		System.out.println("글번호" + "\t| " + "게시자" + "\t\t| " + "제목" + "\t\t\t| " + "게시날짜" + "\t\t\t| " + "조회수");
		for (BoardVO vo : boardList) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println(vo.getNum() + "\t| " + vo.getUsername() + "\t\t| " + vo.getTitle() + "\t\t\t| "
					+ sdf.format(vo.getTime()) + "\t| " + vo.getHit());
		}
	}

}
