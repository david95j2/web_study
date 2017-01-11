package com.dasol.hello;

import java.util.List;

public interface BoardDAO {
	
	public void insertBoard(String bid, String title, String memo);
	public void deleteBoard(int num);
	public void updateBoard(int num, String title, String memo);
	public List<BoardVO> searchUserBoard(String bid, int pageNum);
	public BoardVO selectData(int num);
	public List<BoardVO> selectAllData(int pageNum);

}
