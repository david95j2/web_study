package com.dasol.hello;

public class BoardMain {

	public static void main(String[] args) {
		BoardDAO dao = new BoardDAOImpl();
		
		dao.insertBoard(new BoardVO("luke", "hi~", "hi~ everyone!"));
		
		
	}

}
