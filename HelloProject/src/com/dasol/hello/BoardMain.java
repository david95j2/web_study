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
		int pageNum = 20;
		while(true) {
			boardList = dao.selectAllData(pageNum);
			readBoardAll(boardList, dao, pageNum); // cls? 추가
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("현재 " + (pageNum/10) + "페이지 입니다...");
			System.out.println("[1]게시글쓰기 [2]게시글읽기 [3]작성자검색 [4]페이지이동 [5]종료");
			String menuChoice = scan.nextLine();
			switch(menuChoice) {
			case "1":
				System.out.println("게시글 쓰기..");
				insertBoard(dao);
				break;
			case "2":
				System.out.println("게시글 읽기..");
				readBoard(dao);
				break;
			case "3":
				System.out.println("작성자 검색..");
				searchUserBoard(dao);
				break;
			case "4":
				System.out.println("페이지 이동..");
				pageNum = movePage(pageNum);
				break;
			case "5":
				System.out.println("종료..");
				System.out.println("이용해 주셔서 감사합니다.");
				System.exit(0);
			}
		}
		
	}
	
	public static void readBoardAll(List<BoardVO> boardList, BoardDAO dao, int pageNum) { //boardList size
		System.out.println("글번호" + "\t| " + "게시자" + "\t\t| " + "제목" + "\t\t\t| " + "게시날짜" + "\t\t\t| " + "조회수");
		for (BoardVO vo : boardList) {
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println(vo.getNum() + "\t| " + vo.getUsername() + "\t\t| " + vo.getTitle() + "\t\t\t| "
					+ sdf.format(vo.getTime()) + "\t| " + vo.getHit());
		}
	}
	
	public static void insertBoard(BoardDAO dao) {
		System.out.print("게시자ID : ");
		String id = scan.nextLine();
		System.out.print("글제목 : ");
		String title = scan.nextLine();
		System.out.print("글내용 : ");
		String memo = scan.nextLine();
		
		dao.insertBoard(id, title, memo);
	}
	
	public static void readBoard(BoardDAO dao) { //검색값 없을 때 null포인터
		BoardVO vo = null;
		System.out.print("보고싶은 게시글 번호 : ");
		String boardNum = scan.nextLine();
		
		while (true) {
			vo = dao.selectData(Integer.parseInt(boardNum)); // 조회수 손보기...
			System.out.println("------------------------------------------------------");
			System.out.println("글번호 : " + vo.getNum() + " | " + "게시날짜 : " 
									+ sdf.format(vo.getTime()) + "  | 조회수 : " + vo.getHit());
			System.out.println("------------------------------------------------------");
			System.out.println("제목 : " + vo.getTitle() + "\t\t\t\t| " + "게시자 : " + vo.getUsername());
			System.out.println("------------------------------------------------------");
			System.out.println("글내용 : " + vo.getMemo());
			System.out.println("------------------------------------------------------");
			System.out.println("[1]뒤로 돌아가기 [2]게시글 수정 [3]게시글 삭제");
			String menuChoice = scan.nextLine();
			if (menuChoice.equals("1")) {
				System.out.println("뒤로 돌아가기..");
				break;
			} else if (menuChoice.equals("2")) {
				System.out.println("게시글수정..");
				updateBoard(dao, Integer.parseInt(boardNum));
			} else if (menuChoice.equals("3")) {
				System.out.println("게시글 삭제..");
				deleteBoard(dao, Integer.parseInt(boardNum));
				break;
			} else
				System.out.println("재선택..");
		}
		
	}
	
	public static void updateBoard(BoardDAO dao, int boardNum) {
		System.out.print("글제목 : ");
		String title = scan.nextLine();
		System.out.print("글내용 : ");
		String memo = scan.nextLine();
		dao.updateBoard(boardNum, title, memo);
	}
	
	public static void deleteBoard(BoardDAO dao, int boardNum) {
		System.out.println("현재 게시글을 삭제하시겠습니까? (y/n)");
		String isdelete = scan.nextLine();
		if (isdelete.toLowerCase().equals("y")) { // 리턴 손보기
			dao.deleteBoard(boardNum);
		}
	}
	
	public static void searchUserBoard(BoardDAO dao) {
		List<BoardVO> boardList = null;
		int pageNum = 0;
		System.out.print("게시자ID : ");
		String bid = scan.nextLine();
		while (true) {
			boardList = dao.searchUserBoard(bid, pageNum);
			readBoardAll(boardList, dao, pageNum);
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("현재 " + (pageNum / 10) + "페이지 입니다...");
			System.out.println("[1]되돌아가기 [2]게시글읽기  [3]페이지이동");
			String menuChoice = scan.nextLine();
			if (menuChoice.equals("1"))
				break;
			else if (menuChoice.equals("2")) {
				readBoard(dao);
			} else if (menuChoice.equals("3")) {
				pageNum = movePage(pageNum);
			}
		}
	}

	public static int movePage(int pageNum) {
		int rtvNum = 0;
		System.out.println("[<]이전 10 페이지  [>]다음 10페이지");
		String pageCursor = scan.nextLine();
		
		if (pageNum != 0) { 
			if(pageCursor.equals("<"))
				rtvNum = pageNum - 10;
			else if(pageCursor.equals(">"))
				rtvNum = pageNum + 10;
		} else if (pageNum == 0) {
			if(pageCursor.equals("<"))
				rtvNum = 0;
			else if(pageCursor.equals(">"))
				rtvNum = pageNum + 10;
		}
		
		return rtvNum;
	}
}
