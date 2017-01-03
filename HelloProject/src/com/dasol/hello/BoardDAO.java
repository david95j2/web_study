package com.dasol.hello;

import java.util.List;

public interface BoardDAO {
	
	public void insertBoard(BoardVO boardVO);
	public void deleteBoard(int num);
	public void updateBoard(int num);
	public List<BoardVO> selectData(int num);
	public List<BoardVO> selectAllData();

}
